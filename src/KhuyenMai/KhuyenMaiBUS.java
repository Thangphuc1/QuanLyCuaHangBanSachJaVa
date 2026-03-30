package KhuyenMai;

import java.util.ArrayList;
import HoaDon.HoaDon;
import ChiTietHoaDon.ChiTietHoaDon;
import CHiTietKhuyenMaiSP.CHiTietKhuyenMaiSP;
import CHiTietKhuyenMaiSP.CHiTietKhuyenMaiSPBUS;
import ChiTIetKhuyenMaiHD.ChiTietKhuyenMaiHD;
import ChiTIetKhuyenMaiHD.ChiTietKhuyenMaiHDBUS;
import java.time.LocalDate;

public class KhuyenMaiBUS {

    private KhuyenMaiDAO kmDAO;

    public KhuyenMaiBUS() {
        kmDAO = new KhuyenMaiDAO();
    }

    // Lấy danh sách
    public ArrayList<KhuyenMai> getAll() {
        return kmDAO.getAll();
    }

    // Thêm
    public boolean add(KhuyenMai km) {
        return kmDAO.insert(km);
    }

    // Sửa
    public boolean update(KhuyenMai km) {
        return kmDAO.update(km);
    }

    // Xóa
    public boolean delete(String maKM) {
        if (maKM == null || maKM.trim().equals("")) {
            return false;
        }
        return kmDAO.delete(maKM);
    }

    // Tìm kiếm
    public ArrayList<KhuyenMai> search(String keyword) {
        if (keyword == null || keyword.trim().equals("")) {
            return kmDAO.getAll();
        }
        return kmDAO.search(keyword);
    }

    // Thống kê
    public KhuyenMaiDAO.ThongKeKM thongKe(){
        return kmDAO.thongKeTongHop();
    }
    
    public double tinhGiamGia(HoaDon hd, ArrayList<ChiTietHoaDon> dsCTHD) {
        double giamSP = tinhGiamSanPham(dsCTHD);
        double giamHD = tinhGiamHoaDon(hd);

        return Math.max(giamSP, giamHD);
}
    
    public double tinhGiamSanPham(ArrayList<ChiTietHoaDon> dsCTHD){
        double tongGiam = 0;
        CHiTietKhuyenMaiSPBUS sp = new CHiTietKhuyenMaiSPBUS();
        ArrayList<CHiTietKhuyenMaiSP> ctkm = sp.getAll();
        for (ChiTietHoaDon cthd : dsCTHD)
        {    double maxGiamSP = 0;
            for (CHiTietKhuyenMaiSP km : ctkm)
            {   KhuyenMai kmTong = getKhuyenMaiById(km.getMaKM());
                if (kmTong == null || !hieuluc(kmTong)) continue;
                if(cthd.getMasach().equals(km.getMaSP()))
                    {
                    double giam = cthd.getSoluong()
                              * cthd.getDongia()
                              * km.getPhantramgg() / 100.0;

                    if (giam > maxGiamSP)
                        maxGiamSP = giam;
                }
                
            }
            tongGiam += maxGiamSP;
        }
        return tongGiam;
   
    }
    
    public double tinhGiamHoaDon(HoaDon hd){
        double tinhTong = 0;
        ChiTietKhuyenMaiHDBUS hdbus = new ChiTietKhuyenMaiHDBUS();
        ArrayList<ChiTietKhuyenMaiHD> ctkm = hdbus.getAll();
            for(ChiTietKhuyenMaiHD km : ctkm)
            {   KhuyenMai kmTong = getKhuyenMaiById(km.getMaKM());
                if (kmTong == null || !hieuluc(kmTong)) continue;
                if(hd.getTongtien()>= km.getTTHD())
                    {
                        double giam = hd.getTongtien()*km.getPhantramgg()/100;
                        if(tinhTong < giam) 
                            tinhTong = giam;
                    }        
            }
        return tinhTong;
    }
    
    private boolean hieuluc(KhuyenMai km){
        LocalDate now = LocalDate.now();

        return (now.isEqual(km.getNgayBD()) || now.isAfter(km.getNgayBD()))
            && (now.isEqual(km.getNgayKT()) || now.isBefore(km.getNgayKT()));
    }
    
    public KhuyenMai getKhuyenMaiById(String maKM) {
        if (maKM == null) return null;

        for (KhuyenMai km : getAll()) {
            if (maKM.equals(km.getMaKM()))
                return km;
        }
        return null;
    }
}

