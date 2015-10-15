package com.oscilloquartz.logclient.gui.textfield;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 *
 */
public class IPv4AddressFieldFocusListener implements FocusListener {

    private JTextField ipAddressByteField;

    public IPv4AddressFieldFocusListener(JTextField ipAddressByteField) {
        this.ipAddressByteField = ipAddressByteField;
    }

    @Override
    public void focusGained(FocusEvent e) {
        ipAddressByteField.setSelectionStart(0);
        ipAddressByteField.setSelectionEnd(ipAddressByteField.getText().length());
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
