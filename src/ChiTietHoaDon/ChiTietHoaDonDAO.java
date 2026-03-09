package ChiTietHoaDon;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ChiTietHoaDonDAO {

    String user = "root";
    String password = "";
    String url;
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public ChiTietHoaDonDAO() throws SQLException {

        url = "jdbc:mysql://localhost:3306/quanlibansach3?useUnicode=true&characterEncoding=UTF-8";

        if (conn == null) {

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, password);

                System.out.println("Kết nối thành công");

            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Lỗi driver MySQL");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi kết nối database");
            }

        }
    }

    // ===============================
    // ĐỌC DANH SÁCH CHI TIẾT HÓA ĐƠN
    // ===============================

    public ArrayList<ChiTietHoaDon> Docdscthd(String maHD) {

        ArrayList<ChiTietHoaDon> dscthd = new ArrayList<>();

        try {

            String qry = "SELECT * FROM chitiethoadon WHERE maHD='"+maHD+"'";

            st = conn.createStatement();
            rs = st.executeQuery(qry);

            while (rs.next()) {

                ChiTietHoaDon cthd = new ChiTietHoaDon();

                cthd.setMaHD(rs.getString("maHD"));
                cthd.setMaSach(rs.getString("maSach"));
                cthd.setSoLuong(rs.getInt("soLuong"));
                cthd.setDonGia(rs.getDouble("donGia"));

                dscthd.add(cthd);

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu chi tiết hóa đơn");

        }

        return dscthd;

    }

    // ===============================
    // THÊM CHI TIẾT HÓA ĐƠN
    // ===============================

    public boolean ThemCTHD(ChiTietHoaDon cthd) {

        try {

            String sql = "INSERT INTO chitiethoadon VALUES("
                    + "'" + cthd.getMaHD() + "',"
                    + "'" + cthd.getMaSach() + "',"
                    + cthd.getSoLuong() + ","
                    + cthd.getDonGia() + ","
                    + cthd.getThanhTien() + ")";

            st = conn.createStatement();

            st.executeUpdate(sql);

            return true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Lỗi thêm chi tiết hóa đơn");

        }

        return false;

    }

    // ===============================
    // SỬA CHI TIẾT HÓA ĐƠN
    // ===============================

    public boolean SuaCTHD(ChiTietHoaDon cthd) {

        try {

            String sql = "UPDATE chitiethoadon SET "
                    + "soLuong=" + cthd.getSoLuong() + ","
                    + "donGia=" + cthd.getDonGia() + ","
                    + "thanhTien=" + cthd.getThanhTien()
                    + " WHERE maHD='" + cthd.getMaHD()
                    + "' AND maSach='" + cthd.getMaSach() + "'";

            st = conn.createStatement();

            st.executeUpdate(sql);

            return true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Lỗi sửa chi tiết hóa đơn");

        }

        return false;

    }

    // ===============================
    // XÓA CHI TIẾT HÓA ĐƠN
    // ===============================

    public boolean XoaCTHD(String mahd, String masach) {

        try {

            String sql = "DELETE FROM chitiethoadon "
                    + "WHERE maHD='" + mahd + "' "
                    + "AND maSach='" + masach + "'";

            st = conn.createStatement();

            st.executeUpdate(sql);

            return true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Lỗi xóa chi tiết hóa đơn");

        }

        return false;

    }

}