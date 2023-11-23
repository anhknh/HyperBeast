/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.model;

import com.hyperbeast.entity.HoaDon;
import com.hyperbeast.entity.SanPhamChiTiet;
import com.hyperbeast.utils.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class hoaDonModel {
    public ArrayList<SanPhamChiTiet> getSanPhamCT (int pageSelect) {
        ArrayList<SanPhamChiTiet> listSPCT = new ArrayList<>();
        String query = "select SAN_PHAM.MaSP,SAN_PHAM.TenSP,CHI_TIET_SAN_PHAM.MaCTSP, SoLuong, DonGia, TenMau, KichThuoc, TenChatLieu, TenChatLieuDe,MaBarCode, TenAnh, MoTa  from CHI_TIET_SAN_PHAM join MAU_SAC on MAU_SAC.MaMS = CHI_TIET_SAN_PHAM.MaMS\n" +
"				join SAN_PHAM ON SAN_PHAM.MaSP = CHI_TIET_SAN_PHAM.MaSP\n" +
"                               join SIZE on SIZE.MaSize = CHI_TIET_SAN_PHAM.MaSize\n" +
"				join CHAT_LIEU on CHAT_LIEU.MaCL = CHI_TIET_SAN_PHAM.MaCL\n" +
"				join CHAT_LIEU_DE_GIAY on CHAT_LIEU_DE_GIAY.MaCLDe = CHI_TIET_SAN_PHAM.MaCLDe\n" +
"                               where SAN_PHAM.TrangThai like N'Đang Kinh Doanh'\n" +
"                               order by SAN_PHAM.MaSP\n" +
"                               offset ? row\n" +
"                               fetch next 5 ROWS ONLY";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, pageSelect);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {                
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setMaSP(rs.getInt("MaSP"));
                spct.setMaCTSP(rs.getInt("MaCTSP"));
                spct.setTenSP(rs.getString("TenSP"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setDonGia(rs.getFloat("DonGia"));
                spct.setTenMau(rs.getString("TenMau"));
                spct.setKichThuoc(rs.getInt("KichThuoc"));
                spct.setTenChatLieu(rs.getString("TenChatLieu"));
                spct.setTenChatLieuDe(rs.getString("TenChatLieuDe"));
                spct.setMaBarCode(rs.getString("MaBarCode"));
                spct.setTenAnh(rs.getString("TenAnh"));
                spct.setMoTa(rs.getString("MoTa"));
                listSPCT.add(spct);
            }
            return listSPCT;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList getSizeCTSP () {
        ArrayList listMCTSP = new ArrayList<>();
        String query = "select * from CHI_TIET_SAN_PHAM\n" +
"			join SAN_PHAM ON SAN_PHAM.MaSP = CHI_TIET_SAN_PHAM.MaSP\n" +
                        "where SAN_PHAM.TrangThai like N'Đang Kinh Doanh'";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {                
                int maCTSP = rs.getInt("MaCTSP");
                listMCTSP.add(maCTSP);
            }
            return listMCTSP;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList getHoaDon () {
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        String query = "select MaHD, HOA_DON.NgayTao,HOA_DON.TrangThai, HoTen from HOA_DON join TAI_KHOAN on TAI_KHOAN.MaTK = HOA_DON. MaTK";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {                
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getInt("MaHD"));
                hoaDon.setNgayTao(rs.getString("NgayTao"));
                hoaDon.setTenNhanVien(rs.getString("HoTen"));
                hoaDon.setTrangThai(rs.getString("TrangThai"));
                listHoaDon.add(hoaDon);
            }
            return listHoaDon;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public boolean insertHoaDonCho(String ngayTao, String ngayCapNhat, String trangThai, int maTaiKhoan) {
        String query = "INSERT INTO HOA_DON(NgayTao, NgayCapNhat, TrangThai, MaTK)" +
                        " VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, ngayTao);
            pstmt.setString(2, ngayCapNhat);
            pstmt.setString(3, trangThai);
            pstmt.setInt(4, maTaiKhoan);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
