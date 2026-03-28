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
    JPanel pbutton,ptimkiem,pcontainer,inputpanel,ptable,ptitle,pradio,ptoolbar,pformtitle,pformbutton;
    JButton btnthem,btnxoa,btnsua,btntimkiem,btnluu,btnhuy;
    JTextField txttk,txtma,txttentg,txtquoctich,txtnamsinh;
    JComboBox cbtk;
    DefaultComboBoxModel cbmdtk;
    JTable tbtacgia;
    DefaultTableModel tbmodel;
    Vector<String> header;
    JLabel lbtitle,lbma,lbten,lbgioitinh,lbquoctich,lbnamsinh,lbformtitle;
    JRadioButton btnam,btnu;
    ButtonGroup g;
    JTableHeader th;
    
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
        
        lbma = new JLabel("Mã tác giả");
        thutLabel(lbma,15);
        lbten = new JLabel("Tên tác giả");
        thutLabel(lbten,15);
        lbnamsinh = new JLabel("Năm sinh");
        thutLabel(lbnamsinh,15);
        lbgioitinh = new JLabel("Giới tính");
        thutLabel(lbgioitinh,15);
        lbquoctich = new JLabel("Quốc tịch");
        thutLabel(lbquoctich,15);
        
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
        inputpanel.setLayout(new GridLayout(5,2,10,10));
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
        
        inputpanel.setBackground(new Color(255, 253, 208));
        
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
        ArrayList<TacGia> dstemp = new ArrayList<TacGia>();
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
        
        pformtitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lbformtitle = new JLabel("FORM SỬA");
        pformtitle.add(lbformtitle);
        lbformtitle.setFont(new Font("Segoe UI",Font.BOLD,30));
        pformtitle.setBackground(new Color(255, 253, 208));
        
        lbma = new JLabel("Mã tác giả");
        thutLabel(lbma,15);
        lbten = new JLabel("Tên tác giả");
        thutLabel(lbten,15);
        lbnamsinh = new JLabel("Năm sinh");
        thutLabel(lbnamsinh,15);
        lbgioitinh = new JLabel("Giới tính");
        thutLabel(lbgioitinh,15);
        lbquoctich = new JLabel("Quốc tịch");
        thutLabel(lbquoctich,15);

        
        txtma = new JTextField(20);
        txtma.setEditable(false);
        txttentg = new JTextField(20);
        txtnamsinh = new JTextField(20);
        txtquoctich = new JTextField(20);
        
        btnam = new JRadioButton("Nam");
        btnu = new JRadioButton("Nu");
        g = new ButtonGroup();
        g.add(btnam);
        g.add(btnu);
        
        pradio = new JPanel();
        pradio.add(btnam);
        pradio.add(btnu);
        
        int i = tbtacgia.getSelectedRow();
        txtma.setText(tbtacgia.getValueAt(i, 0).toString());
        txttentg.setText(tbtacgia.getValueAt(i, 1).toString());
        txtnamsinh.setText(tbtacgia.getValueAt(i, 2).toString());
        if(tbtacgia.getValueAt(i, 3).toString().equals("Nam")){
            btnam.setSelected(true);
        }else{
            btnu.setSelected(true);
        }
        txtquoctich.setText(tbtacgia.getValueAt(i, 4).toString());
        
        inputpanel = new JPanel();
        inputpanel.setLayout(new GridLayout(5,2,10,10));
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
        inputpanel.setBackground(new Color(255, 253, 208));
        
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
        styleButton(btntimkiem, new Color(241, 196, 15));
        ptimkiem.setBackground(new Color(255, 253, 208));
        
        styleComboBox(cbtk);
        styleTextField(txttk);
        
        //toolbar container
        ptoolbar = new JPanel();
        ptoolbar.setLayout(new BorderLayout());
        ptoolbar.add(pbutton, BorderLayout.WEST);
        ptoolbar.add(ptimkiem, BorderLayout.EAST);
        ptoolbar.setBorder(new EmptyBorder(30, 0, 8, 0));
        ptoolbar.setBackground(new Color(255, 253, 208));
        
        //table
        header = new Vector<>();
        header.add("Mã tác giả");
        header.add("Tên tác giả");
        header.add("Năm sinh");
        header.add("Giới tính");
        header.add("Quốc tịch");
        tbmodel = new DefaultTableModel(header,0);
        tbtacgia = new JTable(tbmodel);
        tbtacgia.setFillsViewportHeight(true);
        tbtacgia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tbtacgia.setForeground(Color.BLACK);
        tbtacgia.setBackground(Color.WHITE);
        tbtacgia.setGridColor(new Color(120, 120, 120));
        tbtacgia.setShowGrid(true);
        tbtacgia.setBackground(new Color(255, 253, 240));
        tbtacgia.setForeground(Color.BLACK);
        
        th = tbtacgia.getTableHeader();
        th.setBackground(new Color(255, 224, 178));
        th.setForeground(Color.BLACK);
        th.setFont(new Font("Segoe UI", Font.BOLD, 14));
        th.setReorderingAllowed(false);
        
        ptable = new JPanel();
        ptable.setLayout(new BorderLayout());
        JScrollPane sptbtacgia = new JScrollPane(tbtacgia);
        ptable.add(sptbtacgia, BorderLayout.CENTER);
        
        ptitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ptitle.setBackground(new Color(255, 253, 208));
        lbtitle = new JLabel("QUẢN LÝ TÁC GIẢ");
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
