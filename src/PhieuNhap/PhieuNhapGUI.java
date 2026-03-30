/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PhieuNhap;

import ChiTietPhieuNhap.*;
import NhaCungCap.*;
import NhanVien.*;
import Sach.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ASUS
 */
public class PhieuNhapGUI extends JPanel {
    private  PhieuNhapBUS pnbus=new PhieuNhapBUS();
    private  ChiTietPhieuNhapBUS ctbus=new ChiTietPhieuNhapBUS();
    private NhaCungCapBUS nccbus=new NhaCungCapBUS();
    private NhanVienBUS nvbus=new NhanVienBUS();
    private SachBUS sbus=new SachBUS();
    private  JTable tbpn;
    private  JTable tbct;
    private DefaultTableModel pntablemodel;
    private DefaultTableModel cttablemodel;
    private JComboBox<String> cbNCCFilter;
    private JComboBox<String> cbNVFilter;
    
    private JLabel lbtimkiem; 
    private JTextField txtTimKiem;
    private JButton btnThem,btnXoa,btnSua,btnTimKiem,btnTatCa;
     private JFrame parentFrame;
    
    public PhieuNhapGUI(JFrame parent) {
        setLayout(new BorderLayout()); // ⭐ Set layout cho panel
        setBackground(new Color(230, 230, 230)); // Màu xám nhạt
        
        JPanel paneldau = TaoPanel();
        add(paneldau,BorderLayout.NORTH);
        
        this.parentFrame = parent;
        
        JPanel panelpn=TaoPanelBangPN();
        JPanel panelct=TaoPanelBangCT();
        JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,panelpn,panelct);
        splitpane.setDividerLocation(250);
        
        
        
        add(splitpane, BorderLayout.CENTER);
 
