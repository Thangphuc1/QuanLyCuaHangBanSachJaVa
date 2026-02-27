/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChiTietHoaDon;

/**
 *
 * @author ASUS
 */
public class ChiTietHoaDon {
    private String maHD;
    private String maSach;
    private int soLuong;
    private double donGia;
    private double thanhTien;
    public ChiTietHoaDon() {}
    public ChiTietHoaDon (String maHD,String maSach,int soLuong,double donGia,double thanhTien) {
        this.maHD=maHD;
        this.maSach=maSach;
        this.soLuong=soLuong;
        this.donGia=donGia;
        this.thanhTien=thanhTien;
    }

    public String getMaPN() {
        return maHD;
    }

    public String getMaSach() {
        return maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setMaPN(String maHD) {
        this.maHD = maHD;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    
}
