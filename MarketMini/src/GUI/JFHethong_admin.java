/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

//hfdpshrwgsherku
 */
package GUI;

import BUS.*;
import DTO.*;
import java.time.YearMonth;
import BUS.NhanvienBUS;
import BUS.SanphamBUS;
import BUS.KhuyemainBUS;
import DTO.NhanvienDTO;
import DTO.SanphamDTO;
import Report.ReportPDF;
import Report.ReportPDFNH;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;
import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.TimerTask;

/**
 *
 * @author E6540
 */
public class JFHethong_admin extends javax.swing.JFrame {

    DefaultTableModel modelbhleft = new DefaultTableModel();
    DefaultTableModel modelbhright = new DefaultTableModel();
    DefaultTableModel modelkh = new DefaultTableModel();
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel modelbh = new DefaultTableModel();
    DefaultTableModel modelnv = new DefaultTableModel();
    DefaultTableModel modelncc = new DefaultTableModel();
    DefaultTableModel modeltk = new DefaultTableModel();
    DefaultTableModel modelkm = new DefaultTableModel();
    DefaultTableModel modellsp = new DefaultTableModel();
    DefaultTableModel modelhd = new DefaultTableModel();
    DefaultTableModel modelcthd = new DefaultTableModel();
    DefaultTableModel modelsp = new DefaultTableModel();
    DefaultTableModel modelnhright = new DefaultTableModel();
    DefaultTableModel modelspsh = new DefaultTableModel();
    DefaultTableModel modelpnh = new DefaultTableModel();
    DefaultTableModel modelctpnh = new DefaultTableModel();
    DefaultTableModel modelchonKM = new DefaultTableModel();
    DefaultTableModel modelchonKH = new DefaultTableModel();

    int xMouse;
    int yMouse;
    int select = 0;
    int txTong = 0;
    int txTonghd = 0;
    double tongtienhoadon = 0;
    Date now = new Date();
    static ArrayList<SanphamDTO> listSP = new SanphamBUS().getList();
    static ArrayList<NhanvienDTO> listNV = new NhanvienBUS().getList();
    static ArrayList<KhachhangDTO> listKH = new KhachhangBUS().getList();
    static ArrayList<HoadonDTO> listHD = new HoadonBUS().readBUSs();
    static ArrayList<ChitietHoadonDTO> listCTHD = new ChitiethoadonBUS().getList();
    static ArrayList<KhuyenmaiDTO> listKM = new KhuyemainBUS().getList();
    static ArrayList<NhacungcapDTO> listNCC = new NhacungcapBUS().getList();
    static ArrayList<DangnhapDTO> listTK = new DangnhapBUS().readBUSs();
    static ArrayList<LoaisanphamDTO> listLSP = new LoaisanphamBUS().getList();
    static ArrayList<PhieunhaphangDTO> listPNH = new PhieunhaphangBUS().readBUSs();
    static ArrayList<ChitietphieunhaphangDTO> listCTPNH = new ChitietphieunhapBUS().getList();

    int User = 0;

    NhanvienDTO nvSelect = new NhanvienDTO();
    static ArrayList<ChitietHoadonDTO> dscthd = new ArrayList<>();
    SanphamBUS spBUS = new SanphamBUS();
    HoadonBUS qlhdBUS = new HoadonBUS();
    dangnhap dn = new dangnhap();
    NhanvienDTO nvDTO = new NhanvienDTO();
    NhanvienBUS nvBUS = new NhanvienBUS();
    KhuyemainBUS kmBUS = new KhuyemainBUS();
    KhachhangDTO khDTO = new KhachhangDTO();
    KhachhangBUS khBus = new KhachhangBUS();
    ChitiethoadonBUS cthdBUS = new ChitiethoadonBUS();
    Timer updateTimer;
    int DELAY = 100;

    public String uper(String s) {
        String firstLetter = s.substring(0, 1).toUpperCase();
        String remainingLetters = s.substring(1, s.length());
        s = firstLetter + remainingLetters;
        return s;
    }

    /**
     * Creates new form NewJFrame
     */
    public JFHethong_admin() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        initComponents();
        for (NhanvienDTO nvDTO : listNV) {
            if (nvDTO.getIdNhanvien() == dn.tendangnhap.getIdNhanvien()) {
                txtTenNguoidung.setText(nvDTO.getHoNhanvien() + " " + nvDTO.getTenNhanvien());
                break;
            }
        }

        updateTimer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Date currentTime = new Date();
                String formatTimeStr = "HH:mm:ss";
                DateFormat formatTime = new SimpleDateFormat(formatTimeStr);
                String formattedTimeStr = formatTime.format(currentTime);

                jLabel18.setText(formattedTimeStr);

            }

        });
        String formatTime = "yyyy-MM-dd     ";
        DateFormat fm = new SimpleDateFormat(formatTime);
        String formattedTimeStr = fm.format(now);
        jLabel17.setText(formattedTimeStr);
        updateTimer.start();
        txTongBh.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent arg0) {
                setBill();

            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                setBill();

            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                setBill();
            }

            public void setBill() {
                String km = txBhKm.getText();
                String tongs = txTongBh.getText();
                if ("".equals(km) || "".equals(tongs)) {
                } else {

                    int tong = Integer.valueOf(txTongBh.getText().split("VND")[0]);
                    String str[] = km.split("%");
                    int percentkm = Integer.parseInt(str[0]);
                    txTonghd = (tong / 100) * (100 - percentkm);
//                    txTongBhHd.setText(String.valueOf(txTonghd) + "VND");
                }

            }
        });
        ArrayList<NhanvienDTO> list = new ArrayList<>();
        for (NhanvienDTO nv : listNV) {
            if (nv.getTrangthai().equals("Hiện hành")) {
                list.add(nv);
            }

        }
        showTK();
        showNCC();
        showBH();
        showKH();
        showNV(list);
        showKM();
        showLSP();
        showHD();
        showSP(listSP);
        showNH();
        showTKe();
        showPNH();
        showCTHD(listCTHD);
        txNgaybd.setDateFormatString("yyyy-MM-dd");
        txNgaykt.setDateFormatString("yyyy-MM-dd");
        txfromLochd.setDateFormatString("yyy-dd-MM");
        txtoLochd.setDateFormatString("yyy-dd-MM");
        setLocationRelativeTo(null);
        PanelBanhang.setBackground(new Color(37, 171, 191));
        qlbhLeft.setBackground(new Color(102, 102, 102));
        qlkhLeft.setBackground(null);
        qlnhLeft.setBackground(null);
        qlthongkeLeft.setBackground(null);
        qlhdLeft.setBackground(null);
        qllspLeft.setBackground(null);
        qlpnhLeft.setBackground(null);
        qltaikhoanLeft.setBackground(null);
        qlnccLeft.setBackground(null);
        qlnvLeft.setBackground(null);
        qlspLeft.setBackground(null);
        qlkhuyenmaiLeft.setBackground(null);
//        PanelHienthi.removeAll();
//        PanelHienthi.add(panelBH);
//        PanelHienthi.repaint();   

        PanelHienthi.removeAll();
        PanelHienthi.add(panelBH);
        PanelHienthi.repaint();
