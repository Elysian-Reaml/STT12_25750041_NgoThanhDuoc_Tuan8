package tuan8.quanLyKhachHang;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

// ==========================================
// INTERFACE: IQuanLy<T> (Generic Interface Quản Lý)
// ==========================================
public interface IQuanLy<T> {
    // 1. Thêm 1 đối tượng
    void them(T t);

    // 2. Thêm nhiều đối tượng cùng lúc
    @SuppressWarnings("unchecked")
    void themNhieu(T... items);

    // 3. Xóa đối tượng theo mã
    boolean xoa(String ma);

    // 4. Sửa thông tin đối tượng theo mã
    boolean sua(String ma, T tMoi);

    // 5. Tìm kiếm theo tên (chứa từ khóa, không phân biệt hoa thường)
    List<T> timKiemTheoTen(String ten);

    // 6. Tìm kiếm theo tiền tố mã
    List<T> timKiemTheoMa(String prefix);

    // 7. Sắp xếp danh sách theo thành tiền tăng dần (tương ứng sapXepTheoLuong)
    List<T> sapXepTheoThanhTien();

    // 8. Sắp xếp danh sách theo tên A-Z
    List<T> sapXepTheoTen();

    // 9. Thống kê tổng thành tiền theo loại (Việt Nam / Nước ngoài)
    Map<String, Double> thongKeThanhTienTheoLoai();

    // 10. Gom nhóm đối tượng theo loại (Việt Nam / Nước ngoài)
    Map<String, List<T>> groupByLoai();

    // 11. Lọc các đối tượng có thành tiền lớn hơn mức quy định
    List<T> locThanhTienTren(double muc);

    // 12. Đếm số lượng đối tượng thỏa điều kiện (Predicate)
    int dem(Predicate<T> predicate);

    // 13. Tính trung bình thành tiền của khách hàng nước ngoài
    double tinhTrungBinhThanhTienNN();

    // 14. Lấy toàn bộ danh sách
    List<T> getDanhSach();

    // ===== CÁC METHOD TƯƠNG THÍCH THEO TÊN CỦA QUẢN LÝ NHÂN SỰ =====
    default List<T> sapXepTheoLuong() {
        return sapXepTheoThanhTien();
    }

    default Map<String, Double> thongKeTheoLoai() {
        return thongKeThanhTienTheoLoai();
    }

    default Map<String, Double> thongKeLuongTheoLoai() {
        return thongKeThanhTienTheoLoai();
    }

    default List<T> locLuongTren(double muc) {
        return locThanhTienTren(muc);
    }
}
