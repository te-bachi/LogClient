package com.oscilloquartz.logclient.gui.textfield.demo1;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 */
class IntegerListener extends KeyAdapter {

    private IPTextField comp;

    public IntegerListener(IPTextField comp) {
        this.comp = comp;
    }

    // filter the non-numeric char
    public void keyPressed(KeyEvent event) {
        TextField target;

        if (event.getKeyCode() == KeyEvent.VK_DELETE ||
            event.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
            event.getKeyCode() == KeyEvent.VK_UP ||
            event.getKeyCode() == KeyEvent.VK_DOWN ||
            event.getKeyCode() == KeyEvent.VK_LEFT ||
            event.getKeyCode() == KeyEvent.VK_RIGHT)
            return;
        //I'm sure of that
        target = (TextField) event.getComponent();
        if (event.getKeyChar() == '*') {
            event.consume();
            target.setText("*");
            requestFocusOnNextField(target);
        } else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            event.consume();
            requestFocusOnNextField(target);
        } else if (event.getKeyCode() == KeyEvent.VK_TAB ||
                event.getKeyCode() == KeyEvent.VK_SPACE ||
                event.getKeyCode() == KeyEvent.VK_ENTER) {
            requestFocusOnNextField(target);
        } else if (!(event.getKeyChar() >= '0' && event.getKeyChar() <= '9')) {
            event.consume();
        }
    }

    void requestFocusOnNextField(TextField target) {
        if (target == comp.fields[0])
            comp.fields[1].requestFocus();
        else if (target == comp.fields[1])
            comp.fields[2].requestFocus();
        else if (target == comp.fields[2])
            comp.fields[3].requestFocus();
        else if (target == comp.fields[3]) {
            comp.fireActionEvent();
            comp.fields[0].requestFocus();
            comp.fields[0].setText("");
            comp.fields[1].setText("");
            comp.fields[2].setText("");
            comp.fields[3].setText("");
        }
    }

}
