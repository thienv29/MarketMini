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
public class KhachhangDTO {

    private int maKhachhang;
    private String tenKhachhang;
    private String hoKhachhang;

    private String diaChi;
    private String sdtKhachhang;
    private String emailKhachhang;
    private String gioitinh;
    private int tuoiKhachhang;

    public KhachhangDTO() {
    }

    public int getTuoiKhachhang() {
        return tuoiKhachhang;
    }

    public void setTuoiKhachhang(int tuoiKhachhang) {
        this.tuoiKhachhang = tuoiKhachhang;
    }

    public KhachhangDTO(int maKhachhang, String tenKhachhang, String hoKhachhang, String diaChi, String sdtKhachhang, String emailKhachhang, String gioitinh) {
        this.maKhachhang = maKhachhang;
        this.tenKhachhang = tenKhachhang;
        this.hoKhachhang = hoKhachhang;
        this.diaChi = diaChi;
        this.sdtKhachhang = sdtKhachhang;
        this.emailKhachhang = emailKhachhang;
        this.gioitinh = gioitinh;
    }

    public int getMaKhachhang() {
        return maKhachhang;
    }

    public void setMaKhachhang(int maKhachhang) {
        this.maKhachhang = maKhachhang;
    }

    public String getEmailKhachhang() {
        return emailKhachhang;
    }

    public void setEmailKhachhang(String emailKhachhang) {
        this.emailKhachhang = emailKhachhang;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getTenKhachhang() {
        return tenKhachhang;
    }

    public void setTenKhachhang(String tenKhachhang) {
        this.tenKhachhang = tenKhachhang;
    }

    public String getHoKhachhang() {
        return hoKhachhang;
    }

    public void setHoKhachhang(String hoKhachhang) {
        this.hoKhachhang = hoKhachhang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdtKhachhang() {
        return sdtKhachhang;
    }

    public void setSdtKhachhang(String sdtKhachhang) {
        this.sdtKhachhang = sdtKhachhang;
    }

}
