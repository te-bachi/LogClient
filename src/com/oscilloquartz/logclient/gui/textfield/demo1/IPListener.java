package com.oscilloquartz.logclient.gui.textfield.demo1;

import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

/**
 *
 */
class IPListener implements TextListener {

    private IPTextField comp;

    public IPListener(IPTextField comp) {
        this.comp = comp;
    }

    public void textValueChanged(TextEvent e) {
        // I'm sure of that
        TextField target = (TextField) e.getSource();
        String IP = target.getText();

        if (IP.length() < 3)
            return;
        if (IP.length() == 4) {
            target.setText(IP.substring(3));
            return;
        }

        try {
            short ip = Short.parseShort(IP);
            if (ip > 255) {
                target.setText("");
                return;
            }
        } catch (NumberFormatException ex) {
            target.setText("");
            return;
        }
        if (target == comp.fields[0])
            comp.fields[1].requestFocus();
        else if (target == comp.fields[1])
            comp.fields[2].requestFocus();
        else if (target == comp.fields[2])
            comp.fields[3].requestFocus();
    }
}
