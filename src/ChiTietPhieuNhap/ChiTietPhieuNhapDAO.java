
package ChiTietPhieuNhap;

/**
 *
 * @author ASUS
 */
import database.DBConnection;
import java.util.ArrayList;
import java.sql.*;
import PhieuNhap.PhieuNhapDAO;
public class ChiTietPhieuNhapDAO {
    private final PhieuNhapDAO phieunhapdao = new PhieuNhapDAO();
    
     public ArrayList<ChiTietPhieuNhap> LoadChiTietPhieuNhap() {
        String sql="select * from chitietphieunhap";
        ArrayList<ChiTietPhieuNhap> dsctpn=new ArrayList<ChiTietPhieuNhap>();
        try {
            Connection conn=DBConnection.getDBConnection();
            PreparedStatement pstmt= conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()) {
                ChiTietPhieuNhap ctpn=new ChiTietPhieuNhap(
                        rs.getString("maphieunhap"),
                        rs.getString("masach"),
                        rs.getInt("soluong"),
                        rs.getDouble("dongia"),
                        rs.getDouble("thanhtien"));
                dsctpn.add(ctpn);
                        
            }
            conn.close();
            pstmt.close();
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return dsctpn;
    }
     public ArrayList<ChiTietPhieuNhap> GetByPhieuNhap (String mapn) {
         String sql="select * from chitietphieunhap where maphieunhap = ?";
         ArrayList<ChiTietPhieuNhap> dsctpn=new ArrayList<ChiTietPhieuNhap>();
         try {
             Connection conn=DBConnection.getDBConnection();
             PreparedStatement pstmt=conn.prepareStatement(sql);
             pstmt.setString(1, mapn);
             ResultSet rs=pstmt.executeQuery();
             while (rs.next()) {
                 ChiTietPhieuNhap ctpn=new ChiTietPhieuNhap(
                        rs.getString("maphieunhap"),
                        rs.getString("masach"),
                        rs.getInt("soluong"),
                        rs.getDouble("dongia"),
                        rs.getDouble("thanhtien"));
                dsctpn.add(ctpn);
             }
         }catch(SQLException e) {
             e.printStackTrace();
         }
         
         return dsctpn;
     }
     
    public boolean InsertChiTietPhieuNhap(ChiTietPhieuNhap ctpn) {
        String sql="insert into chitietphieunhap (maphieunhap,masach,soluong,dongia,thanhtien) values(?,?,?,?,?)";
        Double thanhtien= ctpn.getSoLuong() * ctpn.getDonGia();
        try {
            Connection conn=DBConnection.getDBConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,ctpn.getMaPN() );
            pstmt.setString(2,ctpn.getMaSach());
            pstmt.setInt(3,ctpn.getSoLuong());
            pstmt.setDouble(4, ctpn.getDonGia());
            pstmt.setDouble(5, thanhtien);
            pstmt.executeUpdate();
            phieunhapdao.UpdateTongTienPN(ctpn.getMaPN());
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean DeleteChiTietPhieuNhap(ChiTietPhieuNhap ctpn) {
        String sql="delete from  chitietphieunhap where maphieunhap = ? and masach = ? ";
        try{
            Connection conn=DBConnection.getDBConnection();
            PreparedStatement pstmt= conn.prepareStatement(sql);
            pstmt.setString(1, ctpn.getMaPN());
            pstmt.setString(2,ctpn.getMaSach());
            pstmt.executeUpdate();
            phieunhapdao.UpdateTongTienPN(ctpn.getMaPN());
            conn.close();
            pstmt.close();
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
            }
     return true;
    }
   public boolean UpdateChiTietPhieuNhap( ChiTietPhieuNhap ctpn) {
       String sql="update chitietphieunhap set soluong = ?, dongia = ? where maphieunhap = ? and masach = ?";
       Double thanhtien= ctpn.getSoLuong() * ctpn.getDonGia();
       try {
           Connection conn=DBConnection.getDBConnection();
           PreparedStatement pstmt=conn.prepareStatement(sql);
           pstmt.setInt(1,ctpn.getSoLuong());
           pstmt.setDouble(2, ctpn.getDonGia());
           pstmt.setDouble(3,thanhtien);
           pstmt.executeUpdate();
           phieunhapdao.UpdateTongTienPN(ctpn.getMaPN());
       }catch(SQLException e) {
           e.printStackTrace();
           return false;
       }
       return true;
   }
   public boolean TonTaiMaSach(String mapn ,String mas) {
       String sql="select count(*) from chitietphieunhap where maphieunhap = ? and masach = ?";
       try{
           Connection conn=DBConnection.getDBConnection();
           PreparedStatement pstmt=conn.prepareStatement(sql);
           pstmt.setString(1, mapn);
           pstmt.setString(2, mas);
           ResultSet rs=pstmt.executeQuery();
           if(rs.next()) {
               return rs.getInt(1)>0;
           }
       }catch(SQLException e) {
           e.printStackTrace();
       }
       return false;
   }
}
   