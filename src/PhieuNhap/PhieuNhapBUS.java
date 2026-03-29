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
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class PhieuNhapBUS {
    private PhieuNhapDAO pndao=new PhieuNhapDAO();
    private NhaCungCapDAO nccdao=new NhaCungCapDAO();
    private NhanVienDAO nvdao = new NhanVienDAO();
    private ChiTietPhieuNhapDAO ctdao=new ChiTietPhieuNhapDAO();
    private ChiTietPhieuNhapBUS ctbus=new ChiTietPhieuNhapBUS();
    ArrayList<PhieuNhap> dspn=new ArrayList<>();
    ArrayList<NhaCungCap> dsncc=new ArrayList<>();
    ArrayList<NhanVien> dsnv=new ArrayList<>();
    ArrayList<ChiTietPhieuNhap> dsct=new ArrayList<>();
    
   public PhieuNhapBUS() {
//       dspn=pndao.LoadPhieuNhap();
       dsncc=nccdao.LoadNhaCungCap();
       dsnv=nvdao.loadNhanVien();
       dsct=ctdao.LoadChiTietPhieuNhap();
   } 
   public ArrayList<PhieuNhap> getPhieuNhap() {
    dspn = pndao.LoadPhieuNhap();  // ⭐ Reload từ DB mỗi lần
    return dspn;
}
   public PhieuNhap GetByMaPhieu(String mapn) {
       return pndao.GetPhieuNhapByMa(mapn);
   }
   public boolean ThemPhieuNhap(PhieuNhap pn,JDialog parentDialog) {
       if(!KiemTra(pn)) {
             
           return false;
       }
       if(KiemTraMaPNTonTai(pn.getMaPN())) {
           JOptionPane.showMessageDialog(parentDialog,"mã phiếu nhập đã tồn tại!","Lỗi",JOptionPane.ERROR_MESSAGE);
           return false;
       }
       if(!KiemTraManvTonTai(pn.getMaNV())) {
           JOptionPane.showMessageDialog(parentDialog,"mã nhân viên ko tồn tại!","Lỗi",JOptionPane.ERROR_MESSAGE);
           return false;
       }
       if(!KiemTraMaNCCTonTai(pn.getMaNCC())) {
           JOptionPane.showMessageDialog(parentDialog,"mã nhà cung cấp ko tồn tại!","Lỗi",JOptionPane.ERROR_MESSAGE);
           return false;
       }
//       if(!KiemTraChiTietPN(pn.getMaPN())) {
//           JOptionPane.showMessageDialog(parentDialog,"phiếu nhập phải có ít nhất 1 chi tiết!","Lỗi",JOptionPane.ERROR_MESSAGE);
//           return false;
//       }
       
       boolean KetQua=pndao.InSertPhieuNhap(pn);
       if(KetQua) {
         dspn.add(pn);  
       }
       return KetQua;
   }
   public boolean SuaPhieuNhap(PhieuNhap pn,JDialog parentDialog) {
       if(!KiemTra(pn)) {
              
           return false;
       }
       if(!KiemTraManvTonTai(pn.getMaNV())) {
           JOptionPane.showMessageDialog(parentDialog,"mã nhân viên ko tồn tại!","Lỗi",JOptionPane.ERROR_MESSAGE);
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
//   public boolean XoaPhieuNhap(PhieuNhap pn) {
//       boolean KetQua=pndao.DeletePhieuNhap(pn);
//       if(KetQua) {
//           dspn=pndao.LoadPhieuNhap();
//       }
//       return KetQua;
//   }
   public boolean XoaPhieuNhap(PhieuNhap pn) {
    // 1) Xóa chi tiết trước
    ctbus.XoaChiTietTheoMaPN(pn.getMaPN());

    // 2) Xóa phiếu
    boolean ketQua = pndao.DeletePhieuNhap(pn);

    if (ketQua) {
        dspn = pndao.LoadPhieuNhap();
    }
    return ketQua;
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
    public boolean UpdateTongTienPN(String maPN) {
        return pndao.UpdateTongTienPN(maPN);
    }
        
    
    
}

