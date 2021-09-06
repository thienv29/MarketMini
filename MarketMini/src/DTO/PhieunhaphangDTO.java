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
public class PhieunhaphangDTO {
    private int idPhieunhaphangDTO;
    private int idNhanvienDTO;
    private int idNhacungcapDTO;
    private Date ngayNhaphangDTO;
    private Date gionNhaphangDTO;
    private int tongTiennhaphangDTO;

    public PhieunhaphangDTO() {
    }

    public PhieunhaphangDTO(int idPhieunhaphangDTO, int idNhanvienDTO, int idNhacungcapDTO, Date ngayNhaphangDTO, Date gionNhaphangDTO, int tongTiennhaphangDTO) {
        this.idPhieunhaphangDTO = idPhieunhaphangDTO;
        this.idNhanvienDTO = idNhanvienDTO;
        this.idNhacungcapDTO = idNhacungcapDTO;
        this.ngayNhaphangDTO = ngayNhaphangDTO;
        this.gionNhaphangDTO = gionNhaphangDTO;
        this.tongTiennhaphangDTO = tongTiennhaphangDTO;
    }

    public int getIdPhieunhaphangDTO() {
        return idPhieunhaphangDTO;
    }

    public void setIdPhieunhaphangDTO(int idPhieunhaphangDTO) {
        this.idPhieunhaphangDTO = idPhieunhaphangDTO;
    }

    public int getIdNhanvienDTO() {
        return idNhanvienDTO;
    }

    public void setIdNhanvienDTO(int idNhanvienDTO) {
        this.idNhanvienDTO = idNhanvienDTO;
    }

    public int getIdNhacungcapDTO() {
        return idNhacungcapDTO;
    }

    public void setIdNhacungcapDTO(int idNhacungcapDTO) {
        this.idNhacungcapDTO = idNhacungcapDTO;
    }

    public Date getNgayNhaphangDTO() {
        return ngayNhaphangDTO;
    }

    public void setNgayNhaphangDTO(Date ngayNhaphangDTO) {
        this.ngayNhaphangDTO = ngayNhaphangDTO;
    }

    public Date getGionNhaphangDTO() {
        return gionNhaphangDTO;
    }

    public void setGionNhaphangDTO(Date gionNhaphangDTO) {
        this.gionNhaphangDTO = gionNhaphangDTO;
    }

    public int getTongTiennhaphangDTO() {
        return tongTiennhaphangDTO;
    }

    public void setTongTiennhaphangDTO(int tongTiennhaphangDTO) {
        this.tongTiennhaphangDTO = tongTiennhaphangDTO;
    }
    
    
}
