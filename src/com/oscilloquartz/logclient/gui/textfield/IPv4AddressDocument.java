package com.oscilloquartz.logclient.gui.textfield;

import javax.swing.*;

/**
 *
 */
public class IPv4AddressDocument extends IntegerDocument {

    private int             index;
    private JTextField[]    ipAddressField;

    public IPv4AddressDocument(int index, JTextField[] ipAddressField) {
        super(3, 0, 255);

        this.index          = index;
        this.ipAddressField = ipAddressField;
    }

    @Override
    public void performLengthAction(int offs, String inserted, String full) {
        this.ipAddressField[index].transferFocus();
    }

    @Override
    public void performNotNumberAction(int offs, String inserted, String full) {
        //
    }

    @Override
    public void performMinMaxAction(int offs, String inserted, String full) {
        this.ipAddressField[index].transferFocus();
    }
}
