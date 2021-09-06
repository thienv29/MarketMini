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
public class ChitietphieunhaphangDTO {
    private PhieunhaphangDTO idPhieunhaphangDTO;
    private SanphamDTO idSanphamDTO;
    private int soLuongnhapDTO;
    private int dongiaNhapDTO;
    private int tongTienNhapDTO;

    public ChitietphieunhaphangDTO() {
    }

    public ChitietphieunhaphangDTO(PhieunhaphangDTO idPhieunhaphangDTO, SanphamDTO idSanphamDTO, int soLuongnhapDTO, int dongiaNhapDTO, int tongTienNhapDTO) {
        this.idPhieunhaphangDTO = idPhieunhaphangDTO;
        this.idSanphamDTO = idSanphamDTO;
        this.soLuongnhapDTO = soLuongnhapDTO;
        this.dongiaNhapDTO = dongiaNhapDTO;
        this.tongTienNhapDTO = tongTienNhapDTO;
    }

    public PhieunhaphangDTO getIdPhieunhaphangDTO() {
        return idPhieunhaphangDTO;
    }

    public void setIdPhieunhaphangDTO(PhieunhaphangDTO idPhieunhaphangDTO) {
        this.idPhieunhaphangDTO = idPhieunhaphangDTO;
    }

    public SanphamDTO getIdSanphamDTO() {
        return idSanphamDTO;
    }

    public void setIdSanphamDTO(SanphamDTO idSanphamDTO) {
        this.idSanphamDTO = idSanphamDTO;
    }

    public int getSoLuongnhapDTO() {
        return soLuongnhapDTO;
    }

    public void setSoLuongnhapDTO(int soLuongnhapDTO) {
        this.soLuongnhapDTO = soLuongnhapDTO;
    }

    public int getDongiaNhapDTO() {
        return dongiaNhapDTO;
    }

    public void setDongiaNhapDTO(int dongiaNhapDTO) {
        this.dongiaNhapDTO = dongiaNhapDTO;
    }

    public int getTongTienNhapDTO() {
        return tongTienNhapDTO;
    }

    public void setTongTienNhapDTO(int tongTienNhapDTO) {
        this.tongTienNhapDTO = tongTienNhapDTO;
    }
    

   
}
