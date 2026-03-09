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
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HoaDonGUI extends JFrame {
    
    HoaDonBUS hdBUS= new HoaDonBUS();
    ChiTietHoaDonBUS cthdBUS= new ChiTietHoaDonBUS();

    JTable tableHD;
    JTable tableCTHD;

    DefaultTableModel modelHD;
    DefaultTableModel modelCTHD;

    // ===== PANEL =====
    Panel pTitle = new Panel();
    Panel pChucNang = new Panel(new GridLayout(1,6,10,10));
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

    public HoaDonGUI(){

        setTitle("Quản lý hóa đơn");
        setSize(1100,700);
        setLayout(new BorderLayout());

        taoGiaoDien();

        setVisible(true);
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
        pTitle.setBackground(new Color(0,102,153));
        lbTitle.setForeground(Color.WHITE);

        btnThem.setBackground(new Color(40,167,69));
        btnSua.setBackground(new Color(255,193,7));
        btnXoa.setBackground(new Color(220,53,69));
        btnTim.setBackground(new Color(23,162,184));

        btnThem.setForeground(Color.WHITE);
        btnXoa.setForeground(Color.WHITE);
        btnTim.setForeground(Color.WHITE);

        loadTable();
        nutSuKien();
    }

    public void loadTable(){
        modelHD.setRowCount(0);
        for(HoaDon hd: hdBUS.getList()){
             modelHD.addRow(new Object[]{
                 hd.getMaHD(),
                 hd.getMaKH(),
                 hd.getMaNV(),
                 hd.getNgayLap(),
                 hd.getTongTien()
                 });
        }
 
    }
    
    public void loadTableCTHD(String mahd){
        modelCTHD.setRowCount(0);
        for(ChiTietHoaDon  cthd : cthdBUS.getList(mahd)){
            modelCTHD.addRow(new Object[]{
                cthd.getMaHD(),
                cthd.getMaSach(),
                cthd.getSoLuong(),
                cthd.getDonGia(),
                cthd.getThanhTien()
            });
        }
        
    }
    

    
    
    
    
    public void nutSuKien(){
        // click hóa đơn thì hiển thị chi tiết hóa đơn
        tableHD.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int row= tableHD.getSelectedRow();
                String mahd=tableHD.getValueAt(row, 0).toString();
                loadTableCTHD(mahd);
            }
            
        });
            
        btnThem.addActionListener(e->{
            new ThemHoaDonGUI(this);
        });
        btnSua.addActionListener(e->{
            
        });
        btnXoa.addActionListener(e->{
            
        });
        btnTim.addActionListener(e->{
            
        });
    }

    public static void main(String[] args){

        new HoaDonGUI();

    }

}