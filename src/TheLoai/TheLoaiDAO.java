package TheLoai;

import database.DBConnection;
import java.util.*;
import java.sql.*;
/**
 *
 * @author Quyen
 */

public class TheLoaiDAO {
    
    public ArrayList<TheLoai> loadTheLoai(){
        String qry = "select * from theloai";
        ArrayList<TheLoai> dstl = new ArrayList<TheLoai>();
        try(Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);
            ResultSet rs = st.executeQuery();){
            
            while(rs.next()){
                TheLoai tl = new TheLoai(
                        rs.getString("matheloai"),
                        rs.getString("tentheloai")
                );
                dstl.add(tl);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return dstl;
    }
    
    public boolean insertTheLoai(TheLoai tl){
        String qry = "insert into theloai values (?,?)";
        try(Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);){
            
            st.setString(1, tl.getMatl());
            st.setString(2, tl.getTentl());
            
            st.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deleteTheLoai(String ma){
        String qry = "Delete from theloai where matheloai = ?";
        try(Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);){
            
            st.setString(1, ma);
            
            st.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean updateTheLoai(TheLoai tl){
        String qry = "update theloai set matheloai = ?, tentheloai = ? where matheloai = ?";
        try(Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);){
            
            st.setString(1, tl.getMatl());
            st.setString(2, tl.getTentl());
            st.setString(3, tl.getMatl());
            
            st.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}