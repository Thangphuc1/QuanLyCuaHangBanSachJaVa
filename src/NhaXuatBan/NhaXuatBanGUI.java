package NhaXuatBan;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.*;
import java.sql.*;
import java.util.Set;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 *
 * @author Quyen
 */
public class NhaXuatBanGUI extends JPanel{
    
    NhaXuatBanBUS nhaxuatbanbus = new NhaXuatBanBUS(); 
    JFrame themform,suaform;
    JPanel pbutton,ptimkiem,pcontainer,inputpanel,ptable,ptitle,ptoolbar,pformtitle,pformbutton;
    JButton btnthem,btnxoa,btnsua,btntimkiem,btnluu,btnhuy;
    JTextField txttk,txtma,txttennxb,txtdiachi,txtsdt,txtemail;
    JComboBox cbtk;
    DefaultComboBoxModel cbmdtk;
    JTable tbnhaxuatban;
    DefaultTableModel tbmodel;
    Vector<String> header;
    JLabel lbtitle,lbma,lbten,lbdiachi,lbemail,lbsdt,lbformtitle;
    JTableHeader th;
    
    public void loadData(){
        ArrayList<NhaXuatBan> dstmp = new ArrayList<NhaXuatBan>();
        nhaxuatbanbus.nhaxuatbanreload();
        dstmp = nhaxuatbanbus.getNhaXuatBanBUS();
        for(NhaXuatBan temp : dstmp){
            Vector<String> row = new Vector<>();
            row.add(temp.getManxb());
            row.add(temp.getTennxb());
            row.add(temp.getSdt());
            row.add(temp.getDiachi());
            row.add(temp.getEmail());
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
        
        lbma = new JLabel("Mã nhà xuất bản");
        thutLabel(lbma,15);
        lbten = new JLabel("Tên nhà xuất bản");
        thutLabel(lbten,15);
        lbsdt = new JLabel("Số điện thoại");
        thutLabel(lbsdt,15);
        lbdiachi = new JLabel("Địa chỉ");
        thutLabel(lbdiachi,15);
        lbemail = new JLabel("Email");
        thutLabel(lbemail,15);
        
        txtma = new JTextField(nhaxuatbanbus.autoThemMa());
        txtma.setEditable(false);
        txttennxb = new JTextField(20);
        txtsdt = new JTextField(20);
        txtdiachi = new JTextField(20);
        txtemail = new JTextField(20);
        
        inputpanel = new JPanel();
        inputpanel.setLayout(new GridLayout(5,2,10,10));
        inputpanel.add(lbma);
        inputpanel.add(txtma);
        inputpanel.add(lbten);
        inputpanel.add(txttennxb);
        inputpanel.add(lbsdt);
        inputpanel.add(txtsdt);
        inputpanel.add(lbdiachi);
        inputpanel.add(txtdiachi);
        inputpanel.add(lbemail);
        inputpanel.add(txtemail);
        inputpanel.setBackground(new Color(255, 253, 208));
        
        btnluu = new JButton("Lưu");
        btnluu.addActionListener(e -> {
            try {
                NhaXuatBan nhaxuatban = new NhaXuatBan();
                nhaxuatban.setManxb(txtma.getText());
                nhaxuatban.setTennxb(txttennxb.getText());
                nhaxuatban.setSdt(txtsdt.getText());
                nhaxuatban.setDiachi(txtdiachi.getText());
                nhaxuatban.setEmail(txtemail.getText());
                
                Result themnhaxuatban = nhaxuatbanbus.themNhaXuatBan(nhaxuatban);
                JOptionPane.showMessageDialog(this, themnhaxuatban.getMessage());
                if (themnhaxuatban == Result.thanhcong) {
                    Vector<String> row = new Vector<>();
                    row.add(txtma.getText());
                    row.add(txttennxb.getText());
                    row.add(txtsdt.getText());
                    row.add(txtdiachi.getText());
                    row.add(txtemail.getText());
                    tbmodel.addRow(row);
                    nhaxuatbanbus.nhaxuatbanreload();
                    themform.dispose();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
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
    
    public void timkiem(){
        ArrayList<NhaXuatBan> dstemp = new ArrayList<>();
        tbmodel.setRowCount(0);

        try {
            int idx = cbtk.getSelectedIndex();
            String text = txttk.getText().trim();
            if (text.isEmpty()) {
                dstemp = nhaxuatbanbus.getNhaXuatBanBUS();
            } else {
                switch (idx) {
                case 0:
                    NhaXuatBan s = nhaxuatbanbus.timNhaXuatBanTheoMa(text);
                    if (s != null) dstemp.add(s);
                    break;
                case 1:
                    dstemp = nhaxuatbanbus.timNhaXuatBanTheoTen(text);
                    break;
                }
            }
            
            for (NhaXuatBan temp : dstemp) {
                Vector<String> row = new Vector<>();
                row.add(temp.getManxb());
                row.add(temp.getTennxb());
                row.add(temp.getSdt());
                row.add(temp.getDiachi());
                row.add(temp.getEmail());
                tbmodel.addRow(row);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void suaForm(){
        suaform = new JFrame("Form Them");
        suaform.setSize(500, 500);
        suaform.setLayout(new BorderLayout());
        
        pformtitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lbformtitle = new JLabel("FORM SỬA");
        pformtitle.add(lbformtitle);
        lbformtitle.setFont(new Font("Segoe UI",Font.BOLD,30));
        pformtitle.setBackground(new Color(255, 253, 208));
        
        lbma = new JLabel("Mã nhà xuất bản");
        thutLabel(lbma,15);
        lbten = new JLabel("Tên nhà xuất bản");
        thutLabel(lbten,15);
        lbsdt = new JLabel("Số điện thoại");
        thutLabel(lbsdt,15);
        lbdiachi = new JLabel("Địa chỉ");
        thutLabel(lbdiachi,15);
        lbemail = new JLabel("Email");
        thutLabel(lbemail,15);
        
        txtma = new JTextField(20);
        txtma.setEditable(false);
        txttennxb = new JTextField(20);
        txtsdt = new JTextField(20);
        txtdiachi = new JTextField(20);
        txtemail = new JTextField(20);
        
        int i = tbnhaxuatban.getSelectedRow();
        txtma.setText(tbnhaxuatban.getValueAt(i, 0).toString());
        txttennxb.setText(tbnhaxuatban.getValueAt(i, 1).toString());
        txtsdt.setText(tbnhaxuatban.getValueAt(i, 2).toString());
        txtdiachi.setText(tbnhaxuatban.getValueAt(i, 3).toString());
        txtemail.setText(tbnhaxuatban.getValueAt(i,4).toString());
        
        inputpanel = new JPanel();
        inputpanel.setLayout(new GridLayout(5,2,10,10));
        inputpanel.add(lbma);
        inputpanel.add(txtma);
        inputpanel.add(lbten);
        inputpanel.add(txttennxb);
        inputpanel.add(lbsdt);
        inputpanel.add(txtsdt);
        inputpanel.add(lbdiachi);
        inputpanel.add(txtdiachi);
        inputpanel.add(lbemail);
        inputpanel.add(txtemail);
        inputpanel.setBackground(new Color(255, 253, 208));
        
        btnluu = new JButton("Lưu");
        btnluu.addActionListener(e -> {
            try {
                
                NhaXuatBan nhaxuatban = new NhaXuatBan();
                nhaxuatban.setManxb(txtma.getText());
                nhaxuatban.setTennxb(txttennxb.getText());
                nhaxuatban.setSdt(txtsdt.getText());
                nhaxuatban.setDiachi(txtdiachi.getText());
                nhaxuatban.setEmail(txtemail.getText());
                
                Result suanhaxuatban = nhaxuatbanbus.suaNhaXuatBan(nhaxuatban);
                JOptionPane.showMessageDialog(this, suanhaxuatban.getMessage());
                
                if (suanhaxuatban == Result.thanhcong) {
                    if(i >= 0){
                        tbmodel.setValueAt(nhaxuatban.getTennxb(),i, 1);
                        tbmodel.setValueAt(nhaxuatban.getSdt(),i, 2);
                        tbmodel.setValueAt(nhaxuatban.getDiachi(),i,3);
                        tbmodel.setValueAt(nhaxuatban.getEmail(),i,4);
                    }
                    nhaxuatbanbus.nhaxuatbanreload();
                    suaform.dispose();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
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
        int i = tbnhaxuatban.getSelectedRow();
        String ma = tbnhaxuatban.getValueAt(i, 0).toString(); 
               
        Result xoanhaxuatban = nhaxuatbanbus.xoaNhaXuatBan(ma);
        JOptionPane.showMessageDialog(this, xoanhaxuatban.getMessage());
        if(xoanhaxuatban == Result.thanhcong){
            tbmodel.removeRow(i);
            nhaxuatbanbus.nhaxuatbanreload();
        }
    }
    
    public NhaXuatBanGUI(){
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
        cbmdtk.addElement("Mã nhà xuất bản");
        cbmdtk.addElement("Tên nhà xuất bản");
        cbtk = new JComboBox(cbmdtk);
        txttk = new JTextField(25);
        
        btntimkiem = new JButton("Tìm");
        ptimkiem = new JPanel();
        ptimkiem.add(cbtk);
        ptimkiem.add(txttk);
        ptimkiem.add(btntimkiem);
        styleButton(btntimkiem, new Color(241, 196, 15));
        ptimkiem.setBackground(new Color(255, 253, 208));
        
        //toolbar container
        ptoolbar = new JPanel();
        ptoolbar.setLayout(new BorderLayout());
        ptoolbar.add(pbutton, BorderLayout.WEST);
        ptoolbar.add(ptimkiem, BorderLayout.EAST);
        ptoolbar.setBorder(new EmptyBorder(30, 0, 8, 0));
        ptoolbar.setBackground(new Color(255, 253, 208));
        
        //table
        header = new Vector<>();
        header.add("Mã nhà xuất bản");
        header.add("Tên nhà xuất bản");
        header.add("Số điện thoại");
        header.add("Địa chỉ");
        header.add("Email");
        tbmodel = new DefaultTableModel(header,0);
        tbnhaxuatban = new JTable(tbmodel);
        tbnhaxuatban.setFillsViewportHeight(true);
        tbnhaxuatban.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tbnhaxuatban.setForeground(Color.BLACK);
        tbnhaxuatban.setBackground(Color.WHITE);
        tbnhaxuatban.setGridColor(new Color(120, 120, 120));
        tbnhaxuatban.setShowGrid(true);
        tbnhaxuatban.setBackground(new Color(255, 253, 240));
        tbnhaxuatban.setForeground(Color.BLACK);
        
        th = tbnhaxuatban.getTableHeader();
        th.setBackground(new Color(255, 224, 178));
        th.setForeground(Color.BLACK);
        th.setFont(new Font("Segoe UI", Font.BOLD, 14));
        th.setReorderingAllowed(false);
        
        ptable = new JPanel();
        ptable.setLayout(new BorderLayout());
        JScrollPane sptbnhaxuatban = new JScrollPane(tbnhaxuatban);
        ptable.add(sptbnhaxuatban, BorderLayout.CENTER);
        
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
