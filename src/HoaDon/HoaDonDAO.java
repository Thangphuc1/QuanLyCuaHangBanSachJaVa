package HoaDon;
import database.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class HoaDonDAO {

    String user = "root";
    String password = "Thang123@";
    String url;
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

   public HoaDonDAO() {

    this.url = "jdbc:mysql://localhost:3306/quanlibansach?useUnicode=true&characterEncoding=UTF-8";

    try {

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, password);

        System.out.println("Kết nối thành công!");

    } catch (Exception e) {

        e.printStackTrace();   // QUAN TRỌNG
    }
}

    // =========================
    // ĐỌC DANH SÁCH HÓA ĐƠN
    // =========================

    public ArrayList<HoaDon> docDSHD() {

        ArrayList<HoaDon> dshd = new ArrayList<>();

        try {

            String qry = "SELECT * FROM hoadon";

            st = conn.createStatement();
            rs = st.executeQuery(qry);

            while (rs.next()) {

                HoaDon hd = new HoaDon();

                hd.mahoadon = rs.getString("mahoadon");
                hd.makh = rs.getString("makh");
                hd.manv = rs.getString("manv");
                hd.thoigiantao = rs.getTimestamp("thoigiantao").toLocalDateTime().toLocalDate();
                hd.tongtien = rs.getDouble("tongtien");

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

    public boolean them(HoaDon hd) {

    try {

        String sql = "INSERT INTO hoadon(mahoadon, manv ,makh , thoigiantao, tongtien) VALUES(?,?,?,?,?)";
        Connection connn=DBConnection.getDBConnection();
        PreparedStatement ps = connn.prepareStatement(sql);

        ps.setString(1, hd.getMahoadon());
        ps.setString(3, hd.getMakh());
        ps.setString(2, hd.getManv());
        ps.setDate(4, java.sql.Date.valueOf(hd.getThoigiantao()));
        ps.setDouble(5, hd.getTongtien());

        ps.executeUpdate();

        return true;

    }catch (SQLException ex) {
        ex.printStackTrace();
    return false;
}
}

    // =========================
    // XÓA HÓA ĐƠN
    // =========================

    public void xoa(String ma) {

        try {

            String qry = "DELETE FROM hoadon WHERE mahoadon='" + ma + "'";

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

                String qry = "UPDATE hoadon SET "
                    + "makh='" + hd.makh + "',"
                    + "manv='" + hd.manv + "',"
                    + "thoigiantao='" + hd.thoigiantao + "',"
                    + "tongtien=" + hd.tongtien
                    + " WHERE mahoadon='" + hd.mahoadon + "'";

            st = conn.createStatement();
            st.executeUpdate(qry);

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Lỗi cập nhật hóa đơn");

        }

    }
    
    public String getMaHDMoi() {

    String maMax = "";

    try {

        String sql = "SELECT mahoadon FROM hoadon ORDER BY mahoadon DESC LIMIT 1";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        if(rs.next()){
            maMax = rs.getString("mahoadon");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    // nếu chưa có hóa đơn nào
    if(maMax.equals("")){
        return "HD01";
    }

    // tách số
    int so = Integer.parseInt(maMax.substring(2));

    so++;

    // format lại
    if(so < 10)
        return "HD0" + so;
    else
        return "HD" + so;
}

}