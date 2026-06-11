package com.hector.papeleriasaas.view.swing;

import com.hector.papeleriasaas.view.swing.model.CartModel;

import java.awt.*;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class Card extends javax.swing.JPanel {

    private Color color1;
    private Color color2;

    public Card() {
        initComponents();
        setOpaque(false);
        color1 = Color.BLACK;
        color2 = Color.WHITE;
    }
    
    public void setColor1(Color color1){
        this.color1 = color1;
    }
    
    public void setColor2(Color color2){
        this.color2 = color2;
    }
    
    public Color getColor1(){
        return color1;
    }
    
    public Color getColor2(){
        return color2;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlb_icon = new javax.swing.JLabel();
        jlb_titulo = new javax.swing.JLabel();
        jlb_valor = new javax.swing.JLabel();
        jlb_descripcion = new javax.swing.JLabel();

        jlb_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/stock.png"))); // NOI18N

        jlb_titulo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jlb_titulo.setForeground(new java.awt.Color(255, 255, 255));
        jlb_titulo.setText("Title");

        jlb_valor.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jlb_valor.setForeground(new java.awt.Color(255, 255, 255));
        jlb_valor.setText("Values");

        jlb_descripcion.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jlb_descripcion.setForeground(new java.awt.Color(255, 255, 255));
        jlb_descripcion.setText("Description");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlb_titulo)
                    .addComponent(jlb_valor)
                    .addComponent(jlb_descripcion)
                    .addComponent(jlb_icon))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jlb_icon)
                .addGap(18, 18, 18)
                .addComponent(jlb_titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlb_valor)
                .addGap(18, 18, 18)
                .addComponent(jlb_descripcion)
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setData(CartModel data){
        jlb_icon.setIcon(data.getIcon());
        jlb_titulo.setText(data.getTitle());
        jlb_valor.setText(data.getValues());
        jlb_descripcion.setText(data.getDescription());
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setColor(new Color(255, 255, 255, 50));
        g2.fillOval(getWidth() - (getHeight() / 2), 10, getHeight(), getHeight());
        g2.fillOval(getWidth() - (getHeight() / 2) - 20, getHeight() / 2 + 20, getHeight(), getHeight());
        super.paintComponent(graphics);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlb_descripcion;
    private javax.swing.JLabel jlb_icon;
    private javax.swing.JLabel jlb_titulo;
    private javax.swing.JLabel jlb_valor;
    // End of variables declaration//GEN-END:variables
}
