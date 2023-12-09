/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.entity;

/**
 *
 * @author Admin
 */
public class nhanVien {
    private int maNV;
    private String tenDangNhap;
    private String matKhau;
    private String hoTen;
    private boolean gioiTinh;
    private String email;
    private String diaChi;
    private String dienThoai;
    private String ngayTao;
    private String ngayCN;
    private String chucVU;
    private String trangThai;

    public nhanVien(int maNV, String tenDangNhap, String matKhau, String hoTen, boolean gioiTinh, String email, String diaChi, String dienThoai, String ngayTao, String ngayCN, String chucVU, String trangThai) {
        this.maNV = maNV;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.ngayTao = ngayTao;
        this.ngayCN = ngayCN;
        this.chucVU = chucVU;
        this.trangThai = trangThai;
    }

    public nhanVien() {
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgayCN() {
        return ngayCN;
    }

    public void setNgayCN(String ngayCN) {
        this.ngayCN = ngayCN;
    }

    public String getChucVU() {
        return chucVU;
    }

    public void setChucVU(String chucVU) {
        this.chucVU = chucVU;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    
}
