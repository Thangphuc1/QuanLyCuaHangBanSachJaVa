package HoaDon;

import ChiTietHoaDon.ChiTietHoaDon;
import ChiTietHoaDon.ChiTietHoaDonBUS;
import KhuyenMai.KhuyenMai;
import KhuyenMai.KhuyenMaiBUS;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ThemHoaDonGUI extends JFrame {

    JTextField txtNgay = new JTextField(10);
    JTextField txtTenSach = new JTextField(15);
    JTextField txtSoLuong = new JTextField(5);
    JTextField txtDonGia = new JTextField(10);
    JTextField txtTongTien = new JTextField(10);

    JComboBox<String> cbNhanVien;
    JComboBox<String> cbKhachHang;
    JTextField txtMaKM;

    JButton btnThemCT = new JButton("Thêm chi tiết");
    JButton btnXoaCT = new JButton("Xóa chi tiết");
    JButton btnTinhTong = new JButton("Tính tổng tiền");
    JButton btnLuu = new JButton("Lưu hóa đơn");

    JTable tableSach;
    JTable tableCTHD;

    DefaultTableModel modelSach;
    DefaultTableModel modelCTHD;

    Connection conn;

    public ThemHoaDonGUI() {

        setTitle("Thêm hóa đơn");
        setSize(950,600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

    
        taoGiaoDien();

       
        setVisible(true);
    }
    
    
    HoaDonGUI parent;

    public ThemHoaDonGUI(HoaDonGUI parent){

        this.parent = parent;

        setTitle("Thêm hóa đơn");
        setSize(900,600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        // 🔥 KẾT NỐI DB
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/quanlibansach?useUnicode=true&characterEncoding=UTF-8",
            "root",
            "Thang123@"
        );

        System.out.println("Kết nối DB OK");
    } catch (Exception e) {
        e.printStackTrace();
    }


        taoGiaoDien();

        setVisible(true);
    }

    private void taoGiaoDien(){

        Panel pTop = new Panel();

        txtNgay.setText(LocalDate.now().toString());
        txtNgay.setEditable(false);

        cbNhanVien = new JComboBox<>();
        cbKhachHang = new JComboBox<>();
        txtMaKM = new JTextField(10);

        pTop.add(new Label("Nhân viên"));
        pTop.add(cbNhanVien);

        pTop.add(new Label("Khách hàng"));
        pTop.add(cbKhachHang);

        pTop.add(new Label("Mã khuyến mãi"));
        pTop.add(txtMaKM);

        pTop.add(new Label("Ngày"));
        pTop.add(txtNgay);

        add(pTop,BorderLayout.NORTH);

        // TABLE SÁCH
        modelSach = new DefaultTableModel(
                new Object[]{"Mã sách","Tên sách","Đơn giá","Tồn"},0
        );

        tableSach = new JTable(modelSach);
        JScrollPane scrollSach = new JScrollPane(tableSach);

        // TABLE CHI TIẾT HÓA ĐƠN
        modelCTHD = new DefaultTableModel(
                new Object[]{"Mã sách","Tên sách","Số lượng","Đơn giá","Thành tiền"},0
        );

        tableCTHD = new JTable(modelCTHD);
        JScrollPane scrollCTHD = new JScrollPane(tableCTHD);

        Panel pCenter = new Panel(new GridLayout(1,2));

        pCenter.add(scrollSach);
        pCenter.add(scrollCTHD);

        add(pCenter,BorderLayout.CENTER);

        // PANEL DƯỚI
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

        loadNhanVien();
        loadKhachHang();
        loadTableSach();

        suKien();
        Color bg = new Color(245,245,220);
        getContentPane().setBackground(bg);
        pTop.setBackground(bg);
        pCenter.setBackground(bg);
        pBottom.setBackground(bg);
    }

    // LOAD SÁCH
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

    // LOAD NHÂN VIÊN
    public void loadNhanVien(){

        try{
            String sql = "SELECT manv FROM nhanvien";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                cbNhanVien.addItem(rs.getString("manv"));
            }
        }catch(Exception e){
            System.out.println("Lỗi load nhân viên");
            e.printStackTrace();
        }
    }

    // LOAD KHÁCH HÀNG
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

    private void suKien(){

        // CLICK SÁCH
        tableSach.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt){

                int row = tableSach.getSelectedRow();

                txtTenSach.setText(
                        tableSach.getValueAt(row,1).toString()
                );

                txtDonGia.setText(
                        tableSach.getValueAt(row,2).toString()
                );
            }
        });

        // THÊM CHI TIẾT
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
            int tonKho = Integer.parseInt(tableSach.getValueAt(row,3).toString());

            if(soLuong > tonKho){
                JOptionPane.showMessageDialog(null,"Số lượng vượt quá tồn kho (" + tonKho + ")");
                return;
            }

            double donGia = Double.parseDouble(txtDonGia.getText());

            double thanhTien = soLuong * donGia;

            modelCTHD.addRow(new Object[]{
                    maSach,
                    tenSach,
                    soLuong,
                    donGia,
                    thanhTien
            });

        });

        // XÓA CHI TIẾT
        btnXoaCT.addActionListener(e->{

            int row = tableCTHD.getSelectedRow();

            if(row >=0){
                modelCTHD.removeRow(row);
            }

        });

        // TÍNH TỔNG TIỀN
        btnTinhTong.addActionListener(e->{

            double tong = 0;

            for(int i=0;i<modelCTHD.getRowCount();i++){

                tong += Double.parseDouble(
                        modelCTHD.getValueAt(i,4).toString()
                );

            }

            // ÁP DỤNG KHUYẾN MÃI
            String maKM = txtMaKM.getText().trim();
            if(!maKM.isEmpty()){

                KhuyenMaiBUS kmBUS = new KhuyenMaiBUS();
                for(KhuyenMai km : kmBUS.getAll()){
                    if(km.getMaKM().equals(maKM)){

                        LocalDate today = LocalDate.now();
                        if(today.isAfter(km.getNgayBD().minusDays(1)) && today.isBefore(km.getNgayKT().plusDays(1)) && tong >= km.getDKTT()){

                            try{
                                double phanTram = Double.parseDouble(km.getGhichu().replace("%",""));
                                tong = tong * (100 - phanTram) / 100;
                            } catch(NumberFormatException ex){
                                // Nếu không phải số, bỏ qua
                            }
                        }
                        break;
                    }
                }
            }

            txtTongTien.setText(String.valueOf(tong));
        });

        // LƯU HÓA ĐƠN
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


        Object nvObj = cbNhanVien.getSelectedItem();
        Object khObj = cbKhachHang.getSelectedItem();
        if (nvObj == null || khObj == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn Nhân viên và Khách hàng!");
            return;
        }
        String maNV = nvObj.toString().trim();
        String maKH = khObj.toString().trim();
        LocalDate ngay = LocalDate.parse(txtNgay.getText());
        double tongTien = Double.parseDouble(txtTongTien.getText());

        HoaDonBUS hdBUS = new HoaDonBUS();
        String maHD = hdBUS.taoMaHD();

        // 👉 1. LƯU HÓA ĐƠN
        HoaDon hd = new HoaDon(maHD, maKH, maNV, ngay, tongTien);

       

        if(hdBUS.them(hd)){

            // 👉 2. LƯU CHI TIẾT
            ChiTietHoaDonBUS cthdBUS = new ChiTietHoaDonBUS();

            for(int i = 0; i < modelCTHD.getRowCount(); i++){

                String maSach = modelCTHD.getValueAt(i,0).toString();
                int soLuong = Integer.parseInt(modelCTHD.getValueAt(i,2).toString());
                double donGia = Double.parseDouble(modelCTHD.getValueAt(i,3).toString());

                ChiTietHoaDon ct = new ChiTietHoaDon(maHD, maSach, soLuong, donGia);

                cthdBUS.them(ct);

                // 👉 3. TRỪ KHO
                String sql = "UPDATE sach SET SoLuongTon = SoLuongTon - ? WHERE MaSach = ?";
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setInt(1, soLuong);
                ps.setString(2, maSach);

                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(null,"Lưu hóa đơn thành công");

            // 👉 refresh trang chính
            if(parent != null){
                parent.loadTable();
            }

            this.dispose();

        } else {
            JOptionPane.showMessageDialog(null,"Lỗi thêm hóa đơn");
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi hệ thống: " + ex.getMessage());
    }

});

    }

}