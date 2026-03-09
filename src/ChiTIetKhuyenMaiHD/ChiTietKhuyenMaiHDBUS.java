/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChiTIetKhuyenMaiHD;

import ChiTIetKhuyenMaiHD.ChiTietKhuyenMaiHD;
import ChiTIetKhuyenMaiHD.ChiTietKhuyenMaiHDDAO;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ChiTietKhuyenMaiHDBUS {

    private ChiTietKhuyenMaiHDDAO ctkmhdDAO;

    public ChiTietKhuyenMaiHDBUS() {
        ctkmhdDAO = new ChiTietKhuyenMaiHDDAO();
    }

    // Lấy danh sách
    public ArrayList<ChiTietKhuyenMaiHD> getAll() {
        return ctkmhdDAO.getAll();
    }

    // Thêm
    public boolean add(ChiTietKhuyenMaiHD ctkmhd) {

        if (ctkmhd.getMaKM().trim().equals("") || ctkmhd.getTTHD().trim().equals("")) {
            return false;
        }

        return ctkmhdDAO.insert(ctkmhd);
    }

    // Sửa
    public boolean update(ChiTietKhuyenMaiHD ctkmhd) {

        if (ctkmhd.getMaKM().trim().equals("") || ctkmhd.getTTHD().trim().equals("")) {
            return false;
        }

        return ctkmhdDAO.Update(ctkmhd);
    }

    // Xóa
    public boolean delete(ChiTietKhuyenMaiHD ctkmhd) {

        if (ctkmhd.getMaKM().trim().equals("") || ctkmhd.getTTHD().trim().equals("")) {
            return false;
        }

        return ctkmhdDAO.Delete(ctkmhd);
    }
}
