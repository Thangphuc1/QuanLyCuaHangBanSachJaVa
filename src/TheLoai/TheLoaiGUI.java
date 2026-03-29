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
    JPanel pbutton,psoluong,ptimkiem,pcontainer,inputpanel,ptable,ptitle,pformtitle,ptoolbar,pformbutton;
    JButton btnthem,btnxoa,btnsua,btntimkiem,btnluu,btnhuy;
    JTextField txttk,txtmatl,txttentl;
    JComboBox cbtk;
    DefaultComboBoxModel cbmdtk;
    JTable tbtheloai;
    DefaultTableModel tbmodel;
    Vector<String> header;
    JLabel lbtitle,lbma,lbten,lbformtitle,lbtksoluong;
    JTableHeader th;
    
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
        themform.setSize(500, 200);
        themform.setLayout(new BorderLayout());
        
        lbma = new JLabel("Mã thể loại");
        thutLabel(lbma,15);
        lbten = new JLabel("Tên thể loại");
        thutLabel(lbten,15);
        
        pformtitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lbformtitle = new JLabel("FORM THÊM");
        pformtitle.add(lbformtitle);
        lbformtitle.setFont(new Font("Segoe UI",Font.BOLD,30));
        pformtitle.setBackground(new Color(255, 253, 208));
        
        txtmatl = new JTextField(theloaibus.autoThemMa());
        txtmatl.setEditable(false);
        txttentl = new JTextField(20);

        inputpanel = new JPanel();
        inputpanel.setLayout(new GridLayout(2,2));
        inputpanel.add(lbma);
        inputpanel.add(txtmatl);
        inputpanel.add(lbten);
        inputpanel.add(txttentl);
        inputpanel.setBackground(new Color(255, 253, 208));
        
        themform.add(inputpanel, BorderLayout.CENTER);
        btnluu = new JButton("Lưu");
        btnluu.addActionListener(e -> {
            try {
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
            lbtksoluong.setText("Số lượng thể loại: " + theloaibus.thongKeSoLuongTheLoai(dstemp));
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
        suaform.setSize(500, 200);
        suaform.setLayout(new BorderLayout());
        
        pformtitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lbformtitle = new JLabel("FORM SỬA");
        pformtitle.add(lbformtitle);
        lbformtitle.setFont(new Font("Segoe UI",Font.BOLD,30));
        pformtitle.setBackground(new Color(255, 253, 208));
        
        lbma = new JLabel("Mã thể loại");
        thutLabel(lbma,15);
        lbten = new JLabel("Tên thể loại");
        thutLabel(lbten,15);
        
        txtmatl = new JTextField(20);
        txtmatl.setEditable(false);
        txttentl = new JTextField(20);
        
        int i = tbtheloai.getSelectedRow();
        txtmatl.setText(tbtheloai.getValueAt(i, 0).toString());
        txttentl.setText(tbtheloai.getValueAt(i, 1).toString());
        inputpanel = new JPanel();
        inputpanel.setLayout(new GridLayout(2,2,10,10));
        inputpanel.add(lbma);
        inputpanel.add(txtmatl);
        inputpanel.add(lbten);
        inputpanel.add(txttentl);
        inputpanel.setBackground(new Color(255, 253, 208));
        
        suaform.add(inputpanel, BorderLayout.CENTER);
        
        btnluu = new JButton("Lưu");
        btnluu.addActionListener(e -> {
            try {
                
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
        
        styleButton(btnthem, new Color(46, 204, 113));
        styleButton(btnxoa, new Color(231, 76, 60));
        styleButton(btnsua, new Color(52, 152, 219));
        
        pbutton = new JPanel();
        pbutton.add(btnthem);
        pbutton.add(btnxoa);
        pbutton.add(btnsua);
        pbutton.setBackground(new Color(255, 253, 208));
        
        //search panel
        
        lbtksoluong = new JLabel("Số lượng thể loại: " + theloaibus.thongKeSoLuongTheLoai(theloaibus.getTheLoaiBUS()));
        lbtksoluong.setFont(new Font("Segoe UI",Font.BOLD,18));
        psoluong = new JPanel();
        psoluong.setBackground(new Color(255, 253, 208));
        psoluong.add(lbtksoluong);
        
        cbmdtk = new DefaultComboBoxModel<>();
        cbmdtk.addElement("Mã thể loại");
        cbmdtk.addElement("Tên thể loại");
        cbtk = new JComboBox(cbmdtk);
        txttk = new JTextField(25);
        
        btntimkiem = new JButton("Tìm kiếm");
        ptimkiem = new JPanel();
        ptimkiem.add(psoluong);
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
        header.add("Mã thể loại");
        header.add("Tên thể loại");
        tbmodel = new DefaultTableModel(header,0);
        tbtheloai = new JTable(tbmodel);
        tbtheloai.setFillsViewportHeight(true);
        tbtheloai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tbtheloai.setForeground(Color.BLACK);
        tbtheloai.setBackground(Color.WHITE);
        tbtheloai.setGridColor(new Color(120, 120, 120));
        tbtheloai.setShowGrid(true);
        tbtheloai.setBackground(new Color(255, 253, 240));
        tbtheloai.setForeground(Color.BLACK);
        
        th = tbtheloai.getTableHeader();
        th.setBackground(new Color(255, 224, 178));
        th.setForeground(Color.BLACK);
        th.setFont(new Font("Segoe UI", Font.BOLD, 14));
        th.setReorderingAllowed(false);
        
        ptable = new JPanel();
        ptable.setLayout(new BorderLayout());
        JScrollPane sptbtheloai = new JScrollPane(tbtheloai);
        ptable.add(sptbtheloai, BorderLayout.CENTER);
        
        ptitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ptitle.setBackground(new Color(255, 253, 208));
        lbtitle = new JLabel("QUẢN LÝ THỂ LOẠI");
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
