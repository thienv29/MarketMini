/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.LoaisanphamDAO;
import DTO.LoaisanphamDTO;
import java.util.ArrayList;

/**
 *
 * @author E6540
 */
public class LoaisanphamBUS {
    public boolean themLSP(LoaisanphamDTO lsp){
        boolean result = new LoaisanphamDAO().AddAloaisanphamDAO(lsp);
        return result;
    }
    public boolean suaLSP(LoaisanphamDTO lsp){
        boolean result = new LoaisanphamDAO().editLoaisanphamDAO(lsp);
        return result;
    }
    public ArrayList<LoaisanphamDTO> getList(){
        ArrayList<LoaisanphamDTO> list  = new ArrayList<>();
        list = new LoaisanphamDAO().readLoaianphamDAO();
        return list;
    }
    
}
