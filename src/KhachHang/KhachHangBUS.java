/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KhachHang;

import java.util.ArrayList;


/**
 *
 * @author DuyPhuong
 */
public class KhachHangBUS {
    private KhachHangDAO khDAO = new KhachHangDAO();
    private ArrayList<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
    
    public KhachHangBUS(){
        dsKhachHang = khDAO.loadKhachHang();
    }

    //Check điều kiện
    public boolean ktMaKhachHang(String id){
        for(KhachHang kh : dsKhachHang)
            if(kh.getMaKH().equals(id))
                return true;
        return false;
    }
    public boolean ktEmailKhachHang(String email){
        for(KhachHang kh : dsKhachHang)
            if(kh.getEmail().equals(email))
                return true;
        return false;
    }

    //Thêm nhân viên 
    public String themKhachHang(KhachHang kh) {
    if (kh == null) return "Dữ liệu nhân viên không hợp lệ!";

    if (kh.getMaKH().isEmpty() || kh.getHoKH().isEmpty() || 
        kh.getTenKH().isEmpty() || kh.getSDT().isEmpty() || 
        kh.getDiaChi().isEmpty() || kh.getEmail().isEmpty() ) {
        return "YÊU CẦU NHẬP ĐẦY ĐỦ TẤT CẢ THÔNG TIN: Mã, họ tên, SDT, địa chỉ, email";
    }

    if (ktMaKhachHang(kh.getMaKH())) {
        return "Mã khách hàng này đã tồn tại!";
    }
    if (ktEmailKhachHang(kh.getEmail())) {
        return "Email này đã được sử dụng bởi khách hàng khác!";
    }

    if (!khDAO.insertKhachHang(kh)) {
        return "Thêm thất bại vào cơ sở dữ liệu!";
    }

    dsKhachHang.add(kh);
    return "Đã thêm thành công khách hàng: " + kh.getTenKH();
    }

    public String xoaKhachHanh(String maKH) {
    if (maKH.isEmpty()) {
        return "Vui lòng nhập mã khách hàng cần xóa!";
    }

    if (!ktMaKhachHang(maKH)) {
        return "Không tìm thấy nhân viên có mã: " + maKH;
    }

    if (khDAO.deleteKhachHang(maKH)) {
        
        for (int i = 0; i < dsKhachHang.size(); i++) {
            if (dsKhachHang.get(i).getMaKH().equalsIgnoreCase(maKH)) {
                dsKhachHang.remove(i);
                break; 
            }
        }
        return "Đã xóa thành công khách hàng mã: " + maKH;
    } else {
        return "Xóa thất bại.Có lỗi xảy ra";
    }
}
public String suaKhachHang(KhachHang khMoi) {
    if (khMoi == null) return "Dữ liệu cập nhật không hợp lệ!";

    if (khMoi.getMaKH().isEmpty() || khMoi.getHoKH().isEmpty() || 
        khMoi.getTenKH().isEmpty() || khMoi.getSDT().isEmpty() || 
        khMoi.getDiaChi().isEmpty() || khMoi.getEmail().isEmpty()
        ) {
        return "Vui lòng nhập đầy đủ thông tin khách hàng!";
    }
    if (!ktMaKhachHang(khMoi.getMaKH())) {
        return "LỖI: Không tìm thấy khách hàng mã " + khMoi.getMaKH() + " để cập nhật!";
    }

    if (khDAO.updateKhachHang(khMoi)) {
        for (int i = 0; i < dsKhachHang.size(); i++) {
            if (dsKhachHang.get(i).getMaKH().equalsIgnoreCase(khMoi.getMaKH())) {
                dsKhachHang.set(i, khMoi);
                break;
            }
        }
        return "Cập nhật thông tin thành công cho khách hàng: " + khMoi.getMaKH();
        
    } else {
        return "Cập nhật thất bại tại cơ sở dữ liệu!";
    }
    }   
}