        LoadDuLieu();
        ClickPnHienChiTiet();
    }
    public JPanel TaoPanel() {
      JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 250, 205)); // Màu vàng nhạt
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(255, 250, 205));
        
         lbtimkiem=new JLabel("Tìm Kiếm");
         lbtimkiem.setFont(new Font("Arial", Font.BOLD, 13));
        txtTimKiem = new JTextField(15);
        txtTimKiem.setPreferredSize(new Dimension(200, 30));
        
        leftPanel.add(lbtimkiem);
        leftPanel.add(txtTimKiem);
        
        cbNCCFilter = new JComboBox<>();
        cbNVFilter = new JComboBox<>();
        cbNCCFilter.setPreferredSize(new Dimension(220, 30));
        cbNVFilter.setPreferredSize(new Dimension(180, 30));

        cbNCCFilter.addItem("Tất cả NCC");
        cbNVFilter.addItem("Tất cả NV");

        // TODO: bạn đổ dữ liệu NCC/NV vào đây (ví dụ loadNCCToCombo, loadNVToCombo)
        loadNCCToCombo(cbNCCFilter);
        loadNVToCombo(cbNVFilter);

        
        leftPanel.add(cbNCCFilter);
        leftPanel.add(cbNVFilter);
        
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
    
    btnThem.addActionListener(e -> themPhieuNhap());
    btnSua.addActionListener(e -> SuaPhieuNhap());
    btnXoa.addActionListener(e -> XoaPhieuNhap());
    btnTimKiem.addActionListener(e -> TimKiem());
    btnTatCa.addActionListener(e -> LoadDuLieu());
    cbNCCFilter.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                 LocTheoNCC_NV();
            }
        });
        cbNVFilter.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                LocTheoNCC_NV();
            }
        });
    return panel;
    }
    public JPanel TaoPanelBangPN() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(200, 150, 0), 3),"Danh Sách phiếu nhập",javax.swing.border.TitledBorder.LEFT,javax.swing.border.TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14),
            new Color(80, 80, 80)
        ));
        panel.setBackground(new Color(200, 200, 200)); // Màu xám
        panel.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(new Color(200, 150, 0), 3),BorderFactory.createEmptyBorder(10, 10, 10, 10))); 
        
         String[] columns = {"Mã PN", "Mã NV", "Mã NCC", "  thời gian tạo", "Tổng tiền"};
        pntablemodel = new DefaultTableModel(columns, 0);
        tbpn = new JTable(pntablemodel);
        tbpn.setBackground(Color.WHITE);
        tbpn.setFont(new Font("Arial", Font.PLAIN, 12));
        tbpn.getTableHeader().setBackground(new Color(230, 180, 0)); // Header màu vàng
        tbpn.getTableHeader().setForeground(Color.BLACK);
        tbpn.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tbpn.setRowHeight(25);
        
         JScrollPane scrollPane = new JScrollPane(tbpn);
           panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
        
    }
    public JPanel TaoPanelBangCT() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(200, 150, 0), 3),"Danh Sách phiếu nhập",javax.swing.border.TitledBorder.LEFT,javax.swing.border.TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14),
            new Color(80, 80, 80)
        ));
        
        panel.setBackground(new Color(200, 200, 200)); // Màu xám
        panel.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(new Color(200, 150, 0), 3),BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        String[] columns = {"Mã PN", "Mã Sách", "số lượng", "đơn giá ", "thành tiền"};
        cttablemodel = new DefaultTableModel(columns,0);
        tbct=new JTable(cttablemodel);
        tbct.setBackground(Color.WHITE);
        tbct.setFont(new Font("Arial", Font.PLAIN, 12));
        tbct.getTableHeader().setBackground(new Color(230, 180, 0)); // Header màu vàng
        tbct.getTableHeader().setForeground(Color.BLACK);
        tbct.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tbct.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(tbct);
           panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    private void themPhieuNhap() {//==================================================================================
    JFrame frame = parentFrame != null ? parentFrame : (JFrame) SwingUtilities.getWindowAncestor(this);
    
    if (frame == null) {
        JOptionPane.showMessageDialog(this, "Lỗi: Không thể tạo dialog!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    JDialog dialog = new JDialog(frame, "Thêm Phiếu Nhập", true);
    dialog.setSize(900, 700);
    dialog.setLocationRelativeTo(this);
    dialog.setLayout(new BorderLayout());
    
    // ⭐ PANEL TIÊU ĐỀ (NORTH)
    JPanel pTitle = new JPanel();
    pTitle.setBackground(new Color(230, 180, 0));
    pTitle.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
    JLabel lbTitle = new JLabel("Thêm Phiếu Nhập");
    lbTitle.setFont(new Font("Arial", Font.BOLD, 16));
    pTitle.add(lbTitle);
    
    // ⭐ PANEL THÔNG TIN PHIẾU (CENTER - TOP)
    JPanel pPhieuInfo = new JPanel();
    pPhieuInfo.setLayout(new GridLayout(3, 2, 15, 15));
    pPhieuInfo.setBackground(new Color(200, 200, 200));
    pPhieuInfo.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(new Color(200, 150, 0), 2),
        "Thông Tin Phiếu Nhập",
        javax.swing.border.TitledBorder.LEFT,
        javax.swing.border.TitledBorder.TOP,
        new Font("Arial", Font.BOLD, 13),
        new Color(80, 80, 80)
    ));
    pPhieuInfo.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(200, 150, 0), 2),
        BorderFactory.createEmptyBorder(15, 15, 15, 15)
    ));
    
    // Mã PN
    JLabel lbMaPN = new JLabel("Mã Phiếu Nhập *");
    lbMaPN.setFont(new Font("Arial", Font.BOLD, 13));
    JTextField txMaPN = new JTextField(pnbus.autoThemMa());
    txMaPN.setEditable(false);
    txMaPN.setFont(new Font("Arial", Font.PLAIN, 12));
    txMaPN.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
    pPhieuInfo.add(lbMaPN);
    pPhieuInfo.add(txMaPN);
    
    // Mã NCC
    JLabel lbMaNCC = new JLabel("Nhà Cung Cấp *");
    lbMaNCC.setFont(new Font("Arial", Font.BOLD, 13));
    JComboBox<String> cbNCC = new JComboBox<>();
    // ⭐ Load danh sách NCC từ BUS
    loadNCCToCombo(cbNCC);
    pPhieuInfo.add(lbMaNCC);
    pPhieuInfo.add(cbNCC);
    
    // Mã NV
    JLabel lbMaNV = new JLabel("Mã Nhân Viên *");
    lbMaNV.setFont(new Font("Arial", Font.BOLD, 13));
    // load danh sách NV từ BUS
    JComboBox<String> cbNV = new JComboBox<>();
    loadNVToCombo(cbNV);
    pPhieuInfo.add(lbMaNV);
    pPhieuInfo.add(cbNV);
    
    // ⭐ PANEL CHI TIẾT SẢN PHẨM (CENTER - BOTTOM)
    JPanel pChiTietContainer = new JPanel(new BorderLayout());
    pChiTietContainer.setBackground(new Color(200, 200, 200));
    pChiTietContainer.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(new Color(200, 150, 0), 2),
        "Chi Tiết Sản Phẩm",
        javax.swing.border.TitledBorder.LEFT,
        javax.swing.border.TitledBorder.TOP,
        new Font("Arial", Font.BOLD, 13),
        new Color(80, 80, 80)
    ));
    pChiTietContainer.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(200, 150, 0), 2),
        BorderFactory.createEmptyBorder(10, 10, 10, 10)
    ));
    
    // ⭐ Bảng chi tiết - MATCH VỚI CLASS
    String[] columnsCT = {"Mã Sách", "Số Lượng", "Đơn Giá", "Thành Tiền", "Xóa"};
    DefaultTableModel ctTableModel = new DefaultTableModel(columnsCT, 0);
    JTable tbChiTiet = new JTable(ctTableModel);
    tbChiTiet.setBackground(Color.WHITE);
    tbChiTiet.setFont(new Font("Arial", Font.PLAIN, 11));
    tbChiTiet.getTableHeader().setBackground(new Color(230, 180, 0));
    tbChiTiet.getTableHeader().setFont(new Font("Arial", Font.BOLD, 11));
    tbChiTiet.setRowHeight(25);
    
    JScrollPane scrollCT = new JScrollPane(tbChiTiet);
    pChiTietContainer.add(scrollCT, BorderLayout.CENTER);
    
    // ⭐ Panel thêm chi tiết
    JPanel pThemCT = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    pThemCT.setBackground(new Color(200, 200, 200));
    
    JLabel lbMaSach = new JLabel("Mã Sách:");
    JTextField txMaSach = new JTextField(10);
    txMaSach.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
    
    JLabel lbSoLuong = new JLabel("Số Lượng:");
    JTextField txSoLuong = new JTextField(8);
    txSoLuong.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
    
    JLabel lbDonGia = new JLabel("Đơn Giá:");
    JTextField txDonGia = new JTextField(10);
    txDonGia.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
    
    JButton btnThemCT = new JButton("Thêm CT");
    btnThemCT.setBackground(new Color(0, 150, 200));
    btnThemCT.setForeground(Color.WHITE);
    btnThemCT.setFont(new Font("Arial", Font.BOLD, 11));
    btnThemCT.setFocusPainted(false);
    
    pThemCT.add(lbMaSach);
    pThemCT.add(txMaSach);
    pThemCT.add(lbSoLuong);
    pThemCT.add(txSoLuong);
    pThemCT.add(lbDonGia);
    pThemCT.add(txDonGia);
    pThemCT.add(btnThemCT);
    
    pChiTietContainer.add(pThemCT, BorderLayout.SOUTH);
    
    // ⭐ CENTER PANEL chứa phiếu info + chi tiết
    JPanel pCenter = new JPanel(new BorderLayout(10, 10));
    pCenter.setBackground(new Color(200, 200, 200));
    pCenter.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    pCenter.add(pPhieuInfo, BorderLayout.NORTH);
    pCenter.add(pChiTietContainer, BorderLayout.CENTER);
    
    // ⭐ PANEL NÚT (SOUTH)
    JPanel pButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
    pButton.setBackground(new Color(240, 240, 240));
    pButton.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
    
    JButton btnLuu = new JButton("Lưu");
    btnLuu.setPreferredSize(new Dimension(100, 35));
    btnLuu.setBackground(new Color(0, 150, 0));
    btnLuu.setForeground(Color.WHITE);
    btnLuu.setFont(new Font("Arial", Font.BOLD, 12));
    btnLuu.setFocusPainted(false);
    btnLuu.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0), 2));
    
    JButton btnHuy = new JButton("Hủy");
    btnHuy.setPreferredSize(new Dimension(100, 35));
    btnHuy.setBackground(new Color(200, 0, 0));
    btnHuy.setForeground(Color.WHITE);
    btnHuy.setFont(new Font("Arial", Font.BOLD, 12));
    btnHuy.setFocusPainted(false);
    btnHuy.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 2));
    
    pButton.add(btnLuu);
    pButton.add(btnHuy);
    
    // ⭐ ACTION XÓA CHI TIẾT
    tbChiTiet.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = tbChiTiet.getSelectedRow();
            int col = tbChiTiet.getSelectedColumn();
            
            // Nếu click cột "Xóa" (cột cuối)
            if (col == 4 && row != -1) {
                ctTableModel.removeRow(row);
            }
        }
    });
    
    // ⭐ NÚT THÊM SP
    btnThemCT.addActionListener(e -> {
        String maSach = txMaSach.getText().trim();
        String soLuongStr = txSoLuong.getText().trim();
        String donGiaStr = txDonGia.getText().trim();
        
        if (maSach.isEmpty() || soLuongStr.isEmpty() || donGiaStr.isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int soLuong = Integer.parseInt(soLuongStr);
            double donGia = Double.parseDouble(donGiaStr);
            double thanhTien = soLuong * donGia;
            
            // ⭐ MATCH: maSach, soLuong, donGia, thanhTien
            Object[] row = {maSach, soLuong, donGia, thanhTien, "Xóa"};
            ctTableModel.addRow(row);
            
            // Clear input
            txMaSach.setText("");
            txSoLuong.setText("");
            txDonGia.setText("");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(dialog, "Số lượng và đơn giá phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    });
    
    // ⭐ NÚT LƯU
    btnLuu.addActionListener(e -> {
        String maPN = txMaPN.getText().trim();
        
        
        
        if (maPN.isEmpty() ) {
            JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin phiếu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (ctTableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(dialog, "Phiếu nhập phải có ít nhất 1 sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // ⭐ Tạo phiếu nhập
            PhieuNhap pn = new PhieuNhap();
            pn.setMaPN(maPN);
            String maNV=((String) cbNV.getSelectedItem()).split(" - ")[0];
            pn.setMaNV(maNV);
            // ⭐ Lấy mã NCC từ ComboBox (định dạng "MA - TEN")
            String maNCC = ((String) cbNCC.getSelectedItem()).split(" - ")[0];
            pn.setMaNCC(maNCC);
            
            pn.setNgayLap(java.time.LocalDate.now());
            
            // ⭐ Lưu phiếu
            if (!pnbus.ThemPhieuNhap(pn, dialog)) {
                JOptionPane.showMessageDialog(dialog, "Lưu phiếu nhập thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // ⭐ Lưu từng chi tiết - MATCH VỚI CLASS
            for (int i = 0; i < ctTableModel.getRowCount(); i++) {
                String maSach = ctTableModel.getValueAt(i, 0).toString();
                int soLuong = Integer.parseInt(ctTableModel.getValueAt(i, 1).toString());
                double donGia = Double.parseDouble(ctTableModel.getValueAt(i, 2).toString());
                double thanhTien = Double.parseDouble(ctTableModel.getValueAt(i, 3).toString());
                
             
                ChiTietPhieuNhap ct = new ChiTietPhieuNhap(
                    maPN,           // maphieunhap
                    maSach,         // masach
                    soLuong,        // soluong
                    donGia,         // dongia
                    thanhTien       // thanhtien
                );
                
                 boolean ctkq=ctbus.ThemChiTietPhieuNhap(ct);
                    if(ctkq) {
                    sbus.CongSoLuongTon(maSach, soLuong);
                }
            }
            
//            pnbus.UpdateTongTienPN(maPN);
            
            JOptionPane.showMessageDialog(dialog, "Lưu phiếu nhập thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            LoadDuLieu();
            dialog.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(dialog, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    });
    
    // ⭐ NÚT HỦY
    btnHuy.addActionListener(e -> dialog.dispose());
    
    // ⭐ Add panels
    dialog.add(pTitle, BorderLayout.NORTH);
    dialog.add(pCenter, BorderLayout.CENTER);
    dialog.add(pButton, BorderLayout.SOUTH);
    dialog.setVisible(true);
}

// ⭐ Hàm load danh sách NCC vào ComboBox
private void loadNCCToCombo(JComboBox<String> cb) {
    ArrayList<NhaCungCap> danhSach = nccbus.getNhaCungCap();
    for (NhaCungCap ncc : danhSach) {
        cb.addItem(ncc.getMaNCC() + " - " + ncc.getTenNCC());
    }
}
private void loadNVToCombo(JComboBox<String> cb) {
    ArrayList<NhanVien> danhSach = nvbus.GetNhanVien();
    for(NhanVien nv : danhSach) {
        cb.addItem(nv.getMaNV() + " - "+ nv.getHoNV() );
    }
}
    public void SuaPhieuNhap() {//===========================================================================================

        int selectedRow = tbpn.getSelectedRow();
        if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu nhập để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
        }
        
        String maPN = pntablemodel.getValueAt(selectedRow, 0).toString();
    
        JFrame frame = parentFrame != null ? parentFrame : (JFrame) SwingUtilities.getWindowAncestor(this);
    
        if (frame == null) {
            JOptionPane.showMessageDialog(this, "Lỗi: Không thể tạo dialog!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JDialog dialog = new JDialog(frame, "Sửa Phiếu Nhập", true);
        dialog.setSize(900, 700);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        
        JPanel pTitle = new JPanel();
        pTitle.setBackground(new Color(230, 180, 0));
        pTitle.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        JLabel lbTitle = new JLabel("Sửa Phiếu Nhập: " + maPN);
        lbTitle.setFont(new Font("Arial", Font.BOLD, 16));
        pTitle.add(lbTitle);
        
        JPanel pPhieuInfo = new JPanel(); //=====================================Panel pn
        pPhieuInfo.setLayout(new GridLayout(3, 2, 15, 15));
        pPhieuInfo.setBackground(new Color(200, 200, 200));
        pPhieuInfo.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(200, 150, 0), 2),
        BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel lbMaPN = new JLabel("Mã Phiếu Nhập");
        lbMaPN.setFont(new Font("Arial", Font.BOLD, 13));
        JTextField txMaPN = new JTextField(maPN);
        txMaPN.setEditable(false);  // ⭐ Không cho sửa
        txMaPN.setFont(new Font("Arial", Font.PLAIN, 12));
        txMaPN.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
        txMaPN.setBackground(new Color(230, 230, 230));
        pPhieuInfo.add(lbMaPN);
        pPhieuInfo.add(txMaPN);
        
        JLabel lbMaNCC = new JLabel("Nhà Cung Cấp *");
        lbMaNCC.setFont(new Font("Arial", Font.BOLD, 13));
        JComboBox<String> cbNCC = new JComboBox<>();
        loadNCCToCombo(cbNCC);
        pPhieuInfo.add(lbMaNCC);
        pPhieuInfo.add(cbNCC);
        
        JLabel lbMaNV = new JLabel("Mã Nhân Viên *");
        lbMaNV.setFont(new Font("Arial", Font.BOLD, 13));
        JComboBox<String> cbNV = new JComboBox<>();
        loadNVToCombo(cbNV);
        pPhieuInfo.add(lbMaNV);
        pPhieuInfo.add(cbNV);
        
        PhieuNhap phieuHienTai = pnbus.GetByMaPhieu(maPN);
        
        for (int i = 0; i < cbNCC.getItemCount(); i++) {
            if (cbNCC.getItemAt(i).startsWith(phieuHienTai.getMaNCC())) {
            cbNCC.setSelectedIndex(i);
            break;
            }
        }

        for (int i = 0; i < cbNV.getItemCount(); i++) {
            if (cbNV.getItemAt(i).startsWith(phieuHienTai.getMaNV())) {
            cbNV.setSelectedIndex(i);
            break;
            }
        }
        
        JPanel pChiTietContainer = new JPanel(new BorderLayout());
        pChiTietContainer.setBackground(new Color(200, 200, 200));
        pChiTietContainer.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(200, 150, 0), 2),
        BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        String[] columnsCT = {"Mã Sách", "Số Lượng", "Đơn Giá", "Thành Tiền", "Xóa"};
        DefaultTableModel ctTableModel = new DefaultTableModel(columnsCT, 0);
        JTable tbChiTiet = new JTable(ctTableModel);
        tbChiTiet.setBackground(Color.WHITE);
        tbChiTiet.setFont(new Font("Arial", Font.PLAIN, 11));
        tbChiTiet.getTableHeader().setBackground(new Color(230, 180, 0));
        tbChiTiet.getTableHeader().setFont(new Font("Arial", Font.BOLD, 11));
        tbChiTiet.setRowHeight(25);
        
        JScrollPane scrollCT = new JScrollPane(tbChiTiet);
        pChiTietContainer.add(scrollCT, BorderLayout.CENTER);
        
        ArrayList<ChiTietPhieuNhap> danhSachCT = ctbus.GetChiTietPhieuNhap(maPN);
        for (ChiTietPhieuNhap ct : danhSachCT) {
        Object[] row = {ct.getMaSach(), ct.getSoLuong(), ct.getDonGia(), ct.getThanhTien(), "Xóa"};
        ctTableModel.addRow(row);
        }
        
        JPanel pThemCT = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); //================panel nhap chi tiet
        pThemCT.setBackground(new Color(200, 200, 200));
    
        JLabel lbMaSach = new JLabel("Mã Sách:");
        JTextField txMaSach = new JTextField(10);
        txMaSach.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
    
        JLabel lbSoLuong = new JLabel("Số Lượng:");
        JTextField txSoLuong = new JTextField(8);
        txSoLuong.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
    
        JLabel lbDonGia = new JLabel("Đơn Giá:");
        JTextField txDonGia = new JTextField(10);
        txDonGia.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
        
        JButton btnThemCT = new JButton("Thêm SP");
        btnThemCT.setBackground(new Color(0, 150, 200));
        btnThemCT.setForeground(Color.WHITE);
        btnThemCT.setFont(new Font("Arial", Font.BOLD, 11));
        btnThemCT.setFocusPainted(false);

        pThemCT.add(lbMaSach);
        pThemCT.add(txMaSach);
        pThemCT.add(lbSoLuong);
        pThemCT.add(txSoLuong);
        pThemCT.add(lbDonGia);
        pThemCT.add(txDonGia);
        pThemCT.add(btnThemCT);
    
        pChiTietContainer.add(pThemCT, BorderLayout.SOUTH);
        // panel giua
        JPanel pCenter = new JPanel(new BorderLayout(10, 10));
        pCenter.setBackground(new Color(200, 200, 200));
        pCenter.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        pCenter.add(pPhieuInfo, BorderLayout.NORTH);
        pCenter.add(pChiTietContainer, BorderLayout.CENTER);
        // panel nut
        JPanel pButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        pButton.setBackground(new Color(240, 240, 240));
        pButton.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        
        JButton btnLuu = new JButton("Lưu");
        btnLuu.setPreferredSize(new Dimension(100, 35));
        btnLuu.setBackground(new Color(0, 150, 0));
        btnLuu.setForeground(Color.WHITE);
        btnLuu.setFont(new Font("Arial", Font.BOLD, 12));
        btnLuu.setFocusPainted(false);
        btnLuu.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0), 2));

        JButton btnHuy = new JButton("Hủy");
        btnHuy.setPreferredSize(new Dimension(100, 35));
        btnHuy.setBackground(new Color(200, 0, 0));
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setFont(new Font("Arial", Font.BOLD, 12));
        btnHuy.setFocusPainted(false);
        btnHuy.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0), 2));
    
        pButton.add(btnLuu);
        pButton.add(btnHuy);
        
        tbChiTiet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tbChiTiet.getSelectedRow();
                int col = tbChiTiet.getSelectedColumn();
            
                if (col == 4 && row != -1) {
                    String maSachXoa = ctTableModel.getValueAt(row, 0).toString();
                
                    // ⭐ Xóa khỏi bảng
                    ctTableModel.removeRow(row);
                
                    // ⭐ Đánh dấu để xóa khỏi DB (sau)
                    System.out.println("Đánh dấu xóa chi tiết: " + maPN + " - " + maSachXoa);
                }
            }
        });
        // nut them ct vao bang ct
        btnThemCT.addActionListener(e -> {
        String maSach = txMaSach.getText().trim();
        String soLuongStr = txSoLuong.getText().trim();
        String donGiaStr = txDonGia.getText().trim();
        
        if (maSach.isEmpty() || soLuongStr.isEmpty() || donGiaStr.isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int soLuong = Integer.parseInt(soLuongStr);
            double donGia = Double.parseDouble(donGiaStr);
            double thanhTien = soLuong * donGia;
            
            Object[] row = {maSach, soLuong, donGia, thanhTien, "Xóa"};
            ctTableModel.addRow(row);
            
            txMaSach.setText("");
            txSoLuong.setText("");
            txDonGia.setText("");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(dialog, "Số lượng và đơn giá phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnLuu.addActionListener(e -> {
        if (cbNCC.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(dialog, "Vui lòng chọn Nhà Cung Cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (cbNV.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(dialog, "Vui lòng chọn Nhân Viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (ctTableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(dialog, "Phiếu nhập phải có ít nhất 1 sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // ⭐ Cập nhật thông tin phiếu
            PhieuNhap pn = new PhieuNhap();
            pn.setMaPN(maPN);
            
            String maNV = ((String) cbNV.getSelectedItem()).split(" - ")[0];
            pn.setMaNV(maNV);
            
            String maNCC = ((String) cbNCC.getSelectedItem()).split(" - ")[0];
            pn.setMaNCC(maNCC);
            
            pn.setNgayLap(java.time.LocalDate.now());
            
            // ⭐ Lưu cập nhật phiếu
            if (!pnbus.SuaPhieuNhap(pn, dialog)) {
                JOptionPane.showMessageDialog(dialog, "Cập nhật phiếu nhập thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            //  Lấy danh sách chi tiết cũ(lấy chi tiết trước khi cập nhật để so sánh với chi tiết mình đang thêm mới vào)
            ArrayList<ChiTietPhieuNhap> danhSachCu = ctbus.GetChiTietPhieuNhap(maPN);
            ArrayList<String> maSachCu = new ArrayList<>();//khác biệt với cái dưới ,cái này là lấy trong dataase
            HashMap<String, Integer> soLuongCuMap = new HashMap<>();
            for (ChiTietPhieuNhap ct : danhSachCu) {
                maSachCu.add(ct.getMaSach());
            }
            
            //  Lấy danh sách chi tiết mới từ bảng(lấy cái chi tiết mới nè)
            ArrayList<String> maSachMoi = new ArrayList<>();
            for (int i = 0; i < ctTableModel.getRowCount(); i++) {
                maSachMoi.add(ctTableModel.getValueAt(i, 0).toString());
            }
            // tìm chi tiết bị xóa(có trong databse nhưng ko có trong bảng mới)
            for (String maSach : maSachCu) {
                if (!maSachMoi.contains(maSach)) {
                    System.out.println("Xóa: " + maSach);
                    ChiTietPhieuNhap ct = new ChiTietPhieuNhap();
                    ct.setMaPN(maPN);
                    ct.setMaSach(maSach);
                    ctbus.XoaChiTietPhieuNhap(ct);
                    
                     int soLuongCu = soLuongCuMap.get(maSach);
                       sbus.CongSoLuongTon(maSach, -soLuongCu);
                }
            }
            
            for (int i = 0; i < ctTableModel.getRowCount(); i++) {
                String maSach = ctTableModel.getValueAt(i, 0).toString();
                int soLuong = Integer.parseInt(ctTableModel.getValueAt(i, 1).toString());
                double donGia = Double.parseDouble(ctTableModel.getValueAt(i, 2).toString());
                double thanhTien = Double.parseDouble(ctTableModel.getValueAt(i, 3).toString());
        
        // ⭐ Kiểm tra: Chi tiết này có trong cũ không?
                if (maSachCu.contains(maSach)) {
                    // ✅ Cập nhật chi tiết cũ
                    System.out.println("Cập nhật: " + maSach);
                    ctbus.SuaChiTietPhieuNhap(
                        new ChiTietPhieuNhap(maPN, maSach, soLuong, donGia, thanhTien)
                    );
//                    int soLuongCu = soLuongCuMap.get(maSach);
//                    int delta = soLuong - soLuongCu;
//                    sbus.CongSoLuongTon(maSach, delta);
                } else {
                    // ✅ Thêm chi tiết mới
                    System.out.println("Thêm mới: " + maSach);
                    ctbus.ThemChiTietPhieuNhap(
                        new ChiTietPhieuNhap(maPN, maSach, soLuong, donGia, thanhTien)
                    );
                     sbus.CongSoLuongTon(maSach, soLuong);
                        }
            }
            
            
            // Reload danh sách phiếu
            LoadDuLieu();

            // Đóng dialog
            JOptionPane.showMessageDialog(dialog, "Cập nhật phiếu nhập thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
        
        
            }catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(dialog, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnHuy.addActionListener(e -> dialog.dispose());
    
        dialog.add(pTitle, BorderLayout.NORTH);
        dialog.add(pCenter, BorderLayout.CENTER);
        dialog.add(pButton, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    public void TimKiem() {
        String dauvao=txtTimKiem.getText();
        pntablemodel.setRowCount(0);
        ArrayList<PhieuNhap> danhSach = pnbus.getPhieuNhap();
        for(PhieuNhap pn : danhSach) {
            if(pn.getMaPN().toLowerCase().contains(dauvao.toLowerCase())) {
                Object[] row = {
                    pn.getMaPN(),
                    pn.getMaNV(),
                    pn.getMaNCC(),
                    pn.getNgayLap(),
                    pn.getTongTien()
                };
                pntablemodel.addRow(row);
            }
        } 
    }
    public void XoaPhieuNhap() {//==========================================================================
        int selectedRow = tbpn.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        String maPN = pntablemodel.getValueAt(selectedRow, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa phiếu nhập " + maPN + " không?","Xác nhận xóa",JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        
        PhieuNhap pn = new PhieuNhap();
        pn.setMaPN(maPN);

        boolean ok = pnbus.XoaPhieuNhap(pn);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Xóa phiếu nhập thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            LoadDuLieu();
        } else {
        JOptionPane.showMessageDialog(this, "Xóa phiếu nhập thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void LocTheoNCC_NV() {
        String ncc = cbNCCFilter.getSelectedItem().toString();
        String nv = cbNVFilter.getSelectedItem().toString();

        String maNCC = ncc.equals("Tất cả NCC") ? "" : ncc.split(" - ")[0];
        String maNV  = nv.equals("Tất cả NV")  ? "" : nv.split(" - ")[0];

        pntablemodel.setRowCount(0);
        ArrayList<PhieuNhap> danhSach = pnbus.getPhieuNhap();

        for (PhieuNhap pn : danhSach) {
            boolean matchNCC = maNCC.isEmpty() || pn.getMaNCC().equals(maNCC);
            boolean matchNV  = maNV.isEmpty()  || pn.getMaNV().equals(maNV);

            if (matchNCC && matchNV) {
                Object[] row = {
                    pn.getMaPN(),
                    pn.getMaNV(),
                    pn.getMaNCC(),
                    pn.getNgayLap(),
                    pn.getTongTien()
                };
                pntablemodel.addRow(row);
            }
        }
    }
    public void LoadDuLieu() {
        pntablemodel.setRowCount(0);
        ArrayList<PhieuNhap> danhSach = pnbus.getPhieuNhap();
        for (PhieuNhap pn : danhSach) {
            Object[] row = {
                pn.getMaPN(),
                pn.getMaNV(),
                pn.getMaNCC(),
                pn.getNgayLap(),
                pn.getTongTien()
            };
           pntablemodel.addRow(row);
        }
    }
    public void ClickPnHienChiTiet() {
        tbpn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow=tbpn.getSelectedRow();
                if(selectedRow != -1) {
                    String mapn=pntablemodel.getValueAt(selectedRow, 0).toString();
                    LoadChiTiet(mapn);
                }
            }
            
        });
    }
    public void LoadChiTiet(String mapn) {
        cttablemodel.setRowCount(0); 
        ArrayList<ChiTietPhieuNhap> danhSach = ctbus.GetChiTietPhieuNhap(mapn);
        for (ChiTietPhieuNhap ct : danhSach) {
        Object[] row = {
            ct.getMaPN(),
            ct.getMaSach(),
            ct.getSoLuong(),
            ct.getDonGia(),
            ct.getThanhTien()
        };
        cttablemodel.addRow(row);
    }
        
    }
}
