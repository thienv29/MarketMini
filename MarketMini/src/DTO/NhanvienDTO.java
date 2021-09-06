/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Tan Thuan
 */
public class NhanvienDTO {
    private int idNhanvien;
    private String tenNhanvien;
    private  String hoNhanvien;
    private String sdtNhanvien;
    private int tuoiNhanvien;
    private String chucvuNhanvien;
    private int luongNhanvien;
    private String diaChiNhanvien;
    private String gioitinh;
    private String trangthai;

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public NhanvienDTO() {
    }

    public NhanvienDTO(int idNhanvien, String tenNhanvien, String hoNhanvien, String sdtNhanvien, int tuoiNhanvien, String chucvuNhanvien, int luongNhanvien, String diaChiNhanvien, String gioitinh) {
        this.idNhanvien = idNhanvien;
        this.tenNhanvien = tenNhanvien;
        this.hoNhanvien = hoNhanvien;
        this.sdtNhanvien = sdtNhanvien;
        this.tuoiNhanvien = tuoiNhanvien;
        this.chucvuNhanvien = chucvuNhanvien;
        this.luongNhanvien = luongNhanvien;
        this.diaChiNhanvien = diaChiNhanvien;
        this.gioitinh = gioitinh;
    }

    public int getIdNhanvien() {
        return idNhanvien;
    }

    public void setIdNhanvien(int idNhanvien) {
        this.idNhanvien = idNhanvien;
    }

    public String getTenNhanvien() {
        return tenNhanvien;
    }

    public void setTenNhanvien(String tenNhanvien) {
        this.tenNhanvien = tenNhanvien;
    }

    public String getHoNhanvien() {
        return hoNhanvien;
    }

    public void setHoNhanvien(String hoNhanvien) {
        this.hoNhanvien = hoNhanvien;
    }

    public String getSdtNhanvien() {
        return sdtNhanvien;
    }

    public void setSdtNhanvien(String sdtNhanvien) {
        this.sdtNhanvien = sdtNhanvien;
    }

    public int getTuoiNhanvien() {
        return tuoiNhanvien;
    }

    public void setTuoiNhanvien(int tuoiNhanvien) {
        this.tuoiNhanvien = tuoiNhanvien;
    }

    public String getChucvuNhanvien() {
        return chucvuNhanvien;
    }

    public void setChucvuNhanvien(String chucvuNhanvien) {
        this.chucvuNhanvien = chucvuNhanvien;
    }

    public int getLuongNhanvien() {
        return luongNhanvien;
    }

    public void setLuongNhanvien(int luongNhanvien) {
        this.luongNhanvien = luongNhanvien;
    }

    public String getDiaChiNhanvien() {
        return diaChiNhanvien;
    }

    public void setDiaChiNhanvien(String diaChiNhanvien) {
        this.diaChiNhanvien = diaChiNhanvien;
    }

    
    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
    
}