//        Hien thi dilog
        addNhanvien.pack();
        editNhanvien.pack();
        editNhanvien.setLocationRelativeTo(null);
        addNhanvien.setLocationRelativeTo(null);
        addSanpham.pack();
        editSanpham.pack();
        addSanpham.setLocationRelativeTo(null);
        editSanpham.setLocationRelativeTo(null);
        addKhachhang.pack();
        addKhachhang.setLocationRelativeTo(null);
        editKhachhang.pack();
        editKhachhang.setLocationRelativeTo(null);
        addKhuyenmai.pack();
        addKhuyenmai.setLocationRelativeTo(null);
        editKhuyenmai.pack();
        editKhuyenmai.setLocationRelativeTo(null);
        addLoaisanpham.pack();
        addLoaisanpham.setLocationRelativeTo(null);
        editLoaisanpham.pack();
        editLoaisanpham.setLocationRelativeTo(null);
        addNCC.pack();
        addNCC.setLocationRelativeTo(null);
        editNCC.pack();
        editNCC.setLocationRelativeTo(null);
        addTaikhoan.pack();
        addTaikhoan.setLocationRelativeTo(null);
        editTaikhoan.pack();
        editTaikhoan.setLocationRelativeTo(null);
        selectNhanvien.pack();
        selectNhanvien.setLocationRelativeTo(null);
        selectLSP.pack();
        selectLSP.setLocationRelativeTo(null);
        selectLSPE.pack();
        selectLSPE.setLocationRelativeTo(null);
        selectSP.pack();
        selectSP.setLocationRelativeTo(null);
        selectNCC.pack();
        selectNCC.setLocationRelativeTo(null);
        chonformkm.pack();
        chonformkm.setLocationRelativeTo(null);
        chonformKH.pack();
        chonformKH.setLocationRelativeTo(null);

    }

    public void cbblsp() {

    }

    public String checkgender(JRadioButton nam, JRadioButton nu) {
        String gender = "";
        if (nam.isSelected()) {
            gender = "Nam";
        } else if (nu.isSelected()) {
            gender = "Nữ";

        } else {
            gender = "";
        }
        return gender;
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public KhuyenmaiDTO getDkKmmax(ArrayList<KhuyenmaiDTO> list) {
        int max = 0;
        KhuyenmaiDTO kmMax = new KhuyenmaiDTO();
        for (KhuyenmaiDTO km : list) {
            if (km.getPhantramKhuyenmaiDTO() > max) {
                kmMax = km;
                max = km.getPhantramKhuyenmaiDTO();
            }
        }
        return kmMax;
    }

    public SanphamDTO findSP(int id) {
        for (SanphamDTO sp : listSP) {
            if (sp.getIdSanphamDTO() == id) {
                return sp;
            }
        }
        return null;
    }

    public boolean checkIdKh(int id) {
        for (KhachhangDTO kh : listKH) {
            if (kh.getMaKhachhang() == id) {
                return true;
            }
        }
        return false;
    }

    public String Chuyentien(String fm) {
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat fmmoney = NumberFormat.getCurrencyInstance(vietnam);
        String c = fmmoney.format(new BigDecimal(fm.toString()));
        return c;
    }

    public void exportExcel(JTable table) {
        JFileChooser chooser = new JFileChooser();
        int i = chooser.showSaveDialog(chooser);
        if (i == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                FileWriter out = new FileWriter(file + ".xls");
                BufferedWriter bwrite = new BufferedWriter(out);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                // ten Cot
                for (int j = 0; j < table.getColumnCount(); j++) {
                    bwrite.write(model.getColumnName(j) + "\t");
                }
                bwrite.write("\n");
                // Lay du lieu dong
                for (int j = 0; j < table.getRowCount(); j++) {
                    for (int k = 0; k < table.getColumnCount(); k++) {
                        bwrite.write(model.getValueAt(j, k) + "\t");
                    }
                    bwrite.write("\n");
                }
                bwrite.close();
                JOptionPane.showMessageDialog(null, "Lưu file thành công!");
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Lỗi khi lưu file!");
            }
        }
    }

    public boolean checkmatchTK(int idnv, String username) {
        for (DangnhapDTO tk : listTK) {
            if (tk.getIdNhanvienDTO().getIdNhanvien() == idnv) {

                return true;
            }
            if (tk.getTenDangnhapDTO().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<ChitietHoadonDTO> getListCthd(ArrayList<ChitietHoadonDTO> list, int id) {
        ArrayList<ChitietHoadonDTO> lt = new ArrayList<>();
        for (ChitietHoadonDTO cthd : list) {
            if (cthd.getIdHoadonDTO() == id) {
                lt.add(cthd);
            }
        }
        return lt;
    }

    public ArrayList<ChitietphieunhaphangDTO> getListCtpnh(ArrayList<ChitietphieunhaphangDTO> list, int id) {
        ArrayList<ChitietphieunhaphangDTO> lt = new ArrayList<>();
        for (ChitietphieunhaphangDTO ctpnh : list) {
            if (ctpnh.getIdPhieunhaphangDTO().getIdPhieunhaphangDTO() == id) {
                lt.add(ctpnh);
            }
        }
        return lt;
    }

    public void showNH() {
        txIdPNH.setText(String.valueOf(listPNH.get(listPNH.size() - 1).getIdPhieunhaphangDTO() + 1));
        txIdNvNh.setText(String.valueOf(dn.tendangnhap.getIdNhanvien()));
    }

    public void showTKe() {
        tbSpTk.setText(String.valueOf(listSP.size()));
        lbNvTk.setText(String.valueOf(listNV.size()));
        pnchart.removeAll();
        pnchart1.removeAll();
        pnchart3.removeAll();

        int don[] = new int[13];
        int tongdon[] = new int[13];
        int doanhthu[] = new int[13];
        for (int i = 1; i <= 12; i++) {
            don[i] = 0;
            doanhthu[i] = 0;
            tongdon[i] = 0;
        }
        YearMonth date1 = YearMonth.of(Integer.valueOf(txYear.getText()), 1);
        YearMonth date2 = YearMonth.of(Integer.valueOf(txYear.getText()), 2);
        YearMonth date3 = YearMonth.of(Integer.valueOf(txYear.getText()), 3);
        YearMonth date4 = YearMonth.of(Integer.valueOf(txYear.getText()), 4);
        YearMonth date5 = YearMonth.of(Integer.valueOf(txYear.getText()), 5);
        YearMonth date6 = YearMonth.of(Integer.valueOf(txYear.getText()), 6);
        YearMonth date7 = YearMonth.of(Integer.valueOf(txYear.getText()), 7);
        YearMonth date8 = YearMonth.of(Integer.valueOf(txYear.getText()), 8);
        YearMonth date9 = YearMonth.of(Integer.valueOf(txYear.getText()), 9);
        YearMonth date10 = YearMonth.of(Integer.valueOf(txYear.getText()), 10);
        YearMonth date11 = YearMonth.of(Integer.valueOf(txYear.getText()), 11);
        YearMonth date12 = YearMonth.of(Integer.valueOf(txYear.getText()), 12);
        XYSeries xy = new XYSeries("Số lượng đơn hàng theo tháng trong năm " + txYear.getText());
        XYSeries xy2 = new XYSeries("Doanh thu hằng tháng trong năm " + txYear.getText());
        for (HoadonDTO hd : listHD) {
            YearMonth date = YearMonth.of(hd.getNgaylapHoadonDTO().getYear(), hd.getNgaylapHoadonDTO().getMonth());

            if (date.compareTo(date1) == 0) {
                don[1]++;
                tongdon[1] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date2) == 0) {
                don[2]++;
                tongdon[2] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date3) == 0) {
                don[3]++;
                tongdon[3] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date4) == 0) {
                don[4]++;
                tongdon[4] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date5) == 0) {
                don[5]++;
                tongdon[5] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date6) == 0) {
                don[6]++;
                tongdon[6] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date7) == 0) {
                don[7]++;
                tongdon[7] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date8) == 0) {
                don[8]++;
                tongdon[8] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date9) == 0) {
                don[9]++;
                tongdon[9] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date10) == 0) {
                don[10]++;
                tongdon[10] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date11) == 0) {
                don[11]++;
                tongdon[11] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date12) == 0) {
                don[12]++;
                tongdon[12] += hd.getTongHoadonDTO();
            }
        }
        for (int i = 1; i <= 12; i++) {
            xy.add(i, don[i]);
        }
        for (int i = 1; i <= 12; i++) {
            xy2.add(i, tongdon[i]);
        }

        XYSeriesCollection data = new XYSeriesCollection();
        XYSeriesCollection data2 = new XYSeriesCollection();
        data.addSeries(xy);
        data2.addSeries(xy2);
        JFreeChart chart1 = ChartFactory.createXYLineChart("", "", "", data);
        JFreeChart chart2 = ChartFactory.createXYLineChart("", "", "", data2);

        chart1.getPlot().setBackgroundPaint(new Color(255, 255, 255));
        chart2.getPlot().setBackgroundPaint(new Color(255, 255, 255));
        ChartPanel chartPn = new ChartPanel(chart1);
        ChartPanel chartPn2 = new ChartPanel(chart2);
        chartPn.setPreferredSize(new Dimension(450, 200));
        chartPn2.setPreferredSize(new Dimension(450, 200));

        int size = listSP.size();
        int a[] = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = 0;
        }
        class spbc {

            SanphamDTO spb;
            Integer soluong;

            public SanphamDTO getSpb() {
                return spb;
            }

            public void setSpb(SanphamDTO spb) {
                this.spb = spb;
            }

            public int getSoluong() {
                return soluong;
            }

            public void setSoluong(int soluong) {
                this.soluong = soluong;
            }

            public ArrayList<spbc> checkspbc(ArrayList<spbc> list, SanphamDTO sp, ChitietHoadonDTO ct) {
                for (spbc sps : list) {
                    if (sps.getSpb().getIdSanphamDTO() == sp.getIdSanphamDTO()) {
                        sps.setSoluong(sps.getSoluong() + ct.getSoluongDTO());
                        return list;
                    }
                }
                spbc spi = new spbc();
                spi.setSpb(sp);
                spi.setSoluong(ct.getSoluongDTO());
                list.add(spi);
                return list;
            }

        }

        ArrayList<spbc> listspbc = new ArrayList<>();
        YearMonth datetk = YearMonth.of(Integer.valueOf(txYear.getText()), Integer.valueOf(((String) cbthangThongke.getSelectedItem()).split(" ")[1]));

        for (SanphamDTO sp : listSP) {
            for (HoadonDTO hd : listHD) {
                if (hd.getNgaylapHoadonDTO().getYear() == datetk.getYear() && hd.getNgaylapHoadonDTO().getMonth() == datetk.getMonth()) {
                    for (ChitietHoadonDTO ct : listCTHD) {
                        if (ct.getIdHoadonDTO() == hd.getIdHoadonDTO() && ct.getIdSanphamDTO() == sp.getIdSanphamDTO()) {
                            listspbc = new spbc().checkspbc(listspbc, sp, ct);

                        }
                    }
                }
            }
        }
        Comparator<spbc> compareSoluong = (spbc o1, spbc o2) -> Integer.compare(o1.getSoluong(), o2.getSoluong());

        Collections.sort(listspbc, compareSoluong);

        DefaultCategoryDataset dta = new DefaultCategoryDataset();
        int sizeListSPBC = listspbc.size();
        if (sizeListSPBC != 0) {
            if (sizeListSPBC > 10) {
                for (int i = listspbc.size() - 1; i > listspbc.size() - 10; i--) {
                    dta.setValue(listspbc.get(i).getSoluong(), "Lượng đơn", String.valueOf(listspbc.get(i).getSpb().getIdSanphamDTO()));
                }
            } else {
                for (int i = listspbc.size() - 1; i >= 0; i--) {
                    dta.setValue(listspbc.get(i).getSoluong(), "Lượng đơn", String.valueOf(listspbc.get(i).getSpb().getIdSanphamDTO()));
                }
            }

        }

        JFreeChart chart3 = ChartFactory.createBarChart("Sản phẩm bán chạy", "ID Sản phẩm ", "Lượng đơn", dta, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = chart3.getCategoryPlot();
        p.setRangeGridlinePaint(Color.black);
        p.setBackgroundPaint(new Color(255, 255, 255));
        ChartPanel chartPn3 = new ChartPanel(chart3);
        chartPn3.setPreferredSize(new Dimension(500, 250));

        YearMonth nowtk = YearMonth.now();
        int tongLuongDonthang = 0;
        int doanhthuthang = 0;

        for (HoadonDTO hds : listHD) {
            YearMonth date = YearMonth.of(hds.getNgaylapHoadonDTO().getYear(), hds.getNgaylapHoadonDTO().getMonth());
            if (date.compareTo(nowtk) == 0) {
                tongLuongDonthang += 1;
                doanhthuthang += hds.getTongHoadonDTO();

            }

        }
        lbHdTk.setText(String.valueOf(tongLuongDonthang));
        lbDtTk.setText(Chuyentien(String.valueOf(doanhthuthang)));

        pnchart.setLayout(new FlowLayout());
        pnchart1.setLayout(new FlowLayout());
        pnchart3.setLayout(new FlowLayout());

        pnchart.add(chartPn);
        pnchart1.add(chartPn2);
        pnchart3.add(chartPn3);

        pnchart.revalidate();
        pnchart1.revalidate();
        pnchart3.revalidate();

    }

    public void showSP(ArrayList<SanphamDTO> list) {
        Vector vr = new Vector();
        vr.add("STT");
        vr.add("ID Loại sản phầm");
        vr.add("Tên loại sản phẩm");
        vr.add("ID sản phẩm");
        vr.add("Tên sản phẩm");
        vr.add("Đơn giá");
        vr.add("Số lượng");
        vr.add("Đơn vị tính");
        vr.add("Trạng thái");

        Vector vx = new Vector();
        vx.add("ID Loại sản phầm");
        vx.add("Tên loại sản phẩm");
        vx.add("Số lượng");

        int index = modelsp.getRowCount();
        int id = modelspsh.getRowCount();
        if (id == 0) {
            modelspsh = new DefaultTableModel(vx, 0);
        }
        if (index == 0) {
            modelsp = new DefaultTableModel(vr, 0);
        }
        for (SanphamDTO sp : list) {
            Object ob[] = new Object[9];
            ob[0] = modelsp.getRowCount();
            ob[1] = sp.getIdLoaiSanphamDTO().getIdLoaiSanphamDTO();
            ob[2] = sp.getIdLoaiSanphamDTO().getTenLoaiSanphamDTO();
            ob[3] = sp.getIdSanphamDTO();
            ob[4] = sp.getTensanphamDTO();
            ob[5] = sp.getDongiasanphamDTO();
            ob[6] = sp.getSoluongsanphamDTO();
            ob[7] = sp.getDonvitinhSanphamDTO();
            ob[8] = sp.getTrangthaisanphamDTO();
            modelsp.addRow(ob);
            if (sp.getSoluongsanphamDTO() < 8 && sp.getTrangthaisanphamDTO().equals("Đang bán")) {
                Object obsh[] = new Object[3];
                obsh[0] = sp.getIdSanphamDTO();
                obsh[1] = sp.getTensanphamDTO();
                obsh[2] = sp.getSoluongsanphamDTO();
                modelspsh.addRow(obsh);
            }

        }
        tblsp.setModel(modelsp);
        tblspsh.setModel(modelspsh);
        tblSelectLSP.setModel(modellsp);
        tblSelectLSPE.setModel(modellsp);
        tblSelectSP.setModel(modelsp);
    }

    public void showHD() {
        Vector header = new Vector();
        header.add("STT");
        header.add("ID hóa đơn");
        header.add("ID nhân viên");
        header.add("ID khách hàng");
        header.add("Ngày lập");
        header.add("Giờ lập");
        header.add("ID khuyến mãi");
        header.add("Tổng hóa đơn");
        if (modelhd.getRowCount() == 0) {
            modelhd = new DefaultTableModel(header, 0);
        }
        for (int i = 0; i < listHD.size(); i++) {
            Object[] row = new Object[8];
            row[0] = i;
            row[1] = (listHD.get(i).getIdHoadonDTO());
            row[2] = (listHD.get(i).getIdNhanvienDTO());
            row[3] = (listHD.get(i).getIdKhachhangDTO());
            row[4] = (listHD.get(i).getNgaylapHoadonDTO());
            row[5] = (listHD.get(i).getGiolapHoadonDTO());
            row[6] = (listHD.get(i).getIdKhuyenmaiDTO());
            row[7] = (listHD.get(i).getTongHoadonDTO());
            modelhd.addRow(row);

        }
//        for (HoadonDTO hd : list) 
//            Object ob[] = new Object[8];
//            ob[0] = modelhd.getRowCount();
//            ob[1] = hd.getIdHoadonDTO();
//            ob[2] = hd.getIdNhanvienDTO();
//            ob[3] = hd.getIdKhachhangDTO();
//            ob[4] = hd.getNgaylapHoadonDTO();
//            ob[5] = hd.getGiolapHoadonDTO();
//            ob[6] = hd.getIdKhuyenmaiDTO();
//            ob[7] = hd.getTongHoadonDTO();
//            modelhd.addRow(ob);
//        }
        tblhd.setModel(modelhd);

    }

    public void showTK() {
        Vector header = new Vector();
        header.add("STT");
        header.add("ID Nhân viên");
        header.add("Tên nhân viên");
        header.add("Tên đăng nhập");
        header.add("Mật khẩu");
        header.add("Chức vụ");
        if (modeltk.getRowCount() == 0) {
            modeltk = new DefaultTableModel(header, 0);
        }
        for (DangnhapDTO dnDTO : listTK) {
            Object object[] = new Object[6];
            object[0] = modeltk.getRowCount();
            object[1] = dnDTO.getIdNhanvienDTO().getIdNhanvien();
            object[2] = dnDTO.getTenNhanvien();
            object[3] = dnDTO.getTenDangnhapDTO();
            object[4] = dnDTO.getMatkhauDangnhapDTO();
            object[5] = dnDTO.getChucvunvDTO().getChucvuNhanvien();
            modeltk.addRow(object);
        }
        tbltk.setModel(modeltk);

    }

    public void showLSP() {
        Vector vt = new Vector();
        vt.add("STT");
        vt.add("ID loại sản phẩm");
        vt.add("Tên loại sản phẩm");
        if (modellsp.getRowCount() == 0) {
            modellsp = new DefaultTableModel(vt, 0);
        }
        for (LoaisanphamDTO lsp : listLSP) {
            Object op[] = new Object[3];
            op[0] = modellsp.getRowCount();
            op[1] = lsp.getIdLoaiSanphamDTO();
            op[2] = lsp.getTenLoaiSanphamDTO();
            modellsp.addRow(op);
        }
        tbllsp.setModel(modellsp);

    }

    public void showNCC() {
        Vector header = new Vector();
        header.add("STT");
        header.add("ID Nhà cung cấp");
        header.add("Tên nhà cung cấp");
        header.add("SDT");
        header.add("Địa chỉ");
        if (modelncc.getRowCount() == 0) {
            modelncc = new DefaultTableModel(header, 0);

        }
        for (NhacungcapDTO nccDTO : listNCC) {
            Object ob[] = new Object[5];
            ob[0] = modelncc.getRowCount();
            ob[1] = nccDTO.getIdNhacungcapDTO();
            ob[2] = nccDTO.getTenNhacungcapDTO();
            ob[3] = nccDTO.getSdtNhacungcapDTO();
            ob[4] = nccDTO.getDiachiNhacungcapDTO();
            modelncc.addRow(ob);
        }
        tblncc.setModel(modelncc);
        tblSelectNCC.setModel(modelncc);

    }

    public void showCTHD(ArrayList<ChitietHoadonDTO> list) {
        Vector header = new Vector();
        header.add("STT");
        header.add("ID sản phẩm");
        header.add("Tên sản phẩm");
        header.add("Số lượng");
        header.add("Giá");
        header.add("Tổng giá");
        if (modelcthd.getRowCount() == 0) {
            modelcthd = new DefaultTableModel(header, 0);

        }
        for (ChitietHoadonDTO ct : list) {
            Object ob[] = new Object[6];
            ob[0] = modelcthd.getRowCount();
            ob[1] = ct.getIdSanphamDTO();
            ob[2] = findSP(ct.getIdSanphamDTO()).getTensanphamDTO();
            ob[3] = ct.getSoluongDTO();
            ob[4] = ct.getDongiaDTO();
            ob[5] = ct.getTongGia();
            modelcthd.addRow(ob);
        }
        tblcthd.setModel(modelcthd);
    }

    public void showCTPN(ArrayList<ChitietphieunhaphangDTO> list) {
        Vector header = new Vector();
        header.add("ID Phiếu nhập");
        header.add("ID Sản phẩm");
        header.add("Số lượng nhập");
        header.add("Đơn giá nhập");
        header.add("Thành tiền");
        if (modelctpnh.getRowCount() == 0) {
            modelctpnh = new DefaultTableModel(header, 0);

        }
        for (ChitietphieunhaphangDTO ct : list) {
            Object ob[] = new Object[6];
            ob[0] = ct.getIdPhieunhaphangDTO().getIdPhieunhaphangDTO();
            ob[1] = ct.getIdSanphamDTO().getIdSanphamDTO();
            ob[2] = ct.getSoLuongnhapDTO();
            ob[3] = ct.getDongiaNhapDTO();
            ob[4] = ct.getTongTienNhapDTO();
            modelctpnh.addRow(ob);
        }
        tbldownctpn.setModel(modelctpnh);
    }

    public void showNV(ArrayList<NhanvienDTO> list) {

        Vector header = new Vector();
        header.add("STT");
        header.add("ID ");
        header.add("Họ ");
        header.add("Tên");
        header.add("Giới tính");
        header.add("Tuổi");
        header.add("Địa Chỉ");
        header.add("SĐT");
        header.add("Chức Vụ");
        header.add("Lương");
        header.add("Trạng thái");
        if (modelnv.getRowCount() == 0) {

            modelnv = new DefaultTableModel(header, 0);
        }

        for (int i = 0; i < list.size(); i++) {
            Object[] row = new Object[11];
            row[0] = modelnv.getRowCount();
            row[1] = (list.get(i).getIdNhanvien());
            row[2] = (list.get(i).getHoNhanvien());
            row[3] = (list.get(i).getTenNhanvien());
            row[4] = (list.get(i).getGioitinh());
            row[5] = (list.get(i).getTuoiNhanvien());
            row[6] = (list.get(i).getDiaChiNhanvien());
            row[7] = (list.get(i).getSdtNhanvien());
            row[8] = (list.get(i).getChucvuNhanvien());
            row[9] = (list.get(i).getLuongNhanvien());
            row[10] = (list.get(i).getTrangthai());

            modelnv.addRow(row);

        }
        tblnv.setModel(modelnv);
        tblTimnv.setModel(modelnv);
    }

    public void showBH() {
        modelbhleft.setColumnIdentifiers(new Object[]{
            "Mã sản phẩm", "Loại sản phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng"
        });
        modelbhright.setColumnIdentifiers(new Object[]{
            "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
        });
        for (int i = 0; i < listSP.size(); i++) {
            modelbhleft.addRow(new Object[]{
                listSP.get(i).getIdSanphamDTO(), listSP.get(i).getIdLoaiSanphamDTO().getIdLoaiSanphamDTO(), listSP.get(i).getTensanphamDTO(),
                listSP.get(i).getDongiasanphamDTO(), listSP.get(i).getSoluongsanphamDTO()
            });

        }
        txBhMahd.setText(String.valueOf(qlhdBUS.getNextID()));
        new java.util.Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                txNgaylapBh.setText(LocalDate.now().toString());
                txGiolapBh.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                if (txBhIdnv.getText().equals("")
                        || txBhIdkh.getText().equals("")
                        || txBhKm.getText().equals("")
                        || dscthd.isEmpty()) {
                    btnThanhtoan.setEnabled(false);
                } else {
                    btnThanhtoan.setEnabled(true);
                }
            }
        }, 0, 1000);

        if (dn.tendangnhap != null) {

            int manvdn = dn.tendangnhap.getIdNhanvien();

            nvDTO = nvBUS.getMaNhanvienDTO(manvdn);
            txBhIdnv.setText(nvDTO.getTenNhanvien() + "(" + String.valueOf(dn.tendangnhap.getIdNhanvien()) + ")");
        }
        tblBhLeft.setModel(modelbhleft);
        tblBhRight.setModel(modelbhright);
    }

    public void showKM() {
        Vector headerkm = new Vector();
        headerkm.add("STT");
        headerkm.add("ID khuyến mãi");
        headerkm.add("Tên khuyến mãi");
        headerkm.add("Ngày bắt đầu");
        headerkm.add("Ngày kết thúc");
        headerkm.add("Nội dung");
        headerkm.add("Phần trăm khuyến mãi");
        headerkm.add("Điều kiện");
        if (modelkm.getRowCount() == 0) {
            modelkm = new DefaultTableModel(headerkm, 0);
        }
        for (KhuyenmaiDTO km : listKM) {
            Object ob[] = new Object[8];
            ob[0] = modelkm.getRowCount();
            ob[1] = km.getIdKhuyenmaiDTO();
            ob[2] = km.getTenKhuyenmaiDTO();
            ob[3] = km.getNgaybdKhuyenmaiDTO();
            ob[4] = km.getNgayktKhuyenmaiDTO();
            ob[5] = km.getNoidungkhuyenmaiDTO();
            ob[6] = km.getPhantramKhuyenmaiDTO();
            ob[7] = km.getDieukienKHuyemaiDTO();
            modelkm.addRow(ob);

        }
        tblkm.setModel(modelkm);

    }

    public void showPNH() {
        Vector headerpnh = new Vector();
        headerpnh.add("STT");
        headerpnh.add("ID Phiếu nhập");
        headerpnh.add("ID Nhà cung cấp");
        headerpnh.add("ID Nhân viên");
        headerpnh.add("Ngày lập");
        headerpnh.add("Giờ lập");
        headerpnh.add("Tổng hóa đơn");

        if (modelpnh.getRowCount() == 0) {
            modelpnh = new DefaultTableModel(headerpnh, 0);
        }

        for (int i = 0; i < listPNH.size(); i++) {
            Object[] row = new Object[8];
            row[0] = i;
            row[1] = (listPNH.get(i).getIdPhieunhaphangDTO());
            row[2] = (listPNH.get(i).getIdNhacungcapDTO());
            row[3] = (listPNH.get(i).getIdNhanvienDTO());
            row[4] = (listPNH.get(i).getNgayNhaphangDTO());
            row[5] = (listPNH.get(i).getGionNhaphangDTO());
            row[6] = (listPNH.get(i).getTongTiennhaphangDTO());
            modelpnh.addRow(row);

        }
        tbluppnh.setModel(modelpnh);
    }

    public void showPNHtt(ArrayList<PhieunhaphangDTO> list) {
        Vector headerpnh = new Vector();
        headerpnh.add("STT");
        headerpnh.add("ID Phiếu nhập");
        headerpnh.add("ID Nhà cung cấp");
        headerpnh.add("ID Nhân viên");
        headerpnh.add("Ngày lập");
        headerpnh.add("Giờ lập");
        headerpnh.add("Tổng hóa đơn");

        if (modelpnh.getRowCount() == 0) {
            modelpnh = new DefaultTableModel(headerpnh, 0);
        }
//        listPNH
//        KhuyenmaiDTO km : listKM
//                PhieunhaphangDTO pnh :list
        int i = 0;
        for (PhieunhaphangDTO pnh : list) {
            Object[] row = new Object[8];
            row[0] = i++;
            row[1] = (pnh.getIdPhieunhaphangDTO());
            row[2] = (pnh.getIdNhacungcapDTO());
            row[3] = (pnh.getIdNhanvienDTO());
            row[4] = (pnh.getNgayNhaphangDTO());
            row[5] = (pnh.getGionNhaphangDTO());
            row[6] = (pnh.getTongTiennhaphangDTO());
            modelpnh.addRow(row);

        }
        tbluppnh.setModel(modelpnh);
    }

    public void showKH() {

        Vector headerkh = new Vector();
        headerkh.add("STT");
        headerkh.add("ID Khách hàng");
        headerkh.add("Họ Khách hàng");
        headerkh.add("Tên Khách hàng");
        headerkh.add("Giới Tính");
        headerkh.add("Địa chỉ");
        headerkh.add("SDT");
        headerkh.add("Email");
        headerkh.add("Tuổi");

        if (modelkh.getRowCount() == 0) {
            modelkh = new DefaultTableModel(headerkh, 0);
        }

        for (int i = 0; i < listKH.size(); i++) {
            Object[] row = new Object[9];
            row[0] = i;
            row[1] = (listKH.get(i).getMaKhachhang());
            row[2] = (listKH.get(i).getHoKhachhang());
            row[3] = (listKH.get(i).getTenKhachhang());
            row[4] = (listKH.get(i).getGioitinh());
            row[5] = (listKH.get(i).getDiaChi());
            row[6] = (listKH.get(i).getSdtKhachhang());
            row[7] = (listKH.get(i).getEmailKhachhang());
            row[8] = (listKH.get(i).getTuoiKhachhang());

            modelkh.addRow(row);
        }
        tblKh.setModel(modelkh);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        themkh = new javax.swing.ButtonGroup();
        addNhanvien = new javax.swing.JDialog();
        jPanel22 = new javax.swing.JPanel();
        txPositionError2 = new javax.swing.JLabel();
        btnSave1 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        lbAddNV5 = new javax.swing.JLabel();
        btnExitThemNV = new javax.swing.JButton();
        btnDel1 = new javax.swing.JButton();
        txtidnvThemnv = new javax.swing.JTextField();
        txttenThemnv = new javax.swing.JTextField();
        txthoThemnv = new javax.swing.JTextField();
        txtdiachiThemnv = new javax.swing.JTextField();
        txtsdtThemnv = new javax.swing.JTextField();
        txttuoiThemnv = new javax.swing.JTextField();
        txtluongThemnv = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        rdNamThemnv = new javax.swing.JRadioButton();
        rdNuThemnv = new javax.swing.JRadioButton();
        cbboxchucvuThemnv = new javax.swing.JComboBox<>();
        editNhanvien = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        txFnameError1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lbAddNV1 = new javax.swing.JLabel();
        btnExitSuaNV = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        txidSuanv = new javax.swing.JTextField();
        txtenSuanv = new javax.swing.JTextField();
        txhoSuanv = new javax.swing.JTextField();
        txdiachiSuanv = new javax.swing.JTextField();
        txsdtSuanv = new javax.swing.JTextField();
        txtuoiSuanv = new javax.swing.JTextField();
        txluongSuanv = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        rdNamSuanv = new javax.swing.JRadioButton();
        rdNuSuanv = new javax.swing.JRadioButton();
        cbbchucvuSuanv = new javax.swing.JComboBox<>();
        cbTrangthainv = new javax.swing.JComboBox<>();
        addKhachhang = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        txPositionError1 = new javax.swing.JLabel();
        btnSaveKh = new javax.swing.JButton();
        txFnameError2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbAddNV2 = new javax.swing.JLabel();
        btnExitThemKH = new javax.swing.JButton();
        btnResetAddKh = new javax.swing.JButton();
        txtIdkh = new javax.swing.JTextField();
        txtTenkh = new javax.swing.JTextField();
        txtHokh = new javax.swing.JTextField();
        txtDiachikh = new javax.swing.JTextField();
        txtSdtkh = new javax.swing.JTextField();
        txtEmailkh = new javax.swing.JTextField();
        gioitinhKH = new javax.swing.JPanel();
        rdNamkh = new javax.swing.JRadioButton();
        rdNukh = new javax.swing.JRadioButton();
        txtTuoiKh = new javax.swing.JTextField();
        editKhachhang = new javax.swing.JDialog();
        jPanel31 = new javax.swing.JPanel();
        txSDTError3 = new javax.swing.JLabel();
        txAgeError3 = new javax.swing.JLabel();
        txPositionError3 = new javax.swing.JLabel();
        btnSaveEditKh = new javax.swing.JButton();
        txFnameError6 = new javax.swing.JLabel();
        txLnameError3 = new javax.swing.JLabel();
        txGenderError3 = new javax.swing.JLabel();
        txAddressError3 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        lbAddNV6 = new javax.swing.JLabel();
        btnExitSuaKH = new javax.swing.JButton();
        btnDel3 = new javax.swing.JButton();
        txtIdkhEdit = new javax.swing.JTextField();
        txtTenKhEdit = new javax.swing.JTextField();
        txtHoKhEdit = new javax.swing.JTextField();
        txtDiachiKhEdit = new javax.swing.JTextField();
        txtSdtKhEdit = new javax.swing.JTextField();
        txtEmailKhEdit = new javax.swing.JTextField();
        jPanel51 = new javax.swing.JPanel();
        rdNamEdit = new javax.swing.JRadioButton();
        rdNuEdit = new javax.swing.JRadioButton();
        txtTuoiKhEdit = new javax.swing.JTextField();
        addSanpham = new javax.swing.JDialog();
        jPanel34 = new javax.swing.JPanel();
        btnSave4 = new javax.swing.JButton();
        jPanel35 = new javax.swing.JPanel();
        lbAddNV7 = new javax.swing.JLabel();
        btnExitThemSP = new javax.swing.JButton();
        btnDel4 = new javax.swing.JButton();
        txIdAddSP = new javax.swing.JTextField();
        txGiaAddSp = new javax.swing.JTextField();
        txTenAddSp = new javax.swing.JTextField();
        txLspAddSp = new javax.swing.JTextField();
        txSoluongAddSp = new javax.swing.JTextField();
        cbDvtAddSp = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        cbTrangthaiAddSp = new javax.swing.JComboBox<>();
        editSanpham = new javax.swing.JDialog();
        jPanel36 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        lbAddNV8 = new javax.swing.JLabel();
        btnSave6 = new javax.swing.JButton();
        btnDel6 = new javax.swing.JButton();
        btnExitThemSP1 = new javax.swing.JButton();
        cbTrangthaiAddSp1 = new javax.swing.JComboBox<>();
        cbDvtAddSp1 = new javax.swing.JComboBox<>();
        txSoluongAddSp1 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        txLspAddSp1 = new javax.swing.JTextField();
        txGiaAddSp1 = new javax.swing.JTextField();
        txTenAddSp1 = new javax.swing.JTextField();
        txIdAddSP1 = new javax.swing.JTextField();
        addLoaisanpham = new javax.swing.JDialog();
        jPanel38 = new javax.swing.JPanel();
        txSDTError6 = new javax.swing.JLabel();
        txAgeError6 = new javax.swing.JLabel();
        txPositionError6 = new javax.swing.JLabel();
        btnThemlsp = new javax.swing.JButton();
        txFnameError9 = new javax.swing.JLabel();
        txLnameError6 = new javax.swing.JLabel();
        txAddressError6 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        lbAddNV9 = new javax.swing.JLabel();
        btnresetThemlsp = new javax.swing.JButton();
        btnhuyThemsp = new javax.swing.JButton();
        txidThemlsp = new javax.swing.JTextField();
        txtenThemlsp = new javax.swing.JTextField();
        editLoaisanpham = new javax.swing.JDialog();
        jPanel40 = new javax.swing.JPanel();
        btnSave7 = new javax.swing.JButton();
        jPanel41 = new javax.swing.JPanel();
        lbAddNV10 = new javax.swing.JLabel();
        btnExit8 = new javax.swing.JButton();
        btnExitSuaLSP = new javax.swing.JButton();
        txidlspSualsp = new javax.swing.JTextField();
        txtenSualsp = new javax.swing.JTextField();
        addKhuyenmai = new javax.swing.JDialog();
        jPanel33 = new javax.swing.JPanel();
        btnSave8 = new javax.swing.JButton();
        jPanel42 = new javax.swing.JPanel();
        lbAddNV11 = new javax.swing.JLabel();
        btnExitThemKM = new javax.swing.JButton();
        btnDel8 = new javax.swing.JButton();
        txIdkmThemkm = new javax.swing.JTextField();
        txphantramThemkm = new javax.swing.JTextField();
        txDkThemkm = new javax.swing.JTextField();
        txTenkmThemkm = new javax.swing.JTextField();
        txNgaykt = new com.toedter.calendar.JDateChooser();
        txNgaybd = new com.toedter.calendar.JDateChooser();
        jScrollPane18 = new javax.swing.JScrollPane();
        txaNoidungThemkm = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        editKhuyenmai = new javax.swing.JDialog();
        jPanel43 = new javax.swing.JPanel();
        btnSave9 = new javax.swing.JButton();
        txLnameError9 = new javax.swing.JLabel();
        txGenderError5 = new javax.swing.JLabel();
        txAddressError9 = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        lbAddNV12 = new javax.swing.JLabel();
        btnExitSuaKM = new javax.swing.JButton();
        btnDel9 = new javax.swing.JButton();
        txIdSuakm = new javax.swing.JTextField();
        txPhantramkmSuakm = new javax.swing.JTextField();
        txDkkmSuakm = new javax.swing.JTextField();
        txTenkmSuakm = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jScrollPane25 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        addNCC = new javax.swing.JDialog();
        jPanel45 = new javax.swing.JPanel();
        btnSave10 = new javax.swing.JButton();
        jPanel46 = new javax.swing.JPanel();
        lbAddNV13 = new javax.swing.JLabel();
        btnExitThemNCC = new javax.swing.JButton();
        btnDel10 = new javax.swing.JButton();
        txidnccThemncc = new javax.swing.JTextField();
        txdiachinccThemncc = new javax.swing.JTextField();
        txtsdtnccThemncc = new javax.swing.JTextField();
        txtennccThemncc = new javax.swing.JTextField();
        editNCC = new javax.swing.JDialog();
        jPanel48 = new javax.swing.JPanel();
        btnSave11 = new javax.swing.JButton();
        txLnameError11 = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        lbAddNV14 = new javax.swing.JLabel();
        btnExitSuaNCC = new javax.swing.JButton();
        btnDel11 = new javax.swing.JButton();
        txidnccSuancc = new javax.swing.JTextField();
        txdiachiSuancc = new javax.swing.JTextField();
        txsdtSuancc = new javax.swing.JTextField();
        txtenSuancc = new javax.swing.JTextField();
        addTaikhoan = new javax.swing.JDialog();
        jPanel47 = new javax.swing.JPanel();
        btnSave12 = new javax.swing.JButton();
        jPanel50 = new javax.swing.JPanel();
        lbAddNV15 = new javax.swing.JLabel();
        btnExitThemTaikhoan = new javax.swing.JButton();
        btnDel12 = new javax.swing.JButton();
        txidThemtk = new javax.swing.JTextField();
        txmkThemtk = new javax.swing.JTextField();
        txtendnThemtk = new javax.swing.JTextField();
        jButton59 = new javax.swing.JButton();
        editTaikhoan = new javax.swing.JDialog();
        jPanel52 = new javax.swing.JPanel();
        btnSave13 = new javax.swing.JButton();
        txFnameError16 = new javax.swing.JLabel();
        jPanel53 = new javax.swing.JPanel();
        lbAddNV16 = new javax.swing.JLabel();
        btnExitSuaTaikhoan = new javax.swing.JButton();
        btnDel13 = new javax.swing.JButton();
        txidSuatk = new javax.swing.JTextField();
        txmkSuatk = new javax.swing.JTextField();
        txtendnSuatk = new javax.swing.JTextField();
        txChucvuSuatk = new javax.swing.JTextField();
        txtenSuatk = new javax.swing.JTextField();
        AcceptDeny = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        lbAcceptDeny = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        pnAccept = new javax.swing.JPanel();
        lbAccept = new javax.swing.JLabel();
        pnDeny = new javax.swing.JPanel();
        lbDeny = new javax.swing.JLabel();
        selectNhanvien = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        txFindSelectnv = new javax.swing.JTextField();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblTimnv = new javax.swing.JTable();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        selectLSP = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tblSelectLSP = new javax.swing.JTable();
        txFindSelectLSP = new javax.swing.JTextField();
        selectLSPE = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tblSelectLSPE = new javax.swing.JTable();
        txFindSelectLSPe = new javax.swing.JTextField();
        selectSP = new javax.swing.JDialog();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        tblSelectSP = new javax.swing.JTable();
        txFindSelectSP = new javax.swing.JTextField();
        selectNCC = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        tblSelectNCC = new javax.swing.JTable();
        txFindSelectNCC = new javax.swing.JTextField();
        chonformkm = new javax.swing.JDialog();
        jPanel57 = new javax.swing.JPanel();
        txPositionError4 = new javax.swing.JLabel();
        btnchonKM = new javax.swing.JButton();
        jPanel58 = new javax.swing.JPanel();
        lbAddNV17 = new javax.swing.JLabel();
        btnhuychonkm = new javax.swing.JButton();
        jScrollPane24 = new javax.swing.JScrollPane();
        tblChonKM = new javax.swing.JTable();
        jPanel59 = new javax.swing.JPanel();
        cbchonkm = new javax.swing.JComboBox<>();
        txtTimkimkm = new javax.swing.JTextField();
        chonformKH = new javax.swing.JDialog();
        jPanel60 = new javax.swing.JPanel();
        txPositionError5 = new javax.swing.JLabel();
        btnchonKH = new javax.swing.JButton();
        jPanel61 = new javax.swing.JPanel();
        lbAddNV18 = new javax.swing.JLabel();
        btnhuychonKH = new javax.swing.JButton();
        jScrollPane26 = new javax.swing.JScrollPane();
        tblChonKH = new javax.swing.JTable();
        jPanel62 = new javax.swing.JPanel();
        cbchonkm1 = new javax.swing.JComboBox<>();
        txtTimkimkm1 = new javax.swing.JTextField();
        Hethong = new javax.swing.JPanel();
        PanelMenuht = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnDangxuat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        PanelBanhang = new javax.swing.JPanel();
        labelBanhang = new javax.swing.JLabel();
        qlbhLeft = new javax.swing.JPanel();
        PanelNhaphang = new javax.swing.JPanel();
        labelnh = new javax.swing.JLabel();
        qlnhLeft = new javax.swing.JPanel();
        PanelSanpham = new javax.swing.JPanel();
        Labelsp = new javax.swing.JLabel();
        qlspLeft = new javax.swing.JPanel();
        PanelLoaisanpham = new javax.swing.JPanel();
        labelLoaisanpham = new javax.swing.JLabel();
        qllspLeft = new javax.swing.JPanel();
        PanelPhieunhaphang = new javax.swing.JPanel();
        labelPhieunhaphang = new javax.swing.JLabel();
        qlpnhLeft = new javax.swing.JPanel();
        PanelHoadon = new javax.swing.JPanel();
        labelqlhd = new javax.swing.JLabel();
        qlhdLeft = new javax.swing.JPanel();
        PanelNhacungcap = new javax.swing.JPanel();
        labelNhacungcap = new javax.swing.JLabel();
        qlnccLeft = new javax.swing.JPanel();
        PanelKhuyenmai = new javax.swing.JPanel();
        labelkhuyenmai = new javax.swing.JLabel();
        qlkhuyenmaiLeft = new javax.swing.JPanel();
        PanelNhanvien = new javax.swing.JPanel();
        labelNhanvien = new javax.swing.JLabel();
        qlnvLeft = new javax.swing.JPanel();
        PanelKhachhang = new javax.swing.JPanel();
        lbkhachhang = new javax.swing.JLabel();
        qlkhLeft = new javax.swing.JPanel();
        PanelThongke = new javax.swing.JPanel();
        labelthongke = new javax.swing.JLabel();
        qlthongkeLeft = new javax.swing.JPanel();
        PanelTaikhoan = new javax.swing.JPanel();
        qltaikhoanLeft = new javax.swing.JPanel();
        lbqltk = new javax.swing.JLabel();
        txtTenNguoidung = new javax.swing.JLabel();
        PanelHienthi = new javax.swing.JPanel();
        panelBH = new javax.swing.JPanel();
        Panelsanpham = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txFindBh = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblBhLeft = new javax.swing.JTable();
        btnBhThem = new javax.swing.JButton();
        txttenspbh = new javax.swing.JTextField();
        txtmaspbh = new javax.swing.JTextField();
        txtdongiabh = new javax.swing.JTextField();
        txtsoluongbh = new javax.swing.JTextField();
        Panelchithietbanhang = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        btnHuyBill = new javax.swing.JButton();
        btnThanhtoan = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblBhRight = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txBhMahd = new javax.swing.JTextField();
        txBhIdnv = new javax.swing.JTextField();
        txBhIdkh = new javax.swing.JTextField();
        txBhKm = new javax.swing.JTextField();
        btnthemkmbh = new javax.swing.JButton();
        txTongBh = new javax.swing.JTextField();
        txNgaylapBh = new javax.swing.JTextField();
        txGiolapBh = new javax.swing.JTextField();
        btnComboboxKH = new javax.swing.JComboBox<>();
        panelKH = new javax.swing.JPanel();
        btnDelKh = new javax.swing.JButton();
        btnSuaKH = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        txFindKh = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblKh = new javax.swing.JTable();
        jButton15 = new javax.swing.JButton();
        panelNV = new javax.swing.JPanel();
        btnThemNV = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSuaNV = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox<>();
        txFindNv = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblnv = new javax.swing.JTable();
        jButton20 = new javax.swing.JButton();
        panelNCC = new javax.swing.JPanel();
        btnThemNCC = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        btnSuaNCC = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblncc = new javax.swing.JTable();
        resetncc = new javax.swing.JButton();
        panelTaikhoan = new javax.swing.JPanel();
        btnThemTaikhoan = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        btnSuaTaikhoan = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jComboBox6 = new javax.swing.JComboBox<>();
        txFindtk = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbltk = new javax.swing.JTable();
        jButton30 = new javax.swing.JButton();
        panelKM = new javax.swing.JPanel();
        btnThemKM = new javax.swing.JButton();
        btnSuaKM = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jTextField22 = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblkm = new javax.swing.JTable();
        jButton37 = new javax.swing.JButton();
        panelLSP = new javax.swing.JPanel();
        btnThemLSP = new javax.swing.JButton();
        btnSuaLSP = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jTextField23 = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        tbllsp = new javax.swing.JTable();
        jButton42 = new javax.swing.JButton();
        panelHD = new javax.swing.JPanel();
        btnInhoadon = new javax.swing.JButton();
        btnXuatechd = new javax.swing.JButton();
        jPanel28 = new javax.swing.JPanel();
        txFindHD = new javax.swing.JTextField();
        txfromLochd = new com.toedter.calendar.JDateChooser();
        txtoLochd = new com.toedter.calendar.JDateChooser();
        btnLochd = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblhd = new javax.swing.JTable();
        btnCapnhathd = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblcthd = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        panelThongke = new javax.swing.JPanel();
        pnchart = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        tbSpTk = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lbNvTk = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lbDtTk = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        lbHdTk = new javax.swing.JLabel();
        pnchart1 = new javax.swing.JPanel();
        jPanel54 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        pnchart3 = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        txYear = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        cbthangThongke = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        tblspsh = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        panelSP = new javax.swing.JPanel();
        btnThemSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jTextField25 = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblsp = new javax.swing.JTable();
        jButton50 = new javax.swing.JButton();
        panelCaidat = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        panelNH = new javax.swing.JPanel();
        Panelchitietnhaphang = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblNhRight = new javax.swing.JTable();
        txIdNvNh = new javax.swing.JTextField();
        txIdNccNh = new javax.swing.JTextField();
        txTongNh = new javax.swing.JTextField();
        txIdPNH = new javax.swing.JTextField();
        txIdSpNh = new javax.swing.JTextField();
        txDongiaNh = new javax.swing.JTextField();
        txSoluongNh = new javax.swing.JTextField();
        txTenspNh = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        btnBhThem1 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        panelPNH = new javax.swing.JPanel();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jComboBox11 = new javax.swing.JComboBox<>();
        txtFindpnh = new javax.swing.JTextField();
        txtngaybatdaupnh = new com.toedter.calendar.JDateChooser();
        txtngayktpn = new com.toedter.calendar.JDateChooser();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tbluppnh = new javax.swing.JTable();
        jButton53 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbldownctpn = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        PanelHeader = new javax.swing.JPanel();
        txtHeader = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        addNhanvien.setUndecorated(true);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        btnSave1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Save.png"))); // NOI18N
        btnSave1.setText("Lưu");
        btnSave1.setMinimumSize(new java.awt.Dimension(60, 30));
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });

        jPanel23.setBackground(new java.awt.Color(37, 187, 219));

        lbAddNV5.setBackground(null);
        lbAddNV5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbAddNV5.setForeground(new java.awt.Color(255, 255, 255));
        lbAddNV5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAddNV5.setText("Thêm Nhân Viên");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV5, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV5, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        btnExitThemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Delete.png"))); // NOI18N
        btnExitThemNV.setText("Hủy");
        btnExitThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitThemNVActionPerformed(evt);
            }
        });

        btnDel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnDel1.setText("Xóa hết");
        btnDel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel1ActionPerformed(evt);
            }
        });

        txtidnvThemnv.setEditable(false);
        txtidnvThemnv.setBackground(new java.awt.Color(255, 255, 255));
        txtidnvThemnv.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Nhân viên"));
        txtidnvThemnv.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtidnvThemnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidnvThemnvActionPerformed(evt);
            }
        });

        txttenThemnv.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên Nhân viên"));

        txthoThemnv.setBorder(javax.swing.BorderFactory.createTitledBorder("Họ Nhân viên"));

        txtdiachiThemnv.setBorder(javax.swing.BorderFactory.createTitledBorder("Địa chỉ"));

        txtsdtThemnv.setBorder(javax.swing.BorderFactory.createTitledBorder("Số điện thoại"));

        txttuoiThemnv.setBorder(javax.swing.BorderFactory.createTitledBorder("Tuổi"));

        txtluongThemnv.setBorder(javax.swing.BorderFactory.createTitledBorder("Lương"));
        txtluongThemnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtluongThemnvActionPerformed(evt);
            }
        });

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder("Giới tính"));

        rdNamThemnv.setBackground(new java.awt.Color(255, 255, 255));
        themkh.add(rdNamThemnv);
        rdNamThemnv.setText("Nam");
        rdNamThemnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNamThemnvActionPerformed(evt);
            }
        });

        rdNuThemnv.setBackground(new java.awt.Color(255, 255, 255));
        themkh.add(rdNuThemnv);
        rdNuThemnv.setText("Nữ");
        rdNuThemnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNuThemnvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(rdNamThemnv)
                .addGap(18, 18, 18)
                .addComponent(rdNuThemnv)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rdNamThemnv)
                .addComponent(rdNuThemnv))
        );

        cbboxchucvuThemnv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên", "Quản lý" }));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtidnvThemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txthoThemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtluongThemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbboxchucvuThemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txttenThemnv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txttuoiThemnv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtsdtThemnv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(btnDel1)
                                .addGap(43, 43, 43)
                                .addComponent(btnExitThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtdiachiThemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txPositionError2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))))
            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtidnvThemnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttuoiThemnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txthoThemnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttenThemnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtluongThemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsdtThemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbboxchucvuThemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(txtdiachiThemnv, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txPositionError2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDel1)
                            .addComponent(btnExitThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout addNhanvienLayout = new javax.swing.GroupLayout(addNhanvien.getContentPane());
        addNhanvien.getContentPane().setLayout(addNhanvienLayout);
        addNhanvienLayout.setHorizontalGroup(
            addNhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        addNhanvienLayout.setVerticalGroup(
            addNhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        editNhanvien.setUndecorated(true);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Save.png"))); // NOI18N
        btnSave.setText("Lưu");
        btnSave.setMinimumSize(new java.awt.Dimension(60, 30));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(37, 187, 219));

        lbAddNV1.setBackground(null);
        lbAddNV1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbAddNV1.setForeground(new java.awt.Color(255, 255, 255));
        lbAddNV1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAddNV1.setText("Sửa Nhân Viên");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV1, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        btnExitSuaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Delete.png"))); // NOI18N
        btnExitSuaNV.setText("Hủy");
        btnExitSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitSuaNVActionPerformed(evt);
            }
        });

        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnDel.setText("Xóa hết");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        txidSuanv.setEditable(false);
        txidSuanv.setBackground(new java.awt.Color(255, 255, 255));
        txidSuanv.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Nhân viên"));
        txidSuanv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txidSuanvActionPerformed(evt);
            }
        });

        txtenSuanv.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên Nhân viên"));

        txhoSuanv.setBorder(javax.swing.BorderFactory.createTitledBorder("Họ Nhân viên"));

        txdiachiSuanv.setBorder(javax.swing.BorderFactory.createTitledBorder("Địa chỉ"));

        txsdtSuanv.setBorder(javax.swing.BorderFactory.createTitledBorder("Số điện thoại"));

        txtuoiSuanv.setBorder(javax.swing.BorderFactory.createTitledBorder("Tuổi"));

        txluongSuanv.setBorder(javax.swing.BorderFactory.createTitledBorder("Lương"));
        txluongSuanv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txluongSuanvActionPerformed(evt);
            }
        });

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("Giới tính"));

        rdNamSuanv.setBackground(new java.awt.Color(255, 255, 255));
        themkh.add(rdNamSuanv);
        rdNamSuanv.setText("Nam");
        rdNamSuanv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNamSuanvActionPerformed(evt);
            }
        });

        rdNuSuanv.setBackground(new java.awt.Color(255, 255, 255));
        themkh.add(rdNuSuanv);
        rdNuSuanv.setText("Nữ");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdNamSuanv)
                .addGap(18, 18, 18)
                .addComponent(rdNuSuanv)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdNamSuanv)
                    .addComponent(rdNuSuanv))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cbbchucvuSuanv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Nhân viên" }));
        cbbchucvuSuanv.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức vụ"));

        cbTrangthainv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hiện hành", "đã nghỉ" }));
        cbTrangthainv.setBorder(javax.swing.BorderFactory.createTitledBorder("Trạng thái"));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txidSuanv, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txhoSuanv, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txluongSuanv, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtenSuanv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtuoiSuanv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txsdtSuanv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbbchucvuSuanv, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txFnameError1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txdiachiSuanv, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(btnDel)
                                .addGap(43, 43, 43)
                                .addComponent(btnExitSuaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(cbTrangthainv, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txidSuanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtuoiSuanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txhoSuanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtenSuanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txFnameError1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txluongSuanv, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txsdtSuanv, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbbchucvuSuanv, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(cbTrangthainv, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txdiachiSuanv, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDel)
                    .addComponent(btnExitSuaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout editNhanvienLayout = new javax.swing.GroupLayout(editNhanvien.getContentPane());
        editNhanvien.getContentPane().setLayout(editNhanvienLayout);
        editNhanvienLayout.setHorizontalGroup(
            editNhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        editNhanvienLayout.setVerticalGroup(
            editNhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        addKhachhang.setUndecorated(true);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        btnSaveKh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Save.png"))); // NOI18N
        btnSaveKh.setText("Lưu");
        btnSaveKh.setMinimumSize(new java.awt.Dimension(60, 30));
        btnSaveKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveKhActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(37, 187, 219));

        lbAddNV2.setBackground(null);
        lbAddNV2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbAddNV2.setForeground(new java.awt.Color(255, 255, 255));
        lbAddNV2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAddNV2.setText("Thêm Khách hàng");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(lbAddNV2, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV2, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        btnExitThemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Delete.png"))); // NOI18N
        btnExitThemKH.setText("Hủy");
        btnExitThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitThemKHActionPerformed(evt);
            }
        });

        btnResetAddKh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnResetAddKh.setText("Xóa hết");
        btnResetAddKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetAddKhActionPerformed(evt);
            }
        });

        txtIdkh.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Khách hàng"));
        txtIdkh.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtIdkh.setEnabled(false);
        txtIdkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdkhActionPerformed(evt);
            }
        });

        txtTenkh.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên Khách hàng"));

        txtHokh.setBorder(javax.swing.BorderFactory.createTitledBorder("Họ Khách hàng"));

        txtDiachikh.setBorder(javax.swing.BorderFactory.createTitledBorder("Địa chỉ"));

        txtSdtkh.setBorder(javax.swing.BorderFactory.createTitledBorder("Số điện thoại"));

        txtEmailkh.setBorder(javax.swing.BorderFactory.createTitledBorder("Email"));

        gioitinhKH.setBackground(new java.awt.Color(255, 255, 255));
        gioitinhKH.setBorder(javax.swing.BorderFactory.createTitledBorder("Giới tính"));

        rdNamkh.setBackground(new java.awt.Color(255, 255, 255));
        themkh.add(rdNamkh);
        rdNamkh.setText("Nam");
        rdNamkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNamkhActionPerformed(evt);
            }
        });

        rdNukh.setBackground(new java.awt.Color(255, 255, 255));
        themkh.add(rdNukh);
        rdNukh.setText("Nữ");

        javax.swing.GroupLayout gioitinhKHLayout = new javax.swing.GroupLayout(gioitinhKH);
        gioitinhKH.setLayout(gioitinhKHLayout);
        gioitinhKHLayout.setHorizontalGroup(
            gioitinhKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gioitinhKHLayout.createSequentialGroup()
                .addComponent(rdNamkh)
                .addGap(0, 0, 0)
                .addComponent(rdNukh)
                .addGap(0, 0, 0))
        );
        gioitinhKHLayout.setVerticalGroup(
            gioitinhKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gioitinhKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rdNamkh)
                .addComponent(rdNukh))
        );

        txtTuoiKh.setBorder(javax.swing.BorderFactory.createTitledBorder("Tuổi"));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSdtkh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtIdkh, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHokh, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmailkh, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenkh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txFnameError2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(gioitinhKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTuoiKh, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnSaveKh, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnResetAddKh)
                        .addGap(43, 43, 43)
                        .addComponent(btnExitThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txPositionError1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(txtDiachikh, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116))))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHokh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txFnameError2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTuoiKh)
                    .addComponent(txtSdtkh)
                    .addComponent(gioitinhKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtDiachikh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txPositionError1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSaveKh, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnResetAddKh)
                            .addComponent(btnExitThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout addKhachhangLayout = new javax.swing.GroupLayout(addKhachhang.getContentPane());
        addKhachhang.getContentPane().setLayout(addKhachhangLayout);
        addKhachhangLayout.setHorizontalGroup(
            addKhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        addKhachhangLayout.setVerticalGroup(
            addKhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        editKhachhang.setUndecorated(true);

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        btnSaveEditKh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Save.png"))); // NOI18N
        btnSaveEditKh.setText("Lưu");
        btnSaveEditKh.setMinimumSize(new java.awt.Dimension(60, 30));
        btnSaveEditKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveEditKhActionPerformed(evt);
            }
        });

        jPanel32.setBackground(new java.awt.Color(37, 187, 219));

        lbAddNV6.setBackground(null);
        lbAddNV6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbAddNV6.setForeground(new java.awt.Color(255, 255, 255));
        lbAddNV6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAddNV6.setText("Sửa Khách hàng");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(lbAddNV6, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV6, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        btnExitSuaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Delete.png"))); // NOI18N
        btnExitSuaKH.setText("Hủy");
        btnExitSuaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitSuaKHActionPerformed(evt);
            }
        });

        btnDel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnDel3.setText("Xóa hết");
        btnDel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel3ActionPerformed(evt);
            }
        });

        txtIdkhEdit.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Khách hàng"));
        txtIdkhEdit.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtIdkhEdit.setEnabled(false);

        txtTenKhEdit.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên Khách hàng"));

        txtHoKhEdit.setBorder(javax.swing.BorderFactory.createTitledBorder("Họ Khách hàng"));

        txtDiachiKhEdit.setBorder(javax.swing.BorderFactory.createTitledBorder("Địa chỉ"));

        txtSdtKhEdit.setBorder(javax.swing.BorderFactory.createTitledBorder("Số điện thoại"));

        txtEmailKhEdit.setBorder(javax.swing.BorderFactory.createTitledBorder("Email"));

        jPanel51.setBackground(new java.awt.Color(255, 255, 255));
        jPanel51.setBorder(javax.swing.BorderFactory.createTitledBorder("Giới tính"));

        rdNamEdit.setBackground(new java.awt.Color(255, 255, 255));
        themkh.add(rdNamEdit);
        rdNamEdit.setText("Nam");
        rdNamEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNamEditActionPerformed(evt);
            }
        });

        rdNuEdit.setBackground(new java.awt.Color(255, 255, 255));
        themkh.add(rdNuEdit);
        rdNuEdit.setText("Nữ");

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdNamEdit)
                .addGap(0, 0, 0)
                .addComponent(rdNuEdit))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rdNamEdit)
                .addComponent(rdNuEdit))
        );

        txtTuoiKhEdit.setBorder(javax.swing.BorderFactory.createTitledBorder("Tuổi"));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnSaveEditKh, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(btnDel3)
                                .addGap(43, 43, 43)
                                .addComponent(btnExitSuaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDiachiKhEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txPositionError3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txAgeError3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txLnameError3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txGenderError3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txAddressError3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txSDTError3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(102, 102, 102))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtIdkhEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHoKhEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSdtKhEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenKhEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmailKhEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txFnameError6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTuoiKhEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdkhEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailKhEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoKhEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenKhEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txFnameError6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txLnameError3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addComponent(txAddressError3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txGenderError3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSdtKhEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtTuoiKhEdit))
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                        .addComponent(txtDiachiKhEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(txSDTError3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txAgeError3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)))
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txPositionError3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSaveEditKh, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDel3)
                            .addComponent(btnExitSuaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout editKhachhangLayout = new javax.swing.GroupLayout(editKhachhang.getContentPane());
        editKhachhang.getContentPane().setLayout(editKhachhangLayout);
        editKhachhangLayout.setHorizontalGroup(
            editKhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        editKhachhangLayout.setVerticalGroup(
            editKhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        addSanpham.setUndecorated(true);

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        btnSave4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Save.png"))); // NOI18N
        btnSave4.setText("Lưu");
        btnSave4.setMinimumSize(new java.awt.Dimension(60, 30));
        btnSave4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave4ActionPerformed(evt);
            }
        });

        jPanel35.setBackground(new java.awt.Color(37, 187, 219));

        lbAddNV7.setBackground(null);
        lbAddNV7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbAddNV7.setForeground(new java.awt.Color(255, 255, 255));
        lbAddNV7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAddNV7.setText("Thêm Sản Phẩm");

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV7, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV7, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        btnExitThemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Delete.png"))); // NOI18N
        btnExitThemSP.setText("Hủy");
        btnExitThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitThemSPActionPerformed(evt);
            }
        });

        btnDel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnDel4.setText("Xóa hết");
        btnDel4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel4ActionPerformed(evt);
            }
        });

        txIdAddSP.setEditable(false);
        txIdAddSP.setBackground(new java.awt.Color(255, 255, 255));
        txIdAddSP.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Sản phẩm"));

        txGiaAddSp.setBorder(javax.swing.BorderFactory.createTitledBorder("Giá Sản phẩm"));
        txGiaAddSp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txGiaAddSpKeyPressed(evt);
            }
        });

        txTenAddSp.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên Sản phẩm"));

        txLspAddSp.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Loại sản phẩm"));
        txLspAddSp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txLspAddSpKeyPressed(evt);
            }
        });

        txSoluongAddSp.setBorder(javax.swing.BorderFactory.createTitledBorder("Số lượng"));
        txSoluongAddSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txSoluongAddSpActionPerformed(evt);
            }
        });
        txSoluongAddSp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txSoluongAddSpKeyPressed(evt);
            }
        });

        cbDvtAddSp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gói", "Hộp", "Chai", "Gram", "ML", "Lốc", "Bao", "Chiếc" }));
        cbDvtAddSp.setBorder(javax.swing.BorderFactory.createTitledBorder("Đơn vị tính"));

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        cbTrangthaiAddSp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang bán", "Dừng bán" }));
        cbTrangthaiAddSp.setBorder(javax.swing.BorderFactory.createTitledBorder("Trạng thái"));

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txGiaAddSp, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(txTenAddSp)
                            .addComponent(txIdAddSP)
                            .addComponent(txLspAddSp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSave4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbTrangthaiAddSp, 0, 88, Short.MAX_VALUE))
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(btnDel4)
                                .addGap(30, 30, 30)
                                .addComponent(btnExitThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cbDvtAddSp, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txSoluongAddSp, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txIdAddSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(txTenAddSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(txGiaAddSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txLspAddSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(35, 35, 35)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTrangthaiAddSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDvtAddSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txSoluongAddSp, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDel4)
                    .addComponent(btnExitThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout addSanphamLayout = new javax.swing.GroupLayout(addSanpham.getContentPane());
        addSanpham.getContentPane().setLayout(addSanphamLayout);
        addSanphamLayout.setHorizontalGroup(
            addSanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addSanphamLayout.setVerticalGroup(
            addSanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        editSanpham.setUndecorated(true);

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        jPanel37.setBackground(new java.awt.Color(37, 187, 219));

        lbAddNV8.setBackground(null);
        lbAddNV8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbAddNV8.setForeground(new java.awt.Color(255, 255, 255));
        lbAddNV8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAddNV8.setText("Sửa Sản Phẩm");

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addComponent(lbAddNV8, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV8, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        btnSave6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Save.png"))); // NOI18N
        btnSave6.setText("Lưu");
        btnSave6.setMinimumSize(new java.awt.Dimension(60, 30));
        btnSave6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave6ActionPerformed(evt);
            }
        });

        btnDel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnDel6.setText("Xóa hết");
        btnDel6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel6ActionPerformed(evt);
            }
        });

        btnExitThemSP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Delete.png"))); // NOI18N
        btnExitThemSP1.setText("Hủy");
        btnExitThemSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitThemSP1ActionPerformed(evt);
            }
        });

        cbTrangthaiAddSp1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang bán", "Dừng bán" }));
        cbTrangthaiAddSp1.setBorder(javax.swing.BorderFactory.createTitledBorder("Trạng thái"));

        cbDvtAddSp1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gói", "Hộp", "Chai", "Gram", "ML", "Lốc", "Bao", "Chiếc" }));
        cbDvtAddSp1.setBorder(javax.swing.BorderFactory.createTitledBorder("Đơn vị tính"));

        txSoluongAddSp1.setBorder(javax.swing.BorderFactory.createTitledBorder("Số lượng"));
        txSoluongAddSp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txSoluongAddSp1ActionPerformed(evt);
            }
        });

        jButton6.setText("jButton4");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        txLspAddSp1.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Loại sản phẩm"));

        txGiaAddSp1.setBorder(javax.swing.BorderFactory.createTitledBorder("Giá Sản phẩm"));

        txTenAddSp1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên Sản phẩm"));

        txIdAddSP1.setEditable(false);
        txIdAddSP1.setBackground(new java.awt.Color(255, 255, 255));
        txIdAddSP1.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Sản phẩm"));

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txGiaAddSp1)
                            .addComponent(txTenAddSp1)
                            .addComponent(txIdAddSP1)
                            .addComponent(txLspAddSp1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addComponent(cbTrangthaiAddSp1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbDvtAddSp1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(txSoluongAddSp1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addComponent(btnSave6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(btnDel6)
                                .addGap(39, 39, 39)
                                .addComponent(btnExitThemSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(txIdAddSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(txTenAddSp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(txGiaAddSp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txLspAddSp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addGap(52, 52, 52)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDvtAddSp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTrangthaiAddSp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txSoluongAddSp1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDel6)
                    .addComponent(btnExitThemSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout editSanphamLayout = new javax.swing.GroupLayout(editSanpham.getContentPane());
        editSanpham.getContentPane().setLayout(editSanphamLayout);
        editSanphamLayout.setHorizontalGroup(
            editSanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        editSanphamLayout.setVerticalGroup(
            editSanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editSanphamLayout.createSequentialGroup()
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        addLoaisanpham.setUndecorated(true);

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));
        jPanel38.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        btnThemlsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Save.png"))); // NOI18N
        btnThemlsp.setText("Lưu");
        btnThemlsp.setMinimumSize(new java.awt.Dimension(60, 30));
        btnThemlsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemlspActionPerformed(evt);
            }
        });

        jPanel39.setBackground(new java.awt.Color(37, 187, 219));

        lbAddNV9.setBackground(null);
        lbAddNV9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbAddNV9.setForeground(new java.awt.Color(255, 255, 255));
        lbAddNV9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAddNV9.setText("Thêm Loại Sản Phẩm");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addComponent(lbAddNV9, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV9, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        btnresetThemlsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnresetThemlsp.setText("Xóa hết");
        btnresetThemlsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetThemlspActionPerformed(evt);
            }
        });

        btnhuyThemsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_cancel_30px_1.png"))); // NOI18N
        btnhuyThemsp.setText("Hủy");
        btnhuyThemsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhuyThemspActionPerformed(evt);
            }
        });

        txidThemlsp.setEditable(false);
        txidThemlsp.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Loại Sản phẩm"));

        txtenThemlsp.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên Loại Sản phẩm"));

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel38Layout.createSequentialGroup()
                        .addGap(462, 462, 462)
                        .addComponent(txLnameError6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txFnameError9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel38Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txAddressError6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(119, 119, 119))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txPositionError6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txAgeError6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnThemlsp, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btnresetThemlsp)
                        .addGap(42, 42, 42)
                        .addComponent(btnhuyThemsp, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txSDTError6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(219, 219, 219))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txidThemlsp, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addComponent(txtenThemlsp))
                .addGap(294, 294, 294))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txFnameError9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txLnameError6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(txidThemlsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(txAddressError6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtenThemlsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(txSDTError6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnhuyThemsp)
                        .addComponent(btnThemlsp, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnresetThemlsp, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txAgeError6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(txPositionError6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout addLoaisanphamLayout = new javax.swing.GroupLayout(addLoaisanpham.getContentPane());
        addLoaisanpham.getContentPane().setLayout(addLoaisanphamLayout);
        addLoaisanphamLayout.setHorizontalGroup(
            addLoaisanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addLoaisanphamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        addLoaisanphamLayout.setVerticalGroup(
            addLoaisanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addLoaisanphamLayout.createSequentialGroup()
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        editLoaisanpham.setUndecorated(true);

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));
        jPanel40.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        btnSave7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Save.png"))); // NOI18N
        btnSave7.setText("Lưu");
        btnSave7.setMinimumSize(new java.awt.Dimension(60, 30));
        btnSave7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave7ActionPerformed(evt);
            }
        });

        jPanel41.setBackground(new java.awt.Color(37, 187, 219));

        lbAddNV10.setBackground(null);
        lbAddNV10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbAddNV10.setForeground(new java.awt.Color(255, 255, 255));
        lbAddNV10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAddNV10.setText("Sửa Loại Sản Phẩm");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV10, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV10, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        btnExit8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnExit8.setText("Xóa hết");
        btnExit8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit8ActionPerformed(evt);
            }
        });

        btnExitSuaLSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_cancel_30px_1.png"))); // NOI18N
        btnExitSuaLSP.setText("Hủy");
        btnExitSuaLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitSuaLSPActionPerformed(evt);
            }
        });

        txidlspSualsp.setEditable(false);
        txidlspSualsp.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Loại Sản phẩm"));

        txtenSualsp.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên Loại Sản phẩm"));

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnExit8)
                .addGap(36, 36, 36)
                .addComponent(btnExitSuaLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(207, 207, 207))
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txidlspSualsp, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtenSualsp, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(txidlspSualsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(txtenSualsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExitSuaLSP)
                    .addComponent(btnExit8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout editLoaisanphamLayout = new javax.swing.GroupLayout(editLoaisanpham.getContentPane());
        editLoaisanpham.getContentPane().setLayout(editLoaisanphamLayout);
        editLoaisanphamLayout.setHorizontalGroup(
            editLoaisanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editLoaisanphamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );
        editLoaisanphamLayout.setVerticalGroup(
            editLoaisanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editLoaisanphamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        addKhuyenmai.setUndecorated(true);

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        btnSave8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Save.png"))); // NOI18N
        btnSave8.setText("Lưu");
        btnSave8.setMinimumSize(new java.awt.Dimension(60, 30));
        btnSave8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave8ActionPerformed(evt);
            }
        });

        jPanel42.setBackground(new java.awt.Color(37, 187, 219));

        lbAddNV11.setBackground(null);
        lbAddNV11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbAddNV11.setForeground(new java.awt.Color(255, 255, 255));
        lbAddNV11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAddNV11.setText("Thêm Khuyến mãi");

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV11, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        btnExitThemKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Delete.png"))); // NOI18N
        btnExitThemKM.setText("Hủy");
        btnExitThemKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitThemKMActionPerformed(evt);
            }
        });

        btnDel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnDel8.setText("Xóa hết");
        btnDel8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel8ActionPerformed(evt);
            }
        });

        txIdkmThemkm.setEditable(false);
        txIdkmThemkm.setBackground(new java.awt.Color(255, 255, 255));
        txIdkmThemkm.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Khuyến mãi"));
        txIdkmThemkm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txIdkmThemkmActionPerformed(evt);
            }
        });

        txphantramThemkm.setBorder(javax.swing.BorderFactory.createTitledBorder("Phần trăm khuyến mãi"));
        txphantramThemkm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txphantramThemkmKeyPressed(evt);
            }
        });

        txDkThemkm.setBorder(javax.swing.BorderFactory.createTitledBorder("Điều kiện khuyến mãi"));
        txDkThemkm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txDkThemkmKeyPressed(evt);
            }
        });

        txTenkmThemkm.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên khuyến mãi"));

        txNgaykt.setBackground(new java.awt.Color(255, 255, 255));
        txNgaykt.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngày kết thúc"));

        txNgaybd.setBackground(new java.awt.Color(255, 255, 255));
        txNgaybd.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngày bắt đầu"));
        txNgaybd.setDateFormatString("MMM, d, y");

        txaNoidungThemkm.setColumns(20);
        txaNoidungThemkm.setRows(5);
        txaNoidungThemkm.setBorder(javax.swing.BorderFactory.createTitledBorder("Nội dung khuyến mãi"));
        jScrollPane18.setViewportView(txaNoidungThemkm);

        jLabel7.setText("%");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txIdkmThemkm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(txDkThemkm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(txNgaybd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addComponent(txphantramThemkm, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txTenkmThemkm, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(txNgaykt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane18)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel33Layout.createSequentialGroup()
                                .addComponent(btnSave8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(btnDel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                .addComponent(btnExitThemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txIdkmThemkm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txTenkmThemkm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txDkThemkm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txphantramThemkm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txNgaybd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNgaykt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDel8)
                    .addComponent(btnExitThemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout addKhuyenmaiLayout = new javax.swing.GroupLayout(addKhuyenmai.getContentPane());
        addKhuyenmai.getContentPane().setLayout(addKhuyenmaiLayout);
        addKhuyenmaiLayout.setHorizontalGroup(
            addKhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        addKhuyenmaiLayout.setVerticalGroup(
            addKhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        editKhuyenmai.setUndecorated(true);

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        btnSave9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Save.png"))); // NOI18N
        btnSave9.setText("Lưu");
        btnSave9.setMinimumSize(new java.awt.Dimension(60, 30));
        btnSave9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave9ActionPerformed(evt);
            }
        });

        jPanel44.setBackground(new java.awt.Color(37, 187, 219));

        lbAddNV12.setBackground(null);
        lbAddNV12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbAddNV12.setForeground(new java.awt.Color(255, 255, 255));
        lbAddNV12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAddNV12.setText("Sửa Khuyến mãi");

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addComponent(lbAddNV12, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV12, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        btnExitSuaKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Delete.png"))); // NOI18N
        btnExitSuaKM.setText("Hủy");
        btnExitSuaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitSuaKMActionPerformed(evt);
            }
        });

        btnDel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnDel9.setText("Xóa hết");
        btnDel9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel9ActionPerformed(evt);
            }
        });

        txIdSuakm.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Khuyến mãi"));

        txPhantramkmSuakm.setBorder(javax.swing.BorderFactory.createTitledBorder("Phần trăm khuyến mãi"));
        txPhantramkmSuakm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txPhantramkmSuakmKeyPressed(evt);
            }
        });

        txDkkmSuakm.setBorder(javax.swing.BorderFactory.createTitledBorder("Điều kiện khuyến mãi"));
        txDkkmSuakm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txDkkmSuakmKeyPressed(evt);
            }
        });

        txTenkmSuakm.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên khuyến mãi"));

        jDateChooser1.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngày bắt đầu"));

        jDateChooser2.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngày kết thúc"));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setBorder(javax.swing.BorderFactory.createTitledBorder("Nội dung khuyến mãi"));
        jScrollPane25.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(270, 270, 270))
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txLnameError9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txGenderError5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txAddressError9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel43Layout.createSequentialGroup()
                                    .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txIdSuakm, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txDkkmSuakm, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(76, 76, 76)
                                    .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txPhantramkmSuakm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txTenkmSuakm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel43Layout.createSequentialGroup()
                                    .addGap(236, 236, 236)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel43Layout.createSequentialGroup()
                                    .addComponent(btnSave9, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(50, 50, 50)
                                    .addComponent(btnDel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnExitSuaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIdSuakm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txTenkmSuakm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txDkkmSuakm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txPhantramkmSuakm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(txLnameError9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(txAddressError9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txGenderError5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDel9)
                    .addComponent(btnSave9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExitSuaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout editKhuyenmaiLayout = new javax.swing.GroupLayout(editKhuyenmai.getContentPane());
        editKhuyenmai.getContentPane().setLayout(editKhuyenmaiLayout);
        editKhuyenmaiLayout.setHorizontalGroup(
            editKhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        editKhuyenmaiLayout.setVerticalGroup(
            editKhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        addNCC.setUndecorated(true);

        jPanel45.setBackground(new java.awt.Color(255, 255, 255));
        jPanel45.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        btnSave10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Save.png"))); // NOI18N
        btnSave10.setText("Lưu");
        btnSave10.setMinimumSize(new java.awt.Dimension(60, 30));
        btnSave10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave10ActionPerformed(evt);
            }
        });

        jPanel46.setBackground(new java.awt.Color(37, 187, 219));

        lbAddNV13.setBackground(null);
        lbAddNV13.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbAddNV13.setForeground(new java.awt.Color(255, 255, 255));
        lbAddNV13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAddNV13.setText("Thêm Nhà cung cấp");

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addComponent(lbAddNV13, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV13, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        btnExitThemNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Delete.png"))); // NOI18N
        btnExitThemNCC.setText("Hủy");
        btnExitThemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitThemNCCActionPerformed(evt);
            }
        });

        btnDel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnDel10.setText("Xóa hết");
        btnDel10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel10ActionPerformed(evt);
            }
        });

        txidnccThemncc.setEditable(false);
        txidnccThemncc.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Nhà cung cấp"));

        txdiachinccThemncc.setBorder(javax.swing.BorderFactory.createTitledBorder("Địa chỉ"));
        txdiachinccThemncc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txdiachinccThemnccActionPerformed(evt);
            }
        });

        txtsdtnccThemncc.setBorder(javax.swing.BorderFactory.createTitledBorder("Số điện thoại"));

        txtennccThemncc.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên Nhà cung cấp"));

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtsdtnccThemncc, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(txidnccThemncc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txdiachinccThemncc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addComponent(btnSave10, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel45Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtennccThemncc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel45Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(btnDel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExitThemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txidnccThemncc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtennccThemncc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsdtnccThemncc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txdiachinccThemncc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDel10)
                    .addComponent(btnExitThemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout addNCCLayout = new javax.swing.GroupLayout(addNCC.getContentPane());
        addNCC.getContentPane().setLayout(addNCCLayout);
        addNCCLayout.setHorizontalGroup(
            addNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        addNCCLayout.setVerticalGroup(
            addNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        editNCC.setUndecorated(true);

        jPanel48.setBackground(new java.awt.Color(255, 255, 255));
        jPanel48.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        btnSave11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Save.png"))); // NOI18N
        btnSave11.setText("Lưu");
        btnSave11.setMinimumSize(new java.awt.Dimension(60, 30));
        btnSave11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave11ActionPerformed(evt);
            }
        });

        jPanel49.setBackground(new java.awt.Color(37, 187, 219));

        lbAddNV14.setBackground(null);
        lbAddNV14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbAddNV14.setForeground(new java.awt.Color(255, 255, 255));
        lbAddNV14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAddNV14.setText("Sửa Nhà cung cấp");

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV14, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        btnExitSuaNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Delete.png"))); // NOI18N
        btnExitSuaNCC.setText("Hủy");
        btnExitSuaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitSuaNCCActionPerformed(evt);
            }
        });

        btnDel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnDel11.setText("Xóa hết");
        btnDel11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel11ActionPerformed(evt);
            }
        });

        txidnccSuancc.setEditable(false);
        txidnccSuancc.setBackground(new java.awt.Color(255, 255, 255));
        txidnccSuancc.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Nhà cung cấp"));

        txdiachiSuancc.setBorder(javax.swing.BorderFactory.createTitledBorder("Địa chỉ"));

        txsdtSuancc.setBorder(javax.swing.BorderFactory.createTitledBorder("Số điện thoại"));

        txtenSuancc.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên Nhà cung cấp"));

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                        .addComponent(btnSave11, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel48Layout.createSequentialGroup()
                                .addComponent(txLnameError11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtenSuancc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txdiachiSuancc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(btnDel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                .addComponent(btnExitSuaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txidnccSuancc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txsdtSuancc, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txLnameError11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txidnccSuancc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtenSuancc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txsdtSuancc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txdiachiSuancc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(66, 66, 66)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDel11)
                    .addComponent(btnExitSuaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout editNCCLayout = new javax.swing.GroupLayout(editNCC.getContentPane());
        editNCC.getContentPane().setLayout(editNCCLayout);
        editNCCLayout.setHorizontalGroup(
            editNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        editNCCLayout.setVerticalGroup(
            editNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        addTaikhoan.setUndecorated(true);

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));
        jPanel47.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        btnSave12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Save.png"))); // NOI18N
        btnSave12.setText("Lưu");
        btnSave12.setMinimumSize(new java.awt.Dimension(60, 30));
        btnSave12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave12ActionPerformed(evt);
            }
        });

        jPanel50.setBackground(new java.awt.Color(37, 187, 219));

        lbAddNV15.setBackground(null);
        lbAddNV15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbAddNV15.setForeground(new java.awt.Color(255, 255, 255));
        lbAddNV15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAddNV15.setText("Thêm Tài khoản");

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV15, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV15, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        btnExitThemTaikhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Delete.png"))); // NOI18N
        btnExitThemTaikhoan.setText("Hủy");
        btnExitThemTaikhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitThemTaikhoanActionPerformed(evt);
            }
        });

        btnDel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnDel12.setText("Xóa hết");
        btnDel12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel12ActionPerformed(evt);
            }
        });

        txidThemtk.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Nhân viên"));

        txmkThemtk.setBorder(javax.swing.BorderFactory.createTitledBorder("Mật khẩu"));

        txtendnThemtk.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên Đăng nhập"));

        jButton59.setText("jButton59");
        jButton59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton59ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnSave12, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(btnDel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExitThemTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtendnThemtk, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txidThemtk, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txmkThemtk, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txidThemtk, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton59))
                .addGap(18, 18, 18)
                .addComponent(txtendnThemtk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txmkThemtk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDel12)
                    .addComponent(btnExitThemTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout addTaikhoanLayout = new javax.swing.GroupLayout(addTaikhoan.getContentPane());
        addTaikhoan.getContentPane().setLayout(addTaikhoanLayout);
        addTaikhoanLayout.setHorizontalGroup(
            addTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTaikhoanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        addTaikhoanLayout.setVerticalGroup(
            addTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        editTaikhoan.setUndecorated(true);

        jPanel52.setBackground(new java.awt.Color(255, 255, 255));
        jPanel52.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        btnSave13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Save.png"))); // NOI18N
        btnSave13.setText("Lưu");
        btnSave13.setMinimumSize(new java.awt.Dimension(60, 30));
        btnSave13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave13ActionPerformed(evt);
            }
        });

        jPanel53.setBackground(new java.awt.Color(37, 187, 219));

        lbAddNV16.setBackground(null);
        lbAddNV16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbAddNV16.setForeground(new java.awt.Color(255, 255, 255));
        lbAddNV16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAddNV16.setText("Sửa Tài khoản");

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addComponent(lbAddNV16, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV16, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        btnExitSuaTaikhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Delete.png"))); // NOI18N
        btnExitSuaTaikhoan.setText("Hủy");
        btnExitSuaTaikhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitSuaTaikhoanActionPerformed(evt);
            }
        });

        btnDel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnDel13.setText("Xóa hết");
        btnDel13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel13ActionPerformed(evt);
            }
        });

        txidSuatk.setEditable(false);
        txidSuatk.setBackground(new java.awt.Color(255, 255, 255));
        txidSuatk.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Nhân viên"));

        txmkSuatk.setBorder(javax.swing.BorderFactory.createTitledBorder("Mật khẩu"));

        txtendnSuatk.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên Đăng nhập"));

        txChucvuSuatk.setEditable(false);
        txChucvuSuatk.setBackground(new java.awt.Color(255, 255, 255));
        txChucvuSuatk.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức vụ"));

        txtenSuatk.setEditable(false);
        txtenSuatk.setBackground(new java.awt.Color(255, 255, 255));
        txtenSuatk.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên Nhân viên"));

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel52Layout.createSequentialGroup()
                                .addGap(325, 325, 325)
                                .addComponent(txFnameError16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel52Layout.createSequentialGroup()
                                .addComponent(btnSave13, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(btnDel13)
                                .addGap(71, 71, 71)
                                .addComponent(btnExitSuaTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtendnSuatk, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txmkSuatk, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(txidSuatk, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtenSuatk, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(txChucvuSuatk, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txFnameError16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txidSuatk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txChucvuSuatk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtenSuatk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addComponent(txtendnSuatk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(txmkSuatk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDel13)
                    .addComponent(btnExitSuaTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout editTaikhoanLayout = new javax.swing.GroupLayout(editTaikhoan.getContentPane());
        editTaikhoan.getContentPane().setLayout(editTaikhoanLayout);
        editTaikhoanLayout.setHorizontalGroup(
            editTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        editTaikhoanLayout.setVerticalGroup(
            editTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel10.setBackground(new java.awt.Color(37, 187, 219));

        lbAcceptDeny.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbAcceptDeny.setForeground(new java.awt.Color(255, 255, 255));
        lbAcceptDeny.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAcceptDeny.setText("Bạn có chắc muốn xóa?");

        jPanel15.setLayout(new java.awt.GridLayout(1, 0));

        pnAccept.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(102, 102, 102)));
        pnAccept.setPreferredSize(new java.awt.Dimension(136, 50));

        lbAccept.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbAccept.setForeground(new java.awt.Color(37, 187, 219));
        lbAccept.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAccept.setText("Đồng ý");
        lbAccept.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAcceptMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnAcceptLayout = new javax.swing.GroupLayout(pnAccept);
        pnAccept.setLayout(pnAcceptLayout);
        pnAcceptLayout.setHorizontalGroup(
            pnAcceptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAccept, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
        );
        pnAcceptLayout.setVerticalGroup(
            pnAcceptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAccept, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        lbAccept.getAccessibleContext().setAccessibleName("pnAccept");

        jPanel15.add(pnAccept);

        pnDeny.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(102, 102, 102)));
        pnDeny.setPreferredSize(new java.awt.Dimension(136, 50));

        lbDeny.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbDeny.setForeground(new java.awt.Color(37, 187, 219));
        lbDeny.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDeny.setText("Từ chối");

        javax.swing.GroupLayout pnDenyLayout = new javax.swing.GroupLayout(pnDeny);
        pnDeny.setLayout(pnDenyLayout);
        pnDenyLayout.setHorizontalGroup(
            pnDenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbDeny, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
        );
        pnDenyLayout.setVerticalGroup(
            pnDenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbDeny, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel15.add(pnDeny);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAcceptDeny, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbAcceptDeny)
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout AcceptDenyLayout = new javax.swing.GroupLayout(AcceptDeny.getContentPane());
        AcceptDeny.getContentPane().setLayout(AcceptDenyLayout);
        AcceptDenyLayout.setHorizontalGroup(
            AcceptDenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        AcceptDenyLayout.setVerticalGroup(
            AcceptDenyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txFindSelectnv.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        txFindSelectnv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txFindSelectnvKeyPressed(evt);
            }
        });

        tblTimnv.setModel(tblnv.getModel());
        tblTimnv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTimnvMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(tblTimnv);
        tblTimnv.setColumnModel(tblnv.getColumnModel());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                    .addComponent(txFindSelectnv))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(txFindSelectnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout selectNhanvienLayout = new javax.swing.GroupLayout(selectNhanvien.getContentPane());
        selectNhanvien.getContentPane().setLayout(selectNhanvienLayout);
        selectNhanvienLayout.setHorizontalGroup(
            selectNhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        selectNhanvienLayout.setVerticalGroup(
            selectNhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane17.setViewportView(jTextArea1);

        jLabel3.setText("jLabel3");

        tblSelectLSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSelectLSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSelectLSPMouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(tblSelectLSP);

        txFindSelectLSP.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        txFindSelectLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txFindSelectLSPActionPerformed(evt);
            }
        });
        txFindSelectLSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txFindSelectLSPKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane19)
                    .addComponent(txFindSelectLSP))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(txFindSelectLSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout selectLSPLayout = new javax.swing.GroupLayout(selectLSP.getContentPane());
        selectLSP.getContentPane().setLayout(selectLSPLayout);
        selectLSPLayout.setHorizontalGroup(
            selectLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        selectLSPLayout.setVerticalGroup(
            selectLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tblSelectLSPE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSelectLSPE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSelectLSPEMouseClicked(evt);
            }
        });
        jScrollPane20.setViewportView(tblSelectLSPE);

        txFindSelectLSPe.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        txFindSelectLSPe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txFindSelectLSPeActionPerformed(evt);
            }
        });
        txFindSelectLSPe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txFindSelectLSPeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane20)
                    .addComponent(txFindSelectLSPe))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(txFindSelectLSPe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout selectLSPELayout = new javax.swing.GroupLayout(selectLSPE.getContentPane());
        selectLSPE.getContentPane().setLayout(selectLSPELayout);
        selectLSPELayout.setHorizontalGroup(
            selectLSPELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectLSPELayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        selectLSPELayout.setVerticalGroup(
            selectLSPELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tblSelectSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSelectSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSelectSPMouseClicked(evt);
            }
        });
        jScrollPane21.setViewportView(tblSelectSP);

        txFindSelectSP.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        txFindSelectSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txFindSelectSPActionPerformed(evt);
            }
        });
        txFindSelectSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txFindSelectSPKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane21)
                    .addComponent(txFindSelectSP))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(txFindSelectSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout selectSPLayout = new javax.swing.GroupLayout(selectSP.getContentPane());
        selectSP.getContentPane().setLayout(selectSPLayout);
        selectSPLayout.setHorizontalGroup(
            selectSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        selectSPLayout.setVerticalGroup(
            selectSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tblSelectNCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSelectNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSelectNCCMouseClicked(evt);
            }
        });
        jScrollPane22.setViewportView(tblSelectNCC);

        txFindSelectNCC.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        txFindSelectNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txFindSelectNCCActionPerformed(evt);
            }
        });
        txFindSelectNCC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txFindSelectNCCKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane22)
                    .addComponent(txFindSelectNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(txFindSelectNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout selectNCCLayout = new javax.swing.GroupLayout(selectNCC.getContentPane());
        selectNCC.getContentPane().setLayout(selectNCCLayout);
        selectNCCLayout.setHorizontalGroup(
            selectNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        selectNCCLayout.setVerticalGroup(
            selectNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        chonformkm.setUndecorated(true);

        jPanel57.setBackground(new java.awt.Color(255, 255, 255));
        jPanel57.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        btnchonKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_database_restore_30px.png"))); // NOI18N
        btnchonKM.setText("Chọn");
        btnchonKM.setMinimumSize(new java.awt.Dimension(60, 30));
        btnchonKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchonKMActionPerformed(evt);
            }
        });

        jPanel58.setBackground(new java.awt.Color(37, 187, 219));

        lbAddNV17.setBackground(null);
        lbAddNV17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbAddNV17.setForeground(new java.awt.Color(255, 255, 255));
        lbAddNV17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAddNV17.setText("Chọn khuyến mãi");

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addComponent(lbAddNV17, javax.swing.GroupLayout.PREFERRED_SIZE, 1014, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV17, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        btnhuychonkm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_30px_1.png"))); // NOI18N
        btnhuychonkm.setText("Hủy");
        btnhuychonkm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhuychonkmActionPerformed(evt);
            }
        });

        tblChonKM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblChonKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblChonKM.setRowHeight(35);
        jScrollPane24.setViewportView(tblChonKM);

        jPanel59.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        cbchonkm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã khuyến mãi", "Tên khuyến mãi", "Chương trình khuyến mãi" }));
        cbchonkm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbchonkmActionPerformed(evt);
            }
        });

        txtTimkimkm.setBorder(javax.swing.BorderFactory.createTitledBorder("Tất cả"));
        txtTimkimkm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimkimkmKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(cbchonkm, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(txtTimkimkm, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimkimkm, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbchonkm, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel57Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                                .addComponent(txPositionError4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                                .addComponent(btnchonKM, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87)
                                .addComponent(btnhuychonkm, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(300, 300, 300))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                        .addComponent(jScrollPane24)
                        .addContainerGap())))
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel57Layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txPositionError4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnchonKM, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhuychonkm))
                .addContainerGap())
        );

        javax.swing.GroupLayout chonformkmLayout = new javax.swing.GroupLayout(chonformkm.getContentPane());
        chonformkm.getContentPane().setLayout(chonformkmLayout);
        chonformkmLayout.setHorizontalGroup(
            chonformkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        chonformkmLayout.setVerticalGroup(
            chonformkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        chonformKH.setUndecorated(true);

        jPanel60.setBackground(new java.awt.Color(255, 255, 255));
        jPanel60.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        btnchonKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_database_restore_30px.png"))); // NOI18N
        btnchonKH.setText("Chọn");
        btnchonKH.setMinimumSize(new java.awt.Dimension(60, 30));
        btnchonKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchonKHActionPerformed(evt);
            }
        });

        jPanel61.setBackground(new java.awt.Color(37, 187, 219));

        lbAddNV18.setBackground(null);
        lbAddNV18.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbAddNV18.setForeground(new java.awt.Color(255, 255, 255));
        lbAddNV18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAddNV18.setText("Chọn khách hàng");

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addComponent(lbAddNV18, javax.swing.GroupLayout.PREFERRED_SIZE, 1014, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAddNV18, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        btnhuychonKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_30px_1.png"))); // NOI18N
        btnhuychonKH.setText("Hủy");
        btnhuychonKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhuychonKHActionPerformed(evt);
            }
        });

        tblChonKH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblChonKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblChonKH.setRowHeight(35);
        jScrollPane26.setViewportView(tblChonKH);

        jPanel62.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        cbchonkm1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã khuyến mãi", "Tên khuyến mãi", "Chương trình khuyến mãi" }));
        cbchonkm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbchonkm1ActionPerformed(evt);
            }
        });

        txtTimkimkm1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tất cả"));
        txtTimkimkm1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimkimkm1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(cbchonkm1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(txtTimkimkm1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimkimkm1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbchonkm1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                                .addComponent(txPositionError5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                                .addComponent(btnchonKH, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87)
                                .addComponent(btnhuychonKH, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(300, 300, 300))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                        .addComponent(jScrollPane26)
                        .addContainerGap())))
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txPositionError5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnchonKH, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhuychonKH))
                .addContainerGap())
        );

        javax.swing.GroupLayout chonformKHLayout = new javax.swing.GroupLayout(chonformKH.getContentPane());
        chonformKH.getContentPane().setLayout(chonformKHLayout);
        chonformKHLayout.setHorizontalGroup(
            chonformKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        chonformKHLayout.setVerticalGroup(
            chonformKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        Hethong.setBackground(new java.awt.Color(37, 187, 219));

        PanelMenuht.setBackground(new java.awt.Color(37, 187, 219));

        panelMenu.setBackground(new java.awt.Color(37, 187, 219));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/avaAdmin.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        btnDangxuat.setBackground(new java.awt.Color(255, 0, 51));
        btnDangxuat.setText("Đăng xuất");
        btnDangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangxuatActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.setRequestFocusEnabled(false);

        jPanel3.setBackground(new java.awt.Color(37, 187, 219));
        jPanel3.setDoubleBuffered(false);
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setVerifyInputWhenFocusTarget(false);

        PanelBanhang.setBackground(null
        );
        PanelBanhang.setPreferredSize(new java.awt.Dimension(101, 33));

        labelBanhang.setBackground(null);
        labelBanhang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelBanhang.setForeground(new java.awt.Color(255, 255, 255));
        labelBanhang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelBanhang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_small_business_30px_3.png"))); // NOI18N
        labelBanhang.setText("Bán hàng");
        labelBanhang.setBorder(new EmptyBorder(0,25,0,0));
        labelBanhang.setPreferredSize(new java.awt.Dimension(92, 33));
        labelBanhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBanhangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelBanhangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelBanhangMouseExited(evt);
            }
        });

        qlbhLeft.setBackground(new java.awt.Color(37, 187, 219));
        qlbhLeft.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout qlbhLeftLayout = new javax.swing.GroupLayout(qlbhLeft);
        qlbhLeft.setLayout(qlbhLeftLayout);
        qlbhLeftLayout.setHorizontalGroup(
            qlbhLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        qlbhLeftLayout.setVerticalGroup(
            qlbhLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelBanhangLayout = new javax.swing.GroupLayout(PanelBanhang);
        PanelBanhang.setLayout(PanelBanhangLayout);
        PanelBanhangLayout.setHorizontalGroup(
            PanelBanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBanhangLayout.createSequentialGroup()
                .addComponent(qlbhLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelBanhang, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelBanhangLayout.setVerticalGroup(
            PanelBanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(qlbhLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
            .addComponent(labelBanhang, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        PanelNhaphang.setBackground(null
        );
        PanelNhaphang.setDoubleBuffered(false);
        PanelNhaphang.setEnabled(false);
        PanelNhaphang.setFocusable(false);
        PanelNhaphang.setPreferredSize(new java.awt.Dimension(101, 33));
        PanelNhaphang.setRequestFocusEnabled(false);
        PanelNhaphang.setVerifyInputWhenFocusTarget(false);

        labelnh.setBackground(null);
        labelnh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelnh.setForeground(new java.awt.Color(255, 255, 255));
        labelnh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_downloads_30px.png"))); // NOI18N
        labelnh.setText("Nhập hàng");
        labelnh.setBorder(new EmptyBorder(0,25,0,0));
        labelnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelnhMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelnhMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelnhMouseExited(evt);
            }
        });

        qlnhLeft.setBackground(new java.awt.Color(37, 187, 219));
        qlnhLeft.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout qlnhLeftLayout = new javax.swing.GroupLayout(qlnhLeft);
        qlnhLeft.setLayout(qlnhLeftLayout);
        qlnhLeftLayout.setHorizontalGroup(
            qlnhLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        qlnhLeftLayout.setVerticalGroup(
            qlnhLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelNhaphangLayout = new javax.swing.GroupLayout(PanelNhaphang);
        PanelNhaphang.setLayout(PanelNhaphangLayout);
        PanelNhaphangLayout.setHorizontalGroup(
            PanelNhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelNhaphangLayout.createSequentialGroup()
                .addComponent(qlnhLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelnh, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelNhaphangLayout.setVerticalGroup(
            PanelNhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(qlnhLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
            .addGroup(PanelNhaphangLayout.createSequentialGroup()
                .addComponent(labelnh)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        PanelSanpham.setBackground(null
        );
        PanelSanpham.setPreferredSize(new java.awt.Dimension(101, 33));

        Labelsp.setBackground(null);
        Labelsp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Labelsp.setForeground(new java.awt.Color(255, 255, 255));
        Labelsp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Labelsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Bar chart.png"))); // NOI18N
        Labelsp.setText("Sản phẩm");
        Labelsp.setBorder(new EmptyBorder(0,25,0,0));
        Labelsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabelspMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LabelspMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LabelspMouseExited(evt);
            }
        });

        qlspLeft.setBackground(new java.awt.Color(37, 187, 219));
        qlspLeft.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout qlspLeftLayout = new javax.swing.GroupLayout(qlspLeft);
        qlspLeft.setLayout(qlspLeftLayout);
        qlspLeftLayout.setHorizontalGroup(
            qlspLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        qlspLeftLayout.setVerticalGroup(
            qlspLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelSanphamLayout = new javax.swing.GroupLayout(PanelSanpham);
        PanelSanpham.setLayout(PanelSanphamLayout);
        PanelSanphamLayout.setHorizontalGroup(
            PanelSanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSanphamLayout.createSequentialGroup()
                .addComponent(qlspLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Labelsp, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelSanphamLayout.setVerticalGroup(
            PanelSanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(qlspLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
            .addComponent(Labelsp, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        PanelLoaisanpham.setBackground(null
        );
        PanelLoaisanpham.setPreferredSize(new java.awt.Dimension(101, 33));

        labelLoaisanpham.setBackground(null);
        labelLoaisanpham.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelLoaisanpham.setForeground(new java.awt.Color(255, 255, 255));
        labelLoaisanpham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelLoaisanpham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_dossier_folder_30px.png"))); // NOI18N
        labelLoaisanpham.setText("Loại sản phẩm");
        labelLoaisanpham.setBorder(new EmptyBorder(0,25,0,0));
        labelLoaisanpham.setMinimumSize(new java.awt.Dimension(122, 33));
        labelLoaisanpham.setPreferredSize(new java.awt.Dimension(101, 15));
        labelLoaisanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelLoaisanphamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelLoaisanphamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelLoaisanphamMouseExited(evt);
            }
        });

        qllspLeft.setBackground(new java.awt.Color(37, 187, 219));
        qllspLeft.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout qllspLeftLayout = new javax.swing.GroupLayout(qllspLeft);
        qllspLeft.setLayout(qllspLeftLayout);
        qllspLeftLayout.setHorizontalGroup(
            qllspLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        qllspLeftLayout.setVerticalGroup(
            qllspLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelLoaisanphamLayout = new javax.swing.GroupLayout(PanelLoaisanpham);
        PanelLoaisanpham.setLayout(PanelLoaisanphamLayout);
        PanelLoaisanphamLayout.setHorizontalGroup(
            PanelLoaisanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLoaisanphamLayout.createSequentialGroup()
                .addComponent(qllspLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoaisanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelLoaisanphamLayout.setVerticalGroup(
            PanelLoaisanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(qllspLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
            .addComponent(labelLoaisanpham, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        PanelPhieunhaphang.setBackground(null
        );
        PanelPhieunhaphang.setMinimumSize(new java.awt.Dimension(0, 33));
        PanelPhieunhaphang.setPreferredSize(new java.awt.Dimension(101, 33));

        labelPhieunhaphang.setBackground(null);
        labelPhieunhaphang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelPhieunhaphang.setForeground(new java.awt.Color(255, 255, 255));
        labelPhieunhaphang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelPhieunhaphang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_truck_30px.png"))); // NOI18N
        labelPhieunhaphang.setText("Phiếu nhập hàng");
        labelPhieunhaphang.setBorder(new EmptyBorder(0,25,0,0));
        labelPhieunhaphang.setPreferredSize(new java.awt.Dimension(101, 33));
        labelPhieunhaphang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelPhieunhaphangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelPhieunhaphangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelPhieunhaphangMouseExited(evt);
            }
        });

        qlpnhLeft.setBackground(new java.awt.Color(37, 187, 219));
        qlpnhLeft.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout qlpnhLeftLayout = new javax.swing.GroupLayout(qlpnhLeft);
        qlpnhLeft.setLayout(qlpnhLeftLayout);
        qlpnhLeftLayout.setHorizontalGroup(
            qlpnhLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        qlpnhLeftLayout.setVerticalGroup(
            qlpnhLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelPhieunhaphangLayout = new javax.swing.GroupLayout(PanelPhieunhaphang);
        PanelPhieunhaphang.setLayout(PanelPhieunhaphangLayout);
        PanelPhieunhaphangLayout.setHorizontalGroup(
            PanelPhieunhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPhieunhaphangLayout.createSequentialGroup()
                .addComponent(qlpnhLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPhieunhaphang, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelPhieunhaphangLayout.setVerticalGroup(
            PanelPhieunhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(qlpnhLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
            .addComponent(labelPhieunhaphang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PanelHoadon.setBackground(null
        );
        PanelHoadon.setPreferredSize(new java.awt.Dimension(101, 33));

        labelqlhd.setBackground(null);
        labelqlhd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelqlhd.setForeground(new java.awt.Color(255, 255, 255));
        labelqlhd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelqlhd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/List.png"))); // NOI18N
        labelqlhd.setText("Hóa đơn");
        labelqlhd.setBorder(new EmptyBorder(0,25,0,0));
        labelqlhd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelqlhdMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelqlhdMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelqlhdMouseExited(evt);
            }
        });

        qlhdLeft.setBackground(new java.awt.Color(37, 187, 219));
        qlhdLeft.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout qlhdLeftLayout = new javax.swing.GroupLayout(qlhdLeft);
        qlhdLeft.setLayout(qlhdLeftLayout);
        qlhdLeftLayout.setHorizontalGroup(
            qlhdLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        qlhdLeftLayout.setVerticalGroup(
            qlhdLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelHoadonLayout = new javax.swing.GroupLayout(PanelHoadon);
        PanelHoadon.setLayout(PanelHoadonLayout);
        PanelHoadonLayout.setHorizontalGroup(
            PanelHoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHoadonLayout.createSequentialGroup()
                .addComponent(qlhdLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelqlhd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelHoadonLayout.setVerticalGroup(
            PanelHoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(qlhdLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
            .addComponent(labelqlhd, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        PanelNhacungcap.setBackground(null
        );
        PanelNhacungcap.setPreferredSize(new java.awt.Dimension(101, 33));

        labelNhacungcap.setBackground(null);
        labelNhacungcap.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelNhacungcap.setForeground(new java.awt.Color(255, 255, 255));
        labelNhacungcap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNhacungcap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_company_30px.png"))); // NOI18N
        labelNhacungcap.setText("Nhà cung cấp");
        labelNhacungcap.setBorder(new EmptyBorder(0,25,0,0));
        labelNhacungcap.setPreferredSize(new java.awt.Dimension(101, 15));
        labelNhacungcap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelNhacungcapMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelNhacungcapMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelNhacungcapMouseExited(evt);
            }
        });

        qlnccLeft.setBackground(new java.awt.Color(37, 187, 219));
        qlnccLeft.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout qlnccLeftLayout = new javax.swing.GroupLayout(qlnccLeft);
        qlnccLeft.setLayout(qlnccLeftLayout);
        qlnccLeftLayout.setHorizontalGroup(
            qlnccLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        qlnccLeftLayout.setVerticalGroup(
            qlnccLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelNhacungcapLayout = new javax.swing.GroupLayout(PanelNhacungcap);
        PanelNhacungcap.setLayout(PanelNhacungcapLayout);
        PanelNhacungcapLayout.setHorizontalGroup(
            PanelNhacungcapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelNhacungcapLayout.createSequentialGroup()
                .addComponent(qlnccLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNhacungcap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelNhacungcapLayout.setVerticalGroup(
            PanelNhacungcapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(qlnccLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
            .addComponent(labelNhacungcap, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        PanelKhuyenmai.setBackground(null
        );
        PanelKhuyenmai.setPreferredSize(new java.awt.Dimension(101, 33));

        labelkhuyenmai.setBackground(null);
        labelkhuyenmai.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelkhuyenmai.setForeground(new java.awt.Color(255, 255, 255));
        labelkhuyenmai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelkhuyenmai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_gift_30px.png"))); // NOI18N
        labelkhuyenmai.setText("Khuyến mãi");
        labelkhuyenmai.setBorder(new EmptyBorder(0,25,0,0));
        labelkhuyenmai.setPreferredSize(new java.awt.Dimension(101, 15));
        labelkhuyenmai.setRequestFocusEnabled(false);
        labelkhuyenmai.setVerifyInputWhenFocusTarget(false);
        labelkhuyenmai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelkhuyenmaiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelkhuyenmaiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelkhuyenmaiMouseExited(evt);
            }
        });

        qlkhuyenmaiLeft.setBackground(new java.awt.Color(37, 187, 219));
        qlkhuyenmaiLeft.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout qlkhuyenmaiLeftLayout = new javax.swing.GroupLayout(qlkhuyenmaiLeft);
        qlkhuyenmaiLeft.setLayout(qlkhuyenmaiLeftLayout);
        qlkhuyenmaiLeftLayout.setHorizontalGroup(
            qlkhuyenmaiLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        qlkhuyenmaiLeftLayout.setVerticalGroup(
            qlkhuyenmaiLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelKhuyenmaiLayout = new javax.swing.GroupLayout(PanelKhuyenmai);
        PanelKhuyenmai.setLayout(PanelKhuyenmaiLayout);
        PanelKhuyenmaiLayout.setHorizontalGroup(
            PanelKhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelKhuyenmaiLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(qlkhuyenmaiLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelkhuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelKhuyenmaiLayout.setVerticalGroup(
            PanelKhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(qlkhuyenmaiLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
            .addComponent(labelkhuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        PanelNhanvien.setBackground(null
        );
        PanelNhanvien.setPreferredSize(new java.awt.Dimension(101, 33));
        PanelNhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PanelNhanvienMouseEntered(evt);
            }
        });

        labelNhanvien.setBackground(null);
        labelNhanvien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelNhanvien.setForeground(new java.awt.Color(255, 255, 255));
        labelNhanvien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNhanvien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/User.png"))); // NOI18N
        labelNhanvien.setText("Nhân viên");
        labelNhanvien.setBorder(new EmptyBorder(0,25,0,0));
        labelNhanvien.setPreferredSize(new java.awt.Dimension(101, 15));
        labelNhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelNhanvienMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelNhanvienMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelNhanvienMouseExited(evt);
            }
        });

        qlnvLeft.setBackground(new java.awt.Color(37, 187, 219));
        qlnvLeft.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout qlnvLeftLayout = new javax.swing.GroupLayout(qlnvLeft);
        qlnvLeft.setLayout(qlnvLeftLayout);
        qlnvLeftLayout.setHorizontalGroup(
            qlnvLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        qlnvLeftLayout.setVerticalGroup(
            qlnvLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelNhanvienLayout = new javax.swing.GroupLayout(PanelNhanvien);
        PanelNhanvien.setLayout(PanelNhanvienLayout);
        PanelNhanvienLayout.setHorizontalGroup(
            PanelNhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelNhanvienLayout.createSequentialGroup()
                .addComponent(qlnvLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelNhanvienLayout.setVerticalGroup(
            PanelNhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(qlnvLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
            .addComponent(labelNhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        PanelKhachhang.setBackground(null
        );
        PanelKhachhang.setPreferredSize(new java.awt.Dimension(101, 33));

        lbkhachhang.setBackground(null);
        lbkhachhang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbkhachhang.setForeground(new java.awt.Color(255, 255, 255));
        lbkhachhang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbkhachhang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Users.png"))); // NOI18N
        lbkhachhang.setText("Khách hàng");
        lbkhachhang.setBorder(new EmptyBorder(0,25,0,0));
        lbkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbkhachhangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbkhachhangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbkhachhangMouseExited(evt);
            }
        });

        qlkhLeft.setBackground(new java.awt.Color(37, 187, 219));
        qlkhLeft.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout qlkhLeftLayout = new javax.swing.GroupLayout(qlkhLeft);
        qlkhLeft.setLayout(qlkhLeftLayout);
        qlkhLeftLayout.setHorizontalGroup(
            qlkhLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        qlkhLeftLayout.setVerticalGroup(
            qlkhLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelKhachhangLayout = new javax.swing.GroupLayout(PanelKhachhang);
        PanelKhachhang.setLayout(PanelKhachhangLayout);
        PanelKhachhangLayout.setHorizontalGroup(
            PanelKhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelKhachhangLayout.createSequentialGroup()
                .addComponent(qlkhLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelKhachhangLayout.setVerticalGroup(
            PanelKhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(qlkhLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(lbkhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        PanelThongke.setBackground(null
        );
        PanelThongke.setPreferredSize(new java.awt.Dimension(101, 33));

        labelthongke.setBackground(null);
        labelthongke.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelthongke.setForeground(new java.awt.Color(255, 255, 255));
        labelthongke.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelthongke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_futures_30px.png"))); // NOI18N
        labelthongke.setText("Thống kê");
        labelthongke.setBorder(new EmptyBorder(0,25,0,0));
        labelthongke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelthongkeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelthongkeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelthongkeMouseExited(evt);
            }
        });

        qlthongkeLeft.setBackground(new java.awt.Color(37, 187, 219));
        qlthongkeLeft.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout qlthongkeLeftLayout = new javax.swing.GroupLayout(qlthongkeLeft);
        qlthongkeLeft.setLayout(qlthongkeLeftLayout);
        qlthongkeLeftLayout.setHorizontalGroup(
            qlthongkeLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        qlthongkeLeftLayout.setVerticalGroup(
            qlthongkeLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelThongkeLayout = new javax.swing.GroupLayout(PanelThongke);
        PanelThongke.setLayout(PanelThongkeLayout);
        PanelThongkeLayout.setHorizontalGroup(
            PanelThongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelThongkeLayout.createSequentialGroup()
                .addComponent(qlthongkeLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelthongke, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelThongkeLayout.setVerticalGroup(
            PanelThongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(qlthongkeLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
            .addComponent(labelthongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PanelTaikhoan.setBackground(null
        );
        PanelTaikhoan.setPreferredSize(new java.awt.Dimension(101, 33));

        qltaikhoanLeft.setBackground(new java.awt.Color(37, 187, 219));
        qltaikhoanLeft.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout qltaikhoanLeftLayout = new javax.swing.GroupLayout(qltaikhoanLeft);
        qltaikhoanLeft.setLayout(qltaikhoanLeftLayout);
        qltaikhoanLeftLayout.setHorizontalGroup(
            qltaikhoanLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        qltaikhoanLeftLayout.setVerticalGroup(
            qltaikhoanLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lbqltk.setBackground(null);
        lbqltk.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbqltk.setForeground(new java.awt.Color(255, 255, 255));
        lbqltk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbqltk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_key_30px.png"))); // NOI18N
        lbqltk.setText("Tài khoản");
        lbqltk.setBorder(new EmptyBorder(0,25,0,0));
        lbqltk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbqltkMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbqltkMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbqltkMouseExited(evt);
            }
        });

        javax.swing.GroupLayout PanelTaikhoanLayout = new javax.swing.GroupLayout(PanelTaikhoan);
        PanelTaikhoan.setLayout(PanelTaikhoanLayout);
        PanelTaikhoanLayout.setHorizontalGroup(
            PanelTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTaikhoanLayout.createSequentialGroup()
                .addComponent(qltaikhoanLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbqltk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelTaikhoanLayout.setVerticalGroup(
            PanelTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(qltaikhoanLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
            .addComponent(lbqltk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelBanhang, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(PanelNhaphang, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(PanelSanpham, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(PanelLoaisanpham, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(PanelPhieunhaphang, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(PanelHoadon, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(PanelNhacungcap, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(PanelKhuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(PanelNhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(PanelKhachhang, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(PanelThongke, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(PanelTaikhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelBanhang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelNhaphang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelSanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelLoaisanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelPhieunhaphang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelHoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelNhacungcap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelKhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelKhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelThongke, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel3);

        txtTenNguoidung.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTenNguoidung.setForeground(new java.awt.Color(255, 255, 255));
        txtTenNguoidung.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTenNguoidung.setText("Admin");

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btnDangxuat))
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(txtTenNguoidung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addComponent(txtTenNguoidung)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDangxuat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelMenuhtLayout = new javax.swing.GroupLayout(PanelMenuht);
        PanelMenuht.setLayout(PanelMenuhtLayout);
        PanelMenuhtLayout.setHorizontalGroup(
            PanelMenuhtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuhtLayout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelMenuhtLayout.setVerticalGroup(
            PanelMenuhtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuhtLayout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        PanelHienthi.setBackground(new java.awt.Color(255, 255, 255));

        panelBH.setBackground(new java.awt.Color(204, 204, 204));

        Panelsanpham.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Tìm kiếm");

        txFindBh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txFindBhActionPerformed(evt);
            }
        });
        txFindBh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txFindBhKeyPressed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_data_backup_30px.png"))); // NOI18N
        jButton8.setText("Làm mới ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txFindBh, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txFindBh))
                .addContainerGap())
        );

        tblBhLeft.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblBhLeft.setRowHeight(35);
        tblBhLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBhLeftMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblBhLeft);

        btnBhThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBhThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_add_30px.png"))); // NOI18N
        btnBhThem.setText("Thêm");
        btnBhThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBhThemActionPerformed(evt);
            }
        });

        txttenspbh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txttenspbh.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên sản phẩm"));
        txttenspbh.setEnabled(false);

        txtmaspbh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtmaspbh.setBorder(javax.swing.BorderFactory.createTitledBorder("Mã sản phẩm"));
        txtmaspbh.setEnabled(false);
        txtmaspbh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmaspbhActionPerformed(evt);
            }
        });

        txtdongiabh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtdongiabh.setBorder(javax.swing.BorderFactory.createTitledBorder("Đơn giá"));
        txtdongiabh.setEnabled(false);

        txtsoluongbh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtsoluongbh.setBorder(javax.swing.BorderFactory.createTitledBorder("Số lượng"));

        javax.swing.GroupLayout PanelsanphamLayout = new javax.swing.GroupLayout(Panelsanpham);
        Panelsanpham.setLayout(PanelsanphamLayout);
        PanelsanphamLayout.setHorizontalGroup(
            PanelsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelsanphamLayout.createSequentialGroup()
                .addGroup(PanelsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelsanphamLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane5)))
                    .addGroup(PanelsanphamLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(PanelsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelsanphamLayout.createSequentialGroup()
                                .addComponent(btnBhThem, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(PanelsanphamLayout.createSequentialGroup()
                                .addGroup(PanelsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtdongiabh)
                                    .addComponent(txttenspbh, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PanelsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtmaspbh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtsoluongbh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)))))
                .addContainerGap())
        );
        PanelsanphamLayout.setVerticalGroup(
            PanelsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelsanphamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(PanelsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmaspbh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttenspbh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsoluongbh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdongiabh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(btnBhThem)
                .addContainerGap())
        );

        Panelchithietbanhang.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("  Chi tiết đơn hàng ");

        btnHuyBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnHuyBill.setText("Hủy");
        btnHuyBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyBillActionPerformed(evt);
            }
        });

        btnThanhtoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_us_dollar_30px.png"))); // NOI18N
        btnThanhtoan.setText("Thanh toán");
        btnThanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhtoanActionPerformed(evt);
            }
        });

        tblBhRight.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblBhRight.setRowHeight(35);
        jScrollPane6.setViewportView(tblBhRight);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Delete.png"))); // NOI18N
        jButton1.setText("Xóa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txBhMahd.setEditable(false);
        txBhMahd.setBackground(new java.awt.Color(255, 255, 255));
        txBhMahd.setBorder(javax.swing.BorderFactory.createTitledBorder("Mã hóa đơn"));
        txBhMahd.setMaximumSize(new java.awt.Dimension(13, 36));

        txBhIdnv.setEditable(false);
        txBhIdnv.setBackground(new java.awt.Color(255, 255, 255));
        txBhIdnv.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhân viên"));
        txBhIdnv.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txBhIdnv.setMaximumSize(new java.awt.Dimension(13, 36));

        txBhIdkh.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Khách hàng"));
        txBhIdkh.setMaximumSize(new java.awt.Dimension(13, 36));

        txBhKm.setEditable(false);
        txBhKm.setBackground(new java.awt.Color(255, 255, 255));
        txBhKm.setBorder(javax.swing.BorderFactory.createTitledBorder("Khuyến mãi"));
        txBhKm.setMaximumSize(new java.awt.Dimension(13, 36));

        btnthemkmbh.setText("...");
        btnthemkmbh.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnthemkmbh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemkmbhActionPerformed(evt);
            }
        });

        txTongBh.setText("0VND");
        txTongBh.setBorder(javax.swing.BorderFactory.createTitledBorder("Tổng giá"));
        txTongBh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTongBhActionPerformed(evt);
            }
        });

        txNgaylapBh.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngày lập"));

        txGiolapBh.setBorder(javax.swing.BorderFactory.createTitledBorder("Giờ lập"));

        btnComboboxKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "....", "Thêm KH", "Chọn KH" }));
        btnComboboxKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComboboxKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelchithietbanhangLayout = new javax.swing.GroupLayout(Panelchithietbanhang);
        Panelchithietbanhang.setLayout(PanelchithietbanhangLayout);
        PanelchithietbanhangLayout.setHorizontalGroup(
            PanelchithietbanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelchithietbanhangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelchithietbanhangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHuyBill, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btnThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(PanelchithietbanhangLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(PanelchithietbanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelchithietbanhangLayout.createSequentialGroup()
                        .addComponent(txBhIdkh, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnComboboxKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txBhMahd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txTongBh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelchithietbanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelchithietbanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelchithietbanhangLayout.createSequentialGroup()
                            .addComponent(txBhKm, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnthemkmbh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txBhIdnv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelchithietbanhangLayout.createSequentialGroup()
                        .addComponent(txNgaylapBh, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txGiolapBh, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelchithietbanhangLayout.setVerticalGroup(
            PanelchithietbanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelchithietbanhangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelchithietbanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txBhMahd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txBhIdnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelchithietbanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txBhIdkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txBhKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthemkmbh)
                    .addComponent(btnComboboxKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelchithietbanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txNgaylapBh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txTongBh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txGiolapBh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelchithietbanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuyBill)
                    .addComponent(btnThanhtoan)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelBHLayout = new javax.swing.GroupLayout(panelBH);
        panelBH.setLayout(panelBHLayout);
        panelBHLayout.setHorizontalGroup(
            panelBHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBHLayout.createSequentialGroup()
                .addComponent(Panelsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Panelchithietbanhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBHLayout.setVerticalGroup(
            panelBHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBHLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelBHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Panelchithietbanhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Panelsanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        panelKH.setBackground(new java.awt.Color(255, 255, 255));

        btnDelKh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnDelKh.setText("Xóa");
        btnDelKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelKhActionPerformed(evt);
            }
        });

        btnSuaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/editicon.png"))); // NOI18N
        btnSuaKH.setText("Sửa");
        btnSuaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKHActionPerformed(evt);
            }
        });

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_ms_excel_30px.png"))); // NOI18N
        jButton14.setText("Xuất Excel ");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID Khách hàng", "Tên Khách hàng", "Số điện thoại", "Địa chỉ", "Trạng thái" }));

        txFindKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txFindKhActionPerformed(evt);
            }
        });
        txFindKh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txFindKhKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txFindKh, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txFindKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tblKh.setAutoCreateRowSorter(true);
        tblKh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblKh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID Khách hàng", "Họ Khách hàng", "Tên Khách hàng", "Giới tính", "Địa chỉ", "SĐT", "Email", "Tuổi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKh.setRowHeight(30);
        jScrollPane3.setViewportView(tblKh);

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_data_backup_30px.png"))); // NOI18N
        jButton15.setText("Làm mới");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelKHLayout = new javax.swing.GroupLayout(panelKH);
        panelKH.setLayout(panelKHLayout);
        panelKHLayout.setHorizontalGroup(
            panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKHLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnDelKh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSuaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                    .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        panelKHLayout.setVerticalGroup(
            panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKHLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKHLayout.createSequentialGroup()
                        .addComponent(btnDelKh)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaKH)
                        .addGap(18, 18, 18)
                        .addComponent(jButton14)
                        .addGap(18, 18, 18)
                        .addComponent(jButton15))
                    .addGroup(panelKHLayout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(143, Short.MAX_VALUE))
        );

        panelNV.setBackground(new java.awt.Color(255, 255, 255));
        panelNV.setPreferredSize(new java.awt.Dimension(1038, 627));

        btnThemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_add_30px.png"))); // NOI18N
        btnThemNV.setText("Thêm");
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSuaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/editicon.png"))); // NOI18N
        btnSuaNV.setText("Sửa");
        btnSuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNVActionPerformed(evt);
            }
        });

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_ms_excel_30px.png"))); // NOI18N
        jButton19.setText("Xuất Excel ");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID Nhân viên", "Tên Nhân viên", "Số điện thoại", "Lương", "Trạng thái" }));

        txFindNv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txFindNvKeyPressed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hiện hành", "Đã nghỉ", "Tất cả" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Trạng thái");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txFindNv)
                .addGap(81, 81, 81)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(txFindNv)
                        .addGap(1, 1, 1))
                    .addComponent(jComboBox4)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1))
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );

        tblnv.setAutoCreateRowSorter(true);
        tblnv.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblnv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID Nhân viên", "Họ ", "Tên ", "Giới tính", "Tuổi", "Địa chỉ", "SĐT", "Chức vụ", "Lương", "Trạng thái"
            }
        ));
        tblnv.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblnv.setRowHeight(30);
        jScrollPane4.setViewportView(tblnv);

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_data_backup_30px.png"))); // NOI18N
        jButton20.setText("Làm mới");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNVLayout = new javax.swing.GroupLayout(panelNV);
        panelNV.setLayout(panelNVLayout);
        panelNVLayout.setHorizontalGroup(
            panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNVLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(btnThemNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE))
                .addGap(86, 86, 86))
        );
        panelNVLayout.setVerticalGroup(
            panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNVLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNVLayout.createSequentialGroup()
                        .addComponent(btnThemNV)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaNV)
                        .addGap(18, 18, 18)
                        .addComponent(jButton19)
                        .addGap(18, 18, 18)
                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelNVLayout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        panelNCC.setBackground(new java.awt.Color(255, 255, 255));
        panelNCC.setPreferredSize(new java.awt.Dimension(1038, 627));

        btnThemNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_add_30px.png"))); // NOI18N
        btnThemNCC.setText("Thêm");
        btnThemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNCCActionPerformed(evt);
            }
        });

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        jButton22.setText("Xóa");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        btnSuaNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/editicon.png"))); // NOI18N
        btnSuaNCC.setText("Sửa");
        btnSuaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNCCActionPerformed(evt);
            }
        });

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "ID NCC", "Tên NCC", "SĐT", "Địa chỉ", " " }));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addContainerGap())
        );

        tblncc.setAutoCreateRowSorter(true);
        tblncc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblncc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID NCC", "Tên NCC", "SĐT", "Địa chỉ"
            }
        ));
        tblncc.setRowHeight(30);
        jScrollPane7.setViewportView(tblncc);

        resetncc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_data_backup_30px.png"))); // NOI18N
        resetncc.setText("Làm mới");
        resetncc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetnccActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNCCLayout = new javax.swing.GroupLayout(panelNCC);
        panelNCC.setLayout(panelNCCLayout);
        panelNCCLayout.setHorizontalGroup(
            panelNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNCCLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panelNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resetncc, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(panelNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        panelNCCLayout.setVerticalGroup(
            panelNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNCCLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelNCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNCCLayout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelNCCLayout.createSequentialGroup()
                        .addComponent(btnThemNCC)
                        .addGap(18, 18, 18)
                        .addComponent(jButton22)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaNCC)
                        .addGap(18, 18, 18)
                        .addComponent(resetncc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        panelTaikhoan.setBackground(new java.awt.Color(255, 255, 255));
        panelTaikhoan.setPreferredSize(new java.awt.Dimension(1038, 627));

        btnThemTaikhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_add_30px.png"))); // NOI18N
        btnThemTaikhoan.setText("Thêm");
        btnThemTaikhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTaikhoanActionPerformed(evt);
            }
        });

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        jButton27.setText("Xóa");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        btnSuaTaikhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/editicon.png"))); // NOI18N
        btnSuaTaikhoan.setText("Sửa");
        btnSuaTaikhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTaikhoanActionPerformed(evt);
            }
        });

        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_ms_excel_30px.png"))); // NOI18N
        jButton29.setText("Xuất Excel ");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "ID Nhân viên", "Tên Đăng nhập", "Chức vụ", " " }));

        txFindtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txFindtkKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(txFindtk, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(195, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(txFindtk))
                .addContainerGap())
        );

        tbltk.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbltk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID Nhân viên", "Tên Nhân viên", "Tên Đăng nhập", "Mật khẩu", "Chức vụ"
            }
        ));
        tbltk.setRowHeight(30);
        jScrollPane8.setViewportView(tbltk);

        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_data_backup_30px.png"))); // NOI18N
        jButton30.setText("Làm mới");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTaikhoanLayout = new javax.swing.GroupLayout(panelTaikhoan);
        panelTaikhoan.setLayout(panelTaikhoanLayout);
        panelTaikhoanLayout.setHorizontalGroup(
            panelTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTaikhoanLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(panelTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaTaikhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemTaikhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTaikhoanLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTaikhoanLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(79, 121, Short.MAX_VALUE))
        );
        panelTaikhoanLayout.setVerticalGroup(
            panelTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTaikhoanLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(panelTaikhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTaikhoanLayout.createSequentialGroup()
                        .addComponent(btnThemTaikhoan)
                        .addGap(18, 18, 18)
                        .addComponent(jButton27)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaTaikhoan)
                        .addGap(18, 18, 18)
                        .addComponent(jButton29)
                        .addGap(18, 18, 18)
                        .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTaikhoanLayout.createSequentialGroup()
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60))
        );

        panelKM.setBackground(new java.awt.Color(255, 255, 255));
        panelKM.setPreferredSize(new java.awt.Dimension(1038, 627));

        btnThemKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_add_30px.png"))); // NOI18N
        btnThemKM.setText("Thêm");
        btnThemKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKMActionPerformed(evt);
            }
        });

        btnSuaKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/editicon.png"))); // NOI18N
        btnSuaKM.setText("Sửa");
        btnSuaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKMActionPerformed(evt);
            }
        });

        jButton36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_ms_excel_30px.png"))); // NOI18N
        jButton36.setText("Xuất Excel ");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "ID Sản phẩm", "Loại Sản phẩm", "Tên Sản phẩm", "Đơn giá", "Trạng thái" }));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(230, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jTextField22))
                .addGap(12, 12, 12))
        );

        tblkm.setAutoCreateRowSorter(true);
        tblkm.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblkm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID Khuyến mãi", "Tên khuyên mãi", "Ngày bắt đầu", "Ngày kết thức", "Nội dung", "Phần trăm KM", "Điều kiện KM"
            }
        ));
        tblkm.setRowHeight(30);
        jScrollPane11.setViewportView(tblkm);

        jButton37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_data_backup_30px.png"))); // NOI18N
        jButton37.setText("Làm mới");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelKMLayout = new javax.swing.GroupLayout(panelKM);
        panelKM.setLayout(panelKMLayout);
        panelKMLayout.setHorizontalGroup(
            panelKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKMLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panelKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaKM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton36, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(jButton37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(panelKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane11)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        panelKMLayout.setVerticalGroup(
            panelKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKMLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelKMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKMLayout.createSequentialGroup()
                        .addComponent(btnThemKM)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaKM)
                        .addGap(18, 18, 18)
                        .addComponent(jButton36)
                        .addGap(18, 18, 18)
                        .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelKMLayout.createSequentialGroup()
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        panelLSP.setBackground(new java.awt.Color(255, 255, 255));

        btnThemLSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_add_30px.png"))); // NOI18N
        btnThemLSP.setText("Thêm");
        btnThemLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLSPActionPerformed(evt);
            }
        });

        btnSuaLSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/editicon.png"))); // NOI18N
        btnSuaLSP.setText("Sửa");
        btnSuaLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaLSPActionPerformed(evt);
            }
        });

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "ID Loại Sản phẩm", "Tên Loại Sản phẩm", " " }));

        jTextField23.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField23KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jTextField23))
                .addContainerGap())
        );

        tbllsp.setAutoCreateRowSorter(true);
        tbllsp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbllsp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID Loại Sản phẩm", "Tên  Loại Sản phẩm"
            }
        ));
        tbllsp.setRowHeight(35);
        jScrollPane12.setViewportView(tbllsp);

        jButton42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_data_backup_30px.png"))); // NOI18N
        jButton42.setText("Làm mới");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLSPLayout = new javax.swing.GroupLayout(panelLSP);
        panelLSP.setLayout(panelLSPLayout);
        panelLSPLayout.setHorizontalGroup(
            panelLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLSPLayout.createSequentialGroup()
                .addGroup(panelLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLSPLayout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLSPLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(panelLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnThemLSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSuaLSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        panelLSPLayout.setVerticalGroup(
            panelLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLSPLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLSPLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btnThemLSP)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaLSP)
                        .addGap(18, 18, 18)
                        .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLSPLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        panelHD.setBackground(new java.awt.Color(255, 255, 255));

        btnInhoadon.setBackground(new java.awt.Color(255, 255, 255));
        btnInhoadon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/editicon.png"))); // NOI18N
        btnInhoadon.setText("IN Hóa Đơn");
        btnInhoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInhoadonActionPerformed(evt);
            }
        });

        btnXuatechd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_ms_excel_30px.png"))); // NOI18N
        btnXuatechd.setText("Xuất Excel ");
        btnXuatechd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatechdActionPerformed(evt);
            }
        });

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        txFindHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txFindHDActionPerformed(evt);
            }
        });
        txFindHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txFindHDKeyPressed(evt);
            }
        });

        txfromLochd.setBackground(new java.awt.Color(255, 255, 255));
        txfromLochd.setBorder(javax.swing.BorderFactory.createTitledBorder("Từ"));

        txtoLochd.setBackground(new java.awt.Color(255, 255, 255));
        txtoLochd.setBorder(javax.swing.BorderFactory.createTitledBorder("Đến"));

        btnLochd.setText("Lọc");
        btnLochd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLochdActionPerformed(evt);
            }
        });

        jButton5.setText("Bỏ lọc");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(txFindHD, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(txfromLochd, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(txtoLochd, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnLochd, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txfromLochd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtoLochd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txFindHD)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel28Layout.createSequentialGroup()
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnLochd)
                                    .addComponent(jButton5))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        tblhd.setAutoCreateRowSorter(true);
        tblhd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblhd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID Hóa đơn", "ID Nhân viên", "ID Khách hàng", "Ngày lập", "Giờ lập", "Tổng hóa đơn", "ID khuyến mãi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblhd.setRowHeight(30);
        tblhd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhdMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tblhd);

        btnCapnhathd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_data_backup_30px.png"))); // NOI18N
        btnCapnhathd.setText("Cập nhập dữ liệu");
        btnCapnhathd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapnhathdActionPerformed(evt);
            }
        });

        tblcthd.setAutoCreateRowSorter(true);
        tblcthd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblcthd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID sản phẩm", "Tên sản phẩm", "Số lượng", "Giá", "Tổng giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblcthd.setRowHeight(30);
        jScrollPane2.setViewportView(tblcthd);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Chi Tiết Hóa Đơn");
        jLabel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout panelHDLayout = new javax.swing.GroupLayout(panelHD);
        panelHD.setLayout(panelHDLayout);
        panelHDLayout.setHorizontalGroup(
            panelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHDLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelHDLayout.createSequentialGroup()
                        .addGroup(panelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCapnhathd, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXuatechd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnInhoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(161, Short.MAX_VALUE))
        );
        panelHDLayout.setVerticalGroup(
            panelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHDLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHDLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(btnCapnhathd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnInhoadon)
                        .addGap(18, 18, 18)
                        .addComponent(btnXuatechd)
                        .addGap(94, 94, 94))
                    .addGroup(panelHDLayout.createSequentialGroup()
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(7, 7, 7)))
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelThongke.setBackground(new java.awt.Color(255, 255, 255));
        panelThongke.setPreferredSize(new java.awt.Dimension(1038, 627));

        pnchart.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnchartLayout = new javax.swing.GroupLayout(pnchart);
        pnchart.setLayout(pnchartLayout);
        pnchartLayout.setHorizontalGroup(
            pnchartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
        );
        pnchartLayout.setVerticalGroup(
            pnchartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 231, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(190, 220, 250));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Sản phẩm");

        tbSpTk.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tbSpTk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tbSpTk.setText("jLabel21");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(tbSpTk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tbSpTk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(152, 172, 248));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Nhân viên");

        lbNvTk.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbNvTk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNvTk.setText("jLabel22");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(lbNvTk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNvTk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(176, 169, 249));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Doanh thu tháng này");

        lbDtTk.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbDtTk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDtTk.setText("jLabel22");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lbDtTk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbDtTk)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel27.setBackground(new java.awt.Color(119, 172, 241));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Tổng đơn hàng tháng này");

        lbHdTk.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbHdTk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHdTk.setText("jLabel22");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(lbHdTk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbHdTk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnchart1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnchart1Layout = new javax.swing.GroupLayout(pnchart1);
        pnchart1.setLayout(pnchart1Layout);
        pnchart1Layout.setHorizontalGroup(
            pnchart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnchart1Layout.setVerticalGroup(
            pnchart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 231, Short.MAX_VALUE)
        );

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("jLabel18");

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnchart3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnchart3Layout = new javax.swing.GroupLayout(pnchart3);
        pnchart3.setLayout(pnchart3Layout);
        pnchart3Layout.setHorizontalGroup(
            pnchart3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 484, Short.MAX_VALUE)
        );
        pnchart3Layout.setVerticalGroup(
            pnchart3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel56.setBackground(new java.awt.Color(255, 255, 255));
        jPanel56.setBorder(javax.swing.BorderFactory.createTitledBorder("Thống kê"));

        jButton16.setText("Thống kê");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        txYear.setText("2021");
        txYear.setBorder(javax.swing.BorderFactory.createTitledBorder("Năm"));
        txYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txYearActionPerformed(evt);
            }
        });

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Mặt hàng bán chạy        ");

        cbthangThongke.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12" }));
        cbthangThongke.setSelectedIndex(4);
        cbthangThongke.setBorder(null);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("jLabel17");

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txYear, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cbthangThongke, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(txYear, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cbthangThongke, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel55.setBackground(new java.awt.Color(255, 255, 255));

        tblspsh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane23.setViewportView(tblspsh);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Sản Phẩm Sắp hết");

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane23)))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel55Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelThongkeLayout = new javax.swing.GroupLayout(panelThongke);
        panelThongke.setLayout(panelThongkeLayout);
        panelThongkeLayout.setHorizontalGroup(
            panelThongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongkeLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThongkeLayout.createSequentialGroup()
                .addGroup(panelThongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelThongkeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelThongkeLayout.createSequentialGroup()
                        .addGroup(panelThongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelThongkeLayout.createSequentialGroup()
                                .addComponent(pnchart3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThongkeLayout.createSequentialGroup()
                                .addComponent(pnchart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(panelThongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnchart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        panelThongkeLayout.setVerticalGroup(
            panelThongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelThongkeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelThongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(panelThongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnchart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnchart1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelThongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnchart3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelSP.setBackground(java.awt.Color.white);
        panelSP.setPreferredSize(new java.awt.Dimension(1038, 627));

        btnThemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_add_30px.png"))); // NOI18N
        btnThemSP.setText("Thêm");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnSuaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/editicon.png"))); // NOI18N
        btnSuaSP.setText("Sửa");
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        jButton49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_ms_excel_30px.png"))); // NOI18N
        jButton49.setText("Xuất Excel ");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        jPanel30.setBackground(java.awt.Color.white);
        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "ID Sản phẩm", "Loại Sản phẩm", "Tên Sản phẩm", "Đơn giá", "Trạng thái" }));

        jTextField25.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField25KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(303, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tblsp.setAutoCreateRowSorter(true);
        tblsp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblsp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID Loại Sản phẩm", "Tên loại sản phẩm", "ID Sản phẩm", "Tên Sản phẩm", "Đơn giá", "Số lượng", "Đơn vị tính", "Trạng thái"
            }
        ));
        tblsp.setRowHeight(35);
        jScrollPane14.setViewportView(tblsp);

        jButton50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_data_backup_30px.png"))); // NOI18N
        jButton50.setText("Làm mới");
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSPLayout = new javax.swing.GroupLayout(panelSP);
        panelSP.setLayout(panelSPLayout);
        panelSPLayout.setHorizontalGroup(
            panelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSPLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(panelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        panelSPLayout.setVerticalGroup(
            panelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSPLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSPLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelSPLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnThemSP)
                        .addGap(47, 47, 47)
                        .addComponent(jButton49)
                        .addGap(44, 44, 44)
                        .addComponent(btnSuaSP)
                        .addGap(49, 49, 49)
                        .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(193, Short.MAX_VALUE))
        );

        panelCaidat.setPreferredSize(new java.awt.Dimension(1038, 627));

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel48.setText("Hệ thống đang nâng cấp");

        javax.swing.GroupLayout panelCaidatLayout = new javax.swing.GroupLayout(panelCaidat);
        panelCaidat.setLayout(panelCaidatLayout);
        panelCaidatLayout.setHorizontalGroup(
            panelCaidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCaidatLayout.createSequentialGroup()
                .addContainerGap(266, Short.MAX_VALUE)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213))
        );
        panelCaidatLayout.setVerticalGroup(
            panelCaidatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCaidatLayout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(289, Short.MAX_VALUE))
        );

        panelNH.setBackground(new java.awt.Color(204, 204, 204));

        Panelchitietnhaphang.setBackground(new java.awt.Color(255, 255, 255));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("  Chi tiết nhập hàng ");

        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_delete_forever_30px_1.png"))); // NOI18N
        jButton31.setText("Hủy");
        jButton31.setPreferredSize(new java.awt.Dimension(110, 40));
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_us_dollar_30px.png"))); // NOI18N
        jButton32.setText("Nhập hàng");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        tblNhRight.setAutoCreateRowSorter(true);
        tblNhRight.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblNhRight.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID sản phẩm", "Tến Sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ));
        tblNhRight.setRowHeight(30);
        jScrollPane10.setViewportView(tblNhRight);

        txIdNvNh.setEditable(false);
        txIdNvNh.setBackground(new java.awt.Color(255, 255, 255));
        txIdNvNh.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhân viên"));

        txIdNccNh.setEditable(false);
        txIdNccNh.setBackground(new java.awt.Color(255, 255, 255));
        txIdNccNh.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhà cung cấp"));

        txTongNh.setEditable(false);
        txTongNh.setBackground(new java.awt.Color(255, 255, 255));
        txTongNh.setBorder(javax.swing.BorderFactory.createTitledBorder("Tổng tiền"));
        txTongNh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTongNhActionPerformed(evt);
            }
        });

        txIdPNH.setEditable(false);
        txIdPNH.setBackground(new java.awt.Color(255, 255, 255));
        txIdPNH.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Phiếu nhập"));

        txIdSpNh.setBorder(javax.swing.BorderFactory.createTitledBorder("ID Sản phẩm"));
        txIdSpNh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txIdSpNhActionPerformed(evt);
            }
        });

        txDongiaNh.setBorder(javax.swing.BorderFactory.createTitledBorder("Đơn giá"));
        txDongiaNh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txDongiaNhActionPerformed(evt);
            }
        });

        txSoluongNh.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Số lượng")));

        txTenspNh.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên sản phẩm"));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/Delete.png"))); // NOI18N
        jButton7.setText("Xóa");
        jButton7.setPreferredSize(new java.awt.Dimension(110, 40));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btnBhThem1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBhThem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_add_30px.png"))); // NOI18N
        btnBhThem1.setText("Thêm");
        btnBhThem1.setPreferredSize(new java.awt.Dimension(110, 40));
        btnBhThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBhThem1ActionPerformed(evt);
            }
        });

        jButton9.setText("Chọn sản phẩm");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Thêm sản phẩm mới");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("...");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelchitietnhaphangLayout = new javax.swing.GroupLayout(Panelchitietnhaphang);
        Panelchitietnhaphang.setLayout(PanelchitietnhaphangLayout);
        PanelchitietnhaphangLayout.setHorizontalGroup(
            PanelchitietnhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelchitietnhaphangLayout.createSequentialGroup()
                .addGroup(PanelchitietnhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelchitietnhaphangLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(PanelchitietnhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txIdNvNh)
                            .addComponent(txIdPNH)
                            .addGroup(PanelchitietnhaphangLayout.createSequentialGroup()
                                .addComponent(txIdNccNh, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59)
                        .addGroup(PanelchitietnhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txIdSpNh, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txDongiaNh, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txTenspNh, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelchitietnhaphangLayout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(btnBhThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(PanelchitietnhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelchitietnhaphangLayout.createSequentialGroup()
                        .addGroup(PanelchitietnhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txSoluongNh)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                        .addGap(71, 71, 71)
                        .addComponent(txTongNh, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 22, Short.MAX_VALUE))
            .addGroup(PanelchitietnhaphangLayout.createSequentialGroup()
                .addGap(359, 359, 359)
                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelchitietnhaphangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelchitietnhaphangLayout.setVerticalGroup(
            PanelchitietnhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelchitietnhaphangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(PanelchitietnhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelchitietnhaphangLayout.createSequentialGroup()
                        .addGroup(PanelchitietnhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelchitietnhaphangLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addGroup(PanelchitietnhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txIdPNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txIdSpNh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelchitietnhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txIdNvNh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txTenspNh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PanelchitietnhaphangLayout.createSequentialGroup()
                                .addGap(285, 285, 285)
                                .addComponent(txTongNh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelchitietnhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelchitietnhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txIdNccNh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txSoluongNh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txDongiaNh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(PanelchitietnhaphangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBhThem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelchitietnhaphangLayout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout panelNHLayout = new javax.swing.GroupLayout(panelNH);
        panelNH.setLayout(panelNHLayout);
        panelNHLayout.setHorizontalGroup(
            panelNHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panelchitietnhaphang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelNHLayout.setVerticalGroup(
            panelNHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panelchitietnhaphang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelPNH.setBackground(new java.awt.Color(255, 255, 255));

        jButton51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/editicon.png"))); // NOI18N
        jButton51.setText("IN Phiếu nhập");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        jButton52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_ms_excel_30px.png"))); // NOI18N
        jButton52.setText("Xuất Excel ");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "ID Phiếu nhập", "ID Nhân viên", "ID Nhà cung cấp", " ", " ", " " }));

        txtFindpnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindpnhActionPerformed(evt);
            }
        });
        txtFindpnh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFindpnhKeyPressed(evt);
            }
        });

        jButton12.setText("Lọc");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Bỏ lọc");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel11.setText("Từ");

        jLabel12.setText("Đến");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtFindpnh, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(txtngaybatdaupnh, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(txtngayktpn, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jButton12)
                .addGap(18, 18, 18)
                .addComponent(jButton13)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton12)
                        .addComponent(jButton13))
                    .addComponent(txtngayktpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFindpnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtngaybatdaupnh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tbluppnh.setAutoCreateRowSorter(true);
        tbluppnh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbluppnh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID Phiếu nhập", "ID Nhà cung cấp", "ID Nhân viên", "Ngày lập", "Giờ lập", "Tổng hóa đơn"
            }
        ));
        tbluppnh.setRowHeight(30);
        tbluppnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbluppnhMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tbluppnh);

        jButton53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_data_backup_30px.png"))); // NOI18N
        jButton53.setText("Cập nhập dữ liệu");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });

        tbldownctpn.setAutoCreateRowSorter(true);
        tbldownctpn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Phiếu nhập", "ID sản phẩm", "Số lượng nhập", "Đơn giá nhập", "Thành tiền"
            }
        ));
        tbldownctpn.setRowHeight(30);
        jScrollPane9.setViewportView(tbldownctpn);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Chi Tiết Phiếu Nhập");
        jLabel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout panelPNHLayout = new javax.swing.GroupLayout(panelPNH);
        panelPNH.setLayout(panelPNHLayout);
        panelPNHLayout.setHorizontalGroup(
            panelPNHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPNHLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelPNHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelPNHLayout.createSequentialGroup()
                        .addGroup(panelPNHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane15))
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane9)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        panelPNHLayout.setVerticalGroup(
            panelPNHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPNHLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelPNHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPNHLayout.createSequentialGroup()
                        .addComponent(jButton52)
                        .addGap(18, 18, 18)
                        .addComponent(jButton51)
                        .addGap(18, 18, 18)
                        .addComponent(jButton53))
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelHienthiLayout = new javax.swing.GroupLayout(PanelHienthi);
        PanelHienthi.setLayout(PanelHienthiLayout);
        PanelHienthiLayout.setHorizontalGroup(
            PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHienthiLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelNV, javax.swing.GroupLayout.PREFERRED_SIZE, 1048, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 37, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 1048, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 37, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 1048, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 37, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelKM, javax.swing.GroupLayout.PREFERRED_SIZE, 1045, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 40, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelLSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 28, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelSP, javax.swing.GroupLayout.PREFERRED_SIZE, 1028, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 30, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelThongke, javax.swing.GroupLayout.PREFERRED_SIZE, 1048, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelCaidat, javax.swing.GroupLayout.PREFERRED_SIZE, 1048, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 128, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelPNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 28, Short.MAX_VALUE)))
        );
        PanelHienthiLayout.setVerticalGroup(
            PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelNV, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelNCC, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelTaikhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelKM, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelLSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 46, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 46, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelSP, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 34, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelThongke, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelCaidat, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 37, Short.MAX_VALUE)))
            .addGroup(PanelHienthiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelHienthiLayout.createSequentialGroup()
                    .addComponent(panelPNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 46, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout HethongLayout = new javax.swing.GroupLayout(Hethong);
        Hethong.setLayout(HethongLayout);
        HethongLayout.setHorizontalGroup(
            HethongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HethongLayout.createSequentialGroup()
                .addComponent(PanelMenuht, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelHienthi, javax.swing.GroupLayout.PREFERRED_SIZE, 1021, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        HethongLayout.setVerticalGroup(
            HethongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelHienthi, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
            .addGroup(HethongLayout.createSequentialGroup()
                .addComponent(PanelMenuht, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelHeader.setBackground(new java.awt.Color(37, 187, 219));
        PanelHeader.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                PanelHeaderMouseDragged(evt);
            }
        });
        PanelHeader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PanelHeaderMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                PanelHeaderMouseReleased(evt);
            }
        });

        txtHeader.setBackground(new java.awt.Color(37, 187, 219));
        txtHeader.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtHeader.setForeground(new java.awt.Color(255, 255, 255));
        txtHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtHeader.setText("QUẢN LÍ BÁN HÀNG");
        txtHeader.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                txtHeaderMouseDragged(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/icons8_cancel_30px_1.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/framedesign/hidePass.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelHeaderLayout = new javax.swing.GroupLayout(PanelHeader);
        PanelHeader.setLayout(PanelHeaderLayout);
        PanelHeaderLayout.setHorizontalGroup(
            PanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHeaderLayout.createSequentialGroup()
                .addContainerGap(641, Short.MAX_VALUE)
                .addComponent(txtHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(312, 312, 312)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5))
        );
        PanelHeaderLayout.setVerticalGroup(
            PanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtHeader)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Hethong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(PanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Hethong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (txdiachiSuanv.getText().length() == 0 || txsdtSuanv.getText().length() == 0 || txluongSuanv.getText().length() == 0 || txtenSuanv.getText().length() == 0 || txhoSuanv.getText().length() == 0 || txtuoiSuanv.getText().length() == 0 || themkh.getSelection() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
        } else {
            NhanvienDTO nv = new NhanvienDTO();
            nv.setIdNhanvien(Integer.parseInt(txidSuanv.getText()));
            nv.setChucvuNhanvien((String) cbbchucvuSuanv.getSelectedItem());
            nv.setTrangthai((String) cbTrangthainv.getSelectedItem());
            nv.setTenNhanvien(txtenSuanv.getText());
            nv.setHoNhanvien(txhoSuanv.getText());
            nv.setGioitinh(checkgender(rdNamSuanv, rdNuSuanv));

            nv.setDiaChiNhanvien(txdiachiSuanv.getText());

            int age1 = (Integer.parseInt(txtuoiSuanv.getText()));
            int number1 = (Integer.parseInt(txluongSuanv.getText()));

            if (age1 > 0) {
                nv.setTuoiNhanvien(Integer.parseInt(txtuoiSuanv.getText()));
                if (number1 > 0) {
                    nv.setLuongNhanvien(Integer.parseInt(txluongSuanv.getText()));
                    boolean phone1;

                    String checknumberphone1 = "^[0]\\d{9}$";
                    String intput1 = txsdtSuanv.getText();
                    phone1 = intput1.matches(checknumberphone1);
                    if (!phone1) {
                        JOptionPane.showMessageDialog(null, "Số điện thoại không họp lệ");

                    } else {
                        nv.setSdtNhanvien(txsdtSuanv.getText());
                        if (new NhanvienBUS().suaNV(nv)) {
                            int i = 0;
                            for (NhanvienDTO nvc : listNV) {
                                i++;
                                if (nv.getIdNhanvien() == nvc.getIdNhanvien()) {
                                    break;
                                }

                            }
                            listNV.set(i - 1, nv);
                            modelnv.setRowCount(0);
                            tblnv.setModel(modelnv);
                            showNV(listNV);
                            JOptionPane.showMessageDialog(null, "Sửa nhân viên thành công");
                            editNhanvien.dispose();

                        } else {
                            JOptionPane.showMessageDialog(null, "Sửa nhân viên thất bại");
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Nhập lương sai");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Số tuổi quá nhỏ");
            }

        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void rdNamSuanvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNamSuanvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNamSuanvActionPerformed

    private void btnExitSuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitSuaNVActionPerformed
        // TODO add your handling code here:
        editNhanvien.dispose();
    }//GEN-LAST:event_btnExitSuaNVActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:
        txhoSuanv.setText("");
        txtenSuanv.setText("");
        txluongSuanv.setText("");
        txsdtSuanv.setText("");
        txdiachiSuanv.setText("");
        txtuoiSuanv.setText("");
        themkh.clearSelection();

    }//GEN-LAST:event_btnDelActionPerformed

    private void lbAcceptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAcceptMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_lbAcceptMouseClicked

    private void btnDangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangxuatActionPerformed
        // TODO add your handling code here:
        int i = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn đóng tài khoản");
        if (i == JOptionPane.YES_OPTION) {
            dangnhap dn = new dangnhap();
            dn.setVisible(true);
            this.dispose();
        } else {
            return;
        }
    }//GEN-LAST:event_btnDangxuatActionPerformed

    private void labelNhacungcapMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNhacungcapMouseExited
        // TODO add your handling code here:
        Color s = new Color(102, 102, 109);
        if (qlnccLeft.getBackground().toString().equals(s.toString())) {
            qlnccLeft.setBackground(null);
            PanelNhacungcap.setBackground(null);
        }
    }//GEN-LAST:event_labelNhacungcapMouseExited

    private void labelNhacungcapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNhacungcapMouseEntered
        // TODO add your handling code here:
        Color s = new Color(102, 102, 102);
        if (qlnccLeft.getBackground().toString().equals(s.toString())) {

        } else {
            qlnccLeft.setBackground(new Color(102, 102, 109));
            PanelNhacungcap.setBackground(new Color(37, 171, 192));
        }
    }//GEN-LAST:event_labelNhacungcapMouseEntered

    private void labelNhacungcapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNhacungcapMouseClicked
        // TODO add your handling code here:
        txtHeader.setText("QUẢN LÍ NHÀ CUNG CẤP");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelNCC);
        PanelHienthi.repaint();
        PanelBanhang.setBackground(null);
        qlbhLeft.setBackground(null);
        qlkhLeft.setBackground(null);
        qlnhLeft.setBackground(null);
        qlthongkeLeft.setBackground(null);
        qlhdLeft.setBackground(null);
        qllspLeft.setBackground(null);
        qltaikhoanLeft.setBackground(null);
        qlpnhLeft.setBackground(null);
        qlnccLeft.setBackground(new Color(102, 102, 102));
        qlnvLeft.setBackground(null);
        qlspLeft.setBackground(null);
        qlkhuyenmaiLeft.setBackground(null);
        PanelKhuyenmai.setBackground(null);
        PanelThongke.setBackground(null);
        PanelKhachhang.setBackground(null);
        PanelNhaphang.setBackground(null);
        PanelLoaisanpham.setBackground(null);
        PanelTaikhoan.setBackground(null);
        PanelBanhang.setBackground(null);
        PanelHoadon.setBackground(null);
        PanelNhacungcap.setBackground(new Color(37, 171, 191));
        PanelNhanvien.setBackground(null);
        PanelPhieunhaphang.setBackground(null);
        PanelSanpham.setBackground(null);

    }//GEN-LAST:event_labelNhacungcapMouseClicked

    private void PanelNhanvienMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelNhanvienMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_PanelNhanvienMouseEntered

    private void labelNhanvienMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNhanvienMouseExited
        // TODO add your handling code here:
        Color s = new Color(102, 102, 109);
        if (qlnvLeft.getBackground().toString().equals(s.toString())) {
            qlnvLeft.setBackground(null);
            PanelNhanvien.setBackground(null);
        }
    }//GEN-LAST:event_labelNhanvienMouseExited

    private void labelNhanvienMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNhanvienMouseEntered
        // TODO add your handling code here:
        Color s = new Color(102, 102, 102);
        if (qlnvLeft.getBackground().toString().equals(s.toString())) {

        } else {
            qlnvLeft.setBackground(new Color(102, 102, 109));
            PanelNhanvien.setBackground(new Color(37, 171, 192));
        }
    }//GEN-LAST:event_labelNhanvienMouseEntered

    private void labelNhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNhanvienMouseClicked
        // TODO add your handling code here:
        txtHeader.setText("QUẢN LÍ NHÂN VIÊN");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelNV);
        PanelHienthi.repaint();
        PanelBanhang.setBackground(null);
        qlbhLeft.setBackground(null);
        qlkhLeft.setBackground(null);
        qlnhLeft.setBackground(null);
        qlthongkeLeft.setBackground(null);
        qlhdLeft.setBackground(null);
        qllspLeft.setBackground(null);
        qlpnhLeft.setBackground(null);
        qltaikhoanLeft.setBackground(null);
        qlnccLeft.setBackground(null);
        qlnvLeft.setBackground(new Color(102, 102, 102));
        qlspLeft.setBackground(null);
        qlkhuyenmaiLeft.setBackground(null);
        PanelKhuyenmai.setBackground(null);
        PanelThongke.setBackground(null);
        PanelKhachhang.setBackground(null);
        PanelNhaphang.setBackground(null);
        PanelLoaisanpham.setBackground(null);
        PanelTaikhoan.setBackground(null);
        PanelBanhang.setBackground(null);
        PanelHoadon.setBackground(null);
        PanelNhacungcap.setBackground(null);
        PanelNhanvien.setBackground(new Color(37, 171, 191));
        PanelPhieunhaphang.setBackground(null);
        PanelSanpham.setBackground(null);
    }//GEN-LAST:event_labelNhanvienMouseClicked

    private void lbkhachhangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbkhachhangMouseExited
        // TODO add your handling code here:
        Color s = new Color(102, 102, 109);
        if (qlkhLeft.getBackground().toString().equals(s.toString())) {
            qlkhLeft.setBackground(null);
            PanelKhachhang.setBackground(null);
        }
    }//GEN-LAST:event_lbkhachhangMouseExited

    private void lbkhachhangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbkhachhangMouseEntered
        // TODO add your handling code here:
        Color s = new Color(102, 102, 102);
        if (qlkhLeft.getBackground().toString().equals(s.toString())) {

        } else {
            qlkhLeft.setBackground(new Color(102, 102, 109));
            PanelKhachhang.setBackground(new Color(37, 171, 192));
        }
    }//GEN-LAST:event_lbkhachhangMouseEntered

    private void lbkhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbkhachhangMouseClicked
        // TODO add your handling code here:
        txtHeader.setText("QUẢN LÍ KHÁCH HÀNG");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelKH);
        PanelHienthi.repaint();
        PanelBanhang.setBackground(null);
        qlbhLeft.setBackground(null);
        qlkhLeft.setBackground(new Color(102, 102, 102));
        qlnhLeft.setBackground(null);
        qlthongkeLeft.setBackground(null);
        qlhdLeft.setBackground(null);
        qllspLeft.setBackground(null);
        qltaikhoanLeft.setBackground(null);
        qlnccLeft.setBackground(null);
        qlnvLeft.setBackground(null);
        qlspLeft.setBackground(null);
        qlpnhLeft.setBackground(null);
        qlkhuyenmaiLeft.setBackground(null);
        PanelKhuyenmai.setBackground(null);
        PanelThongke.setBackground(null);
        PanelKhachhang.setBackground(new Color(37, 171, 191));
        PanelNhaphang.setBackground(null);
        PanelLoaisanpham.setBackground(null);
        PanelTaikhoan.setBackground(null);
        PanelBanhang.setBackground(null);
        PanelHoadon.setBackground(null);
        PanelNhacungcap.setBackground(null);
        PanelNhanvien.setBackground(null);
        PanelPhieunhaphang.setBackground(null);
        PanelSanpham.setBackground(null);
    }//GEN-LAST:event_lbkhachhangMouseClicked

    private void lbqltkMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbqltkMouseExited
        // TODO add your handling code here:
        Color s = new Color(102, 102, 109);
        if (qltaikhoanLeft.getBackground().toString().equals(s.toString())) {
            qltaikhoanLeft.setBackground(null);
            PanelTaikhoan.setBackground(null);
        }
    }//GEN-LAST:event_lbqltkMouseExited

    private void lbqltkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbqltkMouseEntered
        // TODO add your handling code here:
        Color s = new Color(102, 102, 102);
        if (qltaikhoanLeft.getBackground().toString().equals(s.toString())) {

        } else {
            qltaikhoanLeft.setBackground(new Color(102, 102, 109));
            PanelTaikhoan.setBackground(new Color(37, 171, 192));
        }
    }//GEN-LAST:event_lbqltkMouseEntered

    private void lbqltkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbqltkMouseClicked
        // TODO add your handling code here:
        txtHeader.setText("QUẢN LÍ TÀI KHOẢN");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelTaikhoan);
        PanelHienthi.repaint();
        PanelBanhang.setBackground(null);
        qlbhLeft.setBackground(null);
        qlkhLeft.setBackground(null);
        qlnhLeft.setBackground(null);
        qlthongkeLeft.setBackground(null);
        qlhdLeft.setBackground(null);
        qlpnhLeft.setBackground(null);
        qllspLeft.setBackground(null);
        qltaikhoanLeft.setBackground(new Color(102, 102, 102));
        qlnccLeft.setBackground(null);
        qlnvLeft.setBackground(null);
        qlspLeft.setBackground(null);
        qlkhuyenmaiLeft.setBackground(null);
        PanelKhuyenmai.setBackground(null);
        PanelThongke.setBackground(null);
        PanelKhachhang.setBackground(null);
        PanelNhaphang.setBackground(null);
        PanelLoaisanpham.setBackground(null);
        PanelTaikhoan.setBackground(new Color(37, 171, 191));
        PanelBanhang.setBackground(null);
        PanelHoadon.setBackground(null);
        PanelNhacungcap.setBackground(null);
        PanelNhanvien.setBackground(null);
        PanelPhieunhaphang.setBackground(null);
        PanelSanpham.setBackground(null);
    }//GEN-LAST:event_lbqltkMouseClicked

    private void labelnhMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelnhMouseExited
        // TODO add your handling code here:
        Color s = new Color(102, 102, 109);
        if (qlnhLeft.getBackground().toString().equals(s.toString())) {
            qlnhLeft.setBackground(null);
            PanelNhaphang.setBackground(null);
        }

    }//GEN-LAST:event_labelnhMouseExited

    private void labelnhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelnhMouseEntered
        // TODO add your handling code here:
        Color s = new Color(102, 102, 102);
        if (qlnhLeft.getBackground().toString().equals(s.toString())) {

        } else {
            qlnhLeft.setBackground(new Color(102, 102, 109));
            PanelNhaphang.setBackground(new Color(37, 171, 192));
        }
    }//GEN-LAST:event_labelnhMouseEntered

    private void labelnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelnhMouseClicked
        // TODO add your handling code here:
        txtHeader.setText("NHẬP HÀNG");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelNH);
        PanelHienthi.repaint();
        PanelBanhang.setBackground(null);
        qlbhLeft.setBackground(null);
        qlkhLeft.setBackground(null);
        qlnhLeft.setBackground(new Color(102, 102, 102));
        qlthongkeLeft.setBackground(null);
        qlhdLeft.setBackground(null);
        qllspLeft.setBackground(null);
        qltaikhoanLeft.setBackground(null);
        qlnccLeft.setBackground(null);
        qlnvLeft.setBackground(null);
        qlpnhLeft.setBackground(null);
        qlspLeft.setBackground(null);
        qlkhuyenmaiLeft.setBackground(null);
        PanelKhuyenmai.setBackground(null);
        PanelThongke.setBackground(null);
        PanelKhachhang.setBackground(null);
        PanelNhaphang.setBackground(new Color(37, 171, 191));
        PanelLoaisanpham.setBackground(null);
        PanelTaikhoan.setBackground(null);
        PanelBanhang.setBackground(null);
        PanelHoadon.setBackground(null);
        PanelNhacungcap.setBackground(null);
        PanelNhanvien.setBackground(null);
        PanelPhieunhaphang.setBackground(null);
        PanelSanpham.setBackground(null);

    }//GEN-LAST:event_labelnhMouseClicked

    private void labelthongkeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelthongkeMouseExited
        // TODO add your handling code here:
        Color s = new Color(102, 102, 109);
        if (qlthongkeLeft.getBackground().toString().equals(s.toString())) {
            qlthongkeLeft.setBackground(null);
            PanelThongke.setBackground(null);
        }
    }//GEN-LAST:event_labelthongkeMouseExited

    private void labelthongkeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelthongkeMouseEntered
        // TODO add your handling code here:
        Color s = new Color(102, 102, 102);
        if (qlthongkeLeft.getBackground().toString().equals(s.toString())) {

        } else {
            qlthongkeLeft.setBackground(new Color(102, 102, 109));
            PanelThongke.setBackground(new Color(37, 171, 192));
        }
    }//GEN-LAST:event_labelthongkeMouseEntered

    private void labelthongkeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelthongkeMouseClicked
        // TODO add your handling code here:
        txtHeader.setText("QUẢN LÍ THỐNG KÊ");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelThongke);
        PanelHienthi.repaint();
        PanelBanhang.setBackground(null);
        qlbhLeft.setBackground(null);
        qlkhLeft.setBackground(null);
        qlnhLeft.setBackground(null);
        qlthongkeLeft.setBackground(new Color(102, 102, 102));
        qlhdLeft.setBackground(null);
        qllspLeft.setBackground(null);
        qltaikhoanLeft.setBackground(null);
        qlnccLeft.setBackground(null);
        qlpnhLeft.setBackground(null);
        qlnvLeft.setBackground(null);
        qlspLeft.setBackground(null);
        qlkhuyenmaiLeft.setBackground(null);
        PanelKhuyenmai.setBackground(null);
        PanelThongke.setBackground(new Color(37, 171, 191));
        PanelKhachhang.setBackground(null);
        PanelNhaphang.setBackground(null);
        PanelLoaisanpham.setBackground(null);
        PanelTaikhoan.setBackground(null);
        PanelBanhang.setBackground(null);
        PanelHoadon.setBackground(null);
        PanelNhacungcap.setBackground(null);
        PanelNhanvien.setBackground(null);
        PanelPhieunhaphang.setBackground(null);
        PanelSanpham.setBackground(null);

    }//GEN-LAST:event_labelthongkeMouseClicked

    private void labelqlhdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelqlhdMouseExited
        // TODO add your handling code here:
        Color s = new Color(102, 102, 109);
        if (qlhdLeft.getBackground().toString().equals(s.toString())) {
            qlhdLeft.setBackground(null);
            PanelHoadon.setBackground(null);
        }

    }//GEN-LAST:event_labelqlhdMouseExited

    private void labelqlhdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelqlhdMouseEntered
        // TODO add your handling code here:
        Color s = new Color(102, 102, 102);
        if (qlhdLeft.getBackground().toString().equals(s.toString())) {

        } else {
            qlhdLeft.setBackground(new Color(102, 102, 109));
            PanelHoadon.setBackground(new Color(37, 171, 192));
        }
    }//GEN-LAST:event_labelqlhdMouseEntered

    private void labelqlhdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelqlhdMouseClicked
        // TODO add your handling code here:
        txtHeader.setText("QUẢN LÍ HÓA ĐƠN");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelHD);
        PanelHienthi.repaint();
        PanelBanhang.setBackground(null);
        qlbhLeft.setBackground(null);
        qlkhLeft.setBackground(null);
        qlnhLeft.setBackground(null);
        qlthongkeLeft.setBackground(null);
        qlhdLeft.setBackground(new Color(102, 102, 102));
        qllspLeft.setBackground(null);
        qltaikhoanLeft.setBackground(null);
        qlnccLeft.setBackground(null);
        qlpnhLeft.setBackground(null);
        qlnvLeft.setBackground(null);
        qlspLeft.setBackground(null);
        qlkhuyenmaiLeft.setBackground(null);
        PanelKhuyenmai.setBackground(null);
        PanelThongke.setBackground(null);
        PanelKhachhang.setBackground(null);
        PanelNhaphang.setBackground(null);
        PanelLoaisanpham.setBackground(null);
        PanelTaikhoan.setBackground(null);
        PanelBanhang.setBackground(null);
        PanelHoadon.setBackground(new Color(37, 171, 191));
        PanelNhacungcap.setBackground(null);
        PanelNhanvien.setBackground(null);
        PanelPhieunhaphang.setBackground(null);
        PanelSanpham.setBackground(null);
    }//GEN-LAST:event_labelqlhdMouseClicked

    private void LabelspMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelspMouseExited
        // TODO add your handling code here:
        Color s = new Color(102, 102, 109);
        if (qlspLeft.getBackground().toString().equals(s.toString())) {
            qlspLeft.setBackground(null);
            PanelSanpham.setBackground(null);
        }
    }//GEN-LAST:event_LabelspMouseExited

    private void LabelspMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelspMouseEntered
        // TODO add your handling code here:
        Color s = new Color(102, 102, 102);
        if (qlspLeft.getBackground().toString().equals(s.toString())) {

        } else {
            qlspLeft.setBackground(new Color(102, 102, 109));
            PanelSanpham.setBackground(new Color(37, 171, 192));
        }
    }//GEN-LAST:event_LabelspMouseEntered

    private void LabelspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelspMouseClicked
        // TODO add your handling code here:
