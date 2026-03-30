package ChiTietHoaDon;

public class ChiTietHoaDon {

    private String mahoadon;
    private String masach;
    private int soluong;
    private double dongia;

    public ChiTietHoaDon() {}

    public ChiTietHoaDon(String mahoadon, String masach, int soluong, double dongia) {
        this.mahoadon = mahoadon;
        this.masach = masach;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    // Xóa getter/setter cũ, chỉ giữ getter/setter mới bên dưới

    public String getMahoadon() {
        return mahoadon;
    }
    public void setMahoadon(String mahoadon) {
        this.mahoadon = mahoadon;
    }
    public String getMasach() {
        return masach;
    }
    public void setMasach(String masach) {
        this.masach = masach;
    }
    public int getSoluong() {
        return soluong;
    }
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    public double getDongia() {
        return dongia;
    }
    public void setDongia(double dongia) {
        this.dongia = dongia;
    }
}