package tuan8.quanLyKhachHang;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// ==========================================
// CLASS QUẢN LÝ: ListQuanLy<T extends KhachHang>
// ==========================================
public class ListQuanLy<T extends KhachHang> implements IQuanLy<T> {
    private List<T> danhSach = new ArrayList<>();

    public ListQuanLy() {
    }

    // 1. Thêm 1 khách hàng (kiểm tra trùng mã)
    @Override
    public void them(T kh) {
        if (timTheoMa(kh.getMaKhachHang()) != null) {
            System.out.println("Mã khách hàng đã tồn tại: " + kh.getMaKhachHang());
            return;
        }
        danhSach.add(kh);
    }

    // 2. Thêm nhiều khách hàng cùng lúc
    @SafeVarargs
    @Override
    public final void themNhieu(T... items) {
        for (T item : items) {
            them(item);
        }
    }

    // Hàm phụ trợ private: tìm theo mã
    private T timTheoMa(String ma) {
        return danhSach.stream()
                .filter(kh -> kh.getMaKhachHang().equalsIgnoreCase(ma))
                .findFirst()
                .orElse(null);
    }

    // 3. Xóa khách hàng theo mã
    @Override
    public boolean xoa(String ma) {
        return danhSach.removeIf(kh -> kh.getMaKhachHang().equalsIgnoreCase(ma));
    }

    // 4. Sửa khách hàng theo mã
    @Override
    public boolean sua(String ma, T tMoi) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaKhachHang().equalsIgnoreCase(ma)) {
                danhSach.set(i, tMoi);
                return true;
            }
        }
        return false;
    }

    // 5. Tìm kiếm theo tên (chứa từ khóa, không phân biệt hoa thường)
    @Override
    public List<T> timKiemTheoTen(String ten) {
        String kw = ten.toLowerCase();
        return danhSach.stream()
                .filter(kh -> kh.getHoTen().toLowerCase().contains(kw))
                .collect(Collectors.toList());
    }

    // 6. Tìm kiếm theo tiền tố mã
    @Override
    public List<T> timKiemTheoMa(String prefix) {
        return danhSach.stream()
                .filter(kh -> kh.getMaKhachHang().toLowerCase().startsWith(prefix.toLowerCase()))
                .collect(Collectors.toList());
    }

    // 7. Sắp xếp theo thành tiền tăng dần
    @Override
    public List<T> sapXepTheoThanhTien() {
        return danhSach.stream()
                .sorted(Comparator.comparingDouble(KhachHang::thanhTien))
                .collect(Collectors.toList());
    }

    // 8. Sắp xếp theo họ tên A-Z
    @Override
    public List<T> sapXepTheoTen() {
        return danhSach.stream()
                .sorted(Comparator.comparing(KhachHang::getHoTen))
                .collect(Collectors.toList());
    }

    // 9. Thống kê tổng thành tiền theo từng loại (Việt Nam / Nước ngoài)
    @Override
    public Map<String, Double> thongKeThanhTienTheoLoai() {
        return danhSach.stream().collect(Collectors.groupingBy(
                KhachHang::getLoai,
                Collectors.summingDouble(KhachHang::thanhTien)
        ));
    }

    // 10. Gom nhóm theo loại (Việt Nam / Nước ngoài)
    @Override
    public Map<String, List<T>> groupByLoai() {
        return danhSach.stream()
                .collect(Collectors.groupingBy(KhachHang::getLoai));
    }

    // 11. Lọc khách hàng có thành tiền > mức quy định
    @Override
    public List<T> locThanhTienTren(double muc) {
        return danhSach.stream()
                .filter(kh -> kh.thanhTien() > muc)
                .collect(Collectors.toList());
    }

    // 12. Đếm số lượng theo điều kiện
    @Override
    public int dem(Predicate<T> predicate) {
        return (int) danhSach.stream().filter(predicate).count();
    }

    // 13. Tính trung bình thành tiền của khách hàng nước ngoài
    @Override
    public double tinhTrungBinhThanhTienNN() {
        return danhSach.stream()
                .filter(kh -> kh instanceof KhachHangNuocNgoai || kh instanceof KhachHangNN)
                .mapToDouble(KhachHang::thanhTien)
                .average()
                .orElse(0.0);
    }

    // 14. Lấy danh sách khách hàng
    @Override
    public List<T> getDanhSach() {
        return danhSach;
    }
}
