package com.oscilloquartz.logclient.gui;

import com.oscilloquartz.logclient.gui.textfield.IPv4AddressTextField;
import com.oscilloquartz.logclient.gui.textfield.demo1.IPTextField;
import com.oscilloquartz.logclient.gui.textfield.demo2.VersionTextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class ConnectPanel extends JPanel {

    Pattern pattern = Pattern.compile("\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
            "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b");

    public ConnectPanel() {
        JLabel          ipAddressLabel;
        JTextField      ipAddressField;
        JButton         connectButton;
        MaskFormatter   formatter;

        setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        try {
            ipAddressLabel  = new JLabel("IP address");
            formatter       = new MaskFormatter("###.###.###.###");
            ipAddressField  = new JFormattedTextField(formatter);
            IPTextField a   = new IPTextField();
            VersionTextField b = new VersionTextField();
            JTextField c = new JTextField("abc", 5);

            IPv4AddressTextField d = new IPv4AddressTextField();

                    connectButton   = new JButton("Connect");

            ipAddressField.setPreferredSize(new Dimension(125, 24));
            ipAddressField.setInputVerifier(new InputVerifier() {

                public boolean shouldYieldFocus(JComponent input) {
                    boolean inputOK = verify(input);
                    if (inputOK) {
                        return true;
                    } else {
                        Toolkit.getDefaultToolkit().beep();
                        return false;
                    }
                }

                @Override
                public boolean verify(JComponent input) {
                    JTextField field = (JTextField) input;
                    return checkString(field.getText());
                }
            });

            ipAddressField.getDocument().addDocumentListener(new DocumentListener() {
                void checkDocument(DocumentEvent e) {
                    try {
                        String text = e.getDocument().getText(0, e.getDocument().getLength());
                        connectButton.setEnabled(checkString(text));
                    } catch (BadLocationException ex) {
                        //Do something, OK?
                    }
                }

                public void insertUpdate(DocumentEvent e) {
                    checkDocument(e);
                }

                public void removeUpdate(DocumentEvent e) {
                    checkDocument(e);
                }

                public void changedUpdate(DocumentEvent e) {
                    checkDocument(e);
                }
            });

            setLayout(new FlowLayout(FlowLayout.LEFT));
            add(ipAddressLabel);
            //add(ipAddressField);
            //add(a);
            //add(b);
            //add(c);
            add(d);
            add(connectButton);
        } catch (ParseException e) {

        }
    }

    private boolean checkString(String text) {
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
