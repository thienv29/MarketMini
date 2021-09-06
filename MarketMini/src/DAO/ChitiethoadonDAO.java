/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChitietHoadonDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 * @author Tan Thuan
 */
public class ChitiethoadonDAO {

    DB_ConnectionDAO conn = new DB_ConnectionDAO();

    public boolean ThemCthd(ChitietHoadonDTO cthd) {
        String sql = "INSERT INTO `tbl_chitiethoadon`"
                + "(`id_hoadon`, `id_sanpham`, `soluong`, `gia`, `tongtien_loaisanpham`) VALUES "
                + "(" + cthd.getIdHoadonDTO() + "," + cthd.getIdSanphamDTO() + "," + cthd.getSoluongDTO() + "," + cthd.getDongiaDTO() + "," + cthd.getTongGia() + ")";
        boolean result = conn.sqlUpdate(sql);
        return result;
    }

    public boolean XoaCthd(int idhd) {
        String sql = "DELETE FROM `tbl_chitiethoadon` WHERE id_hoadon=" + idhd;
        boolean result = conn.sqlUpdate(sql);
        return result;
    }
    

    public ArrayList<ChitietHoadonDTO> getListCthd() {
        ArrayList<ChitietHoadonDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `tbl_chitiethoadon` WHERE 1";
            ResultSet rs = conn.sqlQuery(sql);
            if (rs != null) {

                while (rs.next()) {
                    ChitietHoadonDTO cthd = new ChitietHoadonDTO();
                    cthd.setIdHoadonDTO(rs.getInt("id_hoadon"));
                    cthd.setSoluongDTO(rs.getInt("soluong"));
                    cthd.setDongiaDTO(rs.getInt("gia"));
                    cthd.setTongGia(rs.getInt("tongtien_loaisanpham"));
                    cthd.setIdSanphamDTO(rs.getInt("id_sanpham"));
                    list.add(cthd);
                }
                
            }
            conn.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
