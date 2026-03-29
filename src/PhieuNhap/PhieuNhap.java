/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PhieuNhap;

import java.time.LocalDate;


public class PhieuNhap {
    private String maPN;
    private String maNCC;
    private String maNV;
    private LocalDate ngayLap;
    private Double tongTien;
    public PhieuNhap() {}
    public PhieuNhap(String maPN,String maNV,String maNCC,LocalDate ngayLap,Double tongTien) {
        this.maPN=maPN;
        this.maNCC=maNCC;
        this.maNV=maNV;
        this.ngayLap=ngayLap;
        this.tongTien=tongTien;
    }

    public String getMaPN() {
        return maPN;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public String getMaNV() {
        return maNV;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }
    
}
