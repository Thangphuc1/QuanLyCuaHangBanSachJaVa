
package NhaCungCap;

import NhaCungCap.NhaCungCap;
import NhaCungCap.NhaCungCapDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class NhaCungCapGUI extends JPanel{
    private NhaCungCapBUS bus = new NhaCungCapBUS();
    private JTable tableNCC;
    private DefaultTableModel tableModel;
     private JTextField  txtTimKiem;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnTatCa;
    private JLabel lbtimkiem;       
    private JFrame parentFrame;
    

    public NhaCungCapGUI(JFrame parent) {
        setLayout(new BorderLayout()); // ⭐ Set layout cho panel
        setBackground(new Color(230, 230, 230)); // Màu xám nhạt
         
        // Panel nhập liệu
        JPanel panelNhapLieu = taoPanel();
       
        add(panelNhapLieu, BorderLayout.NORTH);
        
        // Panel bảng
        JPanel panelBang = taoPanelBang();
        add(panelBang, BorderLayout.CENTER);
        this.parentFrame = parent; // ⭐ Lưu reference
        
        
       

        // Load dữ liệu ban đầu
        loadDuLieu();
        
    }
//    public NhaCungCapGUI() {
//        this(null);
//    }
//    private JPanel MenuPanel() {
//        
//    }
    private JPanel taoPanel() {
    JPanel panel = new JPanel(new BorderLayout());
     panel.setBackground(new Color(255, 250, 205)); // Màu vàng nhạt
     panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
   
    JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
     leftPanel.setBackground(new Color(255, 250, 205));
     
    lbtimkiem = new JLabel("Tìm kiếm");
    lbtimkiem.setFont(new Font("Arial", Font.BOLD, 13));
    txtTimKiem = new JTextField(15);
    txtTimKiem.setPreferredSize(new Dimension(200, 30));
     
    leftPanel.add(lbtimkiem);
    leftPanel.add(txtTimKiem);
    
    
    JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    rightPanel.setBackground(new Color(255, 250, 205)); // ⭐ Thêm: Màu nền cho right panel
    
    btnThem = new JButton("Thêm");
    btnSua = new JButton("Sửa");
    btnXoa = new JButton("Xóa");
    btnTimKiem = new JButton("Tìm Kiếm"); 
    btnTatCa = new JButton("Tất Cả");
    
    JButton[] buttons = {btnThem, btnSua, btnXoa, btnTimKiem,btnTatCa};
    for (JButton btn : buttons) {
        btn.setPreferredSize(new Dimension(100, 35));
        btn.setBackground(new Color(230, 180, 0)); // Màu vàng đậm
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(200, 150, 0), 2));
    }

    rightPanel.add(btnThem);
    rightPanel.add(btnSua);
    rightPanel.add(btnXoa);
    rightPanel.add(btnTimKiem);
    rightPanel.add(btnTatCa);

    
    panel.add(leftPanel, BorderLayout.WEST);
    panel.add(rightPanel, BorderLayout.EAST);

   
    btnThem.addActionListener(e -> themNhaCungCap());
    btnSua.addActionListener(e -> suaNhaCungCap());
    btnXoa.addActionListener(e -> xoaNhaCungCap());
    btnTimKiem.addActionListener(e -> timKiem());
    btnTatCa.addActionListener(e -> loadDuLieu());

    return panel;
}

    private JPanel taoPanelBang() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(200, 150, 0), 3),"Danh Sách Nhà Cung Cấp",javax.swing.border.TitledBorder.LEFT,javax.swing.border.TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14),
            new Color(80, 80, 80)
        ));
        panel.setBackground(new Color(200, 200, 200)); // Màu xám
        panel.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(new Color(200, 150, 0), 3),BorderFactory.createEmptyBorder(10, 10, 10, 10)));  
         
         
        

        String[] columns = {"Mã NCC", "Tên NCC", "Địa Chỉ", "Số Điện Thoại", "Email"};
        tableModel = new DefaultTableModel(columns, 0);
        tableNCC = new JTable(tableModel);
        tableNCC.setBackground(Color.WHITE);
        tableNCC.setFont(new Font("Arial", Font.PLAIN, 12));
        tableNCC.getTableHeader().setBackground(new Color(230, 180, 0)); // Header màu vàng
        tableNCC.getTableHeader().setForeground(Color.BLACK);
        tableNCC.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tableNCC.setRowHeight(25);
        

        JScrollPane scrollPane = new JScrollPane(tableNCC);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Bắt sự kiện click vào bảng
