package com.oscilloquartz.logclient.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 *
 */
public class LogPanelJTable extends JTable {
    private DefaultTableModel   logModel;
    private LogCellRenderer     logRenderer;
    private boolean             autoscroll;

    private int                 selectRow       = -1;
    private int                 selectColumn    = -1;

    public LogPanelJTable() {

        super();

        Object columnNames[] = { "Time", "Category", "Level", "Message" };

        logModel = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        setModel(logModel);

        autoscroll = false;

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                if (autoscroll) {
                    scrollRectToVisible(getCellRect(getRowCount() - 1, 0, true));
                }
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

        logRenderer = new LogCellRenderer();


//        {
//            public Component prepareRenderer(TableCellRenderer logRenderer, int row, int column) {
//                Component c = super.prepareRenderer(logRenderer, row, column);
//
//                //  Alternate row color
//
//                if (!isRowSelected(row))
//                    c.setBackground(row % 2 == 0 ? getBackground() : Color.LIGHT_GRAY);
//
//                return c;
//            }
//        };

        this.setFocusable(true);

        setCellSelectionEnabled(false);
        setColumnSelectionAllowed(false);
        setRowSelectionAllowed(true);
        setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        setFont(FontSelector.getInstance().getMonospaceFont());

        setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        getColumnModel().getColumn(0).setMinWidth(200);
        getColumnModel().getColumn(0).setMaxWidth(2 * 200);
        getColumnModel().getColumn(0).setCellRenderer(logRenderer);
        getColumnModel().getColumn(1).setMinWidth(150);
        getColumnModel().getColumn(1).setMaxWidth(2 * 150);
        getColumnModel().getColumn(1).setCellRenderer(logRenderer);
        getColumnModel().getColumn(2).setMinWidth(100);
        getColumnModel().getColumn(2).setMaxWidth(2 * 100);
        getColumnModel().getColumn(2).setCellRenderer(logRenderer);
        getColumnModel().getColumn(3).setCellRenderer(logRenderer);
    }

//    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
//        if (selectColumn == -1) {
//            selectRow = rowIndex;
//        }
//        if (selectColumn == -1) {
//            selectColumn = columnIndex;
//        }
//
//        if (selectRow == rowIndex && selectColumn == (columnIndex + 1)) {
//            selectRow = rowIndex + 1;
//            selectColumn = 0;
//        } else {
//            selectRow = rowIndex;
//            selectColumn = 0;
//        }
//        System.out.println("request: " + rowIndex + "/" + columnIndex + " select: " + selectRow + "/" + selectColumn + " toggle=" + toggle + " extend=" + extend);
//        changeSelection2(selectRow, selectColumn, toggle, extend);
//    }
//
//    public void changeSelection2(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
//
//        ListSelectionModel rsm = getSelectionModel();
//        ListSelectionModel csm = getColumnModel().getSelectionModel();
//
//        int anchorRow = getAdjustedIndex(rsm.getAnchorSelectionIndex(), true);
//        int anchorCol = getAdjustedIndex(csm.getAnchorSelectionIndex(), false);
//
//        boolean anchorSelected = true;
//
//        if (anchorRow == -1) {
//            if (getRowCount() > 0) {
//                anchorRow = 0;
//            }
//            anchorSelected = false;
//        }
//
//        if (anchorCol == -1) {
//            if (getColumnCount() > 0) {
//                anchorCol = 0;
//            }
//            anchorSelected = false;
//        }
//
//        // Check the selection here rather than in each selection logModel.
//        // This is significant in cell selection mode if we are supposed
//        // to be toggling the selection. In this case it is better to
//        // ensure that the cell's selection state will indeed be changed.
//        // If this were done in the code for the selection logModel it
//        // might leave a cell in selection state if the row was
//        // selected but the column was not - as it would toggle them both.
//
//        //boolean selected = isCellSelected(rowIndex, columnIndex);
//        //anchorSelected = anchorSelected && isCellSelected(anchorRow, anchorCol);
//        boolean selected = true;
//        anchorSelected = false;
//
//        changeSelectionModel(csm, columnIndex, toggle, extend, selected,
//                anchorCol, anchorSelected);
//        changeSelectionModel(rsm, rowIndex, toggle, extend, selected,
//                anchorRow, anchorSelected);
//
//        // Scroll after changing the selection as blit scrolling is immediate,
//        // so that if we cause the repaint after the scroll we end up painting
//        // everything!
//        if (getAutoscrolls()) {
//            Rectangle cellRect = getCellRect(rowIndex, columnIndex, false);
//            if (cellRect != null) {
//                scrollRectToVisible(cellRect);
//            }
//        }
//    }
//
//    void changeSelectionModel(ListSelectionModel sm, int index, boolean toggle, boolean extend, boolean selected, int anchor, boolean anchorSelected) {
//        //System.out.println("request: " + rowIndex + "/" + columnIndex + " select: " + selectRow + "/" + selectColumn + " toggle=" + toggle + " extend=" + extend);
//        if (extend) {
//            if (toggle) {
//                if (anchorSelected) {
//                    sm.addSelectionInterval(anchor, index);
//                } else {
//                    sm.removeSelectionInterval(anchor, index);
//                    // this is a Windows-only behavior that we want for file lists
//                    if (Boolean.TRUE == getClientProperty("Table.isFileList")) {
//                        sm.addSelectionInterval(index, index);
//                        sm.setAnchorSelectionIndex(anchor);
//                    }
//                }
//            }
//            else {
//                sm.setSelectionInterval(anchor, index);
//            }
//        }
//        else {
//            if (toggle) {
//                if (selected) {
//                    sm.removeSelectionInterval(index, index);
//                }
//                else {
//                    sm.addSelectionInterval(index, index);
//                }
//            }
//            else {
//                sm.setSelectionInterval(index, index);
//            }
//        }
//    }
//
//    public int getAdjustedIndex(int index, boolean row) {
//        int compare = row ? getRowCount() : getColumnCount();
//        return index < compare ? index : -1;
//    }

    public DefaultTableModel getDefaultTableModel() {
        return logModel;
    }

    public void setAutoscroll(boolean autoscroll) {
        this.autoscroll = autoscroll;
    }
}
