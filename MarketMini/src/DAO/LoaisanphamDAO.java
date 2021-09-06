/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.LoaisanphamDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author windows10
 */
public class LoaisanphamDAO {

    DB_ConnectionDAO conn = new DB_ConnectionDAO();
    ////lấy data loại sản phẩm

    public ArrayList<LoaisanphamDTO> readLoaianphamDAO() {
        ArrayList<LoaisanphamDTO> list = new ArrayList<>();
        try {
            String sSQL = "SELECT * FROM tbl_loaisanpham";

            ResultSet rs = conn.sqlQuery(sSQL);
            if (rs != null) {
                while (rs.next()) {
                    LoaisanphamDTO lspDTO = new LoaisanphamDTO();
                    lspDTO.setIdLoaiSanphamDTO(rs.getInt("id_loaisanpham"));
                    lspDTO.setTenLoaiSanphamDTO(rs.getString("ten_lsp"));
                    list.add(lspDTO);
                }
            }
            conn.closeConnection();
        } catch (Exception e) {
        }
        return list;
    }

    // thêm loại sản phẩm
    public boolean AddAloaisanphamDAO(LoaisanphamDTO lspDTO) {

        String sSQL = "INSERT INTO `tbl_loaisanpham` (`id_loaisanpham`, `ten_lsp`) "
                + "VALUES (" + lspDTO.getIdLoaiSanphamDTO() + ",'" + lspDTO.getTenLoaiSanphamDTO() + "')";

        boolean result = conn.sqlUpdate(sSQL);
        return result;
    }

    //xóa loại sản phẩm
    public boolean deleteLoaisanphamDAO(LoaisanphamDTO lspDTO) {

        String sSQL = "DELETE FROM `tbl_loaisanpham` WHERE tbl_loaisanpham.id_loaisanpham = '" + lspDTO.getIdLoaiSanphamDTO() + "'";

        boolean result = conn.sqlUpdate(sSQL);
        conn.closeConnection();
        return result;
    }

    // sữa loại sản phẩm
    public boolean editLoaisanphamDAO(LoaisanphamDTO lspDTO) {

        String sSQL = "UPDATE `tbl_loaisanpham` SET"
                + " `ten_lsp` = '" + lspDTO.getTenLoaiSanphamDTO() + "' "
                + "WHERE `id_loaisanpham`= " + lspDTO.getIdLoaiSanphamDTO();

        boolean result = conn.sqlUpdate(sSQL);
        conn.closeConnection();
        return result;

    }
}
