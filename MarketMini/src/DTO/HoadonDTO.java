/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author Tan Thuan
 */
public class HoadonDTO {
     private int idHoadonDTO ;
    private int idNhanvienDTO;
    private int idKhachhangDTO ;
    private int idKhuyenmaiDTO ;
    private LocalDate ngaylapHoadonDTO;
    private LocalTime giolapHoadonDTO;
    private int tongHoadonDTO;

    public HoadonDTO() {
    }

    public HoadonDTO(int idHoadonDTO, int idNhanvienDTO, int idKhachhangDTO, int idKhuyenmaiDTO, LocalDate ngaylapHoadonDTO, LocalTime giolapHoadonDTO, int tongHoadonDTO) {
        this.idHoadonDTO = idHoadonDTO;
        this.idNhanvienDTO = idNhanvienDTO;
        this.idKhachhangDTO = idKhachhangDTO;
        this.idKhuyenmaiDTO = idKhuyenmaiDTO;
        this.ngaylapHoadonDTO = ngaylapHoadonDTO;
        this.giolapHoadonDTO = giolapHoadonDTO;
        this.tongHoadonDTO = tongHoadonDTO;
    }

    public int getIdHoadonDTO() {
        return idHoadonDTO;
    }

    public void setIdHoadonDTO(int idHoadonDTO) {
        this.idHoadonDTO = idHoadonDTO;
    }

    public int getIdNhanvienDTO() {
        return idNhanvienDTO;
    }

    public void setIdNhanvienDTO(int idNhanvienDTO) {
        this.idNhanvienDTO = idNhanvienDTO;
    }

    public int getIdKhachhangDTO() {
        return idKhachhangDTO;
    }

    public void setIdKhachhangDTO(int idKhachhangDTO) {
        this.idKhachhangDTO = idKhachhangDTO;
    }

    public int getIdKhuyenmaiDTO() {
        return idKhuyenmaiDTO;
    }

    public void setIdKhuyenmaiDTO(int idKhuyenmaiDTO) {
        this.idKhuyenmaiDTO = idKhuyenmaiDTO;
    }

    public LocalDate getNgaylapHoadonDTO() {
        return ngaylapHoadonDTO;
    }

    public void setNgaylapHoadonDTO(LocalDate ngaylapHoadonDTO) {
        this.ngaylapHoadonDTO = ngaylapHoadonDTO;
    }

    public LocalTime getGiolapHoadonDTO() {
        return giolapHoadonDTO;
    }

    public void setGiolapHoadonDTO(LocalTime giolapHoadonDTO) {
        this.giolapHoadonDTO = giolapHoadonDTO;
    }

    public int getTongHoadonDTO() {
        return tongHoadonDTO;
    }

    public void setTongHoadonDTO(int tongHoadonDTO) {
        this.tongHoadonDTO = tongHoadonDTO;
    }
    
    
    
}
