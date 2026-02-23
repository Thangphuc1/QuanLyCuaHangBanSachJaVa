package TacGIa;

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
    
    public boolean ktMaTacGia(String ma){
        for(TacGia tg : dstg){
            if(tg.getMatg().equals(ma)){
                return true;
            }
        }
        return false;
    }
    
    public String themTacGia(TacGia tg){
        if(tg.getMatg().isEmpty() || tg.getTentg().isEmpty() || tg.getNamsinh() == 0 || tg.getGioitinh().isEmpty() || tg.getQuoctich().isEmpty()){
            return "Vui long nhap day du thong tin tac gia";
        }
        
        if(ktMaTacGia(tg.getMatg())){
            return "Trung ma tac gia!";
        }else{
            if(!tacgiadao.insertTacGia(tg)){
                return "Them that bai";
            }
        }
        
        dstg.add(tg);
        return "Them thanh cong!";
    }
    
    public String xoaTacGia(String ma){
        if(!ktMaTacGia(ma)){
            return "Khong ton tai ma trong danh sach!";
        }else{
            if(!tacgiadao.deleteTacGia(ma)){
                return "Xoa that bai!";
            }
        }
        TacGia tg = timTacGiaTheoMa(ma);
        int i = dstg.indexOf(tg);
        if(i != -1){
            dstg.remove(i);
        }
        return "Xoa thanh cong!";
    }
    
    public String suaTacGia(TacGia tg){
        if(!ktMaTacGia(tg.getMatg())){
            return "Khong ton tai ma trong danh sach!";
        }else{
            if(!tacgiadao.updateTacGia(tg)){
                return "Sua that bai";
            }
        }
        int i = dstg.indexOf(tg);
        if(i != -1){
            dstg.set(i,tg);
        }
        return "Sua thanh cong!";
    }
    
    public TacGia timTacGiaTheoMa(String ma){
        for(TacGia tg : dstg){
            if(tg.getMatg().equals(ma)){
                return tg;
            }
        }
        return null;
    }
    
    public ArrayList<TacGia> timTacGiaTheoTen(String ten){
        ArrayList<TacGia> dstg = new ArrayList<TacGia>();
        boolean found = false;
        for(TacGia tg : dstg){
            if(tg.getMatg().toLowerCase().contains(ten.toLowerCase())){
                dstg.add(tg);
                found = true;
            }
        }
        if(!found){
            return null;
        }
        return dstg;
    }
}
