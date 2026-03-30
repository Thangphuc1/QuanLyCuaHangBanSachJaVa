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

public class PanelHoaDon extends JPanel {
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

    public PanelHoaDon() {
        setLayout(new BorderLayout());
        taoGiaoDien();
        ketNoiDB();
    }

    private void ketNoiDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/quanlibansach3?useUnicode=true&characterEncoding=UTF-8",
                "root",
                ""
            );
            System.out.println("Kết nối DB OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void taoGiaoDien() {
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
        add(pTop, BorderLayout.NORTH);

        // TABLE SÁCH
        modelSach = new DefaultTableModel(
            new Object[]{"Mã sách", "Tên sách", "Đơn giá", "Tồn"}, 0
        );
        tableSach = new JTable(modelSach);
        JScrollPane scrollSach = new JScrollPane(tableSach);

        // TABLE CHI TIẾT HÓA ĐƠN
        modelCTHD = new DefaultTableModel(
            new Object[]{"Mã sách", "Tên sách", "Số lượng", "Đơn giá", "Thành tiền"}, 0
        );
        tableCTHD = new JTable(modelCTHD);
        JScrollPane scrollCTHD = new JScrollPane(tableCTHD);

        Panel pCenter = new Panel(new GridLayout(1, 2));
        pCenter.add(scrollSach);
        pCenter.add(scrollCTHD);
        add(pCenter, BorderLayout.CENTER);

        // PANEL DƯỚI
        Panel pBottom = new Panel(new GridLayout(3, 6, 10, 10));
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
        add(pBottom, BorderLayout.SOUTH);

        loadNhanVien();
        loadKhachHang();
        loadTableSach();
        suKien();
        Color bg = new Color(245, 245, 220);
        setBackground(bg);
        pTop.setBackground(bg);
        pCenter.setBackground(bg);
        pBottom.setBackground(bg);
    }

    public void loadTableSach() {
        modelSach.setRowCount(0);
        try {
            String sql = "SELECT * FROM sach";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                modelSach.addRow(new Object[]{
                    rs.getString("MaSach"),
                    rs.getString("TenSach"),
                    rs.getDouble("DonGia"),
                    rs.getInt("SoLuongTon")
                });
            }
        } catch (Exception e) {
            System.out.println("Lỗi load sách");
        }
    }

    public void loadNhanVien() {
        try {
            String sql = "SELECT MaNhanVien FROM nhanvien";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cbNhanVien.addItem(rs.getString("MaNhanVien"));
            }
        } catch (Exception e) {
            System.out.println("Lỗi load nhân viên");
        }
    }

    public void loadKhachHang() {
        try {
            String sql = "SELECT maKH FROM khachhang";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cbKhachHang.addItem(rs.getString("maKH"));
            }
        } catch (Exception e) {
            System.out.println("Lỗi load khách hàng");
        }
    }

    private void suKien() {
        // CLICK SÁCH
        tableSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // ...
            }
        });
        // ...
    }
}
