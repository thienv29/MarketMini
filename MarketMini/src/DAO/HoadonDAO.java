/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.HoadonDTO;
import DTO.KhachhangDTO;
import DTO.NhanvienDTO;
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
public class HoadonDAO {

    DB_ConnectionDAO conn = new DB_ConnectionDAO();

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public LocalTime convertToLocalTimeViaSqlTime(Time timeToConvert) {
        return new java.sql.Time(timeToConvert.getTime()).toLocalTime();
    }

    public List<HoadonDTO> readHoadonDAO() {
        List<HoadonDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbl_hoadon";
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            ResultSet rs = conn.sqlQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    HoadonDTO hd = new HoadonDTO();

                    hd.setIdHoadonDTO(rs.getInt("id_hoadon"));
                    hd.setNgaylapHoadonDTO(convertToLocalDateViaSqlDate(rs.getDate("ngay_lap")));
                    hd.setGiolapHoadonDTO(convertToLocalTimeViaSqlTime(rs.getTime("giolap_hoadon")));
                    hd.setTongHoadonDTO(rs.getInt("tong_hoadon"));

                    hd.setIdKhuyenmaiDTO(rs.getInt("id_khuyenmai"));
                    hd.setIdNhanvienDTO(rs.getInt("id_nhanvien"));


                    hd.setIdKhachhangDTO(rs.getInt("id_khachhang"));

                    list.add(hd);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //them hoa don moi

    public boolean AddHoadonDAO(HoadonDTO hdDAO) {

        String sql = "INSERT INTO `tbl_hoadon`(id_hoadon,id_nhanvien,id_khachhang,id_khuyenmai,ngay_lap,tong_hoadon,giolap_hoadon)"
                
                + "VALUE ("
                + hdDAO.getIdHoadonDTO() + ","
                + hdDAO.getIdNhanvienDTO() + ","
                + hdDAO.getIdKhachhangDTO() + ","
                + hdDAO.getIdKhuyenmaiDTO() + ",'"
                + hdDAO.getNgaylapHoadonDTO() + "',"
                + hdDAO.getTongHoadonDTO() + ",'"
                + hdDAO.getGiolapHoadonDTO() + "')";
        boolean result = conn.sqlUpdate(sql);
        return result;
    }
    //Sua hoa don 

    public boolean UpdateHoadonDAO(HoadonDTO hdDAO) {
        String sql = "UPDATE tbl_hoadon SET id_nhanvien = " + hdDAO.getIdNhanvienDTO() + ""
                + "id_khachhang = " + hdDAO.getIdKhachhangDTO() + ""
                + "id_khuyenmai = " + hdDAO.getIdKhuyenmaiDTO() + ""
                + "ngay_lap = " + hdDAO.getNgaylapHoadonDTO() + ""
                + "tong_hoadon = " + hdDAO.getTongHoadonDTO() + ""
                + "giolap_hoadon = " + hdDAO.getGiolapHoadonDTO() + ""
                + "WHERE id_hoadon = " + hdDAO.getIdHoadonDTO();
        boolean result = conn.sqlUpdate(sql);
        return result;
    }

    //Xoa hoa don
    public boolean DeleteHoadonDAO(HoadonDTO hdDAO) {
        String sql1 = "DELETE FROM tbl_chitiethoadon WHERE id.hoadon == '" + hdDAO.getIdHoadonDTO() + "'";
        String sql2 = "DELETE FROM tbl_Hoadon WHERE id.hoadon == '" + hdDAO.getIdHoadonDTO() + "'";
        boolean rs = conn.sqlUpdate(sql1);
        boolean rs2 = conn.sqlUpdate(sql2);
        if (rs && rs2) {
            return true;
        }
        return false;

    }

}
