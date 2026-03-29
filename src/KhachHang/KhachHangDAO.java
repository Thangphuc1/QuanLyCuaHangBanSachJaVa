/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KhachHang;
    import database.DBConnection;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.*;

/**
 *
 * @author DuyPhuong
 */
public class KhachHangDAO {
        public ArrayList<KhachHang> loadKhachHang(){
            String qry = "select * from khachhang";
            ArrayList<KhachHang> dsKhachHang = new ArrayList();
            try(Connection conn = DBConnection.getDBConnection();
                PreparedStatement st = conn.prepareStatement(qry);
                ResultSet rs = st.executeQuery();){
                
                while(rs.next()){
                            KhachHang khachHang = new KhachHang(
                                rs.getString("makh"),
                                rs.getString("tenkhachhang"),
                                rs.getString("hokhachhang"),
                                rs.getString("DiaChi"),
                                rs.getString("sdt"),
                                rs.getString("email")
    );
                    dsKhachHang.add(khachHang);
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            return dsKhachHang;
        } 
        public boolean insertKhachHang(KhachHang khachHang){
            String qry = "insert into khachhang values (?,?,?,?,?,?)";
            try(Connection conn = DBConnection.getDBConnection();
                PreparedStatement st = conn.prepareStatement(qry);){
                
                st.setString(1, khachHang.getMaKH());
                st.setString(2, khachHang.getTenKH());
                st.setString(3, khachHang.getHoKH());
                st.setString(4, khachHang.getDiaChi());
                st.setString(5, khachHang.getSDT());
                st.setString(6, khachHang.getEmail());
                
                st.executeUpdate();
            }catch(SQLException ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }
        public boolean deleteKhachHang(String ma){
            String qry = "Delete from khachhang where makh = ?";
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
        public boolean updateKhachHang(KhachHang khachHang){
            String qry = "update hhakhachhang set makh = ?, tenkhachhang = ?, hokhachhang = ? , DiaChi = ?, sdt = ?, email = ? where makh = ?";
            try(Connection conn = DBConnection.getDBConnection();
                PreparedStatement st = conn.prepareStatement(qry);){
                
                st.setString(1, khachHang.getMaKH());
                st.setString(2, khachHang.getTenKH());
                st.setString(3, khachHang.getHoKH());
                st.setString(4, khachHang.getDiaChi());
                st.setString(5, khachHang.getSDT());
                st.setString(6, khachHang.getEmail());
                st.executeUpdate();
            }catch(SQLException ex){
                ex.printStackTrace();
                return false;
            }
            return true;
        }
    }