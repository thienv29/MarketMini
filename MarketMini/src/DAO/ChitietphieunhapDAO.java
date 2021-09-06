/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChitietphieunhaphangDTO;
import DTO.PhieunhaphangDTO;
import DTO.SanphamDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Tan Thuan
 */
public class ChitietphieunhapDAO {
    
    DB_ConnectionDAO conn=new DB_ConnectionDAO();
    // lấy data
    public ArrayList<ChitietphieunhaphangDTO> readChitietphieunhaphangDAO() {
        ArrayList<ChitietphieunhaphangDTO> list = new ArrayList<>();
        try {
            String sSQL="SELECT * FROM `tbl_chitietphieunhaphang`";
            ResultSet rs=conn.sqlQuery(sSQL);
            if(rs!=null){
                while(rs.next()){
                    ChitietphieunhaphangDTO ctpnhDTO =new ChitietphieunhaphangDTO();
                    PhieunhaphangDTO pnhDTO =new PhieunhaphangDTO();
                    SanphamDTO spDTO=new SanphamDTO();
                    pnhDTO.setIdPhieunhaphangDTO(rs.getInt("id_phieunhaphang"));
                    spDTO.setIdSanphamDTO(rs.getInt("id_sanpham"));
                    ctpnhDTO.setSoLuongnhapDTO(rs.getInt("soluongnhap"));
                    ctpnhDTO.setDongiaNhapDTO(rs.getInt("dongianhap"));
                    ctpnhDTO.setTongTienNhapDTO(rs.getInt("tongtien_pnh"));  
                    ctpnhDTO.setIdPhieunhaphangDTO(pnhDTO);
                    ctpnhDTO.setIdSanphamDTO(spDTO);
                  list.add(ctpnhDTO);
                } 
                conn.closeConnection();
            }  
        } 
            catch (Exception e) {
                }
          return list;
        
    }
    // thêm 
     public boolean AddchitietphieunhahangDAO(ChitietphieunhaphangDTO ctpnhDTO){
        
            String sSQL="INSERT INTO `tbl_chitietphieunhaphang` (`id_phieunhaphang`, `id_sanpham`, `soluongnhap`,`dongianhap`,`tongtien_pnh`) "
                    + "VALUES ("+ctpnhDTO.getIdPhieunhaphangDTO().getIdPhieunhaphangDTO()
                    +","+ctpnhDTO.getIdSanphamDTO().getIdSanphamDTO()
                    +","+ctpnhDTO.getSoLuongnhapDTO()
                    +","+ctpnhDTO.getDongiaNhapDTO()
                    +","+ctpnhDTO.getTongTienNhapDTO()+");";
                    
            boolean result=conn.sqlUpdate(sSQL);
            conn.closeConnection();
            return result;
            
       
        
    }
     
     // sũa khuyen mai
      public boolean editchitietphieunhaphangDAO(ChitietphieunhaphangDTO ctpnhDTO){
        
            String sSQL="UPDATE `tbl_chitietphieunhaphang` SET"
                    +"`id_phieunhaphang`='"+ctpnhDTO.getIdPhieunhaphangDTO()+"',"
                    +"`id_sanpham`='"+ctpnhDTO.getIdSanphamDTO()+"',"
                    +"`soluongnhap`='"+ctpnhDTO.getSoLuongnhapDTO()+"',"
                    +"`dongianhap`='"+ctpnhDTO.getDongiaNhapDTO()+"',"
                    +"`tongtien_pnh`='"+ctpnhDTO.getTongTienNhapDTO()+"',"
                   
                    + "WHERE `id_phieunhaphang`= "+ctpnhDTO.getIdPhieunhaphangDTO();
                    
                    
                    
            boolean result=conn.sqlUpdate(sSQL);
            conn.closeConnection();
            return result;
            
     
   }
    //xóa khuyenmai
  public boolean deletechitietphieunhaphang(ChitietphieunhaphangDTO ctpnhDTO){
        
            String sSQL="DELETE FROM `tbl_chitietphieunhaphang` WHERE tbl_chitietphieunhaphang.id_phieunhaphang = '"+ctpnhDTO.getIdPhieunhaphangDTO()+"'";
                    
                    
            boolean result=conn.sqlUpdate(sSQL);
            conn.closeConnection();
            return result;
            }
}
