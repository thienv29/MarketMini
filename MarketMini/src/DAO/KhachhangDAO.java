/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.KhachhangDTO;
import java.util.ArrayList;
import DAO.DB_ConnectionDAO;
import DTO.NhanvienDTO;
import java.sql.ResultSet;

/**
 *
 * @author Tan Thuan
 */
public class KhachhangDAO {
    DB_ConnectionDAO conn = new DB_ConnectionDAO();
    public ArrayList<KhachhangDTO> getList(){
        ArrayList<KhachhangDTO> list =new ArrayList<>();
        try {
            String sql = "SELECT * FROM `tbl_khachhang`";
            ResultSet rs=conn.sqlQuery(sql);
            if(rs!=null){
                while(rs.next()){
                    KhachhangDTO kh = new KhachhangDTO();
                    kh.setMaKhachhang(rs.getInt("id_khachhang"));
                    kh.setTenKhachhang(rs.getString("ten_kh"));
                    kh.setHoKhachhang(rs.getString("ho_kh"));
                    kh.setGioitinh(rs.getString("gioitinh"));
                    kh.setDiaChi(rs.getString("diachi_kh"));
                    kh.setEmailKhachhang(rs.getString("email_kh"));
                    kh.setSdtKhachhang(rs.getString("sdt_kh"));
                    kh.setTuoiKhachhang(rs.getInt("tuoi_kh"));
                    list.add(kh);
                }
            }
            conn.closeConnection();
            
        } catch (Exception e) {
        }
        return list;
    }
    public boolean themkhRong(int id){
        String sql="INSERT INTO `tbl_khachhang`(`id_khachhang`) VALUES ("+id+")";
        boolean result = conn.sqlUpdate(sql);
        return result;
    }
    public boolean themKh(KhachhangDTO kh){
        String sSQL="INSERT INTO `tbl_khachhang`"
                + "(`id_khachhang`, `ten_kh`, `ho_kh`,`gioitinh`, `diachi_kh`, `sdt_kh`, `email_kh`,`tuoi_kh`)"
                + " VALUES"
                + " ('"+kh.getMaKhachhang()+"','"+kh.getTenKhachhang()+"','"+kh.getHoKhachhang()+"','"+kh.getGioitinh()+"','"+kh.getDiaChi()+"','"+kh.getSdtKhachhang()+"','"+kh.getEmailKhachhang()+"','"+kh.getTuoiKhachhang()+"')";
                    
            boolean result=conn.sqlUpdate(sSQL);
            conn.closeConnection();
            return result;
         
        
    }
    public boolean xoaKh(KhachhangDTO kh){
        String sSQL = "DELETE FROM `tbl_khachhang` WHERE tbl_khachhang.id_khachhang = '"+kh.getMaKhachhang()+"'";
        boolean result  = conn.sqlUpdate(sSQL);
        conn.closeConnection();
        return result;
    }
     public boolean suaKh(KhachhangDTO kh){
        String sql = "UPDATE `tbl_khachhang` SET "
                    + "`ten_kh`='" + kh.getTenKhachhang()+ "',"
                    + "`ho_kh`='" + kh.getHoKhachhang()+ "',"
                    + "`gioitinh`='" + kh.getGioitinh()+ "',"
                    + "`diachi_kh`='" + kh.getDiaChi()+ "',"
                    + "`sdt_kh`=" + kh.getSdtKhachhang()+ ","
                    + "`email_kh`='" + kh.getEmailKhachhang()+ "',"
                    + "`tuoi_kh`=" + kh.getTuoiKhachhang()+ " "
                    + " WHERE id_khachhang = " + kh.getMaKhachhang();
        boolean result  =conn.sqlUpdate(sql);
        conn.closeConnection();
        return result;
    }
}
