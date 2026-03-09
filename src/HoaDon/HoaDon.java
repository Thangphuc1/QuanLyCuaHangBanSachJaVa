/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HoaDon;

import java.time.LocalDate;

public class HoaDon {
    public  String maHD;
    public String maKH;
    public String maNV;
    public LocalDate ngayLap;
    public Double tongTien;
    public HoaDon() {}
    public HoaDon(String maHD,String maKH,String maNV,LocalDate ngayLap,Double tongTien) {
        this.maHD=maHD;
        this.maKH=maKH;
        this.maNV=maNV;
        this.ngayLap=ngayLap;
        this.tongTien=tongTien;
    }

    public String getMaHD() {
        return maHD;
    }

    public String getMaKH() {
        return maKH;
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

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
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
