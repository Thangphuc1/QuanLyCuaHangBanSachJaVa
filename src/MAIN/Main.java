package MAIN;

import javax.swing.*;
import java.awt.*;

// ===== IMPORT GUI =====
import ThongKe.ThongKeGUI;
import NhaCungCap.NhaCungCapGUI;
import NhanVien.NhanVienGUI;
import KhachHang.KhachHangGUI;
import Sach.SachGUI;
import HoaDon.HoaDonGUI;
import PhieuNhap.PhieuNhapGUI;
import KhuyenMai.KhuyenMaiGUI;

public class Main extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;

    public Main() {
        setTitle("Quản Lý Cửa Hàng Sách");
        setSize(1250, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== SIDEBAR =====
        add(createSidebar(), BorderLayout.WEST);

        // ===== CONTENT =====
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        add(contentPanel, BorderLayout.CENTER);

        // ===== ADD PANEL =====
        contentPanel.add(new ThongKeGUI(), "THONGKE");
        contentPanel.add(new NhaCungCapGUI(this), "NHACUNGCAP");
        //contentPanel.add(new NhanVienGUI(), "NHANVIEN");
        //contentPanel.add(new KhachHangGUI(), "KHACHHANG");
        contentPanel.add(new SachGUI(), "SACH");
        //contentPanel.add(new HoaDonGUI(), "HOADON");
        //contentPanel.add(new PhieuNhapGUI(), "PHIEUNHAP");
        //contentPanel.add(new KhuyenMaiGUI(), "KHUYENMAI");

        // ===== DEFAULT =====
        cardLayout.show(contentPanel, "THONGKE");

        setVisible(true);
    }

    // ===== SIDEBAR =====
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(43, 43, 43));
        sidebar.setPreferredSize(new Dimension(220, 0));

        // ===== LOGO =====
        sidebar.add(Box.createVerticalStrut(20));

        JLabel title = new JLabel("BookStore");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Noto Sans", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sub = new JLabel("Quản Lý");
        sub.setForeground(new Color(180, 180, 180));
        sub.setFont(new Font("Noto Sans", Font.PLAIN, 14));
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(title);
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(sub);
        sidebar.add(Box.createVerticalStrut(30));

        // ===== MENU =====
        sidebar.add(createMenuButton("Tổng quan", "THONGKE"));
        sidebar.add(createMenuButton("Sách", "SACH"));
        sidebar.add(createMenuButton("Hóa đơn", "HOADON"));
        sidebar.add(createMenuButton("Nhập hàng", "PHIEUNHAP"));
        sidebar.add(createMenuButton("Khách hàng", "KHACHHANG"));
        sidebar.add(createMenuButton("Nhân viên", "NHANVIEN"));
        sidebar.add(createMenuButton("Khuyến mãi", "KHUYENMAI"));
        sidebar.add(createMenuButton("Nhà cung cấp", "NHACUNGCAP"));

        sidebar.add(Box.createVerticalGlue());

        return sidebar;
    }

    // ===== BUTTON =====
    private JButton createMenuButton(String text, String panelName) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(220, 45));
        btn.setBackground(new Color(43, 43, 43));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFont(new Font("Noto Sans", Font.PLAIN, 14));

        btn.addActionListener(e -> cardLayout.show(contentPanel, panelName));

        // Hover
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(70, 70, 70));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(43, 43, 43));
            }
        });

        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}

