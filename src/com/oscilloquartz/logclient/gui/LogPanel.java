package com.oscilloquartz.logclient.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 *
 */
public class LogPanel extends JPanel {
    public LogPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Object columnNames[] = { "Time", "Category", "Level", "Message" };
        Object data[][] = new Object[][] { { "01.01.2015 12:34:56.789", "GRANDMASTER", "INFO", "dasf sfa sdf sadfsd fdfgh hdfgsd gdf gdg sdfgdfgd gdf gsdfg dfsgd fgdsfg dsf gdsfg dgdfs gdsf gdsfg dsgdf gdsf gdg dfg dfsgdsf gdfgdf gsdfg sdf gdsf gds" } };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        model.addRow(new Object[]{"a", "b", "c", "d"});
        model.addRow(new Object[]{"a", "b", "c", "d"});
        model.addRow(new Object[]{"a", "b", "c", "d"});
        model.addRow(new Object[]{"a", "b", "c", "d"});
        model.addRow(new Object[]{"a", "b", "c", "d"});
        model.addRow(new Object[]{"a", "b", "c", "d"});

        JTable table = new JTable(model)
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
        table.getColumnModel().getColumn(0).setMaxWidth(200);
        table.getColumnModel().getColumn(1).setMinWidth(100);
        table.getColumnModel().getColumn(1).setMaxWidth(100);
        table.getColumnModel().getColumn(2).setMinWidth(100);
        table.getColumnModel().getColumn(2).setMaxWidth(100);

        JScrollPane sp1 = new JScrollPane();
        sp1.setViewportView(table);
        add(sp1);

        add(sp1, BorderLayout.CENTER);
    }
}
