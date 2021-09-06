/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.SanphamDAO;
import DTO.SanphamDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Tan Thuan
 */
public class SanphamBUS {
 
    
    public boolean themSP(SanphamDTO sp){
        if(sp.getIdLoaiSanphamDTO().getIdLoaiSanphamDTO()<0||sp.getSoluongsanphamDTO()<0||sp.getDongiasanphamDTO()<0){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng thông số");
            return false;
        }else{
            boolean result = new SanphamDAO().addSanphamDAO(sp);
        return result;
        }
        
    }
    public boolean xoaSP(SanphamDTO sp){
        boolean result = new SanphamDAO().deleteSanphamDAO(sp);
        return result;
    }
    public boolean suaSP(SanphamDTO sp){
        if(sp.getIdLoaiSanphamDTO().getIdLoaiSanphamDTO()<0||sp.getSoluongsanphamDTO()<0||sp.getDongiasanphamDTO()<0){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng thông số");
            return false;
        }else{
            boolean result = new SanphamDAO().editSanphamDAO(sp);
        return result;
        }
    }
    public ArrayList<SanphamDTO> getList(){
        ArrayList<SanphamDTO> listSP= new SanphamDAO().readSanphamDAO();
        
        return listSP;
    }
    public boolean NhapSPmoi(SanphamDTO sp){
        boolean result = new SanphamDAO().NhapSanPhamDAO(sp);
        return result;
    }
     //    Lay sp theo ma
    public SanphamDTO getSanpham(int masp){
        for(SanphamDTO sp:new SanphamDAO().readSanphamDAO()){
            if(sp.getIdSanphamDTO()== masp){
                return sp;
            }
        }
        return null;
    }
    public boolean UpdateSoluong(int idsp,int soluong){
        return new SanphamDAO().updateSoLuong(idsp, soluong);
    }
}