//        PanelHienthi.removeAll();
//        PanelHienthi.add(panelSP);
//        PanelHienthi.repaint();

        //             **************'
        txtHeader.setText("QUẢN LÍ SẢN PHẨM");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelSP);
        PanelHienthi.repaint();
        qlbhLeft.setBackground(null);
        qlkhLeft.setBackground(null);
        qlnhLeft.setBackground(null);
        qlthongkeLeft.setBackground(null);
        qlhdLeft.setBackground(null);
        qllspLeft.setBackground(null);
        qltaikhoanLeft.setBackground(null);
        qlnccLeft.setBackground(null);
        qlnvLeft.setBackground(null);
        qlpnhLeft.setBackground(null);

        qlspLeft.setBackground(new Color(102, 102, 102));
        qlkhuyenmaiLeft.setBackground(null);
        PanelKhuyenmai.setBackground(null);
        PanelThongke.setBackground(null);
        PanelKhachhang.setBackground(null);
        PanelNhaphang.setBackground(null);
        PanelLoaisanpham.setBackground(null);
        PanelTaikhoan.setBackground(null);
        PanelBanhang.setBackground(null);
        PanelHoadon.setBackground(null);
        PanelNhacungcap.setBackground(null);
        PanelNhanvien.setBackground(null);
        PanelPhieunhaphang.setBackground(null);
        PanelSanpham.setBackground(new Color(37, 171, 191));
    }//GEN-LAST:event_LabelspMouseClicked

    private void labelLoaisanphamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLoaisanphamMouseExited
        // TODO add your handling code here:
        Color s = new Color(102, 102, 109);
        if (qllspLeft.getBackground().toString().equals(s.toString())) {
            qllspLeft.setBackground(null);
            PanelLoaisanpham.setBackground(null);
        }
    }//GEN-LAST:event_labelLoaisanphamMouseExited

    private void labelLoaisanphamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLoaisanphamMouseEntered
        // TODO add your handling code here:
        Color s = new Color(102, 102, 102);
        if (qllspLeft.getBackground().toString().equals(s.toString())) {

        } else {
            qllspLeft.setBackground(new Color(102, 102, 109));
            PanelLoaisanpham.setBackground(new Color(37, 171, 192));
        }
    }//GEN-LAST:event_labelLoaisanphamMouseEntered

    private void labelLoaisanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLoaisanphamMouseClicked
        // TODO add your handling code here:
        txtHeader.setText("QUẢN LÍ LOẠI SẢN PHẨM");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelLSP);
        PanelHienthi.repaint();
        PanelBanhang.setBackground(null);
        qlbhLeft.setBackground(null);
        qlkhLeft.setBackground(null);
        qlnhLeft.setBackground(null);
        qlthongkeLeft.setBackground(null);
        qlhdLeft.setBackground(null);
        qllspLeft.setBackground(new Color(102, 102, 102));
        qltaikhoanLeft.setBackground(null);
        qlnccLeft.setBackground(null);
        qlpnhLeft.setBackground(null);
        qlnvLeft.setBackground(null);
        qlspLeft.setBackground(null);
        qlkhuyenmaiLeft.setBackground(null);
        PanelKhuyenmai.setBackground(null);
        PanelThongke.setBackground(null);
        PanelKhachhang.setBackground(null);
        PanelNhaphang.setBackground(null);
        PanelLoaisanpham.setBackground(new Color(37, 171, 191));
        PanelTaikhoan.setBackground(null);
        PanelBanhang.setBackground(null);
        PanelHoadon.setBackground(null);
        PanelNhacungcap.setBackground(null);
        PanelNhanvien.setBackground(null);
        PanelPhieunhaphang.setBackground(null);
        PanelSanpham.setBackground(null);
    }//GEN-LAST:event_labelLoaisanphamMouseClicked

    private void labelBanhangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBanhangMouseExited
        // TODO add your handling code here:
        Color s = new Color(102, 102, 109);
        if (qlbhLeft.getBackground().toString().equals(s.toString())) {
            qlbhLeft.setBackground(null);
            PanelBanhang.setBackground(null);
        }
    }//GEN-LAST:event_labelBanhangMouseExited

    private void labelBanhangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBanhangMouseEntered
        // TODO add your handling code here:
        Color s = new Color(102, 102, 102);
        if (qlbhLeft.getBackground().toString().equals(s.toString())) {

        } else {
            qlbhLeft.setBackground(new Color(102, 102, 109));
            PanelBanhang.setBackground(new Color(37, 171, 192));
        }
    }//GEN-LAST:event_labelBanhangMouseEntered

    private void labelBanhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBanhangMouseClicked
        // TODO add your handling code here:
