package com.hector.papeleriasaas.utils;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Pattern;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class SwingUtils {

    public static FocusListener addFocusListener(JTextField textField, String text) {
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if (textField.getText().equalsIgnoreCase(text)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (textField.getText().isBlank()) {
                    textField.setText(text);
                }
            }
        };
    }

    public static void filterTable(JTextField textField, JTable table, String text) {
        if (!textField.getText().equals(text)) {
            String searchText = textField.getText();
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
            table.setRowSorter(sorter);

            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(searchText)));
        }
    }

}
