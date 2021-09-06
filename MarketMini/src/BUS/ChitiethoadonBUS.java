/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChitiethoadonDAO;
import DTO.*;
import GUI.*;
import java.util.ArrayList;

/**
 *
 * @author Tan Thuan
 */
public class ChitiethoadonBUS {
    public boolean Themcthd(ChitietHoadonDTO ct){
        boolean result = new ChitiethoadonDAO().ThemCthd(ct);
        return result;
    }
    public boolean Xoacthd(int idct){
        boolean result = new ChitiethoadonDAO().XoaCthd(idct);
        return result;
    }
    public ArrayList<ChitietHoadonDTO> getList(){
        ArrayList<ChitietHoadonDTO> list = new ArrayList<>();
        list = new ChitiethoadonDAO().getListCthd();
        return list;
    }
    
    
    
}
