/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChiTietPhieuNhap;

/**
 *
 * @author ASUS
 */
public class ChiTietPhieuNhap {
    private String maPN;
    private String maSach;
    private int soLuong;
    private Double donGia;
    
    private Double thanhTien;
    public ChiTietPhieuNhap() {}
    public ChiTietPhieuNhap (String maPN,String maSach,int soLuong,double donGia,double thanhTien) {
        this.maPN=maPN;
        this.maSach=maSach;
        this.soLuong=soLuong;
        this.donGia=donGia;
        this.thanhTien=thanhTien;
    }

    public String getMaPN() {
        return maPN;
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

    public void setMaPN(String maPN) {
        this.maPN = maPN;
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
