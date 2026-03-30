package KhuyenMai; 
import java.sql.*; 
import database.DBConnection;
import java.util.ArrayList; 
import java.sql.SQLException; 
import javax.swing.JOptionPane; 
public class KhuyenMaiDAO { 
    Connection conn = null;
    PreparedStatement ps = null; 
    ResultSet rs = null; 

// Lấy danh sách khuyến mãi 
    public ArrayList<KhuyenMai> getAll() { 
        ArrayList<KhuyenMai> list = new ArrayList<>();
        String sql = "SELECT * FROM KhuyenMai"; 
        try { 
            conn = DBConnection.getDBConnection();
            ps = conn.prepareStatement(sql); 
            rs = ps.executeQuery(); 
            while (rs.next()) { 
        KhuyenMai km = new KhuyenMai(
            rs.getString("makm"), 
            rs.getString("tenkm"), 
            rs.getDate("ngaybatdau").toLocalDate(), 
            rs.getDate("ngayketthuc").toLocalDate(), 
            rs.getInt("dieukientoithieu"), 
            rs.getInt("phantramgiam")
    );
        list.add(km);
    }
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, "Lỗi đọc cơ sở dữ liệu khuyến mãi");
        } 
        return list;
    } 
    
    public String generateMaKM() { 
        String prefix = "KM";
        String sql = "SELECT makm FROM KhuyenMai ORDER BY makm DESC LIMIT 1";
        try { 
            conn = DBConnection.getDBConnection();
            ps = conn.prepareStatement(sql); 
            rs = ps.executeQuery(); 
            if (rs.next()) { 
                String lastMa = rs.getString("makm");
            // VD: KM005 
                int number = Integer.parseInt(lastMa.substring(2)); // lấy 005 -> 5 
                number++; 
                return String.format(prefix + "%03d", number); // KM006 
            } 
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null,"Lỗi tạo mã khuyễn mãi tự động");
        } return "KM001";
    }
    
// Thêm khuyến mãi 
    public boolean insert(KhuyenMai km) { 
        String sql = "INSERT INTO KhuyenMai(makm, tenkm, ngaybatdau, ngayketthuc, dieukientoithieu, phantramgiam) VALUES(?,?,?,?,?,?)";
        try { 
            conn = DBConnection.getDBConnection();
            km.setMaKM(generateMaKM()); 
            ps = conn.prepareStatement(sql); 
            ps.setString(1, km.getMaKM()); 
            ps.setString(2, km.getTenKM()); 
            ps.setDate(3, java.sql.Date.valueOf(km.getNgayBD()));
            ps.setDate(4, java.sql.Date.valueOf(km.getNgayKT())); 
            ps.setInt(5, km.getDKTT()); 
            ps.setInt(6, km.getPtgg()); 
            return ps.executeUpdate() > 0; 
        } 
        catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null,"Lỗi thêm khuyến mãi");
        } 
        return false; 
    } 
    
    
// Sửa khuyến mãi 
    public boolean update(KhuyenMai km) { 
        String sql = "UPDATE KhuyenMai SET tenkm=?, ngaybatdau=?, ngayketthuc=?, dieukientoithieu=?, phantramgiam=? " + "WHERE makm=?";
        try { 
            conn = DBConnection.getDBConnection();
            ps = conn.prepareStatement(sql); 
            ps.setString(1, km.getTenKM()); 
            ps.setDate(2, Date.valueOf(km.getNgayBD())); 
            ps.setDate(3, Date.valueOf(km.getNgayKT())); 
            ps.setInt(4, km.getDKTT()); 
            ps.setInt(5, km.getPtgg()); 
            ps.setString(6, km.getMaKM()); 
            return ps.executeUpdate() > 0; 
        } 
        catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null,"Lỗi sửa khuyến mãi");
        }  
        return false; } 
    
    
// Xóa khuyến mãi 
    public boolean delete(String maKM) { 
        String sql = "DELETE FROM KhuyenMai WHERE makm=?";
        try { 
            conn = DBConnection.getDBConnection();
            ps = conn.prepareStatement(sql); 
            ps.setString(1, maKM); 
            return ps.executeUpdate() > 0; 
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null,"Lỗi xoá khuyến mãi");
        }  return false; } 
    
    
// Tìm kiếm khuyến mãi 
    public ArrayList<KhuyenMai> search(String keyword) { 
        ArrayList<KhuyenMai> list = new ArrayList<>();
        String sql = "SELECT * FROM KhuyenMai WHERE makm LIKE ? OR tenkm LIKE ?"; 
        try { 
            conn = DBConnection.getDBConnection();
            ps = conn.prepareStatement(sql); 
            ps.setString(1, "%" + keyword + "%"); ps.setString(2, "%" + keyword + "%"); 
            rs = ps.executeQuery();
            while (rs.next()) { 
                KhuyenMai km = new KhuyenMai( 
                        rs.getString("makm"), 
                        rs.getString("tenkm"), 
                        rs.getDate("ngaybatdau").toLocalDate(), 
                        rs.getDate("ngayketthuc").toLocalDate(), 
                        rs.getInt("dieukientoithieu"), 
                        rs.getInt("phantramgiam") );
                list.add(km); 
            } 
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null,"Lỗi tìm kiếm khuyến mãi");
        } return list; }
    
    
    public class ThongKeKM { 
        private int tongKM;
        private int kmConHieuLuc; 
        private ArrayList<String> theoPhanTram;
        
        public ThongKeKM() { }
        public int getTongKM() { return tongKM;} 
        public void setTongKM(int tongKM) { this.tongKM = tongKM;} 
        public int getKmConHieuLuc() { 
            return kmConHieuLuc;
        } 
        public void setKmConHieuLuc(int kmConHieuLuc) { this.kmConHieuLuc = kmConHieuLuc; }
         
        public ArrayList<String> getTheoPhanTram() { return theoPhanTram;} 
        
        public void setTheoPhanTram(ArrayList<String> theoPhanTram) { this.theoPhanTram = theoPhanTram; }
    }    
        public ThongKeKM thongKeTongHop() {
            ThongKeKM tk = new ThongKeKM();
            ArrayList<String> list = new ArrayList<>();
            
            try { 
                conn = DBConnection.getDBConnection();
                // Tổng số KM 
                String sql1 = "SELECT COUNT(*) FROM KhuyenMai"; 
                ps = conn.prepareStatement(sql1); 
                rs = ps.executeQuery(); 
                
                if (rs.next()) { 
                    tk.setTongKM(rs.getInt(1));
                //  KM còn hiệu lực 
                }
                String sql2 = "SELECT COUNT(*) FROM KhuyenMai WHERE NOW() BETWEEN ngaybatdau AND ngayketthuc";
                ps = conn.prepareStatement(sql2); 
                rs = ps.executeQuery(); 
                if (rs.next()) { 
                    tk.setKmConHieuLuc(rs.getInt(1));
                } 
                
                // Thống kê theo phần trăm 
                String sql3 = "SELECT phantramgiam, COUNT(*) as soluong FROM KhuyenMai GROUP BY phantramgiam"; 
                ps = conn.prepareStatement(sql3); 
                rs = ps.executeQuery(); 
                while (rs.next()) { 
                    String row = "Giảm " + rs.getInt("phantramgiam") + "%: " + rs.getInt("soluong") + " KM"; 
                list.add(row); 
                } 
                tk.setTheoPhanTram(list); 
            } catch (SQLException ex) { 
                JOptionPane.showMessageDialog(null, "Lỗi thống kê tổng hợp");
            } return tk;
    }
}