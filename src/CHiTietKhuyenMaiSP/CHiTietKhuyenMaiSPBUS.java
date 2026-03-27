package CHiTietKhuyenMaiSP;

import java.util.ArrayList;

public class CHiTietKhuyenMaiSPBUS {

    private CHiTietKhuyenMaiSPDAO ctkmDAO;

    public CHiTietKhuyenMaiSPBUS() {
        ctkmDAO = new CHiTietKhuyenMaiSPDAO();
    }

    // Lấy danh sách
    public ArrayList<CHiTietKhuyenMaiSP> getAll() {
        return ctkmDAO.getAll();
    }

    // Thêm
    public boolean add(CHiTietKhuyenMaiSP km) {

        if (km.getMaKM().trim().equals("") || km.getMaSP().trim().equals("")) {
            return false;
        }

        return ctkmDAO.insert(km);
    }

    // Sửa
    public boolean update(CHiTietKhuyenMaiSP km) {

        if (km.getMaKM().trim().equals("") || km.getMaSP().trim().equals("")) {
            return false;
        }

        return ctkmDAO.update(km);
    }

    // Xóa
    public boolean delete(String maKM, String maSP) {

        if (maKM.trim().equals("") || maSP.trim().equals("")) {
            return false;
        }

        return ctkmDAO.delete(maKM, maSP);
    }
    //Tìm kiếm
    public ArrayList<CHiTietKhuyenMaiSP> search(String keyword){
        if (keyword == null || keyword.trim().equals("")) {
            return ctkmDAO.getAll();
        }
        return ctkmDAO.search(keyword);
    }
}