/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;


import DAO.PhieunhaphangDAO;
import DTO.PhieunhaphangDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tan Thuan
 */
public class PhieunhaphangBUS {
     PhieunhaphangDAO hdDAO=new PhieunhaphangDAO();
    ArrayList<PhieunhaphangDTO> list;
    
    public boolean addHoadonBUS(PhieunhaphangDTO hdDTO){
           boolean rsesult=hdDAO.AddphieunhaphangDAO(hdDTO);
           return rsesult;
    }
    
    public boolean XoaHoadonBUS(PhieunhaphangDTO hdDTO){
           boolean rsesult=hdDAO.DeletephieunhaphangDAO(hdDTO);
           return rsesult;
    }
    
    public boolean SuaHoadonBUS(PhieunhaphangDTO hdDTO){
           boolean rsesult=hdDAO.UpdatephieunhaphangDAO(hdDTO);
           return rsesult;
    }
    
  
    public ArrayList<PhieunhaphangDTO> readBUSs(){
            list=new ArrayList<>();
            if(hdDAO.readHoadonDAO()!=null){
                list = hdDAO.readHoadonDAO();
            }
            
            return list;
            
       
    }
}
