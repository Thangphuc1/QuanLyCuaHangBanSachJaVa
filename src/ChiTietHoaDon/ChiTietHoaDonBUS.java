package ChiTietHoaDon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class ChiTietHoaDonBUS {

    ArrayList<ChiTietHoaDon> dscthd;

    public ChiTietHoaDonBUS() {
    }

    // ===============================
    // ĐỌC DANH SÁCH CHI TIẾT HÓA ĐƠN
    // ===============================

    public void docDSCTHD(String mahd) {

        try {

            ChiTietHoaDonDAO data = new ChiTietHoaDonDAO();

            dscthd = data.Docdscthd(mahd);

        } catch (SQLException e) {

            System.out.println("Lỗi đọc danh sách chi tiết hóa đơn");

        }

    }

    // ===============================
    // LẤY DANH SÁCH
    // ===============================

    public ArrayList<ChiTietHoaDon> getList(String mahd) {
        docDSCTHD(mahd);

        return dscthd;

    }

    // ===============================
    // THÊM CHI TIẾT HÓA ĐƠN
    // ===============================

    public boolean them(ChiTietHoaDon cthd) {

        try {

            ChiTietHoaDonDAO data = new ChiTietHoaDonDAO();

            if (data.ThemCTHD(cthd)) {

                if (dscthd == null)
                    dscthd = new ArrayList<>();

                dscthd.add(cthd);

                return true;
            }

        } catch (SQLException e) {

            System.out.println("Lỗi BUS thêm CTHD");

        }

        return false;
    }

    // ===============================
    // SỬA CHI TIẾT HÓA ĐƠN
    // ===============================

    public boolean sua(ChiTietHoaDon cthd) {

        try {

            ChiTietHoaDonDAO data = new ChiTietHoaDonDAO();

            if (data.SuaCTHD(cthd)) {

                for (int i = 0; i < dscthd.size(); i++) {

                    if (dscthd.get(i).getMahoadon().equals(cthd.getMahoadon())
                            && dscthd.get(i).getMasach().equals(cthd.getMasach())) {

                        dscthd.set(i, cthd);

                        break;
                    }

                }

                return true;
            }

        } catch (SQLException e) {

            System.out.println("Lỗi BUS sửa CTHD");

        }

        return false;
    }

    // ===============================
    // XÓA CHI TIẾT HÓA ĐƠN
    // ===============================

    public boolean xoa(String mahd, String masach) {

        try {

            ChiTietHoaDonDAO data = new ChiTietHoaDonDAO();

            if (data.XoaCTHD(mahd, masach)) {

                for (int i = 0; i < dscthd.size(); i++) {

                    if (dscthd.get(i).getMahoadon().equals(mahd)
                            && dscthd.get(i).getMasach().equals(masach)) {

                        dscthd.remove(i);

                        break;
                    }

                }

                return true;
            }

        } catch (SQLException e) {

            System.out.println("Lỗi BUS xóa CTHD");

        }

        return false;
    }
    
    // xoa theo ma 
    
   public boolean xoaTheoMa(String maHD){

     
       ChiTietHoaDonDAO data = null;


     try {
    
         data = new ChiTietHoaDonDAO();

     } catch (SQLException e) {
    
         e.printStackTrace();

     }

     // Load dscthd trước để tránh null
     docDSCTHD(maHD);

    if(data.xoaCTHDTheoMa(maHD)){

        if(dscthd != null){

            Iterator<ChiTietHoaDon> it = dscthd.iterator();

            while(it.hasNext()){

                ChiTietHoaDon ct = it.next();

                if(ct.getMahoadon().equals(maHD)){

                    it.remove();

                }

            }

        }

        return true;

    }

    return false;

}

    // ===============================
    // TÍNH TỔNG TIỀN HÓA ĐƠN
    // ===============================

    public double tinhTongTien() {

        double tong = 0;

        if (dscthd == null)
            return 0;

        for (ChiTietHoaDon ct : dscthd) {

            tong += ct.getSoluong() * ct.getDongia();

        }

        return tong;
    }

}