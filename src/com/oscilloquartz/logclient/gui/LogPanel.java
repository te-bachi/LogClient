package com.oscilloquartz.logclient.gui;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 *
 */
public class LogPanel extends JPanel {

    private DefaultTableModel   model;
    private JTable              table;
    private JScrollPane         sp1;
    private JScrollBar          vertical;
    private LogCellRenderer     renderer;

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

        renderer = new LogCellRenderer();

        table = new JTable(model);
//        {
//            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
//                Component c = super.prepareRenderer(renderer, row, column);
//
//                //  Alternate row color
//
//                if (!isRowSelected(row))
//                    c.setBackground(row % 2 == 0 ? getBackground() : Color.LIGHT_GRAY);
//
//                return c;
//            }
//        };

        table.setFocusable(false);

        table.setCellSelectionEnabled(false);
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        table.setFont(FontSelector.getInstance().getMonospaceFont());

        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table.getColumnModel().getColumn(0).setMinWidth(200);
        table.getColumnModel().getColumn(0).setMaxWidth(2 * 200);
        table.getColumnModel().getColumn(0).setCellRenderer(renderer);
        table.getColumnModel().getColumn(1).setMinWidth(150);
        table.getColumnModel().getColumn(1).setMaxWidth(2 * 150);
        table.getColumnModel().getColumn(1).setCellRenderer(renderer);
        table.getColumnModel().getColumn(2).setMinWidth(100);
        table.getColumnModel().getColumn(2).setMaxWidth(2 * 100);
        table.getColumnModel().getColumn(2).setCellRenderer(renderer);
        table.getColumnModel().getColumn(3).setCellRenderer(renderer);

        sp1 = new JScrollPane();
        sp1.setViewportView(table);
        vertical = sp1.getVerticalScrollBar();
        add(sp1);

        table.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                table.scrollRectToVisible(table.getCellRect(table.getRowCount() - 1, 0, true));
            }
        });

//        table.getModel().addTableModelListener(new TableModelListener() {
//
//            public void tableChanged(TableModelEvent e) {
//                //table.scrollRectToVisible(new Rectangle(0, table.getHeight() - 1, table.getWidth(), 1));
//                vertical.setValue(vertical.getMaximum());
//                //table.changeSelection(table.getRowCount() - 1, 0, false, false);
//            }
//        });

        add(sp1, BorderLayout.CENTER);
    }

    public DefaultTableModel getDefaultTableModel() {
        return model;
    }
}
