package KhachHang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class KhachHangGUI extends JFrame {
  KhachHangBUS bus = new KhachHangBUS();
  DefaultTableModel model = new DefaultTableModel(
          new String[]{"Mã KH", "Họ", "Tên", "SĐT", "Địa chỉ", "Email"}, 0);
  JTable table = new JTable(model);

  JTextField txtMa = new JTextField(10);
  JTextField txtHo = new JTextField(10);
  JTextField txtTen = new JTextField(10);
  JTextField txtSDT = new JTextField(10);
  JTextField txtDiaChi = new JTextField(10);
  JTextField txtEmail = new JTextField(10);
  
  JTextField txtSearch = new JTextField(10);

  JButton btnAdd = new JButton("Thêm");
  JButton btnDelete = new JButton("Xóa");
  JButton btnUpdate = new JButton("Sửa");
  JButton btnSearch = new JButton("Tìm (Mã KH)");
  JButton btnReset = new JButton("Làm mới");

  public KhachHangGUI() {
    setTitle("Quản Lý Khách Hàng");
    setSize(1200, 800);
    table.setRowHeight(35);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    JPanel formInput = new JPanel(new GridLayout(3, 4, 10, 10));
    formInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    formInput.add(new JLabel("Mã KH:")); formInput.add(txtMa);
    formInput.add(new JLabel("Họ KH:")); formInput.add(txtHo);
    formInput.add(new JLabel("Tên KH:")); formInput.add(txtTen);
    formInput.add(new JLabel("SĐT:")); formInput.add(txtSDT);
    formInput.add(new JLabel("Địa Chỉ:")); formInput.add(txtDiaChi);
    formInput.add(new JLabel("Email:")); formInput.add(txtEmail);

    JPanel panelButtons = new JPanel();
    panelButtons.add(btnAdd);
    panelButtons.add(btnUpdate);
    panelButtons.add(btnDelete);
    panelButtons.add(btnReset);
    panelButtons.add(new JLabel(" Tìm Mã KH:"));
    panelButtons.add(txtSearch);
    panelButtons.add(btnSearch);

    JPanel panelNorth = new JPanel(new BorderLayout());
    panelNorth.add(formInput, BorderLayout.CENTER);
    panelNorth.add(panelButtons, BorderLayout.SOUTH);
    add(panelNorth, BorderLayout.NORTH);
    add(new JScrollPane(table), BorderLayout.CENTER);

    loadTable(bus.getDsKhachHang());
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
          txtMa.setEditable(false);
        }
      }
    });

    btnAdd.addActionListener(e -> {
      KhachHang kh = new KhachHang(
          txtMa.getText(), txtTen.getText(), txtHo.getText(),
          txtSDT.getText(), txtDiaChi.getText(), txtEmail.getText()
      );
      JOptionPane.showMessageDialog(this, bus.themKhachHang(kh));
      loadTable(bus.getDsKhachHang());
      clearForm();
    });

    btnUpdate.addActionListener(e -> {
      KhachHang kh = new KhachHang(
          txtMa.getText(), txtTen.getText(), txtHo.getText(),
          txtSDT.getText(), txtDiaChi.getText(), txtEmail.getText()
      );
      JOptionPane.showMessageDialog(this, bus.suaKhachHang(kh));
      loadTable(bus.getDsKhachHang());
      clearForm();
    });

    btnDelete.addActionListener(e -> {
      String ma = txtMa.getText();
      JOptionPane.showMessageDialog(this, bus.xoaKhachHanh(ma)); 
      loadTable(bus.getDsKhachHang());
      clearForm();
    });

    btnSearch.addActionListener(e -> {
      KhachHang kq = bus.timKiemKhachHangTheoMa(txtSearch.getText());
      if (kq != null) {
        ArrayList<KhachHang> dsTimKiem = new ArrayList<>();
        dsTimKiem.add(kq);
        loadTable(dsTimKiem);
      } else {
        JOptionPane.showMessageDialog(this, "Không tìm thấy mã khách hàng: " + txtSearch.getText());
        loadTable(new ArrayList<>());
      }
    });

    btnReset.addActionListener(e -> {
      loadTable(bus.getDsKhachHang());
      clearForm();
    });
  }

  void loadTable(ArrayList<KhachHang> ds) {
    model.setRowCount(0);
    for (KhachHang kh : ds) {
      model.addRow(new Object[] {
          kh.getMaKH(), kh.getHoKH(), kh.getTenKH(), kh.getSDT(), kh.getDiaChi(), kh.getEmail()
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
    txtSearch.setText("");
    txtMa.setEditable(true);
    txtMa.requestFocus();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      Font font20 = new Font("Arial", Font.PLAIN, 20);
      UIManager.put("Label.font", font20);
      UIManager.put("TextField.font", font20);
      UIManager.put("Button.font", font20);
      UIManager.put("Table.font", font20);
      UIManager.put("TableHeader.font", new Font("Arial", Font.BOLD, 20));
      UIManager.put("OptionPane.messageFont", font20);
      UIManager.put("OptionPane.buttonFont", font20);
      new KhachHangGUI().setVisible(true);
    });
  }
}
