/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KhachHang;
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
                                rs.getString("MaNhanVien"),
                                rs.getString("TenNhanVien"),
                                rs.getString("HoNhanVien"),
                                rs.getString("DiaChi"),
                                rs.getString("SDT"),
                                rs.getString("Email")
    );
                    dsKhachHang.add(khachHang);
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            return dsKhachHang;
        } 
        public boolean insertKhachHang(KhachHang khachHang){
            String qry = "insert into khachhang values (?,?,?,?,?)";
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
            String qry = "Delete from khachhang where MaKH = ?";
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
            String qry = "update hhakhachhang set maKH = ?, TenKH = ?, DiaChi = ?, SDT = ?, Email = ? where MaNV = ?";
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

