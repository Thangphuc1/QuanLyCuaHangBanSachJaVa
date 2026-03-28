package ThongKe;

import Sach.Sach;
import database.DBConnection;

import java.sql.*;
import java.util.*;

public class ThongKeDAO {

    public ThongKe getThongKe(int nam) {
        ThongKe thongKe = new ThongKe();
        int[] tongThuQuy = new int[4];
        
        thongKe.setSoLuongSach(getTongSoLuongSach());
        thongKe.setSoLuongKH(getSoLuongKhachHang());
        thongKe.setSoLuongNV(getSoLuongNhanVien());
        
        tongThuQuy[0] = getTongThuQuy(nam, 1);
        tongThuQuy[1] = getTongThuQuy(nam, 2);
        tongThuQuy[2] = getTongThuQuy(nam, 3);
        tongThuQuy[3] = getTongThuQuy(nam, 4);
        thongKe.setTongThuQuy(tongThuQuy);
        
        thongKe.setTopSachBanChay(getTopBanChay());
        
        return thongKe;
    }

    private ArrayList<Sach> getTopBanChay() {
        ArrayList<Sach> dssach = new ArrayList<Sach>();
        String sql = "SELECT MaSach, DaBan FROM (" +
                     "SELECT MaSach, SUM(SoLuong) AS DaBan FROM " +
                     "chitiethoadon GROUP BY MaSach" +
                     ") temp " +
                     "ORDER BY DaBan DESC LIMIT 5";
                     
        try (Connection conn = DBConnection.getDBConnection();
              Statement st = conn.createStatement();
              ResultSet rs = st.executeQuery(sql) ) {
            
            while (rs.next()) {
                Sach sach = new Sach();
                sach.setMasach(rs.getString("MaSach"));
                sach.setSoluongton(rs.getInt("DaBan"));
                dssach.add(sach);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dssach;
    }

    private int getTongSoLuongSach() {
        String sql = "SELECT COUNT(*) FROM sach";
        try (Connection conn = DBConnection.getDBConnection();
              Statement stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(sql) ) {
            
            while (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return 0;
    }

    private String[] getDateString(int nam, int quy) {
        int namBatDau = nam;
        int namKetThuc = nam;
        String thangBatDau = "01"; 
        String thangKetThuc = "04"; 
        String[] kq = new String[2];
        
        switch (quy) {
            case 1:
                thangBatDau = "01";
                thangKetThuc = "04";
                break;
            case 2:
                thangBatDau = "04";
                thangKetThuc = "07";
                break;
            case 3:
                thangBatDau = "07";
                thangKetThuc = "10";
                break;
            case 4:
                thangBatDau = "10";
                thangKetThuc = "01";
                namKetThuc++;
                break;
        }
        String strBatDau = Integer.toString(namBatDau) + "-" + thangBatDau + "-01";
        String strKetThuc = Integer.toString(namKetThuc) + "-" + thangKetThuc + "-01";
        
        kq[0] = strBatDau;
        kq[1] = strKetThuc;
        return kq;
    }

    private int getTongThuQuy(int nam, int quy) {
        String[] dateString = getDateString(nam, quy);
        // Đã sửa NgayLap thành thoigiantao và TongTien thành tongtien
        String sql = "SELECT SUM(tongtien) FROM hoadon WHERE thoigiantao >= CAST(? AS DATE) AND thoigiantao < CAST(? AS DATE)";
        
        try (Connection conn = DBConnection.getDBConnection();
              PreparedStatement prep = conn.prepareStatement(sql) ) {
            
            prep.setString(1, dateString[0]);
            prep.setString(2, dateString[1]);
            
            try (ResultSet rs = prep.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return 0;
    } 

    private int getSoLuongNhanVien() {
        String sql = "SELECT COUNT(*) FROM nhanvien";
        try (Connection conn = DBConnection.getDBConnection();
              Statement stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(sql) ) {
            
            while (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return 0;
    }

    private int getSoLuongKhachHang() {
        String sql = "SELECT COUNT(*) FROM khachhang";
        try ( Connection conn = DBConnection.getDBConnection();
              Statement stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(sql) ) {
            
            while (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
        return 0;
    }

    public double getDoanhThuThang(int thang, int nam) {
        String thangBD = nam + "-" + thang + "-01";
        String thangKT = nam + "-" + (thang + 1) + "-01";
        
        if (thang == 12) {
            thangKT = (nam + 1) + "-01-01";
        }
        
        String sql = "SELECT SUM(tongtien) FROM hoadon WHERE thoigiantao >= CAST(? AS DATE) AND thoigiantao < CAST(? AS DATE)";
        
        try ( Connection conn = DBConnection.getDBConnection();
              PreparedStatement pre = conn.prepareStatement(sql) ) {
            
            pre.setString(1, thangBD);
            pre.setString(2, thangKT);
            
            try ( ResultSet rs = pre.executeQuery() ) {
                while (rs.next()) {
                    return rs.getDouble(1);
                }
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0.0;
    }
}