//        tableNCC.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int row = tableNCC.getSelectedRow();
//                if (row != -1) {
//                    txtMaNCC.setText(tableModel.getValueAt(row, 0).toString());
//                    txtTenNCC.setText(tableModel.getValueAt(row, 1).toString());
//                    txtDiaChi.setText(tableModel.getValueAt(row, 2).toString());
//                    txtSoDienThoai.setText(tableModel.getValueAt(row, 3).toString());
//                    txtEmail.setText(tableModel.getValueAt(row, 4).toString());
//                }
//            }
//        });

        return panel;
    }
    private void loadDuLieu() {
        tableModel.setRowCount(0);
        ArrayList<NhaCungCap> danhSach = bus.getNhaCungCap();
        for (NhaCungCap ncc : danhSach) {
            Object[] row = {
                ncc.getMaNCC(),
                ncc.getTenNCC(),
                ncc.getDiaChi(),
                ncc.getSoDienThoai(),
                ncc.getEmail()
            };
            tableModel.addRow(row);
        }
    }

    private void themNhaCungCap() {
        JFrame frame = parentFrame != null ? parentFrame : (JFrame) SwingUtilities.getWindowAncestor(this);
        
        if (frame == null) {
            JOptionPane.showMessageDialog(this, "Lỗi: Không thể tạo dialog!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JDialog dialog = new JDialog( frame, "Thêm Mới", true);
        dialog.setSize(700, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        
        JPanel p1=new JPanel();
        p1.setBackground(new Color(230, 180, 0)); // Màu vàng đậm
         p1.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20)); // ⭐ Padding nhỏ gọn
        
        JPanel p2=new JPanel();
        p2.setLayout(new GridLayout(10,1));
        p2.setBackground(new Color(200, 200, 200)); // Màu xám
       p2.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40)); // ⭐ Tăng padding
        JPanel p3=new JPanel();
        p3.setBackground(new Color(200, 200, 200));
        p3.setBackground(new Color(240, 240, 240));
        p3.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        p3.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10)); // ⭐ Align phải
        
        
        JLabel lbtieude = new JLabel("Thêm Nhà Cung Cấp");
        lbtieude.setFont(new Font("Arial", Font.BOLD, 16)); // ⭐ Font vừa phải
        p1.add(lbtieude);
        
