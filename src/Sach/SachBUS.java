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
    
    //ham kiem tra
    public boolean ktmasach(String ma){
        return timSachTheoMa(ma) != null;
    }
    
    //them sua xoa
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
    
    //Tim kiem
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
    
    public ArrayList<Sach> timSachTheoMaTacGia(String matg){
        ArrayList<Sach> rs = new ArrayList<Sach>();
        boolean found = false;
        for(Sach s : dss){
            if(s.getMatg().toLowerCase().contains(matg.toLowerCase())){
                rs.add(s);
                found = true;
            }
        }
        if(!found){
            return null;
        }
        return rs;
    }
    
    public ArrayList<Sach> timSachTheoMaTheLoai(String matl){
        ArrayList<Sach> rs = new ArrayList<Sach>();
        boolean found = false;
        for(Sach s : dss){
            if(s.getMatl().toLowerCase().contains(matl.toLowerCase())){
                rs.add(s);
                found = true;
            }
        }
        if(!found){
            return null;
        }
        return rs;
    }
    
    public ArrayList<Sach> timSachTheoNamXuatBan(int nam){
        ArrayList<Sach> rs = new ArrayList<Sach>();
        boolean found = false;
        for(Sach s : dss){
            if(s.getNamxuatban() == nam){
                rs.add(s);
                found = true;
            }
        }
        if(!found){
            return null;
        }
        return rs;
    }
    
    public ArrayList<Sach> timSachTheoNhaXuatBan(String nxb){
        ArrayList<Sach> rs = new ArrayList<Sach>();
        boolean found = false;
        for(Sach s : dss){
            if(s.getManxb().toLowerCase().contains(nxb.toLowerCase())){
                rs.add(s);
                found = true;
            }
        }
        if(!found){
            return null;
        }
        return rs;
    }
    
    public ArrayList<Sach> timSachTheoDonGiaTrongKhoang(int giamin, int giamax){
        ArrayList<Sach> rs = new ArrayList<Sach>();
        boolean found = false;
        for(Sach s : dss){
            if(s.getDongia() >= giamin && s.getDongia() <= giamax){
                rs.add(s);
                found = true;
            }
        }
        if(!found){
            return null;
        }
        return rs;
    }
    
    //Sap Xep
    
    public ArrayList<Sach> sapXepSachTheoGiaGiamDan(){
        ArrayList<Sach> tmp = new ArrayList<Sach>(dss);
        tmp.sort((a,b) -> a.getDongia() - b.getDongia());
        return tmp;
    }
    
    public ArrayList<Sach> sapXepSachTheoGiaTangDan(){
        ArrayList<Sach> tmp = new ArrayList<Sach>(dss);
        tmp.sort((a,b) -> b.getDongia() - a.getDongia());
        return tmp;
    }
    
    //cap nhat
    public boolean capNhatSoLuongTon(String ma,int soluongban){
        Sach s = timSachTheoMa(ma);
        if(s == null){
            return false;
        }
        if(s.getSoluongton() < soluongban){
            return false;
        }
        s.setSoluongton(s.getSoluongton() - soluongban);
        return sachdao.updateSach(s);
    }
    
    public ArrayList<Sach> sachSapHet(int minsl){
        ArrayList<Sach> tmp = new ArrayList<Sach>();
        boolean found = false;
        for(Sach s : dss){
            if(s.getSoluongton() <= minsl){
                tmp.add(s);
                found = true;
            }
        }
        if(!found)return null;
        return tmp;
    }
    
    public ArrayList<Sach> sachHetHang(){
        return sachSapHet(0);
    }
    
    public void sachreload(){
        dss = sachdao.loadSach();
    }
}
