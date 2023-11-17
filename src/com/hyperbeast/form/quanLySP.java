/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.hyperbeast.form;

import com.hyperbeast.entity.SanPham;
import com.hyperbeast.entity.SanPhamChiTiet;
import com.hyperbeast.model.sanPhamModel;
import java.awt.Frame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class quanLySP extends javax.swing.JPanel  {

    /**
     * Creates new form test
     */
    private Frame main;
    sanPhamModel spModel = new sanPhamModel();
    ArrayList<SanPham> listSP = new ArrayList<>();
    ArrayList<SanPhamChiTiet> listCTSP = new ArrayList<>();
    int pageNumberSP;
    int pageNumberSPCT;
    int pageSP = 1;
    int pageSPCT = 1;
    int pageSelectSP;
    int pageSelectSPCT;
    ArrayList listSPSize;
    ArrayList listSPCTSize;
    public quanLySP() {
        initComponents();
        statusPageSP();
        statusPageSPCT();
        getPageSP();
        getPageSPCT();
        fillSanPhamTbl();
        fillCTSP();
        fillmau();
        fillKichThuoc();
        fillChatLieu();
        fillChatLieuDe();
        fillDanhMuc();
    }
    
    void statusPageSP() {
        listSPSize = spModel.getMaSP();
        pageNumberSP = (int) Math.ceil((listSPSize.size()/5.0));
        pageSPLbl.setText(pageSP + "/" + pageNumberSP);
    }
    
    void getPageSP() {
        pageSelectSP = (pageSP - 1) * 5;
    }
    void statusPageSPCT() {
        listSPCTSize = spModel.getMaCTSP();
        pageNumberSPCT = (int) Math.ceil((listSPCTSize.size()/5.0));
        pageCTSPLbl.setText(pageSPCT + "/" + pageNumberSPCT);
    }
    
    void getPageSPCT() {
        pageSelectSPCT = (pageSPCT - 1) * 5;
    }
    
    void validateSP( int choice) {
        sanPhamModel  model = new sanPhamModel();
        int maSP = 0;
        String tenSP = tenSPTxt.getText();
        String ngayNhap = ngayNhapTxt.getText();
        String ngayCN = ngayCapNhatTxt.getText();
        int danhMuc;
        String trangThai;
        int rowSelected = sanPhamTbl.getSelectedRow();
        LocalDateTime ldt = LocalDateTime.now();
        String dateNow = (DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH).format(ldt));
        if(tenSP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập tên sản phẩm");
            return;
        }
        if(danhMucCB.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Chưa chọn danh mục sản phẩm");
            return;
        } else {
            danhMuc = danhMucCB.getSelectedIndex() + 1;
        }
        if(trangThaiCB.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Chưa chọn trạng thái sản phẩm");
            return;
        } else {
            trangThai = (String) trangThaiCB.getSelectedItem();
        }
        if(choice == 1) {
            try {
                model.insertSanPham(tenSP, ngayNhap, ngayCN, trangThai, danhMuc);
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                statusPageSP();
                fillSanPhamTbl();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại");
            }
        }
        if(choice == 2) {
            try {
                if(rowSelected < 0) {
                    JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm trong bảng");
                    return;
                } else {
                    maSP = (int) sanPhamTbl.getValueAt(rowSelected, 0);
                }
                ngayCN = dateNow;
                model.updateSanPham(maSP, tenSP, ngayNhap, ngayCN, trangThai, danhMuc);
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công");
                statusPageSP();
                fillSanPhamTbl();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại");
            }
        }
        //System.out.println(tenSP + "\n" + ngayNhap +"\n" + ngayCN +"\n" + danhMuc + "\n" + trangThai);
    }
    
    void validateCTSP(int choice) {
        sanPhamModel  model = new sanPhamModel();
        File file;
        int rowSelected = sanPhamCTTbl.getSelectedRow();
        String maSPcheck;
        if(tenSPLbl.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Chưa chọn sản phẩm");
            return;
        } else {
            maSPcheck = tenSPLbl.getText().substring(0, tenSPLbl.getText().indexOf(" "));
        }
        int maSP = Integer.parseInt(maSPcheck);
        int soLuong = 0;
        float donGia = 0;
        String moTa;
        int maMS = mauSacCB.getSelectedIndex() +1;
        int maSIZE = kichThuocCB.getSelectedIndex() +1;
        int maCL = chatLieuCB.getSelectedIndex() +1;
        int maCLD = chatLieuDCB.getSelectedIndex() +1;
        String maBarCode;
        String iconfilename;
        String tenAnh;
        
        if(soLuongTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Chưa nhập số lượng");
            return;
        } else {
            try {
                soLuong = Integer.parseInt(soLuongTxt.getText());
                if(soLuong <= 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Sai định dạng số");
            }
        }
        
        if(donGiaTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập đơn giá");
            return;
        } else {
            try {
                donGia = Float.parseFloat(donGiaTxt.getText());
                if(donGia <= 0) {
                    JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn 0");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Sai định dạng đơn giá");
            }
        }
        if(ghiChuTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Chưa nhập ghi chú");
            return;
        } else {
            moTa = ghiChuTxt.getText();
        }
        if(maBarCodeTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập mã BarCode");
            return;
        } else {
            if(maBarCodeTxt.getText().length() == 13) {
                maBarCode = maBarCodeTxt.getText();
            } else {
                JOptionPane.showMessageDialog(this, "Mã BarCode phải đủ 13 ký tự");
                return;
            }
        }
        
        if(anhSPLbl.getIcon() == null) {
            JOptionPane.showMessageDialog(this, "Chưa thêm ảnh sản phẩm");
            return;
        } else {
            iconfilename = anhSPLbl.getIcon().toString();
            file = new File(iconfilename);
        }
        
        if(choice == 1) {
            try {
                tenAnh = file.getName();
                spModel.insertSanPhamCT(maSP, soLuong, donGia, maMS, maSIZE, maCL, maCLD, maBarCode, tenAnh, moTa);
                JOptionPane.showMessageDialog(this, "Thêm chi tiết sản phẩm thành công");
                statusPageSPCT();
                fillCTSP();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Thêm chi tiết sản phẩm thất bại");
            }
        } 
        if(choice == 2){
            try {
                if(rowSelected < 0) {
                    JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm trong bảng");
                    return;
                }
                tenAnh = file.getName();
                model.updateSPCT(maSP, soLuong, donGia, maMS, maSIZE, maCL, maCLD, maBarCode, tenAnh, moTa);
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                statusPageSPCT();
                fillCTSP();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
            }
        }
    }
    
    void clearSanPham() {
        tenSPTxt.setText("");
        LocalDateTime ldt = LocalDateTime.now();
        String dateNow = (DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH).format(ldt));
        ngayNhapTxt.setText("" + dateNow);
        ngayCapNhatTxt.setText("" + dateNow);
        danhMucCB.setSelectedIndex(0);
    }
    
    void clearCTSP() {
        tenSPLbl.setText("");
        soLuongTxt.setText("");
        donGiaTxt.setText("");
        ghiChuTxt.setText("");
        mauSacCB.setSelectedIndex(0);
        kichThuocCB.setSelectedIndex(0);
        chatLieuCB.setSelectedIndex(0);
        chatLieuDCB.setSelectedIndex(0);
        maBarCodeTxt.setText("");
        anhSPLbl.setIcon(null);
        anhSPLbl.setText("Ảnh sản phẩm");
    }
    
    void searchSanPham() {
        String timkiem = timKiemTxt.getText();
        ArrayList<SanPham> listSP2 = spModel.getSanPham2();
        ArrayList listTimKiem = spModel.searchSanPham(timkiem);
        if(listTimKiem.size() == 0) {
            JOptionPane.showMessageDialog(main, "Không tìm thấy sản phẩm");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) sanPhamTbl.getModel();
        model.setRowCount(0);
        for (Object maSP : listTimKiem) {
            for (int i = 0; i < listSP2.size(); i++) {
                 if(listSP2.get(i).getMaSP() == (int) maSP) {
                     Object[] data = {
                         listSP2.get(i).getMaSP(), listSP2.get(i).getTenSP(), listSP2.get(i).getNgayNhap(), listSP2.get(i).getNgayCN(),
                         listSP2.get(i).getTenDanhMuc(), listSP2.get(i).getTrangThai()
                     };
                     model.addRow(data);
                 }
            }
        }
    }
    
    void locSanPham() {
        String timkiem = (String) locSPCB.getSelectedItem();
        if( timkiem == null) {
            return;
        }
        ArrayList listTimKiem = spModel.locSanPham(timkiem);
        DefaultTableModel model = (DefaultTableModel) sanPhamCTTbl.getModel();
        model.setRowCount(0);
        for (Object maSP : listTimKiem) {
            for (int i = 0; i < listCTSP.size(); i++) {
                 if(listCTSP.get(i).getMaSP() == (int) maSP) {
                     Object[] data = {
                         listCTSP.get(i).getTenSP(), listCTSP.get(i).getSoLuong(), listCTSP.get(i).getDonGia(), listCTSP.get(i).getTenMau(),
                         listCTSP.get(i).getKichThuoc(), listCTSP.get(i).getTenChatLieu(), listCTSP.get(i).getTenChatLieuDe(), listCTSP.get(i).getMaBarCode(),
                         listCTSP.get(i).getTenAnh()
                     };
                     model.addRow(data);
                 }
            }
        }
    }
    
    void searchSanPhamCT() {
        String timkiem = timKiemCTSPTxt.getText();
        ArrayList listTimKiem = spModel.searchSanPham(timkiem);
        ArrayList<SanPhamChiTiet> listCTSP2 = spModel.getSanPhamCT2();
        if(listTimKiem.size() == 0) {
            JOptionPane.showMessageDialog(main, "Không tìm thấy sản phẩm");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) sanPhamCTTbl.getModel();
        model.setRowCount(0);
        for (Object maSP : listTimKiem) {
            for (int i = 0; i < listCTSP2.size(); i++) {
                 if(listCTSP2.get(i).getMaSP() == (int) maSP) {
                     Object[] data = {
                         listCTSP2.get(i).getTenSP(), listCTSP2.get(i).getSoLuong(), listCTSP2.get(i).getDonGia(), listCTSP2.get(i).getTenMau(),
                         listCTSP2.get(i).getKichThuoc(), listCTSP2.get(i).getTenChatLieu(), listCTSP2.get(i).getTenChatLieuDe(), listCTSP2.get(i).getMaBarCode(),
                         listCTSP2.get(i).getMoTa(),listCTSP2.get(i).getTenAnh()
                     };
                     model.addRow(data);
                 }
            }
        }
    }
    
    void chonAnh() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File("./src/com/hyperbeast/icon/"));
        int response = fileChooser.showOpenDialog(null);
        if(response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            System.out.println(file.getName());
            anhSPLbl.setText("");
            anhSPLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/" +file.getName())));
        }
    }

    void fillmau () {
        ArrayList<String> listMau = new ArrayList<>();
        listMau = spModel.getMauSac();
        DefaultComboBoxModel model = (DefaultComboBoxModel) mauSacCB.getModel();
        model.removeAllElements();
        String[] mauSac = new String[listMau.size()];
        for (int i = 0; i < listMau.size(); i++) {
            mauSac[i] = listMau.get(i);
        }
        mauSacCB.setModel(new javax.swing.DefaultComboBoxModel(mauSac));
    }
    void fillKichThuoc () {
        ArrayList<String> listKichTHuoc = new ArrayList<>();
        listKichTHuoc = spModel.getKichThuoc();
        DefaultComboBoxModel model = (DefaultComboBoxModel) kichThuocCB.getModel();
        model.removeAllElements();
        String[] kichThuoc = new String[listKichTHuoc.size()];
        for (int i = 0; i < listKichTHuoc.size(); i++) {
            kichThuoc[i] = listKichTHuoc.get(i);
        }
        kichThuocCB.setModel(new javax.swing.DefaultComboBoxModel(kichThuoc));
    }
    
    void fillChatLieu () {
        ArrayList<String> listChatLieu = new ArrayList<>();
        listChatLieu = spModel.getChatLieu();
        DefaultComboBoxModel model = (DefaultComboBoxModel) chatLieuCB.getModel();
        model.removeAllElements();
        String[] chatLieu = new String[listChatLieu.size()];
        for (int i = 0; i < listChatLieu.size(); i++) {
            chatLieu[i] = listChatLieu.get(i);
        }
        chatLieuCB.setModel(new javax.swing.DefaultComboBoxModel(chatLieu));
    }
    
    void fillChatLieuDe () {
        ArrayList<String> listChatLieuDe = new ArrayList<>();
        listChatLieuDe = spModel.getChatLieuDe();
        DefaultComboBoxModel model = (DefaultComboBoxModel) chatLieuDCB.getModel();
        model.removeAllElements();
        String[] chatLieuDe = new String[listChatLieuDe.size()];
        for (int i = 0; i < listChatLieuDe.size(); i++) {
            chatLieuDe[i] = listChatLieuDe.get(i);
        }
        chatLieuDCB.setModel(new javax.swing.DefaultComboBoxModel(chatLieuDe));
    }
    
    void fillDanhMuc () {
        ArrayList<String> listDanhMuc = new ArrayList<>();
        listDanhMuc = spModel.getDanhMuc();
        DefaultComboBoxModel model = (DefaultComboBoxModel) danhMucCB.getModel();
        model.removeAllElements();
        String[] danhMuc = new String[listDanhMuc.size()];
        for (int i = 0; i < listDanhMuc.size(); i++) {
            danhMuc[i] = listDanhMuc.get(i);
        }
       danhMucCB.setModel(new javax.swing.DefaultComboBoxModel(danhMuc)); 
    }
    
    void fillSanPhamTbl() {
        listSP = spModel.getSanPham(pageSelectSP);
        listCTSP = spModel.getSanPhamCT(pageSelectSPCT);
        listSP = spModel.getSanPham(pageSelectSP);
        DefaultTableModel model = (DefaultTableModel) sanPhamTbl.getModel();
        model.setRowCount(0);
        for (SanPham sanPham : listSP) {
            Object[] data = {
                sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getNgayNhap(), sanPham.getNgayCN(),
                sanPham.getTenDanhMuc(),sanPham.getTrangThai()
            };
            model.addRow(data);
        }
    }
    
    void fillCTSP() {
        listSP = spModel.getSanPham(pageSelectSP);
        listCTSP = spModel.getSanPhamCT(pageSelectSPCT);
        DefaultTableModel model = (DefaultTableModel) sanPhamCTTbl.getModel();
        model.setRowCount(0);
        //for (int i = 0; i < listSP.size(); i++) {
            for (int j = 0; j < listCTSP.size(); j++) {
                //if(listSP.get(i).getMaSP() == listCTSP.get(j).getMaSP()){
                    Object[] data = {
                        listCTSP.get(j).getTenSP(), listCTSP.get(j).getSoLuong(), listCTSP.get(j).getDonGia(),
                        listCTSP.get(j).getTenMau(),listCTSP.get(j).getKichThuoc(), listCTSP.get(j).getTenChatLieu(),
                        listCTSP.get(j).getTenChatLieuDe(), listCTSP.get(j).getMaBarCode(),listCTSP.get(j).getMoTa(), listCTSP.get(j).getTenAnh()
                   };
                    model.addRow(data);
                //}
            }
       // }
    }
    
    
    void getDataSPSelected(int rowSelected) {
        tenSPTxt.setText((String) sanPhamTbl.getValueAt(rowSelected, 1));
        ngayNhapTxt.setText((String) sanPhamTbl.getValueAt(rowSelected, 2));
        ngayCapNhatTxt.setText((String) sanPhamTbl.getValueAt(rowSelected, 3));
        danhMucCB.setSelectedItem(sanPhamTbl.getValueAt(rowSelected, 4));
        trangThaiCB.setSelectedItem(sanPhamTbl.getValueAt(rowSelected, 5));
    }
    
    void getDataSPCTSelected (int rowSelected) {
        listSP = spModel.getSanPham(pageSelectSP);
        listCTSP = spModel.getSanPhamCT(pageNumberSPCT);
        String tenSP = null;
        for (SanPhamChiTiet spct : listCTSP) {
            if(sanPhamCTTbl.getValueAt(rowSelected, 0).equals(spct.getTenSP())) {
                tenSP = spct.getMaSP() + " - " + spct.getTenSP();
            }
        }
        tenSPLbl.setText(tenSP);
        soLuongTxt.setText("" +sanPhamCTTbl.getValueAt(rowSelected, 1));
        donGiaTxt.setText("" + sanPhamCTTbl.getValueAt(rowSelected, 2));
        mauSacCB.setSelectedItem(sanPhamCTTbl.getValueAt(rowSelected, 3));
        kichThuocCB.setSelectedIndex(3);
        //kichThuocCB.setSelectedItem(sanPhamCTTbl.getValueAt(rowSelected, 4));
        chatLieuCB.setSelectedItem(sanPhamCTTbl.getValueAt(rowSelected, 5));
        chatLieuDCB.setSelectedItem(sanPhamCTTbl.getValueAt(rowSelected, 6));
        maBarCodeTxt.setText("" + sanPhamCTTbl.getValueAt(rowSelected, 7));
        ghiChuTxt.setText("" + sanPhamCTTbl.getValueAt(rowSelected, 8));
        anhSPLbl.setText("");
        anhSPLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/" + sanPhamCTTbl.getValueAt(rowSelected, 9))));
    } 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new tabbed.MaterialTabbed();
        jPanel2 = new javax.swing.JPanel();
        panelBorder1 = new com.hyperbeast.swing.PanelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        sanPhamTbl = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        pageSPLbl = new javax.swing.JLabel();
        timKiemTxt = new com.hyperbeast.swing.TextField();
        jButton1 = new javax.swing.JButton();
        panelBorder2 = new com.hyperbeast.swing.PanelBorder();
        tenSPTxt = new com.hyperbeast.swing.TextField();
        ngayNhapTxt = new com.hyperbeast.swing.TextField();
        danhMucCB = new com.hyperbeast.swing.Combobox();
        jLabel2 = new javax.swing.JLabel();
        trangThaiCB = new com.hyperbeast.swing.Combobox();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        themBtn = new javax.swing.JButton();
        ngayCapNhatTxt = new com.hyperbeast.swing.TextField();
        themCTSPBtn = new javax.swing.JButton();
        locSPCB = new com.hyperbeast.swing.Combobox();
        jPanel1 = new javax.swing.JPanel();
        panelBorder3 = new com.hyperbeast.swing.PanelBorder();
        jScrollPane2 = new javax.swing.JScrollPane();
        sanPhamCTTbl = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        pageCTSPLbl = new javax.swing.JLabel();
        panelBorder4 = new com.hyperbeast.swing.PanelBorder();
        tenSPLbl = new javax.swing.JLabel();
        donGiaTxt = new com.hyperbeast.swing.TextField();
        soLuongTxt = new com.hyperbeast.swing.TextField();
        mauSacCB = new com.hyperbeast.swing.Combobox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ghiChuTxt = new javax.swing.JTextArea();
        kichThuocCB = new com.hyperbeast.swing.Combobox();
        chatLieuCB = new com.hyperbeast.swing.Combobox();
        chatLieuDCB = new com.hyperbeast.swing.Combobox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        themCTBtn = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        panelBorder5 = new com.hyperbeast.swing.PanelBorder();
        anhSPLbl = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        panelBorder6 = new com.hyperbeast.swing.PanelBorder();
        maBarCodeTxt = new com.hyperbeast.swing.TextField();
        jButton8 = new javax.swing.JButton();
        timKiemCTSPTxt = new com.hyperbeast.swing.TextField();
        jButton16 = new javax.swing.JButton();

        setForeground(new java.awt.Color(255, 255, 255));

        tabs.setForeground(new java.awt.Color(102, 102, 102));
        tabs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabs.setPreferredSize(new java.awt.Dimension(1076, 710));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        sanPhamTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản phẩm", "Tên sản phẩm", "Ngày nhập", "Ngày cập nhật", "Danh mục sản phẩm", "Trạng thái"
            }
        ));
        sanPhamTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sanPhamTblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(sanPhamTbl);

        jButton2.setBackground(new java.awt.Color(0, 102, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText(">>");
        jButton2.setBorderPainted(false);
        jButton2.setFocusable(false);
        jButton2.setPreferredSize(new java.awt.Dimension(85, 30));
        jButton2.setRequestFocusEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("<<");
        jButton3.setBorderPainted(false);
        jButton3.setFocusable(false);
        jButton3.setPreferredSize(new java.awt.Dimension(85, 30));
        jButton3.setRequestFocusEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        pageSPLbl.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        pageSPLbl.setText("Trang");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pageSPLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pageSPLbl))
                .addContainerGap())
        );

        timKiemTxt.setBackground(new java.awt.Color(255, 255, 255));
        timKiemTxt.setLabelText("Tìm kiếm theo tên");

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Tìm kiếm");
        jButton1.setBorderPainted(false);
        jButton1.setFocusable(false);
        jButton1.setRequestFocusEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));

        tenSPTxt.setLabelText("Tên sản phẩm");

        ngayNhapTxt.setEnabled(false);
        ngayNhapTxt.setLabelText("Ngày nhập");

        danhMucCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "item1", "item 2" }));
        danhMucCB.setSelectedIndex(-1);
        danhMucCB.setLabeText("Danh mục");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/add.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        trangThaiCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đang kinh doanh", "Ngừng kinh doanh", "Hết hàng", "Sản phẩm lỗi" }));
        trangThaiCB.setSelectedIndex(-1);
        trangThaiCB.setLabeText("Trạng thái");

        jButton4.setBackground(new java.awt.Color(0, 102, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Cập Nhật");
        jButton4.setBorderPainted(false);
        jButton4.setFocusable(false);
        jButton4.setRequestFocusEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 102, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Mới");
        jButton5.setBorderPainted(false);
        jButton5.setFocusable(false);
        jButton5.setRequestFocusEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        themBtn.setBackground(new java.awt.Color(0, 102, 255));
        themBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        themBtn.setForeground(new java.awt.Color(255, 255, 255));
        themBtn.setText("Thêm");
        themBtn.setBorderPainted(false);
        themBtn.setEnabled(false);
        themBtn.setFocusable(false);
        themBtn.setRequestFocusEnabled(false);
        themBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themBtnActionPerformed(evt);
            }
        });

        ngayCapNhatTxt.setEnabled(false);
        ngayCapNhatTxt.setLabelText("Ngày cập nhật");

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(themBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tenSPTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                    .addComponent(ngayNhapTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ngayCapNhatTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trangThaiCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(danhMucCB, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addGap(9, 9, 9))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(danhMucCB, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tenSPTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ngayNhapTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trangThaiCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(themBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ngayCapNhatTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        themCTSPBtn.setBackground(new java.awt.Color(0, 102, 255));
        themCTSPBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        themCTSPBtn.setForeground(new java.awt.Color(255, 255, 255));
        themCTSPBtn.setText("Thêm chi tiết sản phẩm");
        themCTSPBtn.setBorderPainted(false);
        themCTSPBtn.setEnabled(false);
        themCTSPBtn.setFocusable(false);
        themCTSPBtn.setRequestFocusEnabled(false);
        themCTSPBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themCTSPBtnActionPerformed(evt);
            }
        });

        locSPCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "N/A", "Đang kinh doanh", "Ngừng kinh doanh", "Hết hàng", "Sản phẩm lỗi" }));
        locSPCB.setSelectedIndex(-1);
        locSPCB.setLabeText("Lọc sản phẩm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(timKiemTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(themCTSPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(locSPCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(timKiemTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(themCTSPBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(locSPCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        tabs.addTab("Sản Phẩm", jPanel2);

        panelBorder3.setBackground(new java.awt.Color(255, 255, 255));

        sanPhamCTTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Số lượng", "Đơn giá", "Màu sắc", "Kích thước", "Chất liệu chính", "Chất liệu đế", "Mã barcode", "Ghi chú", "Ảnh"
            }
        ));
        sanPhamCTTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sanPhamCTTblMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(sanPhamCTTbl);

        jButton9.setBackground(new java.awt.Color(0, 102, 255));
        jButton9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText(">>");
        jButton9.setBorderPainted(false);
        jButton9.setFocusable(false);
        jButton9.setRequestFocusEnabled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(0, 102, 255));
        jButton10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("<<");
        jButton10.setBorderPainted(false);
        jButton10.setFocusable(false);
        jButton10.setRequestFocusEnabled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        pageCTSPLbl.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        pageCTSPLbl.setText("Trang");

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE)
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pageCTSPLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pageCTSPLbl)))
                .addContainerGap())
        );

        panelBorder4.setBackground(new java.awt.Color(255, 255, 255));

        tenSPLbl.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tenSPLbl.setForeground(new java.awt.Color(0, 102, 255));

        donGiaTxt.setLabelText("Đơn giá");

        soLuongTxt.setLabelText("Số lượng");

        mauSacCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item1", "item2" }));
        mauSacCB.setSelectedIndex(-1);
        mauSacCB.setLabeText("Màu sắc");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/add.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setText("Ghi chú");

        ghiChuTxt.setColumns(20);
        ghiChuTxt.setRows(5);
        jScrollPane3.setViewportView(ghiChuTxt);

        kichThuocCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item1", "item2" }));
        kichThuocCB.setSelectedIndex(-1);
        kichThuocCB.setLabeText("Kích thước");

        chatLieuCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item1", "item2" }));
        chatLieuCB.setSelectedIndex(-1);
        chatLieuCB.setLabeText("Chất liệu chính");

        chatLieuDCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item1", "item2" }));
        chatLieuDCB.setSelectedIndex(-1);
        chatLieuDCB.setLabeText("Chất liệu đế");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/add.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/add.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/add.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(0, 102, 255));
        jButton13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Mới");
        jButton13.setBorderPainted(false);
        jButton13.setFocusable(false);
        jButton13.setRequestFocusEnabled(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        themCTBtn.setBackground(new java.awt.Color(0, 102, 255));
        themCTBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        themCTBtn.setForeground(new java.awt.Color(255, 255, 255));
        themCTBtn.setText("Thêm");
        themCTBtn.setBorderPainted(false);
        themCTBtn.setEnabled(false);
        themCTBtn.setFocusable(false);
        themCTBtn.setRequestFocusEnabled(false);
        themCTBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themCTBtnActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(0, 102, 255));
        jButton15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Cập nhật");
        jButton15.setBorderPainted(false);
        jButton15.setFocusable(false);
        jButton15.setRequestFocusEnabled(false);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder4Layout = new javax.swing.GroupLayout(panelBorder4);
        panelBorder4.setLayout(panelBorder4Layout);
        panelBorder4Layout.setHorizontalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tenSPLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder4Layout.createSequentialGroup()
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane3)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(soLuongTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelBorder4Layout.createSequentialGroup()
                                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(themCTBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(donGiaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder4Layout.createSequentialGroup()
                                .addComponent(mauSacCB, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder4Layout.createSequentialGroup()
                                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(chatLieuDCB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chatLieuCB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(kichThuocCB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        panelBorder4Layout.setVerticalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tenSPLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(donGiaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mauSacCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(soLuongTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(kichThuocCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chatLieuCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chatLieuDCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelBorder4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                            .addComponent(themCTBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        panelBorder5.setBackground(new java.awt.Color(255, 255, 255));

        anhSPLbl.setBackground(new java.awt.Color(187, 187, 187));
        anhSPLbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        anhSPLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        anhSPLbl.setText("Ảnh sản phẩm");
        anhSPLbl.setToolTipText("");
        anhSPLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                anhSPLblMouseClicked(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(0, 102, 255));
        jButton11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Chọn ảnh");
        jButton11.setBorderPainted(false);
        jButton11.setFocusable(false);
        jButton11.setRequestFocusEnabled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(0, 102, 255));
        jButton12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Chụp ảnh");
        jButton12.setBorderPainted(false);
        jButton12.setFocusable(false);
        jButton12.setRequestFocusEnabled(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(anhSPLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(anhSPLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelBorder6.setBackground(new java.awt.Color(255, 255, 255));

        maBarCodeTxt.setLabelText("Mã barcode");

        jButton8.setBackground(new java.awt.Color(0, 102, 255));
        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Tạo mã barcode");
        jButton8.setBorderPainted(false);
        jButton8.setFocusable(false);
        jButton8.setRequestFocusEnabled(false);

        javax.swing.GroupLayout panelBorder6Layout = new javax.swing.GroupLayout(panelBorder6);
        panelBorder6.setLayout(panelBorder6Layout);
        panelBorder6Layout.setHorizontalGroup(
            panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(maBarCodeTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelBorder6Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder6Layout.setVerticalGroup(
            panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(maBarCodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        timKiemCTSPTxt.setLabelText("Tìm kiếm theo tên");

        jButton16.setBackground(new java.awt.Color(0, 102, 255));
        jButton16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("Tìm");
        jButton16.setBorderPainted(false);
        jButton16.setFocusable(false);
        jButton16.setRequestFocusEnabled(false);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(timKiemCTSPTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelBorder6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(panelBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(timKiemCTSPTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelBorder6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        tabs.addTab("Chi Tiết Sản Phẩm", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 1098, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sanPhamTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sanPhamTblMouseClicked
        // TODO add your handling code here:
        int rowSelected = sanPhamTbl.getSelectedRow();
        themBtn.setEnabled(false);
        getDataSPSelected(rowSelected);
        if(rowSelected >= 0) {
            themCTSPBtn.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Chỉ được chọn 1 sản phẩm");
        }
    }//GEN-LAST:event_sanPhamTblMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        searchSanPham();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void sanPhamCTTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sanPhamCTTblMouseClicked
        // TODO add your handling code here:
        int rowSelected = sanPhamCTTbl.getSelectedRow();
        getDataSPCTSelected(rowSelected);
        if(tenSPLbl.getText().equals("")) {
            themCTBtn.setEnabled(false);
        } else {
            themCTBtn.setEnabled(true);
        }
    }//GEN-LAST:event_sanPhamCTTblMouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        chonAnh();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        new anhJDialog(main, true).setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        searchSanPhamCT();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        clearSanPham();
        themBtn.setEnabled(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        clearCTSP();
        if(tenSPLbl.getText().equals("")) {
            themCTBtn.setEnabled(false);
        } else {
            themCTBtn.setEnabled(true);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void themCTSPBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themCTSPBtnActionPerformed
        // TODO add your handling code here:
        String tenSP;
        int rowSelected = sanPhamTbl.getSelectedRow();
        tenSP = sanPhamTbl.getValueAt(rowSelected, 0) +" - " + sanPhamTbl.getValueAt(rowSelected, 1);
        tenSPLbl.setText(tenSP);
        tabs.setSelectedIndex(1);
        if(tenSPLbl.getText().equals("")) {
            themCTBtn.setEnabled(false);
        } else {
            themCTBtn.setEnabled(true);
        }
    }//GEN-LAST:event_themCTSPBtnActionPerformed

    private void themBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themBtnActionPerformed
        // TODO add your handling code here:
        //validateSP(1);
    }//GEN-LAST:event_themBtnActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        validateSP(2);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        new danhMucJDialog(main, true).setVisible(true);
        fillDanhMuc();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        new themMSJDialog(main, true).setVisible(true);
        fillmau();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        new kichThuocJDialog(main, true).setVisible(true);
        fillKichThuoc();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        new chatLieuJDialog(main, true).setVisible(true);
        fillChatLieu();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        new chatLieuDeJDialog(main, true).setVisible(true);
        fillChatLieuDe();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void themCTBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themCTBtnActionPerformed
        // TODO add your handling code here:
        validateCTSP(1);
    }//GEN-LAST:event_themCTBtnActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        validateCTSP(2);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void anhSPLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anhSPLblMouseClicked
        // TODO add your handling code here:
        String urlIcon = anhSPLbl.getIcon().toString();
        File file = new File(urlIcon);
        System.out.println(file.getName());
    }//GEN-LAST:event_anhSPLblMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(pageSP <= pageNumberSP) {
            if(pageSP == 1) {
                return;
            }
            pageSP--;
            getPageSP();
            statusPageSP();
            fillSanPhamTbl();

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(pageSP >= 1 && pageSP < pageNumberSP) {
            pageSP++;
            getPageSP();
            statusPageSP();
            fillSanPhamTbl();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        if(pageSPCT <= pageNumberSPCT) {
            if(pageSPCT == 1) {
                return;
            }
            pageSPCT--;
            getPageSPCT();
            statusPageSPCT();
            fillCTSP();
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if(pageSPCT >= 1 && pageSPCT < pageNumberSPCT) {
            pageSPCT++;
            getPageSPCT();
            statusPageSPCT();
            fillCTSP();
        }
    }//GEN-LAST:event_jButton9ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel anhSPLbl;
    private com.hyperbeast.swing.Combobox chatLieuCB;
    private com.hyperbeast.swing.Combobox chatLieuDCB;
    private com.hyperbeast.swing.Combobox danhMucCB;
    private com.hyperbeast.swing.TextField donGiaTxt;
    private javax.swing.JTextArea ghiChuTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.hyperbeast.swing.Combobox kichThuocCB;
    private com.hyperbeast.swing.Combobox locSPCB;
    private com.hyperbeast.swing.TextField maBarCodeTxt;
    private com.hyperbeast.swing.Combobox mauSacCB;
    private com.hyperbeast.swing.TextField ngayCapNhatTxt;
    private com.hyperbeast.swing.TextField ngayNhapTxt;
    private javax.swing.JLabel pageCTSPLbl;
    private javax.swing.JLabel pageSPLbl;
    private com.hyperbeast.swing.PanelBorder panelBorder1;
    private com.hyperbeast.swing.PanelBorder panelBorder2;
    private com.hyperbeast.swing.PanelBorder panelBorder3;
    private com.hyperbeast.swing.PanelBorder panelBorder4;
    private com.hyperbeast.swing.PanelBorder panelBorder5;
    private com.hyperbeast.swing.PanelBorder panelBorder6;
    private javax.swing.JTable sanPhamCTTbl;
    private javax.swing.JTable sanPhamTbl;
    private com.hyperbeast.swing.TextField soLuongTxt;
    private tabbed.MaterialTabbed tabs;
    private javax.swing.JLabel tenSPLbl;
    private com.hyperbeast.swing.TextField tenSPTxt;
    private javax.swing.JButton themBtn;
    private javax.swing.JButton themCTBtn;
    private javax.swing.JButton themCTSPBtn;
    private com.hyperbeast.swing.TextField timKiemCTSPTxt;
    private com.hyperbeast.swing.TextField timKiemTxt;
    private com.hyperbeast.swing.Combobox trangThaiCB;
    // End of variables declaration//GEN-END:variables
}
