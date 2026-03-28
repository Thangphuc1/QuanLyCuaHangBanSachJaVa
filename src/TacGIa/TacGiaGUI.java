package TacGIa;

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
public class TacGiaGUI extends JPanel{
    
    TacGiaBUS tacgiabus = new TacGiaBUS(); 
    JFrame themform,suaform;
    JPanel pbutton,ptimkiem,pcontainer,inputpanel,ptable,ptitle,pradio;
    JButton btnthem,btnxoa,btnsua,btntimkiem,btnluu;
    JTextField txttk,txtma,txttentg,txtquoctich,txtnamsinh;
    JComboBox cbtk;
    DefaultComboBoxModel cbmdtk;
    JTable tbtacgia;
    DefaultTableModel tbmodel;
    Vector<String> header;
    JLabel lbtitle,lbma,lbten,lbgioitinh,lbquoctich,lbnamsinh;
    JRadioButton btnam,btnu;
    ButtonGroup g;
    
    public void loadData(){
        ArrayList<TacGia> dstmp = new ArrayList<TacGia>();
        tacgiabus.tacgiareload();
        dstmp = tacgiabus.getTacGiaBUS();
        for(TacGia temp : dstmp){
            Vector<String> row = new Vector<>();
            row.add(temp.getMatg());
            row.add(temp.getTentg());
            row.add(Integer.toString(temp.getNamsinh()));
            row.add(temp.getGioitinh());
            row.add(temp.getQuoctich());
            tbmodel.addRow(row);
        }
    }
    