//        
        
        JLabel lb1 = new JLabel("Mã nhà cung cấp *");
        lb1.setFont(new Font("Arial", Font.BOLD, 15)); // ⭐ Bold, to hơn
        lb1.setForeground(new Color(50, 50, 50));
        
        JLabel lb2 = new JLabel("Tên nhà cung cấp *");
        lb2.setFont(new Font("Arial", Font.BOLD, 15));
        lb2.setForeground(new Color(50, 50, 50));
        
        JLabel lb3 = new JLabel("Số điện thoại *");
        lb3.setFont(new Font("Arial", Font.BOLD, 15));
        lb3.setForeground(new Color(50, 50, 50));
        
        JLabel lb4 = new JLabel("Email *");
        lb4.setFont(new Font("Arial", Font.BOLD, 15));
        lb4.setForeground(new Color(50, 50, 50));
        
        JLabel lb5 = new JLabel("Địa chỉ *");
        lb5.setFont(new Font("Arial", Font.BOLD, 15));
        lb5.setForeground(new Color(50, 50, 50));
        
        JTextField tx1=new JTextField("");
        tx1.setPreferredSize(new Dimension(400, 50));
        tx1.setFont(new Font("Arial", Font.PLAIN, 14));
        tx1.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
        
        JTextField tx2=new JTextField("");
         tx2.setPreferredSize(new Dimension(400, 50));
        tx2.setFont(new Font("Arial", Font.PLAIN, 14));
        tx2.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
        
        JTextField tx3=new JTextField("");
         tx3.setPreferredSize(new Dimension(400, 50));
        tx3.setFont(new Font("Arial", Font.PLAIN, 14));
        tx3.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
        
        JTextField tx4=new JTextField("");
        tx4.setPreferredSize(new Dimension(400, 50));
        tx4.setFont(new Font("Arial", Font.PLAIN, 14));
        tx4.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
        
        JTextField tx5 = new JTextField();
        tx5.setPreferredSize(new Dimension(400, 50));
        tx5.setFont(new Font("Arial", Font.PLAIN, 14));
        tx5.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
        
        
        
        GridBagConstraints gbc = new GridBagConstraints();
         gbc.insets = new Insets(15, 15, 15, 15); // Tăng khoảng cách giữa rows 
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        p2.add(lb1, gbc);
        
        gbc.gridy = 1;
        gbc.weightx = 1;
        p2.add(tx1, gbc);
        
        gbc.gridy = 2;
        gbc.weightx = 0;
        p2.add(lb2, gbc);
        
        gbc.gridy = 3;
        gbc.weightx = 1;
        p2.add(tx2, gbc);
        
        gbc.gridy = 4;
        gbc.weightx = 0;
        p2.add(lb3, gbc);
        
        gbc.gridy = 5;
        gbc.weightx = 1;
        p2.add(tx3, gbc);
        
        gbc.gridy = 6;
        gbc.weightx = 0;
        p2.add(lb4, gbc);
        
        gbc.gridy = 7;
        gbc.weightx = 1;
        p2.add(tx4, gbc);
        
        gbc.gridy = 8;
        gbc.weightx = 0;
        p2.add(lb5, gbc);
        
        gbc.gridy = 9;
        gbc.weightx = 1;
        p2.add(tx5, gbc);
        
        p2.add(lb1);
        p2.add(tx1);
        p2.add(lb2);
        p2.add(tx2);
        p2.add(lb3);
        p2.add(tx3);
        p2.add(lb4);
        p2.add(tx4);
        p2.add(lb5);
        p2.add(tx5);
        p2.setLayout(new GridLayout(8, 1));
        
        JButton bt1=new JButton("lưu");
        bt1.setPreferredSize(new Dimension(100, 35));
        bt1.setBackground(new Color(0, 150, 0));
        bt1.setForeground(Color.WHITE);
        bt1.setFont(new Font("Arial", Font.BOLD, 12));
        bt1.setFocusPainted(false);
        bt1.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0), 2));
        
        JButton bt2=new JButton("hủy");
        bt2.setPreferredSize(new Dimension(100, 35));
        bt2.setBackground(new Color(200, 0, 0));
        bt2.setForeground(Color.WHITE);
        bt2.setFont(new Font("Arial", Font.BOLD, 12));
        bt2.setFocusPainted(false);
        bt2.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 2));
        
        bt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NhaCungCap ncc=new NhaCungCap();
                ncc.setMaNCC(tx1.getText());
                ncc.setTenNCC(tx2.getText());
                ncc.setSoDienThoai(tx3.getText());
                ncc.setEmail(tx4.getText());
                ncc.setDiaChi(tx5.getText());
    
                if(bus.themNhaCungCap(ncc,dialog)) {
                    JOptionPane.showMessageDialog(dialog, "Lưu thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    loadDuLieu();
            
            // ⭐ Clear textfield
            tx1.setText("");
            tx2.setText("");
            tx3.setText("");
            tx4.setText("");
            tx5.setText("");
            
            
                    dialog.dispose();
                    
                } else {
                    JOptionPane.showMessageDialog(dialog, "Lưu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    dialog.dispose();
                }
            }
        });
        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        
        p3.add(bt1);
        p3.add(bt2);
        
         dialog.add(p1, BorderLayout.NORTH);
        dialog.add(p2, BorderLayout.CENTER);
        dialog.add(p3, BorderLayout.SOUTH);
        dialog.setVisible(true);
        
        
    }
    
    
    private void suaNhaCungCap() {
       JFrame frame = parentFrame != null ? parentFrame : (JFrame) SwingUtilities.getWindowAncestor(this);
        
        if (frame == null) {
            JOptionPane.showMessageDialog(this, "Lỗi: Không thể tạo dialog!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int selectedRow = tableNCC.getSelectedRow();
        if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
        }
        JDialog dialog = new JDialog( frame, "Sửa", true);
        dialog.setSize(700, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        
        JPanel p1=new JPanel();
        p1.setBackground(new Color(230, 180, 0)); // Màu vàng đậm
         p1.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20)); // ⭐ Padding nhỏ gọn
        
        JPanel p2=new JPanel();
        p2.setLayout(new GridLayout(10,1));
        p2.setBackground(new Color(200, 200, 200)); // Màu xám
        p2.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40)); // ⭐ Tăng padding
        JPanel p3=new JPanel();
        p3.setBackground(new Color(200, 200, 200));
        p3.setBackground(new Color(240, 240, 240));
        p3.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        p3.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10)); // ⭐ Align phải
        
        
        
        JLabel lbtieude = new JLabel("Sửa Nhà Cung Cấp");
        lbtieude.setFont(new Font("Arial", Font.BOLD, 16)); // ⭐ Font vừa phải
        p1.add(lbtieude);
        
