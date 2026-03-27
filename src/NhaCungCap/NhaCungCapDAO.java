/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package NhaCungCap;

import database.DBConnection;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author ASUS
 */
public class NhaCungCapDAO {
    
    public  ArrayList<NhaCungCap> LoadNhaCungCap() {
        ArrayList<NhaCungCap> dsncc = new ArrayList<NhaCungCap>();
        String sql="select * from nhacungcap";
            try {
                Connection conn=DBConnection.getDBConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql);
                ResultSet rs=pstmt.executeQuery();
                while(rs.next()) {
                NhaCungCap ncc=new NhaCungCap(rs.getString("mancc"),
                        rs.getString("tenncc"),
                        rs.getString("diachincc"),
                        rs.getString("sdt")
                        ,rs.getString("email"));
                        dsncc.add(ncc);
                }
                conn.close();
            pstmt.close();
            rs.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        return dsncc;
    }
    
    public boolean InsertNhaCungCap(NhaCungCap ncc) {
        String sql="insert into nhacungcap (mancc,tenncc,diachincc,sdt,email)values(?,?,?,?,?)";
        try{
           Connection conn=DBConnection.getDBConnection();
//           conn.setAutoCommit(false);
           PreparedStatement pstmt=conn.prepareStatement(sql); 
           pstmt.setString(1, ncc.getMaNCC());
           pstmt.setString(2, ncc.getTenNCC());
           pstmt.setString(3, ncc.getDiaChi());
           pstmt.setString(4, ncc.getSoDienThoai());
           pstmt.setString(5, ncc.getEmail());
           pstmt.executeUpdate();
//           conn.commit();
           conn.close();
           pstmt.close();
        }catch(SQLException e) {
                e.printStackTrace();
                return false;
            }
        return true;
    }
    public boolean deleteNhaCungCap(String mancc) {
        String sql="delete from nhacungcap where mancc=?";
        try{
            Connection conn=DBConnection.getDBConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, mancc);
            pstmt.executeUpdate();
            conn.close();
            pstmt.close();
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean UpdateNhaCungCap(NhaCungCap ncc) {
        String sql="update nhacungcap set tenncc = ?, diachincc = ?, sdt = ?, email = ?  where mancc = ?";
        try {
            Connection conn=DBConnection.getDBConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, ncc.getTenNCC());
            pstmt.setString(2, ncc.getDiaChi());
            pstmt.setString(3, ncc.getSoDienThoai());
            pstmt.setString(4,ncc.getEmail());
            pstmt.setString(5, ncc.getMaNCC());
            pstmt.executeUpdate();
            conn.close();
            pstmt.close();
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
 //==========================VALIDATE=====================
    public boolean KiemTraNhaCungCapCoPhieuNhap(String mancc) {
      String sql="select count(*) from phieunhap where mancc = ?";
      try {
           Connection conn=DBConnection.getDBConnection();
           PreparedStatement pstmt=conn.prepareStatement(sql);
           pstmt.setString(1,mancc);
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()) {
               return rs.getInt(1)>0;
           }
           conn.close();
           pstmt.close();
           rs.close();
      }catch(SQLException e) {
          e.printStackTrace();
      }
      return false;
    }
    
    
}
    