    public void themForm(){
        themform = new JFrame("Form Them");
        themform.setSize(500, 500);
        themform.setLayout(new BorderLayout());
        
        lbma = new JLabel("Mã tác giả");
        lbten = new JLabel("Tên tác giả");
        lbnamsinh = new JLabel("Năm sinh");
        lbgioitinh = new JLabel("Giới tính");
        lbquoctich = new JLabel("Quốc tịch");
        
        txtma = new JTextField(tacgiabus.autoThemMa());
        txtma.setEditable(false);
        txttentg = new JTextField(20);
        txtnamsinh = new JTextField(20);
        txtquoctich = new JTextField(20);
        
        btnam = new JRadioButton("Nam");
        btnu = new JRadioButton("Nụ");
        g = new ButtonGroup();
        g.add(btnam);
        g.add(btnu);
        
        pradio = new JPanel();
        pradio.add(btnam);
        pradio.add(btnu);
        
        inputpanel = new JPanel();
        inputpanel.setLayout(new GridLayout(5,2));
        inputpanel.add(lbma);
        inputpanel.add(txtma);
        inputpanel.add(lbten);
        inputpanel.add(txttentg);
        inputpanel.add(lbnamsinh);
        inputpanel.add(txtnamsinh);
        inputpanel.add(lbgioitinh);
        inputpanel.add(pradio);
        inputpanel.add(lbquoctich);
        inputpanel.add(txtquoctich);
        
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
                TacGia tacgia = new TacGia();
                tacgia.setMatg(txtma.getText());
                tacgia.setTentg(txttentg.getText());
                tacgia.setGioitinh(gt);
                tacgia.setNamsinh(Integer.parseInt(txtnamsinh.getText()));
                tacgia.setQuoctich(txtquoctich.getText());
                
                Result themtacgia = tacgiabus.themTacGia(tacgia);
                JOptionPane.showMessageDialog(this, themtacgia.getMessage());
                if (themtacgia == Result.thanhcong) {
                    Vector<String> row = new Vector<>();
                    row.add(txtma.getText());
                    row.add(txttentg.getText());
                    row.add(txtnamsinh.getText());
                    row.add(gt);
                    row.add(txtquoctich.getText());
                    tbmodel.addRow(row);
                    tacgiabus.tacgiareload();
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
        ArrayList<TacGia> dstemp = new ArrayList<>();
        tbmodel.setRowCount(0);

        try {
            int idx = cbtk.getSelectedIndex();
            String text = txttk.getText().trim();
            if (text.isEmpty()) {
                dstemp = tacgiabus.getTacGiaBUS();
            } else {
                switch (idx) {
                case 0:
                    TacGia s = tacgiabus.timTacGiaTheoMa(text);
                    if (s != null) dstemp.add(s);
                    break;
                case 1:
                    dstemp = tacgiabus.timTacGiaTheoTen(text);
                    break;
                case 2:
                    dstemp = tacgiabus.timTacGiaTheoQuocTich(text);
                    break;
                }
            }
            
            for (TacGia temp : dstemp) {
                Vector<String> row = new Vector<>();
                row.add(temp.getMatg());
                row.add(temp.getTentg());
                row.add(Integer.toString(temp.getNamsinh()));
                row.add(temp.getGioitinh());
                row.add(temp.getQuoctich());
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
        
        lbma = new JLabel("Mã Sách");
        lbten = new JLabel("Tên sách");
        lbgioitinh = new JLabel("Giới tính");
        lbquoctich = new JLabel("Quốc tịch");

        
        txtma = new JTextField(20);
        txtma.setEditable(false);
        txttentg = new JTextField(20);
        txtquoctich = new JTextField(20);
        
        btnam = new JRadioButton("Nam");
        btnu = new JRadioButton("Nam");
        g = new ButtonGroup();
        g.add(btnam);
        g.add(btnu);
        
        pradio = new JPanel();
        pradio.add(btnam);
        pradio.add(btnu);
        
        int i = tbtacgia.getSelectedRow();
        txtma.setText(tbtacgia.getValueAt(i, 0).toString());
        txttentg.setText(tbtacgia.getValueAt(i, 1).toString());
        if(tbtacgia.getValueAt(i, 2).toString() == "Nam"){
            btnam.setSelected(true);
        }else{
            btnu.setSelected(true);
        }
        txtnamsinh.setText(tbtacgia.getValueAt(i, 3).toString());
        txtquoctich.setText(tbtacgia.getValueAt(i, 4).toString());
        
        inputpanel = new JPanel();
        inputpanel.setLayout(new GridLayout(8,2,10,10));
        inputpanel.add(lbma);
        inputpanel.add(txtma);
        inputpanel.add(lbten);
        inputpanel.add(txttentg);
        inputpanel.add(lbgioitinh);
        inputpanel.add(pradio);
        inputpanel.add(lbquoctich);
        inputpanel.add(txtquoctich);
        
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
                
                TacGia tacgia = new TacGia();
                tacgia.setMatg(txtma.getText());
                tacgia.setTentg(txttentg.getText());
                tacgia.setGioitinh(gt);
                tacgia.setQuoctich(txtquoctich.getText());
                
                Result suatacgia = tacgiabus.suaTacGia(tacgia);
                JOptionPane.showMessageDialog(this, suatacgia.getMessage());
                
                if (suatacgia == Result.thanhcong) {
                    if(i >= 0){
                        tbmodel.setValueAt(tacgia.getTentg(),i,1);
                        tbmodel.setValueAt(gt,i,2);
                        tbmodel.setValueAt(tacgia.getGioitinh(),i,3);
                        tbmodel.setValueAt(tacgia.getQuoctich(),i,4);
                    }
                    tacgiabus.tacgiareload();
                    suaform.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(themform, "Vui lòng nhập đúng định dạng số cho năm sinh");
            }
        });
        
        suaform.add(btnluu, BorderLayout.SOUTH);
        suaform.setVisible(true);
    }
    
    public void xoa(){
        int i = tbtacgia.getSelectedRow();
        String ma = tbtacgia.getValueAt(i, 0).toString(); 
               
        Result xoatacgia = tacgiabus.xoaTacGia(ma);
        JOptionPane.showMessageDialog(this, xoatacgia.getMessage());
        if(xoatacgia == Result.thanhcong){
            tbmodel.removeRow(i);
            tacgiabus.tacgiareload();
        }
    }
    
    public TacGiaGUI(){
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
        cbmdtk.addElement("Mã tác giả");
        cbmdtk.addElement("Tên tác giả");
        cbmdtk.addElement("Quốc tịch");
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
        header.add("Mã tác giả");
        header.add("Tên tác giả");
        header.add("Năm sinh");
        header.add("Giới tính");
        header.add("Quốc tịch");
        tbmodel = new DefaultTableModel(header,0);
        tbtacgia = new JTable(tbmodel);
        ptable = new JPanel();
        ptable.setLayout(new BorderLayout());
        JScrollPane sptbtacgia = new JScrollPane(tbtacgia);
        ptable.add(sptbtacgia);
        
        ptitle = new JPanel();
        lbtitle = new JLabel("QUẢN LÝ TÁC GIẢ");
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
