package com.oscilloquartz.logclient.gui;

import com.oscilloquartz.logclient.gui.textfield.IPv4AddressTextField;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 */
public class ConnectPanel extends JPanel {

    private JLabel                  ipAddressLabel;
    private IPv4AddressTextField    ipAddressField;
    private JButton                 connectButton;
    private JCheckBox               autoscrollCheckBox;

    public ConnectPanel(MainFrame mainFrame) {
        String                  text = null;

        setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        setLayout(new FlowLayout(FlowLayout.LEFT));

        if (mainFrame.getConnectList().size() > 0) {
            text = mainFrame.getConnectList().iterator().next();
        }

        ipAddressLabel  = new JLabel("IPv4 Address: ");
        connectButton   = new JButton("Connect");
        connectButton.setEnabled(false);
        ipAddressField  = new IPv4AddressTextField(connectButton, text);

        connectButton.setPreferredSize(new Dimension(100, 20));
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getConnectList().push(ipAddressField.getText());
                mainFrame.getStateMachine().connect();
            }
        });

        autoscrollCheckBox = new JCheckBox("Autoscroll");
        autoscrollCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                mainFrame.getLogPanel().getTable().setAutoscroll(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
        add(ipAddressLabel);
        add(ipAddressField);
        add(connectButton);
        add(autoscrollCheckBox);
    }

    public String getHostname() {
        return ipAddressField.getText();
    }

    public void checkFocus() {
        if (connectButton.isEnabled()) {
            connectButton.requestFocusInWindow();
        }
    }
}
