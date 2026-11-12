package tuan8.quanLyKhachHang;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

// ==========================================
// CLASS MAIN: Quản lý khách hàng với menu console
// ==========================================
public class Main {
    static Scanner sc = new Scanner(System.in);
    static ListQuanLy<KhachHang> ql = new ListQuanLy<>();

    public static void main(String[] args) {
        // Nạp sẵn dữ liệu mẫu để chạy thử ngay
        khoiTaoDuLieuMau();

        while (true) {
            hienThiMenu();
            System.out.print("Chọn chức năng: ");
            try {
                int chon = Integer.parseInt(sc.nextLine().trim());
                switch (chon) {
                    case 1: chucNangThem(); break;
                    case 2: chucNangXoa(); break;
                    case 3: chucNangSua(); break;
                    case 4: chucNangTimTheoTen(); break;
                    case 5: chucNangTimTheoMa(); break;
                    case 6: hienThi(ql.sapXepTheoThanhTien()); break;
                    case 7: hienThi(ql.sapXepTheoTen()); break;
                    case 8: chucNangThongKeTien(); break;
                    case 9: chucNangNhomTheoLoai(); break;
                    case 10: chucNangLocTien(); break;
                    case 11: chucNangThongKeChiTiet(); break;
                    case 12: hienThi(ql.getDanhSach()); break;
                    case 0:
                        System.out.println("Tạm biệt!");
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (Exception e) {
                System.out.println("Lỗi nhập liệu: " + e.getMessage());
            }
        }
    }

    // ==========================================
    // KHỞI TẠO DỮ LIỆU MẪU
    // ==========================================
    static void khoiTaoDuLieuMau() {
        ql.themNhieu(
            new KhachHangVietNam("VN01", "Nguyen Van A", LocalDate.of(2018, 9, 10), 100, 1500, "sinh hoạt", 50),
            new KhachHangVietNam("VN02", "Tran Thi B", LocalDate.of(2018, 10, 5), 200, 2000, "kinh doanh", 100),
            new KhachHangVietNam("VN03", "Le Van C", LocalDate.of(2019, 1, 20), 80, 1800, "sản xuất", 60),
            new KhachHangNuocNgoai("NN01", "John Smith", LocalDate.of(2018, 9, 25), 150, 2500, "Mỹ"),
            new KhachHangNuocNgoai("NN02", "Yuki Tanaka", LocalDate.of(2018, 8, 15), 120, 3000, "Nhật"),
            new KhachHangNuocNgoai("NN03", "Kim Min Su", LocalDate.of(2019, 3, 8), 90, 2800, "Hàn")
        );
    }

    // ==========================================
    // MENU CONSOLE
    // ==========================================
    static void hienThiMenu() {
        System.out.println("\n========== MENU QUẢN LÝ KHÁCH HÀNG ==========");
        System.out.println("1.  Thêm khách hàng");
        System.out.println("2.  Xóa theo mã khách hàng");
        System.out.println("3.  Sửa theo mã khách hàng");
        System.out.println("4.  Tìm theo tên khách hàng");
        System.out.println("5.  Tìm theo mã (tiền tố)");
        System.out.println("6.  Sắp xếp theo thành tiền tăng dần");
        System.out.println("7.  Sắp xếp theo tên khách hàng");
        System.out.println("8.  Thống kê tổng tiền theo loại (VN / Nước ngoài)");
        System.out.println("9.  Nhóm danh sách theo loại (VN / Nước ngoài)");
        System.out.println("10. Lọc khách hàng có thành tiền trên mức X");
        System.out.println("11. Đếm số lượng theo loại & Trung bình thành tiền KH nước ngoài");
        System.out.println("12. Xuất tất cả danh sách");
        System.out.println("0.  Thoát");
    }

    // ==========================================
    // CÁC HÀM XỬ LÝ CHỨC NĂNG
    // ==========================================

    static void chucNangThem() {
        KhachHang kh = nhapKH();
        if (kh != null) {
            ql.them(kh);
            System.out.println("Đã thêm khách hàng thành công!");
        }
    }

    static void chucNangXoa() {
        System.out.print("Nhập mã khách hàng cần xóa: ");
        String ma = sc.nextLine().trim();
        boolean ok = ql.xoa(ma);
        System.out.println(ok ? "Đã xóa thành công!" : "Không tìm thấy khách hàng!");
    }

    static void chucNangSua() {
        System.out.print("Nhập mã khách hàng cần sửa: ");
        String ma = sc.nextLine().trim();
        System.out.println("Nhập thông tin mới:");
        KhachHang khMoi = nhapKH();
        if (khMoi != null) {
            boolean ok = ql.sua(ma, khMoi);
            System.out.println(ok ? "Đã cập nhật thành công!" : "Không tìm thấy khách hàng!");
        }
    }

    static void chucNangTimTheoTen() {
        System.out.print("Nhập tên cần tìm: ");
        String ten = sc.nextLine().trim();
        hienThi(ql.timKiemTheoTen(ten));
    }

    static void chucNangTimTheoMa() {
        System.out.print("Nhập tiền tố mã cần tìm: ");
        String prefix = sc.nextLine().trim();
        hienThi(ql.timKiemTheoMa(prefix));
    }

    static void chucNangThongKeTien() {
        System.out.println("--- Thống kê tổng thành tiền theo loại ---");
        ql.thongKeThanhTienTheoLoai().forEach((k, v) ->
            System.out.printf("%-15s : %,15.1f đ%n", k, v));
    }

    static void chucNangNhomTheoLoai() {
        System.out.println("--- Nhóm danh sách theo loại ---");
        ql.groupByLoai().forEach((k, v) -> {
            System.out.println("\n[Loại: " + k + "] (Số lượng: " + v.size() + ")");
            v.forEach(kh -> System.out.println("  " + kh));
        });
    }

    static void chucNangLocTien() {
        System.out.print("Nhập mức thành tiền: ");
        double muc = Double.parseDouble(sc.nextLine().trim());
        hienThi(ql.locThanhTienTren(muc));
    }

    static void chucNangThongKeChiTiet() {
        System.out.println("--- Báo cáo thống kê chi tiết ---");
        int countVN = ql.dem(kh -> kh instanceof KhachHangVietNam);
        int countNN = ql.dem(kh -> kh instanceof KhachHangNuocNgoai || kh instanceof KhachHangNN);
        double tbNN = ql.tinhTrungBinhThanhTienNN();

        System.out.println("Tổng số khách hàng Việt Nam: " + countVN);
        System.out.println("Tổng số khách hàng Nước ngoài: " + countNN);
        System.out.printf("Trung bình thành tiền của KH Nước ngoài: %,.1f đ%n", tbNN);
    }

    // ==========================================
    // HÀM NHẬP KHÁCH HÀNG
    // ==========================================
    static KhachHang nhapKH() {
        System.out.print("Mã KH: ");
        String ma = sc.nextLine().trim();

        System.out.print("Họ tên: ");
        String ten = sc.nextLine().trim();

        System.out.print("Ngày lập hóa đơn (yyyy-MM-dd): ");
        LocalDate ngay = LocalDate.parse(sc.nextLine().trim());

        System.out.print("Số kW tiêu thụ: ");
        double soKw = Double.parseDouble(sc.nextLine().trim());

        System.out.print("Đơn giá: ");
        double donGia = Double.parseDouble(sc.nextLine().trim());

        System.out.println("Chọn loại khách hàng: 1. Việt Nam | 2. Nước ngoài");
        System.out.print("Chọn: ");
        int loai = Integer.parseInt(sc.nextLine().trim());

        if (loai == 1) {
            System.out.print("Đối tượng (sinh hoạt / kinh doanh / sản xuất): ");
            String doiTuong = sc.nextLine().trim();
            System.out.print("Định mức: ");
            double dinhMuc = Double.parseDouble(sc.nextLine().trim());
            return new KhachHangVietNam(ma, ten, ngay, soKw, donGia, doiTuong, dinhMuc);
        } else {
            System.out.print("Quốc tịch: ");
            String quocTich = sc.nextLine().trim();
            return new KhachHangNuocNgoai(ma, ten, ngay, soKw, donGia, quocTich);
        }
    }

    // ==========================================
    // HIỂN THỊ DANH SÁCH
    // ==========================================
    static void hienThi(List<KhachHang> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Danh sách rỗng!");
        } else {
            System.out.println("------------------------------------------------------------------------------------------------");
            list.forEach(System.out::println);
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.println("Tổng cộng: " + list.size() + " khách hàng.");
        }
    }
}
