/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.hyperbeast.form;

import com.hyperbeast.entity.nhanVien;
import com.hyperbeast.model.nhanVienModel;
import com.hyperbeast.model.sanPhamModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class quanLyNV extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienJPanel
     */
    int pageNumber;
    int page = 1;
    int pageSelect;
    ArrayList<nhanVien> listNV;
    nhanVienModel nVModel = new nhanVienModel();

    public quanLyNV() {
        initComponents();
        fillTableNV();
        getPage();
        statusPage();
    }

    void statusPage() {
        listNV = nVModel.getNhanVienSize();
        pageNumber = (int) Math.ceil((listNV.size() / 5.0));
        pageLbl.setText(page + "/" + pageNumber);
    }

    void getPage() {
        pageSelect = (page - 1) * 5;
    }

    void validateFrom(int choice) {
        Date dateNow = new Date();
        String hoTen = "";
        boolean gioiTinh = true;
        String sdt = "";
        String email = "";
        String diaChi = "";
        Date ngayTao = null;
        Date ngayCN = null;
        String chucVu = "";
        String tenDangNhap = "";
        String matKhau = "";
        int trangThai = 1;
        
        if(trangThaiCB.getSelectedIndex() == 0) {
            trangThai = 1;
        } else {
            trangThai = 0;
        }
        hoTen = hoTenTxt.getText();
        if (hoTen.isEmpty() || hoTen.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ tên không để trống");
            return;
        }
        if (namRB.isSelected()) {
            gioiTinh = true;
        } else {
            gioiTinh = false;
        }
        sdt = sdtTxt.getText();
        if (sdt.isEmpty() || sdt.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không để trống");
            return;
        }
        if (!sdt.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại chỉ chứa số");
            return;
        }
        if (!(sdt.length() == 10)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải đủ 10 chữ số");
            return;
        }
        email = emailTxt.getText();
        if (email.isEmpty() || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email không để trống");
            return;
        }
        if (!email.matches("^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$")) {
            JOptionPane.showMessageDialog(this, "Sai định dạng email");
            return;
        }
        diaChi = diaChiTxt.getText();
        if (diaChi.isEmpty() || diaChi.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không để trống");
            return;
        }
        ngayTao = dateNow;
        ngayCN = dateNow;
        chucVu = chucVuCB.getSelectedItem().toString();
        tenDangNhap = tenDangNhapTxt.getText();
        if (tenDangNhap.isEmpty() || tenDangNhap.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập tên đăng nhập hoặc chỉ chứa dấu cách");
            return;
        }
        if (!tenDangNhap.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập chỉ được chứa chữ cái không dấu");
            return;
        }
        matKhau = String.valueOf(matKhauTxt.getPassword());
        if (matKhau.isEmpty() || matKhau.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập mật khẩu hoặc chỉ chứa dấu cách");
            return;
        }
        if (!matKhau.matches("[A-Za-z0-9]+")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu chỉ được chứa ký tự không dấu");
            return;
        }
        if (matKhau.length() < 8 || matKhau.length() > 8) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải có 8 ký tự");
            return;
        }
        
        if(choice == 1) {
            ArrayList<nhanVien> listnv = nVModel.getNhanVien3();
            for (nhanVien nv : listnv) {
                if(nv.getTenDangNhap().equals(tenDangNhap)) {
                    JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại");
                    return;
                }
            }
            java.sql.Date  ngayBatDau = new java.sql.Date(ngayTao.getTime()); 
            java.sql.Date  ngayKetThuc = new java.sql.Date(ngayCN.getTime()); 
            nVModel.InsertNV(tenDangNhap, matKhau, hoTen, gioiTinh, sdt, email, diaChi, ngayBatDau, ngayKetThuc, chucVu, trangThai);
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            fillTableNV();
        } else {
            int selectedRow = nhanVienTbl.getSelectedRow();
            int maNV = (int) nhanVienTbl.getValueAt(selectedRow, 0);
            java.sql.Date  ngayKetThuc = new java.sql.Date(ngayCN.getTime()); 
            nVModel.updateNV(maNV, tenDangNhap, matKhau, hoTen, gioiTinh, sdt, email, diaChi, ngayKetThuc, chucVu, trangThai);
            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
            fillTableNV();
        }

    }

    void clearFrom() {
        LocalDateTime ldt = LocalDateTime.now();
        String dateNow = (DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(ldt));
        thongTinLbl.setText("Thông tin chi tiết");
        taiKhoanLbl.setText("Tài khoản");
        hoTenTxt.setText("");
        namRB.setSelected(true);
        sdtTxt.setText("");
        emailTxt.setText("");
        diaChiTxt.setText("");
        ngayCapNhatTxt.setText("" + dateNow);
        ngayTaoTxt.setText("" + dateNow);
        tenDangNhapTxt.setText("");
        matKhauTxt.setText("");
        chucVuCB.setSelectedIndex(0);
        trangThaiCB.setSelectedIndex(0);
    }
    
    void searchNV() {
        String hoTen = hoTenTimTxt.getText();
        if(hoTen == null) {
            hoTen = "";
        }
        ArrayList<nhanVien> listNV2 = nVModel.searchNhanVien(hoTen);
        
        DefaultTableModel model = (DefaultTableModel) nhanVienTbl.getModel();
        model.setRowCount(0);
        for (nhanVien nv : listNV2) {
            Object[] data = {
                nv.getMaNV(), nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getDienThoai(),
                nv.getEmail(), nv.getDiaChi(), nv.getNgayTao(), nv.getNgayCN(), nv.getChucVU(), nv.getTrangThai()
            };
            model.addRow(data);
        }
    }

    void getRowData() {
        ArrayList<nhanVien> listNV = nVModel.getNhanVien2(pageSelect);
        int selectedRow = nhanVienTbl.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        int maNV = (int) nhanVienTbl.getValueAt(selectedRow, 0);
        thongTinLbl.setText("Thông tin chi tiết - Nhân viên " + maNV);
        taiKhoanLbl.setText("Tài khoản - Nhân viên " + maNV);
        hoTenTxt.setText("" + nhanVienTbl.getValueAt(selectedRow, 1));
        if (nhanVienTbl.getValueAt(selectedRow, 2).equals("Nam")) {
            namRB.setSelected(true);
        } else {
            nuRB.setSelected(true);
        }
        sdtTxt.setText("" + nhanVienTbl.getValueAt(selectedRow, 3));
        emailTxt.setText("" + nhanVienTbl.getValueAt(selectedRow, 4));
        diaChiTxt.setText("" + nhanVienTbl.getValueAt(selectedRow, 5));
        ngayTaoTxt.setText("" + nhanVienTbl.getValueAt(selectedRow, 6));
        ngayCapNhatTxt.setText("" + nhanVienTbl.getValueAt(selectedRow, 7));
        chucVuCB.setSelectedItem(nhanVienTbl.getValueAt(selectedRow, 8).toString());
        trangThaiCB.setSelectedItem(nhanVienTbl.getValueAt(selectedRow, 9).toString());
        for (nhanVien nv : listNV) {
            if (nv.getMaNV() == maNV) {
                tenDangNhapTxt.setText(nv.getTenDangNhap());
                matKhauTxt.setText(nv.getMatKhau());
            }
        }
    }

    void fillTableNV() {
        ArrayList<nhanVien> listNV = nVModel.getNhanVien2(pageSelect);
        DefaultTableModel model = (DefaultTableModel) nhanVienTbl.getModel();
        model.setRowCount(0);
        for (nhanVien nv : listNV) {
            Object[] data = {
                nv.getMaNV(), nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getDienThoai(),
                nv.getEmail(), nv.getDiaChi(), nv.getNgayTao(), nv.getNgayCN(), nv.getChucVU(), nv.getTrangThai()
            };
            model.addRow(data);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelBorder1 = new com.hyperbeast.swing.PanelBorder();
        thongTinLbl = new javax.swing.JLabel();
        hoTenTxt = new com.hyperbeast.swing.TextField();
        sdtTxt = new com.hyperbeast.swing.TextField();
        jLabel2 = new javax.swing.JLabel();
        namRB = new com.hyperbeast.swing.RadioButtonCustom();
        nuRB = new com.hyperbeast.swing.RadioButtonCustom();
        emailTxt = new com.hyperbeast.swing.TextField();
        diaChiTxt = new com.hyperbeast.swing.TextField();
        ngayTaoTxt = new com.hyperbeast.swing.TextField();
        ngayCapNhatTxt = new com.hyperbeast.swing.TextField();
        chucVuCB = new com.hyperbeast.swing.Combobox();
        jButton1 = new javax.swing.JButton();
        themBtn = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        trangThaiCB = new com.hyperbeast.swing.Combobox();
        panelBorder2 = new com.hyperbeast.swing.PanelBorder();
        taiKhoanLbl = new javax.swing.JLabel();
        tenDangNhapTxt = new com.hyperbeast.swing.TextField();
        matKhauTxt = new com.hyperbeast.swing.PasswordField();
        jLabel5 = new javax.swing.JLabel();
        panelBorder3 = new com.hyperbeast.swing.PanelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        nhanVienTbl = new javax.swing.JTable();
        hoTenTimTxt = new com.hyperbeast.swing.TextField();
        jButton5 = new javax.swing.JButton();
        pageLbl = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        thongTinLbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        thongTinLbl.setForeground(new java.awt.Color(0, 102, 255));
        thongTinLbl.setText("Thông tin chi tiết");

        hoTenTxt.setLabelText("Họ Tên");

        sdtTxt.setLabelText("Số điện thoại");

        jLabel2.setText("Giới tính");

        buttonGroup1.add(namRB);
        namRB.setSelected(true);
        namRB.setText("Nam");

        buttonGroup1.add(nuRB);
        nuRB.setText("Nữ");

        emailTxt.setLabelText("Email");

        diaChiTxt.setLabelText("Địa chỉ");

        ngayTaoTxt.setEnabled(false);
        ngayTaoTxt.setLabelText("Ngày tạo");

        ngayCapNhatTxt.setEnabled(false);
        ngayCapNhatTxt.setLabelText("Ngày cập nhật");

        chucVuCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nhân viên", "Quản lý", "Vô hiệu hóa" }));
        chucVuCB.setSelectedIndex(-1);
        chucVuCB.setLabeText("Chức vụ");

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        themBtn.setBackground(new java.awt.Color(0, 102, 255));
        themBtn.setForeground(new java.awt.Color(255, 255, 255));
        themBtn.setText("Thêm");
        themBtn.setEnabled(false);
        themBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themBtnActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 255));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Cập nhật");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        trangThaiCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đang hoạt động", "Không hoạt động" }));
        trangThaiCB.setSelectedIndex(-1);
        trangThaiCB.setLabeText("Trạng Thái");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(thongTinLbl))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(sdtTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(namRB, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nuRB, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                            .addComponent(hoTenTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(emailTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(diaChiTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(85, 85, 85)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chucVuCB, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(themBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(ngayTaoTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ngayCapNhatTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
                            .addComponent(trangThaiCB, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(thongTinLbl)
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hoTenTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ngayTaoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(namRB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nuRB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(sdtTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(diaChiTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(ngayCapNhatTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chucVuCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(trangThaiCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(themBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));

        taiKhoanLbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        taiKhoanLbl.setForeground(new java.awt.Color(0, 102, 255));
        taiKhoanLbl.setText("Tài khoản");

        tenDangNhapTxt.setLabelText("Tên đăng nhập");

        matKhauTxt.setLabelText("Mật khẩu");
        matKhauTxt.setShowAndHide(true);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/team.png"))); // NOI18N

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(taiKhoanLbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addComponent(tenDangNhapTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(matKhauTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(taiKhoanLbl)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tenDangNhapTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(matKhauTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        panelBorder3.setBackground(new java.awt.Color(255, 255, 255));

        nhanVienTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Giới tính", "Số điện thoại", "Email", "Địa chỉ", "Ngày tạo", "Ngày cập nhật", "Chức vụ", "Trạng thái"
            }
        ));
        nhanVienTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nhanVienTblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(nhanVienTbl);

        hoTenTimTxt.setLabelText("Tìm kiếm theo tên");
        hoTenTimTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hoTenTimTxtKeyReleased(evt);
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

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pageLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4))
                    .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(hoTenTimTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder3Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(hoTenTimTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pageLbl))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nhanVienTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nhanVienTblMouseClicked
        // TODO add your handling code here:
        getRowData();
        themBtn.setEnabled(false);
    }//GEN-LAST:event_nhanVienTblMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        clearFrom();
        themBtn.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void themBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themBtnActionPerformed
        // TODO add your handling code here:
        validateFrom(1);
    }//GEN-LAST:event_themBtnActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if(page <= pageNumber) {
            if(page == 1) {
                return;
            }
            page--;
            getPage();
            statusPage();
            fillTableNV();

        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if(page >= 1 && page < pageNumber) {
            page++;
            getPage();
            statusPage();
            fillTableNV();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int selectedRow = nhanVienTbl.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Chưa chọn dòng thao tác");
            return;
        }
        validateFrom(2);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void hoTenTimTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hoTenTimTxtKeyReleased
        // TODO add your handling code here:
        searchNV();
    }//GEN-LAST:event_hoTenTimTxtKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.hyperbeast.swing.Combobox chucVuCB;
    private com.hyperbeast.swing.TextField diaChiTxt;
    private com.hyperbeast.swing.TextField emailTxt;
    private com.hyperbeast.swing.TextField hoTenTimTxt;
    private com.hyperbeast.swing.TextField hoTenTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private com.hyperbeast.swing.PasswordField matKhauTxt;
    private com.hyperbeast.swing.RadioButtonCustom namRB;
    private com.hyperbeast.swing.TextField ngayCapNhatTxt;
    private com.hyperbeast.swing.TextField ngayTaoTxt;
    private javax.swing.JTable nhanVienTbl;
    private com.hyperbeast.swing.RadioButtonCustom nuRB;
    private javax.swing.JLabel pageLbl;
    private com.hyperbeast.swing.PanelBorder panelBorder1;
    private com.hyperbeast.swing.PanelBorder panelBorder2;
    private com.hyperbeast.swing.PanelBorder panelBorder3;
    private com.hyperbeast.swing.TextField sdtTxt;
    private javax.swing.JLabel taiKhoanLbl;
    private com.hyperbeast.swing.TextField tenDangNhapTxt;
    private javax.swing.JButton themBtn;
    private javax.swing.JLabel thongTinLbl;
    private com.hyperbeast.swing.Combobox trangThaiCB;
    // End of variables declaration//GEN-END:variables
}
