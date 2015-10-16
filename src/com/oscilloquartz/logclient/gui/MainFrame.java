package com.oscilloquartz.logclient.gui;

import com.oscilloquartz.logclient.Main;
import com.oscilloquartz.logclient.gui.statusbar.StatusBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.prefs.Preferences;

/**
 *
 */
public class MainFrame extends JFrame implements WindowListener {

    private ArrayList<Image>    imageList;
    private Container           contentPane;
    private ConnectList         connectList;
    private ConnectPanel        connectPanel;
    private LogPanel            logPanel;
    private StatusBar           statusBar;

    public MainFrame() {

        connectList = new ConnectList();

        imageList = new ArrayList<>();
        imageList.add(new ImageIcon(Main.class.getResource("images/icon64x64.png")).getImage());
        imageList.add(new ImageIcon(Main.class.getResource("images/icon40x40.png")).getImage());
        imageList.add(new ImageIcon(Main.class.getResource("images/icon32x32.png")).getImage());
        imageList.add(new ImageIcon(Main.class.getResource("images/icon20x20.png")).getImage());

        setTitle("OSA Log Client");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImages(imageList);
        setLocationByPlatform(true);
        setLayout(new BorderLayout());
        addWindowListener(this);

        setMinimumSize(new Dimension(600, 400));
        setPreferredSize(new Dimension(800, 600));

        setJMenuBar(new MainMenuBar(connectList));

        connectPanel    = new ConnectPanel(connectList);
        logPanel        = new LogPanel();
        statusBar       = new StatusBar();

        contentPane = getContentPane();
        contentPane.add(connectPanel, BorderLayout.NORTH);
        contentPane.add(logPanel, BorderLayout.CENTER);
        contentPane.add(statusBar, BorderLayout.SOUTH);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        connectPanel.checkFocus();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        connectList.save();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
