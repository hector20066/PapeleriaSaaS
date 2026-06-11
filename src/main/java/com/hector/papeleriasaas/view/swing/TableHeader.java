package com.hector.papeleriasaas.view.swing;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class TableHeader {

    public static JTableHeader getTableHeader(JTable jTable) {
        JTableHeader head = jTable.getTableHeader();
        head.setBackground(new Color(114, 142, 166));
        head.setForeground(new Color(42, 49, 64));
        head.setFont(new Font("Segoe UI", Font.BOLD, 12));

        return head;
    }

}
