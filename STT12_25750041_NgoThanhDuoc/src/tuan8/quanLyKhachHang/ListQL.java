package tuan8.quanLyKhachHang;

/**
 * Lớp ListQL kế thừa từ ListQuanLy để đảm bảo tương thích 
 * nếu có sử dụng tên lớp viết tắt ListQL.
 */
public class ListQL<T extends KhachHang> extends ListQuanLy<T> {
    public ListQL() {
        super();
    }
}