package Sach;

import java.util.*;
import java.sql.*;
/**
 *
 * @author Quyen
 */
public class SachDAO {
    
    public static ArrayList<Sach> loadSach(){
        ArrayList<Sach> dss = new ArrayList<Sach>();
        String qry = "select * from sach";
        try ( Connection conn = DBConnection.getDBConnection();
              PreparedStatement st = conn.prepareStatement(qry);
              ResultSet rs = st.executeQuery()){
            
            while(rs.next()){
                Sach s = new Sach(
                rs.getString("MaSach"),
                rs.getString("TenSach"),
                rs.getString("MaTacGia"),
                rs.getString("MaTheLoai"),
                rs.getInt("NamXuatBan"),
                rs.getString("manxb"),
                rs.getInt("DonGia"),
                rs.getInt("SoLuongTon")
                );
                dss.add(s);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return dss;
    }
    
    public static Boolean insertSach(Sach sach){
        String qry = "insert into sach values(?,?,?,?,?,?,?,?)";
        try(Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);){
            
            st.setString(1,sach.getMasach());
            st.setString(2,sach.getTensach());
            st.setString(3,sach.getMatg());
            st.setString(4,sach.getMatl());
            st.setInt(5,sach.getNamxuatban());
            st.setString(6,sach.getManxb());
            st.setInt(7,sach.getDongia());
            st.setInt(8,sach.getSoluongton());
            
            st.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static Boolean deleteSach(String masach){
        String qry = "delete from sach where MaSach = (?)";
        try(Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);){
            
            st.setString(1,masach);
            
            st.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static Boolean updateSach(Sach sach){
        String qry = "update sach set MaSach = ?, TenSach = ?, MaTacGia = ?, MaTheLoai = ?, NamXuatBan = ?, MaNXB = ?, DonGia = ?, SoLuongTon = ? Where MaSach = ?";
        try(Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);){
            
            st.setString(1,sach.getMasach());
            st.setString(2,sach.getTensach());
            st.setString(3,sach.getMatg());
            st.setString(4,sach.getMatl());
            st.setInt(5,sach.getNamxuatban());
            st.setString(6,sach.getManxb());
            st.setInt(7,sach.getDongia());
            st.setInt(8,sach.getSoluongton());
            st.setString(9,sach.getMasach());
            
            st.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean UpdateSoLuongTon(String maSach, int soLuong) {
    String sql = "UPDATE sach SET soluongton = soluongton + ? WHERE masach = ?";
    
    try {
        Connection conn = DBConnection.getDBConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, soLuong);     // ⭐ Cộng thêm
        pstmt.setString(2, maSach);
        
        int result = pstmt.executeUpdate();
        
        conn.close();
        pstmt.close();
        
        return result > 0;
        
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}
