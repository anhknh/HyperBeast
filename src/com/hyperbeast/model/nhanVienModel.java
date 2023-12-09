/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.model;

import com.hyperbeast.entity.nhanVien;
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
public class nhanVienModel {
    
    public boolean InsertNV (String tenDangNhap, String matKhau, String hoTen, boolean gioiTinh, String sdt, String email, String diaChi, Date ngayTao, Date ngayCN, String chucVu, int trangThai) {
        String query = "insert into TAI_KHOAN(TenDangNhap, MatKhau, HoTen, GioiTinh, DienThoai, Email, DiaChi, NgayTao, NgayCapNhat, ChucVu, trangThai)\n" +
                        "values(?, ?, ?, ?, ?,?, ?, ?, ?, ?,?)";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tenDangNhap);
            pstmt.setString(2, matKhau);
            pstmt.setString(3, hoTen);
            pstmt.setBoolean(4, gioiTinh);
            pstmt.setString(5, sdt);
            pstmt.setString(6, email);
            pstmt.setString(7, diaChi);
            pstmt.setDate(8, ngayTao);
            pstmt.setDate(9, ngayCN);
            pstmt.setString(10, chucVu);
            pstmt.setInt(11, trangThai);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public boolean updateNV (int maTK, String tenDangNhap, String matKhau, String hoTen, boolean gioiTinh, String sdt, String email, String diaChi, Date ngayCN, String chucVu, int trangThai) {
        String query = "update TAI_KHOAN\n" +
                        "set TenDangNhap = ?, MatKhau = ?, HoTen = ?, GioiTinh = ?, DienThoai = ?, Email = ?, DiaChi = ?, NgayCapNhat= ?, ChucVu = ?, TrangThai = ?\n" +
                        "where MaTK = ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tenDangNhap);
            pstmt.setString(2, matKhau);
            pstmt.setString(3, hoTen);
            pstmt.setBoolean(4, gioiTinh);
            pstmt.setString(5, sdt);
            pstmt.setString(6, email);
            pstmt.setString(7, diaChi);
            pstmt.setDate(8, ngayCN);
            pstmt.setString(9, chucVu);
            pstmt.setInt(10, trangThai);
            pstmt.setInt(11, maTK);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public ArrayList<nhanVien> getNhanVien (String tenDangNhap) {
        String query = "select * from TAI_KHOAN where TenDangNhap = ?";
        ArrayList<nhanVien> listNV = new ArrayList<>();
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tenDangNhap);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {   
                nhanVien nv = new nhanVien();
                nv.setMaNV(rs.getInt("MaTK"));
                nv.setTenDangNhap(rs.getString("TenDangNhap"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setDienThoai(rs.getString("DienThoai"));
                nv.setEmail(rs.getString("Email"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setNgayTao(rs.getString("NgayTao"));
                nv.setNgayCN(rs.getString("NgayCapNhat"));
                nv.setChucVU(rs.getString("ChucVu"));
                if(rs.getInt("TrangThai") == 1){
                    nv.setTrangThai("Đang hoạt động");
                } else {
                    nv.setTrangThai("Không hoạt động");
                }
                listNV.add(nv);
            }
            return listNV;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList<nhanVien> getNhanVienSize () {
        String query = "select MaTK from TAI_KHOAN";
        ArrayList<nhanVien> listNV = new ArrayList<>();
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {   
                nhanVien nv = new nhanVien();
                nv.setMaNV(rs.getInt("MaTK"));
                listNV.add(nv);
            }
            return listNV;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList<nhanVien> getNhanVien2 (int pageSelect) {
        String query = "select * from TAI_KHOAN order by TAI_KHOAN.MaTK\n" +
                        "offset ? row\n" +
                        "fetch next 5 ROWS ONLY";
        ArrayList<nhanVien> listNV = new ArrayList<>();
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, pageSelect);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {   
                nhanVien nv = new nhanVien();
                nv.setMaNV(rs.getInt("MaTK"));
                nv.setTenDangNhap(rs.getString("TenDangNhap"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setDienThoai(rs.getString("DienThoai"));
                nv.setEmail(rs.getString("Email"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setNgayTao(rs.getString("NgayTao"));
                nv.setNgayCN(rs.getString("NgayCapNhat"));
                nv.setChucVU(rs.getString("ChucVu"));
                if(rs.getInt("TrangThai") == 1){
                    nv.setTrangThai("Đang hoạt động");
                } else {
                    nv.setTrangThai("Không hoạt động");
                }
                listNV.add(nv);
            }
            return listNV;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public void InsertNV(String tenDangNhap, String matKhau, String hoTen, boolean gioiTinh, String sdt, String email, String diaChi, java.util.Date ngayTao, java.util.Date ngayCN, String chucVu, int trangThai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
