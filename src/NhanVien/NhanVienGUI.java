package NhanVien;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class NhanVienGUI extends JPanel {

    NhanVienBUS bus = new NhanVienBUS();

    DefaultTableModel model = new DefaultTableModel(
        new String[]{"Mã NV", "Họ", "Tên", "SĐT", "Địa chỉ", "Email", "Lương"}, 0);
    JTable table = new JTable(model);

    JTextField txtMa = new JTextField();
    JTextField txtHo = new JTextField();
    JTextField txtTen = new JTextField();
    JTextField txtSDT = new JTextField();
    JTextField txtDiaChi = new JTextField();
    JTextField txtEmail = new JTextField();
    JTextField txtLuong = new JTextField();
    JTextField txtSearch = new JTextField(15);

    JButton btnAdd = new JButton("Thêm Nhân Viên");
    JButton btnDelete = new JButton("Xóa");
    JButton btnUpdate = new JButton("Sửa");
    JButton btnSearch = new JButton("Tìm Kiếm");
    JButton btnReset = new JButton("Làm Mới");

    public NhanVienGUI() {

        Color bgVangCu = new Color(245, 235, 200);
        Color bgPanelNhe = new Color(250, 243, 220);
        Color chuNauDam = new Color(70, 50, 30);

        Font fontHeader = new Font("Serif", Font.BOLD, 26);
        Font fontLabels = new Font("Arial", Font.BOLD, 13);

        setLayout(new BorderLayout(10, 10));
        setBackground(bgVangCu);

        // HEADER
        JLabel lblHeader = new JLabel("QUẢN LÝ THÔNG TIN NHÂN VIÊN", JLabel.CENTER);
        lblHeader.setFont(fontHeader);
        lblHeader.setForeground(chuNauDam);
        lblHeader.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblHeader, BorderLayout.NORTH);

        // ===== LEFT PANEL =====
        JPanel panelLeft = new JPanel(new BorderLayout());
        panelLeft.setBackground(bgVangCu);
        panelLeft.setPreferredSize(new Dimension(400, 0));

        JPanel formInput = new JPanel(new GridLayout(7, 2, 10, 20));
        formInput.setBackground(bgPanelNhe);
        formInput.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(chuNauDam),
                "Thông tin chi tiết",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Serif", Font.BOLD, 16),
                chuNauDam
        ));

        JLabel[] lbls = {
            new JLabel(" Mã Nhân Viên:"), new JLabel(" Họ NV:"),
            new JLabel(" Tên NV:"), new JLabel(" Số Điện Thoại:"),
            new JLabel(" Địa Chỉ:"), new JLabel(" Email:"),
            new JLabel(" Lương:")
        };

        JTextField[] tfts = {
            txtMa, txtHo, txtTen, txtSDT, txtDiaChi, txtEmail, txtLuong
        };

        for (int i = 0; i < lbls.length; i++) {
            lbls[i].setForeground(chuNauDam);
            lbls[i].setFont(fontLabels);
            formInput.add(lbls[i]);

            tfts[i].setBackground(Color.WHITE);
            formInput.add(tfts[i]);
        }

        JPanel panelControl = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelControl.setBackground(bgVangCu);

        btnAdd.setBackground(new Color(100, 120, 80));
        btnAdd.setForeground(Color.WHITE);

        btnUpdate.setBackground(new Color(180, 140, 70));
        btnUpdate.setForeground(Color.WHITE);

        btnDelete.setBackground(new Color(150, 70, 50));
        btnDelete.setForeground(Color.WHITE);

        panelControl.add(btnAdd);
        panelControl.add(btnUpdate);
        panelControl.add(btnDelete);

        panelLeft.add(formInput, BorderLayout.CENTER);
        panelLeft.add(panelControl, BorderLayout.SOUTH);

        // ===== RIGHT PANEL =====
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

        btnReset.setBackground(new Color(120, 120, 120));
        btnReset.setForeground(Color.WHITE);

        panelSearch.add(btnSearch);
        panelSearch.add(btnReset);

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

        // ===== EVENTS =====

        table.addMouseListener(new java.awt.event.MouseAdapter() {
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
                        Double.parseDouble(txtLuong.getText())
                );
                JOptionPane.showMessageDialog(null, bus.themNhanVien(nv));
                loadTable(bus.getDsNhanVien());
                clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lương phải là số!");
            }
        });

        btnUpdate.addActionListener(e -> {
            try {
                NhanVien nv = new NhanVien(
                        txtMa.getText(), txtHo.getText(), txtTen.getText(),
                        txtSDT.getText(), txtDiaChi.getText(), txtEmail.getText(),
                        Double.parseDouble(txtLuong.getText())
                );
                JOptionPane.showMessageDialog(null, bus.suaNhanVien(nv));
                loadTable(bus.getDsNhanVien());
                clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi cập nhật!");
            }
        });

        btnDelete.addActionListener(e -> {
            String ma = txtMa.getText();
            if (ma.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Chọn nhân viên cần xóa!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Xóa nhân viên này?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, bus.xoaNhanVien(ma));
                loadTable(bus.getDsNhanVien());
                clearForm();
            }
        });

        btnSearch.addActionListener(e -> {
            String ma = txtSearch.getText().trim();
            NhanVien nv = bus.timKiemNhanVienTheoMa(ma);

            if (nv != null) {
                ArrayList<NhanVien> list = new ArrayList<>();
                list.add(nv);
                loadTable(list);
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy!");
            }
        });

        btnReset.addActionListener(e -> {
            loadTable(bus.getDsNhanVien());
            clearForm();
        });

        loadTable(bus.getDsNhanVien());
    }

    void loadTable(ArrayList<NhanVien> ds) {
        model.setRowCount(0);
        for (NhanVien nv : ds) {
            model.addRow(new Object[]{
                nv.getMaNV(), nv.getHoNV(), nv.getTenNV(),
                nv.getSDT(), nv.getDiaChi(),
                nv.getEmail(), nv.getLuong()
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
    }
}
