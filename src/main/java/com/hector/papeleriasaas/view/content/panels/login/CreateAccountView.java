package com.hector.papeleriasaas.view.content.panels.login;

import com.hector.papeleriasaas.view.content.panels.dto.DTOCreateAccountViewSummary;
import com.hector.papeleriasaas.view.swing.buttons.ButtonAction;

import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentListener;

/**
 *
 * @author hlacevedo
 */
public class CreateAccountView extends javax.swing.JPanel {

    private String anteriorView;

    public CreateAccountView() {
        initComponents();
        
        Icon normalIcon = new ImageIcon(getClass().getResource("/image/eye.png"));
        Icon iconOff = new ImageIcon(getClass().getResource("/image/eye-off.png"));
        rbn_seePasswd.setIcon(normalIcon);
        rbn_seePasswd.setSelectedIcon(iconOff);
        
        jpl_content.add(jpl_companyInfo, "companyInfo");
        jpl_content.add(jpl_selectPlan, "selectPlan");
        jpl_content.add(jpl_userInfo, "userInfo");
        jpl_content.add(jpl_summary, "summary");
    }

    public void setAnteriorView(String anteriorView) {
        this.anteriorView = anteriorView;
    }

    public String getAnteriorView() {
        return anteriorView;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel23 = new javax.swing.JLabel();
        btg_planGroup = new javax.swing.ButtonGroup();
        jpl_content = new javax.swing.JPanel();
        jpl_companyInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelShadow1 = new com.hector.papeleriasaas.view.swing.panels.PanelShadow();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        roundedPanel1 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        txt_companyNit = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        roundedPanel2 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        txt_companyName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        roundedPanel3 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        txt_companyAddress = new javax.swing.JTextField();
        roundedPanel4 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        roundedPanel5 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        txt_companyPhone = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        roundedPanel6 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        txt_companyEmail = new javax.swing.JTextField();
        btn_showSelectPlan = new com.hector.papeleriasaas.view.swing.buttons.ButtonAction();
        btn_back = new com.hector.papeleriasaas.view.swing.buttons.ButtonAction();
        jpl_selectPlan = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panelShadow2 = new com.hector.papeleriasaas.view.swing.panels.PanelShadow();
        jLabel11 = new javax.swing.JLabel();
        roundedPanel7 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jrb_premium = new javax.swing.JRadioButton();
        btn_showUserInfo = new com.hector.papeleriasaas.view.swing.buttons.ButtonAction();
        jLabel12 = new javax.swing.JLabel();
        btn_backCompanyInfo = new com.hector.papeleriasaas.view.swing.buttons.ButtonAction();
        jpl_userInfo = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        panelShadow3 = new com.hector.papeleriasaas.view.swing.panels.PanelShadow();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        roundedPanel8 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        txt_userId = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        roundedPanel9 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        txt_personName = new javax.swing.JTextField();
        roundedPanel10 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        roundedPanel11 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        txt_username = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        roundedPanel12 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        jpld_passwd = new javax.swing.JPasswordField();
        jLabel37 = new javax.swing.JLabel();
        cbx_role = new javax.swing.JComboBox<>();
        rbn_seePasswd = new javax.swing.JRadioButton();
        btn_showSummary = new com.hector.papeleriasaas.view.swing.buttons.ButtonAction();
        btn_backSelectPlan = new com.hector.papeleriasaas.view.swing.buttons.ButtonAction();
        jpl_summary = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        panelShadow4 = new com.hector.papeleriasaas.view.swing.panels.PanelShadow();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jlb_companyNit = new javax.swing.JLabel();
        jlb_companyName = new javax.swing.JLabel();
        jlb_companyAddress = new javax.swing.JLabel();
        jlb_companyPhone = new javax.swing.JLabel();
        jlb_companyEmail = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jlb_planName = new javax.swing.JLabel();
        jlb_planPrice = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel51 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jlb_userId = new javax.swing.JLabel();
        jlb_personName = new javax.swing.JLabel();
        jlb_username = new javax.swing.JLabel();
        jlb_passwd = new javax.swing.JLabel();
        jlb_role = new javax.swing.JLabel();
        btn_registerCompany = new com.hector.papeleriasaas.view.swing.buttons.ButtonAction();
        btn_backUserInfo = new com.hector.papeleriasaas.view.swing.buttons.ButtonAction();

        jLabel23.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(42, 49, 64));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("negocio, ahorrandole mucho tiempo.");

