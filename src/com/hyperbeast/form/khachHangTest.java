/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.form;

import com.hyperbeast.entity.KhachHang;
import com.hyperbeast.model.khachHangModel;
import com.hyperbeast.utils.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class khachHangTest {
    //DÙNG ĐỂ TEST
    public boolean validateKHTest(int choice, int maTTKH, String tenKH, String soDienThoai, int trangThai) {
        LocalDateTime ldt = LocalDateTime.now();
        Date now = new Date();
        Date ngayTao = now ;
        Date ngayCapNhat = now;
        ArrayList<KhachHang> listKH = new khachHangModel().getKhachHang();
        if(tenKH.isEmpty() || tenKH.trim().isEmpty()) {
            throw new NullPointerException("tên khách hàng rỗng");
        }
        if(soDienThoai.isEmpty() || soDienThoai.trim().isEmpty()) {
            throw new NullPointerException("số điện thoại rỗng rỗng");
        }
        if (soDienThoai.matches("[0-9]")) {
            throw new IllegalArgumentException("số điện thoại không hợp lệ");
        }
        if(!(soDienThoai.length()== 10)) {
            throw new IllegalArgumentException("số điện thoại không hợp lệ");
        }
        for(KhachHang lh : listKH) {
            if(lh.getSoDienThoai().equals(soDienThoai)) {
                throw new IllegalArgumentException("số điện thoại đã đăng ký");
            }
        }
        if(trangThai != 1 || trangThai != 2) {
            throw new IllegalArgumentException("Trạng thái không hợp lệ");
        }
        if(choice == 1) {
            try {
                insertKH(tenKH, soDienThoai, ngayTao, ngayCapNhat, trangThai);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else if(choice == 2) {
            for(KhachHang lh : listKH) {
                if(!(lh.getMaCTKH() == maTTKH)) {
                    throw new IllegalArgumentException("Khách hàng không tồn tại");
                }
             }
            try {
                updateKH(maTTKH, tenKH, soDienThoai, ngayTao, ngayCapNhat, trangThai);
                return true;
            } catch (Exception e) {
                return false;
            }
            
        } else {
            throw new IllegalArgumentException("sai lựa chọn");
        }
    }
    //DÙNG ĐỂ TEST
    boolean insertKH (String tenKH, String sDT, Date ngayTao, Date ngayCN, int trangThai) {
        String query = "insert into THONG_TIN_KH(TenKH, SDT, NgayTao, NgayCN, TrangThai)\n" +
                        "values (?, ?, ?,?, ?)";
        try {
            java.sql.Date  ngayTaoMoi = new java.sql.Date(ngayTao.getTime()); 
            java.sql.Date  ngayCapNhat = new java.sql.Date(ngayCN.getTime());
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tenKH);
            pstmt.setString(2, sDT);
            pstmt.setDate(3, ngayTaoMoi);
            pstmt.setDate(4, ngayCapNhat);
            pstmt.setInt(5, trangThai);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    boolean updateKH (int maTTKH,String tenKH, String sDT, Date ngayTao, Date ngayCN, int trangThai) {
        String query = "update THONG_TIN_KH\n" +
                        "set TenKH = ?, SDT = ?, NgayCN = ?, TrangThai = ?\n" +
                        "where MaTTKH = ?";
        try {
            java.sql.Date  ngayTaoMoi = new java.sql.Date(ngayTao.getTime()); 
            java.sql.Date  ngayCapNhat = new java.sql.Date(ngayCN.getTime());
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tenKH);
            pstmt.setString(2, sDT);
            pstmt.setDate(3, ngayCapNhat);
            pstmt.setInt(4, trangThai);
            pstmt.setInt(5, maTTKH);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
           return false;
        }
    }
    }
    }
    
    
}
