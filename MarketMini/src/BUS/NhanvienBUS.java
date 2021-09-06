/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import DAO.NhanvienDAO;
import DTO.NhanvienDTO;
import java.util.ArrayList;
/**
 *
 * @author Tan Thuan
 */
public class NhanvienBUS {
    ArrayList<NhanvienDTO> listNV=new ArrayList<>();
    public boolean themNV(NhanvienDTO nv){
        boolean result = new NhanvienDAO().themNV(nv);
        return result;
    }
    public boolean xoaNV(NhanvienDTO nv){
        boolean result = new NhanvienDAO().xoaNV(nv);
        return result;
    }
    public boolean suaNV(NhanvienDTO nv){
        boolean result = new NhanvienDAO().suaNV(nv);
        return result;
    }
    public NhanvienDTO search(int Id,ArrayList<NhanvienDTO> list){
        for (NhanvienDTO nv:list) {
            if (nv.getIdNhanvien() == Id) {
                return nv;
            }
            
        }
        return null;
    }
    public ArrayList<NhanvienDTO> getList(){
        listNV= new NhanvienDAO().getListNV();
        
        return listNV;
    }
    public NhanvienDTO getMaNhanvienDTO(int manv){
        for(NhanvienDTO nv:new NhanvienDAO().getListNV()){
            if(nv.getIdNhanvien()==manv){
                return nv;
            }
        }
        return null;
    }
}
