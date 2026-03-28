package NhanVien;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class NhanVienGUI extends JFrame {
    NhanVienBUS bus = new NhanVienBUS();
    DefaultTableModel model = new DefaultTableModel(new String[]{"Mã NV", "Họ NV", "Tên NV", "SDT", "Địa Chỉ", "Email", "Lương"}, 0);
    JTable table = new JTable(model);

    JTextField txtMa = new JTextField();
    JTextField txtHo = new JTextField();
    JTextField txtTen = new JTextField();
    JTextField txtEmail = new JTextField();
    JTextField txtDiaChi = new JTextField();
    JTextField txtSDT = new JTextField();
    JTextField txtLuong = new JTextField();
    JTextField txtSearch = new JTextField();

    JButton btnAdd = new JButton("Thêm");
    JButton btnUpdate = new JButton("Sửa");
    JButton btnDelete = new JButton("Xóa");
    JButton btnReset = new JButton("Làm mới");
    JButton btnSearch = new JButton("Tìm kiếm");

    public NhanVienGUI() {
        Color bgVangCu = new Color(245, 235, 200);
        Color bgPanelNhe = new Color(250, 243, 220);
        Color chuNauDam = new Color(70, 50, 30);
        Font fontHeader = new Font("Serif", Font.BOLD, 26);
        Font fontLabels = new Font("Arial", Font.BOLD, 13);

        setTitle("HỆ THỐNG QUẢN LÝ THÔNG TIN NHÂN VIÊN");
        setSize(1100, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(bgVangCu);

        JLabel lblHeader = new JLabel("QUẢN LÝ NHÂN VIÊN", JLabel.CENTER);
        lblHeader.setForeground(chuNauDam);
        lblHeader.setFont(fontHeader);
        add(lblHeader, BorderLayout.NORTH);

        JPanel panelLeft = new JPanel(new BorderLayout());
        panelLeft.setBackground(bgVangCu);
        panelLeft.setPreferredSize(new Dimension(400, 100));

        JPanel formInput = new JPanel(new GridLayout(7, 2, 10, 20));
        formInput.setBackground(bgPanelNhe);
        formInput.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(chuNauDam), "Thông tin chi tiết",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Serif", Font.BOLD, 16), chuNauDam));

        JLabel[] lbls = {
            new JLabel("Mã nhân viên:"), new JLabel("Họ nhân viên:"),
            new JLabel("Tên nhân viên:"), new JLabel("Số điện thoại:"),
            new JLabel("Địa chỉ:"), new JLabel("Email:"), new JLabel("Lương:")
        };
        JTextField[] tfts = {txtMa, txtHo, txtTen, txtSDT, txtDiaChi, txtEmail, txtLuong};

        for (int i = 0; i < lbls.length; i++) {
            lbls[i].setForeground(chuNauDam);
            lbls[i].setFont(fontLabels);
            formInput.add(lbls[i]);
            tfts[i].setBackground(Color.WHITE);
            formInput.add(tfts[i]);
        }

        JPanel panelControl = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelControl.add(btnAdd);
        panelControl.add(btnUpdate);
        panelControl.add(btnDelete);

        panelLeft.add(formInput, BorderLayout.CENTER);
        panelLeft.add(panelControl, BorderLayout.SOUTH);

        JPanel panelRight = new JPanel(new BorderLayout(5, 5));
        JPanel panelSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSearch.add(new JLabel("Tìm mã:"));
        panelSearch.add(txtSearch);
        txtSearch.setPreferredSize(new Dimension(150, 25));
        panelSearch.add(btnSearch);
        panelSearch.add(btnReset);

        JScrollPane scrollTable = new JScrollPane(table);
        panelRight.add(panelSearch, BorderLayout.NORTH);
        panelRight.add(scrollTable, BorderLayout.CENTER);

        add(panelLeft, BorderLayout.WEST);
        add(panelRight, BorderLayout.CENTER);

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
            loadTable(bus.getDsNhanVien());
        });

        btnReset.addActionListener(e -> clearForm());

    }

    void loadTable(ArrayList<NhanVien> ds) {
        model.setRowCount(0);
        for (NhanVien nv : ds) {
            model.addRow(new Object[]{
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
        txtMa.setEditable(true);
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        NhanVienGUI gui = new NhanVienGUI();
        gui.setVisible(true);
        });
    }
}