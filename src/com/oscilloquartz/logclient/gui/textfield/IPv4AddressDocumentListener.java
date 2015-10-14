package com.oscilloquartz.logclient.gui.textfield;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 */
public class IPv4AddressDocumentListener implements DocumentListener {

    @Override
    public void insertUpdate(DocumentEvent e) {
        Document document = e.getDocument();
        try {
            JTextField textField = (JTextField) FocusManager.getCurrentManager().getFocusOwner();

            String s = document.getText(0, document.getLength());

            if (s.length() == 4){ // && textField.getCaretPosition() == 2) {
                textField.transferFocus();


            }

        } catch (BadLocationException e1) {
            e1.printStackTrace();
            return;
        }

    }

    public void removeUpdate(DocumentEvent e) {
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        //          Document document = e.getDocument();
        //          try {
        //              Component component = FocusManager.getCurrentManager().getFocusOwner();
        //              String s = document.getText(0, document.getLength());
        //
        //              // get selected integer
        //              int valueInt = Integer.parseInt(s);
        //
        //              if (valueInt > 25) {
        //                  component.transferFocus();
        //              }
        //
        //          } catch (BadLocationException e1) {
        //              e1.printStackTrace();
        //              return;
        //          }
    }
}
