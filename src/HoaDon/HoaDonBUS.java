package HoaDon;

import ChiTietHoaDon.ChiTietHoaDon;
import ChiTietHoaDon.ChiTietHoaDonDAO;
import ChiTietHoaDon.ChiTietHoaDonBUS;
import java.time.LocalDate;
import java.util.ArrayList;

public class HoaDonBUS {

    static ArrayList<HoaDon> dshd;
    HoaDonDAO data = new HoaDonDAO();
    ChiTietHoaDonBUS cthdBUS= new ChiTietHoaDonBUS();
    public HoaDonBUS(){}

    // đọc danh sách hóa đơn từ database
    public void docDSHD(){
        dshd = data.docDSHD();
    }

    
    
    
    // lấy danh sách
    public ArrayList<HoaDon> getList(){
        if(dshd == null)
            docDSHD();
        return dshd;
    }

    
    
    
    // ktra ma hoa don
    public boolean checkMaHD(String ma){
        for(HoaDon hd: dshd){
            if(hd.getMahoadon().equals(ma)){
                return false;
            }
        }
     
        return true;
    }
    // thêm hóa đơn
    public boolean them(HoaDon hd){
        if(dshd == null)
            docDSHD();
        if(checkMaHD(hd.getMahoadon())){
        data.them(hd);
        dshd.add(hd);
        return true;
        }
        return false;
    }

    
    
    
    
    // xóa hóa đơn
    public boolean xoa(String ma){
       if(dshd==null){
           docDSHD();
       }
       
       for(int i=0;i<dshd.size();i++){
           if(dshd.get(i).getMahoadon().equals(ma)){
               data.xoa(ma);
               dshd.remove(i);
               return true;
           }
       }
       return false;
    }

    
    
    
    // sửa hóa đơn
    public boolean sua(HoaDon hd){
        if(dshd==null){
            docDSHD();
        }
        
        for(int i=0;i<dshd.size();i++){
            if(dshd.get(i).getMahoadon().equals(hd.getMahoadon())){
                data.sua(hd);
                dshd.set(i, hd);
                return true;
            }
        }
        return false;
    }

    
    
    
    
    // tìm kiếm theo mã hóa đơn
    public HoaDon timKiem(String ma){
       
        if(dshd==null){
            docDSHD();
        }
        
        for(HoaDon hd: dshd){
            if(hd.getMahoadon().equals(ma)){
                return hd;
            }
        }
        return null;
    }

    // tìm kiếm theo khách hàng
    public ArrayList<HoaDon> timTheoKhachHang(String maKH){

        if(dshd==null){
            docDSHD();
        }
        ArrayList<HoaDon> kq= new ArrayList<>();
        for(HoaDon hd: dshd){
            if(hd.getMakh().equals(maKH)){
                
                kq.add(hd);
            }
        }
        return kq;
    }

    // tìm kiếm theo nhân viên
    public ArrayList<HoaDon> timTheoNhanVien(String maNV){

        if(dshd==null){
            docDSHD();
        }
        ArrayList<HoaDon> kq = new ArrayList<>();

        for(HoaDon hd : dshd){
            if(hd.getManv().equals(maNV)){
                kq.add(hd);
            }
        }

        return kq;
    }
    
    
    
    

    // tổng tiền của 1 hóa đơn
   public double tinhTongTien(String maHD){
       ChiTietHoaDonBUS cthdBUS = new ChiTietHoaDonBUS();
       cthdBUS.docDSCTHD(maHD);
       return cthdBUS.tinhTongTien();

   }
   
   
   
   
   
    // tổng thu theo khoảng ngày
    public double tongThu(LocalDate tuNgay, LocalDate denNgay){

        double tong = 0;

        for(HoaDon hd : dshd){

            if((hd.getThoigiantao().isEqual(tuNgay) || hd.getThoigiantao().isAfter(tuNgay)) &&
               (hd.getThoigiantao().isEqual(denNgay) || hd.getThoigiantao().isBefore(denNgay)))
            {
                tong += hd.getTongtien();
            }
        }

        return tong;
    }

    // tổng thu theo tháng
    public double tongThuTheoThang(int thang, int nam){

        double tong = 0;

        for(HoaDon hd : dshd){

            if(hd.getThoigiantao().getMonthValue() == thang &&
               hd.getThoigiantao().getYear() == nam)
            {
                tong += hd.getTongtien();
            }
        }

        return tong;
    }

    // tổng thu theo quý
    public double tongThuTheoQuy(int quy, int nam){

        double tong = 0;

        for(HoaDon hd : dshd){

            int thang = hd.getThoigiantao().getMonthValue();

            if((thang >= (quy-1)*3 + 1) && (thang <= quy*3) &&
                hd.getThoigiantao().getYear() == nam)
            {
                tong += hd.getTongtien();
            }
        }

        return tong;
    }
    
    public String taoMaHD(){  
        return data.getMaHDMoi();
    }

}