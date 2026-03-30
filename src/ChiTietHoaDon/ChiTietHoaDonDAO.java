package ChiTietHoaDon;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

public class ChiTietHoaDonDAO {

    String user = "root";
    String password = "Thang123@";
    String url;
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public ChiTietHoaDonDAO() throws SQLException {

        url = "jdbc:mysql://localhost:3306/quanlibansach?useUnicode=true&characterEncoding=UTF-8";

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

            String qry = "SELECT * FROM chitiethoadon WHERE mahoadon='"+maHD+"'";

            st = conn.createStatement();
            rs = st.executeQuery(qry);

            while (rs.next()) {

                ChiTietHoaDon cthd = new ChiTietHoaDon();

                cthd.setMahoadon(rs.getString("mahoadon"));
                cthd.setMasach(rs.getString("masach"));
                cthd.setSoluong(rs.getInt("soluong"));
                cthd.setDongia(rs.getDouble("dongia"));

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

                String sql = "INSERT INTO chitiethoadon(mahoadon, masach, soluong, dongia) VALUES(?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, cthd.getMahoadon());
                ps.setString(2, cthd.getMasach());
                ps.setInt(3, cthd.getSoluong());
                ps.setDouble(4, cthd.getDongia());
                ps.executeUpdate();
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
                    + "soluong=" + cthd.getSoluong() + ","
                    + "dongia=" + cthd.getDongia()
                    + " WHERE mahoadon='" + cthd.getMahoadon()
                    + "' AND masach='" + cthd.getMasach() + "'";

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
                    + "WHERE mahoadon='" + mahd + "' "
                    + "AND masach='" + masach + "'";

            st = conn.createStatement();

            st.executeUpdate(sql);

            return true;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Lỗi xóa chi tiết hóa đơn");

        }

        return false;

    }
    public boolean xoaCTHDTheoMa(String maHD){

    String sql = "DELETE FROM chitiethoadon WHERE mahoadon=?";

    try{

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, maHD);

        ps.executeUpdate();

        return true;

    }catch(SQLException e){

        JOptionPane.showMessageDialog(null,"Lỗi xóa chi tiết hóa đơn");

    }

    return false;
}

}