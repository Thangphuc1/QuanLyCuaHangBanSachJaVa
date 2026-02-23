package TheLoai;

import java.util.*;
import java.sql.*;
/**
 *
 * @author Quyen
 */
public class TheLoaiBUS {
    private TheLoaiDAO theloaidao = new TheLoaiDAO();
    private ArrayList<TheLoai> dstl = new ArrayList<TheLoai>();
    
    public TheLoaiBUS(){
        dstl = theloaidao.loadTheLoai();
    }
    
    public boolean ktMaTheLoai(String ma){
        for(TheLoai tl : dstl){
            if(tl.getMatl().equals(ma)){
                return true;
            }
        }
        return false;
    }
    
    public String themTheLoai(TheLoai tl){
        if(tl.getMatl().isEmpty() || tl.getTentl().isEmpty()){
            return "Vui long nhap day du thong tin tac gia";
        }
        
        if(ktMaTheLoai(tl.getMatl())){
            return "Trung ma tac gia!";
        }else{
            if(!theloaidao.insertTheLoai(tl)){
                return "Them that bai";
            }
        }
        
        dstl.add(tl);
        return "Them thanh cong!";
    }
    
    public String xoaTheLoai(String ma){
        if(!ktMaTheLoai(ma)){
            return "Khong ton tai ma trong danh sach!";
        }else{
            if(!theloaidao.deleteTheLoai(ma)){
                return "Xoa that bai!";
            }
        }
        TheLoai tl = timTheLoaiTheoMa(ma);
        int i = dstl.indexOf(tl);
        if(i != -1){
            dstl.remove(i);
        }
        return "Xoa thanh cong!";
    }
    
    public String suaTheLoai(TheLoai tl){
        if(!ktMaTheLoai(tl.getMatl())){
            return "Khong ton tai ma trong danh sach!";
        }else{
            if(!theloaidao.updateTheLoai(tl)){
                return "Sua that bai";
            }
        }
        int i = dstl.indexOf(tl);
        if(i != -1){
            dstl.set(i,tl);
        }
        return "Sua thanh cong!";
    }
    
    public TheLoai timTheLoaiTheoMa(String ma){
        for(TheLoai tl : dstl){
            if(tl.getMatl().equals(ma)){
                return tl;
            }
        }
        return null;
    }
    
    public ArrayList<TheLoai> timTheLoaiTheoTen(String ten){
        ArrayList<TheLoai> dstl = new ArrayList<TheLoai>();
        boolean found = false;
        for(TheLoai tl : dstl){
            if(tl.getMatl().toLowerCase().contains(ten.toLowerCase())){
                dstl.add(tl);
                found = true;
            }
        }
        if(!found){
            return null;
        }
        return dstl;
    }
}