//        panelHienthi.removeAll();
//        panelHienthi.add(panelBH);
//        panelHienthi.repaint();
        txtHeader.setText("BÁN HÀNG");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelBH);
        PanelHienthi.repaint();
        qlbhLeft.setBackground(new Color(102, 102, 102));
        qlkhLeft.setBackground(null);
        qlnhLeft.setBackground(null);
        qlthongkeLeft.setBackground(null);
        qlhdLeft.setBackground(null);
        qllspLeft.setBackground(null);
        qltaikhoanLeft.setBackground(null);
        qlnccLeft.setBackground(null);
        qlnvLeft.setBackground(null);
        qlpnhLeft.setBackground(null);
        qlspLeft.setBackground(null);
        qlkhuyenmaiLeft.setBackground(null);
        PanelKhuyenmai.setBackground(null);
        PanelThongke.setBackground(null);
        PanelKhachhang.setBackground(null);
        PanelNhaphang.setBackground(null);
        PanelLoaisanpham.setBackground(null);
        PanelTaikhoan.setBackground(null);
        PanelBanhang.setBackground(new Color(37, 171, 191));
        PanelHoadon.setBackground(null);
        PanelNhacungcap.setBackground(null);
        PanelNhanvien.setBackground(null);
        PanelPhieunhaphang.setBackground(null);
        PanelSanpham.setBackground(null);
    }//GEN-LAST:event_labelBanhangMouseClicked

    private void labelPhieunhaphangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPhieunhaphangMouseExited
        // TODO add your handling code here:
        Color s = new Color(102, 102, 109);
        if (qlpnhLeft.getBackground().toString().equals(s.toString())) {
            qlpnhLeft.setBackground(null);
            PanelPhieunhaphang.setBackground(null);
        }
    }//GEN-LAST:event_labelPhieunhaphangMouseExited

    private void labelPhieunhaphangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPhieunhaphangMouseEntered
        // TODO add your handling code here:
        Color s = new Color(102, 102, 102);
        if (qlpnhLeft.getBackground().toString().equals(s.toString())) {

        } else {
            qlpnhLeft.setBackground(new Color(102, 102, 109));
            PanelPhieunhaphang.setBackground(new Color(37, 171, 192));
        }
    }//GEN-LAST:event_labelPhieunhaphangMouseEntered

    private void labelPhieunhaphangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPhieunhaphangMouseClicked
        // TODO add your handling code here:
        txtHeader.setText("QUẢN LÍ PHIẾU NHẬP HÀNG");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelPNH);
        PanelHienthi.repaint();
        PanelBanhang.setBackground(null);
        qlbhLeft.setBackground(null);
        qlkhLeft.setBackground(null);
        qlnhLeft.setBackground(null);
        qlthongkeLeft.setBackground(null);
        qlhdLeft.setBackground(null);
        qllspLeft.setBackground(null);
        qltaikhoanLeft.setBackground(null);
        qlnccLeft.setBackground(null);
        qlnvLeft.setBackground(null);
        qlspLeft.setBackground(null);
        qlpnhLeft.setBackground(new Color(102, 102, 102));
        qlkhuyenmaiLeft.setBackground(null);
        PanelKhuyenmai.setBackground(null);
        PanelThongke.setBackground(null);
        PanelKhachhang.setBackground(null);
        PanelNhaphang.setBackground(null);
        PanelLoaisanpham.setBackground(null);
        PanelTaikhoan.setBackground(null);
        PanelBanhang.setBackground(null);
        PanelHoadon.setBackground(null);
        PanelNhacungcap.setBackground(null);
        PanelNhanvien.setBackground(null);
        PanelPhieunhaphang.setBackground(new Color(37, 171, 191));
        PanelSanpham.setBackground(null);
    }//GEN-LAST:event_labelPhieunhaphangMouseClicked

    private void labelkhuyenmaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelkhuyenmaiMouseClicked
        // TODO add your handling code here:
        txtHeader.setText("QUẢN LÍ KHUYẾN MÃI");
        PanelHienthi.removeAll();
        PanelHienthi.add(panelKM);
        PanelHienthi.repaint();
        PanelBanhang.setBackground(null);
        qlbhLeft.setBackground(null);
        qlkhLeft.setBackground(null);
        qlnhLeft.setBackground(null);
        qlthongkeLeft.setBackground(null);
        qlhdLeft.setBackground(null);
        qllspLeft.setBackground(null);
        qltaikhoanLeft.setBackground(null);
        qlnccLeft.setBackground(null);
        qlnvLeft.setBackground(null);
        qlspLeft.setBackground(null);
        qlpnhLeft.setBackground(null);
        qlkhuyenmaiLeft.setBackground(new Color(102, 102, 102));
        PanelThongke.setBackground(null);
        PanelKhachhang.setBackground(null);
        PanelNhaphang.setBackground(null);
        PanelLoaisanpham.setBackground(null);
        PanelTaikhoan.setBackground(null);
        PanelBanhang.setBackground(null);
        PanelHoadon.setBackground(null);
        PanelNhacungcap.setBackground(null);
        PanelNhanvien.setBackground(null);
        PanelPhieunhaphang.setBackground(null);
        PanelKhuyenmai.setBackground(new Color(37, 171, 191));
        PanelSanpham.setBackground(null);
    }//GEN-LAST:event_labelkhuyenmaiMouseClicked

    private void labelkhuyenmaiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelkhuyenmaiMouseEntered
        // TODO add your handling code here:
        Color s = new Color(102, 102, 102);
        if (qlkhuyenmaiLeft.getBackground().toString().equals(s.toString())) {

        } else {
            qlkhuyenmaiLeft.setBackground(new Color(102, 102, 109));
            PanelKhuyenmai.setBackground(new Color(37, 171, 192));
        }
    }//GEN-LAST:event_labelkhuyenmaiMouseEntered

    private void labelkhuyenmaiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelkhuyenmaiMouseExited
        // TODO add your handling code here:
        Color s = new Color(102, 102, 109);
        if (qlkhuyenmaiLeft.getBackground().toString().equals(s.toString())) {
            qlkhuyenmaiLeft.setBackground(null);
            PanelKhuyenmai.setBackground(null);
        }
    }//GEN-LAST:event_labelkhuyenmaiMouseExited

    private void txluongSuanvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txluongSuanvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txluongSuanvActionPerformed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        // TODO add your handling code here:
        try {

            NhanvienDTO nvDTO = new NhanvienDTO();

            if (txtidnvThemnv.getText().length() != 0 && txthoThemnv.getText().length() != 0 && txtluongThemnv.getText().length() != 0
                    && txttenThemnv.getText().length() != 0 && txttuoiThemnv.getText().length() != 0 && txtsdtThemnv.getText().length() != 0 && txtdiachiThemnv.getText().length() != 0)//xem thử đã nhập hay chưa(textflied)
            {
                nvDTO.setIdNhanvien(Integer.valueOf(txtidnvThemnv.getText()));
                nvDTO.setTenNhanvien(txttenThemnv.getText());
                nvDTO.setHoNhanvien(txthoThemnv.getText());

                nvDTO.setChucvuNhanvien((String) cbboxchucvuThemnv.getSelectedItem());

                nvDTO.setDiaChiNhanvien(txtdiachiThemnv.getText());
                nvDTO.setGioitinh(checkgender(rdNamThemnv, rdNuThemnv));
                nvDTO.setTrangthai("Hiện hành");
                // dien thoại

                int age = (Integer.parseInt(txttuoiThemnv.getText()));
                if (age > 0) {
                    nvDTO.setTuoiNhanvien(Integer.valueOf(txttuoiThemnv.getText()));

                    int number = (Integer.parseInt(txtluongThemnv.getText()));
                    if (number > 0) {
                        nvDTO.setLuongNhanvien(Integer.valueOf(txtluongThemnv.getText()));
                        boolean phone11;

                        String checknumberphone = "^[0]\\d{9}$";
                        String intput = txtsdtThemnv.getText();
                        phone11 = intput.matches(checknumberphone);
                        if (!phone11) {
                            JOptionPane.showMessageDialog(null, "Số điện thoại không họp lệ");

                        } else {
                            nvDTO.setSdtNhanvien(txtsdtThemnv.getText());
                            if (new NhanvienBUS().themNV(nvDTO)) {
                                modelnv.setRowCount(0);
                                tblnv.setModel(modelnv);
                                listNV.add(nvDTO);
                                showNV(listNV);
                                addNhanvien.dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Thêm thất bại");

                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Nhập sai lương");

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Số tuổi quá nhỏ");
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "vui lòng nhập đầy đủ thông tin");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Failed!");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void btnExitThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitThemNVActionPerformed
        // TODO add your handling code here:
        addNhanvien.dispose();
    }//GEN-LAST:event_btnExitThemNVActionPerformed

    private void btnDel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel1ActionPerformed
        // TODO add your handling code here:
        txtdiachiThemnv.setText("");
        txthoThemnv.setText("");
        txtluongThemnv.setText("");
        txtsdtThemnv.setText("");
        themkh.clearSelection();
        txttenThemnv.setText("");
        txttuoiThemnv.setText("");
    }//GEN-LAST:event_btnDel1ActionPerformed

    private void txtluongThemnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtluongThemnvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtluongThemnvActionPerformed

    private void rdNamThemnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNamThemnvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNamThemnvActionPerformed

    private void btnSaveKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveKhActionPerformed
        // TODO add your handling code here:
        if (txtTenkh.getText().equals("") || txtHokh.getText().equals("") || txtEmailkh.getText().equals("") || txtSdtkh.getText().equals("") || txtDiachikh.getText().equals("") || txtTuoiKh.getText().equals("") || checkgender(rdNamkh, rdNukh) == "") {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
        } else {
            KhachhangDTO kh = new KhachhangDTO();
            kh.setMaKhachhang(Integer.valueOf(txtIdkh.getText()));
            kh.setTenKhachhang(uper(txtTenkh.getText()));
            kh.setHoKhachhang(uper(txtHokh.getText()));
            kh.setDiaChi(txtDiachikh.getText());
            kh.setSdtKhachhang(txtSdtkh.getText());
            kh.setEmailKhachhang(txtEmailkh.getText());
            kh.setTuoiKhachhang(Integer.valueOf(txtTuoiKh.getText()));
            kh.setGioitinh(checkgender(rdNamkh, rdNukh));

            listKH.add(kh);
            if (khBus.themKH(kh)) {
                JOptionPane.showMessageDialog(addKhachhang, "Thêm mới thành công!");
                txBhIdkh.setText(txtTenkh.getText() + "(" + txtIdkh.getText() + ")");
                int makh = Integer.parseInt(txtIdkh.getText());
                khDTO = khBus.getKh(makh);
                addKhachhang.dispose();
                modelkh.setRowCount(0);
                showKH();
                addKhachhang.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
            }
        }


    }//GEN-LAST:event_btnSaveKhActionPerformed

    private void btnExitThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitThemKHActionPerformed
        // TODO add your handling code here:
        addKhachhang.dispose();
    }//GEN-LAST:event_btnExitThemKHActionPerformed

    private void btnResetAddKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetAddKhActionPerformed
        // TODO add your handling code here:
        txtTenkh.setText("");
        txtHokh.setText("");
        txtEmailkh.setText("");
        themkh.clearSelection();
        txtDiachikh.setText("");
        txtSdtkh.setText("");


    }//GEN-LAST:event_btnResetAddKhActionPerformed

    private void btnSave4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave4ActionPerformed
        // TODO add your handling code here:
        if (txIdAddSP.getText().length() == 0 || txTenAddSp.getText().length() == 0 || txGiaAddSp.getText().length() == 0 || txLspAddSp.getText().length() == 0 || txSoluongAddSp.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
        } else {
            try {
                SanphamDTO sp = new SanphamDTO();
                LoaisanphamDTO lsp = new LoaisanphamDTO();
                sp.setIdSanphamDTO(Integer.valueOf(txIdAddSP.getText()));
                sp.setTensanphamDTO(txTenAddSp.getText());
                sp.setDongiasanphamDTO(Integer.parseInt(txGiaAddSp.getText()));
                sp.setDonvitinhSanphamDTO((String) cbDvtAddSp.getSelectedItem());
                lsp.setIdLoaiSanphamDTO(Integer.valueOf(txLspAddSp.getText()));
                lsp.setTenLoaiSanphamDTO(String.valueOf(tblSelectLSP.getValueAt(tblSelectLSP.getSelectedRow(), 2)));
                sp.setIdLoaiSanphamDTO(lsp);
                sp.setTrangthaisanphamDTO((String) cbTrangthaiAddSp.getSelectedItem());
                sp.setSoluongsanphamDTO(Integer.parseInt(txSoluongAddSp.getText()));
                if (new SanphamBUS().themSP(sp)) {
                    listSP.add(sp);
                    modelsp.setRowCount(0);
                    tblsp.setModel(modelsp);
                    showSP(listSP);
                    addSanpham.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng thông số");
            }

        }


    }//GEN-LAST:event_btnSave4ActionPerformed

    private void btnExitThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitThemSPActionPerformed
        // TODO add your handling code here:
        addSanpham.dispose();
    }//GEN-LAST:event_btnExitThemSPActionPerformed

    private void btnDel4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel4ActionPerformed
        // TODO add your handling code here:
        txTenAddSp.setText("");
        txLspAddSp.setText("");
        txSoluongAddSp.setText("");
        txIdAddSP.setText("");
        txGiaAddSp.setText("");

    }//GEN-LAST:event_btnDel4ActionPerformed

    private void txSoluongAddSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txSoluongAddSpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txSoluongAddSpActionPerformed

    private void btnThemlspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemlspActionPerformed
        // TODO add your handling code here:
        if (txidThemlsp.getText().length() == 0 || txtenThemlsp.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
        } else {
            LoaisanphamDTO lsp = new LoaisanphamDTO();
            lsp.setIdLoaiSanphamDTO(Integer.valueOf(txidThemlsp.getText()));
            lsp.setTenLoaiSanphamDTO(txtenThemlsp.getText());
            if (new LoaisanphamBUS().themLSP(lsp)) {
                jButton42.doClick();
                addLoaisanpham.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm thất bại");
            }
        }

    }//GEN-LAST:event_btnThemlspActionPerformed

    private void btnresetThemlspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetThemlspActionPerformed
        // TODO add your handling code here:
        txidThemlsp.setText("");
        txtenThemlsp.setText("");

    }//GEN-LAST:event_btnresetThemlspActionPerformed

    private void btnhuyThemspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuyThemspActionPerformed
        // TODO add your handling code here:
        addLoaisanpham.dispose();
    }//GEN-LAST:event_btnhuyThemspActionPerformed

    private void btnSave7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave7ActionPerformed
        // TODO add your handling code here:
        if (txtenSualsp.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
        } else {
            LoaisanphamDTO lsp = new LoaisanphamDTO();
            lsp.setIdLoaiSanphamDTO(Integer.valueOf(txidlspSualsp.getText()));
            lsp.setTenLoaiSanphamDTO(txtenSualsp.getText());
            if (new LoaisanphamBUS().suaLSP(lsp)) {
                int index = 0;
                for (LoaisanphamDTO lsps : listLSP) {
                    index++;
                    if (lsps.getIdLoaiSanphamDTO() == lsp.getIdLoaiSanphamDTO()) {
                        break;
                    }
                }
                listLSP.set(index - 1, lsp);
                modellsp.setRowCount(0);
                tbllsp.setModel(modellsp);
                showLSP();
                editLoaisanpham.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "Sửa thất bại");
            }
        }
    }//GEN-LAST:event_btnSave7ActionPerformed

    private void btnExit8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExit8ActionPerformed

    private void btnExitSuaLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitSuaLSPActionPerformed
        // TODO add your handling code here:
        editLoaisanpham.dispose();
    }//GEN-LAST:event_btnExitSuaLSPActionPerformed

    private void btnSave9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave9ActionPerformed
        if (txIdSuakm.getText().length() == 0 || txTenkmSuakm.getText().length() == 0 || txDkkmSuakm.getText().length() == 0 || txPhantramkmSuakm.getText().length() == 0
                || jTextArea2.getText().length() == 0 || jDateChooser1.getDate() == null || jDateChooser2.getDate() == null) {
            JOptionPane.showMessageDialog(null, "vui lòng nhập đầy đủ thông tin");
        } else {
            KhuyenmaiDTO km = new KhuyenmaiDTO();
            km.setIdKhuyenmaiDTO(Integer.parseInt(txIdSuakm.getText()));
            km.setTenKhuyenmaiDTO(txTenkmSuakm.getText());
            km.setPhantramKhuyenmaiDTO(Integer.parseInt(txPhantramkmSuakm.getText()));
            km.setNgaybdKhuyenmaiDTO((Date) jDateChooser1.getDate());
            km.setNgayktKhuyenmaiDTO((Date) jDateChooser2.getDate());
            km.setNoidungkhuyenmaiDTO(jTextArea2.getText());
            if (new KhuyemainBUS().suaKM(km)) {
                JOptionPane.showMessageDialog(null, "sửa thành công");
                editKhuyenmai.dispose();
                listKM = new KhuyemainBUS().getList();
                modelkm.setRowCount(0);
                tblkm.setModel(modelkm);
                showKM();

            } else {
                JOptionPane.showMessageDialog(null, "sửa không thành công");
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnSave9ActionPerformed

    private void btnExitSuaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitSuaKMActionPerformed
        // TODO add your handling code here:
        editKhuyenmai.dispose();
    }//GEN-LAST:event_btnExitSuaKMActionPerformed

    private void btnDel9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel9ActionPerformed
        txIdSuakm.setText("");
        txTenkmSuakm.setText("");
        txPhantramkmSuakm.setText("");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jTextArea2.setText("");
// TODO add your handling code here:
    }//GEN-LAST:event_btnDel9ActionPerformed

    private void btnSave11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave11ActionPerformed
        // TODO add your handling code here:
        if (txidnccSuancc.getText().length() == 0 || txtenSuancc.getText().length() == 0 || txsdtSuancc.getText().length() == 0 || txdiachiSuancc.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
        } else {
            NhacungcapDTO ncc = new NhacungcapDTO();
            ncc.setIdNhacungcapDTO(Integer.parseInt(txidnccSuancc.getText()));
            ncc.setSdtNhacungcapDTO(txsdtSuancc.getText());
            ncc.setDiachiNhacungcapDTO(txdiachiSuancc.getText());
            ncc.setTenNhacungcapDTO(txtenSuancc.getText());
            if (new NhacungcapBUS().suaNCC(ncc)) {
                int index = 0;
                for (NhacungcapDTO nccDTO : listNCC) {
                    index++;
                    if (nccDTO.getIdNhacungcapDTO() == ncc.getIdNhacungcapDTO()) {
                        break;

                    }
                }
                listNCC.set(index - 1, ncc);
                modelncc.setRowCount(0);
                tblncc.setModel(modelncc);
                showNCC();
                editNCC.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Sửa thất bại");
            }
        }
    }//GEN-LAST:event_btnSave11ActionPerformed

    private void btnExitSuaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitSuaNCCActionPerformed
        // TODO add your handling code here:
        editNCC.dispose();
    }//GEN-LAST:event_btnExitSuaNCCActionPerformed

    private void btnDel11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDel11ActionPerformed

    private void btnDel12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel12ActionPerformed
        // TODO add your handling code here:
        txidThemtk.setText("");
        txtendnThemtk.setText("");
        txmkThemtk.setText("");
    }//GEN-LAST:event_btnDel12ActionPerformed

    private void btnExitThemTaikhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitThemTaikhoanActionPerformed
        // TODO add your handling code here:
        addTaikhoan.dispose();
    }//GEN-LAST:event_btnExitThemTaikhoanActionPerformed

    private void btnSave12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave12ActionPerformed
        // TODO add your handling code here:
        if (txidThemtk.getText().length() == 0 || txtendnThemtk.getText().length() == 0 || txmkThemtk.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
        } else {
            DangnhapDTO dnDTO = new DangnhapDTO();
            dnDTO.setChucvunvDTO(nvSelect);
            dnDTO.setIdNhanvienDTO(nvSelect);
            dnDTO.setMatkhauDangnhapDTO(txmkThemtk.getText());
            dnDTO.setTenDangnhapDTO(txtendnThemtk.getText());
            dnDTO.setTenNhanvien(nvSelect.getTenNhanvien());

            if (checkmatchTK(nvSelect.getIdNhanvien(), txtendnThemtk.getText())) {
                JOptionPane.showMessageDialog(null, "Nhân viên đã có tài khoản hoặc nhập trùng tên đăng nhập");
            } else {
                if (new DangnhapBUS().addAccountBUS(dnDTO)) {
                    listTK.add(dnDTO);
                    modeltk.setRowCount(0);
                    tbltk.setModel(modeltk);
                    showTK();
                    addTaikhoan.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại");

                }
            }
        }
    }//GEN-LAST:event_btnSave12ActionPerformed

    private void btnSave13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave13ActionPerformed
        // TODO add your handling code here:
        if (txtendnSuatk.getText().length() == 0 || txmkSuatk.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");

        } else {
            DangnhapDTO dn = new DangnhapDTO();
            NhanvienDTO nv = new NhanvienDTO();
            nv.setIdNhanvien(Integer.valueOf(txidSuatk.getText()));
            nv.setChucvuNhanvien(txChucvuSuatk.getText());
            dn.setMatkhauDangnhapDTO(txmkSuatk.getText());
            dn.setTenDangnhapDTO(txtendnSuatk.getText());
            dn.setIdNhanvienDTO(nv);
            dn.setChucvunvDTO(nv);
            dn.setTenNhanvien(txtenSuatk.getText());

            if (new DangnhapBUS().editAccount(dn)) {
                int index = 0;
                for (DangnhapDTO nccDTO : listTK) {
                    index++;
                    if (nccDTO.getIdNhanvienDTO().getIdNhanvien() == dn.getIdNhanvienDTO().getIdNhanvien()) {
                        break;
                    }
                }
                listTK.set(index - 1, dn);
                modeltk.setRowCount(0);
                tbltk.setModel(modeltk);
                showTK();
                editTaikhoan.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "Sửa thất bại");
            }
        }
    }//GEN-LAST:event_btnSave13ActionPerformed

    private void btnExitSuaTaikhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitSuaTaikhoanActionPerformed
        // TODO add your handling code here:
        editTaikhoan.dispose();
    }//GEN-LAST:event_btnExitSuaTaikhoanActionPerformed

    private void btnDel13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel13ActionPerformed
        // TODO add your handling code here:
        txidSuatk.setText("");
        txtendnSuatk.setText("");
        txmkSuatk.setText("");
        txtenSuatk.setText("");
        txChucvuSuatk.setText("");
    }//GEN-LAST:event_btnDel13ActionPerformed

    private void btnSuaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKHActionPerformed
        // TODO add your handling code here:

        int i = tblKh.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần sửa");
        } else {
            txtIdkhEdit.setText(String.valueOf(tblKh.getValueAt(i, 1)));
            txtDiachiKhEdit.setText(String.valueOf((tblKh.getValueAt(i, 5))));
            txtEmailKhEdit.setText(String.valueOf((tblKh.getValueAt(i, 7))));
            txtTenKhEdit.setText(String.valueOf((tblKh.getValueAt(i, 3))));
            txtHoKhEdit.setText(String.valueOf((tblKh.getValueAt(i, 2))));
            txtSdtKhEdit.setText(String.valueOf((tblKh.getValueAt(i, 6))));
            txtTuoiKhEdit.setText(String.valueOf(tblKh.getValueAt(i, 8)));
            String rd = String.valueOf(tblKh.getValueAt(i, 4));
            if (rd.equals("Nam")) {

                rdNamEdit.setSelected(true);
            } else if (rd.equals("Nữ")) {
                rdNuEdit.setSelected(true);
            }
            editKhachhang.setVisible(true);
        }


    }//GEN-LAST:event_btnSuaKHActionPerformed

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        // TODO add your handling code here:
        String maxid = String.valueOf(listNV.get(listNV.size() - 1).getIdNhanvien() + 1);
        txtidnvThemnv.setText(maxid);
        addNhanvien.setVisible(true);
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void btnSuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNVActionPerformed
        // TODO add your handling code here:
        int i = tblnv.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần sửa");
        } else {
            txidSuanv.setText(String.valueOf(tblnv.getValueAt(i, 1)));
            txtenSuanv.setText(String.valueOf((tblnv.getValueAt(i, 3))));
            txhoSuanv.setText(String.valueOf((tblnv.getValueAt(i, 2))));
            txluongSuanv.setText(String.valueOf((tblnv.getValueAt(i, 9))));
            txsdtSuanv.setText(String.valueOf((tblnv.getValueAt(i, 7))));
            txtuoiSuanv.setText(String.valueOf((tblnv.getValueAt(i, 5))));
            txdiachiSuanv.setText(String.valueOf(tblnv.getValueAt(i, 6)));
            String cbb = String.valueOf(tblnv.getValueAt(i, 8));
            if (cbb.equals("Quản lý")) {
                cbbchucvuSuanv.setSelectedIndex(0);
            } else {
                cbbchucvuSuanv.setSelectedIndex(1);

            }

            String rd = String.valueOf(tblnv.getValueAt(i, 4));
            if (rd.equals("Nam")) {

                rdNamSuanv.setSelected(true);
            } else if (rd.equals("Nữ")) {
                rdNuSuanv.setSelected(true);
            }
            editNhanvien.setVisible(true);

        }
    }//GEN-LAST:event_btnSuaNVActionPerformed

    private void btnThemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNCCActionPerformed
        // TODO add your handling code here:
        String t = String.valueOf(listNCC.get(listNCC.size() - 1).getIdNhacungcapDTO() + 1);
        txidnccThemncc.setText(t);
        addNCC.setVisible(true);
    }//GEN-LAST:event_btnThemNCCActionPerformed

    private void btnSuaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNCCActionPerformed
        // TODO add your handling code here:
        int i = tblncc.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cần sửa");
        } else {
            txidnccSuancc.setText(String.valueOf(tblncc.getValueAt(i, 1)));
            txtenSuancc.setText(String.valueOf(tblncc.getValueAt(i, 2)));
            txsdtSuancc.setText(String.valueOf(tblncc.getValueAt(i, 3)));
            txdiachiSuancc.setText(String.valueOf(tblncc.getValueAt(i, 4)));
            editNCC.setVisible(true);
        }
    }//GEN-LAST:event_btnSuaNCCActionPerformed

    private void btnThemTaikhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTaikhoanActionPerformed
        // TODO add your handling code here:

        addTaikhoan.setVisible(true);
    }//GEN-LAST:event_btnThemTaikhoanActionPerformed

    private void btnSuaTaikhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTaikhoanActionPerformed
        // TODO add your handling code here:
        int i = tbltk.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cần sửa");
        } else {
            txidSuatk.setText(String.valueOf(tbltk.getValueAt(i, 1)));
            txtendnSuatk.setText(String.valueOf(tbltk.getValueAt(i, 3)));
            txmkSuatk.setText(String.valueOf(tbltk.getValueAt(i, 4)));
            txtenSuatk.setText(String.valueOf(tbltk.getValueAt(i, 2)));
            txChucvuSuatk.setText(String.valueOf(tbltk.getValueAt(i, 5)));

            editTaikhoan.setVisible(true);
        }
    }//GEN-LAST:event_btnSuaTaikhoanActionPerformed

    private void btnThemKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKMActionPerformed
        // TODO add your handling code here:
        String t = String.valueOf(listKM.get(listKM.size() - 1).getIdKhuyenmaiDTO() + 1);
        txIdkmThemkm.setText(t);

        addKhuyenmai.setVisible(true);
    }//GEN-LAST:event_btnThemKMActionPerformed

    private void btnSuaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKMActionPerformed

        int i = tblkm.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "vui lòng chọn dòng cần sửa");
        } else {
            txIdSuakm.setText(String.valueOf(tblkm.getValueAt(i, 1)));
            txTenkmSuakm.setText(String.valueOf(tblkm.getValueAt(i, 2)));
            txDkkmSuakm.setText(String.valueOf(tblkm.getValueAt(i, 6)));
            txPhantramkmSuakm.setText(String.valueOf(tblkm.getValueAt(i, 6)));
            jTextArea2.setText(String.valueOf(tblkm.getValueAt(i, 5)));
            jDateChooser1.setDate((Date) tblkm.getValueAt(i, 3));
            jDateChooser2.setDate((Date) tblkm.getValueAt(i, 4));

            editKhuyenmai.setVisible(true);
        }

