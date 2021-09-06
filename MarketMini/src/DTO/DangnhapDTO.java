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
public class DangnhapDTO {
    private NhanvienDTO idNhanvienDTO;
    private String tenDangnhapDTO;
    private String matkhauDangnhapDTO;
    private String trangthaiDTO;
    private NhanvienDTO chucvunvDTO;
    private String tenNhanvien;

    public String getTenNhanvien() {
        return tenNhanvien;
    }

    public void setTenNhanvien(String tenNhanvien) {
        this.tenNhanvien = tenNhanvien;
    }

    public DangnhapDTO() {
    }

    public DangnhapDTO(NhanvienDTO idNhanvienDTO, String tenDangnhapDTO, String matkhauDangnhapDTO, String trangthaiDTO, NhanvienDTO chucDTO) {
        this.idNhanvienDTO = idNhanvienDTO;
        this.tenDangnhapDTO = tenDangnhapDTO;
        this.matkhauDangnhapDTO = matkhauDangnhapDTO;
        this.trangthaiDTO = trangthaiDTO;
        this.chucvunvDTO = chucDTO;
    }

    public NhanvienDTO getIdNhanvienDTO() {
        return idNhanvienDTO;
    }

    public void setIdNhanvienDTO(NhanvienDTO idNhanvienDTO) {
        this.idNhanvienDTO = idNhanvienDTO;
    }

    public String getTenDangnhapDTO() {
        return tenDangnhapDTO;
    }

    public void setTenDangnhapDTO(String tenDangnhapDTO) {
        this.tenDangnhapDTO = tenDangnhapDTO;
    }

    public String getMatkhauDangnhapDTO() {
        return matkhauDangnhapDTO;
    }

    public void setMatkhauDangnhapDTO(String matkhauDangnhapDTO) {
        this.matkhauDangnhapDTO = matkhauDangnhapDTO;
    }

    public String getTrangthaiDTO() {
        return trangthaiDTO;
    }

    public void setTrangthaiDTO(String trangthaiDTO) {
        this.trangthaiDTO = trangthaiDTO;
    }

    public NhanvienDTO getChucvunvDTO() {
        return chucvunvDTO;
    }

    public void setChucvunvDTO(NhanvienDTO chucvunvDTO) {
        this.chucvunvDTO = chucvunvDTO;
    }

    
    
    
}
