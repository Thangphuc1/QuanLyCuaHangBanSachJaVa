/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HoaDon;

/**
 *
 * @author Asus
 */
import ChiTietHoaDon.ChiTietHoaDon;
import ChiTietHoaDon.ChiTietHoaDonBUS;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import javax.swing.JPanel;

public class HoaDonGUI extends JPanel {
    
    HoaDonBUS hdBUS= new HoaDonBUS();
    ChiTietHoaDonBUS cthdBUS= new ChiTietHoaDonBUS();
    Connection conn;

    JTable tableHD;
    JTable tableCTHD;

    DefaultTableModel modelHD;
    DefaultTableModel modelCTHD;

    // ===== PANEL =====
    Panel pTitle = new Panel();
    Panel pChucNang = new Panel(new GridLayout(1,7,10,10));
    Panel pTableHD = new Panel(new BorderLayout());
    Panel pTableCTHD = new Panel(new BorderLayout());

    Panel pCenter = new Panel(new BorderLayout());

    // ===== LABEL =====
    Label lbTitle = new Label("QUẢN LÝ HÓA ĐƠN");

    // ===== BUTTON =====
    Button btnThem = new Button("Thêm");
    Button btnSua = new Button("Sửa");
    Button btnXoa = new Button("Xóa");
    Button btnTim = new Button("Tìm kiếm");
    Button btnClear = new Button("Clear");
    Button btnExit = new Button("Exit");

    // ===== TEXT FIELD =====
    JTextField txtTimKiem = new JTextField(20);

    public HoaDonGUI(){
        setLayout(new BorderLayout());
        // Kết nối DB
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/quanlibansach?useUnicode=true&characterEncoding=UTF-8",
                "root",
                "Thang123@"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        taoGiaoDien();
    }

    private void taoGiaoDien(){

        // ===== TITLE =====
        lbTitle.setAlignment(Label.CENTER);
        lbTitle.setFont(new Font("Arial",Font.BOLD,28));
        pTitle.add(lbTitle);

        // ===== NÚT CHỨC NĂNG =====
        pChucNang.add(btnThem);
        pChucNang.add(btnSua);
        pChucNang.add(btnXoa);
        pChucNang.add(txtTimKiem);
        pChucNang.add(btnTim);
        pChucNang.add(btnClear);
        pChucNang.add(btnExit);

        pChucNang.setPreferredSize(new Dimension(0,40));

        // ===== TABLE HÓA ĐƠN =====
        modelHD = new DefaultTableModel(
                new Object[]{"Mã HD","Mã KH","Mã NV","Ngày","Tổng tiền"},0
        );

        tableHD = new JTable(modelHD);
        JScrollPane scrollHD = new JScrollPane(tableHD);

        pTableHD.add(pChucNang,BorderLayout.NORTH);
        pTableHD.add(scrollHD,BorderLayout.CENTER);

        // ===== TABLE CHI TIẾT =====
        modelCTHD = new DefaultTableModel(
                new Object[]{"mã HD","Mã sách","Số lượng","Đơn giá","Thành tiền"},0
        );

        tableCTHD = new JTable(modelCTHD);
        JScrollPane scrollCTHD = new JScrollPane(tableCTHD);

        pTableCTHD.add(scrollCTHD);

        // kích thước 2/5
        pTableCTHD.setPreferredSize(new Dimension(0,200));

        // ===== CENTER =====
        pCenter.add(pTableHD,BorderLayout.CENTER);
        pCenter.add(pTableCTHD,BorderLayout.SOUTH);

        add(pTitle,BorderLayout.NORTH);
        add(pCenter,BorderLayout.CENTER);

        // ===== MÀU =====
        pTitle.setBackground(new Color(245,245,220));
        lbTitle.setForeground(Color.BLACK);

        btnThem.setBackground(new Color(40,167,69));
        btnSua.setBackground(new Color(255,193,7));
        btnXoa.setBackground(new Color(220,53,69));
        btnTim.setBackground(new Color(23,162,184));

        btnThem.setForeground(Color.WHITE);
        btnXoa.setForeground(Color.WHITE);
        btnTim.setForeground(Color.WHITE);

        loadTable();
        nutSuKien();
              
        Color bg = new Color(245,245,220);

        // set nền toàn bộ

         this.setBackground(bg);
        
         pChucNang.setBackground(bg);
         pTableHD.setBackground(bg);
         pTableCTHD.setBackground(bg);
         pCenter.setBackground(bg);
    
    }

