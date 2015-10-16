package com.oscilloquartz.logclient.gui.textfield;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 */
public class IntegerDocument extends PlainDocument {

    private int len;
    private int min;
    private int max;

    public IntegerDocument(int len, int min, int max) {
        super();
        this.len = len;
        this.min = min;
        this.max = max;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        String  oldString;
        String  newString;
        int     number;

        oldString = this.getText(0, this.getLength());
        newString = oldString.substring(0, offs) + str + oldString.substring(offs, this.getLength());

        if (this.getLength() + str.length() > len) {
            performLengthAction(offs, str, a, newString);
            return;
        }

        try {
            number = Integer.parseInt(newString);
        } catch (NumberFormatException e) {
            performNotNumberAction(offs, str, a, newString);
            return;
        }

        if (number < this.min || number > this.max) {
            performMinMaxAction(offs, str, a, newString);
            return;
        }

        super.insertString(offs, str, a);

    }

    public void performLengthAction(int offs, String str, AttributeSet a, String newString) throws BadLocationException {

    }

    public void performNotNumberAction(int offs, String str, AttributeSet a, String newString) throws BadLocationException {

    }

    public void performMinMaxAction(int offs, String str, AttributeSet a, String newString) throws BadLocationException {

    }
}
