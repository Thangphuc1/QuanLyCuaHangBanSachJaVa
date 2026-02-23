package NhaXuatBan;

import java.util.*;
/**
 *
 * @author Quyen
 */
public class NhaXuatBan {
    private String manxb, tennxb, diachi, sdt, email;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NhaXuatBan nxb)) return false;
        return Objects.equals(manxb, nxb.manxb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manxb);
    }
    
    public NhaXuatBan(){
        manxb = tennxb = diachi = sdt = email = null;
        
    }
    
    public NhaXuatBan(String manxb, String tennxb, String diachi, String sdt, String email) {
        this.manxb = manxb;
        this.tennxb = tennxb;
        this.diachi = diachi;
        this.sdt = sdt;
        this.email = email;
    }
    
    public NhaXuatBan(NhaXuatBan nxb){
        manxb = nxb.manxb;
        tennxb = nxb.tennxb;
        diachi = nxb.diachi;
        sdt = nxb.sdt;
        email = nxb.email;
    }

    public String getManxb() {
        return manxb;
    }

    public void setManxb(String manxb) {
        this.manxb = manxb;
    }

    public String getTennxb() {
        return tennxb;
    }

    public void setTennxb(String tennxb) {
        this.tennxb = tennxb;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
    
    
}

