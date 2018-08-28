package com.oscilloquartz.logclient.gui;

import com.oscilloquartz.logclient.GuiMain;
import com.oscilloquartz.logclient.fsm.StateMachine;
import com.oscilloquartz.logclient.gui.statusbar.StatusBar;
import com.oscilloquartz.logclient.net.Network;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Set;

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
        imageList.add(new ImageIcon(GuiMain.class.getResource("images/icon64x64.png")).getImage());
        imageList.add(new ImageIcon(GuiMain.class.getResource("images/icon40x40.png")).getImage());
        imageList.add(new ImageIcon(GuiMain.class.getResource("images/icon32x32.png")).getImage());
        imageList.add(new ImageIcon(GuiMain.class.getResource("images/icon20x20.png")).getImage());

        //setDefaultSize(24);
        //LogClientUtil.applyHiDPIOnFonts();

        setTitle("OSA Log Client v1.02");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImages(imageList);
        setLocationByPlatform(true);
        setLayout(new BorderLayout());
        addWindowListener(this);

        setMinimumSize(new Dimension(LogClientUtil.scale(600), LogClientUtil.scale(400)));
        setPreferredSize(new Dimension(LogClientUtil.scale(800), LogClientUtil.scale(600)));

        setJMenuBar(new MainMenuBar(this, connectList));

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

    public void setDefaultSize(int size) {
        Set<Object> keySet = UIManager.getLookAndFeelDefaults().keySet();
        Object[] keys = keySet.toArray(new Object[keySet.size()]);

        for (Object key : keys) {

            if (key != null && key.toString().toLowerCase().contains("font")) {

                System.out.println(key);
                Font font = UIManager.getDefaults().getFont(key);
                if (font != null) {
                    font = font.deriveFont((float)size);
                    UIManager.put(key, font);
                }
            }
        }
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

    public void exit() {
        processEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
