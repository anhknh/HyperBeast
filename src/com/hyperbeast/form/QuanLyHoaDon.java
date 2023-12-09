/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.hyperbeast.form;

import com.hyperbeast.entity.HoaDon;
import com.hyperbeast.entity.HoaDonChiTiet;
import com.hyperbeast.entity.KhachHang;
import com.hyperbeast.entity.KhuyenMai;
import com.hyperbeast.entity.SanPhamChiTiet;
import com.hyperbeast.model.hoaDonModel;
import com.hyperbeast.model.khachHangModel;
import com.hyperbeast.model.khuyenMaiModel;
import com.hyperbeast.model.sanPhamModel;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class QuanLyHoaDon extends javax.swing.JPanel {

    /**
     * Creates new form QuanLyHoaDon
     */
    int pageNumberSPCT;
    int pageSPCT = 1;
    int pageSelectLSHD;
    hoaDonModel hDModel = new hoaDonModel();
    sanPhamModel sPModel = new sanPhamModel();
    ArrayList listSPCTSize;
    ArrayList<SanPhamChiTiet> listTimKiem;
    khachHangModel kHModel = new khachHangModel();
    ArrayList<KhachHang> listKH;
    khuyenMaiModel kMModel = new khuyenMaiModel();
    ArrayList<KhuyenMai> listKM = kMModel.getKhuyenMai();

    public QuanLyHoaDon() {
        initComponents();
        statusPageSPCT();
        getPageSPCT();
        loadLichSuHD();
        setNow();
    }

    void statusPageSPCT() {
        ArrayList<HoaDon> listHD = hDModel.getLichSuHoaDonSize();
        pageNumberSPCT = (int) Math.ceil((listHD.size() / 10.0));
        pageLbl.setText(pageSPCT + "/" + pageNumberSPCT);
    }

    void getPageSPCT() {
        pageSelectLSHD = (pageSPCT - 1) * 10;
    }

    void setNow() {
        Date dateNow = new Date();
        date1.setDate(dateNow);
        date2.setDate(dateNow);
    }

    void locLichSu() {
        Date batDau = date1.getDate();
        Date ketThuc = date2.getDate();
        if (batDau == null || ketThuc == null) {
            return;
        }
        if (!ketThuc.after(batDau)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu");
            return;
        }
        java.sql.Date ngayBatDau = new java.sql.Date(batDau.getTime());
        java.sql.Date ngayKetThuc = new java.sql.Date(ketThuc.getTime());
        ArrayList<HoaDon> listLSHD = hDModel.locLSHD(ngayBatDau, ngayKetThuc);
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) lichSuHDTbl.getModel();
        model.setRowCount(0);
        for (HoaDon hd : listLSHD) {
            Object[] data = {
                hd.getMaHoaDon(), hd.getTenNhanVien(), hd.getTenKhachHang(), hd.getTongTien(),
                hd.getTenKhuyenMai(), hd.getMucKhuyenMai() + " " + hd.getDonViKhuyenMai(), hd.getSoTienSauKM(),
                hd.getNgayTao(), hd.getTrangThai()
            };
            model.addRow(data);
        }
    }

    public void loadLichSuHD() {
        ArrayList<HoaDon> listLSHD = hDModel.getLichSuHoaDon(pageSelectLSHD);
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) lichSuHDTbl.getModel();
        model.setRowCount(0);
        for (HoaDon hd : listLSHD) {
            Object[] data = {
                hd.getMaHoaDon(), hd.getTenNhanVien(), hd.getTenKhachHang(), hd.getTongTien(),
                hd.getTenKhuyenMai(), hd.getMucKhuyenMai() + " " + hd.getDonViKhuyenMai(), hd.getSoTienSauKM(),
                hd.getNgayTao(), hd.getTrangThai()
            };
            model.addRow(data);
        }
    }

    void clearForm() {
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) gioHangLSTbl.getModel();
        model.setRowCount(0);
        hoaDonCTTxt.setText("");
        chiTietGioHangLbl.setText("Chi tiết giỏ hàng");
    }

    void getCTSPHoaDon() {
        int selectedRow = lichSuHDTbl.getSelectedRow();
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) gioHangLSTbl.getModel();
        model.setRowCount(0);
        if (selectedRow < 0) {
            return;
        }
        int maHD = (int) lichSuHDTbl.getValueAt(selectedRow, 0);
        ArrayList<HoaDonChiTiet> listMaCTSP = hDModel.getHDCT(maHD);
        ArrayList<SanPhamChiTiet> listSPCT = sPModel.getSPCTAll();
        for (HoaDonChiTiet hdct : listMaCTSP) {
            int maCTSP = hdct.getMaCTSP();
            for (int i = 0; i < listSPCT.size(); i++) {
                if (maCTSP == listSPCT.get(i).getMaCTSP()) {
                    Object[] data = {
                        listSPCT.get(i).getMaCTSP(), listSPCT.get(i).getTenSP(), listSPCT.get(i).getTenMau(), listSPCT.get(i).getKichThuoc(),
                        listSPCT.get(i).getTenChatLieu(), listSPCT.get(i).getTenChatLieuDe(), hdct.getSoLuong(), hdct.getDonGia(),
                        hdct.getSoLuong() * hdct.getDonGia()
                    };
                    model.addRow(data);
                }
            }
        }
    }

    void getRowHD(int selectedRow) {
        hoaDonCTTxt.setText("");
        ArrayList<HoaDon> listLSHD = hDModel.getLichSuHoaDon(pageSelectLSHD);
        int maHD = (int) lichSuHDTbl.getValueAt(selectedRow, 0);
        String tenNhanVien = "" + lichSuHDTbl.getValueAt(selectedRow, 1);
        String tenKhachHang;
        String tenKhuyenMai;
        String mucGiam = lichSuHDTbl.getValueAt(selectedRow, 5) + "";
        if(lichSuHDTbl.getValueAt(selectedRow, 2) == null) {
            tenKhachHang = "";
        } else {
            tenKhachHang = lichSuHDTbl.getValueAt(selectedRow, 2) + "";
        }
        if(lichSuHDTbl.getValueAt(selectedRow, 4) ==  null) {
            tenKhuyenMai = "";
        }  else {
            tenKhuyenMai = lichSuHDTbl.getValueAt(selectedRow, 4)+"";
        }
        String ngayTao = "" + lichSuHDTbl.getValueAt(selectedRow, 7);
        String trangThai = "" + lichSuHDTbl.getValueAt(selectedRow, 8);
        float tienTong = (float) lichSuHDTbl.getValueAt(selectedRow, 3);
        String patternTienTe = "###,###,###";
        DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
        String stringTienTe = formatTienTe.format(tienTong);
        String patternTienTe1 = "###,###,###";
        DecimalFormat formatTienTe1 = new DecimalFormat(patternTienTe1);
        String sauGiam = formatTienTe1.format(lichSuHDTbl.getValueAt(selectedRow, 6));
        hoaDonCTTxt.setText("********************************************\n");
        hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "*                HYPERBEAST                *\n");
        hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "********************************************\n");
        hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "Tên nhân viên:                    " + tenNhanVien + "\n");
        hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "Tên khách hàng:                   " + tenKhachHang + "\n");
        hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "Ngày tạo:                         " + ngayTao + "\n");
        hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "--------------------------------------------\n");
        if (trangThai.equals("Đã thanh toán")) {
            hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "Tên sản phẩm" + "\t" + "|Số lượng" + "\t" + "|Đơn giá"+"\n");
            for (int i = 0; i < gioHangLSTbl.getRowCount(); i++) {
                String tenSP = "" + gioHangLSTbl.getValueAt(i, 1);
                String soLuong = "" + gioHangLSTbl.getValueAt(i, 6);
                String donGia = "" + gioHangLSTbl.getValueAt(i, 7);
                hoaDonCTTxt.setText(hoaDonCTTxt.getText()+ tenSP + "\t|" + soLuong + "\t"+ "\t|" + donGia+"\n");
            }
            hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "--------------------------------------------\n");
                hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "Tổng tiền:                      " + stringTienTe + "\n");
            if(tenKhuyenMai.isEmpty()) {
                hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "Khuyến mãi:                      " + tenKhuyenMai + "\n");
                hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "Mức giảm:                      "+ "\n");
                hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "Sau giảm:                      " + "\n");
                
            } else {
                hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "Khuyến mãi:                     " + tenKhuyenMai + "\n");
                hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "Mức giảm:                       "+mucGiam +"\n");
                hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "Sau giảm:                       " + sauGiam+"\n");
            }
            for (HoaDon hoaDon : listLSHD) {
                if(hoaDon.getMaHoaDon() == maHD) {
                    String httt = hoaDon.getHinhThucThanhToan();
                    hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "Thanh toán:                     " + httt + "\n");
                }
            }
            hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "********************************************\n");
            hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "           Cảm ơn và hẹn gặp lại            \n");
        } else if (trangThai.equals("Đã Hủy")) {
            for (HoaDon hoaDon : listLSHD) {
                if (hoaDon.getMaHoaDon() == maHD) {
                    String lydoHuy = hoaDon.getGhiChu();
                    hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "Lý do hủy:"+lydoHuy+"\n");
                    hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "********************************************\n");
                    hoaDonCTTxt.setText(hoaDonCTTxt.getText() + "           Cảm ơn và hẹn gặp lại            \n");
                }
            }
        }
    }

