package ChiTietHoaDon;

public class ChiTietHoaDon {

    private String maHD;
    private String maSach;
    private int soLuong;
    private double donGia;
    private double thanhTien;

    public ChiTietHoaDon() {}

    // Constructor KHÔNG truyền thanhTien
    public ChiTietHoaDon(String maHD, String maSach, int soLuong, double donGia) {
        this.maHD = maHD;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = soLuong * donGia;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
        this.thanhTien = this.soLuong * this.donGia; // tự cập nhật
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
        this.thanhTien = this.soLuong * this.donGia; // tự cập nhật
    }

    public double getThanhTien() {
        return thanhTien;
    }
}