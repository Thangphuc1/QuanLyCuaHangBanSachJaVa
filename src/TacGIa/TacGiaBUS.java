package TacGIa;

import Sach.SachBUS;
import java.util.ArrayList;
import java.util.*;
import java.sql.*;
/**
 *
 * @author Quyen
 */
public class TacGiaBUS {
    private TacGiaDAO tacgiadao = new TacGiaDAO();
    private ArrayList<TacGia> dstg = new ArrayList<TacGia>();
    
    public TacGiaBUS(){
        dstg = tacgiadao.loadTacGia();
    }
    
    public ArrayList<TacGia> getTacGiaBUS(){
        return dstg;
    }
    
    //kiem tra
    public boolean ktMaTacGia(String ma){
        for(TacGia tg : dstg){
            if(tg.getMatg().equals(ma)){
                return true;
            }
        }
        return false;
    }
    
    public boolean ktRangBuocTacGia(String matg){
        SachBUS sach = new SachBUS();
        return !sach.timSachTheoMaTacGia(matg).isEmpty();
    }
    
    //them sua xoa
    public Result themTacGia(TacGia tg){
        if(tg.getMatg().isEmpty() || tg.getTentg().isEmpty() || tg.getNamsinh() == 0 || tg.getGioitinh().isEmpty() || tg.getQuoctich().isEmpty()){
            return Result.thieuthongtin;
        }
        
        if(ktMaTacGia(tg.getMatg())){
            return Result.khongtontai;
        }else{
            if(!tacgiadao.insertTacGia(tg)){
                return Result.thatbai;
            }
        }
        
        dstg.add(tg);
        return Result.thanhcong;
    }
    
    public Result xoaTacGia(String ma){
        if(!ktMaTacGia(ma)){
            return Result.khongtontai;
        }else{
            if(!tacgiadao.deleteTacGia(ma)){
                return Result.thatbai;
            }
        }
        TacGia tg = timTacGiaTheoMa(ma);
        int i = dstg.indexOf(tg);
        if(i != -1){
            dstg.remove(i);
        }
        return Result.thanhcong;
    }
    
    public Result suaTacGia(TacGia tg){
        if(!ktMaTacGia(tg.getMatg())){
            return Result.khongtontai;
        }else{
            if(!tacgiadao.updateTacGia(tg)){
                return Result.thatbai;
            }
        }
        int i = dstg.indexOf(tg);
        if(i != -1){
            dstg.set(i,tg);
        }
        return Result.thanhcong;
    }
    
    //tim kiem
    public TacGia timTacGiaTheoMa(String ma){
        for(TacGia tg : dstg){
            if(tg.getMatg().equals(ma)){
                return tg;
            }
        }
        return null;
    }
    
    public ArrayList<TacGia> timTacGiaTheoTen(String ten){
        ArrayList<TacGia> ds = new ArrayList<TacGia>();
        for(TacGia tg : dstg){
            if(tg.getTentg().toLowerCase().contains(ten.toLowerCase())){
                ds.add(tg);
            }
        }
        return ds;
    }
    
    public ArrayList<TacGia> timTacGiaTheoQuocTich(String quoctich){
        ArrayList<TacGia> ds = new ArrayList<TacGia>();
        for(TacGia tg : dstg ){
            if(tg.getQuoctich().toLowerCase().contains(quoctich.toLowerCase())){
                ds.add(tg);
            }
        }
        return ds;
    }
    
    public String autoThemMa(){
        int max = 0;
        
        for(TacGia tg : dstg){
            if(tg.getMatg() != null && tg.getMatg().matches("TG\\d+")){
                int num = Integer.parseInt(tg.getMatg().substring(2));
                if(num > max && num - max == 1){
                    max = num;
                }
            }
        }
        return String.format("TG%03d", max + 1);
    }
    
    
    public int thongKeSoLuongTacGia(ArrayList<TacGia> dstemp){
        int cnt = 0;
        for(TacGia tg : dstemp){
            cnt++;
        }
        return cnt;
    }
            
    
    public void tacgiareload(){
        dstg = tacgiadao.loadTacGia();
    }
}