//        
        
        JLabel lb1 = new JLabel("Mã nhà cung cấp *");
        lb1.setFont(new Font("Arial", Font.BOLD, 15)); // ⭐ Bold, to hơn
        lb1.setForeground(new Color(50, 50, 50));
        
        JLabel lb2 = new JLabel("Tên nhà cung cấp *");
        lb2.setFont(new Font("Arial", Font.BOLD, 15));
        lb2.setForeground(new Color(50, 50, 50));
        
        JLabel lb3 = new JLabel("Số điện thoại *");
        lb3.setFont(new Font("Arial", Font.BOLD, 15));
        lb3.setForeground(new Color(50, 50, 50));
        
        JLabel lb4 = new JLabel("Email *");
        lb4.setFont(new Font("Arial", Font.BOLD, 15));
        lb4.setForeground(new Color(50, 50, 50));
        
        JLabel lb5 = new JLabel("Địa chỉ *");
        lb5.setFont(new Font("Arial", Font.BOLD, 15));
        lb5.setForeground(new Color(50, 50, 50));
        
        JTextField tx1=new JTextField("");
        tx1.setPreferredSize(new Dimension(400, 50));
        tx1.setFont(new Font("Arial", Font.PLAIN, 14));
        tx1.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
        tx1.setText(tableModel.getValueAt(selectedRow, 0).toString()); // ⭐ Mã NCC
        tx1.setEditable(false);
        
        JTextField tx2=new JTextField("");
         tx2.setPreferredSize(new Dimension(400, 50));
        tx2.setFont(new Font("Arial", Font.PLAIN, 14));
        tx2.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
        tx2.setText(tableModel.getValueAt(selectedRow, 1).toString());
        
        JTextField tx3=new JTextField("");
         tx3.setPreferredSize(new Dimension(400, 50));
        tx3.setFont(new Font("Arial", Font.PLAIN, 14));
        tx3.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
        tx3.setText(tableModel.getValueAt(selectedRow, 3).toString());
        
        JTextField tx4=new JTextField("");
        tx4.setPreferredSize(new Dimension(400, 50));
        tx4.setFont(new Font("Arial", Font.PLAIN, 14));
        tx4.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
        tx4.setText(tableModel.getValueAt(selectedRow, 4).toString()); 
        
        JTextField tx5 = new JTextField();
        tx5.setPreferredSize(new Dimension(400, 50));
        tx5.setFont(new Font("Arial", Font.PLAIN, 14));
        tx5.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
        tx5.setText(tableModel.getValueAt(selectedRow, 2).toString());
        
        
        
        GridBagConstraints gbc = new GridBagConstraints();
         gbc.insets = new Insets(15, 15, 15, 15); // Tăng khoảng cách giữa rows 
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        p2.add(lb1, gbc);
        
        gbc.gridy = 1;
        gbc.weightx = 1;
        p2.add(tx1, gbc);
        
        gbc.gridy = 2;
        gbc.weightx = 0;
        p2.add(lb2, gbc);
        
        gbc.gridy = 3;
        gbc.weightx = 1;
        p2.add(tx2, gbc);
        
        gbc.gridy = 4;
        gbc.weightx = 0;
        p2.add(lb3, gbc);
        
        gbc.gridy = 5;
        gbc.weightx = 1;
        p2.add(tx3, gbc);
        
        gbc.gridy = 6;
        gbc.weightx = 0;
        p2.add(lb4, gbc);
        
        gbc.gridy = 7;
        gbc.weightx = 1;
        p2.add(tx4, gbc);
        
        gbc.gridy = 8;
        gbc.weightx = 0;
        p2.add(lb5, gbc);
        
        gbc.gridy = 9;
        gbc.weightx = 1;
        p2.add(tx5, gbc);
        
        p2.add(lb1);
        p2.add(tx1);
        p2.add(lb2);
        p2.add(tx2);
        p2.add(lb3);
        p2.add(tx3);
        p2.add(lb4);
        p2.add(tx4);
        p2.add(lb5);
        p2.add(tx5);
        p2.setLayout(new GridLayout(8, 1));
        
        JButton bt1=new JButton("lưu");
        bt1.setPreferredSize(new Dimension(100, 35));
        bt1.setBackground(new Color(0, 150, 0));
        bt1.setForeground(Color.WHITE);
        bt1.setFont(new Font("Arial", Font.BOLD, 12));
        bt1.setFocusPainted(false);
        bt1.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0), 2));
        
        JButton bt2=new JButton("hủy");
        bt2.setPreferredSize(new Dimension(100, 35));
        bt2.setBackground(new Color(200, 0, 0));
        bt2.setForeground(Color.WHITE);
        bt2.setFont(new Font("Arial", Font.BOLD, 12));
        bt2.setFocusPainted(false);
        bt2.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 2)); 
        
      
        bt1.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
               NhaCungCap ncc=new NhaCungCap();
               ncc.setMaNCC(tx1.getText());
               ncc.setTenNCC(tx2.getText());
               ncc.setSoDienThoai(tx3.getText());
               ncc.setEmail(tx4.getText());
               ncc.setDiaChi(tx5.getText());
               if(bus.SuaNhaCungCap(ncc,dialog)) {
                   JOptionPane.showMessageDialog(dialog, "Lưu thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    loadDuLieu();
                    tx1.setText("");
                    tx2.setText("");
                    tx3.setText("");
                    tx4.setText("");
                    tx5.setText("");
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Lưu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    dialog.dispose();
                }
               }
         });           
        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        
        p3.add(bt1);
        p3.add(bt2);
        
         dialog.add(p1, BorderLayout.NORTH);
        dialog.add(p2, BorderLayout.CENTER);
        dialog.add(p3, BorderLayout.SOUTH);
        dialog.setVisible(true);
    
    }

    private void xoaNhaCungCap() {
        int selectedRow = tableNCC.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        String maNCC = tableModel.getValueAt(selectedRow, 0).toString();
         String tenNCC = tableModel.getValueAt(selectedRow, 1).toString();
        int confirm = JOptionPane.showConfirmDialog( this, "Bạn chắc chắn muốn xóa nhà cung cấp:\n" + tenNCC + " (" + maNCC + ") ?","Xác nhận xóa",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
         
        
        if(bus.XoaNhaCungCap(maNCC)) { 
            JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            loadDuLieu(); // ⭐ Load lại bảng
             // ⭐ Clear các textfield
        } else {
            JOptionPane.showMessageDialog(this, "xóa thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        }
         
         
        
        
     
            
    }
    

    private void timKiem() {
        String dauvao=txtTimKiem.getText();
        tableModel.setRowCount(0);
        ArrayList<NhaCungCap> danhSach = bus.getNhaCungCap();
        for (NhaCungCap ncc : danhSach) {
            if (ncc.getMaNCC().toLowerCase().contains(dauvao.toLowerCase())
                    || ncc.getTenNCC().toLowerCase().contains(dauvao.toLowerCase())) {
                Object[] row = {
                    ncc.getMaNCC(),
                    ncc.getTenNCC(),
                    ncc.getDiaChi(),
                    ncc.getSoDienThoai(),
                    ncc.getEmail()
                };
                tableModel.addRow(row);
            }
        }
    }

//    private void lamLai() {
//        txtMaNCC.setText("");
//        txtTenNCC.setText("");
//        txtDiaChi.setText("");
//        txtSoDienThoai.setText("");
//        txtEmail.setText("");
//    }

    
}
