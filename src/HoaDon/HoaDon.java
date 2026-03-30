/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HoaDon;

import java.time.LocalDate;

public class HoaDon {
    public String mahoadon;
    public String manv;
    public String makh;
    public LocalDate thoigiantao;
    public double tongtien;

    public HoaDon() {}

    public HoaDon(String mahoadon, String manv, String makh, LocalDate thoigiantao, double tongtien) {
        this.mahoadon = mahoadon;
        this.manv = manv;
        this.makh = makh;
        this.thoigiantao = thoigiantao;
        this.tongtien = tongtien;
    }

    public String getMahoadon() {
        return mahoadon;
    }
    public void setMahoadon(String mahoadon) {
        this.mahoadon = mahoadon;
    }
    public String getManv() {
        return manv;
    }
    public void setManv(String manv) {
        this.manv = manv;
    }
    public String getMakh() {
        return makh;
    }
    public void setMakh(String makh) {
        this.makh = makh;
    }
    public LocalDate getThoigiantao() {
        return thoigiantao;
    }
    public void setThoigiantao(LocalDate thoigiantao) {
        this.thoigiantao = thoigiantao;
    }
    public double getTongtien() {
        return tongtien;
    }
    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }
}
