/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NhaCungCap;
    

public class NhaCungCap {
    private String maNCC;
    private String tenNCC;
    private String soDienThoai;
    private String email;
    private String diaChi; 
    public NhaCungCap() {}
    public NhaCungCap(String maNCC,String tenNCC,String soDienThoai,String email,String diaChi) {
        this.maNCC=maNCC;
        this.tenNCC=tenNCC;
        this.soDienThoai=soDienThoai;
        this.email=email;
        this.diaChi=diaChi;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
}
