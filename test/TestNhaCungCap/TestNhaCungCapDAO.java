
package TestNhaCungCap;

import NhaCungCap.NhaCungCap;
import NhaCungCap.NhaCungCapDAO;
import java.util.ArrayList;

public class TestNhaCungCapDAO {

    public static void main(String[] args) {
        NhaCungCapDAO dao = new NhaCungCapDAO();

        // ID nhà cung cấp test
        String maNhaCungCapTest = "NCC942";

        // 1. Test Insert
        System.out.println("===== TEST INSERT =====");
        NhaCungCap ncc = new NhaCungCap();
        ncc.setMaNCC(maNhaCungCapTest);
        ncc.setTenNCC("Công ty cung cấp linh kiện A");
        ncc.setDiaChi("123 Đường Lê Lợi, Quận 1, TP.HCM");
        ncc.setSoDienThoai("0123456789");
        ncc.setEmail("contact@ctyA.com");

        try {
            boolean insertOk = dao.InsertNhaCungCap(ncc);
            System.out.println("Insert result: " + insertOk);
        } catch (Exception e) {
            System.out.println("Lỗi khi insert: " + e.getMessage());
            e.printStackTrace();
            return; // nếu insert lỗi thì dừng test
        }

        System.out.println("Nhà cung cấp được thêm: " + maNhaCungCapTest);

        // 2. Test LoadNhaCungCap (getAll)
        System.out.println("\n===== TEST LoadNhaCungCap =====");
        try {
            ArrayList<NhaCungCap> danhSach = dao.LoadNhaCungCap();
            System.out.println("Số lượng nhà cung cấp: " + danhSach.size());
            for (NhaCungCap item : danhSach) {
                printNhaCungCap(item);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi LoadNhaCungCap: " + e.getMessage());
            e.printStackTrace();
        }

        // 3. Test UpdateNhaCungCap
        System.out.println("\n===== TEST UpdateNhaCungCap =====");
        try {
            NhaCungCap nccUpdate = new NhaCungCap();
            nccUpdate.setMaNCC(maNhaCungCapTest);
            nccUpdate.setTenNCC("Công ty cung cấp linh kiện A - UPDATED");
            nccUpdate.setDiaChi("456 Đường Nguyễn Huệ, Quận 1, TP.HCM");
            nccUpdate.setSoDienThoai("0987654321");
            nccUpdate.setEmail("newemail@ctyA.com");

            boolean updateOk = dao.UpdateNhaCungCap(nccUpdate);
            System.out.println("UpdateNhaCungCap result: " + updateOk);

            // Load lại để xem kết quả sau update
            ArrayList<NhaCungCap> danhSachSauUpdate = dao.LoadNhaCungCap();
            System.out.println("Nhà cung cấp sau khi update:");
            for (NhaCungCap item : danhSachSauUpdate) {
                if (item.getMaNCC().equals(maNhaCungCapTest)) {
                    printNhaCungCap(item);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi UpdateNhaCungCap: " + e.getMessage());
            e.printStackTrace();
        }

        // 4. Test KiemTraNhaCungCapCoPhieuNhap
        System.out.println("\n===== TEST KiemTraNhaCungCapCoPhieuNhap =====");
        try {
            boolean coPhieuNhap = dao.KiemTraNhaCungCapCoPhieuNhap(maNhaCungCapTest);
            System.out.println("Nhà cung cấp có phiếu nhập: " + coPhieuNhap);
        } catch (Exception e) {
            System.out.println("Lỗi khi KiemTraNhaCungCapCoPhieuNhap: " + e.getMessage());
            e.printStackTrace();
        }

        // 5. Test deleteNhaCungCap
        System.out.println("\n===== TEST deleteNhaCungCap =====");
//        try {
//            NhaCungCap nccDelete = new NhaCungCap();
//            nccDelete.setMaNCC(maNhaCungCapTest);
//
//            boolean deleteOk = dao.deleteNhaCungCap(nccDelete);
//            System.out.println("deleteNhaCungCap result: " + deleteOk);
//
//            // Load lại để xác nhận đã xóa
//            ArrayList<NhaCungCap> danhSachSauXoa = dao.LoadNhaCungCap();
//            System.out.println("Số lượng nhà cung cấp sau khi xóa: " + danhSachSauXoa.size());
//            boolean timThay = false;
//            for (NhaCungCap item : danhSachSauXoa) {
//                if (item.getMaNCC().equals(maNhaCungCapTest)) {
//                    timThay = true;
//                    break;
//                }
//            }
//            System.out.println("Nhà cung cấp test còn trong DB: " + timThay);
//        } catch (Exception e) {
//            System.out.println("Lỗi khi deleteNhaCungCap: " + e.getMessage());
//            e.printStackTrace();
//        }

        System.out.println("\n===== KẾT THÚC TEST NhaCungCapDAO =====");
    }

    // Hàm in thông tin 1 nhà cung cấp cho dễ nhìn
    private static void printNhaCungCap(NhaCungCap ncc) {
        if (ncc == null) {
            System.out.println("NhaCungCap = null");
            return;
        }
        System.out.println("MaNCC     : " + ncc.getMaNCC());
        System.out.println("TenNCC    : " + ncc.getTenNCC());
        System.out.println("DiaChi    : " + ncc.getDiaChi());
        System.out.println("SoDienThoai: " + ncc.getSoDienThoai());
        System.out.println("Email     : " + ncc.getEmail());
        System.out.println("----------------------------------------");
    }
}