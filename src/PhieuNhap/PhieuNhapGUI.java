/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PhieuNhap;
import PhieuNhap.*;
import ChiTietPhieuNhap.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class PhieuNhapGUI extends JPanel {
    private  PhieuNhapBUS pnbus=new PhieuNhapBUS();
    private  ChiTietPhieuNhapBUS ctbus=new ChiTietPhieuNhapBUS();
    private  JTable tbpn;
    private  JTable tbct;
    private DefaultTableModel pntablemodel;
    private DefaultTableModel cttablemodel;
    
    private JLabel lbtimkiem; 
    private JTextField txtTimKiem;
    private JButton btnThem,btnXoa,btnSua,btnTimKiem,btnTatCa;
    
    public PhieuNhapGUI() {
        
     
    }
    public void TaoPanel() {
      JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 250, 205)); // Màu vàng nhạt
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(255, 250, 205));
        
         lbtimkiem=new JLabel();
         lbtimkiem.setFont(new Font("Arial", Font.BOLD, 13));
        txtTimKiem = new JTextField(15);
        txtTimKiem.setPreferredSize(new Dimension(200, 30));
        
        leftPanel.add(lbtimkiem);
        leftPanel.add(txtTimKiem);
        
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(new Color(255, 250, 205)); // ⭐ Thêm: Màu nền cho right panel
    
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnTimKiem = new JButton("Tìm Kiếm"); 
        btnTatCa = new JButton("Tất Cả");
    
    JButton[] buttons = {btnThem, btnSua, btnXoa, btnTimKiem,btnTatCa};
    for (JButton btn : buttons) {
        btn.setPreferredSize(new Dimension(100, 35));
        btn.setBackground(new Color(230, 180, 0)); // Màu vàng đậm
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(200, 150, 0), 2));
    }

    rightPanel.add(btnThem);
    rightPanel.add(btnSua);
    rightPanel.add(btnXoa);
    rightPanel.add(btnTimKiem);
    rightPanel.add(btnTatCa);
    }
    public void TaoPaneBangPN() {
        
    }
    public void TaoPanelBangCT() {
        
    }
    public void NutThemPhieuNhap() {
        
    }
    public void NutXoaPhieuNhap() {
        
    }
    public void NutSuaPhieuNhap() {
        
    }
    public void NutTimKiem() {
        
    }
    public void NutLoaiLai() {
        
    }
    
    
}
