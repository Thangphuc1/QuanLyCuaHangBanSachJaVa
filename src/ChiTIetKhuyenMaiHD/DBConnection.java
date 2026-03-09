/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChiTIetKhuyenMaiHD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER NITRO
 */
public class DBConnection {
    public static Connection getConnection(){
        try{
            String url = "jdbc:mysql://localhost:3306/qlsach";
            String user = "root";
            String password = "";
            
            return DriverManager.getConnection(url, user, password);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Lỗi kết nối cơ sở dữ liệu CTKMHD");
        }
        return null;
    }
}
