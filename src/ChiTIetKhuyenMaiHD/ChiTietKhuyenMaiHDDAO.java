/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChiTIetKhuyenMaiHD;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author ASUS
 */
public class ChiTietKhuyenMaiHDDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    // Lấy danh sách
    public ArrayList<ChiTietKhuyenMaiHD> getAll(){
        ArrayList<ChiTietKhuyenMaiHD> list = new ArrayList<>();
        
        String sql = "Select* from ChiTietKhuyenMaiHD";
        
        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                ChiTietKhuyenMaiHD ctkmhd = new ChiTietKhuyenMaiHD(
                    rs.getString("maKM"),
                    rs.getString("TTHD"),
                    rs.getFloat("Phantramgg")
                );
                
                list.add(ctkmhd);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Lỗi đọc csdl ChiTietKhuyenMaiHoaDon");
        }
        
        return list;
    }
    
    // Thêm
    public boolean insert(ChiTietKhuyenMaiHD ctkmhd){
        String sql = "Insert into ChiTietKhuyenMaiHD Values(?,?,?)";
        
        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, ctkmhd.getMaKM());
            ps.setString(2, ctkmhd.getTTHD());
            ps.setFloat(3, ctkmhd.getPhantramgg());
            
            return ps.executeUpdate() > 0;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Lỗi thêm ChiTietKhuyenMaiHD");
        }
        return false;
    }
    
    // Sửa
    public boolean Update(ChiTietKhuyenMaiHD ctkmhd){
        String sql = "Update ChiTietKhuyenMaiHD set Phantramgg=? where maKM=? and TTHD = ?";
        
        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setFloat(1, ctkmhd.getPhantramgg());
            ps.setString(2, ctkmhd.getMaKM());
            ps.setString(3, ctkmhd.getTTHD());
            
            return ps.executeUpdate() >0;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Lỗi sửa ChiTietKhuyenMaiHD");
        }
        return false;
    }
    
//Xoa
    public boolean Delete(ChiTietKhuyenMaiHD ctkmhd){
        String sql = "Delete from ChiTietKhuyenMaiHD where maKM=? and TTHD=?";
        
        try{
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, ctkmhd.getMaKM());
            ps.setString(2, ctkmhd.getTTHD());
            
            return ps.executeUpdate() > 0;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Lỗi xoá ChiTietKhuyenMaiHD");
        }
        return false;
    }
    
}