package CHiTietKhuyenMaiSP;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CHiTietKhuyenMaiSPDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // Lấy danh sách
    public ArrayList<CHiTietKhuyenMaiSP> getAll() {

        ArrayList<CHiTietKhuyenMaiSP> list = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietKhuyenMaiSP";

        try {

            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                CHiTietKhuyenMaiSP ctkm = new CHiTietKhuyenMaiSP(
                        rs.getString("makm"),
                        rs.getString("masach"),
                        rs.getFloat("phantramgiamgia")
                );

                list.add(ctkm);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu ChiTietKhuyenMaiSP");
        }

        return list;
    }

    // Thêm
    public boolean insert(CHiTietKhuyenMaiSP ctkm) {

        String sql = "INSERT INTO ChiTietKhuyenMaiSP VALUES(?,?,?)";

        try {

            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, ctkm.getMaKM());
            ps.setString(2, ctkm.getMaSP());
            ps.setFloat(3, ctkm.getPhantramgg());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm ChiTietKhuyenMaiSP");
        }

        return false;
    }

    // Sửa
    public boolean update(CHiTietKhuyenMaiSP ctkm) {

        String sql = "UPDATE ChiTietKhuyenMaiSP SET phantramgiamgia =? WHERE makm=? AND masach=?";

        try {

            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setFloat(1, ctkm.getPhantramgg());
            ps.setString(2, ctkm.getMaKM());
            ps.setString(3, ctkm.getMaSP());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi sửa ChiTietKhuyenMaiSP");
        }

        return false;
    }

    // Xóa
    public boolean delete(String maKM, String maSP) {

        String sql = "DELETE FROM ChiTietKhuyenMaiSP WHERE makm =? AND masach=?";

        try {

            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, maKM);
            ps.setString(2, maSP);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi xoá ChiTietKhuyenMaiSP");
        }

        return false;
    }
    
    //Tìm kiếm
    public ArrayList<CHiTietKhuyenMaiSP> search(String keyword) { 
        ArrayList<CHiTietKhuyenMaiSP> list = new ArrayList<>();
        String sql = "SELECT * FROM KhuyenMai WHERE makm LIKE ? OR masach LIKE ?"; 
        try { 
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql); 
            ps.setString(1, "%" + keyword + "%"); ps.setString(2, "%" + keyword + "%"); 
            rs = ps.executeQuery();
            while (rs.next()) { 
                CHiTietKhuyenMaiSP ctkm = new CHiTietKhuyenMaiSP( 
                        rs.getString("makm"), 
                        rs.getString("masach"),  
                        rs.getFloat("phantramgiamgia") );
                list.add(ctkm); 
            } 
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null,"Lỗi tìm kiếm khuyến mãi");
        } return list; }
}