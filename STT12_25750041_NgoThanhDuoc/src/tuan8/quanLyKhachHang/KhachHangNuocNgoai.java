package tuan8.quanLyKhachHang;

import java.time.LocalDate;

public class KhachHangNuocNgoai extends KhachHang {
    private String quocTich;

    public KhachHangNuocNgoai(String ma, String hoTen, LocalDate ngay, double soKw, double donGia,
                             String quocTich) {
        super(ma, hoTen, ngay, soKw, donGia);
        this.quocTich = quocTich;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    @Override
    public double thanhTien() {
        return soKw * donGia;
    }

    @Override
    public String getLoai() {
        return "Nước ngoài";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Quốc tịch: %s", quocTich);
    }
}
