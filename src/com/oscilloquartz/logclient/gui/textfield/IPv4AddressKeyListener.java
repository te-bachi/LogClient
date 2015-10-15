package com.oscilloquartz.logclient.gui.textfield;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 */
public class IPv4AddressKeyListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        JTextField textField = (JTextField) e.getComponent();

        if (textField.getCaretPosition() == 0 && KeyEvent.VK_LEFT == e.getKeyCode() && e.getModifiers() == 0) {
            textField.transferFocusBackward();
        }

        if (textField.getCaretPosition() == 0 && KeyEvent.VK_BACK_SPACE == e.getKeyCode() && e.getModifiers() == 0) {
            textField.transferFocusBackward();
        }

        if (KeyEvent.VK_RIGHT == e.getKeyCode() && e.getModifiers() == 0) {
            int length = textField.getText().length();
            int caretPosition = textField.getCaretPosition();

            if (caretPosition == length) {
                textField.transferFocus();
                e.consume();
            }
        }
        if (e.getKeyChar() == '.' && textField.getText().trim().length() != 0) {
            textField.setText(textField.getText().trim());
            textField.transferFocus();
            e.consume();
        }
    }
}
