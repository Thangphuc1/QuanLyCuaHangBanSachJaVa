package KhuyenMai;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class KhuyenMaiGUI extends JFrame {

    KhuyenMaiBUS kmBUS = new KhuyenMaiBUS();

    JTextField txtMa, txtTen, txtBD, txtKT, txtDK, txtGhiChu;
    JTextField txtSearch;

    JTable table;
    DefaultTableModel model;

    JButton btnThem, btnSua, btnXoa, btnLoad, btnSearch;

    public KhuyenMaiGUI() {

        setTitle("Quản lý khuyến mãi");
        setSize(900,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
        loadTable();
    }

    private void init(){

        setLayout(new BorderLayout());

        // ===== PANEL INPUT =====
        JPanel pInput = new JPanel(new GridLayout(6,2,5,5));

        txtMa = new JTextField();
        txtTen = new JTextField();
        txtBD = new JTextField();
        txtKT = new JTextField();
        txtDK = new JTextField();
        txtGhiChu = new JTextField();

        pInput.add(new JLabel("Mã KM"));
        pInput.add(txtMa);

        pInput.add(new JLabel("Tên KM"));
        pInput.add(txtTen);

        pInput.add(new JLabel("Ngày bắt đầu (yyyy-mm-dd)"));
        pInput.add(txtBD);

        pInput.add(new JLabel("Ngày kết thúc (yyyy-mm-dd)"));
        pInput.add(txtKT);

        pInput.add(new JLabel("Điều kiện"));
        pInput.add(txtDK);

        pInput.add(new JLabel("Ghi chú"));
        pInput.add(txtGhiChu);

        add(pInput,BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "Mã KM","Tên KM","Ngày BD","Ngày KT","DKTT","Ghi chú"
        });

        table = new JTable(model);

        add(new JScrollPane(table),BorderLayout.CENTER);

        // ===== SEARCH =====
        JPanel pSearch = new JPanel();

        txtSearch = new JTextField(20);
        btnSearch = new JButton("Tìm");

        pSearch.add(new JLabel("Tìm kiếm"));
        pSearch.add(txtSearch);
        pSearch.add(btnSearch);

        add(pSearch,BorderLayout.WEST);

        // ===== BUTTON =====
        JPanel pButton = new JPanel();

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLoad = new JButton("Load");

        pButton.add(btnThem);
        pButton.add(btnSua);
        pButton.add(btnXoa);
        pButton.add(btnLoad);

        add(pButton,BorderLayout.SOUTH);

        // ===== EVENT =====

        btnThem.addActionListener(e -> them());
        btnSua.addActionListener(e -> sua());
        btnXoa.addActionListener(e -> xoa());
        btnLoad.addActionListener(e -> loadTable());
        btnSearch.addActionListener(e -> search());

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                fill();
            }
        });
    }

    // ===== LOAD TABLE =====
    private void loadTable(){

        model.setRowCount(0);

        ArrayList<KhuyenMai> list = kmBUS.getAll();

        for(KhuyenMai km : list){

            model.addRow(new Object[]{
                    km.getMaKM(),
                    km.getTenKM(),
                    km.getNgayBD(),
                    km.getNgayKT(),
                    km.getDKTT(),
                    km.getGhichu()
            });
        }
    }

    // ===== LẤY DỮ LIỆU FORM =====
    private KhuyenMai getForm(){

        String ma = txtMa.getText();
        String ten = txtTen.getText();
        LocalDate bd = LocalDate.parse(txtBD.getText());
        LocalDate kt = LocalDate.parse(txtKT.getText());
        int dk = Integer.parseInt(txtDK.getText());
        String gc = txtGhiChu.getText();

        return new KhuyenMai(ma,ten,bd,kt,dk,gc);
    }

    // ===== THÊM =====
    private void them(){

        KhuyenMai km = getForm();

        if(kmBUS.add(km)){
            JOptionPane.showMessageDialog(this,"Thêm thành công");
            loadTable();
        }else{
            JOptionPane.showMessageDialog(this,"Thêm thất bại");
        }
    }

    // ===== SỬA =====
    private void sua(){

        KhuyenMai km = getForm();

        if(kmBUS.update(km)){
            JOptionPane.showMessageDialog(this,"Sửa thành công");
            loadTable();
        }else{
            JOptionPane.showMessageDialog(this,"Sửa thất bại");
        }
    }

    // ===== XÓA =====
    private void xoa(){

        String ma = txtMa.getText();

        if(kmBUS.delete(ma)){
            JOptionPane.showMessageDialog(this,"Xóa thành công");
            loadTable();
        }else{
            JOptionPane.showMessageDialog(this,"Xóa thất bại");
        }
    }

    // ===== FILL FORM =====
    private void fill(){

        int row = table.getSelectedRow();

        txtMa.setText(model.getValueAt(row,0).toString());
        txtTen.setText(model.getValueAt(row,1).toString());
        txtBD.setText(model.getValueAt(row,2).toString());
        txtKT.setText(model.getValueAt(row,3).toString());
        txtDK.setText(model.getValueAt(row,4).toString());
        txtGhiChu.setText(model.getValueAt(row,5).toString());
    }

    // ===== SEARCH =====
    private void search(){

        String key = txtSearch.getText();

        ArrayList<KhuyenMai> list = kmBUS.search(key);

        model.setRowCount(0);

        for(KhuyenMai km : list){

            model.addRow(new Object[]{
                    km.getMaKM(),
                    km.getTenKM(),
                    km.getNgayBD(),
                    km.getNgayKT(),
                    km.getDKTT(),
                    km.getGhichu()
            });
        }
    }

    public static void main(String[] args) {

        new KhuyenMaiGUI().setVisible(true);
    }
}