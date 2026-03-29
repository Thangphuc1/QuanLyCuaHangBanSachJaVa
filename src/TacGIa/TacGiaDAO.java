package TacGIa;

import java.util.*;
import java.sql.*;
/**
 *
 * @author Quyen
 */

public class TacGiaDAO {
    
    public ArrayList<TacGia> loadTacGia(){
        String qry = "select * from tacgia";
        ArrayList<TacGia> dstg = new ArrayList<TacGia>();
        try(Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);
            ResultSet rs = st.executeQuery();){
            
            while(rs.next()){
                TacGia tg = new TacGia(
                        rs.getString("matacgia"),
                        rs.getString("tentacgia"),
                        rs.getInt("namsinh"),
                        rs.getString("gioitinh"),
                        rs.getString("quoctich")
                );
                dstg.add(tg);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return dstg;
    }
    
    public boolean insertTacGia(TacGia tg){
        String qry = "insert into tacgia values (?,?,?,?,?)";
        try(Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);){
            
            st.setString(1, tg.getMatg());
            st.setString(2, tg.getTentg());
            st.setString(3, tg.getGioitinh());
            st.setInt(4, tg.getNamsinh());
            st.setString(5, tg.getQuoctich());
            
            st.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deleteTacGia(String ma){
        String qry = "Delete from tacgia where matacgia = ?";
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
    
    public boolean updateTacGia(TacGia tg){
        String qry = "update tacgia set matacgia = ?, tentacgia = ?, gioitinh = ?, namsinh = ?,  quoctich = ? where matacgia = ?";
        try(Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);){
            
            st.setString(1, tg.getMatg());
            st.setString(2, tg.getTentg());
            st.setString(3, tg.getGioitinh());
            st.setInt(4, tg.getNamsinh());
            st.setString(5, tg.getQuoctich());
            st.setString(6, tg.getMatg());
            
            st.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
