package com.oscilloquartz.logclient.gui;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 */
public class MainMenuBar extends JMenuBar implements Observer {

    private JMenu       fileMenu;
    private JMenu       connectMenu;
    private JMenuItem   exitItem;

    private JMenu       editMenu;
    private JMenuItem   copyItem;
    private JMenuItem   clearItem;

    private JMenu       helpMenu;
    private JMenuItem   aboutItem;

    public MainMenuBar(ConnectList connectList) {
        super();

        fileMenu    = new JMenu("File");
        connectMenu = new JMenu("Connect");
        exitItem    = new JMenuItem("Exit");
        connectMenu.setEnabled(false);
        fileMenu.add(connectMenu);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        fileMenu.setMnemonic('F');
        add(fileMenu);

        editMenu    = new JMenu("Edit");
        copyItem    = new JMenuItem("Copy all to clipboard");
        clearItem   = new JMenuItem("Clear screen");
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
}
