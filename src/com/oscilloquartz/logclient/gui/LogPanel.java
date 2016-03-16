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

    private LogPanelJTable      table;
    private JScrollPane         sp1;
    private JScrollBar          vertical;
    private LogClipboardAdapter clipboardAdapter;

    public LogPanel() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        table = new LogPanelJTable();

        sp1 = new JScrollPane();
        sp1.setViewportView(table);
        vertical = sp1.getVerticalScrollBar();
        add(sp1);

        clipboardAdapter = new LogClipboardAdapter(table);

        add(sp1, BorderLayout.CENTER);
    }

    public LogPanelJTable getTable() {
        return table;
    }
}