// TODO add your handling code here:

    }//GEN-LAST:event_btnSuaKMActionPerformed

    private void btnSuaLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLSPActionPerformed
        // TODO add your handling code here:
        int i = tbllsp.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cần sửa");
        } else {
            txidlspSualsp.setText(String.valueOf(tbllsp.getValueAt(i, 1)));
            txtenSualsp.setText(String.valueOf(tbllsp.getValueAt(i, 2)));

            editLoaisanpham.setVisible(true);
        }
    }//GEN-LAST:event_btnSuaLSPActionPerformed

    private void btnThemLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLSPActionPerformed
        // TODO add your handling code here:
        txidThemlsp.setText(String.valueOf(listLSP.get(listLSP.size() - 1).getIdLoaiSanphamDTO() + 1));
        addLoaisanpham.setVisible(true);
    }//GEN-LAST:event_btnThemLSPActionPerformed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        // TODO add your handling code here:
        int max = 0;
        for (SanphamDTO sp : listSP) {
            if (sp.getIdSanphamDTO() > max) {
                max = sp.getIdSanphamDTO();
            }
        }
        txIdAddSP.setText(String.valueOf(max + 1));
        addSanpham.setVisible(true);
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        // TODO add your handling code here:
        int i = tblsp.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cần xóa");
        } else {
            txIdAddSP1.setText(String.valueOf(tblsp.getValueAt(i, 3)));
            txTenAddSp1.setText(String.valueOf(tblsp.getValueAt(i, 4)));
            txLspAddSp1.setText(String.valueOf(tblsp.getValueAt(i, 1)));
            txSoluongAddSp1.setText(String.valueOf(tblsp.getValueAt(i, 6)));
            txGiaAddSp1.setText(String.valueOf(tblsp.getValueAt(i, 5)));
            cbDvtAddSp1.setSelectedItem(tblsp.getValueAt(i, 7));
            cbTrangthaiAddSp1.setSelectedItem(tblsp.getValueAt(i, 8));

            editSanpham.setVisible(true);
        }
    }//GEN-LAST:event_btnSuaSPActionPerformed
    //    lay thong tin tu ban san pham

    public int getSelectedSanPham(int col) {
        int i = tblBhLeft.getSelectedRow();
        if (i >= 0) {
            int realI = tblBhLeft.convertRowIndexToModel(i);
            return (int) tblBhLeft.getValueAt(realI, col);
        }
        return -1;
    }
