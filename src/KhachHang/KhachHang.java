/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KhachHang;

import java.util.Objects;
/**
 *
 * @author DuyPhuong
 */
public class KhachHang {
    private String maKH , tenKH , hoKH , sdt , diaChi , email ;

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KhachHang kh)) return false;
        return Objects.equals(maKH, kh.maKH);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maKH);
    }
    public String getMaKH(){
        return maKH;
    }
    public void setMaNV(String maKH){
        this.maKH = maKH;
    }
    public String getTenKH(){
        return tenKH;
    }
    public void setTenKH(String maKH){
        this.maKH=maKH;
    }
    public String getHoKH(){
        return hoKH;
    }
    public void setHoKH(String hoKH){
        this.hoKH=hoKH;
    }
    public String getSDT(){
        return sdt;
    }
    public void setSDT(String sdt){
        this.sdt=sdt;
    }
    public String getDiaChi(){
        return diaChi;
    }
    public void setDiaChi(String diaChi){
        this.diaChi=diaChi;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    
    public KhachHang(String maKH , String tenKH , String hoKH , String sdt, String diaChi , String email , double luong){
        this.maKH=maKH;
        this.tenKH=tenKH;
        this.hoKH=hoKH;
        this.sdt=sdt;
        this.diaChi=diaChi;
        this.email=email;
    }
}

