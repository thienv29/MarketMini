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
public class NhacungcapDTO {
    private int idNhacungcapDTO;
    private String tenNhacungcapDTO;
    private String sdtNhacungcapDTO;
    private String diachiNhacungcapDTO;

    public NhacungcapDTO() {
    }

    public NhacungcapDTO(int idNhacungcapDTO, String tenNhacungcapDTO, String sdtNhacungcapDTO, String diachiNhacungcapDTO) {
        this.idNhacungcapDTO = idNhacungcapDTO;
        this.tenNhacungcapDTO = tenNhacungcapDTO;
        this.sdtNhacungcapDTO = sdtNhacungcapDTO;
        this.diachiNhacungcapDTO = diachiNhacungcapDTO;
    }

    public int getIdNhacungcapDTO() {
        return idNhacungcapDTO;
    }

    public void setIdNhacungcapDTO(int idNhacungcapDTO) {
        this.idNhacungcapDTO = idNhacungcapDTO;
    }

    public String getTenNhacungcapDTO() {
        return tenNhacungcapDTO;
    }

    public void setTenNhacungcapDTO(String tenNhacungcapDTO) {
        this.tenNhacungcapDTO = tenNhacungcapDTO;
    }

    public String getSdtNhacungcapDTO() {
        return sdtNhacungcapDTO;
    }

    public void setSdtNhacungcapDTO(String sdtNhacungcapDTO) {
        this.sdtNhacungcapDTO = sdtNhacungcapDTO;
    }

    public String getDiachiNhacungcapDTO() {
        return diachiNhacungcapDTO;
    }

    public void setDiachiNhacungcapDTO(String diachiNhacungcapDTO) {
        this.diachiNhacungcapDTO = diachiNhacungcapDTO;
    }
    
    
}
