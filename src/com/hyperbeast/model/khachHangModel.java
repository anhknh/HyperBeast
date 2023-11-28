/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.model;

import com.hyperbeast.entity.KhachHang;
import com.hyperbeast.utils.DBconnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class khachHangModel {
    public ArrayList getKhachHang () {
        ArrayList<KhachHang> listKH = new ArrayList<>();
        String query = "select * from THONG_TIN_KH";
        try {
            Connection conn = DBconnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {   
                if(rs.getInt("TrangThai") == 1) {
                    KhachHang kh = new KhachHang();
                    kh.setMaCTKH(rs.getInt("MaTTKH"));
                    kh.setTenKH(rs.getString("TenKH"));
                    kh.setSoDienThoai(rs.getString("SDT"));
                    kh.setNgayTao(rs.getDate("NgayTao"));
                    kh.setNgayCN(rs.getDate("NgayCN"));
                    listKH.add(kh);
                }
            }
            return listKH;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
}
