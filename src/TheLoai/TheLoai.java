package TheLoai;

import java.util.*;
/**
 *
 * @author Quyen
 */
public class TheLoai {
    private String matl, tentl;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TheLoai tg)) return false;
        return Objects.equals(matl, tg.matl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matl);
    }
    
    public TheLoai(){
        matl = tentl = null;
    }
    
    public TheLoai(String matl, String tentl) {
        this.matl = matl;
        this.tentl = tentl;
    }
    
    public TheLoai(TheLoai tg){
        matl = tg.matl;
        tentl = tg.tentl;
    }

    public String getMatl() {
        return matl;
    }

    public void setMatl(String matl) {
        this.matl = matl;
    }

    public String getTentl() {
        return tentl;
    }

    public void setTentl(String tentl) {
        this.tentl = tentl;
    }
    
    
}
