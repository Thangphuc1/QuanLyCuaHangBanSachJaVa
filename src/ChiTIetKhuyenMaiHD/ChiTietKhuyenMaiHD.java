/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChiTIetKhuyenMaiHD;

/**
 *
 * @author ASUS
 */
public class ChiTietKhuyenMaiHD {
    private String maKM;
    private String TTHD;
    private float Phantramgg; 
    
    public ChiTietKhuyenMaiHD() {}
    
    public ChiTietKhuyenMaiHD(String maKM, String TTHD, float Phantramgg) {
        this.maKM = maKM;
        this.TTHD = TTHD;
        this.Phantramgg = Phantramgg;
    }
    
    public String getMaKM() {
        return maKM;
    }

    public String getTTHD() {
        return TTHD;
    }

    public float getPhantramgg() {
        return Phantramgg;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public void setTTHD(String TTHD) {
        this.TTHD = TTHD;
    }

    public void setPhantramgg(float Phantramgg) {
        this.Phantramgg = Phantramgg;
    }
}