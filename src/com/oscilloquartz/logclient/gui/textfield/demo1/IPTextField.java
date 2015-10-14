// IPTemplateAttributeEditor.java
// $Id: IPTextField.java,v 1.7 2000/08/16 21:37:56 ylafon Exp $
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package com.oscilloquartz.logclient.gui.textfield.demo1;

import com.oscilloquartz.logclient.gui.TextEditable;

import java.awt.AWTEventMulticaster;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.StringTokenizer;

/**
 * IPTextField :
 *
 * @author Benoit Mahe <bmahe@sophia.inria.fr>
 */

public class IPTextField extends Panel implements TextEditable {

    protected TextField[] fields = null;
    transient ActionListener actionListener;
    String command = "";


    public IPTextField() {
        IntegerListener intlistener = new IntegerListener(this);
        IPListener iplistener = new IPListener(this);

        fields = new TextField[4];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new TextField(3);
            fields[i].addTextListener(iplistener);
            fields[i].addKeyListener(intlistener);
        }
        setLayout(new GridLayout(1, 4));
        add(fields[0]);
        add(fields[1]);
        add(fields[2]);
        add(fields[3]);

    }

    /**
     * Adds the specified action listener to recieve action events from
     * this IPTextField.
     *
     * @param al - the action listener.
     */
    public synchronized void addActionListener(ActionListener al) {
        actionListener = AWTEventMulticaster.add(actionListener, al);
    }

    /**
     * Removes the specified action listener so that it no longer receives
     * action events from IPTextField.
     *
     * @param al - the action listener.
     */
    public synchronized void removeActionListener(ActionListener al) {
        actionListener = AWTEventMulticaster.remove(actionListener, al);
    }

    /**
     * fire a new ActionEvent and process it, if some listeners are listening
     */

    protected void fireActionEvent() {
        if (actionListener != null) {
            ActionEvent ae = new ActionEvent(this,
                    ActionEvent.ACTION_PERFORMED,
                    command);
            actionListener.actionPerformed(ae);
        }
    }

    private String getFieldValue(TextField field) {
        String svalue = field.getText();
        if (svalue == null)
            return "0";
        if (svalue.length() == 0)
            return "0";
        else
            return svalue;
    }

    /**
     * Gets the text that is presented by this IPTextField.
     */
    public String getText() {
        return (getFieldValue(fields[0]) +
                "." +
                getFieldValue(fields[1]) +
                "." +
                getFieldValue(fields[2]) +
                "." +
                getFieldValue(fields[3]));
    }

    /**
     * Sets the text that is presented by this IPTextField to be the specified
     * text.
     *
     * @param text - the new text
     */
    public void setText(String IPT) {
        StringTokenizer st = new StringTokenizer(IPT, ".");
        int i = 0;
        while (i < 4 && st.hasMoreTokens())
            fields[i++].setText(st.nextToken());
    }

    /**
     * Check if the current text value and the default value are different.
     */
    public boolean updated() {
        return (!getText().equals("0.0.0.0"));
    }

    /**
     * Sets the text at its default value
     */
    public void setDefault() {
        fields[0].setText("");
        fields[1].setText("");
        fields[2].setText("");
        fields[3].setText("");
    }

    public Dimension getMinimumSize() {
        return new Dimension(4 * fields[0].getMinimumSize().width,
                fields[0].getMinimumSize().height);
    }

    public Dimension getPreferredSize() {
        return getMinimumSize();
    }


}
