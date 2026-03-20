/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PhieuNhap;

import NhaCungCap.*;
import NhanVien.*;
import ChiTietPhieuNhap.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class PhieuNhapBUS {
    private PhieuNhapDAO pndao=new PhieuNhapDAO();
    private NhaCungCapDAO nccdao=new NhaCungCapDAO();
    private NhanVienDAO nvdao = new NhanVienDAO();
    private ChiTietPhieuNhapDAO ctdao=new ChiTietPhieuNhapDAO();
    ArrayList<PhieuNhap> dspn=new ArrayList<>();
    ArrayList<NhaCungCap> dsncc=new ArrayList<>();
    ArrayList<NhanVien> dsnv=new ArrayList<>();
    ArrayList<ChiTietPhieuNhap> dsct=new ArrayList<>();
    
   public PhieuNhapBUS() {
       dspn=pndao.LoadPhieuNhap();
       dsncc=nccdao.LoadNhaCungCap();
       dsnv=nvdao.loadNhanVien();
       dsct=ctdao.LoadChiTietPhieuNhap();
   } 
   public ArrayList<PhieuNhap> getPhieuNhap () {
       return dspn;
   }
   public boolean ThemPhieuNhap(PhieuNhap pn) {
       if(!KiemTra(pn)) {
           System.out.println("vui long kiem tra da dien du thong tin va nhap du lieu phu hop");   
           return false;
       }
       if(!KiemTraMaPNTonTai(pn.getMaPN())) {
           System.out.println("ma phieu nhap ko ton tai");
           return false;
       }
       if(!KiemTraManvTonTai(pn.getMaNV())) {
           System.out.println("ma nhan vien ko ton tai");
           return false;
       }
       if(!KiemTraMaNCCTonTai(pn.getMaNCC())) {
           System.out.println("ma nha cung cap ko ton tai");
           return false;
       }
       if(!KiemTraChiTietPN(pn.getMaNCC())) {
           System.out.println("phieu nhap phai co it nhat 1 phieu nhap");
           return false;
       }
       
       boolean KetQua=pndao.InSertPhieuNhap(pn);
       if(KetQua) {
         dspn.add(pn);  
       }
       return KetQua;
   }
   public boolean SuaPhieuNhap(PhieuNhap pn) {
       if(!KiemTra(pn)) {
           System.out.println("vui long kiem tra da dien du thong tin va nhap du lieu phu hop");   
           return false;
       }
       if(!KiemTraManvTonTai(pn.getMaNV())) {
           System.out.println("ma nhan vien ko ton tai");
           return false;
       }
       if(!KiemTraMaNCCTonTai(pn.getMaNCC())) {
           System.out.println("ma nha cung cap ko ton tai");
           return false;
       }
       boolean KetQua=pndao.UpdatePhieuNhap(pn);
       if(KetQua) {
           dspn=pndao.LoadPhieuNhap();
       }
       return KetQua;
       
   }
   public boolean XoaPhieuNhap(PhieuNhap pn) {
       if(!KiemTraMaPNTonTai(pn.getMaPN())) {
           System.out.println("ma phieu nhap ko ton tai");
           return false;
       }
       boolean KetQua=pndao.DeletePhieuNhap(pn);
       if(KetQua) {
           dspn=pndao.LoadPhieuNhap();
       }
       return KetQua;
   }
//=======================TIMKIEM====================
   public PhieuNhap TimPhieuNhapTheoMa(String mapn) {
       for(PhieuNhap pn :dspn) {
           if(pn.getMaPN().equalsIgnoreCase(mapn)) {
               return pn;
           }
       }
       return null;
   }
   public PhieuNhap TimPhieuNhapTheoNCC(String mancc) {
       for(PhieuNhap pn: dspn) {
           if(pn.getMaNCC().equalsIgnoreCase(mancc)) {
               return pn;
            }
       
        }
        return null;
   }
   public PhieuNhap TimPhieuNhapTheoNV(String manv) {
       for(PhieuNhap pn: dspn) {
           if(pn.getMaNV().equalsIgnoreCase(manv)) {
               return pn;
            }
       
        }
        return null;
   }
    
//===============VALIDATE===========================
    public boolean KiemTra (PhieuNhap pn) {
        if(pn.getMaPN()==null||pn.getMaNCC()==null||pn.getMaNV()==null||pn.getNgayLap()==null) {
            return false;
        }
        if(pn.getMaPN().isEmpty()||pn.getMaNCC().isEmpty()||pn.getMaNV().isEmpty()||pn.getNgayLap().isAfter(LocalDate.now())) {
            return false;
        }
        if(pn.getTongTien()==null||pn.getTongTien()<0) {
            return false;
        }
        return true;
    }
    public boolean KiemTraMaPNTonTai(String mapn) {
        for(PhieuNhap pn : dspn) {
            if(pn.getMaPN().equals(mapn)) {
                return true;
            }
        }
        return false;
    }
    public boolean KiemTraMaNCCTonTai (String mancc) {
        for(NhaCungCap ncc : dsncc) {
            if(ncc.getMaNCC().equals(mancc)) {
                return true;
            }
        }
        return false;
    }
    public boolean KiemTraManvTonTai (String manv) {
        for(NhanVien nv : dsnv) {
            if(nv.getMaNV().equals(manv)) {
                return true;
            }
        }
        return false;
    }
    //phieunhap phai co ton tai it nhat 1 chi tiet phieu nhap
    public boolean KiemTraChiTietPN (String mapn) {
        for(ChiTietPhieuNhap ct :dsct) {
            if(ct.getMaPN().equals(mapn)) {
                return true;
            }
        }
        return false;
    }
    
        
    
    
}
