/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sach;

import java.util.*;
import java.sql.*;
/**
 *
 * @author ASUS
 */
public class SachDAO {
    
    public static Boolean insertSach(Sach sach){
        String qry = "insert into Sach values(?,?,?,?,?,?,?,?)";
        try{
            Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);
            st.setString(1,sach.getMasach());
            st.setString(2,sach.getTensach());
            st.setString(3,sach.getMatg());
            st.setString(4,sach.getMatl());
            st.setString(5,Integer.toString(sach.getNamxuatban()));
            st.setString(6,sach.getManxb());
            st.setString(7,Integer.toString(sach.getDongia()));
            st.setString(8,Integer.toString(sach.getSoluongton()));
            
            int rowsinserted = st.executeUpdate();
            if (rowsinserted > 0) {
                System.out.println("Thêm sach thành công!");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static Boolean deleteSach(String masach){
        String qry = "delete from Sach where MaSach = (?)";
        try{
            Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);
            st.setString(1,masach);
            
            int rowsdeleted = st.executeUpdate();
            if (rowsdeleted > 0) {
                System.out.println("Xoa sach thành công!");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static Boolean updateSach(Sach sach){
        String qry = "update Sach set MaSach = ?, TenSach = ?, MaTacGia = ?, MaTheLoai = ?, NamXuatBan = ?, MaNXB = ?, DonGia = ?, SoLuongTon = ? Where MaSach = ?";
        try{
            Connection conn = DBConnection.getDBConnection();
            PreparedStatement st = conn.prepareStatement(qry);
            st.setString(1,sach.getMasach());
            st.setString(2,sach.getTensach());
            st.setString(3,sach.getMatg());
            st.setString(4,sach.getMatl());
            st.setString(5,Integer.toString(sach.getNamxuatban()));
            st.setString(6,sach.getManxb());
            st.setString(7,Integer.toString(sach.getDongia()));
            st.setString(8,Integer.toString(sach.getSoluongton()));
            st.setString(9,sach.getMasach());
            
            int rowupdate = st.executeUpdate();
            if(rowupdate > 0){
                System.out.println("Cap nhat sach thành công!");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
}
