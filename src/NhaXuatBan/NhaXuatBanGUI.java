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
    JPanel pbutton,ptimkiem,pcontainer,inputpanel,ptable,ptitle,pradio;
    JButton btnthem,btnxoa,btnsua,btntimkiem,btnluu;
    JTextField txttk,txtma,txttennxb,txtdiachi,txtsdt,txtemail;
    JComboBox cbtk;
    DefaultComboBoxModel cbmdtk;
    JTable tbnhaxuatban;
    DefaultTableModel tbmodel;
    Vector<String> header;
    JLabel lbtitle,lbma,lbten,lbdiachi,lbemail,lbsdt;
    
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
    
    public void themForm(){
        themform = new JFrame("Form Them");
        themform.setSize(500, 500);
        themform.setLayout(new BorderLayout());
        
        lbma = new JLabel("Mã nhà xuất bản");
        lbten = new JLabel("Tên nhà xuất bản");
        lbsdt = new JLabel("Số điện thoại");
        lbdiachi = new JLabel("Địa chỉ");
        lbemail = new JLabel("Email");

        
        txtma = new JTextField(20);
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
        inputpanel.add(lbdiachi);
        inputpanel.add(txtdiachi);
        inputpanel.add(lbemail);
        inputpanel.add(txtemail);
        
        themform.add(inputpanel, BorderLayout.CENTER);
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
        
        themform.add(btnluu, BorderLayout.SOUTH);
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
        
        lbma = new JLabel("Mã nhà xuất bản");
        lbten = new JLabel("Tên nhà xuất bản");
        lbsdt = new JLabel("Số điện thoại");
        lbdiachi = new JLabel("Địa chỉ");
        lbemail = new JLabel("Email");

        
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
        inputpanel.add(lbdiachi);
        inputpanel.add(txtdiachi);
        inputpanel.add(lbemail);
        inputpanel.add(txtemail);
        
        
        suaform.add(inputpanel, BorderLayout.CENTER);
        
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
        
        suaform.add(btnluu, BorderLayout.SOUTH);
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
        pbutton = new JPanel();
        pbutton.add(btnthem);
        pbutton.add(btnxoa);
        pbutton.add(btnsua);
        
        //search panel
        cbmdtk = new DefaultComboBoxModel<>();
        cbmdtk.addElement("Mã nhà xuất bản");
        cbmdtk.addElement("Tên nhà xuất bản");
        cbtk = new JComboBox(cbmdtk);
        txttk = new JTextField(25);
        
        btntimkiem = new JButton("Tìm kiếm");
        ptimkiem = new JPanel();
        ptimkiem.add(cbtk);
        ptimkiem.add(txttk);
        ptimkiem.add(btntimkiem);
        
        //toolbar container
        pcontainer = new JPanel();
        pcontainer.setLayout(new BorderLayout());
        pcontainer.add(pbutton, BorderLayout.WEST);
        pcontainer.add(ptimkiem, BorderLayout.EAST);
        pcontainer.setBorder(new EmptyBorder(40, 0, 8, 0));
        
        //table
        header = new Vector<>();
        header.add("Mã nhà xuất bản");
        header.add("Tên nhà xuất bản");
        header.add("Số điện thoại");
        header.add("Địa chỉ");
        header.add("Email");
        tbmodel = new DefaultTableModel(header,0);
        tbnhaxuatban = new JTable(tbmodel);
        ptable = new JPanel();
        ptable.setLayout(new BorderLayout());
        JScrollPane sptbnhaxuatban = new JScrollPane(tbnhaxuatban);
        ptable.add(sptbnhaxuatban);
        
        ptitle = new JPanel();
        lbtitle = new JLabel("QUẢN LÝ NHÀ XUẤT BẢN");
        ptitle.add(lbtitle);
        
        add(ptitle, BorderLayout.NORTH);
        add(pcontainer, BorderLayout.CENTER);
        add(ptable, BorderLayout.SOUTH);
        
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
