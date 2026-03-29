/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NhanVien;
    import java.util.*;
    
    import KhachHang.KhachHang;

import java.sql.*;
/**
 *
 * @author DuyPhuong
 */
public class NhanVienBUS {
    private NhanVienDAO nvDAO = new NhanVienDAO();
    private ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
    
    public NhanVienBUS(){
        dsNhanVien = nvDAO.loadNhanVien();
    }
    public ArrayList<NhanVien> GetNhanVien() {
        return dsNhanVien;
    }

    //Check điều kiện
    public boolean ktMaNhanVien(String id){
        for(NhanVien nv : dsNhanVien)
            if(nv.getMaNV().equals(id))
                return true;
        return false;
    }
    public boolean ktEmailNhanVien(String email){
        for(NhanVien nv : dsNhanVien)
            if(nv.getEmail().equals(email))
                return true;
        return false;
    }

    //Thêm nhân viên 
    public String themNhanVien(NhanVien nv) {
    if (nv == null) return "Dữ liệu nhân viên không hợp lệ!";

    if (nv.getMaNV().isEmpty() || nv.getHoNV().isEmpty() || 
        nv.getTenNV().isEmpty() || nv.getSDT().isEmpty() || 
        nv.getDiaChi().isEmpty() || nv.getEmail().isEmpty() || 
        nv.getLuong()==0 ) {
        return "YÊU CẦU NHẬP ĐẦY ĐỦ TẤT CẢ THÔNG TIN: Mã, họ tên, SDT, địa chỉ, email , lương";
    }
    if(nv.getLuong()<0)
        return "Lương phải lớn hơn 0";

    if (ktMaNhanVien(nv.getMaNV())) {
        return "Mã nhân viên này đã tồn tại!";
    }
    if (ktEmailNhanVien(nv.getEmail())) {
        return "Email này đã được sử dụng bởi nhân viên khác!";
    }

    if (!nvDAO.insertNhanVien(nv)) {
        return "Thêm thất bại vào cơ sở dữ liệu!";
    }

    dsNhanVien.add(nv);
    return "Đã thêm thành công nhân viên: " + nv.getTenNV();
    }

    public String xoaNhanVien(String maNV) {
    if (maNV.isEmpty()) {
        return "Vui lòng nhập mã nhân viên cần xóa!";
    }

    if (!ktMaNhanVien(maNV)) {
        return "Không tìm thấy nhân viên có mã: " + maNV;
    }

    if (nvDAO.deleteNhanVien(maNV)) {
        
        for (int i = 0; i < dsNhanVien.size(); i++) {
            if (dsNhanVien.get(i).getMaNV().equalsIgnoreCase(maNV)) {
                dsNhanVien.remove(i);
                break; 
            }
        }
        return "Đã xóa thành công nhân viên mã: " + maNV;
    } else {
        return "Xóa thất bại.Có lỗi xảy ra";
    }
}
public String suaNhanVien(NhanVien nvMoi) {
    if (nvMoi == null) return "Dữ liệu cập nhật không hợp lệ!";

    if (nvMoi.getMaNV().isEmpty() || nvMoi.getHoNV().isEmpty() || 
        nvMoi.getTenNV().isEmpty() || nvMoi.getSDT().isEmpty() || 
        nvMoi.getDiaChi().isEmpty() || nvMoi.getEmail().isEmpty() || 
        nvMoi.getLuong()==0) {
        return "Vui lòng nhập đầy đủ thông tin nhân viên!";
    }

    if (nvMoi.getLuong() <= 0) {
        return "THẤT BẠI: Lương phải là số dương!";
    }

    if (!ktMaNhanVien(nvMoi.getMaNV())) {
        return "LỖI: Không tìm thấy nhân viên mã " + nvMoi.getMaNV() + " để cập nhật!";
    }

    if (nvDAO.updateNhanVien(nvMoi)) {
        for (int i = 0; i < dsNhanVien.size(); i++) {
            if (dsNhanVien.get(i).getMaNV().equalsIgnoreCase(nvMoi.getMaNV())) {
                dsNhanVien.set(i, nvMoi);
                break;
            }
        }
        return "Cập nhật thông tin thành công cho nhân viên: " + nvMoi.getMaNV();
        
    } else {
        return "Cập nhật thất bại tại cơ sở dữ liệu!";
    }
    }   

    public NhanVien timKiemNhanVienTheoMa(String maNV){
        if(maNV==null){
            System.out.println("Vui lòng nhập mã nhân viên cần tìm kiếm!");
        }
        for(int i = 0 ; i<dsNhanVien.size() ; i++){
            if(dsNhanVien.get(i).getMaNV().equals(maNV))
            return dsNhanVien.get(i);
        }
        return null;
    }
    public ArrayList<NhanVien> getDsNhanVien() {
        return dsNhanVien;
    }
}
