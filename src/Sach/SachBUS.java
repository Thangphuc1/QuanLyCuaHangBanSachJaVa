package Sach;

import java.util.*;
/**
 *
 * @author ASUS
 */

public class SachBUS {
    private SachDAO sachdao = new SachDAO();
    private ArrayList<Sach> dss = new ArrayList<Sach>();
    
    public SachBUS(){
        dss = sachdao.loadSach();
    }
    
    public ArrayList<Sach> getSachBUS(){
        return dss;
    }
    
    public boolean ktmasach(String ma){
        for(int i = 0;i < dss.size();i++){
            if(dss.get(i).getMasach().equals(ma)){
                return true;
            }
        }
        return false;
    }
    
    public String themSach(Sach sach){
       if(sach.getMasach().isEmpty() || sach.getTensach().isEmpty() || sach.getMatg().isEmpty() || sach.getMatl().isEmpty() || sach.getNamxuatban() == 0 || sach.getManxb().isEmpty() || sach.getDongia() == 0 || sach.getSoluongton() == 0){
           return "Vui long nhap day du thong tin sach!";
       }
       if(!ktmasach(sach.getMasach())){
           if(!sachdao.insertSach(sach)){
               return "Them that bai";
           }
       }else{
           return "Ma sach trung";
       }
       dss.add(sach);
       return "Them thanh cong";
    }
    
    public String xoaSach(String ma){
        if(ktmasach(ma)){
            if(sachdao.deleteSach(ma)){
                return "Xoa that bai";
            }
        }else{
            return "Khong tim thay ma trong danh sach";
        }
        Sach s = timSachTheoMa(ma);
        if(s != null){
            dss.remove(s);
        }
        return "Xoa thanh cong";
    }
    
    public String suaSach(Sach sach){
        if(ktmasach(sach.getMasach())){
            if(sachdao.updateSach(sach)){
                return "Sua that bai";
            }
        }else{
            return "Khong tim thay ma trong danh sach";
        }
        int i = dss.indexOf(sach);
        if(i != -1){
            dss.set(i,sach);
        }
        return "sua thanh cong";
    }
    
    public Sach timSachTheoMa(String ma){
        for(Sach s : dss){
            if(s.getMasach().equals(ma)){
                return s;
            }
        }
        return null;
    }
    
    public ArrayList<Sach> timSachTheoTen(String ten){
        ArrayList<Sach> rs = new ArrayList<Sach>();
        boolean found = false;
        for(Sach s : dss){
            if(s.getTensach().toLowerCase().contains(ten.toLowerCase())){
                rs.add(s);
                found = true;
            }
        }
        if(!found){
           return null; 
        }
        return rs;
    }
}
