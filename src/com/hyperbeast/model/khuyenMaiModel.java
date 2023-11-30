/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.model;

import com.hyperbeast.entity.KhuyenMai;
import com.hyperbeast.form.quanLyKM;
import com.hyperbeast.utils.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class khuyenMaiModel {
    public ArrayList getKhuyenMai () {
        ArrayList<KhuyenMai> listKhuyenMai = new ArrayList<>();
        String query = "select * from KHUYEN_MAI";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {                
                KhuyenMai kM =  new KhuyenMai();
                kM.setMaKH(rs.getInt("MaKM"));
                kM.setTenKhuyenMai(rs.getString("TenKhuyenMai"));
                kM.setNgayBatDau(rs.getDate("NgayBD"));
                kM.setNgayKetThuc(rs.getDate("NgayKT"));
                kM.setMucGiam(rs.getFloat("MucGiam"));
                kM.setMaApDung(rs.getString("MaGiam"));
                if(rs.getInt("TrangThai") == 0) {
                    kM.setTrangThai("Đang hoạt động");
                } else {
                    kM.setTrangThai("Không hoạt động");
                }
                if(rs.getInt("DonVi") == 0) {
                    kM.setDonVi("VNĐ");
                } else {
                    kM.setDonVi("%");
                }
                listKhuyenMai.add(kM);
            }
            return listKhuyenMai;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public boolean insertKhuyenMai(String tenKhuyenMai, Date ngayBD, Date ngayKT, float mucGiam, String maGiam, int trangThai, int donVi) {
        String query = "insert into KHUYEN_MAI(TenKhuyenMai, NgayBD, NgayKT, MucGiam, MaGiam, TrangThai, DonVi)\n" +
                        "values(?, ?, ?, ?, ?,?, ?)";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tenKhuyenMai);
            pstmt.setDate(2,  ngayBD);
            pstmt.setDate(3,  ngayKT);
            pstmt.setFloat(4, mucGiam);
            pstmt.setString(5, maGiam);
            pstmt.setInt(6, trangThai);
            pstmt.setInt(7, donVi);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public boolean insertHDKM(int maHD, int maKM, float soTienConLai) {
        String query = "INSERT INTO HOA_DON_KHUYEN_MAI (MaHD, MaKM, SoTienConlai)\n" +
                        "VALUES(?,?,?)";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, maHD);
            pstmt.setInt(2,  maKM);
            pstmt.setFloat(3,  soTienConLai);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public boolean updateKhuyenMai(int maKM,String tenKhuyenMai, Date ngayBD, Date ngayKT, float mucGiam, String maGiam, int trangThai, int donVi) {
        String query = "Update KHUYEN_MAI\n" +
                    "set TenKhuyenMai = ?, NgayBD = ?, NgayKT= ?, MucGiam = ?,MaGiam = ?, TrangThai = ?, DonVi = ?\n" +
                    "where MaKM = ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tenKhuyenMai);
            pstmt.setDate(2,  ngayBD);
            pstmt.setDate(3,  ngayKT);
            pstmt.setFloat(4, mucGiam);
            pstmt.setString(5, maGiam);
            pstmt.setInt(6, trangThai);
            pstmt.setInt(7, donVi);
            pstmt.setInt(8, maKM);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
