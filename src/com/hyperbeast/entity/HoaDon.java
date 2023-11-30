/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.entity;

/**
 *
 * @author Admin
 */
public class HoaDon {
    private int maHoaDon;
    private String ngayTao;
    private String ngayCapNhat;
    private String trangThai;
    private float tongTien;
    private String tenKhachHang;
    private String hinhThucThanhToan;
    private String tenNhanVien;
    private String tenKhuyenMai;
    private String donViKhuyenMai;
    private float mucKhuyenMai;
    private float soTienSauKM;
    private String ghiChu;

    public HoaDon(int maHoaDon, String ngayTao, String ngayCapNhat, String trangThai, float tongTien, String tenKhachHang, String hinhThucThanhToan, String tenNhanVien, String tenKhuyenMai, String donViKhuyenMai, float mucKhuyenMai, float soTienSauKM, String ghiChu) {
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.tenKhachHang = tenKhachHang;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.tenNhanVien = tenNhanVien;
        this.tenKhuyenMai = tenKhuyenMai;
        this.donViKhuyenMai = donViKhuyenMai;
        this.mucKhuyenMai = mucKhuyenMai;
        this.soTienSauKM = soTienSauKM;
        this.ghiChu = ghiChu;
    }

    public HoaDon() {
    }

    public float getSoTienSauKM() {
        return soTienSauKM;
    }

    public void setSoTienSauKM(float soTienSauKM) {
        this.soTienSauKM = soTienSauKM;
    }

    

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(String ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public String getDonViKhuyenMai() {
        return donViKhuyenMai;
    }

    public void setDonViKhuyenMai(String donViKhuyenMai) {
        this.donViKhuyenMai = donViKhuyenMai;
    }

    public float getMucKhuyenMai() {
        return mucKhuyenMai;
    }

    public void setMucKhuyenMai(float mucKhuyenMai) {
        this.mucKhuyenMai = mucKhuyenMai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    

   
    
}
