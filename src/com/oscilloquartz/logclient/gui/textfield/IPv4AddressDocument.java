package com.oscilloquartz.logclient.gui.textfield;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

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

        if ((index = str.indexOf('.')) != -1) {
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
