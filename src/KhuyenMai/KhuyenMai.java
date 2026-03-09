/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KhuyenMai;

/**
 *
 * @author ASUS
 */

import java.time.LocalDate;

public class KhuyenMai {
    private String maKM;
    private String tenKM;
    private LocalDate ngayBD;
    private LocalDate ngayKT;
    private int DKTT; 
    private String ghichu;
    
    public KhuyenMai(){}
    
    public KhuyenMai(String maKM, String tenKM, LocalDate ngayBD, LocalDate ngayKT,int DKTT, String ghichu){
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.DKTT = DKTT;
        this.ghichu = ghichu;
    }

    public String getMaKM() {
        return maKM;
    }

    public String getTenKM() {
        return tenKM;
    }

    public LocalDate getNgayBD() {
        return ngayBD;
    }

    public LocalDate getNgayKT() {
        return ngayKT;
    }

    public int getDKTT() {
        return DKTT;
    }
    
    public String getGhichu(){
        return ghichu;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public void setNgayBD(LocalDate ngayBD) {
        this.ngayBD = ngayBD;
    }

    public void setNgayKT(LocalDate ngayKT) {
        this.ngayKT = ngayKT;
    }

    public void setDKTT(int DKTT) {
        this.DKTT = DKTT;
    }
    
    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
