package com.oscilloquartz.logclient.gui.textfield.demo3;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class AddingDocumentListenerJTextFieldSample {

    private class IntDocument extends PlainDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

            System.out.println("offs=" + offs + " str=" + str + "");
            super.insertString(offs, str, a);
        }
    }

    public static void main(String args[]) {
        final JFrame frame = new JFrame("Default Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField textField = new JTextField();
        frame.add(textField, BorderLayout.NORTH);

        JTextField textField2 = new JTextField();
        frame.add(textField2, BorderLayout.SOUTH);

        DocumentListener documentListener = new DocumentListener() {
            public void changedUpdate(DocumentEvent documentEvent) {
                printIt(documentEvent);
            }
            public void insertUpdate(DocumentEvent documentEvent) {
                printIt(documentEvent);
            }
            public void removeUpdate(DocumentEvent documentEvent) {
                printIt(documentEvent);
            }
            private void printIt(DocumentEvent documentEvent) {
                DocumentEvent.EventType type = documentEvent.getType();
                String typeString = null;
                if (type.equals(DocumentEvent.EventType.CHANGE)) {
                    typeString = "Change";
                }  else if (type.equals(DocumentEvent.EventType.INSERT)) {
                    typeString = "Insert";
                }  else if (type.equals(DocumentEvent.EventType.REMOVE)) {
                    typeString = "Remove";
                }
                System.out.print("Type: " + typeString);
                Document source = documentEvent.getDocument();
                int length = source.getLength();
                System.out.print(" Length: " + length);
                try {
                    String text = source.getText(0, length);
                    System.out.println(" Text: " + text);
                } catch (BadLocationException e) {
                    System.out.println("BadLocationException");
                }
            }
        };


        textField.getDocument().addDocumentListener(documentListener);

        frame.setSize(250, 150);
        frame.setVisible(true);
    }
}