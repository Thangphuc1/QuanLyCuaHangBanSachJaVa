import javax.swing.JFrame;
import KhuyenMai.KhuyenMaiGUI;

public class TestKhuyenMai {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Khuyến Mãi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Gọi JPanel khuyến mãi của bạn
        KhuyenMaiGUI panel = new KhuyenMaiGUI(); // sửa đúng tên class của bạn

        frame.add(panel);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}