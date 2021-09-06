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
public class LoaisanphamDTO {
    private int idLoaiSanphamDTO;
    private String tenLoaiSanphamDTO;

    public LoaisanphamDTO() {
    }

    public LoaisanphamDTO(int idLoaiSanphamDTO, String tenLoaiSanphamDTO) {
        this.idLoaiSanphamDTO = idLoaiSanphamDTO;
        this.tenLoaiSanphamDTO = tenLoaiSanphamDTO;
    }

    public int getIdLoaiSanphamDTO() {
        return idLoaiSanphamDTO;
    }

    public void setIdLoaiSanphamDTO(int idLoaiSanphamDTO) {
        this.idLoaiSanphamDTO = idLoaiSanphamDTO;
    }

    public String getTenLoaiSanphamDTO() {
        return tenLoaiSanphamDTO;
    }

    public void setTenLoaiSanphamDTO(String tenLoaiSanphamDTO) {
        this.tenLoaiSanphamDTO = tenLoaiSanphamDTO;
    }

    
    
    
}