        jpl_content.setBackground(new java.awt.Color(255, 255, 255));
        jpl_content.setLayout(new java.awt.CardLayout());

        jpl_companyInfo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/loginPapeleria.png"))); // NOI18N

        jLabel3.setBackground(new java.awt.Color(42, 49, 64));
        jLabel3.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(42, 49, 64));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Registrar Papelería");

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(42, 49, 64));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Por favor ingrese la informacion solicitada a continuación (Los campos con * son obligatorios).");

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(42, 49, 64));
        jLabel5.setText("NIT*");

        roundedPanel1.setBackground(new java.awt.Color(242, 246, 253));
        roundedPanel1.setBorderColor(new java.awt.Color(119, 141, 169));

        txt_companyNit.setBackground(new java.awt.Color(242, 246, 253));
        txt_companyNit.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txt_companyNit.setForeground(new java.awt.Color(42, 49, 64));
        txt_companyNit.setBorder(null);

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_companyNit, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_companyNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(42, 49, 64));
        jLabel6.setText("Nombre*");

        roundedPanel2.setBackground(new java.awt.Color(242, 246, 253));
        roundedPanel2.setBorderColor(new java.awt.Color(119, 141, 169));

        txt_companyName.setBackground(new java.awt.Color(242, 246, 253));
        txt_companyName.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txt_companyName.setForeground(new java.awt.Color(42, 49, 64));
        txt_companyName.setBorder(null);

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_companyName, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_companyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(42, 49, 64));
        jLabel7.setText("Dirección");

        roundedPanel3.setBackground(new java.awt.Color(242, 246, 253));
        roundedPanel3.setBorderColor(new java.awt.Color(119, 141, 169));

        txt_companyAddress.setBackground(new java.awt.Color(242, 246, 253));
        txt_companyAddress.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txt_companyAddress.setForeground(new java.awt.Color(42, 49, 64));
        txt_companyAddress.setBorder(null);

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_companyAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_companyAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundedPanel4.setBackground(new java.awt.Color(242, 246, 253));
        roundedPanel4.setBorderColor(new java.awt.Color(42, 49, 64));

        roundedPanel5.setBackground(new java.awt.Color(242, 246, 253));
        roundedPanel5.setBorderColor(new java.awt.Color(119, 141, 169));

        txt_companyPhone.setBackground(new java.awt.Color(242, 246, 253));
        txt_companyPhone.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txt_companyPhone.setForeground(new java.awt.Color(42, 49, 64));
        txt_companyPhone.setBorder(null);

        javax.swing.GroupLayout roundedPanel5Layout = new javax.swing.GroupLayout(roundedPanel5);
        roundedPanel5.setLayout(roundedPanel5Layout);
        roundedPanel5Layout.setHorizontalGroup(
            roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_companyPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundedPanel5Layout.setVerticalGroup(
            roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_companyPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(42, 49, 64));
        jLabel8.setText("Información de contacto");

        jLabel9.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(42, 49, 64));
        jLabel9.setText("Telefono*");

        jLabel10.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(42, 49, 64));
        jLabel10.setText("Correo electrónico");

        roundedPanel6.setBackground(new java.awt.Color(242, 246, 253));
        roundedPanel6.setBorderColor(new java.awt.Color(119, 141, 169));

        txt_companyEmail.setBackground(new java.awt.Color(242, 246, 253));
        txt_companyEmail.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txt_companyEmail.setForeground(new java.awt.Color(42, 49, 64));
        txt_companyEmail.setBorder(null);

        javax.swing.GroupLayout roundedPanel6Layout = new javax.swing.GroupLayout(roundedPanel6);
        roundedPanel6.setLayout(roundedPanel6Layout);
        roundedPanel6Layout.setHorizontalGroup(
            roundedPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_companyEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundedPanel6Layout.setVerticalGroup(
            roundedPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_companyEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundedPanel4Layout = new javax.swing.GroupLayout(roundedPanel4);
        roundedPanel4.setLayout(roundedPanel4Layout);
        roundedPanel4Layout.setHorizontalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel4Layout.createSequentialGroup()
                .addGroup(roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel8))
                    .addGroup(roundedPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(roundedPanel4Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(roundedPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel10))
                            .addGroup(roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(roundedPanel4Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(roundedPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel9)))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        roundedPanel4Layout.setVerticalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btn_showSelectPlan.setText("Continuar");
        btn_showSelectPlan.setEnabled(false);
        btn_showSelectPlan.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btn_showSelectPlan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btn_back.setBackground(new java.awt.Color(194, 203, 212));
        btn_back.setForeground(new java.awt.Color(42, 49, 64));
        btn_back.setText("Regresar");
        btn_back.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        btn_back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE))
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelShadow1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelShadow1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5)))
                            .addComponent(roundedPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_showSelectPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(29, 29, 29)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(roundedPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btn_showSelectPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpl_companyInfoLayout = new javax.swing.GroupLayout(jpl_companyInfo);
        jpl_companyInfo.setLayout(jpl_companyInfoLayout);
        jpl_companyInfoLayout.setHorizontalGroup(
            jpl_companyInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl_companyInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jpl_companyInfoLayout.setVerticalGroup(
            jpl_companyInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl_companyInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpl_companyInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpl_companyInfoLayout.createSequentialGroup()
                        .addGap(0, 7, Short.MAX_VALUE)
                        .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpl_content.add(jpl_companyInfo, "card2");

        jpl_selectPlan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/loginPapeleria.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(42, 49, 64));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Planes Disponibles");

        roundedPanel7.setBackground(new java.awt.Color(226, 232, 240));
        roundedPanel7.setDrawBorder(false);

        jLabel13.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(42, 49, 64));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Plan PREMIUM");

        jLabel14.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(42, 49, 64));
        jLabel14.setText("Beneficios:");

        jLabel16.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(42, 49, 64));
        jLabel16.setText("- Crear usuarios con diferentes roles");

        jLabel17.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(42, 49, 64));
        jLabel17.setText("- Registro y control de compras");

        jLabel18.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(42, 49, 64));
        jLabel18.setText("- Generación de facturas");

        jLabel19.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(42, 49, 64));
        jLabel19.setText("- Registro y control de ventas");

        jLabel20.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(42, 49, 64));
        jLabel20.setText("- Control automático del inventario");

        jLabel15.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(42, 49, 64));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Al adquirir este plan obtendra acceso al");

        jLabel21.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(42, 49, 64));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("sistema 24/7, permitiendole gestionar de");

        jLabel22.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(42, 49, 64));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("forma eficiente toda la informacion de su");

        jLabel24.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(42, 49, 64));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("negocio ahorrandole tiempo y dinero.");

        jLabel25.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(42, 49, 64));
        jLabel25.setText("- Informe de ganancias y ventas mensuales");

        jLabel26.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(42, 49, 64));
        jLabel26.setText("- etc...");

        jLabel27.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(42, 49, 64));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("$ 35000.00 mes");

        btg_planGroup.add(jrb_premium);

        javax.swing.GroupLayout roundedPanel7Layout = new javax.swing.GroupLayout(roundedPanel7);
        roundedPanel7.setLayout(roundedPanel7Layout);
        roundedPanel7Layout.setHorizontalGroup(
            roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel7Layout.createSequentialGroup()
                .addGroup(roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel7Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(roundedPanel7Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel26))))
                        .addGap(0, 41, Short.MAX_VALUE))
                    .addGroup(roundedPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(roundedPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundedPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundedPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundedPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(roundedPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jrb_premium)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundedPanel7Layout.setVerticalGroup(
            roundedPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jrb_premium)
                .addGap(1, 1, 1)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addGap(32, 32, 32)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel26)
                .addGap(18, 21, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addGap(23, 23, 23))
        );

        btn_showUserInfo.setText("Continuar");
        btn_showUserInfo.setEnabled(false);
        btn_showUserInfo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btn_showUserInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel12.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(42, 49, 64));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Actualmente solo contamos con un unico plan de beneficios.");

        btn_backCompanyInfo.setBackground(new java.awt.Color(194, 203, 212));
        btn_backCompanyInfo.setForeground(new java.awt.Color(42, 49, 64));
        btn_backCompanyInfo.setText("Regresar");
        btn_backCompanyInfo.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        btn_backCompanyInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
        panelShadow2.setLayout(panelShadow2Layout);
        panelShadow2Layout.setHorizontalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(roundedPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_backCompanyInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175)
                .addComponent(btn_showUserInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelShadow2Layout.setVerticalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(roundedPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_showUserInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_backCompanyInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jpl_selectPlanLayout = new javax.swing.GroupLayout(jpl_selectPlan);
        jpl_selectPlan.setLayout(jpl_selectPlanLayout);
        jpl_selectPlanLayout.setHorizontalGroup(
            jpl_selectPlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl_selectPlanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jpl_selectPlanLayout.setVerticalGroup(
            jpl_selectPlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl_selectPlanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpl_selectPlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpl_selectPlanLayout.createSequentialGroup()
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpl_content.add(jpl_selectPlan, "card3");

        jpl_userInfo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/loginPapeleria.png"))); // NOI18N

        jLabel29.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(42, 49, 64));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Crear Usuario");

        jLabel30.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(42, 49, 64));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Por favor ingrese su informacion personal. Esta se usara para construir su usuario");

        jLabel31.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(42, 49, 64));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("como administrador (Los campos con * son obligatorios)");

        jLabel32.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(42, 49, 64));
        jLabel32.setText("Cédula*");

        roundedPanel8.setBackground(new java.awt.Color(242, 246, 253));
        roundedPanel8.setBorderColor(new java.awt.Color(119, 141, 169));

        txt_userId.setBackground(new java.awt.Color(242, 246, 253));
        txt_userId.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txt_userId.setForeground(new java.awt.Color(42, 49, 64));
        txt_userId.setBorder(null);

        javax.swing.GroupLayout roundedPanel8Layout = new javax.swing.GroupLayout(roundedPanel8);
        roundedPanel8.setLayout(roundedPanel8Layout);
        roundedPanel8Layout.setHorizontalGroup(
            roundedPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_userId, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundedPanel8Layout.setVerticalGroup(
            roundedPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_userId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel33.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(42, 49, 64));
        jLabel33.setText("Nombre*");

        roundedPanel9.setBackground(new java.awt.Color(242, 246, 253));
        roundedPanel9.setBorderColor(new java.awt.Color(119, 141, 169));

        txt_personName.setBackground(new java.awt.Color(242, 246, 253));
        txt_personName.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txt_personName.setForeground(new java.awt.Color(42, 49, 64));
        txt_personName.setBorder(null);

        javax.swing.GroupLayout roundedPanel9Layout = new javax.swing.GroupLayout(roundedPanel9);
        roundedPanel9.setLayout(roundedPanel9Layout);
        roundedPanel9Layout.setHorizontalGroup(
            roundedPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_personName, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundedPanel9Layout.setVerticalGroup(
            roundedPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_personName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundedPanel10.setBackground(new java.awt.Color(242, 246, 253));
        roundedPanel10.setBorderColor(new java.awt.Color(42, 49, 64));

        roundedPanel11.setBackground(new java.awt.Color(242, 246, 253));
        roundedPanel11.setBorderColor(new java.awt.Color(119, 141, 169));

        txt_username.setBackground(new java.awt.Color(242, 246, 253));
        txt_username.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txt_username.setForeground(new java.awt.Color(42, 49, 64));
        txt_username.setBorder(null);

        javax.swing.GroupLayout roundedPanel11Layout = new javax.swing.GroupLayout(roundedPanel11);
        roundedPanel11.setLayout(roundedPanel11Layout);
        roundedPanel11Layout.setHorizontalGroup(
            roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_username, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundedPanel11Layout.setVerticalGroup(
            roundedPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel34.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(42, 49, 64));
        jLabel34.setText("Credenciales de acceso");

        jLabel35.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(42, 49, 64));
        jLabel35.setText("Usuario*");

        jLabel36.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(42, 49, 64));
        jLabel36.setText("Contraseña*");

        roundedPanel12.setBackground(new java.awt.Color(242, 246, 253));
        roundedPanel12.setBorderColor(new java.awt.Color(119, 141, 169));

        jpld_passwd.setBackground(new java.awt.Color(242, 246, 253));
        jpld_passwd.setForeground(new java.awt.Color(42, 49, 64));
        jpld_passwd.setText("jPasswordField1");
        jpld_passwd.setBorder(null);

        javax.swing.GroupLayout roundedPanel12Layout = new javax.swing.GroupLayout(roundedPanel12);
        roundedPanel12.setLayout(roundedPanel12Layout);
        roundedPanel12Layout.setHorizontalGroup(
            roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpld_passwd, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundedPanel12Layout.setVerticalGroup(
            roundedPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpld_passwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel37.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(42, 49, 64));
        jLabel37.setText("Rol");

        cbx_role.setBackground(new java.awt.Color(242, 246, 253));
        cbx_role.setForeground(new java.awt.Color(42, 49, 64));
        cbx_role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador" }));

        rbn_seePasswd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbn_seePasswd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/eye.png"))); // NOI18N

        javax.swing.GroupLayout roundedPanel10Layout = new javax.swing.GroupLayout(roundedPanel10);
        roundedPanel10.setLayout(roundedPanel10Layout);
        roundedPanel10Layout.setHorizontalGroup(
            roundedPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel10Layout.createSequentialGroup()
                .addGroup(roundedPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel10Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel34))
                    .addGroup(roundedPanel10Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(roundedPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundedPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel35)
                                .addComponent(jLabel36)
                                .addComponent(jLabel37)
                                .addGroup(roundedPanel10Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(roundedPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(roundedPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbx_role, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(roundedPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbn_seePasswd)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundedPanel10Layout.setVerticalGroup(
            roundedPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel10Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbn_seePasswd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbx_role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btn_showSummary.setText("Continuar");
        btn_showSummary.setEnabled(false);
        btn_showSummary.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btn_showSummary.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btn_backSelectPlan.setBackground(new java.awt.Color(194, 203, 212));
        btn_backSelectPlan.setForeground(new java.awt.Color(42, 49, 64));
        btn_backSelectPlan.setText("Regresar");
        btn_backSelectPlan.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        btn_backSelectPlan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelShadow3Layout = new javax.swing.GroupLayout(panelShadow3);
        panelShadow3.setLayout(panelShadow3Layout);
        panelShadow3Layout.setHorizontalGroup(
            panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
            .addGroup(panelShadow3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundedPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel33)
                        .addComponent(roundedPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelShadow3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow3Layout.createSequentialGroup()
                        .addComponent(btn_backSelectPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(168, 168, 168)
                        .addComponent(btn_showSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelShadow3Layout.setVerticalGroup(
            panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addGroup(panelShadow3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow3Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundedPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundedPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(roundedPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_showSummary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_backSelectPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jpl_userInfoLayout = new javax.swing.GroupLayout(jpl_userInfo);
        jpl_userInfo.setLayout(jpl_userInfoLayout);
        jpl_userInfoLayout.setHorizontalGroup(
            jpl_userInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl_userInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelShadow3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jpl_userInfoLayout.setVerticalGroup(
            jpl_userInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl_userInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpl_userInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpl_userInfoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panelShadow3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpl_content.add(jpl_userInfo, "card4");

        jpl_summary.setBackground(new java.awt.Color(255, 255, 255));

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/loginPapeleria.png"))); // NOI18N

        jLabel39.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(42, 49, 64));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Información Ingresada");

        jLabel40.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(42, 49, 64));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Por favor verifique la informacion ingresada antes de registrarla en");

        jLabel42.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(42, 49, 64));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("el sistema");

        jScrollPane1.setBackground(new java.awt.Color(242, 246, 253));
        jScrollPane1.setBorder(null);

        jPanel2.setBackground(new java.awt.Color(242, 246, 253));

        jLabel41.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(42, 49, 64));
        jLabel41.setText("Informacion de la papelería");

        jlb_companyNit.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jlb_companyNit.setForeground(new java.awt.Color(42, 49, 64));
        jlb_companyNit.setText("NIT:");

        jlb_companyName.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jlb_companyName.setForeground(new java.awt.Color(42, 49, 64));
        jlb_companyName.setText("Nombre:");

        jlb_companyAddress.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jlb_companyAddress.setForeground(new java.awt.Color(42, 49, 64));
        jlb_companyAddress.setText("Dirección:");

        jlb_companyPhone.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jlb_companyPhone.setForeground(new java.awt.Color(42, 49, 64));
        jlb_companyPhone.setText("Teléfono:");

        jlb_companyEmail.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jlb_companyEmail.setForeground(new java.awt.Color(42, 49, 64));
        jlb_companyEmail.setText("Correo:");

        jLabel48.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(42, 49, 64));
        jLabel48.setText("Plan Seleccionado");

        jlb_planName.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jlb_planName.setForeground(new java.awt.Color(42, 49, 64));
        jlb_planName.setText("Plan Premium");

        jlb_planPrice.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jlb_planPrice.setForeground(new java.awt.Color(42, 49, 64));
        jlb_planPrice.setText("Valor a pagar: $");

        jLabel51.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(42, 49, 64));
        jLabel51.setText("Datos del usuario (Administrador)");

        jlb_userId.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jlb_userId.setForeground(new java.awt.Color(42, 49, 64));
        jlb_userId.setText("Cedula:");

        jlb_personName.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jlb_personName.setForeground(new java.awt.Color(42, 49, 64));
        jlb_personName.setText("Nombre:");

        jlb_username.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jlb_username.setForeground(new java.awt.Color(42, 49, 64));
        jlb_username.setText("Usuario:");

        jlb_passwd.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jlb_passwd.setForeground(new java.awt.Color(42, 49, 64));
        jlb_passwd.setText("Contraseña: *********");

        jlb_role.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jlb_role.setForeground(new java.awt.Color(42, 49, 64));
        jlb_role.setText("Rol:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlb_companyName)
                                    .addComponent(jlb_companyNit)
                                    .addComponent(jlb_companyAddress)
                                    .addComponent(jlb_companyPhone)
                                    .addComponent(jlb_companyEmail)
                                    .addComponent(jlb_planName)
                                    .addComponent(jlb_planPrice)
                                    .addComponent(jlb_userId)
                                    .addComponent(jlb_personName)
                                    .addComponent(jlb_username)
                                    .addComponent(jlb_passwd)
                                    .addComponent(jlb_role)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel48)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel51))))
                        .addGap(0, 212, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel41)
                .addGap(18, 18, 18)
                .addComponent(jlb_companyNit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlb_companyName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlb_companyAddress)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlb_companyPhone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlb_companyEmail)
                .addGap(26, 26, 26)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlb_planName)
                .addGap(18, 18, 18)
                .addComponent(jlb_planPrice)
                .addGap(23, 23, 23)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel51)
                .addGap(18, 18, 18)
                .addComponent(jlb_userId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlb_personName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlb_username)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlb_passwd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlb_role)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        btn_registerCompany.setText("Registrar Informacion");
        btn_registerCompany.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        btn_backUserInfo.setBackground(new java.awt.Color(194, 203, 212));
        btn_backUserInfo.setForeground(new java.awt.Color(42, 49, 64));
        btn_backUserInfo.setText("Regresar");
        btn_backUserInfo.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        btn_backUserInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelShadow4Layout = new javax.swing.GroupLayout(panelShadow4);
        panelShadow4.setLayout(panelShadow4Layout);
        panelShadow4Layout.setHorizontalGroup(
            panelShadow4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelShadow4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelShadow4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_backUserInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141)
                .addComponent(btn_registerCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelShadow4Layout.setVerticalGroup(
            panelShadow4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelShadow4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelShadow4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btn_registerCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(34, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_backUserInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jpl_summaryLayout = new javax.swing.GroupLayout(jpl_summary);
        jpl_summary.setLayout(jpl_summaryLayout);
        jpl_summaryLayout.setHorizontalGroup(
            jpl_summaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl_summaryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelShadow4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jpl_summaryLayout.setVerticalGroup(
            jpl_summaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl_summaryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpl_summaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpl_summaryLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panelShadow4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpl_content.add(jpl_summary, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpl_content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpl_content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void loadDataSummary(DTOCreateAccountViewSummary dtoCreateAccountViewSummary) {
        this.jlb_companyNit.setText("NIT: " + dtoCreateAccountViewSummary.getCompanyNit());
        this.jlb_companyName.setText("Nombre: " + dtoCreateAccountViewSummary.getCompanyName());
        this.jlb_companyPhone.setText("Teléfono: " + dtoCreateAccountViewSummary.getCompanyPhone());
        this.jlb_companyAddress.setText("Dirección: " + dtoCreateAccountViewSummary.getCompanyAddress());
        this.jlb_companyEmail.setText("Correo: " + dtoCreateAccountViewSummary.getCompanyEmail());

        this.jlb_planName.setText(dtoCreateAccountViewSummary.getPlanName());

        this.jlb_userId.setText("Cedula: " + dtoCreateAccountViewSummary.getUserId());
        this.jlb_personName.setText("Nombre: " + dtoCreateAccountViewSummary.getPersonName());
        this.jlb_username.setText("Usuario: " + dtoCreateAccountViewSummary.getUsername());
        this.jlb_role.setText("Rol: " + dtoCreateAccountViewSummary.getRole());
    }

    public void setControllerRegisterCompany(ActionListener action) {
        this.btn_registerCompany.addActionListener(action);
    }

    public void setControllerBack(ActionListener action) {
        this.btn_back.addActionListener(action);
    }

    public void setControllerShowSelectPlan(ActionListener action) {
        this.btn_showSelectPlan.addActionListener(action);
    }

    public void setControllerShowSummary(ActionListener action) {
        this.btn_showSummary.addActionListener(action);
    }

    public void setControllerShowUserInfo(ActionListener action) {
        this.btn_showUserInfo.addActionListener(action);
    }

    public void setControllerBackUserInfo(ActionListener action) {
        this.btn_backUserInfo.addActionListener(action);
    }

    public void setControllerBackCompanyInfo(ActionListener action) {
        this.btn_backCompanyInfo.addActionListener(action);
    }

    public void setControllerBackSelectPlan(ActionListener action) {
        this.btn_backSelectPlan.addActionListener(action);
    }

    public void setControllerSeePasswd(ActionListener action) {
        this.rbn_seePasswd.addActionListener(action);
    }

    public void jrbPremiumAction(ActionListener action) {
        jrb_premium.addActionListener(action);
    }
    
    public void addFieldsCompanyInfo(DocumentListener listen) {
        txt_companyNit.getDocument().addDocumentListener(listen);
        txt_companyName.getDocument().addDocumentListener(listen);
        txt_companyAddress.getDocument().addDocumentListener(listen);
        txt_companyPhone.getDocument().addDocumentListener(listen);
        txt_companyEmail.getDocument().addDocumentListener(listen);
    }

    public void addFieldsUserInfo(DocumentListener listen) {
        txt_userId.getDocument().addDocumentListener(listen);
        txt_personName.getDocument().addDocumentListener(listen);
        txt_username.getDocument().addDocumentListener(listen);
        jpld_passwd.getDocument().addDocumentListener(listen);
    }

    public ButtonGroup getBtg_planGroup() {
        return btg_planGroup;
    }

    public ButtonAction getBtn_back() {
        return btn_back;
    }

    public ButtonAction getBtn_backCompanyInfo() {
        return btn_backCompanyInfo;
    }

    public ButtonAction getBtn_backSelectPlan() {
        return btn_backSelectPlan;
    }

    public ButtonAction getBtn_registerCompany() {
        return btn_registerCompany;
    }

    public ButtonAction getBtn_showSelectPlan() {
        return btn_showSelectPlan;
    }

    public ButtonAction getBtn_showSummary() {
        return btn_showSummary;
    }

    public ButtonAction getBtn_showUserInfo() {
        return btn_showUserInfo;
    }

    public ButtonAction getBtn_userInfo() {
        return btn_backUserInfo;
    }

    public JComboBox<String> getCbx_role() {
        return cbx_role;
    }

    public JLabel getJlb_companyAddress() {
        return jlb_companyAddress;
    }

    public JLabel getJlb_companyEmail() {
        return jlb_companyEmail;
    }

    public JLabel getJlb_companyName() {
        return jlb_companyName;
    }

    public JLabel getJlb_companyNit() {
        return jlb_companyNit;
    }

    public JLabel getJlb_companyPhone() {
        return jlb_companyPhone;
    }

    public JLabel getJlb_passwd() {
        return jlb_passwd;
    }

    public JLabel getJlb_planName() {
        return jlb_planName;
    }

    public JLabel getJlb_planPrice() {
        return jlb_planPrice;
    }

    public JLabel getJlb_role() {
        return jlb_role;
    }

    public JLabel getJlb_userId() {
        return jlb_userId;
    }

    public JLabel getJlb_userName() {
        return jlb_personName;
    }

    public JLabel getJlb_username() {
        return jlb_username;
    }

    public JPanel getJpl_companyInfo() {
        return jpl_companyInfo;
    }

    public JPanel getJpl_content() {
        return jpl_content;
    }

    public JPanel getJpl_selectPlan() {
        return jpl_selectPlan;
    }

    public JPanel getJpl_summary() {
        return jpl_summary;
    }

    public JPanel getJpl_userInfo() {
        return jpl_userInfo;
    }

    public JRadioButton getJrb_premium() {
        return jrb_premium;
    }

    public JRadioButton getRbn_seePasswd() {
        return rbn_seePasswd;
    }

    public JTextField getTxt_companyAddress() {
        return txt_companyAddress;
    }

    public JTextField getTxt_companyEmail() {
        return txt_companyEmail;
    }

    public JTextField getTxt_companyName() {
        return txt_companyName;
    }

    public JTextField getTxt_companyNit() {
        return txt_companyNit;
    }

    public JTextField getTxt_companyPhone() {
        return txt_companyPhone;
    }

    public JPasswordField getJpld_passwd() {
        return jpld_passwd;
    }

    public JTextField getTxt_userId() {
        return txt_userId;
    }

    public JTextField getTxt_personName() {
        return txt_personName;
    }

    public JTextField getTxt_username() {
        return txt_username;
    }

    public void resetUI() {
        this.jlb_companyNit.setText("NIT:");
        this.jlb_companyName.setText("Nombre:");
        this.jlb_companyPhone.setText("Teléfono:");
        this.jlb_companyAddress.setText("Dirección:");
        this.jlb_companyEmail.setText("Correo:");

        this.jlb_planName.setText("");
        this.jlb_planPrice.setText("Valor a pagar: $");

        this.jlb_userId.setText("Cedula:");
        this.jlb_personName.setText("Nombre:");
        this.jlb_username.setText("Usuario");
        this.jlb_role.setText("Rol:");

        txt_companyNit.setText("");
        txt_companyName.setText("");
        txt_companyPhone.setText("");
        txt_companyAddress.setText("");
        txt_companyEmail.setText("");

        jrb_premium.setSelected(false);

        txt_userId.setText("");
        txt_personName.setText("");
        txt_username.setText("");
        jpld_passwd.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btg_planGroup;
    private com.hector.papeleriasaas.view.swing.buttons.ButtonAction btn_back;
    private com.hector.papeleriasaas.view.swing.buttons.ButtonAction btn_backCompanyInfo;
    private com.hector.papeleriasaas.view.swing.buttons.ButtonAction btn_backSelectPlan;
    private com.hector.papeleriasaas.view.swing.buttons.ButtonAction btn_backUserInfo;
    private com.hector.papeleriasaas.view.swing.buttons.ButtonAction btn_registerCompany;
    private com.hector.papeleriasaas.view.swing.buttons.ButtonAction btn_showSelectPlan;
    private com.hector.papeleriasaas.view.swing.buttons.ButtonAction btn_showSummary;
    private com.hector.papeleriasaas.view.swing.buttons.ButtonAction btn_showUserInfo;
    private javax.swing.JComboBox<String> cbx_role;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel jlb_companyAddress;
    private javax.swing.JLabel jlb_companyEmail;
    private javax.swing.JLabel jlb_companyName;
    private javax.swing.JLabel jlb_companyNit;
    private javax.swing.JLabel jlb_companyPhone;
    private javax.swing.JLabel jlb_passwd;
    private javax.swing.JLabel jlb_personName;
    private javax.swing.JLabel jlb_planName;
    private javax.swing.JLabel jlb_planPrice;
    private javax.swing.JLabel jlb_role;
    private javax.swing.JLabel jlb_userId;
    private javax.swing.JLabel jlb_username;
    private javax.swing.JPanel jpl_companyInfo;
    private javax.swing.JPanel jpl_content;
    private javax.swing.JPanel jpl_selectPlan;
    private javax.swing.JPanel jpl_summary;
    private javax.swing.JPanel jpl_userInfo;
    private javax.swing.JPasswordField jpld_passwd;
    private javax.swing.JRadioButton jrb_premium;
    private com.hector.papeleriasaas.view.swing.panels.PanelShadow panelShadow1;
    private com.hector.papeleriasaas.view.swing.panels.PanelShadow panelShadow2;
    private com.hector.papeleriasaas.view.swing.panels.PanelShadow panelShadow3;
    private com.hector.papeleriasaas.view.swing.panels.PanelShadow panelShadow4;
    private javax.swing.JRadioButton rbn_seePasswd;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel1;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel10;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel11;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel12;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel2;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel3;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel4;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel5;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel6;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel7;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel8;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel9;
    private javax.swing.JTextField txt_companyAddress;
    private javax.swing.JTextField txt_companyEmail;
    private javax.swing.JTextField txt_companyName;
    private javax.swing.JTextField txt_companyNit;
    private javax.swing.JTextField txt_companyPhone;
    private javax.swing.JTextField txt_personName;
    private javax.swing.JTextField txt_userId;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
