/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.HoadonDAO;
import DTO.HoadonDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tan Thuan
 */
public class HoadonBUS {
    
    HoadonDAO hdDAO=new HoadonDAO();
   ArrayList<HoadonDTO> list  =new ArrayList<>();
    
    public boolean addHoadonBUS(HoadonDTO hdDTO){
           boolean rsesult=hdDAO.AddHoadonDAO(hdDTO);
           return rsesult;
    }
    
    public boolean XoaHoadonBUS(HoadonDTO hdDTO){
           boolean rsesult=hdDAO.DeleteHoadonDAO(hdDTO);
           return rsesult;
    }
    
    public boolean SuaHoadonBUS(HoadonDTO hdDTO){
           boolean rsesult=hdDAO.UpdateHoadonDAO(hdDTO);
           return rsesult;
    }
    
  
    public ArrayList<HoadonDTO> readBUSs(){
            
            if(hdDAO.readHoadonDAO()!=null){
                list = (ArrayList<HoadonDTO>) hdDAO.readHoadonDAO();
            }
            
            return  list;
            
       
    }
     public int getNextID() {
        return hdDAO.readHoadonDAO().size() + 1;
    }
}
