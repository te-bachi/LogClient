package com.oscilloquartz.logclient.gui.textfield;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 *
 */
public class IPv4AddressTextField extends GridbagPanel {

    private static final long   serialVersionUID           = 1L;

    public static final int     IPV4_ADDRESS_LENGTH         = 4;
    public static final int     IPV4_ADDRESS_FIELD_COLUMN   = 3;

    private JTextField[]    ipAddressField = new JTextField[IPV4_ADDRESS_LENGTH];
    private int             maxHeight;

    public IPv4AddressTextField() {
        JLabel  dotLabel;
        int     i;

        maxHeight = 0;

        for (i = 0; i < ipAddressField.length; i++) {
            ipAddressField[i] = new JTextField(IPV4_ADDRESS_FIELD_COLUMN);
            ipAddressField[i].setDocument(new IPv4AddressDocument(i, ipAddressField));
            ipAddressField[i].addFocusListener(new IPv4AddressFieldFocusListener(ipAddressField[i]));
            ipAddressField[i].addKeyListener(new IPv4AddressKeyListener());
            ipAddressField[i].setBorder(BorderFactory.createEmptyBorder());
            ipAddressField[i].setHorizontalAlignment(JTextField.CENTER);

            /* add IP byte to GUI */
            add(ipAddressField[i], i * 2, 0, 1, 1);
            if (ipAddressField[i].getPreferredSize().height > maxHeight) {
                maxHeight = ipAddressField[i].getPreferredSize().height;
            }

            /* add dot to GUI */
            if (i < (ipAddressField.length - 1)) {
                dotLabel = new JLabel(".");
                add(dotLabel, (i * 2) + 1, 0, 1, 1);
                if (dotLabel.getPreferredSize().height > maxHeight)
                    maxHeight = dotLabel.getPreferredSize().height;
            }
        }

        /* behave as a normal TextField */
        Color backgroundColor = UIManager.getColor("TextField.background");
        setBackground(backgroundColor);
        Border border = UIManager.getBorder("TextField.border");
        setBorder(border);
    }
}
