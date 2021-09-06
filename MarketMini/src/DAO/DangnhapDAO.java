/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.DangnhapDTO;
import DTO.NhanvienDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Tan Thuan
 */
public class DangnhapDAO {
    DB_ConnectionDAO conn=new DB_ConnectionDAO();
    
   
   
//    Lay ra tat ca danh sach tai khoan 
    public ArrayList<DangnhapDTO> readAccountDAO(){
        
       ArrayList<DangnhapDTO> list  =new ArrayList<>();
        try {
            
            String sSQL="SELECT tbl_dangnhap.id_nhanvien,tbl_dangnhap.tendangnhap"
                    + ",tbl_dangnhap.matkhau,tbl_nhanvien.chucvu_nv,tbl_nhanvien.ten_nv "
                    + "from tbl_nhanvien,tbl_dangnhap "
                    + "WHERE tbl_nhanvien.id_nhanvien=tbl_dangnhap.id_nhanvien ";
           
                   

                   
                   
            ResultSet rs=conn.sqlQuery(sSQL);
            if(rs!=null){
                while(rs.next()){
                    DangnhapDTO dn=new DangnhapDTO();
                     NhanvienDTO nv=new NhanvienDTO();
                     nv.setIdNhanvien(rs.getInt("id_nhanvien"));
                    dn.setIdNhanvienDTO(nv);
                    dn.setTenDangnhapDTO(rs.getString("tendangnhap"));
                    dn.setMatkhauDangnhapDTO(rs.getString("matkhau"));
                    nv.setChucvuNhanvien(rs.getString("chucvu_nv"));
                    dn.setChucvunvDTO(nv);
                    dn.setTenNhanvien(rs.getString("ten_nv"));
                    list.add(dn);
                }
            }
        } catch (Exception e) {
        }
        return list;
        
    }
   
//    Kiem tra danh nhap tai khoan
    public boolean checkAccount(String tenDangnhap,String matkhau){
        
        try {
            String sSQL="SELECT tbl_nhanvien.id_nhanvien,tbl_dangnhap.tendangnhap"
                    + ",tbl_dangnhap.matkhau,tbl_nhanvien.chucvu_nv "
                    + "from tbl_nhanvien,tbl_dangnhap "
                    + "WHERE tbl_nhanvien.id_nhanvien=tbl_dangnhap.id_nhanvien "
                   
                    + "and tbl_dangnhap.tendangnhap='"+tenDangnhap+"'"
                    + "and tbl_dangnhap.matkhau='"+matkhau+"'" ;
           
            
            ResultSet rs=conn.sqlQuery(sSQL);
            if(rs!=null){
                while (rs.next()) {
                    DangnhapDTO dn=new DangnhapDTO();
                    NhanvienDTO nv=new NhanvienDTO();
                    nv.setIdNhanvien(rs.getInt("id_nhanvien"));
                    dn.setIdNhanvienDTO(nv);
                    dn.setTenDangnhapDTO(rs.getString("tendangnhap"));
                    dn.setMatkhauDangnhapDTO(rs.getString("matkhau"));
                    nv.setChucvuNhanvien(rs.getString("tbl_nhanvien.chucvu_nv"));
                    dn.setChucvunvDTO(nv);
                    return true;
    
                }
            }
            conn.closeConnection();
        } catch (Exception e) {
            System.out.println("Không tìm thấy tài khoản");
           return false;
        }
        return false;
    }
   
//    Them tai khoan nhan vien
    public boolean AddAccountDAO(DangnhapDTO dnDTO){
        
            String sSQL="INSERT INTO `tbl_dangnhap` (`id_nhanvien`, `tendangnhap`, `matkhau`) "
                    + "VALUES ('"+dnDTO.getIdNhanvienDTO().getIdNhanvien()+"','"+dnDTO.getTenDangnhapDTO()+"','"+dnDTO.getMatkhauDangnhapDTO()+"')";
                    
            boolean result=conn.sqlUpdate(sSQL);
            conn.closeConnection();
            return result;
            
       
        
    }
    public boolean deleteAccount(DangnhapDTO dn){
        String sql="DELETE FROM `tbl_dangnhap` WHERE id_nhanvien = "+dn.getIdNhanvienDTO().getIdNhanvien()+"";
        boolean result = conn.sqlUpdate(sql);
        conn.closeConnection();
        return result;
    }
    public boolean editAccount(DangnhapDTO dn){
        String sql ="UPDATE `tbl_dangnhap` SET `matkhau`='"+dn.getMatkhauDangnhapDTO()+"',`tendangnhap`='"+dn.getTenDangnhapDTO()+"' WHERE `id_nhanvien`="+dn.getIdNhanvienDTO().getIdNhanvien()+"";
        boolean result =  conn.sqlUpdate(sql);
        return result;
    }
    
}
