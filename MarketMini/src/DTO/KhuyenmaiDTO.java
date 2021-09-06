/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Tan Thuan
 */
public class KhuyenmaiDTO {
    private int idKhuyenmaiDTO;
    private String tenKhuyenmaiDTO;
    private Date ngaybdKhuyenmaiDTO;
    private Date ngayktKhuyenmaiDTO;
    private int phantramKhuyenmaiDTO;
    private int dieukienKHuyemaiDTO;
    private String noidungkhuyenmaiDTO;

    public KhuyenmaiDTO() {
    }

    public KhuyenmaiDTO(int idKhuyenmaiDTO, String tenKhuyenmaiDTO, Date ngaybdKhuyenmaiDTO, Date ngayktKhuyenmaiDTO, int phantramKhuyenmaiDTO, int dieukienKHuyemaiDTO, String noidungkhuyenmaiDTO) {
        this.idKhuyenmaiDTO = idKhuyenmaiDTO;
        this.tenKhuyenmaiDTO = tenKhuyenmaiDTO;
        this.ngaybdKhuyenmaiDTO = ngaybdKhuyenmaiDTO;
        this.ngayktKhuyenmaiDTO = ngayktKhuyenmaiDTO;
        this.phantramKhuyenmaiDTO = phantramKhuyenmaiDTO;
        this.dieukienKHuyemaiDTO = dieukienKHuyemaiDTO;
        this.noidungkhuyenmaiDTO = noidungkhuyenmaiDTO;
    }

    public int getIdKhuyenmaiDTO() {
        return idKhuyenmaiDTO;
    }

    public void setIdKhuyenmaiDTO(int idKhuyenmaiDTO) {
        this.idKhuyenmaiDTO = idKhuyenmaiDTO;
    }

    public String getTenKhuyenmaiDTO() {
        return tenKhuyenmaiDTO;
    }

    public void setTenKhuyenmaiDTO(String tenKhuyenmaiDTO) {
        this.tenKhuyenmaiDTO = tenKhuyenmaiDTO;
    }

    public Date getNgaybdKhuyenmaiDTO() {
        return ngaybdKhuyenmaiDTO;
    }

    public void setNgaybdKhuyenmaiDTO(Date ngaybdKhuyenmaiDTO) {
        this.ngaybdKhuyenmaiDTO = ngaybdKhuyenmaiDTO;
    }

    public Date getNgayktKhuyenmaiDTO() {
        return ngayktKhuyenmaiDTO;
    }

    public void setNgayktKhuyenmaiDTO(Date ngayktKhuyenmaiDTO) {
        this.ngayktKhuyenmaiDTO = ngayktKhuyenmaiDTO;
    }

    public int  getPhantramKhuyenmaiDTO() {
        return phantramKhuyenmaiDTO;
    }

    public void setPhantramKhuyenmaiDTO(int phantramKhuyenmaiDTO) {
        this.phantramKhuyenmaiDTO = phantramKhuyenmaiDTO;
    }

    public int getDieukienKHuyemaiDTO() {
        return dieukienKHuyemaiDTO;
    }

    public void setDieukienKHuyemaiDTO(int dieukienKHuyemaiDTO) {
        this.dieukienKHuyemaiDTO = dieukienKHuyemaiDTO;
    }

    public String getNoidungkhuyenmaiDTO() {
        return noidungkhuyenmaiDTO;
    }

    public void setNoidungkhuyenmaiDTO(String noidungkhuyenmaiDTO) {
        this.noidungkhuyenmaiDTO = noidungkhuyenmaiDTO;
    }
public String getTrangThai() {
//        LocalDate now = LocalDate.now();
//        Date date=new Date();
//        if (now.isBefore(LocalDate.parse((CharSequence) this.ngaybdKhuyenmaiDTO))) {
//            return "Chưa bắt đầu";
//        } else if (now.isAfter(LocalDate.parse((CharSequence)this.ngayktKhuyenmaiDTO))) {
//            return "Đã kết thúc";
//        } else {
//            return "Đang diễn ra";
//        }
        Date now=new Date();
        if(now.before(this.ngaybdKhuyenmaiDTO)){
            return "Chưa bắt đầu";
        }
        else if (now.after(this.ngayktKhuyenmaiDTO)) {
            return "Đã kết thúc";
        } else {
            return "Đang diễn ra";
        }
    }
   
}
