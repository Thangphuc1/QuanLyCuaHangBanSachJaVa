/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NhanVien;
    import database.DBConnection;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
/**
 *
 * @author ASUS
 */
public class NhanVienDAO {
    public ArrayList<NhanVien> loadNhanVien(){
        String qry = "select * from nhanvien";
        ArrayList<NhanVien> dsNhanVien = new ArrayList();
        try(Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);
            ResultSet rs = st.executeQuery();){
            
            while(rs.next()){
                        NhanVien nhanVien = new NhanVien(
                            rs.getString("manv"),
                            rs.getString("tennv"),
                            rs.getString("honv"),
                            rs.getString("DiaChi"),
                            rs.getString("sdt"),
                            rs.getString("email"),
                            rs.getDouble("luong")
);
                dsNhanVien.add(nhanVien);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return dsNhanVien;
    } 
    public boolean insertNhanVien(NhanVien nhanVien){
        String qry = "insert into nhanvien values (?,?,?,?,?,?,?)";
        try(Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);){
            
            st.setString(1, nhanVien.getMaNV());
            st.setString(2, nhanVien.getTenNV());
            st.setString(3, nhanVien.getHoNV());
            st.setString(4, nhanVien.getDiaChi());
            st.setString(5, nhanVien.getSDT());
            st.setString(6, nhanVien.getEmail());
            st.setDouble(7, nhanVien.getLuong());
            
            st.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean deleteNhanVien(String ma){
        String qry = "Delete from nhanvien where manv = ?";
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
    public boolean updateNhanVien(NhanVien nhanVien){
        String qry = "update hhanhanvien set manv = ?, tennv = ?, DiaChi = ?, sdt = ?, email = ? where manv = ?";
        try(Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);){
            
            st.setString(1, nhanVien.getMaNV());
            st.setString(2, nhanVien.getTenNV());
            st.setString(3, nhanVien.getHoNV());
            st.setString(4, nhanVien.getDiaChi());
            st.setString(5, nhanVien.getSDT());
            st.setString(6, nhanVien.getEmail());
            st.setDouble(7, nhanVien.getLuong());
            
            st.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}

