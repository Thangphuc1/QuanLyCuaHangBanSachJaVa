package NhaXuatBan;

import database.DBConnection;
import java.util.*;
import java.sql.*;
/**
 *
 * @author Quyen
 */

public class NhaXuatBanDAO {
    
    public ArrayList<NhaXuatBan> loadNhaXuatBan(){
        String qry = "select * from nhaxuatban";
        ArrayList<NhaXuatBan> dsnxb = new ArrayList<NhaXuatBan>();
        try(Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);
            ResultSet rs = st.executeQuery();){
            
            while(rs.next()){
                NhaXuatBan nxb = new NhaXuatBan(
                        rs.getString("manxb"),
                        rs.getString("tennxb"),
                        rs.getString("diachinxb"),
                        rs.getString("sdt"),
                        rs.getString("email")
                );
                dsnxb.add(nxb);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return dsnxb;
    }
    
    public boolean insertNhaXuatBan(NhaXuatBan nxb){
        String qry = "insert into nhaxuatban values (?,?,?,?,?)";
        try(Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);){
            
            st.setString(1, nxb.getManxb());
            st.setString(2, nxb.getTennxb());
            st.setString(3, nxb.getDiachi());
            st.setString(4, nxb.getSdt());
            st.setString(5, nxb.getEmail());
            
            st.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deleteNhaXuatBan(String ma){
        String qry = "Delete from nhaxuatban where manxb = ?";
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
    
    public boolean updateNhaXuatBan(NhaXuatBan nxb){
        String qry = "update nhaxuatban set manxb = ?, tennxb = ?, diachinxb = ?, sdt = ?, email = ? where manxb = ?";
        try(Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);){
            
            st.setString(1, nxb.getManxb());
            st.setString(2, nxb.getTennxb());
            st.setString(3, nxb.getDiachi());
            st.setString(4, nxb.getSdt());
            st.setString(5, nxb.getEmail());
            st.setString(6, nxb.getManxb());
            
            st.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}

