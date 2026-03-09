package HoaDon;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class HoaDonDAO {

    String user = "root";
    String password = "";
    String url;
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public HoaDonDAO() {

        this.url = "jdbc:mysql://localhost:3306/quanlibansach3?useUnicode=true&characterEncoding=UTF-8";

        if (conn == null) {

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, password);

                System.out.println("Kết nối thành công!");

            } catch (ClassNotFoundException e) {

                JOptionPane.showMessageDialog(null, "Lỗi driver MySQL");

            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "Lỗi kết nối database");

            }
        }
    }

    // =========================
    // ĐỌC DANH SÁCH HÓA ĐƠN
    // =========================

    public ArrayList<HoaDon> docDSHD() {

        ArrayList<HoaDon> dshd = new ArrayList<>();

        try {

            String qry = "SELECT * FROM HoaDon";

            st = conn.createStatement();
            rs = st.executeQuery(qry);

            while (rs.next()) {

                HoaDon hd = new HoaDon();

                hd.maHD = rs.getString("maHD");
                hd.maKH = rs.getString("maKH");
                hd.maNV = rs.getString("maNV");
                hd.ngayLap = rs.getDate("ngayLap").toLocalDate();
                hd.tongTien = rs.getDouble("tongTien");

                dshd.add(hd);

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu hóa đơn");

        }

        return dshd;
    }

    // =========================
    // THÊM HÓA ĐƠN
    // =========================

    public void them(HoaDon hd) {

        try {

            String qry = "INSERT INTO HoaDon VALUES("
                    + "'" + hd.maHD + "',"
                    + "'" + hd.maKH + "',"
                    + "'" + hd.maNV + "',"
                    + "'" + hd.ngayLap + "',"
                    + hd.tongTien
                    + ")";

            st = conn.createStatement();
            st.executeUpdate(qry);

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Lỗi ghi thông tin hóa đơn");

        }
    }

    // =========================
    // XÓA HÓA ĐƠN
    // =========================

    public void xoa(String ma) {

        try {

            String qry = "DELETE FROM HoaDon WHERE maHD='" + ma + "'";

            st = conn.createStatement();
            st.executeUpdate(qry);

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Lỗi xóa hóa đơn");

        }

    }

    // =========================
    // SỬA HÓA ĐƠN
    // =========================

    public void sua(HoaDon hd) {

        try {

            String qry = "UPDATE HoaDon SET "
                    + "maKH='" + hd.maKH + "',"
                    + "maNV='" + hd.maNV + "',"
                    + "ngayLap='" + hd.ngayLap + "',"
                    + "tongTien=" + hd.tongTien
                    + " WHERE maHD='" + hd.maHD + "'";

            st = conn.createStatement();
            st.executeUpdate(qry);

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Lỗi cập nhật hóa đơn");

        }

    }

}