    public void loadTable(){
        modelHD.setRowCount(0);
        for(HoaDon hd: hdBUS.getList()){
             modelHD.addRow(new Object[]{
                 hd.getMahoadon(),
                 hd.getMakh(),
                 hd.getManv(),
                 hd.getThoigiantao(),
                 hd.getTongtien()
                 });
        }
 
    }
    
    public void loadTableCTHD(String mahd){
        modelCTHD.setRowCount(0);
        for(ChiTietHoaDon  cthd : cthdBUS.getList(mahd)){
            modelCTHD.addRow(new Object[]{
                cthd.getMahoadon(),
                cthd.getMasach(),
                cthd.getSoluong(),
                cthd.getDongia(),
                cthd.getSoluong() * cthd.getDongia()
            });
        }
        
    }
    

    
    
    
    
    public void nutSuKien(){
        // click hóa đơn thì hiển thị chi tiết hóa đơn
        tableHD.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int row= tableHD.getSelectedRow();
                if(row == -1) return;
                String mahd=tableHD.getValueAt(row, 0).toString();
                loadTableCTHD(mahd);
            }
            
        });
            
        btnThem.addActionListener(e->{
            new ThemHoaDonGUI(this);
        });
        
        
       btnSua.addActionListener(e -> {
    
           int row = tableHD.getSelectedRow();

    
           if(row == -1){
        
               JOptionPane.showMessageDialog(this,"Chọn hóa đơn cần sửa");
        
               return;
    
           }

    
           String maHD = tableHD.getValueAt(row,0).toString();

   
           new SuaHoaDonGUI(this, maHD);

       });
        
        btnXoa.addActionListener(e->{

     
            int row = tableHD.getSelectedRow();   
            if(row == -1){       
                JOptionPane.showMessageDialog(this,"Chọn hóa đơn cần xóa");       
                return;
   
            }
            String maHD = modelHD.getValueAt(row,0).toString();
            int confirm = JOptionPane.showConfirmDialog(this,"Xóa hóa đơn này?");
            if(confirm == JOptionPane.YES_OPTION){
                // Cập nhật tồn kho trước khi xóa
                try {
                    ChiTietHoaDonBUS cthdBUS = new ChiTietHoaDonBUS();
                    for(ChiTietHoaDon ct : cthdBUS.getList(maHD)){
                        String sql = "UPDATE sach SET SoLuongTon = SoLuongTon + ? WHERE MaSach = ?";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setInt(1, ct.getSoluong());
                        ps.setString(2, ct.getMasach());
                        ps.executeUpdate();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                cthdBUS.xoaTheoMa(maHD);
                hdBUS.xoa(maHD);
                loadTable();
                modelCTHD.setRowCount(0);
                JOptionPane.showMessageDialog(this,"Đã xóa hóa đơn");   
            }
        });
        btnTim.addActionListener(e->{
            String keyword = txtTimKiem.getText().trim();
            if(keyword.isEmpty()){
                JOptionPane.showMessageDialog(this,"Nhập từ khóa tìm kiếm");
                return;
            }
            modelHD.setRowCount(0);
            for(HoaDon hd: hdBUS.getList()){
                if(hd.getMahoadon().toLowerCase().contains(keyword.toLowerCase()) ||
                   hd.getMakh().toLowerCase().contains(keyword.toLowerCase()) ||
                   hd.getManv().toLowerCase().contains(keyword.toLowerCase())){
                    modelHD.addRow(new Object[]{
                        hd.getMahoadon(),
                        hd.getMakh(),
                        hd.getManv(),
                        hd.getThoigiantao(),
                        hd.getTongtien()
                    });
                }
            }
        });
        
        btnExit.addActionListener(e -> {    
            // dispose(); // Removed because HoaDonGUI is now a JPanel
        });
        
        btnClear.addActionListener(e -> {
            loadTable();
            modelCTHD.setRowCount(0);
        });
        
        
        
    }

    

}