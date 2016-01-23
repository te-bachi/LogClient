package com.oscilloquartz.logclient.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 *
 */
public class LogCellRenderer extends DefaultTableCellRenderer {

    Color originalColor = null;

    static final Color GRAY = new Color(221, 221, 221);
    static final Color WHITE = new Color(255, 255, 255);
    static final Color BLUE_DARK = new Color(170, 187, 204);
    static final Color BLUE_LIGHT = new Color(204, 221, 238);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (originalColor == null) {
            originalColor = getForeground();
        }

        if (isSelected) {
            renderer.setFont(renderer.getFont().deriveFont(Font.BOLD));
            if (row % 2 == 0) {
                renderer.setBackground(BLUE_DARK);
            } else {
                renderer.setBackground(BLUE_LIGHT);
            }
        } else {
            renderer.setFont(renderer.getFont().deriveFont(Font.PLAIN));
            if (row % 2 == 0) {
                renderer.setBackground(GRAY);
            } else {
                renderer.setBackground(WHITE);
            }
        }
        if (value == null) {
            renderer.setText("");
        } else {
            renderer.setText(value.toString());
        }

        if (row == 3) {
            renderer.setForeground(Color.RED);
        } else {
            renderer.setForeground(originalColor); // Retore original color
        }
        return renderer;
    }
}