//        Show san ra text

    public void showchitietchonsp(int masp, int soluong) {
        if (masp != 0) {
            for (SanphamDTO sp : listSP) {
                if (sp.getIdSanphamDTO() == masp) {
                    txttenspbh.setText(sp.getTensanphamDTO());
                    txtdongiabh.setText(sp.getDongiasanphamDTO() + "");
                    txtsoluongbh.setText(String.valueOf(soluong));
                    txtmaspbh.setText(String.valueOf(sp.getIdSanphamDTO()));

                }

            }
        }
    }

    public void addChiTiet(int masp3, int soluong3) {

        SanphamDTO sp = spBUS.getSanpham(masp3);
//        System.out.println(sp);

        Boolean daCo = false; // check xem trong danh sách chi tiết hóa đơn đã có sản phẩm này chưa
        for (ChitietHoadonDTO cthd : dscthd) {
            if (cthd.getIdSanphamDTO() == sp.getIdSanphamDTO()) {
                int tongSoLuong = soluong3 + cthd.getSoluongDTO();
                if (tongSoLuong > sp.getSoluongsanphamDTO()) {
                    JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ (" + sp.getSoluongsanphamDTO() + ")");
                    return;
                }
                cthd.setSoluongDTO(tongSoLuong); // có rồi thì thay đổi số lượng
                daCo = true;
            }
        }

        if (!daCo) { // nếu chưa có thì thêm mới
            if (soluong3 > sp.getSoluongsanphamDTO()) {
                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ (" + sp.getSoluongsanphamDTO() + ")");
                return;
            }
            ChitietHoadonDTO cthd2 = new ChitietHoadonDTO(qlhdBUS.getNextID(), masp3, soluong3, sp.getDongiasanphamDTO(), soluong3 * sp.getDongiasanphamDTO());
            dscthd.add(cthd2);
        }

        // cập nhật lại table
//        modelbhright.addRow();
        setTable(dscthd);
    }
// Cap nhat table

    public void setTable(ArrayList<ChitietHoadonDTO> dshd) {
        modelbhright.setRowCount(0);

        int stt = 1;
        double tien = 0;
        for (ChitietHoadonDTO cthd : dshd) {
            int masp1 = cthd.getIdSanphamDTO();
            SanphamDTO sp1 = spBUS.getSanpham(masp1);
            String tensp1 = sp1.getTensanphamDTO();
            int soluong1 = cthd.getSoluongDTO();
            double dongia1 = cthd.getDongiaDTO();
            double thanhtien = soluong1 * dongia1;

            modelbhright.addRow(new Object[]{
                stt, masp1, tensp1, soluong1, dongia1, thanhtien,});

            stt++;
//            tongtienhoadon += thanhtien;
            tien += thanhtien;
//            System.out.println(thanhtien);
        }
        tongtienhoadon = tien;
//        System.out.println(tongtienhoadon);

        txTongBh.setText(String.valueOf(tongtienhoadon));

        // check khuyến mãi
//        t.addRow(new String[]{"", "", "", "", "", ""});
//        t.addRow(new String[]{"", "", "", "", "Tổng tiền", PriceFormatter.format(tongtien)});
//        if (khuyenMai != null && khuyenMai.getPhanTramKM() > 0 && khuyenMai.getDieuKienKM() <= tongtien) {
//            float giaTriKhuyenMai = tongtien * khuyenMai.getPhanTramKM() / 100;
//            float tongTienSauKhuyenMai = tongtien - giaTriKhuyenMai;
//            t.addRow(new String[]{"", "", "", "", "Khuyến mãi", PriceFormatter.format(-giaTriKhuyenMai)});
//            t.addRow(new String[]{"", "", "", "", "Còn lại", PriceFormatter.format(tongTienSauKhuyenMai)});
//            txTongTien.setText(String.valueOf(tongTienSauKhuyenMai));
//        } else {
//            txTongTien.setText(String.valueOf(tongtien));
//        }
//tblBhRight.setModel(modelbhright);
    }
    private void btnBhThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBhThemActionPerformed
        // TODO add your handling code here:

        int i = tblBhLeft.getSelectedRow();
        int maspleft = (int) tblBhLeft.getValueAt(i, 0);
//        JOptionPane.showMessageDialog(PanelHoadon, i);
        if (i == -1) {
            JOptionPane.showMessageDialog(new JFHethong_admin(), "Vui lòng chọn sản phẩm muốn thêm");
        } else {
            int masp = Integer.parseInt(txtmaspbh.getText());
            int soluong = Integer.parseInt(txtsoluongbh.getText());
//                 JOptionPane.showMessageDialog(PanelHoadon,soluong);

            try {

                if (soluong > 0) {
                    addChiTiet(masp, soluong);
//                    System.out.println(tongtien);
                } else {
                    JOptionPane.showMessageDialog(new JFHethong_admin(), "Số lượng không được âm");
                }
            } catch (Exception e) {
//                JOptionPane.showMessageDialog(new JFHethong_admin(), "Số lượng phải là số nguyên");
            }

        }
    }//GEN-LAST:event_btnBhThemActionPerformed

    private void txFindBhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txFindBhKeyPressed
        // TODO add your handling code here:
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelbhleft);
        tblBhLeft.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(txFindBh.getText().trim()));

    }//GEN-LAST:event_txFindBhKeyPressed

    private void txtIdkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdkhActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_txtIdkhActionPerformed

    private void btnDel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel3ActionPerformed
        // TODO add your handling code here:
        txtEmailKhEdit.setText("");
        txtTenKhEdit.setText("");
        txtHoKhEdit.setText("");
        txtDiachiKhEdit.setText("");
        txtSdtKhEdit.setText("");
        themkh.clearSelection();
    }//GEN-LAST:event_btnDel3ActionPerformed

    private void btnExitSuaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitSuaKHActionPerformed
        // TODO add your handling code here:
        editKhachhang.dispose();
    }//GEN-LAST:event_btnExitSuaKHActionPerformed

    private void btnSaveEditKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveEditKhActionPerformed
        // TODO add your handling code here:
        if (txtTenKhEdit.getText().equals("") || txtHoKhEdit.getText().equals("") || txtEmailKhEdit.getText().equals("") || txtSdtKhEdit.getText().equals("") || txtDiachiKhEdit.getText().equals("") || txtTuoiKhEdit.getText().equals("") || checkgender(rdNamEdit, rdNuEdit) == "") {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
        } else {
            KhachhangDTO kh = new KhachhangDTO();
            kh.setMaKhachhang(Integer.valueOf(txtIdkhEdit.getText()));
            kh.setTenKhachhang(uper(txtTenKhEdit.getText()));
            kh.setHoKhachhang(uper(txtHoKhEdit.getText()));
            kh.setDiaChi(txtDiachiKhEdit.getText());
            kh.setSdtKhachhang(txtSdtKhEdit.getText());
            kh.setEmailKhachhang(txtEmailKhEdit.getText());
            kh.setTuoiKhachhang(Integer.valueOf(txtTuoiKhEdit.getText()));

            kh.setGioitinh(checkgender(rdNamEdit, rdNuEdit));
            KhachhangBUS khbus = new KhachhangBUS();
            if (khbus.suaKH(kh)) {
                int index = 0;
                for (KhachhangDTO row : listKH) {
                    index++;
                    if (row.getMaKhachhang() == kh.getMaKhachhang()) {
                        break;
                    }
                }
                listKH.set(index - 1, kh);
                JOptionPane.showMessageDialog(addKhachhang, "Thêm khách hàng thành công!");
                editKhachhang.dispose();
                modelkh.setRowCount(0);
                showKH();
            } else {
                JOptionPane.showMessageDialog(PanelHoadon, "Thêm thất bại");
            }
        }


    }//GEN-LAST:event_btnSaveEditKhActionPerformed

    private void rdNamEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNamEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNamEditActionPerformed

    private void rdNamkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNamkhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNamkhActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        modelkh.setRowCount(0);
        listKH = new KhachhangBUS().getList();
        showKH();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void btnDelKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelKhActionPerformed
        // TODO add your handling code here:
        int i = tblKh.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "vui lòng chọn dòng cần xóa");

        } else if (JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa khách hàng này? ", "Confirm", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION) == JOptionPane.YES_OPTION) {
            select = (int) tblKh.getModel().getValueAt(i, 1);
            KhachhangDTO kh = new KhachhangDTO();
            kh.setMaKhachhang(select);
            if (new KhachhangBUS().xoaKH(kh)) {

                modelkh.removeRow(i);
                tblKh.setModel(modelkh);
                listKH.remove(i);
                showMessageDialog(rootPane, "Xóa thành công");
            } else {
                showMessageDialog(rootPane, "Xóa thất bại");
            }
        }

    }//GEN-LAST:event_btnDelKhActionPerformed

    private void txtDiachikhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiachikhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiachikhActionPerformed

    private void rdNuThemnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNuThemnvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNuThemnvActionPerformed

    private void txFindKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txFindKhActionPerformed
        // TODO add your handling code here:
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelkh);
        tblKh.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(txFindKh.getText().trim()));

    }//GEN-LAST:event_txFindKhActionPerformed

    private void txFindBhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txFindBhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txFindBhActionPerformed

    private void btnHuyBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyBillActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Bạn chắc chắn?", "Cảnh báo",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            // yes option
            modelbhright.setRowCount(0);
            tblBhRight.setModel(modelbhright);
            txTong = 0;
            txTongBh.setText("0VND");
            txBhKm.setText("");
            txTonghd = 0;
