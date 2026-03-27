/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NhaCungCap;
import PhieuNhap.*;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

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
    public boolean themNhaCungCap(NhaCungCap ncc,JDialog parentDialog)  {
        if(!KiemTra(ncc, parentDialog))
                return false;
        

       if(KiemTraTenTrung(ncc.getTenNCC())) {
           JOptionPane.showMessageDialog(parentDialog,"tên nhà cung cấp đã bị trùng!","Lỗi",JOptionPane.ERROR_MESSAGE);
           return false;
        }
       if(KiemTraMaTonTai(ncc.getMaNCC())) {
           JOptionPane.showMessageDialog(parentDialog,"mã nhà cung cấp đã bị trùng!","Lỗi",JOptionPane.ERROR_MESSAGE);    
       }
       
       boolean KetQua=nhacungcapdao.InsertNhaCungCap(ncc);
       if(KetQua) {
           dsncc.add(ncc);
       }
       return KetQua;
    }
    public boolean XoaNhaCungCap (String mancc) {
        if(nhacungcapdao.KiemTraNhaCungCapCoPhieuNhap(mancc)) {
            System.out.println("khong the xoa nha cung cap vi co phieu nhap ton tai");
        }
        boolean KetQua=nhacungcapdao.deleteNhaCungCap(mancc);
         if (KetQua) dsncc = nhacungcapdao.LoadNhaCungCap();
        return KetQua;
    }
    public boolean SuaNhaCungCap (NhaCungCap ncc,JDialog parentDialog) {
        if(!KiemTraMaTonTai(ncc.getMaNCC())) {
            JOptionPane.showMessageDialog(parentDialog,"mã nhà cung cấp không tồn tại!","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
        if(!KiemTra(ncc,parentDialog)) {
            return false;
        }
                
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
    public boolean  KiemTra(NhaCungCap ncc, JDialog parentDialog) {
        //kiem tra rong
        if(ncc.getTenNCC()==null||ncc.getMaNCC()==null||ncc.getDiaChi()==null||ncc.getEmail()==null) {
            JOptionPane.showMessageDialog(parentDialog,"vui lòng điền đầy đủ thông tin!","Lỗi",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(ncc.getTenNCC().isEmpty()||ncc.getMaNCC().isEmpty()||ncc.getSoDienThoai().isEmpty()||ncc.getDiaChi().isEmpty()||ncc.getEmail().isEmpty()) {
            JOptionPane.showMessageDialog(parentDialog,"vui lòng không để trống!","Lỗi",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(ncc.getSoDienThoai()==null||ncc.getSoDienThoai().length()<10) {
            JOptionPane.showMessageDialog(parentDialog,"số điện thoại phải có 10 chữ số!","Lỗi",JOptionPane.ERROR_MESSAGE);
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

    boolean xoaNhaCungCap(NhaCungCap ncc) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
}    
