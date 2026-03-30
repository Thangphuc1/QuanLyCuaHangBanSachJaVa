package NhaXuatBan;

import Sach.SachBUS;
import java.util.*;
import java.sql.*;
/**
 *
 * @author Quyen
 */
public class NhaXuatBanBUS {
    private NhaXuatBanDAO nxbdao = new NhaXuatBanDAO();
    private ArrayList<NhaXuatBan> dsnxb = new ArrayList<NhaXuatBan>();
    
    public NhaXuatBanBUS(){
        dsnxb = nxbdao.loadNhaXuatBan();
    }
    
    public ArrayList<NhaXuatBan> getNhaXuatBanBUS(){
        return dsnxb;
    }
    
    //kiem tra
    public boolean ktMaNhaXuatBan(String ma){
        for(NhaXuatBan nxb : dsnxb){
            if(nxb.getManxb().equals(ma)){
                return true;
            }
        }
        return false;
    }
    
    public boolean ktRangBuocNXB(String manxb){
        SachBUS sach = new SachBUS();
        return !sach.timSachTheoNhaXuatBan(manxb).isEmpty();
    }
    
    //them sua xoa
    public Result themNhaXuatBan(NhaXuatBan nxb){
        if(nxb.getManxb().isEmpty() || nxb.getTennxb().isEmpty() || nxb.getDiachi().isEmpty() || nxb.getSdt().isEmpty() || nxb.getEmail().isEmpty()){
            return Result.thieuthongtin;
        }
        
        if(ktMaNhaXuatBan(nxb.getManxb())){
            return Result.trungma;
        }else{
            if(!nxbdao.insertNhaXuatBan(nxb)){
                return Result.thatbai;
            }
        }
        
        dsnxb.add(nxb);
        return Result.thanhcong;
    }
    
    public Result xoaNhaXuatBan(String ma){
        if(!ktMaNhaXuatBan(ma)){
            if(!ktRangBuocNXB(ma)){
                if(!nxbdao.deleteNhaXuatBan(ma)){
                    return Result.thatbai;
                }
                NhaXuatBan nxb = timNhaXuatBanTheoMa(ma);
                int i = dsnxb.indexOf(nxb);
                if(i != -1){
                    dsnxb.remove(i);
                }
                return Result.thanhcong;
            }else{
                return Result.dangduocsudung;
            }
        }else{
            return Result.khongtontai;
        }
    }
    
    public Result suaNhaXuatBan(NhaXuatBan nxb){
        if(!ktMaNhaXuatBan(nxb.getManxb())){
            return Result.khongtontai;
        }else{
            if(!nxbdao.updateNhaXuatBan(nxb)){
                return Result.thatbai;
            }
        }
        int i = dsnxb.indexOf(nxb);
        if(i != -1){
            dsnxb.set(i,nxb);
        }
        return Result.thanhcong;
    }
    
    //tim kiem
    public NhaXuatBan timNhaXuatBanTheoMa(String ma){
        for(NhaXuatBan nxb : dsnxb){
            if(nxb.getManxb().equals(ma)){
                return nxb;
            }
        }
        return null;
    }
    
    public ArrayList<NhaXuatBan> timNhaXuatBanTheoTen(String ten){
        ArrayList<NhaXuatBan> dsnxb = new ArrayList<NhaXuatBan>();
        boolean found = false;
        for(NhaXuatBan nxb : dsnxb){
            if(nxb.getManxb().toLowerCase().contains(ten.toLowerCase())){
                dsnxb.add(nxb);
                found = true;
            }
        }
        if(!found){
            return null;
        }
        return dsnxb;
    }
    
    public String autoThemMa(){
        int max = 0;
        
        for(NhaXuatBan nxb : dsnxb){
            if(nxb.getManxb() != null && nxb.getManxb().matches("NXB\\d+")){
                int num = Integer.parseInt(nxb.getManxb().substring(3));
                if(num > max && num - max == 1){
                    max = num;
                }
            }
        }
        return String.format("NXB%02d", max + 1);
    }
    
    public int thongKeSoLuongNhaXuatBan(ArrayList<NhaXuatBan> dstemp){
        int cnt = 0;
        for(NhaXuatBan nxb : dstemp){
            cnt++;
        }
        return cnt;
    }
    
    //reload
    public void nhaxuatbanreload(){
        dsnxb = nxbdao.loadNhaXuatBan();
    }
}

