package com.oscilloquartz.logclient.gui.textfield;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 *
 */
public class IPv4AddressDocument extends IntegerDocument {

    protected int           index;
    protected JTextField[]  ipAddressField;
    protected JButton       connectButton;

    public IPv4AddressDocument(int index, JTextField[] ipAddressField, JButton connectButton) {
        super(3, 0, 255);

        addDocumentListener(new IPv4AddressDocumentListener(this));

        this.index          = index;
        this.ipAddressField = ipAddressField;
        this.connectButton  = connectButton;
    }

    @Override
    public void performLengthAction(int offs, String str, AttributeSet a, String newString) throws BadLocationException {
        if (!checkPoint(str)) {
            this.ipAddressField[index].transferFocus();
        }
    }

    @Override
    public void performNotNumberAction(int offs, String str, AttributeSet a, String newString) throws BadLocationException {
        checkPoint(str);
    }

    @Override
    public void performMinMaxAction(int offs, String str, AttributeSet a, String newString) throws BadLocationException {
        this.ipAddressField[index].transferFocus();
    }

    private boolean checkPoint(String str) {
        int     index;
        String  substring;

        index = str.indexOf('.');

        if (index != -1 && index != 0) {
            substring = str.substring(0, index);
            this.ipAddressField[this.index].setText(substring);
            if (this.index < (IPv4AddressTextField.IPV4_ADDRESS_LENGTH - 1)) {
                substring = str.substring(index + 1, str.length());
                this.ipAddressField[this.index + 1].setText(substring);
            }

            if (this.index == (IPv4AddressTextField.IPV4_ADDRESS_LENGTH - 2)) {
                this.ipAddressField[this.index + 1].transferFocus();
            }

            return true;
        }

        return false;
    }
}
