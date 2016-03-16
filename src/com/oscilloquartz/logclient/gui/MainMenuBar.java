package com.oscilloquartz.logclient.gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 */
public class MainMenuBar extends JMenuBar implements Observer {

    private MainFrame   frame;

    private JMenu       fileMenu;
    private JMenu       connectMenu;
    private JMenuItem   saveAsItem;
    private JMenuItem   exitItem;

    private JMenu       editMenu;
    private JMenuItem   copyItem;
    private JMenuItem   clearItem;

    private JMenu       helpMenu;
    private JMenuItem   aboutItem;

    public MainMenuBar(MainFrame frame, ConnectList connectList) {
        super();
        this.frame = frame;

        fileMenu    = new JMenu("File");
        connectMenu = new JMenu("Connect");
        saveAsItem  = new JMenuItem("Save as...");
        saveAsItem.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { saveAs(e); } });
        exitItem    = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { exit(e); } });
        connectMenu.setEnabled(false);
        fileMenu.add(connectMenu);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        fileMenu.setMnemonic('F');
        add(fileMenu);

        editMenu    = new JMenu("Edit");
        copyItem    = new JMenuItem("Copy all to clipboard");
        copyItem.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { copyAllToClipboard(e); } });
        clearItem   = new JMenuItem("Clear screen");
        clearItem.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { clearScreen(e); } });
        editMenu.add(copyItem);
        editMenu.add(clearItem);
        editMenu.setMnemonic('E');
        add(editMenu);

        helpMenu    = new JMenu("Help");
        aboutItem   = new JMenuItem("About");
        helpMenu.add(aboutItem);
        helpMenu.setMnemonic('H');

        add(Box.createHorizontalGlue());
        add(helpMenu);

        connectList.addObserver(this);
        updateConnectMenu(connectList);
    }

    @Override
    public void update(Observable o, Object arg) {
        updateConnectMenu((ConnectList) o);
    }

    public void updateConnectMenu(ConnectList connectList) {
        JMenuItem menuItem;

        connectMenu.removeAll();
        if (connectList.size() > 0) {
            for (String ipAddress : connectList) {
                menuItem = new JMenuItem(ipAddress);
                connectMenu.add(menuItem);
            }
            connectMenu.setEnabled(true);
        }
    }

    private void saveAs(ActionEvent e) {
        JFileChooser fc = new JFileChooser(){
            @Override
            public void approveSelection(){
                File f = getSelectedFile();

                /* extension match */
                FileNameExtensionFilter filter = (FileNameExtensionFilter) getFileFilter();
                if (!filter.accept(f)) {
                    JOptionPane.showMessageDialog(this,"File has no extension!","Use Extension", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                /* overwrite */
                if(f.exists() && getDialogType() == SAVE_DIALOG){
                    int result = JOptionPane.showConfirmDialog(this,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
                    switch(result){
                        case JOptionPane.YES_OPTION:
                            super.approveSelection();
                            return;
                        case JOptionPane.NO_OPTION:
                            return;
                        case JOptionPane.CLOSED_OPTION:
                            return;
                        case JOptionPane.CANCEL_OPTION:
                            cancelSelection();
                            return;
                    }
                }
                super.approveSelection();
            }
        };
        //fc.setFileView(new CsvFileView());
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Comma Separated Values File (*.csv)", "csv"));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Text File (*.txt)", "txt"));
        fc.setAcceptAllFileFilterUsed(false);

        switch(fc.showSaveDialog(this)) {
            case JFileChooser.APPROVE_OPTION:
                File f = fc.getSelectedFile();
                try {
                    JTable table = frame.getLogPanel().getTable();
                    BufferedWriter out = new BufferedWriter(new FileWriter(f));
                    String extension = ((FileNameExtensionFilter) fc.getFileFilter()).getExtensions()[0];
                    int numcols = table.getColumnCount();
                    int numrows = table.getRowCount();
                    if (extension.equalsIgnoreCase("csv")) {
                        for (int i = 0; i < numrows; i++) {
                            for (int j = 0; j < numcols; j++) {
                                out.append("\"");
                                out.append(table.getValueAt(i, j).toString());
                                out.append("\"");
                                if (j < numcols - 1) out.append(";");
                            }
                            out.append("\n");
                        }
                    } else if (extension.equalsIgnoreCase("txt")) {
                        for (int i = 0; i < numrows; i++) {
                            for (int j = 0; j < numcols; j++) {
                                if (j < numcols - 2) out.append("[");
                                out.append(table.getValueAt(i, j).toString());
                                if (j < numcols - 2) out.append("]");
                                if (j < numcols - 1) out.append(" ");
                            }
                            out.append("\n");
                        }
                    }
                    out.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                System.out.println("Cancel");
        }

    }

    private void exit(ActionEvent e) {
        frame.exit();
    }

    private void copyAllToClipboard(ActionEvent e) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stsel;
        JTable table = frame.getLogPanel().getTable();

        StringBuffer sbf=new StringBuffer();
        // Check to ensure we have selected only a contiguous block of
        // cells
        int numcols=table.getColumnCount();
        int numrows=table.getRowCount();

        for (int i=0;i<numrows;i++)
        {
            for (int j=0;j<numcols;j++)
            {
                sbf.append(table.getValueAt(i,j));
                if (j<numcols-1) sbf.append("\t");
            }
            sbf.append("\n");
        }
        stsel  = new StringSelection(sbf.toString());
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stsel,stsel);
    }

    private void clearScreen(ActionEvent e) {
        frame.getLogPanel().getTable().getDefaultTableModel().setRowCount(0);
    }
}
