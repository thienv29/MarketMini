/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.KhachhangDAO;
import DTO.KhachhangDTO;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Tan Thuan
 */
public class KhachhangBUS {
    public boolean themKH(KhachhangDTO kh){
        if("".equals(kh.getMaKhachhang())){
            JOptionPane.showMessageDialog(null,"Không được để trống Id Khách hàng");
            return false;
        }

        if(kh.getEmailKhachhang().equals("")){
            JOptionPane.showMessageDialog(null,"Không được để trống Email");
            return false;
        }else {
            String RegEx = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
            if(kh.getEmailKhachhang().matches(RegEx)==false){
                JOptionPane.showMessageDialog(null,"Phần đuôi Email phải có dạng @abcxyz.com (Trước dấu chấm không giới hạn ký tự, sau dấu chấm độ dài chuỗi bắt buộc <= 3)");
                return false;
            }
            
        }
        if(kh.getHoKhachhang().equals("")){
            JOptionPane.showMessageDialog(null,"Không được để trống họ của khách hàng");
            return false;
        }
        else{
            String RegEx = "^[^!@#$?<>.*:;,/\\-+=()\\d]{1,}$";
            if(!Pattern.matches(RegEx, kh.getHoKhachhang())){
                JOptionPane.showMessageDialog(null,"Họ khách hàng không hợp lệ");
                return false;
            }
        }
        if(kh.getTenKhachhang().equals("")){
            JOptionPane.showMessageDialog(null,"Không được để trống tên khách hàng");
            return false;
        }
        else{
            String RegEx = "^[^!@#$?<>.*:;,/\\-+=()\\d]{1,}$";
            if(!Pattern.matches(RegEx,kh.getTenKhachhang())){
                JOptionPane.showMessageDialog(null,"Tên khách hàng không hợp lệ");
                return false;
            }
        }
        if(kh.getDiaChi().equals("")){
            JOptionPane.showMessageDialog(null,"Không được để trống địa chỉ của khách hàng");
            return false;
        }
        
       if(kh.getDiaChi().equals("")){
            JOptionPane.showMessageDialog(null,"Không được để trống SDT của khách hàng");
            return false;
       }
       if(kh.getGioitinh().equals("")){
            JOptionPane.showMessageDialog(null,"Vui lòng chọn giới tính khách hàng");
            return false;
       }
       if("".equals(kh.getTuoiKhachhang())){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập tuổi khách hàng");
            return false;
       }
        boolean result = new KhachhangDAO().themKh(kh);
        return result;
    }
    public boolean themKHRong(int id){
        boolean result  = new KhachhangDAO().themkhRong(id);
        return result;
    }
    public boolean xoaKH(KhachhangDTO kh){
        boolean result = new KhachhangDAO().xoaKh(kh);
        return result;
    }
    public boolean suaKH(KhachhangDTO kh){
        boolean result = new KhachhangDAO().suaKh(kh);
        return result;
    }
    public ArrayList<KhachhangDTO> getList(){
        ArrayList<KhachhangDTO> list= new KhachhangDAO().getList();
        
        return list;
    }
     public KhachhangDTO getKh(int makh){
        for(KhachhangDTO kh:new KhachhangDAO().getList()){
            if(kh.getMaKhachhang()==makh){
                return kh;
            }
        }
        return null;
    }
}
