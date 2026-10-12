import java.util.ArrayList;
import java.util.List;

public class QuanLyTaiLieu {
    private final List<TaiLieu> danhSach = new ArrayList<>();

    public boolean themTaiLieu(TaiLieu taiLieu) {
        if (taiLieu == null || taiLieu.getMaTaiLieu() == null ||
                taiLieu.getMaTaiLieu().trim().isEmpty() ||
                timTheoMa(taiLieu.getMaTaiLieu()) != null) {
            return false;
        }
        danhSach.add(taiLieu);
        return true;
    }

    public TaiLieu timTheoMa(String ma) {
        if (ma == null) return null;
        for (TaiLieu tl : danhSach) {
            if (tl.getMaTaiLieu().equalsIgnoreCase(ma.trim())) return tl;
        }
        return null;
    }

    public boolean xoaTaiLieu(String ma) {
        TaiLieu tl = timTheoMa(ma);
        return tl != null && danhSach.remove(tl);
    }

    public void hienThiTatCa() {
        if (danhSach.isEmpty()) {
            System.out.println("Danh sach tai lieu dang rong.");
            return;
        }
        for (TaiLieu tl : danhSach) tl.hienThiThongTin();
    }

    public void timTheoLoai(int loai) {
        boolean coKetQua = false;
        for (TaiLieu tl : danhSach) {
            if ((loai == 1 && tl instanceof Sach) ||
                (loai == 2 && tl instanceof TapChi) ||
                (loai == 3 && tl instanceof Bao)) {
                tl.hienThiThongTin();
                coKetQua = true;
            }
        }
        if (!coKetQua) System.out.println("Khong tim thay tai lieu thuoc loai nay.");
    }
}
