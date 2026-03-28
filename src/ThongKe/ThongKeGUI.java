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

public class ThongKeGUI extends JFrame {

  // Gruvbox https://github.com/morhetz/gruvbox
  private final Color COLOR_MAIN_BG = new Color(251, 241, 199);      // bg0 (Nền chính - be sáng)
  private final Color COLOR_SIDEBAR_BG = new Color(235, 219, 178);   // bg1 (Nền sidebar - be đậm hơn)
  private final Color COLOR_HEADER_BG = new Color(213, 196, 161);    // bg2 (Nền header)
  private final Color COLOR_MENU_ACTIVE = new Color(131, 165, 152);  // blue/aqua (Menu active)
  
  private final Color COLOR_TEXT_MAIN = new Color(60, 56, 54);       // fg1 (Chữ xám đen đậm)
  private final Color COLOR_TEXT_MUTED = new Color(124, 111, 100);   // gray (Chữ xám nhạt)

  // Mau chinh cua gruvbox
  private final Color ORANGE = new Color(215, 95, 0);           // Gruvbox Orange
  private final Color BLUE = new Color(69, 133, 136);           // Gruvbox Blue
  private final Color PURPLE = new Color(177, 98, 134);         // Gruvbox Purple
  private final Color GREEN = new Color(152, 151, 26);          // Gruvbox Green
  private final Color RED =  new Color(204, 36, 29);          // Gruvbox Red

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
    setTitle("Phần mềm quản lý cửa hàng bán sách");
    setSize(1250, 750);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    add(createHeaderPanel(), BorderLayout.NORTH);
    add(createSidebarPanel(), BorderLayout.WEST);

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

  private JPanel createHeaderPanel() {
    JPanel header = new JPanel(new BorderLayout());
    header.setBackground(COLOR_HEADER_BG);
    header.setPreferredSize(new Dimension(0, 40));
    header.setBorder(new EmptyBorder(0, 15, 0, 15));

    JLabel lblTitle = new JLabel("PHẦN MỀM QUẢN LÝ CỬA HÀNG BÁN SÁCH", JLabel.CENTER);
    lblTitle.setForeground(COLOR_TEXT_MAIN);
    lblTitle.setFont(new Font("Noto Sans", Font.BOLD, 14));

    header.add(lblTitle, BorderLayout.CENTER);
    return header;
  }

  private JPanel createSidebarPanel() {
    JPanel sidebar = new JPanel();
    sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
    sidebar.setBackground(COLOR_SIDEBAR_BG);
    sidebar.setPreferredSize(new Dimension(220, 0));

    JPanel logoPanel = new JPanel();
    logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
    logoPanel.setOpaque(false);
    logoPanel.setPreferredSize(new Dimension(220, 180));
    logoPanel.setMaximumSize(new Dimension(220, 180));

    // Title
    JLabel lblTitle = new JLabel("BookStore");
    lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    lblTitle.setForeground(PURPLE);
    lblTitle.setFont(new Font("Noto Sans", Font.BOLD, 24));

    // Subtitle
    JLabel lblSub = new JLabel("Quản Lý");
    lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);
    lblSub.setForeground(COLOR_TEXT_MUTED); // gray (Chữ xám nhạt)
    lblSub.setFont(new Font("Noto Sans", Font.PLAIN, 14));

    logoPanel.add(Box.createVerticalGlue());
    logoPanel.add(lblTitle);
    logoPanel.add(Box.createVerticalStrut(5));
    logoPanel.add(lblSub);
    logoPanel.add(Box.createVerticalGlue());

    sidebar.add(logoPanel);

    sidebar.add(createMenuItem(" Tổng quan", true));
    sidebar.add(createMenuItem(" Bán hàng", false));
    sidebar.add(createMenuItem(" Quản lý Sách", false));
    sidebar.add(createMenuItem(" Nhập hàng", false));
    sidebar.add(createMenuItem(" Khách hàng", false));
    sidebar.add(createMenuItem(" Nhân viên", false));
    sidebar.add(createMenuItem(" Khuyến mãi", false));

    sidebar.add(Box.createVerticalGlue());
    return sidebar;
}

  private JPanel createMenuItem(String text, boolean isActive) {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 12));
    panel.setMaximumSize(new Dimension(220, 50));
    if (isActive) panel.setBackground(COLOR_MENU_ACTIVE);
    else panel.setBackground(COLOR_SIDEBAR_BG);

    JLabel label = new JLabel(text);
    label.setForeground(isActive ? COLOR_MAIN_BG : COLOR_TEXT_MAIN);
    label.setFont(new Font("Noto Sans", isActive ? Font.BOLD : Font.PLAIN, 15));
    panel.add(label);

    if (!isActive) {
      panel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) { 
          panel.setBackground(COLOR_HEADER_BG); // Hover dung mau bg2
          label.setForeground(COLOR_TEXT_MAIN);
        }
        @Override
        public void mouseExited(MouseEvent e) { 
          panel.setBackground(COLOR_SIDEBAR_BG); 
          label.setForeground(COLOR_TEXT_MAIN);
        }
      });
      panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    return panel;
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
    lblSubHeader.setForeground(COLOR_TEXT_MUTED);
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

    lblNumber.setForeground(COLOR_MAIN_BG);
    lblNumber.setFont(new Font("Noto Sans", Font.BOLD, 36));
    lblNumber.setAlignmentX(Component.RIGHT_ALIGNMENT);

    JLabel lblText = new JLabel(text);
    lblText.setForeground(COLOR_MAIN_BG);
    lblText.setFont(new Font("Noto Sans", Font.BOLD, 15));
    lblText.setAlignmentX(Component.RIGHT_ALIGNMENT);

    infoPanel.add(Box.createVerticalGlue());
    infoPanel.add(lblNumber);
    infoPanel.add(Box.createVerticalStrut(5));
    infoPanel.add(lblText);
    infoPanel.add(Box.createVerticalGlue());

    card.add(infoPanel, BorderLayout.EAST);

    // hieu ung hover + 15 de doi mau nhe
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
    yearCombo.setBackground(COLOR_SIDEBAR_BG);
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
    
    table.setBackground(COLOR_MAIN_BG);
    table.setForeground(COLOR_TEXT_MAIN);
    table.setFont(new Font("Noto Sans", Font.PLAIN, 15));
    table.setRowHeight(45); 
    table.setShowVerticalLines(false);
    table.setGridColor(COLOR_HEADER_BG);
    table.setSelectionBackground(COLOR_MENU_ACTIVE);
    table.setSelectionForeground(COLOR_MAIN_BG);
    table.setFocusable(false);

    JTableHeader header = table.getTableHeader();
    header.setBackground(COLOR_SIDEBAR_BG);
    header.setForeground(COLOR_TEXT_MAIN);
    header.setFont(new Font("Noto Sans", Font.BOLD, 14));
    header.setPreferredSize(new Dimension(0, 45));
    header.setBorder(BorderFactory.createEmptyBorder());
    
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    for (int i = 0; i < table.getColumnCount(); i++) {
      table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.getViewport().setBackground(COLOR_MAIN_BG);
    scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_HEADER_BG, 1, true));

    tableSection.add(scrollPane, BorderLayout.CENTER);
    return tableSection;
  }

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }

    SwingUtilities.invokeLater(() -> {
      ThongKeGUI dashboard = new ThongKeGUI();
      dashboard.setVisible(true);
    });
  }

}
