package com.oscilloquartz.logclient.gui.textfield;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 */
public class IPv4AddressDocumentListener implements DocumentListener {

    private IPv4AddressDocument doc;

    public IPv4AddressDocumentListener(IPv4AddressDocument doc) {
        this.doc = doc;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        checkFields();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        checkFields();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        System.out.println("bla");
    }

    private void checkFields() {
        int i;

        if (doc.ipAddressField[doc.index].getText().length() >= 3) {
            doc.ipAddressField[doc.index].transferFocus();
        }

        for (i = 0; i < doc.ipAddressField.length; i++) {
            if (doc.ipAddressField[i].getText().equals("")) {
                doc.connectButton.setEnabled(false);
                return;
            }
        }
        doc.connectButton.setEnabled(true);
    }
}
