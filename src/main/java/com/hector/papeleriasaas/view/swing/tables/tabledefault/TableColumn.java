/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hector.papeleriasaas.view.swing.tables.tabledefault;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 *
 * @author hlacevedo
 */
public class TableColumn extends JTable {

    public TableColumn() {
        setBackground(new Color(245, 245, 245));
        setRowHeight(40);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setRowSelectionAllowed(true);
        setColumnSelectionAllowed(false);
        setCellSelectionEnabled(false);
        setShowGrid(false);
        setShowHorizontalLines(false);
        setShowVerticalLines(false);
        setIntercellSpacing(new Dimension(0, 0));
        setFillsViewportHeight(true);
        setFocusable(false);
        setBorder(BorderFactory.createEmptyBorder());

        getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        getTableHeader().setFocusable(false);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                return new TableCell(o);
            }
        });
    }

    @Override
    public Component prepareRenderer(TableCellRenderer tcr, int i, int i1) {
        TableCell.CellType celLType = TableCell.CellType.CENTER;
        
        if (i1 == 0) {
            celLType = TableCell.CellType.LEFT;
        } else if (i1 == getColumnCount() - 1) {
            celLType = TableCell.CellType.RIGHT;
        }
        
        return new TableCell(getValueAt(i, i1), isRowSelected(i), celLType);
    }

}