//    void getRowLSHD(int selectedRow) {
//        int maHD = (int) lichSuHDTbl.getValueAt(selectedRow, 0);
//        ArrayList<HoaDon> listLSHD = hDModel.getLichSuHoaDon(pageSelectLSHD);
//        ArrayList<KhachHang> listKH = kHModel.getKhachHangTheoHD(maHD);
//        tenHDTxt.setText("Thông tin chi tiết - Hóa đơn " +maHD);
//        chiTietGioHangLbl.setText("Chi tiết giỏ hàng - Hóa đơn " +maHD);
//        tenNVTxt.setText("" + lichSuHDTbl.getValueAt(selectedRow, 1));
//        ngayTaoLStxt.setText("" +lichSuHDTbl.getValueAt(selectedRow, 7));
//        giamGiaTxt.setText("" +lichSuHDTbl.getValueAt(selectedRow, 4));
//        mucGiamTxt.setText("" +lichSuHDTbl.getValueAt(selectedRow, 5));
//        String patternTienTe1 = "###,###,###";
//        DecimalFormat formatTienTe1 = new DecimalFormat(patternTienTe1);
//        String stringTienTe1 = formatTienTe1.format(lichSuHDTbl.getValueAt(selectedRow, 6));
//        sauGiamTxt.setText(stringTienTe1);
//        if(lichSuHDTbl.getValueAt(selectedRow, 2) == null) {
//            tenKHTxt.setText("");
//        } else {
//            tenKHTxt.setText(lichSuHDTbl.getValueAt(selectedRow, 2) + "");
//        }
//        for (KhachHang khachHang : listKH) {
//            sdtTxt.setText(khachHang.getSoDienThoai());
//        }
//        if(listKH.size() == 0) {
//            sdtTxt.setText("");
//        }
//        float tienTong = (float) lichSuHDTbl.getValueAt(selectedRow, 3);
//        String patternTienTe = "###,###,###";
//        DecimalFormat formatTienTe = new DecimalFormat(patternTienTe);
//        String stringTienTe = formatTienTe.format(tienTong);
//        tongGiaTriTxt.setText(stringTienTe);
//        for (HoaDon hoaDon : listLSHD) {
//            if(hoaDon.getMaHoaDon() == maHD) {
//                hinhThucTTTxt.setText(hoaDon.getHinhThucThanhToan());
//                lyDoHuyTxt.setText(hoaDon.getGhiChu());
//            }
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        panelBorder4 = new com.hyperbeast.swing.PanelBorder();
        jScrollPane2 = new javax.swing.JScrollPane();
        gioHangLSTbl = new javax.swing.JTable();
        chiTietGioHangLbl = new javax.swing.JLabel();
        panelBorder5 = new com.hyperbeast.swing.PanelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        lichSuHDTbl = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        pageLbl = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        date1 = new com.toedter.calendar.JDateChooser();
        date2 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelBorder6 = new com.hyperbeast.swing.PanelBorder();
        jScrollPane3 = new javax.swing.JScrollPane();
        hoaDonCTTxt = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();

        panelBorder4.setBackground(new java.awt.Color(255, 255, 255));

        gioHangLSTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã CTSP", "Tên sản phẩm", "Màu sắc", "Kích thước", "Chất liệu chính", "Chất liệu đế", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(gioHangLSTbl);

        chiTietGioHangLbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        chiTietGioHangLbl.setForeground(new java.awt.Color(0, 102, 255));
        chiTietGioHangLbl.setText("Chi tiết giỏ hàng");

        javax.swing.GroupLayout panelBorder4Layout = new javax.swing.GroupLayout(panelBorder4);
        panelBorder4.setLayout(panelBorder4Layout);
        panelBorder4Layout.setHorizontalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chiTietGioHangLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        panelBorder4Layout.setVerticalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(chiTietGioHangLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        panelBorder5.setBackground(new java.awt.Color(255, 255, 255));

        lichSuHDTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên người tạo", "Tên khách hàng", "Tổng giá trị", "Khuyến mãi", "Mức giảm", "Sau giảm", "Ngày tạo", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lichSuHDTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lichSuHDTblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lichSuHDTbl);
        if (lichSuHDTbl.getColumnModel().getColumnCount() > 0) {
            lichSuHDTbl.getColumnModel().getColumn(0).setPreferredWidth(30);
            lichSuHDTbl.getColumnModel().getColumn(3).setPreferredWidth(50);
        }

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 255));
        jLabel2.setText("Lịch sử hóa đơn");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/arrow.png"))); // NOI18N
        jLabel1.setText("Làm mới");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 102, 255));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("<<");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        pageLbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pageLbl.setText("Trang");

        jButton4.setBackground(new java.awt.Color(0, 102, 255));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText(">>");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        date1.setDateFormatString("dd-MM-yyyy");

        date2.setDateFormatString("dd-MM-yyyy");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("-");

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Bắt đầu:");

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Kết thúc:");

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pageLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4))
                    .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelBorder5Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder5Layout.createSequentialGroup()
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder5Layout.createSequentialGroup()
                                .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(232, 232, 232)))
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(panelBorder5Layout.createSequentialGroup()
                                .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1)
                        .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pageLbl))
                .addGap(9, 9, 9))
        );

        panelBorder6.setBackground(new java.awt.Color(255, 255, 255));

        hoaDonCTTxt.setColumns(20);
        hoaDonCTTxt.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        hoaDonCTTxt.setRows(5);
        hoaDonCTTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(hoaDonCTTxt);

        jButton2.setBackground(new java.awt.Color(0, 102, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("In hóa đơn");
        jButton2.setBorderPainted(false);
        jButton2.setFocusPainted(false);
        jButton2.setRequestFocusEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder6Layout = new javax.swing.GroupLayout(panelBorder6);
        panelBorder6.setLayout(panelBorder6Layout);
        panelBorder6Layout.setHorizontalGroup(
            panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        panelBorder6Layout.setVerticalGroup(
            panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelBorder5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelBorder6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1098, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 13, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 14, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 729, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lichSuHDTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lichSuHDTblMouseClicked
        // TODO add your handling code here:
        int selectedRow = lichSuHDTbl.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        getCTSPHoaDon();
        getRowHD(selectedRow);
        //getRowLSHD(selectedRow);
    }//GEN-LAST:event_lichSuHDTblMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        setNow();
        clearForm();
        statusPageSPCT();
        loadLichSuHD();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (pageSPCT <= pageNumberSPCT) {
            if (pageSPCT == 1) {
                return;
            }
            pageSPCT--;
            getPageSPCT();
            statusPageSPCT();
            loadLichSuHD();
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (pageSPCT >= 1 && pageSPCT < pageNumberSPCT) {
            pageSPCT++;
            getPageSPCT();
            statusPageSPCT();
            loadLichSuHD();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        locLichSu();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int selectedRow = lichSuHDTbl.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn thao tác");
            return;
        }
        try {
            // TODO add your handling code here:
            hoaDonCTTxt.print();
        } catch (PrinterException ex) {
            Logger.getLogger(QuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chiTietGioHangLbl;
    private com.toedter.calendar.JDateChooser date1;
    private com.toedter.calendar.JDateChooser date2;
    private javax.swing.JTable gioHangLSTbl;
    private javax.swing.JTextArea hoaDonCTTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable lichSuHDTbl;
    private javax.swing.JLabel pageLbl;
    private com.hyperbeast.swing.PanelBorder panelBorder4;
    private com.hyperbeast.swing.PanelBorder panelBorder5;
    private com.hyperbeast.swing.PanelBorder panelBorder6;
    // End of variables declaration//GEN-END:variables
}
