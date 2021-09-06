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
public class SanphamDTO {
    private int idSanphamDTO;
    private LoaisanphamDTO idLoaiSanphamDTO;
    private String tensanphamDTO;
    private int dongiasanphamDTO;
    private int soluongsanphamDTO;
    private String donvitinhSanphamDTO;
    private String trangthaisanphamDTO;

    public SanphamDTO() {
    }

    public SanphamDTO(int idSanphamDTO, LoaisanphamDTO idLoaiSanphamDTO, String tensanphamDTO, int dongiasanphamDTO, int soluongsanphamDTO, String donvitinhSanphamDTO, String trangthaisanphamDTO) {
        this.idSanphamDTO = idSanphamDTO;
        this.idLoaiSanphamDTO = idLoaiSanphamDTO;
        this.tensanphamDTO = tensanphamDTO;
        this.dongiasanphamDTO = dongiasanphamDTO;
        this.soluongsanphamDTO = soluongsanphamDTO;
        this.donvitinhSanphamDTO = donvitinhSanphamDTO;
        this.trangthaisanphamDTO = trangthaisanphamDTO;
    }

    public int getIdSanphamDTO() {
        return idSanphamDTO;
    }

    public void setIdSanphamDTO(int idSanphamDTO) {
        this.idSanphamDTO = idSanphamDTO;
    }

    public LoaisanphamDTO getIdLoaiSanphamDTO() {
        return idLoaiSanphamDTO;
    }

    public void setIdLoaiSanphamDTO(LoaisanphamDTO idLoaiSanphamDTO) {
        this.idLoaiSanphamDTO = idLoaiSanphamDTO;
    }

    public String getTensanphamDTO() {
        return tensanphamDTO;
    }

    public void setTensanphamDTO(String tensanphamDTO) {
        this.tensanphamDTO = tensanphamDTO;
    }

    public int getDongiasanphamDTO() {
        return dongiasanphamDTO;
    }

    public void setDongiasanphamDTO(int dongiasanphamDTO) {
        this.dongiasanphamDTO = dongiasanphamDTO;
    }

    public int getSoluongsanphamDTO() {
        return soluongsanphamDTO;
    }

    public void setSoluongsanphamDTO(int soluongsanphamDTO) {
        this.soluongsanphamDTO = soluongsanphamDTO;
    }

    public String getDonvitinhSanphamDTO() {
        return donvitinhSanphamDTO;
    }

    public void setDonvitinhSanphamDTO(String donvitinhSanphamDTO) {
        this.donvitinhSanphamDTO = donvitinhSanphamDTO;
    }

    public String getTrangthaisanphamDTO() {
        return trangthaisanphamDTO;
    }

    public void setTrangthaisanphamDTO(String trangthaisanphamDTO) {
        this.trangthaisanphamDTO = trangthaisanphamDTO;
    }
}

    