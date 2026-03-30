package HoaDon;

import ChiTietHoaDon.ChiTietHoaDon;
import ChiTietHoaDon.ChiTietHoaDonBUS;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SuaHoaDonGUI extends JFrame {

    JTextField txtNgay = new JTextField(10);
    JTextField txtTenSach = new JTextField(15);
    JTextField txtSoLuong = new JTextField(5);
    JTextField txtDonGia = new JTextField(10);
    JTextField txtTongTien = new JTextField(10);

    JComboBox<String> cbNhanVien;
    JComboBox<String> cbKhachHang;

    JButton btnThemCT = new JButton("Thêm chi tiết");
    JButton btnXoaCT = new JButton("Xóa chi tiết");
    JButton btnTinhTong = new JButton("Tính tổng tiền");
    JButton btnLuu = new JButton("Lưu hóa đơn");

    JTable tableSach;
    JTable tableCTHD;

    DefaultTableModel modelSach;
    DefaultTableModel modelCTHD;

    Connection conn;
    String maHD;

    HoaDonGUI parent;

    public SuaHoaDonGUI(HoaDonGUI parent, String maHD) {
    this.parent = parent;
    this.maHD = maHD;

    setTitle("Sửa hóa đơn");
    setSize(900,600);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/quanlibansach3?useUnicode=true&characterEncoding=UTF-8",
            "root",
            ""
        );
    } catch (Exception e) {
        e.printStackTrace();
    }

    taoGiaoDien();

    // ❗ QUAN TRỌNG: load combo trước
    loadNhanVien();
    loadKhachHang();

    // ❗ rồi mới load hóa đơn
    loadDuLieuHoaDon();

    setVisible(true);
}

    private void taoGiaoDien(){
        Panel pTop = new Panel();
        txtNgay.setEditable(false);
        cbNhanVien = new JComboBox<>();
        cbKhachHang = new JComboBox<>();
        pTop.add(new Label("Nhân viên"));
        pTop.add(cbNhanVien);
        pTop.add(new Label("Khách hàng"));
        pTop.add(cbKhachHang);
        pTop.add(new Label("Ngày"));
        pTop.add(txtNgay);
        add(pTop,BorderLayout.NORTH);

        modelSach = new DefaultTableModel(
                new Object[]{"Mã sách","Tên sách","Đơn giá","Tồn"},0
        );
        tableSach = new JTable(modelSach);
        JScrollPane scrollSach = new JScrollPane(tableSach);

        modelCTHD = new DefaultTableModel(
                new Object[]{"Mã sách","Tên sách","Số lượng","Đơn giá","Thành tiền"},0
        );
        tableCTHD = new JTable(modelCTHD);
        JScrollPane scrollCTHD = new JScrollPane(tableCTHD);

        Panel pCenter = new Panel(new GridLayout(1,2));
        pCenter.add(scrollSach);
        pCenter.add(scrollCTHD);
        add(pCenter,BorderLayout.CENTER);

        Panel pBottom = new Panel(new GridLayout(3,6,10,10));
        pBottom.add(new Label("Tên sách"));
        pBottom.add(txtTenSach);
        pBottom.add(new Label("Số lượng"));
        pBottom.add(txtSoLuong);
        pBottom.add(new Label("Đơn giá"));
        pBottom.add(txtDonGia);
        pBottom.add(btnThemCT);
        pBottom.add(btnXoaCT);
        pBottom.add(btnTinhTong);
        pBottom.add(new Label("Tổng tiền"));
        pBottom.add(txtTongTien);
        pBottom.add(btnLuu);
        add(pBottom,BorderLayout.SOUTH);

       
        loadTableSach();
        suKien();
        Color bg = new Color(245,245,220);

        getContentPane().setBackground(bg);
        tableCTHD.setBackground(Color.WHITE);
    }

    public void loadTableSach(){
        modelSach.setRowCount(0);
        try{
            String sql = "SELECT * FROM sach";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                modelSach.addRow(new Object[]{
                        rs.getString("MaSach"),
                        rs.getString("TenSach"),
                        rs.getDouble("DonGia"),
                        rs.getInt("SoLuongTon")
                });
            }
        }catch(Exception e){
            System.out.println("Lỗi load sách");
        }
    }

    public void loadNhanVien(){
        try{
            String sql = "SELECT MaNhanVien FROM nhanvien";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                cbNhanVien.addItem(rs.getString("MaNhanVien"));
            }
        }catch(Exception e){
            System.out.println("Lỗi load nhân viên");
        }
    }

    public void loadKhachHang(){
        try{
            String sql = "SELECT maKH FROM khachhang";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                cbKhachHang.addItem(rs.getString("maKH"));
            }
        }catch(Exception e){
            System.out.println("Lỗi load khách hàng");
        }
    }

    private void loadDuLieuHoaDon(){
        modelCTHD.setRowCount(0);
        HoaDonBUS hdBUS = new HoaDonBUS();
        HoaDon hd = hdBUS.timKiem(maHD);
        if(hd != null){
            cbNhanVien.setSelectedItem(hd.getManv());
            cbKhachHang.setSelectedItem(hd.getMakh());
            txtNgay.setText(hd.getThoigiantao().toString());
            txtTongTien.setText(String.valueOf(hd.getTongtien()));
        }

        ChiTietHoaDonBUS cthdBUS = new ChiTietHoaDonBUS();
        for(ChiTietHoaDon ct : cthdBUS.getList(maHD)){
            try{
                String sql = "SELECT TenSach FROM sach WHERE MaSach = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ct.getMasach());
                ResultSet rs = ps.executeQuery();
                String tenSach = "";
                if(rs.next()){
                    tenSach = rs.getString("TenSach");
                }
                modelCTHD.addRow(new Object[]{
                    ct.getMasach(),
                    tenSach,
                    ct.getSoluong(),
                    ct.getDongia(),
                    ct.getSoluong() * ct.getDongia()
                });
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void suKien(){
        tableSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                int row = tableSach.getSelectedRow();
                txtTenSach.setText(tableSach.getValueAt(row,1).toString());
                txtDonGia.setText(tableSach.getValueAt(row,2).toString());
            }
        });

        btnThemCT.addActionListener(e->{
            int row = tableSach.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(null,"Chọn sách trước");
                return;
            }
            if(txtSoLuong.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Nhập số lượng");
                return;
            }
            String maSach = tableSach.getValueAt(row,0).toString();
            String tenSach = txtTenSach.getText();
            int soLuong = Integer.parseInt(txtSoLuong.getText());
            double donGia = Double.parseDouble(txtDonGia.getText());
            double thanhTien = soLuong * donGia;
            modelCTHD.addRow(new Object[]{maSach, tenSach, soLuong, donGia, thanhTien});
        });

        btnXoaCT.addActionListener(e->{
            int row = tableCTHD.getSelectedRow();
            if(row >=0){
                modelCTHD.removeRow(row);
            }
        });

        btnTinhTong.addActionListener(e->{
            double tong = 0;
            for(int i=0;i<modelCTHD.getRowCount();i++){
                tong += Double.parseDouble(modelCTHD.getValueAt(i,4).toString());
            }
            txtTongTien.setText(String.valueOf(tong));
        });

        btnLuu.addActionListener(e -> {
            try {
                if(modelCTHD.getRowCount() == 0){
                    JOptionPane.showMessageDialog(null,"Chưa có chi tiết hóa đơn");
                    return;
                }
                if(txtTongTien.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Bấm tính tổng trước");
                    return;
                }

                String maNV = cbNhanVien.getSelectedItem().toString();
                String maKH = cbKhachHang.getSelectedItem().toString();
                LocalDate ngay = LocalDate.parse(txtNgay.getText());
                double tongTien = Double.parseDouble(txtTongTien.getText());

                HoaDonBUS hdBUS = new HoaDonBUS();
                HoaDon hd = new HoaDon(maHD, maKH, maNV, ngay, tongTien);
                
                // Cộng lại tồn kho cho chi tiết cũ
                ChiTietHoaDonBUS cthdBUS = new ChiTietHoaDonBUS();
                for(ChiTietHoaDon ct : cthdBUS.getList(maHD)){
                    String sql = "UPDATE sach SET SoLuongTon = SoLuongTon + ? WHERE MaSach = ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, ct.getSoluong());
                    ps.setString(2, ct.getMasach());
                    ps.executeUpdate();
                }
                
                // Xóa chi tiết cũ
                cthdBUS.xoaTheoMa(maHD);
                
                // Thêm chi tiết mới và trừ tồn kho
                for(int i = 0; i < modelCTHD.getRowCount(); i++){
                    String maSach = modelCTHD.getValueAt(i,0).toString();
                    int soLuong = Integer.parseInt(modelCTHD.getValueAt(i,2).toString());
                    double donGia = Double.parseDouble(modelCTHD.getValueAt(i,3).toString());
                    ChiTietHoaDon ct = new ChiTietHoaDon(maHD, maSach, soLuong, donGia);
                    cthdBUS.them(ct);
                    
                    // Trừ tồn kho
                    String sql = "UPDATE sach SET SoLuongTon = SoLuongTon - ? WHERE MaSach = ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, soLuong);
                    ps.setString(2, maSach);
                    ps.executeUpdate();
                }
                
                if(hdBUS.sua(hd)){
                    JOptionPane.showMessageDialog(null,"Sửa hóa đơn thành công");
                    parent.loadTable();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null,"Lỗi sửa hóa đơn");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,"Lỗi hệ thống");
            }
        });
    }
}