/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NhaCungCap;
import PhieuNhap.*;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class NhaCungCapBUS {
    private  NhaCungCapDAO nhacungcapdao = new NhaCungCapDAO();
    private PhieuNhapDAO pndao = new PhieuNhapDAO();
    ArrayList<NhaCungCap> dsncc=new ArrayList<>();
    ArrayList<PhieuNhap> dspn=new ArrayList<>();
    public NhaCungCapBUS() {
        dsncc=nhacungcapdao.LoadNhaCungCap();
        dspn=pndao.LoadPhieuNhap();
    }
    public ArrayList<NhaCungCap> getNhaCungCap() {
        return dsncc;
    }
    public boolean themNhaCungCap(NhaCungCap ncc) {
       if(KiemTra(ncc)) {
           System.out.println("vui long kiem tra da dien du thong tin va nhap du lieu phu hop");
           return false;
       }
  

       if(KiemTraTenTrung(ncc.getTenNCC())) {
           System.out.println("ten nha cung cap bi trung");
           return false;
        }
       if(KiemTraMaTonTai(ncc.getMaNCC())) {
           System.out.println("ma nha cung cap da ton tai");
           return false;
       }
       boolean KetQua=nhacungcapdao.InsertNhaCungCap(ncc);
       if(KetQua) {
           dsncc.add(ncc);
       }
       return KetQua;
    }
    public boolean XoaNhaCungCap (NhaCungCap ncc) {
        if(!KiemTraMaTonTai(ncc.getMaNCC())) {
            System.out.println("ma khong ton tai");
        }
        if(nhacungcapdao.KiemTraNhaCungCapCoPhieuNhap(ncc.getMaNCC())) {
            System.out.println("khong the xoa nha cung cap vi co phieu nhap ton tai");
        }
        boolean KetQua=nhacungcapdao.deleteNhaCungCap(ncc);
         if (KetQua) dsncc = nhacungcapdao.LoadNhaCungCap();
        return KetQua;
    }
    public boolean SuaNhaCungCap (NhaCungCap ncc) {
        if(!KiemTraMaTonTai(ncc.getMaNCC())) {
            System.out.println("ma khong ton tai");
        }
        KiemTra(ncc);
        boolean KetQua=nhacungcapdao.UpdateNhaCungCap(ncc);
        if (KetQua) dsncc = nhacungcapdao.LoadNhaCungCap();
        return KetQua ;
        
    }
 //====================TIM KIEM====================
    public NhaCungCap TimKiemTheoMa(String mancc) {
        for(NhaCungCap ncc : dsncc) {
            if(ncc.getMaNCC().equalsIgnoreCase(mancc)) {
                return ncc;
            }
        }
        return null;
    }
    public NhaCungCap TimKiemTheoTen(String tenncc) {
       for(NhaCungCap ncc : dsncc) {
            if(ncc.getTenNCC().equalsIgnoreCase(tenncc)) {
                return ncc;
            }
        }
        return null;
    } 
    
        
    
//=======================VALIDATION==================    
    public boolean  KiemTra(NhaCungCap ncc) {
        //kiem tra rong
        if(ncc.getTenNCC()==null||ncc.getMaNCC()==null||ncc.getDiaChi()==null||ncc.getEmail()==null) {
            System.out.println("vui long dien thong tin day du");
            return false;
        }
        if(ncc.getTenNCC().isEmpty()||ncc.getMaNCC().isEmpty()||ncc.getSoDienThoai().isEmpty()||ncc.getDiaChi().isEmpty()||ncc.getEmail().isEmpty()) {
            System.out.println("vui long ko de trong");
            return false;
        }
        if(ncc.getSoDienThoai()==null||ncc.getSoDienThoai().length()!=10) {
            System.out.println("so dien thoai phai bang 10 so");
            return false;
        }
        
        return true;
    }
    public boolean KiemTraTenTrung(String tenncc) {
        for(NhaCungCap ncc : dsncc) {
            if(ncc.getTenNCC().equals(tenncc)) {
                System.out.println("ten da bi trung");
                return true;
            }
        }
        return false;
    }
    public boolean KiemTraMaTonTai(String mancc) {
        for(NhaCungCap ncc : dsncc) {
            if(ncc.getMaNCC().equals(mancc)) {
                return true;
            }
        }
        return false;
    }
    
    
}    
