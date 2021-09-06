/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhanvienDTO;
import java.util.ArrayList;
import DAO.DB_ConnectionDAO;
import DTO.DangnhapDTO;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Tan Thuan
 */
public class NhanvienDAO {

    DB_ConnectionDAO conn = new DB_ConnectionDAO();

    public ArrayList<NhanvienDTO> getListNV() {
        ArrayList<NhanvienDTO> list = new ArrayList<>();
        try {

            String sSQL = "SELECT * FROM `tbl_nhanvien`";
            ResultSet rs = conn.sqlQuery(sSQL);
            if (rs != null) {
                while (rs.next()) {
                    NhanvienDTO nv = new NhanvienDTO();
                    nv.setIdNhanvien(rs.getInt("id_nhanvien"));
                    nv.setHoNhanvien(rs.getString("ho_nv"));
                    nv.setTenNhanvien(rs.getString("ten_nv"));
                    nv.setDiaChiNhanvien(rs.getString("diachi_nv"));
                    nv.setSdtNhanvien(rs.getString("sdt_nv"));
                    nv.setChucvuNhanvien(rs.getString("chucvu_nv"));
                    nv.setLuongNhanvien(rs.getInt("luong_nv"));
                    nv.setTuoiNhanvien(rs.getInt("tuoi_nv"));
                    nv.setGioitinh(rs.getString("gioitinh_nv"));
                    nv.setTrangthai(rs.getString("trangthai_nv"));
                    list.add(nv);
                }
            }
            conn.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public boolean themNV(NhanvienDTO nv) {

        String sSQL = "INSERT INTO `tbl_nhanvien"
                + "`(`id_nhanvien`, `ten_nv`, `ho_nv`, `diachi_nv`, `sdt_nv`, `tuoi_nv`, `chucvu_nv`, `luong_nv`,`gioitinh_nv`,`trangthai_nv`) "
                + "VALUES "
                + "(" + nv.getIdNhanvien() + ",'" + nv.getTenNhanvien() + "','" + nv.getHoNhanvien() + "','" + nv.getDiaChiNhanvien() + "'," + nv.getSdtNhanvien() + "," + nv.getTuoiNhanvien() + ",'" + nv.getChucvuNhanvien() + "'," + nv.getLuongNhanvien() + ",'" + nv.getGioitinh() + "','"+nv.getTrangthai()+"')";

        boolean result = conn.sqlUpdate(sSQL);
        conn.closeConnection();
        return result;

    }

    public boolean xoaNV(NhanvienDTO nv) {
        String sSQL = "DELETE FROM `tbl_nhanvien` WHERE tbl_nhanvien.id_nhanvien = " + nv.getIdNhanvien() + "";
        boolean result = conn.sqlUpdate(sSQL);
        conn.closeConnection();
        return result;
    }

    public boolean suaNV(NhanvienDTO nv) {
        String sql = "UPDATE `tbl_nhanvien` SET "
                + "`ten_nv`='" + nv.getTenNhanvien() + "',"
                + "`ho_nv`='" + nv.getHoNhanvien() + "',"
                + "`diachi_nv`='" + nv.getDiaChiNhanvien() + "',"
                + "`sdt_nv`=" + nv.getSdtNhanvien() + ","
                + "`chucvu_nv`='" + nv.getChucvuNhanvien() + "',"
                + "`luong_nv`=" + nv.getLuongNhanvien() + ","
                + "`gioitinh_nv`='" + nv.getGioitinh() + "',"
                + "`tuoi_nv`=" + nv.getTuoiNhanvien()+ ","
                + "`trangthai_nv`='" + nv.getTrangthai()+ "' "
                + " WHERE id_nhanvien = " + nv.getIdNhanvien();
        boolean result = conn.sqlUpdate(sql);
        conn.closeConnection();
        return result;
    }

}
