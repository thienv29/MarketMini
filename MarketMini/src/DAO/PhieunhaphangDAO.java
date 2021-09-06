/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhacungcapDTO;
import DTO.NhanvienDTO;
import DTO.PhieunhaphangDTO;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
/**
 *
 * @author Tan Thuan
 */
public class PhieunhaphangDAO {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Tan Thuan
 */
    DB_ConnectionDAO conn=new DB_ConnectionDAO();
    
    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
}
    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
}
    public LocalTime convertToLocalTimeViaSqlTime(Time timeToConvert){
        return new java.sql.Time(timeToConvert.getTime()).toLocalTime();
    }
    public ArrayList<PhieunhaphangDTO> readHoadonDAO(){
    ArrayList<PhieunhaphangDTO> list = new ArrayList<>();
    try{
        String sql = "SELECT * FROM tbl_phieunhaphang";
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        ResultSet rs = conn.sqlQuery(sql);
        if(rs != null){
            while(rs.next()){
                PhieunhaphangDTO pnh = new PhieunhaphangDTO();
                
                pnh.setIdPhieunhaphangDTO(rs.getInt("id_phieunhaphang"));
                pnh.setIdNhanvienDTO(rs.getInt("id_nhanvien"));
              
                pnh.setNgayNhaphangDTO(rs.getDate("ngaynhap_pnh"));
                pnh.setTongTiennhaphangDTO(rs.getInt("tongtien_pnh"));
                pnh.setGionNhaphangDTO(rs.getTime("giolap_pnh"));
//                NhacungcapDTO ncc = new NhacungcapDTO();
                
                pnh.setIdNhacungcapDTO(rs.getInt("id_nhacungcap"));
//                pnh.setIdNhacungcapDTO(ncc);
                
                list.add(pnh);
                
            }
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }
    return list;
}
    //them hoa don moi
    
    public boolean AddphieunhaphangDAO(PhieunhaphangDTO pnhDAO){
        //INSERT INTO `tbl_phieunhaphang` (`id_phieunhaphang`, `id_nhanvien`, `id_nhacungcap`, `ngaynhap_pnh`, `tongtien_pnh`, `giolap_pnh`) VALUES (NULL, '4026', '100', '2021-05-04', '50000', '23:04:47');
        String sql = "INSERT INTO `tbl_phieunhaphang` (`id_nhanvien`,`id_nhacungcap`,`ngaynhap_pnh`,`tongtien_pnh`,`giolap_pnh`)"
        + "VALUE ('"+ pnhDAO.getIdNhanvienDTO() + "','"
                    + pnhDAO.getIdNhacungcapDTO() + "','"
                    + pnhDAO.getNgayNhaphangDTO() + "','"
                    + pnhDAO.getTongTiennhaphangDTO() + "','"
                    + pnhDAO.getGionNhaphangDTO() + "');";
            boolean result=conn.sqlUpdate(sql);
            return result;
    }
    //Sua hoa don 
    
    public boolean UpdatephieunhaphangDAO(PhieunhaphangDTO pnhDAO){
        String sql = "UPDATE tbl_phieunhaphang SET id_nhanvien = [" + pnhDAO.getIdNhanvienDTO() +"]"
                                            + "id_nhacungcap = [" + pnhDAO.getIdNhacungcapDTO() + "]"
                                            + "ngaynhap_pnh = [" + pnhDAO.getNgayNhaphangDTO()+"]"
                                            + "tongtien_pnh = ["+ pnhDAO.getTongTiennhaphangDTO()+"]"
                                            + "giolap_pnh = ["+ pnhDAO.getGionNhaphangDTO()+"]"
                                            + "WHERE id_phieunhaphang == " + pnhDAO.getIdPhieunhaphangDTO();
        boolean result = conn.sqlUpdate(sql);
        return result;
    }
    
    
    //Xoa hoa don
    
    public boolean DeletephieunhaphangDAO(PhieunhaphangDTO pnhDAO){
        String sql1 = "DELETE FROM tbl_chitietphieunhaphang WHERE id.phieunhaphang == '"+ pnhDAO.getIdPhieunhaphangDTO()+"'";
        String sql2 = "DELETE FROM tbl_phieunhaphang WHERE id.phieunhaphang == '"+ pnhDAO.getIdPhieunhaphangDTO()+"'";
        boolean rs1 = conn.sqlUpdate(sql1);
        boolean rs2 = conn.sqlUpdate(sql2);
        if(rs1 && rs2){
        return true;
        }return false;
    }
    
}


