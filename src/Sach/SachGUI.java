package Sach;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 *
 * @author Quyen
 */
public class SachGUI extends JPanel{
    
    SachBUS sachbus = new SachBUS(); 
    JFrame themform,suaform; 
    JPanel pbutton,ptimkiem,pcontainer,inputpanel,ptable,ptitle;
    JButton btnthem,btnxoa,btnsua,btntimkiem,btnluu;
    JTextField txttk,txtma,txttensach,txtmatg,txtmatl,txtnam,txtnxb,txtdongia,txtsoluong,txtgiamin,txtgiamax;
    JComboBox cbtk;
    DefaultComboBoxModel cbmdtk;
    JTable tbsach;
    DefaultTableModel tbmodel;
    Vector<String> header;
    JLabel lbtitle,lbma,lbten,lbmatg,lbmatl,lbnam,lbnxb,lbdongia,lbtxtsoluong,lbden;
    
    public void loadData(){
        ArrayList<Sach> dstmp = new ArrayList<Sach>();
        sachbus.sachreload();
        dstmp = sachbus.getSachBUS();
        for(Sach temp : dstmp){
            Vector<String> row = new Vector<>();
            row.add(temp.getMasach());
            row.add(temp.getTensach());
            row.add(temp.getMatg());
            row.add(temp.getMatl());
            row.add(Integer.toString(temp.getNamxuatban()));
            row.add(temp.getManxb());
            row.add(Integer.toString(temp.getDongia()));
            row.add(Integer.toString(temp.getSoluongton()));
            tbmodel.addRow(row);
        }
    }
    
