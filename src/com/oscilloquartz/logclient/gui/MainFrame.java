package com.oscilloquartz.logclient.gui;

import com.oscilloquartz.logclient.Main;
import com.oscilloquartz.logclient.fsm.StateMachine;
import com.oscilloquartz.logclient.gui.statusbar.StatusBar;
import com.oscilloquartz.logclient.net.Network;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.net.SocketException;
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

    private StateMachine        stateMachine;
    private Network             network;

    public MainFrame() {

        stateMachine    = new StateMachine(this);
        connectList     = new ConnectList();

        imageList = new ArrayList<>();
        imageList.add(new ImageIcon(Main.class.getResource("images/icon64x64.png")).getImage());
        imageList.add(new ImageIcon(Main.class.getResource("images/icon40x40.png")).getImage());
        imageList.add(new ImageIcon(Main.class.getResource("images/icon32x32.png")).getImage());
        imageList.add(new ImageIcon(Main.class.getResource("images/icon20x20.png")).getImage());

        setTitle("OSA Log Client v1.01");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImages(imageList);
        setLocationByPlatform(true);
        setLayout(new BorderLayout());
        addWindowListener(this);

        setMinimumSize(new Dimension(600, 400));
        setPreferredSize(new Dimension(800, 600));

        setJMenuBar(new MainMenuBar(connectList));

        connectPanel    = new ConnectPanel(this);
        logPanel        = new LogPanel();
        statusBar       = new StatusBar();

        statusBar.setState(stateMachine.getCurrentState().getStateName());
        statusBar.setText("Ready");

        contentPane = getContentPane();
        contentPane.add(connectPanel, BorderLayout.NORTH);
        contentPane.add(logPanel, BorderLayout.CENTER);
        contentPane.add(statusBar, BorderLayout.SOUTH);
    }

    public boolean setupNetwork() {
        boolean result = true;
        try {
            network = new Network(this);
        } catch (SocketException e) {
            //ExceptionDialog dialog = new ExceptionDialog(this, "Exception in startup", e.getMessage(), e);

            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            result = false;
        }
        return result;
    }

    public String getHostname() {
        return connectPanel.getHostname();
    }

    public StateMachine getStateMachine() {
        return stateMachine;
    }

    public Network getNetwork() {
        return network;
    }

    public ConnectList getConnectList() {
        return connectList;
    }

    public LogPanel getLogPanel() {
        return logPanel;
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }

    public void close() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
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
