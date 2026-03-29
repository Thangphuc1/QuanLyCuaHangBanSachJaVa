package ThongKe;

import ThongKe.ThongKe;
import ThongKe.ThongKeBUS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.Calendar;

public class ThongKeGUI extends JPanel {

  private final Color COLOR_MAIN_BG = new Color(253, 255, 208);      // Nền chính màu vàng kem
  private final Color COLOR_SIDEBAR_BG = new Color(43, 43, 43);      // Sidebar màu xám đen
  private final Color COLOR_MENU_ACTIVE = new Color(0, 120, 215);    // Menu active màu xanh dương
  private final Color COLOR_MENU_HOVER = new Color(70, 70, 70);      // Menu hover màu xám nhạt hơn
  
  private final Color COLOR_TEXT_MAIN = new Color(30, 30, 30);       // Chữ phần chính (Đen)
  private final Color COLOR_TEXT_SIDEBAR = Color.WHITE;              // Chữ phần sidebar (Trắng)

  private final Color ORANGE = new Color(239, 131, 84);              
  private final Color BLUE = new Color(95, 158, 160);                
  private final Color PURPLE = new Color(163, 116, 166);             
  private final Color GREEN = new Color(126, 161, 114);              

  private CardLayout cardLayout;
  private JPanel centerContentPanel;

  private ThongKeBUS thongKeBUS = new ThongKeBUS();
  private DecimalFormat dcf = new DecimalFormat("###,###,### VND");
  
  private JLabel lblSoLuongSach = new JLabel("0");
  private JLabel lblSoLuongKH = new JLabel("0");
  private JLabel lblSoLuongNV = new JLabel("0");
  private JLabel lblTongDoanhThu = new JLabel("0");
  
  private DefaultTableModel tableModel;

  public ThongKeGUI() {
    setLayout(new BorderLayout());

    cardLayout = new CardLayout();
    centerContentPanel = new JPanel(cardLayout);
    
    centerContentPanel.add(createOverviewPanel(), "OVERVIEW");
    add(centerContentPanel, BorderLayout.CENTER);
    
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    loadData(currentYear);
    
    cardLayout.show(centerContentPanel, "OVERVIEW");
  }

  private void loadData(int year) {
    ThongKe tk = thongKeBUS.thongKe(year);

    lblSoLuongSach.setText(String.valueOf(tk.getSoLuongSach()));
    lblSoLuongKH.setText(String.valueOf(tk.getSoLuongKH()));
    lblSoLuongNV.setText(String.valueOf(tk.getSoLuongNV()));

    int[] thuQuy = tk.getTongThuQuy();
    int tongThu = thuQuy[0] + thuQuy[1] + thuQuy[2] + thuQuy[3];
    lblTongDoanhThu.setText(dcf.format(tongThu));

    tableModel.setRowCount(0); 
    tableModel.addRow(new Object[]{
            "Doanh thu (VNĐ)", 
            dcf.format(thuQuy[0]), 
            dcf.format(thuQuy[1]), 
            dcf.format(thuQuy[2]), 
            dcf.format(thuQuy[3])
    });
    tableModel.addRow(new Object[]{
            "Tổng cộng", 
            "--", 
            dcf.format(tongThu), 
            "--", 
            "--"
    });
  }

  private JPanel createOverviewPanel() {
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBackground(COLOR_MAIN_BG);
    mainPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

    JPanel headerWrap = new JPanel(new BorderLayout());
    headerWrap.setOpaque(false);
    
    JLabel lblHeader = new JLabel("TỔNG QUAN THỐNG KÊ");
    lblHeader.setForeground(COLOR_TEXT_MAIN);
    lblHeader.setFont(new Font("Noto Sans", Font.BOLD, 28));
    
    JLabel lblSubHeader = new JLabel("Dữ liệu hoạt động kinh doanh hiện tại");
    lblSubHeader.setForeground(new Color(100, 100, 100));
    lblSubHeader.setFont(new Font("Noto Sans", Font.ITALIC, 14));
    lblSubHeader.setBorder(new EmptyBorder(5, 0, 30, 0));

    headerWrap.add(lblHeader, BorderLayout.NORTH);
    headerWrap.add(lblSubHeader, BorderLayout.CENTER);
    mainPanel.add(headerWrap, BorderLayout.NORTH);

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    centerPanel.setOpaque(false);

    JPanel cardsPanel = new JPanel(new GridLayout(2, 2, 30, 30));
    cardsPanel.setOpaque(false);
    cardsPanel.setMaximumSize(new Dimension(900, 350));
    
    cardsPanel.add(createCard(lblSoLuongSach, "Đầu Sách Trong Kho", ORANGE));
    cardsPanel.add(createCard(lblSoLuongKH, "Khách Hàng Đăng Ký", BLUE));
    cardsPanel.add(createCard(lblSoLuongNV, "Nhân Viên Đang Làm", PURPLE));
    cardsPanel.add(createCard(lblTongDoanhThu, "Tổng Doanh Thu Năm", GREEN));
    
    centerPanel.add(cardsPanel);
    centerPanel.add(Box.createVerticalStrut(40)); 
    centerPanel.add(createTableSection());

    mainPanel.add(centerPanel, BorderLayout.CENTER);
    return mainPanel;
  }