    public void themForm(){
        themform = new JFrame("Form Them");
        themform.setSize(500, 500);
        themform.setLayout(new BorderLayout());
        
        lbma = new JLabel("Mã Sách");
        lbten = new JLabel("Tên sách");
        lbmatg = new JLabel("Mã tác giả");
        lbmatl = new JLabel("Mã thể loại");
        lbnam = new JLabel("Năm xuất bản");
        lbnxb = new JLabel("Nhà xuất bản");
        lbdongia = new JLabel("Đơn giá");
        lbtxtsoluong = new JLabel("Số lượng tồn");
        
        txtma = new JTextField(sachbus.autoThemMa());
        txtma.setEditable(false);
        txttensach = new JTextField(20);
        txtmatg = new JTextField(20);
        txtmatl = new JTextField(20);
        txtnam = new JTextField(20);
        txtnxb = new JTextField(20);
        txtdongia = new JTextField(20);
        txtsoluong = new JTextField(20);
        
        inputpanel = new JPanel();
        inputpanel.setLayout(new GridLayout(8,2));
        inputpanel.add(lbma);
        inputpanel.add(txtma);
        inputpanel.add(lbten);
        inputpanel.add(txttensach);
        inputpanel.add(lbmatg);
        inputpanel.add(txtmatg);
        inputpanel.add(lbmatl);
        inputpanel.add(txtmatl);
        inputpanel.add(lbnam);
        inputpanel.add(txtnam);
        inputpanel.add(lbnxb);
        inputpanel.add(txtnxb);
        inputpanel.add(lbdongia);
        inputpanel.add(txtdongia);
        inputpanel.add(lbtxtsoluong);
        inputpanel.add(txtsoluong);
        
        themform.add(inputpanel, BorderLayout.CENTER);
        btnluu = new JButton("Lưu");
        btnluu.addActionListener(e -> {
            try {
                Sach sach = new Sach();
                sach.setMasach(txtma.getText());
                sach.setTensach(txttensach.getText());
                sach.setMatg(txtmatg.getText());
                sach.setMatl(txtmatl.getText());
                sach.setNamxuatban(Integer.parseInt(txtnam.getText()));
                sach.setManxb(txtnxb.getText());
                sach.setDongia(Integer.parseInt(txtdongia.getText()));
                sach.setSoluongton(Integer.parseInt(txtsoluong.getText()));
                    
                Result themsach = sachbus.themSach(sach);
                JOptionPane.showMessageDialog(this, themsach.getMessage());
                if (themsach == Result.thanhcong) {
                    Vector<String> row = new Vector<>();
                    row.add(txtma.getText());
                    row.add(txttensach.getText());
                    row.add(txtmatg.getText());
                    row.add(txtmatl.getText());
                    row.add(txtnam.getText());
                    row.add(txtnxb.getText());
                    row.add(txtdongia.getText());
                    row.add(txtsoluong.getText());
                    tbmodel.addRow(row);
                    sachbus.sachreload();
                    themform.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(themform, "Vui lòng nhập đúng định dạng số cho năm xuất bản, đơn giá và số lượng tồn.");
            }
        });
        
        themform.add(btnluu, BorderLayout.SOUTH);
        themform.setVisible(true);
        
    }
    
    public void timkiemdongia(){
        cbtk.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                boolean isdongia = cbtk.getSelectedIndex() == 6;
                
                txttk.setVisible(!isdongia);
                txtgiamin.setVisible(isdongia);
                lbden.setVisible(isdongia);
                txtgiamax.setVisible(isdongia);
                
                txttk.setText("");
                txtgiamin.setText("");
                txtgiamax.setText("");
                
                ptimkiem.revalidate();
                ptimkiem.repaint();
            }
        });
    }
    
    public void timkiem(){
        ArrayList<Sach> dstemp = new ArrayList<>();
    tbmodel.setRowCount(0);

    try {
        int idx = cbtk.getSelectedIndex();

        if (idx == 6) {
            String minStr = txtgiamin.getText().trim();
            String maxStr = txtgiamax.getText().trim();

            if (minStr.isEmpty() || maxStr.isEmpty()) {
                dstemp = sachbus.getSachBUS();
            } else {
                int giamin = Integer.parseInt(minStr);
                int giamax = Integer.parseInt(maxStr);

                if (giamin > giamax) {
                    int t = giamin; giamin = giamax; giamax = t;
                }

                dstemp = sachbus.timSachTheoDonGiaTrongKhoang(giamin, giamax);
            }
        } else {
            String text = txttk.getText().trim();
            if (text.isEmpty()) {
                dstemp = sachbus.getSachBUS();
            } else {
                switch (idx) {
                    case 0:
                        Sach s = sachbus.timSachTheoMa(text);
                        if (s != null) dstemp.add(s);
                        break;
                    case 1:
                        dstemp = sachbus.timSachTheoTen(text);
                        break;
                    case 2:
                        dstemp = sachbus.timSachTheoMaTacGia(text);
                        break;
                    case 3:
                        dstemp = sachbus.timSachTheoMaTheLoai(text);
                        break;
                    case 4:
                        dstemp = sachbus.timSachTheoNamXuatBan(Integer.parseInt(text));
                        break;
                    case 5:
                        dstemp = sachbus.timSachTheoNhaXuatBan(text);
                        break;
                }
            }
        }

        for (Sach temp : dstemp) {
            Vector<String> row = new Vector<>();
            row.add(temp.getMasach());
            row.add(temp.getTensach());
            row.add(temp.getMatg());
            row.add(temp.getMatl());
            row.add(Integer.toString(temp.getNamxuatban()));
            row.add(temp.getManxb());
            row.add(Integer.toString(temp.getDongia()));
            row.add(Integer.toString(temp.getSoluongton()));
            tbmodel.addRow(row);
        }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng số (năm, giá min, giá max).");
        }
    }
    
    public void suaForm(){
        suaform = new JFrame("Form Them");
        suaform.setSize(500, 500);
        suaform.setLayout(new BorderLayout());
        
        lbma = new JLabel("Mã Sách");
        lbten = new JLabel("Tên sách");
        lbmatg = new JLabel("Mã tác giả");
        lbmatl = new JLabel("Mã thể loại");
        lbnam = new JLabel("Năm xuất bản");
        lbnxb = new JLabel("Nhà xuất bản");
        lbdongia = new JLabel("Đơn giá");
        lbtxtsoluong = new JLabel("Số lượng tồn");
        
        txtma = new JTextField(20);
        txtma.setEditable(false);
        txttensach = new JTextField(20);
        txtmatg = new JTextField(20);
        txtmatl = new JTextField(20);
        txtnam = new JTextField(20);
        txtnxb = new JTextField(20);
        txtdongia = new JTextField(20);
        txtsoluong = new JTextField(20);
        
        int i = tbsach.getSelectedRow();
        txtma.setText(tbsach.getValueAt(i, 0).toString());
        txttensach.setText(tbsach.getValueAt(i, 1).toString());
        txtmatg.setText(tbsach.getValueAt(i, 2).toString());
        txtmatl.setText(tbsach.getValueAt(i, 3).toString());
        txtnam.setText(tbsach.getValueAt(i, 4).toString());
        txtnxb.setText(tbsach.getValueAt(i, 5).toString());
        txtdongia.setText(tbsach.getValueAt(i, 6).toString());
        txtsoluong.setText(tbsach.getValueAt(i, 7).toString());
        
        inputpanel = new JPanel();
        inputpanel.setLayout(new GridLayout(8,2,10,10));
        inputpanel.add(lbma);
        inputpanel.add(txtma);
        inputpanel.add(lbten);
        inputpanel.add(txttensach);
        inputpanel.add(lbmatg);
        inputpanel.add(txtmatg);
        inputpanel.add(lbmatl);
        inputpanel.add(txtmatl);
        inputpanel.add(lbnam);
        inputpanel.add(txtnam);
        inputpanel.add(lbnxb);
        inputpanel.add(txtnxb);
        inputpanel.add(lbdongia);
        inputpanel.add(txtdongia);
        inputpanel.add(lbtxtsoluong);
        inputpanel.add(txtsoluong);
        
        suaform.add(inputpanel, BorderLayout.CENTER);
        
        btnluu = new JButton("Lưu");
        btnluu.addActionListener(e -> {
            try {
                Sach sach = new Sach();
                sach.setMasach(txtma.getText());
                sach.setTensach(txttensach.getText());
                sach.setMatg(txtmatg.getText());
                sach.setMatl(txtmatl.getText());
                sach.setNamxuatban(Integer.parseInt(txtnam.getText()));
                sach.setManxb(txtnxb.getText());
                sach.setDongia(Integer.parseInt(txtdongia.getText()));
                sach.setSoluongton(Integer.parseInt(txtsoluong.getText()));
                
                Result suasach = sachbus.suaSach(sach);
                JOptionPane.showMessageDialog(this, suasach.getMessage());
                
                if (suasach == Result.thanhcong) {
                    if(i >= 0){
                        tbmodel.setValueAt(sach.getTensach(),i,1);
                        tbmodel.setValueAt(sach.getMatg(),i,2);
                        tbmodel.setValueAt(sach.getMatl(),i,3);
                        tbmodel.setValueAt(sach.getNamxuatban(),i,4);
                        tbmodel.setValueAt(sach.getManxb(),i,5);
                        tbmodel.setValueAt(sach.getDongia(),i,6);
                        tbmodel.setValueAt(sach.getSoluongton(),i,7);
                    }
                    sachbus.sachreload();
                    suaform.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(themform, "Vui lòng nhập đúng định dạng số cho năm xuất bản, đơn giá và số lượng tồn.");
            }
        });
        
        suaform.add(btnluu, BorderLayout.SOUTH);
        suaform.setVisible(true);
    }
    
    public void xoa(){
        int i = tbsach.getSelectedRow();
        String ma = tbsach.getValueAt(i, 0).toString(); 
               
        Result xoasach = sachbus.xoaSach(ma);
        JOptionPane.showMessageDialog(this, xoasach.getMessage());
        if(xoasach == Result.thanhcong){
            tbmodel.removeRow(i);
            sachbus.sachreload();
        }
    }
    
    public SachGUI(){
        setLayout(new BorderLayout());
        
        //chuc nang
        btnthem = new JButton("Thêm");
        btnxoa = new JButton("Xoá");
        btnsua = new JButton("Sửa");
        pbutton = new JPanel();
        pbutton.add(btnthem);
        pbutton.add(btnxoa);
        pbutton.add(btnsua);
        
        //search panel
        cbmdtk = new DefaultComboBoxModel<>();
        cbmdtk.addElement("Ma sach");
        cbmdtk.addElement("Ten sach");
        cbmdtk.addElement("Ma tac gia");
        cbmdtk.addElement("Ma the loai");
        cbmdtk.addElement("Nam xuat ban");
        cbmdtk.addElement("Nha xuat ban");
        cbmdtk.addElement("don gia");
        cbtk = new JComboBox(cbmdtk);
        txttk = new JTextField(25);
        
        txtgiamin = new JTextField(10);
        lbden = new JLabel("đến");
        txtgiamax = new JTextField(10);
        txtgiamin.setVisible(false);
        txtgiamax.setVisible(false);
        lbden.setVisible(false);
        
        btntimkiem = new JButton("Tìm kiếm");
        ptimkiem = new JPanel();
        ptimkiem.add(cbtk);
        ptimkiem.add(txttk);
        ptimkiem.add(txtgiamin);
        ptimkiem.add(lbden);
        ptimkiem.add(txtgiamax);
        ptimkiem.add(btntimkiem);
        
        //toolbar container
        pcontainer = new JPanel();
        pcontainer.setLayout(new BorderLayout());
        pcontainer.add(pbutton, BorderLayout.WEST);
        pcontainer.add(ptimkiem, BorderLayout.EAST);
        pcontainer.setBorder(new EmptyBorder(40, 0, 8, 0));
        
        //table
        header = new Vector<>();
        header.add("Ma sach");
        header.add("Ten sach");
        header.add("Ma tac gia");
        header.add("Ma the loai");
        header.add("Nam xuat ban");
        header.add("Ma nha xuat ban");
        header.add("Don gia");
        header.add("So luong ton");
        tbmodel = new DefaultTableModel(header,0);
        tbsach = new JTable(tbmodel);
        ptable = new JPanel();
        ptable.setLayout(new BorderLayout());
        JScrollPane sptbsach = new JScrollPane(tbsach);
        ptable.add(sptbsach);
        
        ptitle = new JPanel();
        lbtitle = new JLabel("QUẢN LÝ SÁCH");
        ptitle.add(lbtitle);
        
        add(ptitle, BorderLayout.NORTH);
        add(pcontainer, BorderLayout.CENTER);
        add(ptable, BorderLayout.SOUTH);
        
        loadData();
        timkiemdongia();
        
        btnthem.addActionListener(e -> {
            try{
                themForm();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        
        btnsua.addActionListener(e -> {
            try{
                suaForm();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        
        btnxoa.addActionListener(e -> {
            try{
                xoa();
            }catch(Exception ex){
                ex.printStackTrace();
            } 
        });
        
        btntimkiem.addActionListener(e ->{
            try{
                timkiem();
            }catch (Exception ex) {
                ex.printStackTrace();
            } 
        });
    }
}
