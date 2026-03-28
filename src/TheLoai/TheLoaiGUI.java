package TheLoai;

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
public class TheLoaiGUI extends JPanel{
    
    TheLoaiBUS theloaibus = new TheLoaiBUS(); 
    JFrame themform,suaform;
    JPanel pbutton,ptimkiem,pcontainer,inputpanel,ptable,ptitle,pradio;
    JButton btnthem,btnxoa,btnsua,btntimkiem,btnluu;
    JTextField txttk,txtmatl,txttentl;
    JComboBox cbtk;
    DefaultComboBoxModel cbmdtk;
    JTable tbtheloai;
    DefaultTableModel tbmodel;
    Vector<String> header;
    JLabel lbtitle,lbma,lbten;
    JRadioButton btnam,btnu;
    ButtonGroup g;
    
    public void loadData(){
        ArrayList<TheLoai> dstmp = new ArrayList<TheLoai>();
        theloaibus.theloaireload();
        dstmp = theloaibus.getTheLoaiBUS();
        for(TheLoai temp : dstmp){
            Vector<String> row = new Vector<>();
            row.add(temp.getMatl());
            row.add(temp.getTentl());
            tbmodel.addRow(row);
        }
    }
    
    public void themForm(){
        themform = new JFrame("Form Them");
        themform.setSize(500, 500);
        themform.setLayout(new BorderLayout());
        
        lbma = new JLabel("Mã thể loại");
        lbten = new JLabel("Tên thể loại");

        
        txtmatl = new JTextField(theloaibus.autoThemMa());
        txtmatl.setEditable(false);
        txttentl = new JTextField(20);

        inputpanel = new JPanel();
        inputpanel.setLayout(new GridLayout(2,2));
        inputpanel.add(lbma);
        inputpanel.add(txtmatl);
        inputpanel.add(lbten);
        inputpanel.add(txttentl);
        
        themform.add(inputpanel, BorderLayout.CENTER);
        btnluu = new JButton("Lưu");
        btnluu.addActionListener(e -> {
            try {
                String gt;
                if(btnam.isSelected()){
                    gt = "Nam";
                }else{
                    gt = "Nu";
                }
                TheLoai theloai = new TheLoai();
                theloai.setMatl(txtmatl.getText());
                theloai.setTentl(txttentl.getText());
                    
                Result themtheloai = theloaibus.themTheLoai(theloai);
                JOptionPane.showMessageDialog(this, themtheloai.getMessage());
                if (themtheloai == Result.thanhcong) {
                    Vector<String> row = new Vector<>();
                    row.add(txtmatl.getText());
                    row.add(txttentl.getText());
                    tbmodel.addRow(row);
                    theloaibus.theloaireload();
                    themform.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(themform, "Vui lòng nhập đúng định dạng số cho năm sinh");
            }
        });
        
        themform.add(btnluu, BorderLayout.SOUTH);
        themform.setVisible(true);
        
    }  
    
    public void timkiem(){
        ArrayList<TheLoai> dstemp = new ArrayList<>();
        tbmodel.setRowCount(0);

        try {
            int idx = cbtk.getSelectedIndex();
            String text = txttk.getText().trim();
            if (text.isEmpty()) {
                dstemp = theloaibus.getTheLoaiBUS();
            } else {
                switch (idx) {
                case 0:
                    TheLoai s = theloaibus.timTheLoaiTheoMa(text);
                    if (s != null) dstemp.add(s);
                    break;
                case 1:
                    dstemp = theloaibus.timTheLoaiTheoTen(text);
                    break;
                }
            }
            
            for (TheLoai temp : dstemp) {
                Vector<String> row = new Vector<>();
                row.add(temp.getMatl());
                row.add(temp.getTentl());
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
        
        lbma = new JLabel("Mã thể loại");
        lbten = new JLabel("Tên thể loại");

        txtmatl = new JTextField(20);
        txtmatl.setEditable(false);
        txttentl = new JTextField(20);
        
        int i = tbtheloai.getSelectedRow();
        txtmatl.setText(tbtheloai.getValueAt(i, 0).toString());
        txttentl.setText(tbtheloai.getValueAt(i, 1).toString());
        inputpanel = new JPanel();
        inputpanel.setLayout(new GridLayout(8,2,10,10));
        inputpanel.add(lbma);
        inputpanel.add(txtmatl);
        inputpanel.add(lbten);
        inputpanel.add(txttentl);
        
        suaform.add(inputpanel, BorderLayout.CENTER);
        
        btnluu = new JButton("Lưu");
        btnluu.addActionListener(e -> {
            try {
                String gt;
                if(btnam.isSelected()){
                    gt = "Nam";
                }else{
                    gt = "Nu";
                }
                
                TheLoai theloai = new TheLoai();
                theloai.setMatl(txtmatl.getText());
                theloai.setTentl(txttentl.getText());
                
                Result suatheloai = theloaibus.suaTheLoai(theloai);
                JOptionPane.showMessageDialog(this, suatheloai.getMessage());
                
                if (suatheloai == Result.thanhcong) {
                    if(i >= 0){
                        tbmodel.setValueAt(theloai.getTentl(),i,1);
                    }
                    theloaibus.theloaireload();
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
        int i = tbtheloai.getSelectedRow();
        String ma = tbtheloai.getValueAt(i, 0).toString(); 
               
        Result xoatheloai = theloaibus.xoaTheLoai(ma);
        JOptionPane.showMessageDialog(this, xoatheloai.getMessage());
        if(xoatheloai == Result.thanhcong){
            tbmodel.removeRow(i);
            theloaibus.theloaireload();
        }
    }
    
    public TheLoaiGUI(){
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
        cbmdtk.addElement("Mã thể loại");
        cbmdtk.addElement("Tên thể loại");
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
        header.add("Mã thể loại");
        header.add("Tên thể loại");
        tbmodel = new DefaultTableModel(header,0);
        tbtheloai = new JTable(tbmodel);
        ptable = new JPanel();
        ptable.setLayout(new BorderLayout());
        JScrollPane sptbtheloai = new JScrollPane(tbtheloai);
        ptable.add(sptbtheloai);
        
        ptitle = new JPanel();
        lbtitle = new JLabel("QUẢN LÝ THỂ LOẠI");
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