//            txTongBhHd.setText("0VND");
        } else {
            // no option
        }


    }//GEN-LAST:event_btnHuyBillActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int i = tblBhRight.getSelectedRow();
        int percent = 0;
        txTong = 0;
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui Lòng chọn dòng cần xóa");
        } else {
            txTongBh.setText("0VND");
            modelbhright.removeRow(i);
            tblBhRight.setModel(modelbhright);
            for (int j = 0; j < modelbhright.getRowCount(); j++) {

                txTong += (int) modelbhright.getValueAt(j, 5);

            }

            Date today = new Date();
            txBhKm.setText("0%");
            ArrayList<KhuyenmaiDTO> kmListMax = new ArrayList<>();
            for (KhuyenmaiDTO kmDTO : listKM) {
                int ssnbd = today.compareTo(kmDTO.getNgaybdKhuyenmaiDTO());
                int ssnkt = kmDTO.getNgayktKhuyenmaiDTO().compareTo(today);

                if (ssnbd >= 0 && ssnkt >= 0 && txTong >= kmDTO.getDieukienKHuyemaiDTO()) {
                    kmListMax.add(kmDTO);
                    percent = kmDTO.getPhantramKhuyenmaiDTO();
                }
            }
            KhuyenmaiDTO kmMax = getDkKmmax(kmListMax);
            txBhKm.setText(String.valueOf(kmMax.getPhantramKhuyenmaiDTO()) + "% " + "( ID:" + String.valueOf(kmMax.getIdKhuyenmaiDTO()) + ")");
            txTongBh.setText(String.valueOf(txTong) + "VND");

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        exportExcel(tblKh);

    }//GEN-LAST:event_jButton14ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Chắc chắn muốn thoát", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            // yes option
            System.exit(0);

        } else {
            // no option
        }

    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        this.setState(JFHethong_admin.ICONIFIED);

    }//GEN-LAST:event_jLabel6MouseClicked

    private void txtHeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHeaderMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_txtHeaderMouseDragged

    private void PanelHeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelHeaderMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);

    }//GEN-LAST:event_PanelHeaderMouseDragged

    private void PanelHeaderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelHeaderMouseReleased
        // TODO add your handling code here:


    }//GEN-LAST:event_PanelHeaderMouseReleased

    private void PanelHeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelHeaderMousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_PanelHeaderMousePressed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        // TODO add your handling code here:
        exportExcel(tblsp);
    }//GEN-LAST:event_jButton49ActionPerformed

    private void btnThanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhtoanActionPerformed
        // TODO add your handling code here:
//        if (txBhIdnv.getText().length() == 0 || txBhIdkh.getText().length() == 0 || txBhMahd.getText().length() == 0 || txBhMahd.getText().length() == 0 || txGiolapBh.getText().length() == 0 || txNgaylapBh.getText().length() == 0 || txTongBh.getText().equals("0VND") || txTongBhHd.getText().equals("0VND")) {
//            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
//
//        } else {
//            if (checkIdKh(Integer.parseInt(txBhIdkh.getText()))) {
//                HoadonDTO hd = new HoadonDTO();
//                hd.setGiolapHoadonDTO(LocalTime.parse(txGiolapBh.getText()));
//                hd.setIdHoadonDTO(Integer.valueOf(txBhMahd.getText()));
//
//                hd.setNgaylapHoadonDTO(LocalDate.parse(txNgaylapBh.getText()));
////                hd.setTongHoadonDTO(Integer.valueOf(txTongBhHd.getText().split("VND")[0]));
//                if (txBhKm.getText().equals("0%")) {
//
//                    hd.setIdKhuyenmaiDTO(208);
//                } else {
//                    String sd = txBhKm.getText();
//                    int x = sd.indexOf("(");
//                    int y = sd.indexOf(")");
//                    String mhd = sd.substring(x + 5, y);
//                    if (mhd.equals("0")) {
//                        mhd = "208";
//                    }
//                    hd.setIdKhuyenmaiDTO(Integer.valueOf(mhd));
//
//                }
//                hd.setIdKhachhangDTO(Integer.parseInt(txBhIdkh.getText()));
////                hd.setTongHoadonDTO(Integer.valueOf(txTongBhHd.getText().split("VND")[0]));
//                hd.setIdNhanvienDTO(Integer.valueOf(txBhIdnv.getText()));
//                if (new HoadonBUS().addHoadonBUS(hd)) {
//                    int count = tblBhRight.getRowCount();
//                    for (int i = 0; i < count; i++) {
//                        ChitietHoadonDTO cthd = new ChitietHoadonDTO();
//                        cthd.setDongiaDTO((int) tblBhRight.getValueAt(i, 3));
//                        cthd.setTongGia((int) tblBhRight.getValueAt(i, 5));
//                        cthd.setIdSanphamDTO((int) tblBhRight.getValueAt(i, 1));
//                        cthd.setSoluongDTO((int) tblBhRight.getValueAt(i, 4));
//                        cthd.setIdHoadonDTO(hd.getIdHoadonDTO());
//                        if (new ChitiethoadonBUS().Themcthd(cthd)) {
//                            SanphamDTO sps = findSP(cthd.getIdSanphamDTO());
//                            sps.setSoluongsanphamDTO(sps.getSoluongsanphamDTO() - cthd.getSoluongDTO());
//                            if (new SanphamBUS().suaSP(sps)) {
//
//                            } else {
//                                JOptionPane.showMessageDialog(null, "Thanh toán thất bại");
//                            }
//                        } else {
//                            JOptionPane.showMessageDialog(null, "Thanh toán thất bại");
//                        }
//
//                    }
//                    JOptionPane.showMessageDialog(null, "Thanh toán thành công ");
//                    modelbhright.setRowCount(0);
//                    tblBhRight.setModel(modelbhright);
//                    txTong = 0;
//                    txTongBh.setText("0VND");
//                    txBhKm.setText("0%");
//                    txTonghd = 0;
//                    txTongBhHd.setText("0VND");
//                    int mhd = Integer.valueOf(txBhMahd.getText()) + 1;
//                    int mkh = Integer.valueOf(txBhIdkh.getText()) + 1;
//                    txBhMahd.setText(String.valueOf(mhd));
//                    txBhIdkh.setText(String.valueOf(mkh));
//                    listSP = new SanphamBUS().getList();
//                    modelbhleft.setRowCount(0);
//                    tblBhLeft.setModel(modelbhleft);
//                    showBH(Integer.valueOf(txBhIdnv.getText()));
//
//                } else {
//                    JOptionPane.showMessageDialog(null, "Thanh toán thất bại");
//
//                }
//            } else {
//                if (new KhachhangBUS().themKHRong(Integer.valueOf(txBhIdkh.getText()))) {
//                    HoadonDTO hd = new HoadonDTO();
//                    hd.setGiolapHoadonDTO(LocalTime.parse(txGiolapBh.getText()));
//                    hd.setIdHoadonDTO(Integer.valueOf(txBhMahd.getText()));
//
//                    hd.setNgaylapHoadonDTO(LocalDate.parse(txNgaylapBh.getText()));
////                    hd.setTongHoadonDTO(Integer.valueOf(txTongBhHd.getText().split("VND")[0]));
//                    if (txBhKm.getText().equals("0%")) {
//
//                        hd.setIdKhuyenmaiDTO(208);
//                    } else {
//                        String sd = txBhKm.getText();
//                        int x = sd.indexOf("(");
//                        int y = sd.indexOf(")");
//                        String mhd = sd.substring(x + 5, y);
//                        if (mhd.equals("0")) {
//                            mhd = "208";
//                        }
//                        hd.setIdKhuyenmaiDTO(Integer.valueOf(mhd));
//
//                    }
//                    hd.setIdKhachhangDTO(Integer.parseInt(txBhIdkh.getText()));
//                    hd.setTongHoadonDTO(Integer.valueOf(txTongBhHd.getText().split("VND")[0]));
//                    hd.setIdNhanvienDTO(Integer.valueOf(txBhIdnv.getText()));
//                    if (new HoadonBUS().addHoadonBUS(hd)) {
//                        int count = tblBhRight.getRowCount();
//                        for (int i = 0; i < count; i++) {
//                            ChitietHoadonDTO cthd = new ChitietHoadonDTO();
//                            cthd.setDongiaDTO((int) tblBhRight.getValueAt(i, 3));
//                            cthd.setTongGia((int) tblBhRight.getValueAt(i, 5));
//                            cthd.setIdSanphamDTO((int) tblBhRight.getValueAt(i, 1));
//                            cthd.setSoluongDTO((int) tblBhRight.getValueAt(i, 4));
//                            cthd.setIdHoadonDTO(hd.getIdHoadonDTO());
//                            if (new ChitiethoadonBUS().Themcthd(cthd)) {
//                                SanphamDTO sps = findSP(cthd.getIdSanphamDTO());
//                                sps.setSoluongsanphamDTO(sps.getSoluongsanphamDTO() - cthd.getSoluongDTO());
//                                if (new SanphamBUS().suaSP(sps)) {
//
//                                } else {
//                                    JOptionPane.showMessageDialog(null, "Thất bại");
//                                }
//
//                            } else {
//                                JOptionPane.showMessageDialog(null, "Thanh toán thất bại ");
//                            }
//
//                        }
//                        JOptionPane.showMessageDialog(null, "Thanh toán thành công ");
//                        modelbhright.setRowCount(0);
//                        tblBhRight.setModel(modelbhright);
//                        txTong = 0;
//                        txTongBh.setText("0VND");
//                        txBhKm.setText("0%");
//                        txTonghd = 0;
//                        txTongBhHd.setText("0VND");
//                        int mhd = Integer.valueOf(txBhMahd.getText()) + 1;
//                        int mkh = Integer.valueOf(txBhIdkh.getText()) + 1;
//                        txBhMahd.setText(String.valueOf(mhd));
//                        txBhIdkh.setText(String.valueOf(mkh));
//                        listSP = new SanphamBUS().getList();
//                        modelbhleft.setRowCount(0);
//                        tblBhLeft.setModel(modelbhleft);
//                        showBH(Integer.valueOf(txBhIdnv.getText()));
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Thanh toán thất bại");
//
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "Tạo khách hàng thất bại");
//                }
//
//            }
//
//        }
        HoadonDTO hoadon = new HoadonDTO(Integer.parseInt(txBhMahd.getText()),
                nvDTO.getIdNhanvien(), khDTO.getMaKhachhang(),
                Integer.parseInt(txBhKm.getText()), LocalDate.parse(txNgaylapBh.getText()),
                LocalTime.parse(txGiolapBh.getText()), (int) Double.parseDouble(txTongBh.getText()));
        qlhdBUS.addHoadonBUS(hoadon);
        for (ChitietHoadonDTO ct : dscthd) {
            cthdBUS.Themcthd(ct);
            SanphamDTO sp = new SanphamDTO();
            sp = findSP(ct.getIdSanphamDTO());
            sp.setSoluongsanphamDTO(sp.getSoluongsanphamDTO() - ct.getSoluongDTO());
            updateSoLuongSanPham(sp.getIdSanphamDTO(), sp.getSoluongsanphamDTO());
            System.out.println(sp.getSoluongsanphamDTO());

//            System.out.println(ct.getSoluongDTO());
        }

//        setTable(dscthd);
        int reply = JOptionPane.showConfirmDialog(getRootPane(),
                "Thanh toán thành công, bạn có muốn IN HÓA ĐƠN?", "Thành công",
                JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.OK_OPTION) {
            new ReportPDF(txBhMahd.getText());
            JOptionPane.showMessageDialog(this, "In thanh cong");
        }
        txBhMahd.setText(String.valueOf(qlhdBUS.getNextID()));

        tblBhLeft.removeAll();
        listSP = new SanphamBUS().getList();

        modelbhleft.setRowCount(0);
        listHD = new HoadonBUS().readBUSs();
        showBH();
       showHD();

        clear();
//        listSP = new SanphamBUS().getList();


    }//GEN-LAST:event_btnThanhtoanActionPerformed
    private boolean updateSoLuongSanPham(int _masp, int _soLuongThayDoi) {
        for (SanphamDTO sp : listSP) {
            if (sp.getIdSanphamDTO() == _masp) {
                return new SanphamBUS().UpdateSoluong(_masp, _soLuongThayDoi);
            }
        }
        return false;
    }

    public void clear() {
        txBhIdkh.setText("");
        txTongBh.setText("0VND");
        txBhKm.setText("");
        dscthd.clear();
        setTable(dscthd);
    }
    private void txtidnvThemnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidnvThemnvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidnvThemnvActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int i = tblnv.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(rootPane, "vui lòng chọn dòng cần xóa");

        } else if (JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa nhân viên này? ", "Confirm", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION) == JOptionPane.YES_OPTION) {
            select = (int) tblnv.getModel().getValueAt(i, 1);
            NhanvienDTO nv = new NhanvienDTO();
            nv.setIdNhanvien(select);
            if (new NhanvienBUS().xoaNV(nv)) {

                modelnv.removeRow(i);
                tblnv.setModel(modelnv);
                listNV.remove(i);
                showMessageDialog(rootPane, "Xóa thành công");
            } else {
                showMessageDialog(rootPane, "Xóa thất bại");
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        modelnv.setRowCount(0);
        tblnv.setModel(modelnv);
        listNV = new NhanvienBUS().getList();
        showNV(listNV);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        exportExcel(tblnv);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void txidSuanvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txidSuanvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txidSuanvActionPerformed

    private void resetnccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetnccActionPerformed
        // TODO add your handling code here:
        modelncc.setRowCount(0);
        tblncc.setModel(modelncc);
        listNCC = new NhacungcapBUS().getList();
        showNCC();
    }//GEN-LAST:event_resetnccActionPerformed

    private void btnDel10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel10ActionPerformed
        // TODO add your handling code here:
        txdiachinccThemncc.setText("");
        txtsdtnccThemncc.setText("");
        txtennccThemncc.setText("");


    }//GEN-LAST:event_btnDel10ActionPerformed

    private void btnExitThemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitThemNCCActionPerformed
        // TODO add your handling code here:
        addNCC.dispose();
    }//GEN-LAST:event_btnExitThemNCCActionPerformed

    private void btnSave10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave10ActionPerformed
        // TODO add your handling code here:
        if (txidnccThemncc.getText().length() == 0 || txtennccThemncc.getText().length() == 0 || txdiachinccThemncc.getText().length() == 0 || txtsdtnccThemncc.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
        } else {
            NhacungcapDTO ncc = new NhacungcapDTO();
            ncc.setIdNhacungcapDTO(Integer.parseInt(txidnccThemncc.getText()));
            ncc.setSdtNhacungcapDTO(txtsdtnccThemncc.getText());
            ncc.setDiachiNhacungcapDTO(txdiachinccThemncc.getText());
            ncc.setTenNhacungcapDTO(txtennccThemncc.getText());
            if (new NhacungcapBUS().themNCC(ncc)) {
                listNCC.add(ncc);
                addNCC.dispose();
                resetncc.doClick();

            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
            }
        }
    }//GEN-LAST:event_btnSave10ActionPerformed

    private void txdiachinccThemnccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txdiachinccThemnccActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txdiachinccThemnccActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        int i = tblncc.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cân xóa");
        } else {
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa nhà cung cấp này?", "Xác nhận", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION) == JOptionPane.YES_OPTION) {
                NhacungcapDTO ncc = new NhacungcapDTO();
                ncc.setIdNhacungcapDTO((int) tblncc.getValueAt(i, 1));
                if (new NhacungcapBUS().xoaNCC(ncc)) {
                    int index = 0;
                    for (NhacungcapDTO nccDTO : listNCC) {
                        index++;
                        if (nccDTO.getIdNhacungcapDTO() == ncc.getIdNhacungcapDTO()) {
                            break;
                        }

                    }
                    listNCC.remove(i);
                    modelncc.setRowCount(0);
                    tblncc.setModel(modelncc);
                    showNCC();

                } else {
                    JOptionPane.showMessageDialog(null, "Xóa thất bại");

                }
            }
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void txFindKhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txFindKhKeyPressed
        // TODO add your handling code here:
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelkh);
        tblKh.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(txFindKh.getText().trim()));
    }//GEN-LAST:event_txFindKhKeyPressed

    private void txFindNvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txFindNvKeyPressed
        // TODO add your handling code here:
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelnv);
        tblnv.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(txFindNv.getText().trim()));
    }//GEN-LAST:event_txFindNvKeyPressed

    private void jButton59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton59ActionPerformed
        // TODO add your handling code here:
        selectNhanvien.setVisible(true);
    }//GEN-LAST:event_jButton59ActionPerformed

    private void tblTimnvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTimnvMouseClicked
        // TODO add your handling code here:
        int i = tblTimnv.getSelectedRow();
        nvSelect.setIdNhanvien((int) tblTimnv.getValueAt(i, 1));
        nvSelect.setHoNhanvien((String) tblTimnv.getValueAt(i, 2));
        nvSelect.setTenNhanvien((String) tblTimnv.getValueAt(i, 3));
        nvSelect.setGioitinh((String) tblTimnv.getValueAt(i, 4));
        nvSelect.setTuoiNhanvien((int) tblTimnv.getValueAt(i, 5));
        nvSelect.setDiaChiNhanvien((String) tblTimnv.getValueAt(i, 6));
        nvSelect.setSdtNhanvien((String) tblTimnv.getValueAt(i, 7));
        nvSelect.setChucvuNhanvien((String) tblTimnv.getValueAt(i, 8));
        nvSelect.setLuongNhanvien((int) tblTimnv.getValueAt(i, 9));
        txidThemtk.setText(String.valueOf(nvSelect.getIdNhanvien()));
        selectNhanvien.dispose();

    }//GEN-LAST:event_tblTimnvMouseClicked

    private void txFindSelectnvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txFindSelectnvKeyPressed
        // TODO add your handling code here:
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelnv);
        tblTimnv.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(txFindSelectnv.getText().trim()));
    }//GEN-LAST:event_txFindSelectnvKeyPressed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        int i = tbltk.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cần xóa");

        } else {
            if (JOptionPane.showConfirmDialog(this, "bạn chắc chắn muốn xóa tài khoản này?", "Xác nhận", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION) == JOptionPane.YES_OPTION) {
                DangnhapDTO dn = new DangnhapDTO();
                NhanvienDTO nv = new NhanvienDTO();
                nv.setIdNhanvien((int) tbltk.getValueAt(i, 1));
                dn.setIdNhanvienDTO(nv);
                if (new DangnhapBUS().deleteAccount(dn)) {
                    listTK.remove(i);
                    modeltk.setRowCount(0);
                    tbltk.setModel(modeltk);
                    showTK();
                } else {

                    JOptionPane.showMessageDialog(null, "Xóa thất bại");
                }
            }

        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
        modeltk.setRowCount(0);
        tbltk.setModel(modeltk);
        listTK = new DangnhapBUS().readBUSs();
        showTK();
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
        exportExcel(tbltk);
    }//GEN-LAST:event_jButton29ActionPerformed

    private void txFindtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txFindtkKeyPressed
        // TODO add your handling code here:
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modeltk);
        tbltk.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(txFindtk.getText().trim()));
    }//GEN-LAST:event_txFindtkKeyPressed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
        exportExcel(tblkm);
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
        listKM = new KhuyemainBUS().getList();
        modelkm.setRowCount(0);
        tblkm.setModel(modelkm);
        showKM();
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        // TODO add your handling code here:
        listLSP = new LoaisanphamBUS().getList();
        modellsp.setRowCount(0);
        tbllsp.setModel(modellsp);
        showLSP();
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jTextField23KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField23KeyPressed
        // TODO add your handling code here:
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modellsp);
        tbllsp.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(jTextField23.getText().trim()));
    }//GEN-LAST:event_jTextField23KeyPressed

    private void tblhdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhdMouseClicked
        // TODO add your handling code here:
        int i = tblhd.getSelectedRow();
        int idsp = (int) tblhd.getValueAt(i, 1);
        modelcthd.setRowCount(0);
        tblcthd.setModel(modelcthd);
//        listHD=new HoadonBUS().readBUSs();
        listCTHD=new ChitiethoadonBUS().getList();
//        showHD();
        showCTHD(getListCthd(listCTHD, idsp));

    }//GEN-LAST:event_tblhdMouseClicked

    private void btnLochdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLochdActionPerformed
        // TODO add your handling code here:
        if (txtoLochd.getDate() != null || txfromLochd.getDate() != null) {
            LocalDate dayt = convertToLocalDateViaInstant(txtoLochd.getDate());
            LocalDate dayf = convertToLocalDateViaInstant(txfromLochd.getDate());

            ArrayList<HoadonDTO> lis = new ArrayList<>();
            for (HoadonDTO hd : listHD) {
                if (hd.getNgaylapHoadonDTO().compareTo(dayf) >= 0 && dayt.compareTo(hd.getNgaylapHoadonDTO()) >= 0) {
                    lis.add(hd);
                } else {
                }
            }

            modelhd.setRowCount(0);
            tblhd.setModel(modelhd);
            showHD();
        } else {
            JOptionPane.showMessageDialog(null, "Yêu cầu chọn khoảng ngày!");
        }
    }//GEN-LAST:event_btnLochdActionPerformed

    private void btnCapnhathdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhathdActionPerformed
        // TODO add your handling code here:
        modelhd.setRowCount(0);
        tblhd.setModel(modelhd);
        listHD = new HoadonBUS().readBUSs();
        showHD();
    }//GEN-LAST:event_btnCapnhathdActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        modelhd.setRowCount(0);
        tblhd.setModel(modelhd);
        listHD = new HoadonBUS().readBUSs();
        showHD();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txFindHDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txFindHDKeyPressed
        // TODO add your handling code here:
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelhd);
        tblhd.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(txFindHD.getText().trim()));
    }//GEN-LAST:event_txFindHDKeyPressed

    private void btnXuatechdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatechdActionPerformed
        // TODO add your handling code here:
        exportExcel(tblhd);
    }//GEN-LAST:event_btnXuatechdActionPerformed

    private void txFindSelectLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txFindSelectLSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txFindSelectLSPActionPerformed

    private void txFindSelectLSPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txFindSelectLSPKeyPressed
        // TODO add your handling code here:
        TableRowSorter<TableModel> tr = new TableRowSorter<TableModel>(tblSelectLSP.getModel());
        tblSelectLSP.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(txFindSelectLSP.getText().trim()));
    }//GEN-LAST:event_txFindSelectLSPKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        selectLSP.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tblSelectLSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSelectLSPMouseClicked
        // TODO add your handling code here:
        txLspAddSp.setText(String.valueOf(tblSelectLSP.getValueAt(tblSelectLSP.getSelectedRow(), 1)));
        selectLSP.dispose();
    }//GEN-LAST:event_tblSelectLSPMouseClicked

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        // TODO add your handling code here:
        listSP = new SanphamBUS().getList();
        modelsp.setRowCount(0);
        tblsp.setModel(modelsp);

        showSP(listSP);
    }//GEN-LAST:event_jButton50ActionPerformed

    private void btnSave6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave6ActionPerformed
        // TODO add your handling code here:
        if (txIdAddSP1.getText().length() == 0 || txTenAddSp1.getText().length() == 0 || txGiaAddSp1.getText().length() == 0 || txLspAddSp1.getText().length() == 0 || txSoluongAddSp1.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
        } else {
            try {
                SanphamDTO sp = new SanphamDTO();
                LoaisanphamDTO lsp = new LoaisanphamDTO();
                sp.setIdSanphamDTO(Integer.valueOf(txIdAddSP1.getText()));
                sp.setTensanphamDTO(txTenAddSp1.getText());
                sp.setDongiasanphamDTO(Integer.parseInt(txGiaAddSp1.getText()));
                sp.setDonvitinhSanphamDTO((String) cbDvtAddSp1.getSelectedItem());
                lsp.setIdLoaiSanphamDTO(Integer.valueOf(txLspAddSp1.getText()));
                sp.setIdLoaiSanphamDTO(lsp);
                sp.setTrangthaisanphamDTO((String) cbTrangthaiAddSp1.getSelectedItem());
                sp.setSoluongsanphamDTO(Integer.parseInt(txSoluongAddSp1.getText()));
                if (new SanphamBUS().suaSP(sp)) {
                    listSP = new SanphamBUS().getList();

                    modelsp.setRowCount(0);
                    tblsp.setModel(modelsp);
                    showSP(listSP);
                    editSanpham.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Sửa thất bại");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng thông số");
            }
        }

    }//GEN-LAST:event_btnSave6ActionPerformed

    private void btnDel6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel6ActionPerformed
        // TODO add your handling code here:
        txTenAddSp1.setText("");
        txLspAddSp1.setText("");
        txSoluongAddSp1.setText("");
        txIdAddSP1.setText("");
        txGiaAddSp1.setText("");
    }//GEN-LAST:event_btnDel6ActionPerformed

    private void btnExitThemSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitThemSP1ActionPerformed
        // TODO add your handling code here:
        editSanpham.dispose();
    }//GEN-LAST:event_btnExitThemSP1ActionPerformed

    private void txSoluongAddSp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txSoluongAddSp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txSoluongAddSp1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        selectLSPE.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tblSelectLSPEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSelectLSPEMouseClicked
        // TODO add your handling code here:
        txLspAddSp1.setText(String.valueOf(tblSelectLSPE.getValueAt(tblSelectLSPE.getSelectedRow(), 1)));
        selectLSPE.dispose();
    }//GEN-LAST:event_tblSelectLSPEMouseClicked

    private void txFindSelectLSPeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txFindSelectLSPeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txFindSelectLSPeActionPerformed

    private void txFindSelectLSPeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txFindSelectLSPeKeyPressed
        // TODO add your handling code here:
        TableRowSorter<TableModel> tr = new TableRowSorter<TableModel>(tblSelectLSPE.getModel());
        tblSelectLSPE.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(txFindSelectLSPe.getText().trim()));
    }//GEN-LAST:event_txFindSelectLSPeKeyPressed

    private void jTextField25KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField25KeyPressed
        // TODO add your handling code here:
        TableRowSorter<TableModel> tr = new TableRowSorter<TableModel>(tblsp.getModel());
        tblsp.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(jTextField25.getText().trim()));
    }//GEN-LAST:event_jTextField25KeyPressed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String s = (String) jComboBox1.getSelectedItem();
        ArrayList<NhanvienDTO> list = new ArrayList<>();
        if (s.equals("Đã nghỉ")) {
            for (NhanvienDTO nv : listNV) {
                if (nv.getTrangthai().equals("Đã nghỉ") || nv.getTrangthai().equals("đã nghỉ")) {
                    list.add(nv);
                }

            }
        } else if (s.equals("Hiện hành")) {
            for (NhanvienDTO nv : listNV) {
                if (nv.getTrangthai().equals("Hiện hành")) {
                    list.add(nv);
                }

            }
        } else if (s.equals("Tất cả")) {
            list = listNV;
        }
        modelnv.setRowCount(0);
        tblnv.setModel(modelnv);
        showNV(list);

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int i = tblNhRight.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cần xóa");

        } else {
            modelnhright.removeRow(i);
            tblNhRight.setModel(modelnhright);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnBhThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBhThem1ActionPerformed
        // TODO add your handling code here:
        String checksoluong = "([0-9]{1,})";
        if (txIdSpNh.getText().length() == 0 || txDongiaNh.getText().length() == 0 || txTenspNh.getText().length() == 0 || txSoluongNh.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
        } else if (!Pattern.matches(checksoluong, txSoluongNh.getText())) {
            JOptionPane.showMessageDialog(null, "Không nhập số âm hay chữ vào số lượng");
        } else {
            Vector vt = new Vector();
            vt.add("STT");
            vt.add("ID sản phẩm");
            vt.add("Tên sản phẩm");
            vt.add("Đơn giá");
            vt.add("Số lượng");
            vt.add("Thành tiền");
            if (modelnhright.getRowCount() == 0) {
                modelnhright = new DefaultTableModel(vt, 0);
            }
            if (modelnhright.getRowCount() > 0) {
                int idsp = Integer.parseInt(txIdSpNh.getText());
                int allow = 0;
                int soluong_moi = 0;
                int vitri = 0;
                int thanhtiennew = 0;
                for (int i = 0; i < modelnhright.getRowCount(); i++) {
                    if (idsp == Integer.parseInt(modelnhright.getValueAt(i, 1).toString())) {
                        allow = 1;
                        soluong_moi = Integer.parseInt(txSoluongNh.getText()) + Integer.parseInt(modelnhright.getValueAt(i, 4).toString());
                        vitri = i;
                    }
                }
                if (allow == 1) {
                    modelnhright.setValueAt(soluong_moi, vitri, 4);
                    thanhtiennew = Integer.parseInt(modelnhright.getValueAt(vitri, 3).toString()) * soluong_moi;
                    modelnhright.setValueAt(thanhtiennew, vitri, 5);
                }
                if (allow == 0) {
                    Object ob[] = new Object[6];
                    int stt = modelnhright.getRowCount();
                    ob[0] = stt + 1;
                    ob[1] = txIdSpNh.getText();
                    ob[2] = findSP(Integer.parseInt(txIdSpNh.getText())).getTensanphamDTO();
                    ob[3] = txDongiaNh.getText();
                    ob[4] = txSoluongNh.getText();
                    int dongia = Integer.parseInt(txDongiaNh.getText());
                    int soluong = Integer.parseInt(txSoluongNh.getText());
                    ob[5] = dongia * soluong;
                    modelnhright.addRow(ob);
                    tblNhRight.setModel(modelnhright);
                    int TongNh = 0;
                    for (int j = 0; j < modelnhright.getRowCount(); j++) {
                        TongNh += (int) modelnhright.getValueAt(j, 5);
                    }
                    txTongNh.setText(String.valueOf(TongNh));
                }
            } else {
                Object ob[] = new Object[6];
                int stt = modelnhright.getRowCount();
                ob[0] = stt + 1;
                ob[1] = txIdSpNh.getText();
                ob[2] = findSP(Integer.parseInt(txIdSpNh.getText())).getTensanphamDTO();
                ob[3] = txDongiaNh.getText();
                ob[4] = txSoluongNh.getText();
                int dongia = Integer.parseInt(txDongiaNh.getText());
                int soluong = Integer.parseInt(txSoluongNh.getText());
                ob[5] = dongia * soluong;
                modelnhright.addRow(ob);
                tblNhRight.setModel(modelnhright);
                int TongNh = 0;
                for (int i = 0; i < modelnhright.getRowCount(); i++) {
                    TongNh += (int) modelnhright.getValueAt(i, 5);
                }
                txTongNh.setText(String.valueOf(TongNh));
            }
        }
    }//GEN-LAST:event_btnBhThem1ActionPerformed

    private void txTongNhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTongNhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTongNhActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        selectSP.setVisible(true);

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        addSanpham.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void tblSelectSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSelectSPMouseClicked
        // TODO add your handling code here:
        txIdSpNh.setText(String.valueOf(tblSelectSP.getValueAt(tblSelectSP.getSelectedRow(), 3)));
        txTenspNh.setText(String.valueOf(tblSelectSP.getValueAt(tblSelectSP.getSelectedRow(), 4)));
        txDongiaNh.setText(String.valueOf(tblSelectSP.getValueAt(tblSelectSP.getSelectedRow(), 5)));
        selectSP.dispose();

    }//GEN-LAST:event_tblSelectSPMouseClicked

    private void txFindSelectSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txFindSelectSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txFindSelectSPActionPerformed

    private void txFindSelectSPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txFindSelectSPKeyPressed
        // TODO add your handling code here:
        TableRowSorter<TableModel> tr = new TableRowSorter<TableModel>(tblSelectSP.getModel());
        tblSelectSP.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(txFindSelectSP.getText().trim()));

    }//GEN-LAST:event_txFindSelectSPKeyPressed

    private void tblSelectNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSelectNCCMouseClicked
        // TODO add your handling code here:
        txIdNccNh.setText(String.valueOf(tblSelectNCC.getValueAt(tblSelectNCC.getSelectedRow(), 1)));
        selectNCC.dispose();
    }//GEN-LAST:event_tblSelectNCCMouseClicked

    private void txFindSelectNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txFindSelectNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txFindSelectNCCActionPerformed

    private void txFindSelectNCCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txFindSelectNCCKeyPressed
        // TODO add your handling code here:
        TableRowSorter<TableModel> tr = new TableRowSorter<TableModel>(tblSelectNCC.getModel());
        tblSelectNCC.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(txFindSelectNCC.getText().trim()));
    }//GEN-LAST:event_txFindSelectNCCKeyPressed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        selectNCC.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void txGiaAddSpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txGiaAddSpKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            txGiaAddSp.setEditable(false);

        } else {
            txGiaAddSp.setEditable(true);
        }
    }//GEN-LAST:event_txGiaAddSpKeyPressed

    private void txLspAddSpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txLspAddSpKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            txLspAddSp.setEditable(false);

        } else {
            txLspAddSp.setEditable(true);
        }
    }//GEN-LAST:event_txLspAddSpKeyPressed

    private void txSoluongAddSpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txSoluongAddSpKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            txSoluongAddSp.setEditable(false);

        } else {
            txSoluongAddSp.setEditable(true);
        }
    }//GEN-LAST:event_txSoluongAddSpKeyPressed

    private void txYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txYearActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        pnchart.removeAll();
        pnchart1.removeAll();
        pnchart3.removeAll();

        int don[] = new int[13];
        int tongdon[] = new int[13];
        int doanhthu[] = new int[13];
        for (int i = 1; i <= 12; i++) {
            don[i] = 0;
            doanhthu[i] = 0;
            tongdon[i] = 0;
        }
        YearMonth date1 = YearMonth.of(Integer.valueOf(txYear.getText()), 1);
        YearMonth date2 = YearMonth.of(Integer.valueOf(txYear.getText()), 2);
        YearMonth date3 = YearMonth.of(Integer.valueOf(txYear.getText()), 3);
        YearMonth date4 = YearMonth.of(Integer.valueOf(txYear.getText()), 4);
        YearMonth date5 = YearMonth.of(Integer.valueOf(txYear.getText()), 5);
        YearMonth date6 = YearMonth.of(Integer.valueOf(txYear.getText()), 6);
        YearMonth date7 = YearMonth.of(Integer.valueOf(txYear.getText()), 7);
        YearMonth date8 = YearMonth.of(Integer.valueOf(txYear.getText()), 8);
        YearMonth date9 = YearMonth.of(Integer.valueOf(txYear.getText()), 9);
        YearMonth date10 = YearMonth.of(Integer.valueOf(txYear.getText()), 10);
        YearMonth date11 = YearMonth.of(Integer.valueOf(txYear.getText()), 11);
        YearMonth date12 = YearMonth.of(Integer.valueOf(txYear.getText()), 12);
        XYSeries xy = new XYSeries("Số lượng đơn hàng theo tháng trong năm " + txYear.getText());
        XYSeries xy2 = new XYSeries("Doanh thu hằng tháng trong năm " + txYear.getText());
        for (HoadonDTO hd : listHD) {
            YearMonth date = YearMonth.of(hd.getNgaylapHoadonDTO().getYear(), hd.getNgaylapHoadonDTO().getMonth());

            if (date.compareTo(date1) == 0) {
                don[1]++;
                tongdon[1] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date2) == 0) {
                don[2]++;
                tongdon[2] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date3) == 0) {
                don[3]++;
                tongdon[3] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date4) == 0) {
                don[4]++;
                tongdon[4] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date5) == 0) {
                don[5]++;
                tongdon[5] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date6) == 0) {
                don[6]++;
                tongdon[6] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date7) == 0) {
                don[7]++;
                tongdon[7] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date8) == 0) {
                don[8]++;
                tongdon[8] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date9) == 0) {
                don[9]++;
                tongdon[9] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date10) == 0) {
                don[10]++;
                tongdon[10] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date11) == 0) {
                don[11]++;
                tongdon[11] += hd.getTongHoadonDTO();
            } else if (date.compareTo(date12) == 0) {
                don[12]++;
                tongdon[12] += hd.getTongHoadonDTO();
            }
        }
        for (int i = 1; i <= 12; i++) {
            xy.add(i, don[i]);
        }
        for (int i = 1; i <= 12; i++) {
            xy2.add(i, tongdon[i]);
        }

        XYSeriesCollection data = new XYSeriesCollection();
        XYSeriesCollection data2 = new XYSeriesCollection();
        data.addSeries(xy);
        data2.addSeries(xy2);
        JFreeChart chart1 = ChartFactory.createXYLineChart("", "", "", data);
        JFreeChart chart2 = ChartFactory.createXYLineChart("", "", "", data2);

        chart1.getPlot().setBackgroundPaint(new Color(255, 255, 255));
        chart2.getPlot().setBackgroundPaint(new Color(255, 255, 255));
        ChartPanel chartPn = new ChartPanel(chart1);
        ChartPanel chartPn2 = new ChartPanel(chart2);
        chartPn.setPreferredSize(new Dimension(450, 200));
        chartPn2.setPreferredSize(new Dimension(450, 200));

        int size = listSP.size();
        int a[] = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = 0;
        }
        class spbc {

            SanphamDTO spb;
            Integer soluong;

            public SanphamDTO getSpb() {
                return spb;
            }

            public void setSpb(SanphamDTO spb) {
                this.spb = spb;
            }

            public int getSoluong() {
                return soluong;
            }

            public void setSoluong(int soluong) {
                this.soluong = soluong;
            }

            public ArrayList<spbc> checkspbc(ArrayList<spbc> list, SanphamDTO sp, ChitietHoadonDTO ct) {
                for (spbc sps : list) {
                    if (sps.getSpb().getIdSanphamDTO() == sp.getIdSanphamDTO()) {
                        sps.setSoluong(sps.getSoluong() + ct.getSoluongDTO());
                        return list;
                    }
                }
                spbc spi = new spbc();
                spi.setSpb(sp);
                spi.setSoluong(ct.getSoluongDTO());
                list.add(spi);
                return list;
            }

        }

        ArrayList<spbc> listspbc = new ArrayList<>();
        YearMonth datetk = YearMonth.of(Integer.valueOf(txYear.getText()), Integer.valueOf(((String) cbthangThongke.getSelectedItem()).split(" ")[1]));
        for (SanphamDTO sp : listSP) {
            for (HoadonDTO hd : listHD) {
                if (hd.getNgaylapHoadonDTO().getYear() == datetk.getYear() && hd.getNgaylapHoadonDTO().getMonth() == datetk.getMonth()) {
                    for (ChitietHoadonDTO ct : listCTHD) {
                        if (ct.getIdHoadonDTO() == hd.getIdHoadonDTO() && ct.getIdSanphamDTO() == sp.getIdSanphamDTO()) {
                            listspbc = new spbc().checkspbc(listspbc, sp, ct);

                        }
                    }
                }
            }
        }
        Comparator<spbc> compareSoluong = (spbc o1, spbc o2) -> Integer.compare(o1.getSoluong(), o2.getSoluong());

        Collections.sort(listspbc, compareSoluong);

        DefaultCategoryDataset dta = new DefaultCategoryDataset();
        int sizeListSPBC = listspbc.size();
        if (sizeListSPBC != 0) {
            if (sizeListSPBC > 10) {
                for (int i = listspbc.size() - 1; i > listspbc.size() - 10; i--) {
                    dta.setValue(listspbc.get(i).getSoluong(), "Lượng đơn", String.valueOf(listspbc.get(i).getSpb().getIdSanphamDTO()));
                }
            } else {
                for (int i = listspbc.size() - 1; i >= 0; i--) {
                    dta.setValue(listspbc.get(i).getSoluong(), "Lượng đơn", String.valueOf(listspbc.get(i).getSpb().getIdSanphamDTO()));
                }
            }

        }

        JFreeChart chart3 = ChartFactory.createBarChart("Sản phẩm bán chạy", "ID Sản phẩm ", "Lượng đơn", dta, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = chart3.getCategoryPlot();
        p.setRangeGridlinePaint(Color.black);
        p.setBackgroundPaint(new Color(255, 255, 255));
        ChartPanel chartPn3 = new ChartPanel(chart3);
        chartPn3.setPreferredSize(new Dimension(500, 250));

        pnchart.setLayout(new FlowLayout());
        pnchart1.setLayout(new FlowLayout());
        pnchart3.setLayout(new FlowLayout());

        pnchart.add(chartPn);
        pnchart1.add(chartPn2);
        pnchart3.add(chartPn3);

        pnchart.revalidate();
        pnchart1.revalidate();
        pnchart3.revalidate();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void txDongiaNhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txDongiaNhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txDongiaNhActionPerformed

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }
    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:

        try {
            if (txIdPNH.getText().equals("") || txIdNvNh.getText().equals("") || txIdNccNh.getText().equals("") || txTongNh.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Chưa nhập đủ dữ liệu");
            } else {

                PhieunhaphangDTO pn = new PhieunhaphangDTO();
                pn.setIdPhieunhaphangDTO(Integer.parseInt(txIdPNH.getText()));
                pn.setIdNhanvienDTO(Integer.parseInt(txIdNvNh.getText()));
//                NhacungcapDTO ncc = new NhacungcapDTO();
                pn.setIdNhacungcapDTO(Integer.parseInt(txIdNccNh.getText()));
//                pn.setIdNhacungcapDTO(ncc);
                pn.setTongTiennhaphangDTO(Integer.parseInt(txTongNh.getText()));
                LocalDate date = LocalDate.now();
                pn.setNgayNhaphangDTO(convertToDateViaSqlDate(date));
                LocalTime ltime = LocalTime.now();
                Time time = Time.valueOf(ltime);
                pn.setGionNhaphangDTO(time);

                PhieunhaphangBUS pnb = new PhieunhaphangBUS();
                if (pnb.addHoadonBUS(pn)) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công!");

                    modelpnh.setRowCount(0);
                    listPNH = new PhieunhaphangBUS().readBUSs();
                    showPNH();
                    int idpnh = 0;
                    for (int c = 0; c < tbluppnh.getRowCount(); c++) {
                        if (String.valueOf(date).equals(tbluppnh.getValueAt(c, 4).toString()) || String.valueOf(time).equals(tbluppnh.getValueAt(c, 5).toString())) {
                            idpnh = Integer.parseInt(tbluppnh.getValueAt(c, 1).toString());
                        }
                    }
//                    idpnh = idpnh;
                    System.out.println(idpnh);
                    for (int d = 0; d < tblNhRight.getRowCount(); d++) {
                        ChitietphieunhaphangDTO ctpn = new ChitietphieunhaphangDTO();
                        SanphamDTO sp = new SanphamDTO();
                        sp.setIdSanphamDTO(Integer.valueOf(tblNhRight.getValueAt(d, 1).toString()));
                        ctpn.setIdSanphamDTO(sp);
                        PhieunhaphangDTO pnh2 = new PhieunhaphangDTO();
                        pnh2.setIdPhieunhaphangDTO(idpnh);
                        ctpn.setIdPhieunhaphangDTO(pnh2);
                        ctpn.setSoLuongnhapDTO(Integer.valueOf(tblNhRight.getValueAt(d, 4).toString()));
                        ctpn.setTongTienNhapDTO(Integer.valueOf(tblNhRight.getValueAt(d, 5).toString()));
                        ctpn.setDongiaNhapDTO(Integer.valueOf(tblNhRight.getValueAt(d, 3).toString()));
                        ChitietphieunhapBUS ctpnb = new ChitietphieunhapBUS();
                        if (ctpnb.themCTPNH(ctpn)) {
                            listCTPNH = new ChitietphieunhapBUS().getList();
                            int soluongspo = 0;
                            int Soluongspnhap = Integer.valueOf(tblNhRight.getValueAt(d, 4).toString());
                            for (int z = 0; z < tblsp.getRowCount(); z++) {
                                if (String.valueOf(sp.getIdSanphamDTO()).equals(tblsp.getValueAt(z, 3).toString())) {
                                    soluongspo = Integer.parseInt(tblsp.getValueAt(z, 6).toString());
                                }
                            }
                            int soluongmoi = soluongspo + Soluongspnhap;
                            sp.setSoluongsanphamDTO(soluongmoi);
                            SanphamBUS spbus = new SanphamBUS();
                            if (spbus.NhapSPmoi(sp)) {
//                                JOptionPane.showMessageDialog(null, "Nhập thành công");
                            }
                            modelbhleft.setRowCount(0);
                            modelsp.setRowCount(0);
                            listSP = new SanphamBUS().getList();
                            showBH();
                            showSP(listSP);

                        } else {
                            JOptionPane.showMessageDialog(null, "Nhập thất bại");
                        }

                    }

                    txIdNccNh.setText("");
                    txIdSpNh.setText("");
                    txTenspNh.setText("");
                    txDongiaNh.setText("");
                    txSoluongNh.setText("");
                    txTongNh.setText("");
                    modelnhright.setRowCount(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        // TODO add your handling code here:
        modelpnh.setRowCount(0);
        listPNH = new PhieunhaphangBUS().readBUSs();
        showPNH();
        modelctpnh.setRowCount(0);
    }//GEN-LAST:event_jButton53ActionPerformed


    private void tbluppnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbluppnhMouseClicked
        // TODO add your handling code here:
        int i = tbluppnh.getSelectedRow();
        int idsp = (int) tbluppnh.getValueAt(i, 1);
        modelctpnh.setRowCount(0);
        tbldownctpn.setModel(modelctpnh);
        showCTPN(getListCtpnh(listCTPNH, idsp));


    }//GEN-LAST:event_tbluppnhMouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        if (txtngayktpn.getDate() == null || txtngaybatdaupnh.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Yêu cầu chọn khoảng ngày");
        } else {
            Date dayt = txtngayktpn.getDate(); //ketthuc
            Date dayf = txtngaybatdaupnh.getDate(); //batdau

            ArrayList<PhieunhaphangDTO> lis = new ArrayList<>();
            for (PhieunhaphangDTO hd : listPNH) {
                if (hd.getNgayNhaphangDTO().compareTo(dayf) >= 0 && dayt.compareTo(hd.getNgayNhaphangDTO()) >= 0) {
                    lis.add(hd);
                } else {
                    System.out.println(hd.getNgayNhaphangDTO());
                }
            }

            modelpnh.setRowCount(0);
            tbluppnh.setModel(modelpnh);
            showPNHtt(lis);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void txtFindpnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindpnhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindpnhActionPerformed

    private void txtFindpnhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindpnhKeyPressed
        // TODO add your handling code here:
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelpnh);
        tbluppnh.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(txtFindpnh.getText().trim()));
    }//GEN-LAST:event_txtFindpnhKeyPressed

    private void txFindHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txFindHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txFindHDActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        // TODO add your handling code here:
        exportExcel(tbluppnh);
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed

        int i = tbluppnh.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn phiếu nhập muốn in!!");
        } else {
            String maPNH = tbluppnh.getValueAt(i, 1).toString();
            ReportPDFNH pdf = new ReportPDFNH(maPNH);
            JOptionPane.showMessageDialog(null, "Đã in thành công!");
        }

    }//GEN-LAST:event_jButton51ActionPerformed

    private void btnInhoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInhoadonActionPerformed
        // TODO add your handling code here:
        int i = tblhd.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn cần in");
        } else {
            new ReportPDF(String.valueOf(tblhd.getValueAt(i, 1)));
        }
    }//GEN-LAST:event_btnInhoadonActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        modelpnh.setRowCount(0);
        tbluppnh.setModel(modelpnh);
        showPNH();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void txIdSpNhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txIdSpNhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txIdSpNhActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
        int i = JOptionPane.showConfirmDialog(null, "Bạn có muốn hủy quá trình không?");
        if (i == 0) {
            txIdNccNh.setText("");
            txIdSpNh.setText("");
            txTenspNh.setText("");
            txDongiaNh.setText("");
            txSoluongNh.setText("");
            txTongNh.setText("");
            modelnhright.setRowCount(0);
        }
        if (i == 1) {

        }
        if (i == 2) {

        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void txtmaspbhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmaspbhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmaspbhActionPerformed

    private void btnthemkmbhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemkmbhActionPerformed
        // TODO add your handling code here:
        //        new ChonFormKM().setVisible(true);

        chonformkm.setVisible(true);
        modelchonKM.setColumnIdentifiers(new Object[]{
            "STT", "Mã khuyến mãi", "Tên khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Phần trăm KM", "Điều kiện", "Trạng thái"
        });
        int sttkm = tblChonKM.getSelectedRowCount();
        for (KhuyenmaiDTO km : kmBUS.getList()) {

            //                long noDay = (km.getNgayktKhuyenmaiDTO().getTime().getTime() - km.getNgaybdKhuyenmaiDTO().getTime().getTime()) / (24 * 3600 * 1000);
            //                   SimpleDateFormat format1=new SimpleDateFormat("YYYY-MM-DD");
            //                    Date now =new Date();
            //                    System.out.println(now.after());
            modelchonKM.addRow(new Object[]{
                sttkm + 1, km.getIdKhuyenmaiDTO(), km.getTenKhuyenmaiDTO(),
                km.getNgaybdKhuyenmaiDTO(), km.getNgayktKhuyenmaiDTO(),
                km.getPhantramKhuyenmaiDTO(), km.getDieukienKHuyemaiDTO(),
                km.getTrangThai()
            });
        }
        tblChonKM.setModel(modelchonKM);

    }//GEN-LAST:event_btnthemkmbhActionPerformed

    private void txTongBhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTongBhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTongBhActionPerformed

    private void btnchonKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchonKMActionPerformed
        // TODO add your handling code here:

        int chonkm = tblChonKM.getSelectedRow();
        if (chonkm == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khuyến mãi");

        } else {

            int dkkm = (Integer) tblChonKM.getValueAt(chonkm, 6);
            int makm = (Integer) (tblChonKM.getValueAt(chonkm, 1));
            int phantramkm = (Integer) tblChonKM.getValueAt(chonkm, 5);
            String trangthai = (String) tblChonKM.getValueAt(chonkm, 7);
            if (trangthai.equals("Đã kết thúc")) {
                JOptionPane.showMessageDialog(this, "Chương trình khuyến mãi đã kết thúc");
            } else {
                double tienhientai = Double.parseDouble(txTongBh.getText());
                double tienkm = tongtienhoadon - (tongtienhoadon * phantramkm) / 100;
                tongtienhoadon = tienkm;
                txTongBh.setText(String.valueOf(tongtienhoadon));
                //                   System.out.println(tongtienhoadon);

            }
            txBhKm.setText(String.valueOf(makm));
            chonformkm.dispose();
        }
    }//GEN-LAST:event_btnchonKMActionPerformed

    private void btnhuychonkmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuychonkmActionPerformed
        // TODO add your handling code here:

        chonformkm.dispose();
    }//GEN-LAST:event_btnhuychonkmActionPerformed

    private void cbchonkmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbchonkmActionPerformed
        // TODO add your handling code here:
        txtTimkimkm.setBorder(BorderFactory.createTitledBorder(cbchonkm.getSelectedItem().toString()));
        txtTimkimkm.requestFocus();
        if (!txtTimkimkm.getText().equals("")) {
            //                txSearchOnChange();
        }
    }//GEN-LAST:event_cbchonkmActionPerformed

    private void tblBhLeftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBhLeftMouseClicked
        // TODO add your handling code here:
        int masp = getSelectedSanPham(0);
        if (masp != -1) {
            showchitietchonsp(masp, 1);
        }
