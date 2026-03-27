
package MAIN;

import NhaCungCap.NhaCungCapGUI;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author ASUS
 */
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private JPanel contentPanel;
    
    public Main() {
        setTitle("Quản Lý Kho");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());
        
        JPanel sidebarPanel = createSidebar();
        add(sidebarPanel, BorderLayout.WEST);
        
        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
        
        showNhaCungCapPanel();
        
        setVisible(true);
    }
    
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(45, 45, 50));
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton btnNhaCungCap = new JButton("📦 Nhà Cung Cấp");
        btnNhaCungCap.setMaximumSize(new Dimension(200, 50));
        btnNhaCungCap.setBackground(new Color(60, 60, 65));
        btnNhaCungCap.setForeground(Color.WHITE);
        btnNhaCungCap.setFocusPainted(false);
        btnNhaCungCap.addActionListener(e -> showNhaCungCapPanel());
        
        sidebar.add(btnNhaCungCap);
        sidebar.add(Box.createVerticalGlue());
        
        return sidebar;
    }
    
    private void showNhaCungCapPanel() {
        contentPanel.removeAll();
        // ⭐ Truyền 'this' (JFrame) vào NhaCungCapPanel
        contentPanel.add(new NhaCungCapGUI(this), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
