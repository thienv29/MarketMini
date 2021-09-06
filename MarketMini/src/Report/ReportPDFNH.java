/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report;
/**
 *
 * @author DELL
 */
import BUS.*;
import DTO.*;



//import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ReportPDFNH {
 

//    public ReportPDF(String mahd) throws FileNotFoundException, IOException {
//        init(mahd);
//        File myFile = new File("BillReport/" + mahd + ".pdf");
//
//        Desktop.getDesktop().open(myFile);
//    }
    
    public ReportPDFNH(String mahd){
        try {
            init(mahd);
            File myFile = new File("BillReport/"+ "NH" + mahd + ".pdf");
            
            Desktop.getDesktop().open(myFile);
        } catch (IOException ex) {
            //Logger.getLogger(ReportPDF.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Đang tạo hóa đơn");
        }
    }
    public ArrayList<ChitietphieunhaphangDTO> getListCthd(ArrayList<ChitietphieunhaphangDTO> list, int id) {
        ArrayList<ChitietphieunhaphangDTO> lt = new ArrayList<>();
        for (ChitietphieunhaphangDTO cthd : list) {
            if (cthd.getIdPhieunhaphangDTO().getIdPhieunhaphangDTO()== id) {
                lt.add(cthd);
            }
        }
        return lt;
    }
    public SanphamDTO findSP(int id,ArrayList<SanphamDTO> list) {
        for (SanphamDTO sp : list) {
            if (sp.getIdSanphamDTO() == id) {
                return sp;
            }
        }
        return null;
    }
    public NhanvienDTO findNV(int id,ArrayList<NhanvienDTO> list){
        for (NhanvienDTO sp : list) {
            if (sp.getIdNhanvien()== id) {
                return sp;
            }
        }
        return null;
    }
    public PhieunhaphangDTO findHD(int id,ArrayList<PhieunhaphangDTO> list){
        for (PhieunhaphangDTO sp : list) {
            if (sp.getIdPhieunhaphangDTO()== id) {
                return sp;
            }
        }
        return null;
    }
    public NhacungcapDTO findKH(int id,ArrayList<NhacungcapDTO> list){
        for (NhacungcapDTO kh : list) {
            if (kh.getIdNhacungcapDTO()== id) {
                return kh;
            }
        }
        return null;
    }
    public KhuyenmaiDTO findKM(int id,ArrayList<KhuyenmaiDTO> list){
        for (KhuyenmaiDTO sp : list) {
            if (sp.getIdKhuyenmaiDTO()== id) {
                return sp;
            }
        }
        return null;
    }

    public String Chuyentien(String fm) {
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat fmmoney = NumberFormat.getCurrencyInstance(vietnam);
        String c = fmmoney.format(new BigDecimal(fm.toString()));
        return c;
    }

    public void init(String mahd) throws FileNotFoundException, IOException {
        
        ArrayList<PhieunhaphangDTO> listPNH = new PhieunhaphangBUS().readBUSs();
        ArrayList<ChitietphieunhaphangDTO> listCPNH = new ChitietphieunhapBUS().getList();
        ArrayList<NhanvienDTO> listNV = new NhanvienBUS().getList();
        ArrayList<SanphamDTO> listSP = new SanphamBUS().getList();
        ArrayList<NhacungcapDTO> listNCC = new NhacungcapBUS().getList();
        
        
        PhieunhaphangDTO hd = findHD(Integer.parseInt(mahd), listPNH);
        
        //khuyenmaibus.docDSKMT(); 
        PdfWriter writer = new PdfWriter("BillReport/" +"NH"+ mahd + ".pdf");
        PdfFont font = PdfFontFactory.createFont("font/OpenSans-Regular.ttf", PdfEncodings.IDENTITY_H, true);

        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A5);
        PdfPage pdfPage = pdf.addNewPage();

        Paragraph tencuahang = new Paragraph("Siêu thị GS25");
        tencuahang.setTextAlignment(TextAlignment.CENTER).setFont(font);
        Paragraph diachi = new Paragraph("273 An Dương Vương, Phường 3, Quận 5, Hồ Chí Minh");
        diachi.setTextAlignment(TextAlignment.CENTER).setFont(font);
        Paragraph row = new Paragraph("----------------------------------------------");
        row.setTextAlignment(TextAlignment.CENTER).setFont(font);

        document.add(tencuahang);
        document.add(diachi);
        document.add(row);
        
        NhacungcapDTO ncc = findKH(hd.getIdNhacungcapDTO(),listNCC);
        NhanvienDTO nv = findNV(hd.getIdNhanvienDTO(),listNV);
        Paragraph phieuthanhtoan = new Paragraph("Hóa Đơn Phiếu Nhập Hàng");
        phieuthanhtoan.setTextAlignment(TextAlignment.CENTER).setFont(font);
        Paragraph hoadonso = new Paragraph("Phiếu nhập Số: " + hd.getIdPhieunhaphangDTO());
        hoadonso.setFont(font);
        Paragraph khachhang = new Paragraph("Nhà cung cấp: " + hd.getIdNhacungcapDTO()+ " - " + ncc.getTenNhacungcapDTO());
        khachhang.setFont(font);
        Paragraph nhanvien = new Paragraph("Nhân viên: " + hd.getIdNhanvienDTO()+ " - " + nv.getHoNhanvien()+" "+ nv.getTenNhanvien());
        nhanvien.setFont(font);
        Paragraph thoigian = new Paragraph("Ngày lập: " + hd.getNgayNhaphangDTO());
        thoigian.setFont(font);

        document.add(phieuthanhtoan);
        document.add(hoadonso);
        document.add(khachhang);
        document.add(nhanvien);
        document.add(thoigian);
        document.add(row);

        Paragraph phieuthanhtoanct = new Paragraph("Chi Tiết Phiếu Nhập");
        phieuthanhtoanct.setTextAlignment(TextAlignment.CENTER).setFont(font);

        float[] pointColumnWidths = {120F, 85F, 85F, 85F};
        Table table = new Table(pointColumnWidths);
        table.addHeaderCell(new Cell().add("Sản phẩm").setBackgroundColor(Color.YELLOW).setTextAlignment(TextAlignment.CENTER).setFont(font));
        table.addHeaderCell(new Cell().add("Số lượng").setBackgroundColor(Color.YELLOW).setTextAlignment(TextAlignment.CENTER).setFont(font));
        table.addHeaderCell(new Cell().add("Đơn giá").setBackgroundColor(Color.YELLOW).setTextAlignment(TextAlignment.CENTER).setFont(font));
        table.addHeaderCell(new Cell().add("Thành tiền").setBackgroundColor(Color.YELLOW).setTextAlignment(TextAlignment.CENTER).setFont(font));
        ArrayList<ChitietphieunhaphangDTO> listct=getListCthd(listCPNH,Integer.parseInt(mahd));
        for (ChitietphieunhaphangDTO cthd : listct) {
            //table.addCell(new Cell().add(cthd.getTen()).setTextAlignment(TextAlignment.CENTER).setFont(font));
            SanphamDTO sp = findSP(cthd.getIdSanphamDTO().getIdSanphamDTO(), listSP);
            
            
            
            table.addCell(new Cell().add(sp.getTensanphamDTO()).setTextAlignment(TextAlignment.CENTER).setFont(font));
            table.addCell(new Cell().add(String.valueOf(cthd.getSoLuongnhapDTO())).setTextAlignment(TextAlignment.CENTER).setFont(font));
            //table.addCell(new Cell().add(Chuyentien(String.valueOf(cthd.getDongia()))).setTextAlignment(TextAlignment.CENTER).setFont(font));
            table.addCell(new Cell().add(Chuyentien(String.valueOf(sp.getDongiasanphamDTO()))).setTextAlignment(TextAlignment.CENTER).setFont(font));
            int thanhtien = sp.getDongiasanphamDTO()*cthd.getSoLuongnhapDTO();
            table.addCell(new Cell().add(Chuyentien(String.valueOf(thanhtien))).setTextAlignment(TextAlignment.CENTER).setFont(font));
        }

        document.add(phieuthanhtoanct);
        document.add(table);
        Paragraph row2 = new Paragraph("----------------------------------------------");
        row2.setTextAlignment(TextAlignment.CENTER).setFont(font);
        document.add(row2);
        //Paragraph tongsoluong = new Paragraph("Tổng số lượng: " + hd.getSoluongtong());
        //tongsoluong.setFont(font);
        Paragraph tongtien = new Paragraph("Tổng tiền: " + Chuyentien(String.valueOf(hd.getTongTiennhaphangDTO())));
        tongtien.setFont(font);
        //Paragraph giamgia = new Paragraph("Mã KM : " + hd.getMakm() + " - Tỉ lệ giảm : " + khuyenmaibus.duyetTile(hd.getMakm()) + "% - Mô tả KM : " + khuyenmaibus.duyetMota(hd.getMakm()));
        //giamgia.setFont(font);
        //Paragraph tienphaitra = new Paragraph("Tiền phải trả: " + Chuyentien(String.valueOf(hd.getThanhtien())));
//        int percent = findKM(hd.getIdKhuyenmaiDTO(), listKM).getPhantramKhuyenmaiDTO();
//        Paragraph tiengiamgia = new Paragraph("Tiền khuyến mãi:"+Chuyentien(String.valueOf(hd.getTongHoadonDTO()/100*percent)));
        
//        tiengiamgia.setFont(font);
        Paragraph tienphaitra = new Paragraph("Tiền phải trả: " + Chuyentien(String.valueOf(hd.getTongTiennhaphangDTO())));
        tienphaitra.setFont(font);

        //document.add(tongsoluong);
        document.add(tongtien);
        //document.add(giamgia);
//        document.add(tiengiamgia);
        document.add(tienphaitra);

        document.close();

    }

    public static void main(String[] args) throws IOException {
        //ReportPDF test = new ReportPDF("HD01");
        ReportPDFNH test = new ReportPDFNH("236");
    }
}
