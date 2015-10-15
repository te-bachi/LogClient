package com.oscilloquartz.logclient.gui.textfield.demo3;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class AddingDocumentListenerJTextFieldSample {

    private JFrame frame;

    public class IntTestDocument extends PlainDocument {

        private int min;
        private int max;

        public IntTestDocument(int min, int max) {
            super();
            this.min = min;
            this.max = max;
        }

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            String oldString;
            String newString;
            int number;

            if (this.getLength() + str.length() > 10) {
                return;
            }

            oldString = this.getText(0, this.getLength());
            newString = oldString.substring(0, offs) + str + oldString.substring(offs, this.getLength());

            try {
                number = Integer.parseInt(newString);
            } catch (NumberFormatException e) {
                return;
            }

            if (number < this.min || number > this.max) {
                return;
            }

            System.out.println("offs=" + offs + " str=" + str + " total=" + newString);
            super.insertString(offs, str, a);
        }


    }

    public AddingDocumentListenerJTextFieldSample() {
        frame = new JFrame("Default Example");
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

        textField.setDocument(new IntTestDocument(0, 255));
        textField.getDocument().addDocumentListener(documentListener);

        frame.setSize(250, 150);
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                AddingDocumentListenerJTextFieldSample frame = new AddingDocumentListenerJTextFieldSample();
            }
        });
    }
}