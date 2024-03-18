/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.hyperbeast.main;

import com.hyperbeast.component.menu;
import com.hyperbeast.entity.HoaDon;
import com.hyperbeast.form.QuanLyHoaDon;
import com.hyperbeast.form.chaoJDialog;
import com.hyperbeast.form.dangNhapJDialog;
import com.hyperbeast.form.quanLyKM;
import com.hyperbeast.form.quanLySP;
import com.hyperbeast.form.quanLyBanHang;
import com.hyperbeast.form.quanLyNV;
import com.hyperbeast.form.quanlyTK;
import com.hyperbeast.utils.XacThuc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class main extends javax.swing.JFrame {

    JPanel quanlySP = new quanLySP();
    JPanel quanLyBH = new quanLyBanHang();
    JPanel khuyenMai = new quanLyKM();
    JPanel nhanVien = new quanLyNV();
    JPanel hoaDon = new QuanLyHoaDon();
    JPanel thongKe = new quanlyTK();
    QuanLyHoaDon hoadon1 = new QuanLyHoaDon();
    public main() {
        initComponents();
        setBackground(new Color(0,0,0,0));
        initMoving(main.this);
        menuPanel.setBackground(new Color(61,103,173));
        focus();
        startWelcome();
        login();
    }
    
    void startWelcome() {
        new chaoJDialog(this, true).setVisible(true);
    }

    void login() {
        new dangNhapJDialog(this, true).setVisible(true);
        if(XacThuc.isLogin()) {
            System.exit(0);
        }
        clearFormat();
        panelSP.setOpaque(true);
        panelSP.setBackground(new Color(224, 224, 224));
        jLabel6.setForeground(Color.blue);
        quanLyBH.setVisible(false);
        khuyenMai.setVisible(false);
        nhanVien.setVisible(false);
        hoaDon.setVisible(false);
        dashBoardMain.add(quanlySP).setVisible(true);
    }
    
    private int x;
    private int y;

    public void initMoving(JFrame fram) {
        panelLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }

        });
        panelLogo.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.hyperbeast.swing.PanelBorder();
        menuPanel = new javax.swing.JPanel();
        panelLogo = new javax.swing.JPanel();
        logoLbl = new javax.swing.JLabel();
        panelSP = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        panelBH = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        panelKM = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        panelTK = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        panelNV = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        panelDX = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        panelKM1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        dashBoardMain = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        menuPanel.setBackground(new Color(66, 134, 244)
        );

        panelLogo.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        panelLogo.setOpaque(false);

        logoLbl.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        logoLbl.setForeground(new java.awt.Color(255, 255, 255));
        logoLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/shoes.png"))); // NOI18N
        logoLbl.setText("HyperBeast");

        javax.swing.GroupLayout panelLogoLayout = new javax.swing.GroupLayout(panelLogo);
        panelLogo.setLayout(panelLogoLayout);
        panelLogoLayout.setHorizontalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelLogoLayout.setVerticalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLogoLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(logoLbl)
                .addGap(24, 24, 24))
        );

        panelSP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelSP.setOpaque(false);
        panelSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelSPMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/products.png"))); // NOI18N
        jLabel6.setText("Quản lý sản phẩm");

        javax.swing.GroupLayout panelSPLayout = new javax.swing.GroupLayout(panelSP);
        panelSP.setLayout(panelSPLayout);
        panelSPLayout.setHorizontalGroup(
            panelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSPLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panelSPLayout.setVerticalGroup(
            panelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSPLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel6)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelBH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelBH.setOpaque(false);
        panelBH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBHMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/cross.png"))); // NOI18N
        jLabel7.setText("Bán hàng");

        javax.swing.GroupLayout panelBHLayout = new javax.swing.GroupLayout(panelBH);
        panelBH.setLayout(panelBHLayout);
        panelBHLayout.setHorizontalGroup(
            panelBHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBHLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBHLayout.setVerticalGroup(
            panelBHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBHLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel7)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelKM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelKM.setOpaque(false);
        panelKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelKMMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/sale.png"))); // NOI18N
        jLabel8.setText("Quản lý khuyến mãi");

        javax.swing.GroupLayout panelKMLayout = new javax.swing.GroupLayout(panelKM);
        panelKM.setLayout(panelKMLayout);
        panelKMLayout.setHorizontalGroup(
            panelKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKMLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelKMLayout.setVerticalGroup(
            panelKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKMLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel8)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelTK.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelTK.setOpaque(false);
        panelTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelTKMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/analytics.png"))); // NOI18N
        jLabel9.setText("Quản lý thống kê");

        javax.swing.GroupLayout panelTKLayout = new javax.swing.GroupLayout(panelTK);
        panelTK.setLayout(panelTKLayout);
        panelTKLayout.setHorizontalGroup(
            panelTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTKLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTKLayout.setVerticalGroup(
            panelTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTKLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(25, 25, 25))
        );

        panelNV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelNV.setOpaque(false);
        panelNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelNVMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/teamwork.png"))); // NOI18N
        jLabel10.setText("Quản lý nhân viên");

        javax.swing.GroupLayout panelNVLayout = new javax.swing.GroupLayout(panelNV);
        panelNV.setLayout(panelNVLayout);
        panelNVLayout.setHorizontalGroup(
            panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNVLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelNVLayout.setVerticalGroup(
            panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNVLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel10)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelDX.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelDX.setOpaque(false);
        panelDX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelDXMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setIcon(new javax.swing.ImageIcon("D:\\tmp\\logout.png")); // NOI18N
        jLabel11.setText("Đăng xuất");

        javax.swing.GroupLayout panelDXLayout = new javax.swing.GroupLayout(panelDX);
        panelDX.setLayout(panelDXLayout);
        panelDXLayout.setHorizontalGroup(
            panelDXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDXLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addGap(57, 57, 57))
        );
        panelDXLayout.setVerticalGroup(
            panelDXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDXLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel11)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelKM1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelKM1.setOpaque(false);
        panelKM1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelKM1MouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/bill.png"))); // NOI18N
        jLabel12.setText("Quản lý hóa đơn");

        javax.swing.GroupLayout panelKM1Layout = new javax.swing.GroupLayout(panelKM1);
        panelKM1.setLayout(panelKM1Layout);
        panelKM1Layout.setHorizontalGroup(
            panelKM1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKM1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelKM1Layout.setVerticalGroup(
            panelKM1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKM1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel12)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelKM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelDX, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelKM1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelKM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelDX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );

        dashBoardMain.setPreferredSize(new java.awt.Dimension(1088, 750));
        dashBoardMain.setLayout(new javax.swing.BoxLayout(dashBoardMain, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashBoardMain, javax.swing.GroupLayout.DEFAULT_SIZE, 1111, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashBoardMain, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
            .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 774, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void clearFormat () {
        panelLogo.setOpaque(false);
        panelBH.setOpaque(false);
        panelKM.setOpaque(false);
        panelSP.setOpaque(false);
        panelNV.setOpaque(false);
        panelDX.setOpaque(false);
        panelTK.setOpaque(false);
        panelKM1.setOpaque(false);
        jLabel6.setForeground(new Color(255, 255, 255));
        jLabel7.setForeground(new Color(255, 255,  255));
        jLabel8.setForeground(new Color(255, 255, 255));
        jLabel9.setForeground(new Color(255, 255, 255));
        jLabel10.setForeground(new Color(255, 255, 255));
        jLabel11.setForeground(new Color(255, 255, 255));
        jLabel12.setForeground(new Color(255, 255, 255));
        panelBH.setBackground(new Color(225, 225, 225, 0));
        panelKM.setBackground(new Color(225, 225, 225, 0));
        panelSP.setBackground(new Color(225, 225, 225, 0));
        panelNV.setBackground(new Color(225, 225, 225, 0));
        panelDX.setBackground(new Color(225, 225, 225, 0));
        panelTK.setBackground(new Color(225, 225, 225, 0));
        panelKM1.setBackground(new Color(225, 225, 225, 0));
    }
    private void focus() {
        clearFormat();
        panelSP.setOpaque(true);
        panelSP.setBackground(new Color(224, 224, 224));
        jLabel6.setForeground(Color.blue);
        //test2 form2 =new test2();
        dashBoardMain.add(quanlySP).setVisible(true);
    }
    
    private void panelSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSPMouseClicked
        // TODO add your handling code here:
        clearFormat();
        panelSP.setOpaque(true);
        panelSP.setBackground(new Color(224, 224, 224));
        jLabel6.setForeground(Color.blue);
        quanLyBH.setVisible(false);
        khuyenMai.setVisible(false);
        nhanVien.setVisible(false);
        hoaDon.setVisible(false);
        thongKe.setVisible(false);
        dashBoardMain.add(quanlySP).setVisible(true);
    }//GEN-LAST:event_panelSPMouseClicked

    private void panelBHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBHMouseClicked
        // TODO add your handling code here:
        clearFormat();
        panelBH.setOpaque(true);
        panelBH.setBackground(new Color(224, 224, 224));
        jLabel7.setForeground(Color.blue);
        quanlySP.setVisible(false);
        khuyenMai.setVisible(false);
        nhanVien.setVisible(false);
        hoaDon.setVisible(false);
        thongKe.setVisible(false);
        dashBoardMain.add(quanLyBH).setVisible(true);
    }//GEN-LAST:event_panelBHMouseClicked

    private void panelKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelKMMouseClicked
        // TODO add your handling code here:
        
        clearFormat();
        panelKM.setOpaque(true);
        panelKM.setBackground(new Color(224, 224, 224));
        jLabel8.setForeground(Color.blue);
        quanlySP.setVisible(false);
        quanLyBH.setVisible(false);
        nhanVien.setVisible(false);
        hoaDon.setVisible(false);
        thongKe.setVisible(false);
        dashBoardMain.add(khuyenMai).setVisible(true);
    }//GEN-LAST:event_panelKMMouseClicked

    private void panelTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTKMouseClicked
        // TODO add your handling code here:
        clearFormat();
        panelTK.setOpaque(true);
        panelTK.setBackground(new Color(224, 224, 224));
        jLabel9.setForeground(Color.blue);
        quanlySP.setVisible(false);
        quanLyBH.setVisible(false);
        nhanVien.setVisible(false);
        hoaDon.setVisible(false);
        khuyenMai.setVisible(false);
        dashBoardMain.add(thongKe).setVisible(true);
    }//GEN-LAST:event_panelTKMouseClicked

    private void panelNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelNVMouseClicked
        // TODO add your handling code here:
        if(!XacThuc.isManager().equals("Quản lý")) {
            JOptionPane.showMessageDialog(this, "Tài khoản không có quyền thao tác");
            return;
        }
        clearFormat();
        panelNV.setOpaque(true);
        panelNV.setBackground(new Color(224, 224, 224));
        jLabel10.setForeground(Color.blue);
        quanlySP.setVisible(false);
        quanLyBH.setVisible(false);
        khuyenMai.setVisible(false);
        hoaDon.setVisible(false);
        thongKe.setVisible(false);
        dashBoardMain.add(nhanVien).setVisible(true);
    }//GEN-LAST:event_panelNVMouseClicked

    private void panelDXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDXMouseClicked
        // TODO add your handling code here:
        clearFormat();
        panelDX.setOpaque(true);
        panelDX.setBackground(new Color(224, 224, 224));
        jLabel11.setForeground(Color.blue);
        XacThuc.user = null;
        login();
    }//GEN-LAST:event_panelDXMouseClicked

    private void panelKM1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelKM1MouseClicked
        // TODO add your handling code here:
        clearFormat();
        panelKM1.setOpaque(true);
        panelKM1.setBackground(new Color(224, 224, 224));
        jLabel12.setForeground(Color.blue);
        quanlySP.setVisible(false);
        quanLyBH.setVisible(false);
        khuyenMai.setVisible(false);
        nhanVien.setVisible(false);
        thongKe.setVisible(false);
        dashBoardMain.add(hoaDon).setVisible(true);
        hoadon1.loadLichSuHD();
    }//GEN-LAST:event_panelKM1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel dashBoardMain;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel logoLbl;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel panelBH;
    private com.hyperbeast.swing.PanelBorder panelBorder1;
    private javax.swing.JPanel panelDX;
    private javax.swing.JPanel panelKM;
    private javax.swing.JPanel panelKM1;
    private javax.swing.JPanel panelLogo;
    private javax.swing.JPanel panelNV;
    private javax.swing.JPanel panelSP;
    private javax.swing.JPanel panelTK;
    // End of variables declaration//GEN-END:variables
}
