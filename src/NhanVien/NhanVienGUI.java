package NhanVien;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class NhanVienGUI extends JFrame {
    NhanVienBUS bus = new NhanVienBUS();
    // Thêm cột Lương vào bảng
    DefaultTableModel model = new DefaultTableModel(
        new String[]{"Mã NV", "Họ", "Tên", "SĐT", "Địa chỉ", "Email", "Lương"}, 0);
    JTable table = new JTable(model);

    JTextField txtMa = new JTextField();
    JTextField txtHo = new JTextField();
    JTextField txtTen = new JTextField();
    JTextField txtSDT = new JTextField();
    JTextField txtDiaChi = new JTextField();
    JTextField txtEmail = new JTextField();
    JTextField txtLuong = new JTextField(); // Thêm ô nhập lương
    JTextField txtSearch = new JTextField(15);

    JButton btnAdd = new JButton("Thêm Nhân Viên");
    JButton btnDelete = new JButton("Xóa");
    JButton btnUpdate = new JButton("Sửa");
    JButton btnSearch = new JButton("Tìm Kiếm");
    JButton btnReset = new JButton("Làm Mới");

    public NhanVienGUI() {
        // Màu sắc và Font theo phong cách cũ
        Color bgVangCu = new Color(245, 235, 200);   
        Color bgPanelNhe = new Color(250, 243, 220);
        Color chuNauDam = new Color(70, 50, 30); 
        Font fontHeader = new Font("Serif", Font.BOLD, 26);
        Font fontLabels = new Font("Arial", Font.BOLD, 13);

        setTitle("HỆ THỐNG QUẢN LÝ NHÂN VIÊN - NHÀ SÁCH");
        setSize(1100, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(bgVangCu);

        // Header
        JLabel lblHeader = new JLabel("QUẢN LÝ THÔNG TIN NHÂN VIÊN", JLabel.CENTER);
        lblHeader.setFont(fontHeader);
        lblHeader.setForeground(chuNauDam);
        lblHeader.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblHeader, BorderLayout.NORTH);

        // Panel trái (Input)
        JPanel panelLeft = new JPanel(new BorderLayout());
        panelLeft.setBackground(bgVangCu);
        panelLeft.setPreferredSize(new Dimension(400, 0));
        
        // GridLayout(7, 2) vì có thêm trường Lương
        JPanel formInput = new JPanel(new GridLayout(7, 2, 10, 20));
        formInput.setBackground(bgPanelNhe);
        formInput.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(chuNauDam), "Thông tin chi tiết", 
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Serif", Font.BOLD, 16), chuNauDam));

        JLabel[] lbls = {
            new JLabel(" Mã Nhân Viên:"), new JLabel(" Họ NV:"), 
            new JLabel(" Tên NV:"), new JLabel(" Số Điện Thoại:"), 
            new JLabel(" Địa Chỉ:"), new JLabel(" Email:"),
            new JLabel(" Lương:")
        };
        JTextField[] tfts = {txtMa, txtHo, txtTen, txtSDT, txtDiaChi, txtEmail, txtLuong};

        for (int i = 0; i < lbls.length; i++) {
            lbls[i].setForeground(chuNauDam);
            lbls[i].setFont(fontLabels);
            formInput.add(lbls[i]);
            tfts[i].setBackground(Color.WHITE);
            formInput.add(tfts[i]);
        }

        // Panel nút bấm
        JPanel panelControl = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelControl.setBackground(bgVangCu);
        
        btnAdd.setBackground(new Color(100, 120, 80));    btnAdd.setForeground(Color.WHITE);
        btnUpdate.setBackground(new Color(180, 140, 70)); btnUpdate.setForeground(Color.WHITE);
        btnDelete.setBackground(new Color(150, 70, 50));  btnDelete.setForeground(Color.WHITE);
        
        panelControl.add(btnAdd);
        panelControl.add(btnUpdate);
        panelControl.add(btnDelete);

        panelLeft.add(formInput, BorderLayout.CENTER);
        panelLeft.add(panelControl, BorderLayout.SOUTH);

        // Panel phải (Table & Search)
        JPanel panelRight = new JPanel(new BorderLayout(5, 5));
        panelRight.setBackground(bgVangCu);
        
        JPanel panelSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSearch.setBackground(bgVangCu);
        JLabel lblSearch = new JLabel("Tìm theo Mã NV: ");
        lblSearch.setForeground(chuNauDam);
        panelSearch.add(lblSearch);
        panelSearch.add(txtSearch);
        
        btnSearch.setBackground(chuNauDam); 
        btnSearch.setForeground(Color.WHITE);
        btnReset.setBackground(new Color(120, 120, 120)); btnReset.setForeground(Color.WHITE);
        panelSearch.add(btnSearch);
        panelSearch.add(btnReset);

        // Table Custom
        table.setRowHeight(30);
        table.setBackground(bgPanelNhe);
        table.setSelectionBackground(new Color(210, 180, 140));
        table.getTableHeader().setBackground(chuNauDam);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        
        JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.getViewport().setBackground(bgVangCu);
        scrollTable.setBorder(BorderFactory.createLineBorder(chuNauDam));

        panelRight.add(panelSearch, BorderLayout.NORTH);
        panelRight.add(scrollTable, BorderLayout.CENTER);

        add(panelLeft, BorderLayout.WEST);
        add(panelRight, BorderLayout.CENTER);

        // --- XỬ LÝ SỰ KIỆN ---

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int i = table.getSelectedRow();
                if (i >= 0) {
                    txtMa.setText(model.getValueAt(i, 0).toString());
                    txtHo.setText(model.getValueAt(i, 1).toString());
                    txtTen.setText(model.getValueAt(i, 2).toString());
                    txtSDT.setText(model.getValueAt(i, 3).toString());
                    txtDiaChi.setText(model.getValueAt(i, 4).toString());
                    txtEmail.setText(model.getValueAt(i, 5).toString());
                    txtLuong.setText(model.getValueAt(i, 6).toString());
                    txtMa.setEditable(false);
                }
            }
        });

        btnAdd.addActionListener(e -> {
            try {
                NhanVien nv = new NhanVien(
                    txtMa.getText(), txtHo.getText(), txtTen.getText(),
                    txtSDT.getText(), txtDiaChi.getText(), txtEmail.getText(),
                    Double.parseDouble(txtLuong.getText()) // Ép kiểu lương về Double/Int tùy Class NV
                );
                JOptionPane.showMessageDialog(this, bus.themNhanVien(nv));
                loadTable(bus.getDsNhanVien());
                clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng lương!");
            }
        });

        btnUpdate.addActionListener(e -> {
            try {
                NhanVien nv = new NhanVien(
                    txtMa.getText(), txtHo.getText(), txtTen.getText(),
                    txtSDT.getText(), txtDiaChi.getText(), txtEmail.getText(),
                    Double.parseDouble(txtLuong.getText())
                );
                JOptionPane.showMessageDialog(this, bus.suaNhanVien(nv));
                loadTable(bus.getDsNhanVien());
                clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Có lỗi khi cập nhật!");
            }
        });

        btnDelete.addActionListener(e -> {
            String ma = txtMa.getText();
            if(ma.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa!");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Xóa nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, bus.xoaNhanVien(ma)); 
                loadTable(bus.getDsNhanVien());
                clearForm();
            }
        });

        btnSearch.addActionListener(e -> {
            String maCanTim = txtSearch.getText().trim();
            NhanVien kq = bus.timKiemNhanVienTheoMa(maCanTim);
            if (kq != null) {
                ArrayList<NhanVien> dsTimKiem = new ArrayList<>();
                dsTimKiem.add(kq);
                loadTable(dsTimKiem);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã: " + maCanTim);
            }
        });

        btnReset.addActionListener(e -> {
            loadTable(bus.getDsNhanVien());
            clearForm();
        });

        // Load dữ liệu lần đầu khi mở app
        loadTable(bus.getDsNhanVien());
    }

    void loadTable(ArrayList<NhanVien> ds) {
        model.setRowCount(0);
        for (NhanVien nv : ds) {
            model.addRow(new Object[] {
                nv.getMaNV(), nv.getHoNV(), nv.getTenNV(), nv.getSDT(), nv.getDiaChi(), nv.getEmail(), nv.getLuong()
            });
        }
    }

    void clearForm() {
        txtMa.setText("");
        txtHo.setText("");
        txtTen.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtLuong.setText("");
        txtSearch.setText("");
        txtMa.setEditable(true);
        txtMa.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NhanVienGUI().setVisible(true);
        });
    }
}