//        tblBhLeft.addMouseListener(new MouseAdapter() { // copy từ HienThiSanPham
//            @Override
//            public void mouseReleased(MouseEvent me) {
//                
//            }
//        });
    }//GEN-LAST:event_tblBhLeftMouseClicked

    private void txtTimkimkmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkimkmKeyPressed
        // TODO add your handling code here:
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelchonKM);
        tblChonKM.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(txtTimkimkm.getText().trim()));
    }//GEN-LAST:event_txtTimkimkmKeyPressed

    private void txIdkmThemkmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txIdkmThemkmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txIdkmThemkmActionPerformed

    private void btnDel8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel8ActionPerformed
        // TODO add your handling code here:
        txphantramThemkm.setText("");
        txTenkmThemkm.setText("");
        txDkThemkm.setText("");
        txNgaybd.setDate(null);
        txNgaykt.setDate(null);
        txaNoidungThemkm.setText("");
    }//GEN-LAST:event_btnDel8ActionPerformed

    private void btnExitThemKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitThemKMActionPerformed
        // TODO add your handling code here:
        addKhuyenmai.dispose();
    }//GEN-LAST:event_btnExitThemKMActionPerformed

    private void btnSave8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave8ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, txNgaybd.getDate());
        if (txphantramThemkm.getText().length() == 0 || txIdkmThemkm.getText().length() == 0 || txTenkmThemkm.getText().length() == 0 || txDkThemkm.getText().length() == 0 || txaNoidungThemkm.getText().length() == 0 || txNgaybd.getDate() == null || txNgaykt.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
        } else {

            KhuyenmaiDTO km = new KhuyenmaiDTO();
            km.setIdKhuyenmaiDTO(Integer.parseInt(txIdkmThemkm.getText()));
            km.setNgaybdKhuyenmaiDTO(txNgaybd.getDate());
            km.setNgayktKhuyenmaiDTO(txNgaykt.getDate());
            km.setNoidungkhuyenmaiDTO(txaNoidungThemkm.getText());
            km.setTenKhuyenmaiDTO(txTenkmThemkm.getText());
            km.setDieukienKHuyemaiDTO(Integer.parseInt(txDkThemkm.getText()));
            km.setPhantramKhuyenmaiDTO(Integer.parseInt(txphantramThemkm.getText()));
            if (new KhuyemainBUS().themKM(km)) {
                jButton37.doClick();
                addKhuyenmai.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
            }
        }
    }//GEN-LAST:event_btnSave8ActionPerformed

    private void txDkThemkmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDkThemkmKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            txDkThemkm.setEditable(false);

        } else {
            txDkThemkm.setEditable(true);
        }
    }//GEN-LAST:event_txDkThemkmKeyPressed

    private void txphantramThemkmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txphantramThemkmKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            txphantramThemkm.setEditable(false);

        } else {
            txphantramThemkm.setEditable(true);
        }
    }//GEN-LAST:event_txphantramThemkmKeyPressed

    private void txDkkmSuakmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDkkmSuakmKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            txDkkmSuakm.setEditable(false);

        } else {
            txDkkmSuakm.setEditable(true);
        }
    }//GEN-LAST:event_txDkkmSuakmKeyPressed

    private void txPhantramkmSuakmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPhantramkmSuakmKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            txPhantramkmSuakm.setEditable(false);

        } else {
            txPhantramkmSuakm.setEditable(true);
        }
    }//GEN-LAST:event_txPhantramkmSuakmKeyPressed

    private void btnComboboxKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComboboxKHActionPerformed
        // TODO add your handling code here:
        System.out.println(btnComboboxKH.getSelectedIndex());
        if (btnComboboxKH.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn chức năng");
        } else if (btnComboboxKH.getSelectedIndex() == 1) {
            int a = (Integer) tblKh.getModel().getValueAt(listKH.size() - 1, 1);
            txtIdkh.setText(String.valueOf(a + 1));
            addKhachhang.setVisible(true);
        } else {
            chonformKH.setVisible(true);
            modelchonKH.setColumnIdentifiers(new Object[]{
                "STT", "ID Khách hàng", "Họ KH", "Tên KH", "Giới tính", "Địa chỉ", "STD", "Email", "Tuổi"
            });
            int sttckh = 0;
            for (KhachhangDTO listkh : listKH) {

                System.out.println(sttckh);
                modelchonKH.addRow(new Object[]{
                    sttckh++, listkh.getMaKhachhang(), listkh.getHoKhachhang(), listkh.getTenKhachhang(),
                    listkh.getGioitinh(), listkh.getDiaChi(), listkh.getDiaChi(), listkh.getEmailKhachhang(), listkh.getTuoiKhachhang()
                });

            }
            tblChonKH.setModel(modelchonKH);

        }
    }//GEN-LAST:event_btnComboboxKHActionPerformed

    private void btnchonKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchonKHActionPerformed
        // TODO add your handling code here:
        int mkhchon = tblChonKH.getSelectedRow();
//        JOptionPane.showMessageDialog(PanelHoadon, mkhchon);
        if (mkhchon == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng");
        } else {
            String tenKH=(String)tblChonKH.getValueAt(mkhchon,3);
            int makh=(Integer)tblChonKH.getValueAt(mkhchon, 1);
            txBhIdkh.setText(tenKH+"("+makh+")");
            
              khDTO = khBus.getKh(makh);
            chonformKH.dispose();
        }


    }//GEN-LAST:event_btnchonKHActionPerformed

    private void btnhuychonKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuychonKHActionPerformed
        // TODO add your handling code here:
        chonformKH.dispose();
    }//GEN-LAST:event_btnhuychonKHActionPerformed

    private void cbchonkm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbchonkm1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbchonkm1ActionPerformed

    private void txtTimkimkm1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkimkm1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimkimkm1KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFHethong_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFHethong_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFHethong_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFHethong_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFHethong_admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AcceptDeny;
    private javax.swing.JPanel Hethong;
    private javax.swing.JLabel Labelsp;
    private javax.swing.JPanel PanelBanhang;
    private javax.swing.JPanel PanelHeader;
    private javax.swing.JPanel PanelHienthi;
    private javax.swing.JPanel PanelHoadon;
    private javax.swing.JPanel PanelKhachhang;
    private javax.swing.JPanel PanelKhuyenmai;
    private javax.swing.JPanel PanelLoaisanpham;
    private javax.swing.JPanel PanelMenuht;
    private javax.swing.JPanel PanelNhacungcap;
    private javax.swing.JPanel PanelNhanvien;
    private javax.swing.JPanel PanelNhaphang;
    private javax.swing.JPanel PanelPhieunhaphang;
    private javax.swing.JPanel PanelSanpham;
    private javax.swing.JPanel PanelTaikhoan;
    private javax.swing.JPanel PanelThongke;
    private javax.swing.JPanel Panelchithietbanhang;
    private javax.swing.JPanel Panelchitietnhaphang;
    private javax.swing.JPanel Panelsanpham;
    private javax.swing.JDialog addKhachhang;
    private javax.swing.JDialog addKhuyenmai;
    private javax.swing.JDialog addLoaisanpham;
    private javax.swing.JDialog addNCC;
    private javax.swing.JDialog addNhanvien;
    private javax.swing.JDialog addSanpham;
    private javax.swing.JDialog addTaikhoan;
    private javax.swing.JButton btnBhThem;
    private javax.swing.JButton btnBhThem1;
    private javax.swing.JButton btnCapnhathd;
    private javax.swing.JComboBox<String> btnComboboxKH;
    private javax.swing.JButton btnDangxuat;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnDel1;
    private javax.swing.JButton btnDel10;
    private javax.swing.JButton btnDel11;
    private javax.swing.JButton btnDel12;
    private javax.swing.JButton btnDel13;
    private javax.swing.JButton btnDel3;
    private javax.swing.JButton btnDel4;
    private javax.swing.JButton btnDel6;
    private javax.swing.JButton btnDel8;
    private javax.swing.JButton btnDel9;
    private javax.swing.JButton btnDelKh;
    private javax.swing.JButton btnExit8;
    private javax.swing.JButton btnExitSuaKH;
    private javax.swing.JButton btnExitSuaKM;
    private javax.swing.JButton btnExitSuaLSP;
    private javax.swing.JButton btnExitSuaNCC;
    private javax.swing.JButton btnExitSuaNV;
    private javax.swing.JButton btnExitSuaTaikhoan;
    private javax.swing.JButton btnExitThemKH;
    private javax.swing.JButton btnExitThemKM;
    private javax.swing.JButton btnExitThemNCC;
    private javax.swing.JButton btnExitThemNV;
    private javax.swing.JButton btnExitThemSP;
    private javax.swing.JButton btnExitThemSP1;
    private javax.swing.JButton btnExitThemTaikhoan;
    private javax.swing.JButton btnHuyBill;
    private javax.swing.JButton btnInhoadon;
    private javax.swing.JButton btnLochd;
    private javax.swing.JButton btnResetAddKh;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnSave10;
    private javax.swing.JButton btnSave11;
    private javax.swing.JButton btnSave12;
    private javax.swing.JButton btnSave13;
    private javax.swing.JButton btnSave4;
    private javax.swing.JButton btnSave6;
    private javax.swing.JButton btnSave7;
    private javax.swing.JButton btnSave8;
    private javax.swing.JButton btnSave9;
    private javax.swing.JButton btnSaveEditKh;
    private javax.swing.JButton btnSaveKh;
    private javax.swing.JButton btnSuaKH;
    private javax.swing.JButton btnSuaKM;
    private javax.swing.JButton btnSuaLSP;
    private javax.swing.JButton btnSuaNCC;
    private javax.swing.JButton btnSuaNV;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnSuaTaikhoan;
    private javax.swing.JButton btnThanhtoan;
    private javax.swing.JButton btnThemKM;
    private javax.swing.JButton btnThemLSP;
    private javax.swing.JButton btnThemNCC;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnThemTaikhoan;
    private javax.swing.JButton btnThemlsp;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuatechd;
    private javax.swing.JButton btnchonKH;
    private javax.swing.JButton btnchonKM;
    private javax.swing.JButton btnhuyThemsp;
    private javax.swing.JButton btnhuychonKH;
    private javax.swing.JButton btnhuychonkm;
    private javax.swing.JButton btnresetThemlsp;
    private javax.swing.JButton btnthemkmbh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbDvtAddSp;
    private javax.swing.JComboBox<String> cbDvtAddSp1;
    private javax.swing.JComboBox<String> cbTrangthaiAddSp;
    private javax.swing.JComboBox<String> cbTrangthaiAddSp1;
    private javax.swing.JComboBox<String> cbTrangthainv;
    private javax.swing.JComboBox<String> cbbchucvuSuanv;
    private javax.swing.JComboBox<String> cbboxchucvuThemnv;
    private javax.swing.JComboBox<String> cbchonkm;
    private javax.swing.JComboBox<String> cbchonkm1;
    private javax.swing.JComboBox<String> cbthangThongke;
    private javax.swing.JDialog chonformKH;
    private javax.swing.JDialog chonformkm;
    private javax.swing.JDialog editKhachhang;
    private javax.swing.JDialog editKhuyenmai;
    private javax.swing.JDialog editLoaisanpham;
    private javax.swing.JDialog editNCC;
    private javax.swing.JDialog editNhanvien;
    private javax.swing.JDialog editSanpham;
    private javax.swing.JDialog editTaikhoan;
    private javax.swing.JPanel gioitinhKH;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel labelBanhang;
    private javax.swing.JLabel labelLoaisanpham;
    private javax.swing.JLabel labelNhacungcap;
    private javax.swing.JLabel labelNhanvien;
    private javax.swing.JLabel labelPhieunhaphang;
    private javax.swing.JLabel labelkhuyenmai;
    private javax.swing.JLabel labelnh;
    private javax.swing.JLabel labelqlhd;
    private javax.swing.JLabel labelthongke;
    private javax.swing.JLabel lbAccept;
    private javax.swing.JLabel lbAcceptDeny;
    private javax.swing.JLabel lbAddNV1;
    private javax.swing.JLabel lbAddNV10;
    private javax.swing.JLabel lbAddNV11;
    private javax.swing.JLabel lbAddNV12;
    private javax.swing.JLabel lbAddNV13;
    private javax.swing.JLabel lbAddNV14;
    private javax.swing.JLabel lbAddNV15;
    private javax.swing.JLabel lbAddNV16;
    private javax.swing.JLabel lbAddNV17;
    private javax.swing.JLabel lbAddNV18;
    private javax.swing.JLabel lbAddNV2;
    private javax.swing.JLabel lbAddNV5;
    private javax.swing.JLabel lbAddNV6;
    private javax.swing.JLabel lbAddNV7;
    private javax.swing.JLabel lbAddNV8;
    private javax.swing.JLabel lbAddNV9;
    private javax.swing.JLabel lbDeny;
    private javax.swing.JLabel lbDtTk;
    private javax.swing.JLabel lbHdTk;
    private javax.swing.JLabel lbNvTk;
    private javax.swing.JLabel lbkhachhang;
    private javax.swing.JLabel lbqltk;
    private javax.swing.JPanel panelBH;
    private javax.swing.JPanel panelCaidat;
    private javax.swing.JPanel panelHD;
    private javax.swing.JPanel panelKH;
    private javax.swing.JPanel panelKM;
    private javax.swing.JPanel panelLSP;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelNCC;
    private javax.swing.JPanel panelNH;
    private javax.swing.JPanel panelNV;
    private javax.swing.JPanel panelPNH;
    private javax.swing.JPanel panelSP;
    private javax.swing.JPanel panelTaikhoan;
    private javax.swing.JPanel panelThongke;
    private javax.swing.JPanel pnAccept;
    private javax.swing.JPanel pnDeny;
    private javax.swing.JPanel pnchart;
    private javax.swing.JPanel pnchart1;
    private javax.swing.JPanel pnchart3;
    private javax.swing.JPanel qlbhLeft;
    private javax.swing.JPanel qlhdLeft;
    private javax.swing.JPanel qlkhLeft;
    private javax.swing.JPanel qlkhuyenmaiLeft;
    private javax.swing.JPanel qllspLeft;
    private javax.swing.JPanel qlnccLeft;
    private javax.swing.JPanel qlnhLeft;
    private javax.swing.JPanel qlnvLeft;
    private javax.swing.JPanel qlpnhLeft;
    private javax.swing.JPanel qlspLeft;
    private javax.swing.JPanel qltaikhoanLeft;
    private javax.swing.JPanel qlthongkeLeft;
    public javax.swing.JRadioButton rdNamEdit;
    public javax.swing.JRadioButton rdNamSuanv;
    public javax.swing.JRadioButton rdNamThemnv;
    public javax.swing.JRadioButton rdNamkh;
    public javax.swing.JRadioButton rdNuEdit;
    public javax.swing.JRadioButton rdNuSuanv;
    public javax.swing.JRadioButton rdNuThemnv;
    public javax.swing.JRadioButton rdNukh;
    private javax.swing.JButton resetncc;
    private javax.swing.JDialog selectLSP;
    private javax.swing.JDialog selectLSPE;
    private javax.swing.JDialog selectNCC;
    private javax.swing.JDialog selectNhanvien;
    private javax.swing.JDialog selectSP;
    private javax.swing.JLabel tbSpTk;
    private javax.swing.JTable tblBhLeft;
    private javax.swing.JTable tblBhRight;
    private javax.swing.JTable tblChonKH;
    private javax.swing.JTable tblChonKM;
    private javax.swing.JTable tblKh;
    private javax.swing.JTable tblNhRight;
    private javax.swing.JTable tblSelectLSP;
    private javax.swing.JTable tblSelectLSPE;
    private javax.swing.JTable tblSelectNCC;
    private javax.swing.JTable tblSelectSP;
    private javax.swing.JTable tblTimnv;
    private javax.swing.JTable tblcthd;
    private javax.swing.JTable tbldownctpn;
    private javax.swing.JTable tblhd;
    private javax.swing.JTable tblkm;
    private javax.swing.JTable tbllsp;
    private javax.swing.JTable tblncc;
    private javax.swing.JTable tblnv;
    private javax.swing.JTable tblsp;
    private javax.swing.JTable tblspsh;
    private javax.swing.JTable tbltk;
    private javax.swing.JTable tbluppnh;
    private javax.swing.ButtonGroup themkh;
    private javax.swing.JLabel txAddressError3;
    private javax.swing.JLabel txAddressError6;
    private javax.swing.JLabel txAddressError9;
    private javax.swing.JLabel txAgeError3;
    private javax.swing.JLabel txAgeError6;
    private javax.swing.JTextField txBhIdkh;
    private javax.swing.JTextField txBhIdnv;
    private javax.swing.JTextField txBhKm;
    private javax.swing.JTextField txBhMahd;
    private javax.swing.JTextField txChucvuSuatk;
    private javax.swing.JTextField txDkThemkm;
    private javax.swing.JTextField txDkkmSuakm;
    private javax.swing.JTextField txDongiaNh;
    private javax.swing.JTextField txFindBh;
    private javax.swing.JTextField txFindHD;
    private javax.swing.JTextField txFindKh;
    private javax.swing.JTextField txFindNv;
    private javax.swing.JTextField txFindSelectLSP;
    private javax.swing.JTextField txFindSelectLSPe;
    private javax.swing.JTextField txFindSelectNCC;
    private javax.swing.JTextField txFindSelectSP;
    private javax.swing.JTextField txFindSelectnv;
    private javax.swing.JTextField txFindtk;
    private javax.swing.JLabel txFnameError1;
    private javax.swing.JLabel txFnameError16;
    private javax.swing.JLabel txFnameError2;
    private javax.swing.JLabel txFnameError6;
    private javax.swing.JLabel txFnameError9;
    private javax.swing.JLabel txGenderError3;
    private javax.swing.JLabel txGenderError5;
    private javax.swing.JTextField txGiaAddSp;
    private javax.swing.JTextField txGiaAddSp1;
    private javax.swing.JTextField txGiolapBh;
    private javax.swing.JTextField txIdAddSP;
    private javax.swing.JTextField txIdAddSP1;
    private javax.swing.JTextField txIdNccNh;
    private javax.swing.JTextField txIdNvNh;
    private javax.swing.JTextField txIdPNH;
    private javax.swing.JTextField txIdSpNh;
    private javax.swing.JTextField txIdSuakm;
    private javax.swing.JTextField txIdkmThemkm;
    private javax.swing.JLabel txLnameError11;
    private javax.swing.JLabel txLnameError3;
    private javax.swing.JLabel txLnameError6;
    private javax.swing.JLabel txLnameError9;
    private javax.swing.JTextField txLspAddSp;
    private javax.swing.JTextField txLspAddSp1;
    private com.toedter.calendar.JDateChooser txNgaybd;
    private com.toedter.calendar.JDateChooser txNgaykt;
    private javax.swing.JTextField txNgaylapBh;
    private javax.swing.JTextField txPhantramkmSuakm;
    private javax.swing.JLabel txPositionError1;
    private javax.swing.JLabel txPositionError2;
    private javax.swing.JLabel txPositionError3;
    private javax.swing.JLabel txPositionError4;
    private javax.swing.JLabel txPositionError5;
    private javax.swing.JLabel txPositionError6;
    private javax.swing.JLabel txSDTError3;
    private javax.swing.JLabel txSDTError6;
    private javax.swing.JTextField txSoluongAddSp;
    private javax.swing.JTextField txSoluongAddSp1;
    private javax.swing.JTextField txSoluongNh;
    private javax.swing.JTextField txTenAddSp;
    private javax.swing.JTextField txTenAddSp1;
    private javax.swing.JTextField txTenkmSuakm;
    private javax.swing.JTextField txTenkmThemkm;
    private javax.swing.JTextField txTenspNh;
    private javax.swing.JTextField txTongBh;
    private javax.swing.JTextField txTongNh;
    private javax.swing.JTextField txYear;
    private javax.swing.JTextArea txaNoidungThemkm;
    private javax.swing.JTextField txdiachiSuancc;
    private javax.swing.JTextField txdiachiSuanv;
    private javax.swing.JTextField txdiachinccThemncc;
    private com.toedter.calendar.JDateChooser txfromLochd;
    private javax.swing.JTextField txhoSuanv;
    private javax.swing.JTextField txidSuanv;
    private javax.swing.JTextField txidSuatk;
    private javax.swing.JTextField txidThemlsp;
    private javax.swing.JTextField txidThemtk;
    private javax.swing.JTextField txidlspSualsp;
    private javax.swing.JTextField txidnccSuancc;
    private javax.swing.JTextField txidnccThemncc;
    private javax.swing.JTextField txluongSuanv;
    private javax.swing.JTextField txmkSuatk;
    private javax.swing.JTextField txmkThemtk;
    private javax.swing.JTextField txphantramThemkm;
    private javax.swing.JTextField txsdtSuancc;
    private javax.swing.JTextField txsdtSuanv;
    private javax.swing.JTextField txtDiachiKhEdit;
    private javax.swing.JTextField txtDiachikh;
    private javax.swing.JTextField txtEmailKhEdit;
    private javax.swing.JTextField txtEmailkh;
    private javax.swing.JTextField txtFindpnh;
    private javax.swing.JLabel txtHeader;
    private javax.swing.JTextField txtHoKhEdit;
    private javax.swing.JTextField txtHokh;
    private javax.swing.JTextField txtIdkh;
    private javax.swing.JTextField txtIdkhEdit;
    private javax.swing.JTextField txtSdtKhEdit;
    private javax.swing.JTextField txtSdtkh;
    private javax.swing.JTextField txtTenKhEdit;
    private javax.swing.JLabel txtTenNguoidung;
    private javax.swing.JTextField txtTenkh;
    private javax.swing.JTextField txtTimkimkm;
    private javax.swing.JTextField txtTimkimkm1;
    private javax.swing.JTextField txtTuoiKh;
    private javax.swing.JTextField txtTuoiKhEdit;
    private javax.swing.JTextField txtdiachiThemnv;
    private javax.swing.JTextField txtdongiabh;
    private javax.swing.JTextField txtenSualsp;
    private javax.swing.JTextField txtenSuancc;
    private javax.swing.JTextField txtenSuanv;
    private javax.swing.JTextField txtenSuatk;
    private javax.swing.JTextField txtenThemlsp;
    private javax.swing.JTextField txtendnSuatk;
    private javax.swing.JTextField txtendnThemtk;
    private javax.swing.JTextField txtennccThemncc;
    private javax.swing.JTextField txthoThemnv;
    private javax.swing.JTextField txtidnvThemnv;
    private javax.swing.JTextField txtluongThemnv;
    private javax.swing.JTextField txtmaspbh;
    private com.toedter.calendar.JDateChooser txtngaybatdaupnh;
    private com.toedter.calendar.JDateChooser txtngayktpn;
    private com.toedter.calendar.JDateChooser txtoLochd;
    private javax.swing.JTextField txtsdtThemnv;
    private javax.swing.JTextField txtsdtnccThemncc;
    private javax.swing.JTextField txtsoluongbh;
    private javax.swing.JTextField txttenThemnv;
    private javax.swing.JTextField txttenspbh;
    private javax.swing.JTextField txttuoiThemnv;
    private javax.swing.JTextField txtuoiSuanv;
    // End of variables declaration//GEN-END:variables

}
