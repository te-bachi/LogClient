package com.oscilloquartz.logclient.gui;

import com.oscilloquartz.logclient.gui.textfield.IPv4AddressTextField;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 */
public class ConnectPanel extends JPanel {

    private JLabel                  ipAddressLabel;
    private IPv4AddressTextField    ipAddressField;
    private JButton                 connectButton;

    public ConnectPanel(ConnectList connectList) {
        String                  text = null;

        setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        setLayout(new FlowLayout(FlowLayout.LEFT));

        if (connectList.size() > 0) {
            text = connectList.iterator().next();
        }

        ipAddressLabel  = new JLabel("IPv4 Address: ");
        connectButton   = new JButton("Connect");
        connectButton.setEnabled(false);
        ipAddressField  = new IPv4AddressTextField(connectButton, text);

        connectButton.setPreferredSize(new Dimension(100, 20));
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectList.push(ipAddressField.getText());
            }
        });

        add(ipAddressLabel);
        add(ipAddressField);
        add(connectButton);
    }

    public void checkFocus() {
        if (connectButton.isEnabled()) {
            connectButton.requestFocusInWindow();
        }
    }
}
