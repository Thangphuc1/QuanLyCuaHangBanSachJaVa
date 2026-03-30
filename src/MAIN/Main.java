package MAIN;

import javax.swing.*;
import java.awt.*;

// IMPORT GUI của bạn
import ThongKe.ThongKeGUI;
import Sach.SachGUI;
import KhachHang.KhachHangGUI;
import NhanVien.NhanVienGUI;
import HoaDon.HoaDonGUI;
import NhaCungCap.NhaCungCapGUI;
import PhieuNhap.PhieuNhapGUI;
import KhuyenMai.KhuyenMaiGUI;
import TacGIa.TacGiaGUI;
import TheLoai.TheLoaiGUI;
import NhaXuatBan.NhaXuatBanGUI;

public class Main extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;
    private JButton selectedButton = null;
    private PhieuNhapGUI panelphieunhap;
    
    public Main() {
        setTitle("Quản Lý Kho");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        add(createSidebar(), BorderLayout.WEST);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        
        // ⭐ ADD TOÀN BỘ GUI VÀO ĐÂY
        contentPanel.add(new ThongKeGUI(), "TongQuan");
        contentPanel.add(new SachGUI(), "Sach");
        contentPanel.add(new KhachHangGUI(), "KhachHang");
        contentPanel.add(new NhanVienGUI(), "NhanVien");
        // contentPanel.add(new HoaDonGUI(), "HoaDon");
        contentPanel.add(new NhaCungCapGUI(this), "NhaCungCap");
        contentPanel.add(new PhieuNhapGUI(this), "NhapHang");
        contentPanel.add(new TacGiaGUI(),"TacGia");
        contentPanel.add(new TheLoaiGUI(),"TheLoai");
        contentPanel.add(new NhaXuatBanGUI(),"NhaXuatBan");
        contentPanel.add(new KhuyenMaiGUI(), "KhuyenMai");

        add(contentPanel, BorderLayout.CENTER);
        cardLayout.show(contentPanel, "TongQuan"); // mac dinh hien overview

        setVisible(true);
    }

    // ================= SIDEBAR =================
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(35, 39, 42));
        sidebar.setPreferredSize(new Dimension(220, 0));

        JLabel title = new JLabel("BookStore");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 10));

        sidebar.add(title);

        JButton btnTongQuan = createMenuButton("Tổng quan");
        JButton btnSach = createMenuButton("Sách");
        JButton btnHoaDon = createMenuButton("Hóa đơn");
        JButton btnNhap = createMenuButton("Nhập hàng");
        JButton btnKhach = createMenuButton("Khách hàng");
        JButton btnNhanVien = createMenuButton("Nhân viên");
        JButton btnKM = createMenuButton("Khuyến mãi");
        JButton btnTacGia = createMenuButton("Tác giả");
        JButton btnTheLoai = createMenuButton("Thể loại");
        JButton btnNhaXuatBan = createMenuButton("Nhà xuất bản");
        JButton btnNCC = createMenuButton("Nhà cung cấp");
        
        // ACTION
        btnTongQuan.addActionListener(e -> switchPanel(btnTongQuan, "TongQuan"));
        btnSach.addActionListener(e -> switchPanel(btnSach, "Sach"));
        btnHoaDon.addActionListener(e -> switchPanel(btnHoaDon, "HoaDon"));
        btnNhap.addActionListener(e -> switchPanel(btnNhap, "NhapHang"));
        btnKhach.addActionListener(e -> switchPanel(btnKhach, "KhachHang"));
        btnNhanVien.addActionListener(e -> switchPanel(btnNhanVien, "NhanVien"));
        btnKM.addActionListener(e -> switchPanel(btnKM, "KhuyenMai"));
        btnTacGia.addActionListener(e -> switchPanel(btnTacGia, "TacGia"));
        btnTheLoai.addActionListener(e -> switchPanel(btnTheLoai, "TheLoai"));
        btnNhaXuatBan.addActionListener(e -> switchPanel(btnNhaXuatBan, "NhaXuatBan"));
        btnNCC.addActionListener(e -> switchPanel(btnNCC, "NhaCungCap"));

        sidebar.add(btnTongQuan);
        sidebar.add(btnSach);
        sidebar.add(btnHoaDon);
        sidebar.add(btnNhap);
        sidebar.add(btnKhach);
        sidebar.add(btnNhanVien);
        sidebar.add(btnKM);
        sidebar.add(btnTacGia);
        sidebar.add(btnTheLoai);
        sidebar.add(btnNhaXuatBan);
        sidebar.add(btnNCC);

        setActiveButton(btnTongQuan);

        return sidebar;
    }

    // ================= SWITCH PANEL =================
    private void switchPanel(JButton btn, String name) {
        setActiveButton(btn);
        cardLayout.show(contentPanel, name);
    }

    // ================= STYLE BUTTON =================
    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(220, 50));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setBackground(new Color(35, 39, 42));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Fixed padding: 20 pixels from the left side.
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (btn != selectedButton) btn.setBackground(new Color(60, 60, 65));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (btn != selectedButton) btn.setBackground(new Color(35, 39, 42));
            }
        });
        return btn;
    }
    // ================= ACTIVE BUTTON =================
    private void setActiveButton(JButton btn) {
        // Reset previous button
        if (selectedButton != null) {
            selectedButton.setBackground(new Color(35, 39, 42));
        }

        // Update current button
        selectedButton = btn;
        btn.setBackground(new Color(24, 144, 255)); 
        // Notice: We do NOT set a MatteBorder here anymore.
    }

    // ================= MAIN =================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
