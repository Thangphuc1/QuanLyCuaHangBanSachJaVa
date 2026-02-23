package NhaXuatBan;

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
    
    public boolean ktMaNhaXuatBan(String ma){
        for(NhaXuatBan nxb : dsnxb){
            if(nxb.getManxb().equals(ma)){
                return true;
            }
        }
        return false;
    }
    
    public String themNhaXuatBan(NhaXuatBan nxb){
        if(nxb.getManxb().isEmpty() || nxb.getTennxb().isEmpty() || nxb.getDiachi().isEmpty() || nxb.getSdt().isEmpty() || nxb.getEmail().isEmpty()){
            return "Vui long nhap day du thong tin nha xuat ban";
        }
        
        if(ktMaNhaXuatBan(nxb.getManxb())){
            return "Trung ma nha xuat ban!";
        }else{
            if(!nxbdao.insertNhaXuatBan(nxb)){
                return "Them that bai";
            }
        }
        
        dsnxb.add(nxb);
        return "Them thanh cong!";
    }
    
    public String xoaNhaXuatBan(String ma){
        if(!ktMaNhaXuatBan(ma)){
            return "Khong ton tai ma trong danh sach!";
        }else{
            if(!nxbdao.deleteNhaXuatBan(ma)){
                return "Xoa that bai!";
            }
        }
        NhaXuatBan nxb = timNhaXuatBanTheoMa(ma);
        int i = dsnxb.indexOf(nxb);
        if(i != -1){
            dsnxb.remove(i);
        }
        return "Xoa thanh cong!";
    }
    
    public String suaNhaXuatBan(NhaXuatBan nxb){
        if(!ktMaNhaXuatBan(nxb.getManxb())){
            return "Khong ton tai ma trong danh sach!";
        }else{
            if(!nxbdao.updateNhaXuatBan(nxb)){
                return "Sua that bai";
            }
        }
        int i = dsnxb.indexOf(nxb);
        if(i != -1){
            dsnxb.set(i,nxb);
        }
        return "Sua thanh cong!";
    }
    
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
}

