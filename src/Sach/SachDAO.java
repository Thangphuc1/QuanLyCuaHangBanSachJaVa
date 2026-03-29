package Sach;

import database.DBConnection;
import java.util.*;
import java.sql.*;
/**
 *
 * @author Quyen
 */
public class SachDAO {
    
    public static ArrayList<Sach> loadSach(){
        ArrayList<Sach> dss = new ArrayList<Sach>();
        String qry = "SELECT masach, tensach, matacgia, matheloai, namxuatban, manxb, dongia, soluongton FROM sach";
        try ( Connection conn = DBConnection.getDBConnection();
              PreparedStatement st = conn.prepareStatement(qry);
              ResultSet rs = st.executeQuery()){
            
            while(rs.next()){
                Sach s = new Sach(
                rs.getString("masach"),
                rs.getString("tensach"),
                rs.getString("matacgia"),
                rs.getString("matheloai"),
                rs.getInt("namxuatban"),
                rs.getString("manxb"),
                rs.getInt("dongia"),
                rs.getInt("soluongton")
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
        String qry = "delete from sach where masach = (?)";
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
        String qry = "update sach set masach = ?, tensach = ?, matacgia = ?, matheloai = ?, namxuatban = ?, manxb = ?, dongia = ?, soluongton = ? Where masach = ?";
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
            pstmt.setInt(1, soLuong); 
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
