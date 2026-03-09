/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HoaDon;

/**
 *
 * @author Asus
 */
import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ThemHoaDonGUI extends JFrame {

    JTextField txtNgay = new JTextField(10);
    JTextField txtTenSach = new JTextField(15);
    JTextField txtSoLuong = new JTextField(5);
    JTextField txtDonGia = new JTextField(10);
    JTextField txtTongTien = new JTextField(10);

    JButton btnThemCT = new JButton("Thêm chi tiết");
    JButton btnTinhTong = new JButton("Tổng tiền");
    JButton btnLuu = new JButton("Lưu hóa đơn");

    JTable tableSach;
    JTable tableCTHD;

    DefaultTableModel modelSach;
    DefaultTableModel modelCTHD;

    HoaDonGUI parent;

    public ThemHoaDonGUI(HoaDonGUI parent){

        this.parent = parent;

        setTitle("Thêm hóa đơn");
        setSize(900,600);
        setLayout(new BorderLayout());

        taoGiaoDien();

        setVisible(true);
    }

    private void taoGiaoDien(){

        // ===== PANEL TRÊN =====
        Panel pTop = new Panel();

        txtNgay.setText(LocalDate.now().toString());
        txtNgay.setEditable(false);

        pTop.add(new Label("Ngày:"));
        pTop.add(txtNgay);

        add(pTop,BorderLayout.NORTH);

        // ===== TABLE SÁCH =====
        modelSach = new DefaultTableModel(
                new Object[]{"Mã sách","Tên sách","Đơn giá","Tồn"},0
        );

        tableSach = new JTable(modelSach);
        JScrollPane scrollSach = new JScrollPane(tableSach);
        

        
        // ===== TABLE CHI TIẾT =====
        modelCTHD = new DefaultTableModel(
                new Object[]{"Tên sách","Số lượng","Đơn giá","Thành tiền"},0
        );

        tableCTHD = new JTable(modelCTHD);
        JScrollPane scrollCTHD = new JScrollPane(tableCTHD);

        // ===== PANEL CENTER =====
        Panel pCenter = new Panel(new GridLayout(1,2));

        pCenter.add(scrollSach);
        pCenter.add(scrollCTHD);

        add(pCenter,BorderLayout.CENTER);

        // ===== PANEL DƯỚI =====
        Panel pBottom = new Panel();

        pBottom.add(new Label("Tên sách"));
        pBottom.add(txtTenSach);

        pBottom.add(new Label("Số lượng"));
        pBottom.add(txtSoLuong);

        pBottom.add(new Label("Đơn giá"));
        pBottom.add(txtDonGia);

        pBottom.add(btnThemCT);

        pBottom.add(btnTinhTong);

        pBottom.add(new Label("Tổng tiền"));
        pBottom.add(txtTongTien);

        pBottom.add(btnLuu);

        add(pBottom,BorderLayout.SOUTH);
        
        loadTableSach();

        suKien();
    }
    

    private void suKien(){

        // CLICK SÁCH
        tableSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){

                int row = tableSach.getSelectedRow();

                txtTenSach.setText(
                        tableSach.getValueAt(row,1).toString()
                );

                txtDonGia.setText(
                        tableSach.getValueAt(row,2).toString()
                );

            }
        });

        // THÊM CHI TIẾT
        btnThemCT.addActionListener(e->{

            String tenSach = txtTenSach.getText();
            int soLuong = Integer.parseInt(txtSoLuong.getText());
            double donGia = Double.parseDouble(txtDonGia.getText());

            double thanhTien = soLuong * donGia;

            modelCTHD.addRow(new Object[]{
                    tenSach,
                    soLuong,
                    donGia,
                    thanhTien
            });

        });

        // TỔNG TIỀN
        btnTinhTong.addActionListener(e->{

            double tong = 0;

            for(int i=0;i<modelCTHD.getRowCount();i++){

                tong += Double.parseDouble(
                        modelCTHD.getValueAt(i,3).toString()
                );
            }

            txtTongTien.setText(String.valueOf(tong));

        });

        // LƯU HÓA ĐƠN
        btnLuu.addActionListener(e->{

            JOptionPane.showMessageDialog(this,"Thêm hóa đơn thành công");

            dispose();

        });

    }

}

