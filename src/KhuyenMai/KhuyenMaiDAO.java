package KhuyenMai;

import java.sql.*;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class KhuyenMaiDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // Lấy danh sách khuyến mãi
    public ArrayList<KhuyenMai> getAll() {

        ArrayList<KhuyenMai> list = new ArrayList<>();
        String sql = "SELECT * FROM KhuyenMai";

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                KhuyenMai km = new KhuyenMai(
                        rs.getString("maKM"),
                        rs.getString("tenKM"),
                        rs.getDate("ngayBD").toLocalDate(),
                        rs.getDate("ngayKT").toLocalDate(),
                        rs.getInt("DKTT"),
                        rs.getString("ghichu")
                );

                list.add(km);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc cơ sở dữ liệu khuyến mãi");
        }

        return list;
    }

    // Thêm khuyến mãi
    public boolean insert(KhuyenMai km) {

        String sql = "INSERT INTO KhuyenMai VALUES(?,?,?,?,?,?)";

        try {

            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, km.getMaKM());
            ps.setString(2, km.getTenKM());
            ps.setDate(3, Date.valueOf(km.getNgayBD()));
            ps.setDate(4, Date.valueOf(km.getNgayKT()));
            ps.setInt(5, km.getDKTT());
            ps.setString(6, km.getGhichu());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Lỗi thêm khuyến mãi");
        }

        return false;
    }

    // Sửa khuyến mãi
    public boolean update(KhuyenMai km) {

        String sql = "UPDATE KhuyenMai SET tenKM=?, ngayBD=?, ngayKT=?, DKTT=?, ghichu=? WHERE maKM=?";

        try {

            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, km.getTenKM());
            ps.setDate(2, Date.valueOf(km.getNgayBD()));
            ps.setDate(3, Date.valueOf(km.getNgayKT()));
            ps.setInt(4, km.getDKTT());
            ps.setString(5, km.getGhichu());
            ps.setString(6, km.getMaKM());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Lỗi sửa khuyến mãi");
        }

        return false;
    }

    // Xóa khuyến mãi
    public boolean delete(String maKM) {

        String sql = "DELETE FROM KhuyenMai WHERE maKM=?";

        try {

            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, maKM);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Lỗi xoá khuyến mãi");
        }

        return false;
    }
    // Tìm kiếm khuyến mãi
public ArrayList<KhuyenMai> search(String keyword) {

    ArrayList<KhuyenMai> list = new ArrayList<>();
    String sql = "SELECT * FROM KhuyenMai WHERE maKM LIKE ? OR tenKM LIKE ?";

    try {

        conn = DBConnection.getConnection();
        ps = conn.prepareStatement(sql);

        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");

        rs = ps.executeQuery();

        while (rs.next()) {

            KhuyenMai km = new KhuyenMai(
                    rs.getString("maKM"),
                    rs.getString("tenKM"),
                    rs.getDate("ngayBD").toLocalDate(),
                    rs.getDate("ngayKT").toLocalDate(),
                    rs.getInt("DKTT"),
                    rs.getString("ghichu")
            );

            list.add(km);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,"Lỗi tìm kiếm khuyến mãi");
    }

    return list;
}
}