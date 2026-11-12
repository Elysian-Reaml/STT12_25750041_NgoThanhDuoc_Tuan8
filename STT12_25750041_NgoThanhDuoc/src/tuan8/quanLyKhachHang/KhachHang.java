package tuan8.quanLyKhachHang;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class KhachHang {
    // Khai bao thuoc tinh chung
    protected String maKhachHang;
    protected String hoTen;
    protected LocalDate ngayLapHoaDon;
    protected double soKw;
    protected double donGia;

    // Constructor day du tham so
    public KhachHang(String ma, String hoTen, LocalDate ngay, double soKw, double donGia) {
        this.maKhachHang = ma;
        this.hoTen = hoTen;
        this.ngayLapHoaDon = ngay;
        this.soKw = soKw;
        this.donGia = donGia;
    }

    // Phuong thuc truu tuong de lop con override
    public abstract double thanhTien();
    public abstract String getLoai();

    // Getters va Setters
    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDate getNgayLapHoaDon() {
        return ngayLapHoaDon;
    }

    public void setNgayLapHoaDon(LocalDate ngayLapHoaDon) {
        this.ngayLapHoaDon = ngayLapHoaDon;
    }

    public double getSoKw() {
        return soKw;
    }

    public void setSoKw(double soKw) {
        this.soKw = soKw;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String ngayStr = (ngayLapHoaDon != null) ? ngayLapHoaDon.format(fmt) : "N/A";
        return String.format("%-8s | %-20s | Ngày: %-10s | %6.1f kW | %8.1f đ | TT: %,12.1f đ",
                maKhachHang, hoTen, ngayStr, soKw, donGia, thanhTien());
    }
}
