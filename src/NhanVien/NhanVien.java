/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NhanVien;

import java.util.Objects;

/**
 *
 
 * @author DuyPhuong
 */
public class NhanVien {
    private String maNV , tenNV , hoNV , sdt , diaChi , email ;
    private double luong;

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NhanVien nv)) return false;
        return Objects.equals(maNV, nv.maNV);
    }

    public String getMaNV(){
        return maNV;
    }
    public void setMaNV(String maNV){
        this.maNV = maNV;
    }
    public String getTenNV(){
        return tenNV;
    }
    public void setTenNV(String maNV){
        this.maNV=maNV;
    }
    public String getHoNV(){
        return hoNV;
    }
    public void setHoNV(String hoNV){
        this.hoNV=hoNV;
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
    public double getLuong(){
        return luong;
    }
    public void setLuong(double luong){
        this.luong=luong;
    }
    public NhanVien(String maNV , String tenNV , String hoNV , String sdt, String diaChi , String email , double luong){
        this.maNV=maNV;
        this.tenNV=tenNV;
        this.hoNV=hoNV;
        this.sdt=sdt;
        this.diaChi=diaChi;
        this.email=email;
        this.luong=luong;
    }
}
