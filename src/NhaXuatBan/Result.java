package NhaXuatBan;

/**
 *
 * @author Quyen
 */
public enum Result {
    thanhcong("Thanh cong!"),
    khongtontai("Khong ton tai!"),
    loidb("Loi khi thao tac database!"),
    thieuthongtin("Vui long nhap day du thong tin!"),
    trungma("Trung ma!"),
    thatbai("That bai!"),
    dangduocsudung("Đang được sử dụng không thể xoá!");
    
    
    private final String message;

    Result(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
