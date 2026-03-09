/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CHiTietKhuyenMaiSP;

/**
 *
 * @author ASUS
 */
public class CHiTietKhuyenMaiSP {
    private String maKM;
    private String maSP;
    private float Phantramgg; 
    
    public CHiTietKhuyenMaiSP() {}
    
    public CHiTietKhuyenMaiSP(String maKM, String maSP, float Phantramgg) {
        this.maKM = maKM;
        this.maSP = maSP;
        this.Phantramgg = Phantramgg;
    }

    public String getMaKM() {
        return maKM;
    }

    public String getMaSP() {
        return maSP;
    }

    public float getPhantramgg() {
        return Phantramgg;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setPhantramgg(float Phantramgg) {
        this.Phantramgg = Phantramgg;
    }
    
    //dùng để làm việc với object
    @Override
    public String toString() {
        return "ChiTietKhuyenMaiSP{" +
            "maKM='" + maKM + '\'' +
            ", maSP='" + maSP + '\'' +
            ", phantramgg=" + Phantramgg +
            '}';
}
}
