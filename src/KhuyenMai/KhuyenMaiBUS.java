package KhuyenMai;

import java.util.ArrayList;

public class KhuyenMaiBUS {

    private KhuyenMaiDAO kmDAO;

    public KhuyenMaiBUS() {
        kmDAO = new KhuyenMaiDAO();
    }

    // Lấy danh sách
    public ArrayList<KhuyenMai> getAll() {
        return kmDAO.getAll();
    }

    // Thêm
    public boolean add(KhuyenMai km) {
        return kmDAO.insert(km);
    }

    // Sửa
    public boolean update(KhuyenMai km) {
        return kmDAO.update(km);
    }

    // Xóa
    public boolean delete(String maKM) {
        if (maKM == null || maKM.trim().equals("")) {
            return false;
        }
        return kmDAO.delete(maKM);
    }

    // Tìm kiếm
    public ArrayList<KhuyenMai> search(String keyword) {
        if (keyword == null || keyword.trim().equals("")) {
            return kmDAO.getAll();
        }
        return kmDAO.search(keyword);
    }

    // Thống kê
    public KhuyenMaiDAO.ThongKeKM thongKe(){
        return kmDAO.thongKeTongHop();
    }
}

