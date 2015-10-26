package com.oscilloquartz.logclient.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 *
 */
public class LogPanel extends JPanel {

    private DefaultTableModel   model;
    private JTable              table;

    public LogPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Object columnNames[] = { "Time", "Category", "Level", "Message" };

        model = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        table = new JTable(model)
        {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);

                //  Alternate row color

                if (!isRowSelected(row))
                    c.setBackground(row % 2 == 0 ? getBackground() : Color.LIGHT_GRAY);

                return c;
            }
        };

        table.setFocusable(false);
        table.setRowSelectionAllowed(false);
        table.setFont(FontSelector.getInstance().getMonospaceFont());

        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table.getColumnModel().getColumn(0).setMinWidth(200);
        table.getColumnModel().getColumn(0).setMaxWidth(2 * 200);
        table.getColumnModel().getColumn(1).setMinWidth(150);
        table.getColumnModel().getColumn(1).setMaxWidth(2 * 150);
        table.getColumnModel().getColumn(2).setMinWidth(100);
        table.getColumnModel().getColumn(2).setMaxWidth(2 * 100);

        JScrollPane sp1 = new JScrollPane();
        sp1.setViewportView(table);
        add(sp1);

        add(sp1, BorderLayout.CENTER);
    }

    public DefaultTableModel getDefaultTableModel() {
        return model;
    }
}
