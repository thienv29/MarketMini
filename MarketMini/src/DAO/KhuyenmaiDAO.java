/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.KhuyenmaiDTO;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Tan Thuan
 */
public class KhuyenmaiDAO {

    DB_ConnectionDAO conn = new DB_ConnectionDAO();

    // lấy data
    public ArrayList<KhuyenmaiDTO> readKhuyenmaiDAO() {
        ArrayList<KhuyenmaiDTO> list = new ArrayList<>();
        try {
            String sSQL = "SELECT * FROM `tbl_khuyenmai`";
            ResultSet rs = conn.sqlQuery(sSQL);
            if (rs != null) {
                while (rs.next()) {
                    KhuyenmaiDTO kmDTO = new KhuyenmaiDTO();
                    kmDTO.setIdKhuyenmaiDTO(rs.getInt("id_khuyenmai"));
                    kmDTO.setNgaybdKhuyenmaiDTO(rs.getDate("ngaybd_km"));
                    kmDTO.setNgayktKhuyenmaiDTO(rs.getDate("ngaykt_km"));
                    kmDTO.setNoidungkhuyenmaiDTO(rs.getString("noidung_km"));
                    kmDTO.setTenKhuyenmaiDTO(rs.getString("ten_km"));
                    kmDTO.setPhantramKhuyenmaiDTO(rs.getInt("phantram_km"));
                    kmDTO.setDieukienKHuyemaiDTO(rs.getInt("dieukien_km"));

                    list.add(kmDTO);
                }
                conn.closeConnection();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    // thêm 
    public boolean AddKhuyenmaiDAO(KhuyenmaiDTO kmDTO) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String sSQL = "INSERT INTO `tbl_khuyenmai` (`id_khuyenmai`, `ngaybd_km`, `ngaykt_km`,`noidung_km`,`ten_km`,`phantram_km`,`dieukien_km`) "
                + "VALUES (" + kmDTO.getIdKhuyenmaiDTO() + ",'" + dateFormat.format(kmDTO.getNgaybdKhuyenmaiDTO())
                + "','" + dateFormat.format(kmDTO.getNgayktKhuyenmaiDTO()) + "','" + kmDTO.getNoidungkhuyenmaiDTO()
                + "','" + kmDTO.getTenKhuyenmaiDTO() + "'," + kmDTO.getPhantramKhuyenmaiDTO() + "," + kmDTO.getDieukienKHuyemaiDTO() + ")";

        boolean result = conn.sqlUpdate(sSQL);
        conn.closeConnection();
        return result;

    }

    // sũa khuyen mai
    public boolean editKhuyenmaiDAO(KhuyenmaiDTO kmDTO) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String sSQL = "UPDATE `tbl_khuyenmai` SET "
                + "`ngaybd_km`='" + dateFormat.format(kmDTO.getNgaybdKhuyenmaiDTO()) + "',"
                + "`ngaykt_km`='" + dateFormat.format(kmDTO.getNgayktKhuyenmaiDTO()) + "',"
                + "`noidung_km`='" + kmDTO.getNoidungkhuyenmaiDTO() + "',"
                + "`ten_km`='" + kmDTO.getTenKhuyenmaiDTO() + "',"
                + "`phantram_km`=" + kmDTO.getPhantramKhuyenmaiDTO() + ","
                + "`dieukien_km`=" + kmDTO.getDieukienKHuyemaiDTO() + " "
                + " WHERE `id_khuyenmai`= " + kmDTO.getIdKhuyenmaiDTO();

        boolean result = conn.sqlUpdate(sSQL);
        conn.closeConnection();
        return result;

    }

    //xóa khuyenmai
    public boolean deleteKhuyenmaiDAO(KhuyenmaiDTO kmDTO) {

        String sSQL = "DELETE FROM `tbl_khuyenmai` WHERE tbl_khuyenmai.id_khuyenmai = '" + kmDTO.getIdKhuyenmaiDTO() + "'";

        boolean result = conn.sqlUpdate(sSQL);
        conn.closeConnection();
        return result;
    }

}
