package TacGIa;

import java.util.*;
/**
 *
 * @author Quyen
 */
public class TacGia {
    private String matg, tentg, gioitinh, quoctich;
    private int namsinh;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TacGia tg)) return false;
        return Objects.equals(matg, tg.matg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matg);
    }
    
    public TacGia(){
        matg = tentg = gioitinh = quoctich = null;
        namsinh = 0;
    }
    
    public TacGia(String matg, String tentg, int namsinh, String gioitinh, String quoctich) {
        this.matg = matg;
        this.tentg = tentg;
        this.gioitinh = gioitinh;
        this.quoctich = quoctich;
        this.namsinh = namsinh;
    }
    
    public TacGia(TacGia tg){
        matg = tg.matg;
        tentg = tg.tentg;
        gioitinh = tg.gioitinh;
        quoctich = tg.quoctich;
        namsinh = tg.namsinh;
    }
    
    public String getMatg() {
        return matg;
    }

    public void setMatg(String matg) {
        this.matg = matg;
    }

    public String getTentg() {
        return tentg;
    }

    public void setTentg(String tentg) {
        this.tentg = tentg;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getQuoctich() {
        return quoctich;
    }

    public void setQuoctich(String quoctich) {
        this.quoctich = quoctich;
    }

    public int getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(int namsinh) {
        this.namsinh = namsinh;
    }

}
