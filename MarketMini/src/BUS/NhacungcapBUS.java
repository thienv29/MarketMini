/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhacungcapDAO;
import DTO.NhacungcapDTO;
import java.util.ArrayList;

/**
 *
 * @author Tan Thuan
 */
public class NhacungcapBUS {
    
    public boolean themNCC(NhacungcapDTO ncc){
        boolean result = new NhacungcapDAO().AddNhacungcapDAO(ncc);
        return result;
    }
    public boolean xoaNCC(NhacungcapDTO ncc){
        boolean result = new NhacungcapDAO().deleteNhacungcapDAO(ncc);
        return result;
    }
    public boolean suaNCC(NhacungcapDTO ncc){
        boolean result = new NhacungcapDAO().editNhacungcapDAO(ncc);
        return result;
    }
    public ArrayList<NhacungcapDTO> getList(){
        ArrayList<NhacungcapDTO> listNCC= new NhacungcapDAO().readNhacungcapDAO();
        
        return listNCC;
    }
    
}
