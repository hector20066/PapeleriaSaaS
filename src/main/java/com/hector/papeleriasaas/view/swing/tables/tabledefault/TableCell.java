/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hector.papeleriasaas.view.swing.tables.tabledefault;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 *
 * @author hlacevedo
 */
public class TableCell extends JLabel {

    private static final Color ROW_BACKGROUND = Color.WHITE;
    private static final Color SELECTED_ROW_BORDER = new Color(137, 215, 255);
    private static final Color EMPTY_BACKGROUND = new Color(245, 245, 245);

    private final CellType cellType;
    private boolean selected;

    public TableCell(Object obj) {
        setFont(new Font("sansserif", 1, 12));
        this.cellType = CellType.EMPTY;
        
        if (obj != null) {
            setText(obj.toString());
        }
        
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setForeground(new Color(80, 80, 80));
    }

    public TableCell(Object obj, boolean selected, CellType cellType) {
        this.selected = selected;
        this.cellType = cellType;
        
        if (obj != null) {
            setText(obj.toString());
        }
        
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setForeground(new Color(80, 80, 80));
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth() + 5;
        int height = getHeight() - 4;
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(ROW_BACKGROUND);
        
        if (cellType == CellType.LEFT) {
            g2.fillRoundRect(0, 2, width, height, 6, 6);
            
            if (selected) {
                g2.setColor(SELECTED_ROW_BORDER);
                g2.setStroke(new BasicStroke(2f));
                g2.drawRoundRect(1, 2, width, height, 6, 6);
            }
        } else if (cellType == CellType.RIGHT) {
            g2.fillRoundRect(-5, 2, width, height, 6, 6);
            
            if (selected) {
                g2.setColor(SELECTED_ROW_BORDER);
                g2.setStroke(new BasicStroke(2f));
                g2.drawRoundRect(-5, 2, width - 2, height, 6, 6);
            }
        } else if (cellType == CellType.CENTER) {
            g2.fillRect(0, 2, width, height);
            
            if (selected) {
                g2.setColor(SELECTED_ROW_BORDER);
                g2.setStroke(new BasicStroke(2f));
                g2.drawRect(-5, 2, width + 2, height);
            }
        } else {
            g2.setColor(EMPTY_BACKGROUND);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
        
        super.paintComponent(grphcs);
    }

    public static enum CellType {
        
        LEFT,
        RIGHT,
        CENTER,
        EMPTY
        
    }
    
}
