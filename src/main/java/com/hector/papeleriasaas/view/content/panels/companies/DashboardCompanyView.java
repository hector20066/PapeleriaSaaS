package com.hector.papeleriasaas.view.content.panels.companies;

import com.hector.papeleriasaas.view.chart.LineChart;
import com.hector.papeleriasaas.view.swing.model.CartModel;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DashboardCompanyView extends javax.swing.JPanel {

    public DashboardCompanyView() {
        initComponents();
        setData();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        panel = new javax.swing.JLayeredPane();
        card1 = new com.hector.papeleriasaas.view.swing.Card();
        card2 = new com.hector.papeleriasaas.view.swing.Card();
        card3 = new com.hector.papeleriasaas.view.swing.Card();
        card4 = new com.hector.papeleriasaas.view.swing.Card();
        lineChart = new com.hector.papeleriasaas.view.chart.LineChart();
        jLabel1 = new javax.swing.JLabel();
        jlb_date = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1117, 640));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(918, 544));

        jPanel2.setBackground(new java.awt.Color(241, 241, 241));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(79, 107, 220));
        card1.setColor2(new java.awt.Color(58, 84, 196));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(29, 158, 117));
        card2.setColor2(new java.awt.Color(15, 110, 86));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(127, 119, 221));
        card3.setColor2(new java.awt.Color(99, 90, 195));
        panel.add(card3);

        card4.setColor1(new java.awt.Color(216, 90, 48));
        card4.setColor2(new java.awt.Color(171, 60, 29));
        panel.add(card4);

        lineChart.setForeground(new java.awt.Color(42, 49, 64));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(114, 142, 166));
        jLabel1.setText("Ventas Mensuales");

        jlb_date.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jlb_date.setForeground(new java.awt.Color(114, 142, 166));
        jlb_date.setText("Hoy es {dayname} {day} de {month} de {year}");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlb_date, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lineChart, javax.swing.GroupLayout.DEFAULT_SIZE, 1083, Short.MAX_VALUE)
                                .addGap(5, 5, 5))
                            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jlb_date)
                .addGap(18, 18, 18)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineChart, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        panel.getAccessibleContext().setAccessibleParent(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    public void updatePanel(float totalPurchases, float totalSales, float totalDebts, int amountProduct, String product){
        card1.setData(new CartModel(new ImageIcon(getClass().getResource("/image/stock.png")),
                "Stock", String.valueOf(totalPurchases), "Total dinero invertido en stock"));
        card2.setData(new CartModel(new ImageIcon(getClass().getResource("/image/profit.png")),
                "Ventas", String.valueOf(totalSales), "Total ventas"));
        card3.setData(new CartModel(new ImageIcon(getClass().getResource("/image/flag.png")),
                "Ganancias", String.valueOf(totalDebts), "Ganancias totales"));
        card4.setData(new CartModel(new ImageIcon(getClass().getResource("/image/productBestSelling.png")),
                product, String.valueOf(amountProduct), "Producto mas vendido"));
    }
    
    private void setData(){
        LocalDate now = LocalDate.now();
        Locale spanishLocale = Locale.of("es", "ES");
        jlb_date.setText(now.format(DateTimeFormatter.ofPattern("EEEE dd 'de' MMMM 'de' yyyy", spanishLocale)));
    }
    
    public LineChart getLineChart(){
        return lineChart;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hector.papeleriasaas.view.swing.Card card1;
    private com.hector.papeleriasaas.view.swing.Card card2;
    private com.hector.papeleriasaas.view.swing.Card card3;
    private com.hector.papeleriasaas.view.swing.Card card4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jlb_date;
    private com.hector.papeleriasaas.view.chart.LineChart lineChart;
    private javax.swing.JLayeredPane panel;
    // End of variables declaration//GEN-END:variables
}
