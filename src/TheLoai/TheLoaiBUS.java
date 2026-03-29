package TheLoai;

import Sach.SachBUS;
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
    
    public ArrayList<TheLoai> getTheLoaiBUS(){
        return dstl;
    }
    
    //kiem tra
    public boolean ktMaTheLoai(String ma){
        for(TheLoai tl : dstl){
            if(tl.getMatl().equals(ma)){
                return true;
            }
        }
        return false;
    }
    
    public boolean ktRangBuocTheLoai(String matl){
        SachBUS sach = new SachBUS();
        return !sach.timSachTheoMaTheLoai(matl).isEmpty();
    }
    
    //them sua xoa
    public Result themTheLoai(TheLoai tl){
        if(tl.getMatl().isEmpty() || tl.getTentl().isEmpty()){
            return Result.thieuthongtin;
        }
        
        if(ktMaTheLoai(tl.getMatl())){
            return Result.trungma;
        }else{
            if(!theloaidao.insertTheLoai(tl)){
                return Result.thatbai;
            }
        }
        
        dstl.add(tl);
        return Result.thanhcong;
    }
    
    public Result xoaTheLoai(String ma){
        if(!ktMaTheLoai(ma)){
            return Result.khongtontai;
        }else{
            if(!theloaidao.deleteTheLoai(ma)){
                return Result.thatbai;
            }
        }
        TheLoai tl = timTheLoaiTheoMa(ma);
        int i = dstl.indexOf(tl);
        if(i != -1){
            dstl.remove(i);
        }
        return Result.thanhcong;
    }
    
    public Result suaTheLoai(TheLoai tl){
        if(!ktMaTheLoai(tl.getMatl())){
            return Result.khongtontai;
        }else{
            if(!theloaidao.updateTheLoai(tl)){
                return Result.thatbai;
            }
        }
        int i = dstl.indexOf(tl);
        if(i != -1){
            dstl.set(i,tl);
        }
        return Result.thanhcong;
    }
    
    //tim kiem
    public TheLoai timTheLoaiTheoMa(String ma){
        for(TheLoai tl : dstl){
            if(tl.getMatl().equals(ma)){
                return tl;
            }
        }
        return null;
    }
    
    public ArrayList<TheLoai> timTheLoaiTheoTen(String ten){
        ArrayList<TheLoai> ds = new ArrayList<TheLoai>();
        for(TheLoai tl : dstl){
            if(tl.getTentl().toLowerCase().contains(ten.toLowerCase())){
                ds.add(tl);
            }
        }
        return ds;
    }
   
    public String autoThemMa(){
        int max = 0;
        
        for(TheLoai tl : dstl){
            if(tl.getMatl() != null && tl.getMatl().matches("TL\\d+"));
            int num = Integer.parseInt(tl.getMatl().substring(2));
            if(num > max && num - max ==1){
                max = num;
            }
        }
        return String.format("TL%03d", max + 1);
    }
    
    public int thongKeSoLuongTheLoai(ArrayList<TheLoai> dstemp){
        int cnt = 0;
        for(TheLoai tl : dstemp){
            cnt++;
        }
        return cnt;
    }
    
    //reload
    public void theloaireload(){
        dstl = theloaidao.loadTheLoai();
    }
}
