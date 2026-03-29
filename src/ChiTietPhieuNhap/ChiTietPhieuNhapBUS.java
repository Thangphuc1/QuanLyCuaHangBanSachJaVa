    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChiTietPhieuNhap;
import PhieuNhap.*;
import Sach.*;

/**
 *
 * @author ASUS
 */
import Sach.SachDAO;
import java.util.ArrayList;
public class ChiTietPhieuNhapBUS {
    private ChiTietPhieuNhapDAO ctdao=new ChiTietPhieuNhapDAO();
    private PhieuNhapDAO pndao=new PhieuNhapDAO();
    private SachDAO sdao=new SachDAO();
    ArrayList<ChiTietPhieuNhap> dsctpn=new ArrayList<>();
    ArrayList<PhieuNhap> dspn=new ArrayList<>();
    ArrayList<Sach> dss=new ArrayList<>();
    
    public ArrayList<ChiTietPhieuNhap> GetChiTietPhieuNhap(String mapn) {
        return ctdao.GetByPhieuNhap(mapn);
    }
    public boolean ThemChiTietPhieuNhap(ChiTietPhieuNhap ctpn) {
        if(!KiemTra(ctpn)) {
            System.out.println("vui long dien day du thong tin, khong bo trong bat ky o nao !");
            return false;
        }
//        if(!KiemTraMaPNTonTai(ctpn.getMaPN())) {
//            System.out.println("ma phieu nhap ko ton tai");
//            return false;
//        }
//        if(!KiemTraMaSachTonTai(ctpn.getMaSach())) {
//            System.out.println("ma sach ko ton tai");
//            return false;
//        }
        boolean KetQua=ctdao.InsertChiTietPhieuNhap(ctpn);
        if(KetQua) {
            dsctpn=ctdao.LoadChiTietPhieuNhap();
        }
        return KetQua;
    }
    
    public boolean SuaChiTietPhieuNhap(ChiTietPhieuNhap ctpn) {
        if(!KiemTra(ctpn)) {
            System.out.println("vui long dien day du thong tin, khong bo trong bat ky o nao !");
            return false;
        }
        boolean KetQua=ctdao.UpdateChiTietPhieuNhap(ctpn);
        if(KetQua) {
            dsctpn=ctdao.LoadChiTietPhieuNhap();
        }
        return KetQua;
    }
    public boolean XoaChiTietPhieuNhap(ChiTietPhieuNhap ctpn) {
        boolean KetQua=ctdao.DeleteChiTietPhieuNhap(ctpn);
        if(KetQua) {
            dsctpn=ctdao.LoadChiTietPhieuNhap();
        }
        return KetQua;
    }
    public boolean XoaChiTietTheoMaPN(String maPN) {
        boolean ketQua = ctdao.DeleteChiTietTheoMaPN(maPN);
        if (ketQua) {
            dsctpn = ctdao.LoadChiTietPhieuNhap();
        }
    return ketQua;
    }
    
    
//=================VALIDATE==============
    public boolean KiemTra(ChiTietPhieuNhap ctpn) {
        if(ctpn.getMaPN()==null || ctpn.getMaSach()==null) {
            return false;
        }
        if(ctpn.getMaPN().isEmpty() || ctpn.getMaSach().isEmpty()) {
            return false;
        }
        if (ctpn.getSoLuong()<=0) {
            return false;
        }
        if(ctpn.getDonGia()<=0.0 ) {
            return false;
        }
        if(ctpn.getThanhTien()<=0.0) {
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
    public boolean KiemTraMaSachTonTai(String mas) {
        for(Sach s : dss) {
            if(s.getMasach().equalsIgnoreCase(mas)) {
                return true;
            }
        }
        return false;
    }
    // trong 1 phieu nhap ko the nao co 2 dong chi tiet phieu nhap giong nhau, nhapp cung 1 loai sach(sai logic)
}
