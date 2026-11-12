package tuan8.quanLyKhachHang;

import java.time.LocalDate;

public class KhachHangVietNam extends KhachHang {
    private String doiTuong;
    private double dinhMuc;

    public KhachHangVietNam(String ma, String hoTen, LocalDate ngay, double soKw, double donGia,
                           String doiTuong, double dinhMuc) {
        super(ma, hoTen, ngay, soKw, donGia);
        this.doiTuong = doiTuong;
        this.dinhMuc = dinhMuc;
    }

    public String getDoiTuong() {
        return doiTuong;
    }

    public void setDoiTuong(String doiTuong) {
        this.doiTuong = doiTuong;
    }

    public double getDinhMuc() {
        return dinhMuc;
    }

    public void setDinhMuc(double dinhMuc) {
        this.dinhMuc = dinhMuc;
    }

    @Override
    public double thanhTien() {
        return soKw <= dinhMuc
                ? soKw * donGia
                : dinhMuc * donGia + (soKw - dinhMuc) * donGia * 2.5;
    }

    @Override
    public String getLoai() {
        return "Việt Nam";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | ĐT: %-10s | Định mức: %6.1f", doiTuong, dinhMuc);
    }
}