  private JPanel createCard(JLabel lblNumber, String text, Color bgColor) {
    JPanel card = new JPanel(new BorderLayout());
    card.setBackground(bgColor);
    card.setBorder(new EmptyBorder(20, 25, 20, 25));

    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
    infoPanel.setOpaque(false);

    lblNumber.setForeground(Color.WHITE); 
    lblNumber.setFont(new Font("Noto Sans", Font.BOLD, 36));
    lblNumber.setAlignmentX(Component.RIGHT_ALIGNMENT);

    JLabel lblText = new JLabel(text);
    lblText.setForeground(Color.WHITE); 
    lblText.setFont(new Font("Noto Sans", Font.BOLD, 15));
    lblText.setAlignmentX(Component.RIGHT_ALIGNMENT);

    infoPanel.add(Box.createVerticalGlue());
    infoPanel.add(lblNumber);
    infoPanel.add(Box.createVerticalStrut(5));
    infoPanel.add(lblText);
    infoPanel.add(Box.createVerticalGlue());

    card.add(infoPanel, BorderLayout.EAST);

    // Hieu ung hover + 15 cho mau no nhat di
    Color hoverColor = new Color(
      Math.min(bgColor.getRed() + 15, 255),
      Math.min(bgColor.getGreen() + 15, 255),
      Math.min(bgColor.getBlue() + 15, 255)
    );

    card.setCursor(new Cursor(Cursor.HAND_CURSOR));
    card.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        card.setBackground(hoverColor);
      }
      @Override
      public void mouseExited(MouseEvent e) {
        card.setBackground(bgColor);
      }
    });

    return card;
  } 

  private JPanel createTableSection() {
    JPanel tableSection = new JPanel(new BorderLayout(0, 15));
    tableSection.setOpaque(false);

    JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    filterPanel.setOpaque(false);
    
    JLabel lblFilter = new JLabel("Lọc theo năm:  ");
    lblFilter.setForeground(COLOR_TEXT_MAIN);
    lblFilter.setFont(new Font("Noto Sans", Font.BOLD, 14));
    filterPanel.add(lblFilter);

    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    String[] years = new String[currentYear - 2020 + 2];
    for(int i = 0; i < years.length; i++) {
      years[i] = String.valueOf(2020 + i);
    }
    
    JComboBox<String> yearCombo = new JComboBox<>(years);
    yearCombo.setSelectedItem(String.valueOf(currentYear));
    yearCombo.setPreferredSize(new Dimension(120, 32));
    yearCombo.setFont(new Font("Noto Sans", Font.PLAIN, 14));
    yearCombo.setBackground(Color.WHITE);
    yearCombo.setForeground(COLOR_TEXT_MAIN);
    yearCombo.setFocusable(false); 
    
    yearCombo.addActionListener(e -> {
      int selectedYear = Integer.parseInt(yearCombo.getSelectedItem().toString());
      loadData(selectedYear);
    });
    
    filterPanel.add(yearCombo);
    tableSection.add(filterPanel, BorderLayout.NORTH);

    String[] columns = {"Hạng Mục", "Quý 1", "Quý 2", "Quý 3", "Quý 4"};
    tableModel = new DefaultTableModel(columns, 0) {
      @Override
      public boolean isCellEditable(int row, int column) { return false; }
    };
    JTable table = new JTable(tableModel);
    
    table.setBackground(Color.WHITE);
    table.setForeground(COLOR_TEXT_MAIN);
    table.setFont(new Font("Noto Sans", Font.PLAIN, 14));
    table.setRowHeight(40); 
    table.setGridColor(Color.LIGHT_GRAY);
    table.setSelectionBackground(COLOR_MENU_ACTIVE);
    table.setSelectionForeground(Color.WHITE);
    table.setFocusable(false);

    JTableHeader header = table.getTableHeader();
    header.setBackground(new Color(230, 185, 0)); 
    header.setForeground(Color.BLACK);
    header.setFont(new Font("Noto Sans", Font.BOLD, 14));
    header.setPreferredSize(new Dimension(0, 35));
    header.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    for (int i = 0; i < table.getColumnCount(); i++) {
      table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.getViewport().setBackground(Color.WHITE);
    scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

    tableSection.add(scrollPane, BorderLayout.CENTER);
    return tableSection;
  }
  
}
