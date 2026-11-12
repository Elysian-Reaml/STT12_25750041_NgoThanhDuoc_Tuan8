package tuan8.quanLyKhachHang;

import java.time.LocalDate;

/**
 * Lớp KhachHangNN kế thừa từ KhachHangNuocNgoai để đảm bảo tính tương thích
 * cho cả hai cách đặt tên (KhachHangNN hoặc KhachHangNuocNgoai).
 */
public class KhachHangNN extends KhachHangNuocNgoai {

    public KhachHangNN(String ma, String hoTen, LocalDate ngay, double soKw, double donGia, String quocTich) {
        super(ma, hoTen, ngay, soKw, donGia, quocTich);
    }
}
