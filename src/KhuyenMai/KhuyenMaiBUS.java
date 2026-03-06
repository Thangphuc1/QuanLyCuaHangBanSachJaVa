package KhuyenMai;

import java.util.ArrayList;

public class KhuyenMaiBUS {

    private KhuyenMaiDAO kmDAO;

    public KhuyenMaiBUS() {
        kmDAO = new KhuyenMaiDAO();
    }

    // Lấy danh sách khuyến mãi
    public ArrayList<KhuyenMai> getAll() {
        return kmDAO.getAll();
    }

    // Thêm khuyến mãi
    public boolean add(KhuyenMai km) {

        if (km.getMaKM().trim().equals("")) {
            return false;
        }

        return kmDAO.insert(km);
    }

    // Sửa khuyến mãi
    public boolean update(KhuyenMai km) {

        if (km.getMaKM().trim().equals("")) {
            return false;
        }

        return kmDAO.update(km);
    }

    // Xóa khuyến mãi
    public boolean delete(String maKM) {

        if (maKM.trim().equals("")) {
            return false;
        }

        return kmDAO.delete(maKM);
    }
}