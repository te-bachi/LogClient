package com.oscilloquartz.logclient.gui;

import com.oscilloquartz.logclient.gui.textfield.IPv4AddressTextField;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;

/**
 *
 */
public class ConnectPanel extends JPanel {

    public ConnectPanel() {
        JLabel                  ipAddressLabel;
        IPv4AddressTextField    ipAddressField;
        JButton                 connectButton;

        setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        setLayout(new FlowLayout(FlowLayout.LEFT));

        ipAddressLabel  = new JLabel("IPv4 Address: ");
        ipAddressField  = new IPv4AddressTextField();
        connectButton   = new JButton("Connect");

        add(ipAddressLabel);
        add(ipAddressField);
        add(connectButton);
    }
}
