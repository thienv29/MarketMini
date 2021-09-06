/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.LoaisanphamDTO;
import DTO.SanphamDTO;
import static java.rmi.Naming.list;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import static java.util.Collections.list;

/**
 *
 * @author Tan Thuan
 */
public class SanphamDAO {

    DB_ConnectionDAO conn = new DB_ConnectionDAO();

    // lấy tất cả các sản phẩm từ server
    public ArrayList<SanphamDTO> readSanphamDAO() {
        ArrayList<SanphamDTO> list = new ArrayList<>();
        try {
            String sSQL = "SELECT tbl_sanpham.id_sanpham,tbl_sanpham.id_loaisanpham,tbl_loaisanpham.ten_lsp"
                    + ",tbl_sanpham.ten_sp,tbl_sanpham.dongia_sp,tbl_sanpham.soluong_sp,tbl_sanpham.donvitinh_sp,tbl_sanpham.trangthai_sp "
                    + "from tbl_sanpham,tbl_loaisanpham where tbl_sanpham.id_loaisanpham = tbl_loaisanpham.id_loaisanpham ";
            ResultSet rs = conn.sqlQuery(sSQL);
            if (rs != null) {
                while (rs.next()) {
                    SanphamDTO spDTO = new SanphamDTO();
                    LoaisanphamDTO loaiDTO = new LoaisanphamDTO();
                    loaiDTO.setIdLoaiSanphamDTO(rs.getInt("id_loaisanpham"));
                    loaiDTO.setTenLoaiSanphamDTO(rs.getString("ten_lsp"));
                    spDTO.setIdSanphamDTO(rs.getInt("id_sanpham"));
                    spDTO.setIdLoaiSanphamDTO(loaiDTO);
                    spDTO.setTensanphamDTO(rs.getString("ten_sp"));
                    spDTO.setDongiasanphamDTO(rs.getInt("dongia_sp"));
                    spDTO.setSoluongsanphamDTO(rs.getInt("soluong_sp"));
                    spDTO.setDonvitinhSanphamDTO(rs.getString("donvitinh_sp"));
                    spDTO.setTrangthaisanphamDTO(rs.getString("trangthai_sp"));
                    list.add(spDTO);
                }
                conn.closeConnection();
            }

        } catch (Exception e) {

        }

        return list;

    }

    // thêm sản phẩm
    public boolean addSanphamDAO(SanphamDTO spDTO) {

        String sSQL = "INSERT INTO `tbl_sanpham` (`id_sanpham`, `id_loaisanpham`, `ten_sp`,`dongia_sp`,`soluong_sp`,`donvitinh_sp`,`trangthai_sp`) "
                + "VALUES (" + spDTO.getIdSanphamDTO() + "," + spDTO.getIdLoaiSanphamDTO().getIdLoaiSanphamDTO()
                + ",'" + spDTO.getTensanphamDTO() + "'," + spDTO.getDongiasanphamDTO()
                + "," + spDTO.getSoluongsanphamDTO() + ",'" + spDTO.getDonvitinhSanphamDTO() + "','" + spDTO.getTrangthaisanphamDTO() + "')";

        boolean result = conn.sqlUpdate(sSQL);
        conn.closeConnection();
        return result;

    }

    // sũa sản phẩm
    public boolean editSanphamDAO(SanphamDTO spDTO) {

        String sSQL = "UPDATE `tbl_sanpham` SET "
                + "`ten_sp`='" + spDTO.getTensanphamDTO() + "',"
                + "`dongia_sp`=" + spDTO.getDongiasanphamDTO() + ","
                + "`soluong_sp`=" + spDTO.getSoluongsanphamDTO() + ","
                + "`donvitinh_sp`='" + spDTO.getDonvitinhSanphamDTO() + "',"
                + "`trangthai_sp`='" + spDTO.getTrangthaisanphamDTO() + "',"
                + "`id_loaisanpham`=" + spDTO.getIdLoaiSanphamDTO().getIdLoaiSanphamDTO() + ""
                + " WHERE `id_sanpham`= " + spDTO.getIdSanphamDTO();

        boolean result = conn.sqlUpdate(sSQL);
        conn.closeConnection();
        return result;

    }

    //xóa sản phẩm
    public boolean deleteSanphamDAO(SanphamDTO spDTO) {

        String sSQL = "DELETE FROM `tbl_sanpham` WHERE tbl_sanpham.id_sanpham = " + spDTO.getIdSanphamDTO() + "";

        boolean result = conn.sqlUpdate(sSQL);
        conn.closeConnection();
        return result;
    }
    
    public boolean NhapSanPhamDAO(SanphamDTO sp){
        String sSQL = "UPDATE `tbl_sanpham` SET `soluong_sp` ="+ sp.getSoluongsanphamDTO()+" WHERE `id_sanpham` ="+sp.getIdSanphamDTO()+"";
        boolean result = conn.sqlUpdate(sSQL);
        conn.closeConnection();
        return result;
    }
    public boolean updateSoLuong(int MaSP, int SoLuong) {
     
        Boolean ok = conn.sqlUpdate("Update `tbl_sanpham` Set "
                + "soluong_sp='" + SoLuong
                + "' where id_sanpham=" + MaSP + "");
        conn.closeConnection();
        return ok;
    }
}
