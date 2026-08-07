package com.hector.papeleriasaas.view.swing.tables.tablestatus.users;

import com.hector.papeleriasaas.model.enums.Status;
import com.hector.papeleriasaas.view.swing.tables.tablestatus.TableHeader;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 *
 * @author hlacevedo
 */
public class Table extends JTable {

    private static final Color ROW_BACKGROUND = Color.WHITE;
    private static final Color ROW_FOREGROUND = new Color(102, 102, 102);
    private static final Color SELECTED_ROW_FOREGROUND = new Color(15, 89, 140);
    private static final Color SELECTED_ROW_BACKGROUND = new Color(230, 241, 250);

    private boolean isStatusColumn(int viewColumn) {
        return convertColumnIndexToModel(viewColumn) == getModel().getColumnCount() - 1;
    }
    
    public Table() {
        setRowHeight(40);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setRowSelectionAllowed(true);
        setColumnSelectionAllowed(false);
        setCellSelectionEnabled(false);
        setShowGrid(false);
        setShowHorizontalLines(true);
        setShowVerticalLines(false);
        setGridColor(new Color(230, 230, 230));
        setIntercellSpacing(new Dimension(0, 1));
        setFillsViewportHeight(true);
        setBorder(BorderFactory.createEmptyBorder());

        getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        getTableHeader().setReorderingAllowed(false);

        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + "");

                if (isStatusColumn(i1)) {
                    header.setHorizontalAlignment(JLabel.CENTER);
                }

                return header;
            }
        });

        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int i, int i1) {
                if (!isStatusColumn(i1) || !(o instanceof Status type)) {
                    Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1);
                    com.setBackground(selected ? SELECTED_ROW_BACKGROUND : ROW_BACKGROUND);

                    if (com instanceof JComponent component) {
                        component.setBorder(noFocusBorder);
                    }

                    if (selected) {
                        com.setForeground(SELECTED_ROW_FOREGROUND);
                    } else {
                        com.setForeground(ROW_FOREGROUND);
                    }

                    return com;
                } else {
                    CellStatus cell = new CellStatus(type);
                    cell.setSelected(selected);
                    cell.setBackground(selected ? SELECTED_ROW_BACKGROUND : ROW_BACKGROUND);
                    return cell;
                }
            }
        });
    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        boolean selected = isRowSelected(row);

        if (component instanceof JComponent jComponent) {
            jComponent.setOpaque(true);
        }

        component.setBackground(selected ? SELECTED_ROW_BACKGROUND : ROW_BACKGROUND);
        component.setForeground(selected ? SELECTED_ROW_FOREGROUND : ROW_FOREGROUND);

        return component;
    }
    
}
