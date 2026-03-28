package ThongKe;

import Sach.Sach;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ThongKe {
    private int soLuongSach;
    private int soLuongKH;
    private int soLuongNV;
    private int[] tongThuQuy;
    private ArrayList<Sach> topSachBanChay;

    public ThongKe() {
    }

    public ThongKe(int soLuongSach, int soLuongKH, int soLuongNV, int[] tongThuQuy, ArrayList<Sach> topSachBanChay) {
        this.soLuongSach = soLuongSach;
        this.soLuongKH = soLuongKH;
        this.soLuongNV = soLuongNV;
        this.tongThuQuy = tongThuQuy;
        this.topSachBanChay = topSachBanChay;
    }

    public int getSoLuongSach() {
        return soLuongSach;
    }

    public void setSoLuongSach(int soLuongSach) {
        this.soLuongSach = soLuongSach;
    }

    public int getSoLuongKH() {
        return soLuongKH;
    }

    public void setSoLuongKH(int soLuongKH) {
        this.soLuongKH = soLuongKH;
    }

    public int getSoLuongNV() {
        return soLuongNV;
    }

    public void setSoLuongNV(int soLuongNV) {
        this.soLuongNV = soLuongNV;
    }

    public int[] getTongThuQuy() {
        return tongThuQuy;
    }

    public void setTongThuQuy(int[] tongThuQuy) {
        this.tongThuQuy = tongThuQuy;
    }

    public ArrayList<Sach> getTopSachBanChay() {
        return topSachBanChay;
    }

    public void setTopSachBanChay(ArrayList<Sach> topSachBanChay) {
        this.topSachBanChay = topSachBanChay;
    }
}
