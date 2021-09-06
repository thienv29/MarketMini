/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChitietphieunhapDAO;
import DTO.ChitietphieunhaphangDTO;
import java.util.ArrayList;

/**
 *
 * @author Tan Thuan
 */
public class ChitietphieunhapBUS {
    
       public boolean themCTPNH(ChitietphieunhaphangDTO CTPNH){
        boolean result = new ChitietphieunhapDAO().AddchitietphieunhahangDAO(CTPNH);
        return result;
    }
    public boolean xoaCTPNH(ChitietphieunhaphangDTO CTPNH){
        boolean result = new ChitietphieunhapDAO().deletechitietphieunhaphang(CTPNH);
        return result;
    }
    public boolean suaCTPNH(ChitietphieunhaphangDTO CTPNH){
        boolean result = new ChitietphieunhapDAO().editchitietphieunhaphangDAO(CTPNH);
        return result;
    }
    public ArrayList<ChitietphieunhaphangDTO> getList(){
        ArrayList<ChitietphieunhaphangDTO> listCTPNH= new ChitietphieunhapDAO().readChitietphieunhaphangDAO();
        
        return listCTPNH;
    }
    
    
    
}
