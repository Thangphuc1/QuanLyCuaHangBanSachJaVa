package SanPham;

import java.util.*;

public class SanPham {
    private String masp,tensp,matg,matl,manxb;
    private int dongia,soluongton,namxuatban;
    
    public SanPham(){
        masp = tensp = matg = matl = manxb = null;
        dongia = soluongton = namxuatban = 0;
    }
    
    public SanPham(String masp, String tensp, String matg, String matl, int namxuatban, String manxb, int dongia, int soluongton){
        this.masp = masp;
        this.tensp = tensp;
        this.matg = matg;
        this.matl = matl;
        this.namxuatban = namxuatban;
        this.manxb = manxb;
        this.dongia = dongia;
        this.soluongton = soluongton;
    }
    
    public SanPham(SanPham sp){
        masp = sp.masp;
        tensp = sp.tensp;
        matg = sp.matg;
        matl = sp.matl;
        namxuatban = sp.namxuatban;
        manxb = sp.manxb;
        dongia = sp.dongia;
        soluongton = sp.soluongton;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getMatg() {
        return matg;
    }

    public void setMatg(String matg) {
        this.matg = matg;
    }

    public String getMatl() {
        return matl;
    }

    public void setMatl(String matl) {
        this.matl = matl;
    }

    public String getManxb() {
        return manxb;
    }

    public void setManxb(String manxb) {
        this.manxb = manxb;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    public int getNamxuatban() {
        return namxuatban;
    }

    public void setNamxuatban(int namxuatban) {
        this.namxuatban = namxuatban;
    }
    
    
}
