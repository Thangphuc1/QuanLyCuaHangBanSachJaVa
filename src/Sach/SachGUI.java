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
    JPanel pbutton,ptimkiem,pcontainer,inputpanel,ptable,ptitle,ptoolbar,pformtitle,pformbutton;
    JButton btnthem,btnxoa,btnsua,btntimkiem,btnluu,btnhuy;
    JTextField txttk,txtma,txttensach,txtmatg,txtmatl,txtnam,txtnxb,txtdongia,txtsoluong,txtgiamin,txtgiamax;
    JComboBox cbtk;
    DefaultComboBoxModel cbmdtk;
    JTable tbsach;
    DefaultTableModel tbmodel;
    Vector<String> header;
    JLabel lbtitle,lbma,lbten,lbmatg,lbmatl,lbnam,lbnxb,lbdongia,lbsoluong,lbden,lbformtitle;
    JTableHeader th;
    
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
    
    private void thutLabel(JLabel lb, int left) {
        lb.setBorder(new EmptyBorder(0, left, 0, 0));
    }
    
    private void styleComboBox(JComboBox cb) {
        cb.setPreferredSize(new Dimension(110, 38));
        cb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cb.setBackground(Color.WHITE);
        cb.setForeground(new Color(33, 37, 41));
        cb.setFocusable(false);
        cb.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
    }
    
    private void styleTextField(JTextField txt) {
        txt.setPreferredSize(new Dimension(220, 38));
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txt.setBackground(Color.WHITE);
        txt.setForeground(new Color(33, 37, 41));
        txt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
    }
    
    private void styleButton(JButton btn, Color bg) {
        btn.setPreferredSize(new Dimension(90, 38));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    public void themForm(){
        themform = new JFrame("Form Them");
        themform.setSize(500, 500);
        themform.setLayout(new BorderLayout());
        
        pformtitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lbformtitle = new JLabel("FORM THÊM");
        pformtitle.add(lbformtitle);
        lbformtitle.setFont(new Font("Segoe UI",Font.BOLD,30));
        pformtitle.setBackground(new Color(255, 253, 208));
        
        
        lbma = new JLabel("Mã Sách");
        thutLabel(lbma,15);
        lbten = new JLabel("Tên sách");
        thutLabel(lbten,15);
        lbmatg = new JLabel("Mã tác giả");
        thutLabel(lbmatg,15);
        lbmatl = new JLabel("Mã thể loại");
        thutLabel(lbmatl,15);
        lbnam = new JLabel("Năm xuất bản");
        thutLabel(lbnam,15);
        lbnxb = new JLabel("Nhà xuất bản");
        thutLabel(lbnxb,15);
        lbdongia = new JLabel("Đơn giá");
        thutLabel(lbdongia,15);
        lbsoluong = new JLabel("Số lượng tồn");
        thutLabel(lbsoluong,15);
        
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
        inputpanel.add(lbsoluong);
        inputpanel.add(txtsoluong);
        
        inputpanel.setBackground(new Color(255, 253, 208));
        
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
        
        btnhuy = new JButton("Huỷ");
        btnhuy.addActionListener(e -> {
            try{
                themform.dispose();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        
        styleButton(btnluu, new Color(46, 204, 113));
        styleButton(btnhuy, new Color(231, 76, 60));
        
        pformbutton = new JPanel();
        pformbutton.add(btnluu);
        pformbutton.add(btnhuy);
        pformbutton.setBackground(new Color(255, 253, 208));
        
        themform.add(pformtitle, BorderLayout.NORTH);
        themform.add(inputpanel, BorderLayout.CENTER);
        themform.add(pformbutton, BorderLayout.SOUTH);
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
        suaform = new JFrame("Form Sửa");
        suaform.setSize(500, 500);
        suaform.setLayout(new BorderLayout());
        
        pformtitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lbformtitle = new JLabel("FORM SỬA");
        pformtitle.add(lbformtitle);
        lbformtitle.setFont(new Font("Segoe UI",Font.BOLD,30));
        pformtitle.setBackground(new Color(255, 253, 208));
        
        lbma = new JLabel("Mã Sách");
        thutLabel(lbma,15);
        lbten = new JLabel("Tên sách");
        thutLabel(lbten,15);
        lbmatg = new JLabel("Mã tác giả");
        thutLabel(lbmatg,15);
        lbmatl = new JLabel("Mã thể loại");
        thutLabel(lbmatl,15);
        lbnam = new JLabel("Năm xuất bản");
        thutLabel(lbnam,15);
        lbnxb = new JLabel("Nhà xuất bản");
        thutLabel(lbnxb,15);
        lbdongia = new JLabel("Đơn giá");
        thutLabel(lbdongia,15);
        lbsoluong = new JLabel("Số lượng tồn");
        thutLabel(lbsoluong,15);
        
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
        inputpanel.add(lbsoluong);
        inputpanel.add(txtsoluong);
        inputpanel.setBackground(new Color(255, 253, 208));
        
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
        
        btnhuy = new JButton("Huỷ");
        btnhuy.addActionListener(e -> {
            try{
                themform.dispose();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        
        styleButton(btnluu, new Color(46, 204, 113));
        styleButton(btnhuy, new Color(231, 76, 60));
        
        pformbutton = new JPanel();
        pformbutton.add(btnluu);
        pformbutton.add(btnhuy);
        pformbutton.setBackground(new Color(255, 253, 208));
        
        suaform.add(pformtitle, BorderLayout.NORTH);
        suaform.add(inputpanel, BorderLayout.CENTER);
        suaform.add(pformbutton, BorderLayout.SOUTH);
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
        styleButton(btnthem, new Color(46, 204, 113));
        styleButton(btnxoa, new Color(231, 76, 60));
        styleButton(btnsua, new Color(52, 152, 219));
        
        pbutton = new JPanel();
        pbutton.add(btnthem);
        pbutton.add(btnxoa);
        pbutton.add(btnsua);
        pbutton.setBackground(new Color(255, 253, 208));
        
        //search panel
        cbmdtk = new DefaultComboBoxModel<>();
        cbmdtk.addElement("Mã sách");
        cbmdtk.addElement("Tên sách");
        cbmdtk.addElement("Mã tác giả");
        cbmdtk.addElement("Mã thể loại");
        cbmdtk.addElement("Năm xuất bản");
        cbmdtk.addElement("Nhà xuất bản");
        cbmdtk.addElement("Đơn giá");
        cbtk = new JComboBox(cbmdtk);
        txttk = new JTextField(50);
        
        txtgiamin = new JTextField(10);
        lbden = new JLabel("đến");
        txtgiamax = new JTextField(10);
        txtgiamin.setVisible(false);
        txtgiamax.setVisible(false);
        lbden.setVisible(false);
        
        btntimkiem = new JButton("Tìm");
        ptimkiem = new JPanel();
        ptimkiem.add(cbtk);
        ptimkiem.add(txttk);
        ptimkiem.add(txtgiamin);
        ptimkiem.add(lbden);
        ptimkiem.add(txtgiamax);
        ptimkiem.add(btntimkiem);
        styleButton(btntimkiem, new Color(241, 196, 15));
        ptimkiem.setBackground(new Color(255, 253, 208));
        
        styleComboBox(cbtk);
        styleTextField(txttk);
        styleTextField(txtgiamin);
        styleTextField(txtgiamax);
        
        //toolbar container
        ptoolbar = new JPanel();
        ptoolbar.setLayout(new BorderLayout());
        ptoolbar.add(pbutton, BorderLayout.WEST);
        ptoolbar.add(ptimkiem, BorderLayout.EAST);
        ptoolbar.setBorder(new EmptyBorder(30, 0, 8, 0));
        ptoolbar.setBackground(new Color(255, 253, 208));
        //table
        header = new Vector<>();
        header.add("Mã sách");
        header.add("Tên sách");
        header.add("Mã tác giả");
        header.add("Mã thể loại");
        header.add("Năm xuất bản");
        header.add("Mã nhà xuất bản");
        header.add("Đơn giá");
        header.add("Số lượng tồn");
        tbmodel = new DefaultTableModel(header,0);
        tbsach = new JTable(tbmodel);
        tbsach.setFillsViewportHeight(true);
        tbsach.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tbsach.setForeground(Color.BLACK);
        tbsach.setBackground(Color.WHITE);
        tbsach.setGridColor(new Color(120, 120, 120));
        tbsach.setShowGrid(true);
        tbsach.setBackground(new Color(255, 253, 240));
        tbsach.setForeground(Color.BLACK);
        
        th = tbsach.getTableHeader();
        th.setBackground(new Color(255, 224, 178));
        th.setForeground(Color.BLACK);
        th.setFont(new Font("Segoe UI", Font.BOLD, 14));
        th.setReorderingAllowed(false);
        
        ptable = new JPanel();
        ptable.setLayout(new BorderLayout());
        
        JScrollPane sptbsach = new JScrollPane(tbsach);
        ptable.add(sptbsach, BorderLayout.CENTER);
        
        ptitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ptitle.setBackground(new Color(255, 253, 208));
        lbtitle = new JLabel("QUẢN LÝ SÁCH");
        lbtitle.setFont(new Font("Segoe UI",Font.BOLD,50));
        ptitle.add(lbtitle);
        
        pcontainer = new JPanel(new BorderLayout());
        pcontainer.add(ptoolbar, BorderLayout.NORTH);
        pcontainer.add(ptable, BorderLayout.CENTER);
        
        add(ptitle, BorderLayout.NORTH);
        add(pcontainer, BorderLayout.CENTER);
        
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
