/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package PhieuNhap;

/**
 *
 * @author ASUS
 */
import database.DBConnection;
import java.util.ArrayList;
import java.sql.*;

public class PhieuNhapDAO {
    public ArrayList<PhieuNhap> LoadPhieuNhap() {
        ArrayList<PhieuNhap> dspn= new ArrayList<PhieuNhap>();
        String sql="select * from phieunhap";
        try {
            Connection conn=DBConnection.getDBConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()) {
               PhieuNhap pn=new PhieuNhap(
                       rs.getString("maphieunhap"),
                       rs.getString("manv"),
                       rs.getString("mancc"),
                       rs.getDate("thoigiantao").toLocalDate(),
                       rs.getDouble("tongtien"));
                       dspn.add(pn); 
            }
            conn.close();
            pstmt.close();
            rs.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return dspn;
    }
    public boolean InSertPhieuNhap(PhieuNhap pn) {
        String sql="insert into phieunhap (maphieunhap,manv,mancc,thoigiantao,tongtien) values (?,?,?,?,?)";
        try {
            Connection conn=DBConnection.getDBConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,pn.getMaPN());
            pstmt.setString(2,pn.getMaNV() );
            pstmt.setString(3,pn.getMaNCC());
            pstmt.setDate(4,java.sql.Date.valueOf(pn.getNgayLap()));
            pstmt.setDouble(5,0.0);
            pstmt.executeUpdate();
            conn.close();
            pstmt.close();
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean DeletePhieuNhap (PhieuNhap pn) {
        String sql="delete from phieunhap where maphieunhap= ?";
        try {
            Connection conn=DBConnection.getDBConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,pn.getMaNCC());
            pstmt.executeUpdate();
            conn.close();
            pstmt.close();
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
       return true;
    }
    public boolean UpdatePhieuNhap(PhieuNhap pn) {
        String sql="update phieunhap set manv= ?, mancc= ?, thoigiantao = ?,";
        if(KtraTrungMa(pn.getMaPN())) {
            System.out.println("ma da bi trung");
            return false;
        }
        try {
            Connection conn=DBConnection.getDBConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,pn.getMaNV());
            pstmt.setString(2,pn.getMaNCC() );
            pstmt.setDate(3,java.sql.Date.valueOf(pn.getNgayLap()) );
            pstmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public  void UpdateTongTienPN(String mapn) {
        String sql = "UPDATE phieunhap SET tongtien = (" +
             "SELECT IFNULL(SUM(thanhtien),0) " +
             "FROM chitietphieunhap WHERE maphieunhap = ?" +
             ") WHERE maphieunhap = ?";
        try {
            Connection conn=DBConnection.getDBConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, mapn);
            pstmt.setString(2, mapn);
            pstmt.executeUpdate();
            conn.close();
            pstmt.close();     
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    //validate kiem tra trung phieu
    public boolean KtraTrungMa(String mapn) {
        String sql="select count(*) from phieunhap where maphieunhap=?";
        try {
            Connection conn=DBConnection.getDBConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, mapn);
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
