import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class DemoYeuCau2 {
    private static final Scanner sc = new Scanner(System.in);
    private static final QuanLyTaiLieu ql = new QuanLyTaiLieu();
    private static final DateTimeFormatter DINH_DANG_NGAY =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    private static String nhapChuoi(String nhan) {
        while (true) {
            System.out.print(nhan);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Khong duoc de trong!");
        }
    }

    private static int nhapSo(String nhan, int min, int max) {
        while (true) {
            System.out.print(nhan);
            try {
                int n = Integer.parseInt(sc.nextLine().trim());
                if (n >= min && n <= max) return n;
            } catch (NumberFormatException e) {
                // Yeu cau nhap lai.
            }
            System.out.println("Gia tri khong hop le, vui long nhap lai!");
        }
    }

    private static String nhapNgay() {
        while (true) {
            String ngay = nhapChuoi("Ngay phat hanh (dd/MM/yyyy): ");
            try {
                LocalDate.parse(ngay, DINH_DANG_NGAY);
                return ngay;
            } catch (DateTimeParseException e) {
                System.out.println("Ngay khong hop le!");
            }
        }
    }

    private static void themMoi() {
        System.out.println("1. Sach | 2. Tap chi | 3. Bao");
        int loai = nhapSo("Chon loai: ", 1, 3);
        String ma = nhapChuoi("Ma tai lieu: ");
        if (ql.timTheoMa(ma) != null) {
            System.out.println("Ma tai lieu da ton tai!");
            return;
        }
        String nxb = nhapChuoi("Ten nha xuat ban: ");
        int soBan = nhapSo("So ban phat hanh: ", 0, Integer.MAX_VALUE);
        TaiLieu tl;
        if (loai == 1) {
            String tacGia = nhapChuoi("Ten tac gia: ");
            int soTrang = nhapSo("So trang: ", 1, Integer.MAX_VALUE);
            tl = new Sach(ma, nxb, soBan, tacGia, soTrang);
        } else if (loai == 2) {
            int soPhatHanh = nhapSo("So phat hanh: ", 1, Integer.MAX_VALUE);
            int thang = nhapSo("Thang phat hanh: ", 1, 12);
            tl = new TapChi(ma, nxb, soBan, soPhatHanh, thang);
        } else {
            tl = new Bao(ma, nxb, soBan, nhapNgay());
        }
        System.out.println(ql.themTaiLieu(tl) ? "Them tai lieu thanh cong!" : "Them that bai!");
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n========== QUAN LY THU VIEN ==========");
            System.out.println("1. Them moi tai lieu");
            System.out.println("2. Xoa tai lieu theo ma");
            System.out.println("3. Hien thi danh sach tai lieu");
         
            System.out.println("======================================");
            int chon = nhapSo("Nhap lua chon: ", 1, 3);
            switch (chon) {
                case 1:
                    themMoi();
                    break;
                case 2:
                    String ma = nhapChuoi("Nhap ma can xoa: ");
                    System.out.println(ql.xoaTaiLieu(ma) ? "Xoa thanh cong!" : "Khong tim thay ma tai lieu!");
                    break;
                case 3:
                    ql.hienThiTatCa();
                    break;
              
                default:
                    break;
            }
        }
    }
}
