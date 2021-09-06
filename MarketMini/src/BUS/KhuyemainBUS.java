/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.KhuyenmaiDAO;
import DTO.KhuyenmaiDTO;
import java.util.ArrayList;

/**
 *
 * @author Tan Thuan
 */
public class KhuyemainBUS {
    
        public boolean themKM(KhuyenmaiDTO km){
        boolean result = new KhuyenmaiDAO().AddKhuyenmaiDAO(km);
        return result;
    }
    public boolean xoaKM(KhuyenmaiDTO km){
        boolean result = new KhuyenmaiDAO().deleteKhuyenmaiDAO(km);
        return result;
    }
    public boolean suaKM(KhuyenmaiDTO km){
        boolean result = new KhuyenmaiDAO().editKhuyenmaiDAO(km);
        return result;
    }
    public ArrayList<KhuyenmaiDTO> getList(){
        ArrayList<KhuyenmaiDTO> listKM= new KhuyenmaiDAO().readKhuyenmaiDAO();
        
        return listKM;
    }
    
}
