package com.oscilloquartz.logclient.gui.textfield;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 */
public class IntegerDocument extends PlainDocument {

    private int min;
    private int max;

    public IntegerDocument(int min, int max) {
        super();
        this.min = min;
        this.max = max;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        int number;

        try {
            number = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return;
        }

        if (number < this.min || number > this.max) {
            return;
        }

        super.insertString(offs, str, a);
    }
}
