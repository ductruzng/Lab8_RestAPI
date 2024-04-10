package trungndph39729.fpoly.dethithu.model;

public class Student {
    private String _id,
            hoten_ph39729,
            quequan_ph39729,
            diem_ph39729,
            hinh_anh_ph39729;

    public Student() {
    }

    public Student(String _id, String hoten_ph39729, String quequan_ph39729, String diem_ph39729, String hinh_anh_ph39729) {
        this._id = _id;
        this.hoten_ph39729 = hoten_ph39729;
        this.quequan_ph39729 = quequan_ph39729;
        this.diem_ph39729 = diem_ph39729;
        this.hinh_anh_ph39729 = hinh_anh_ph39729;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getHoten_ph39729() {
        return hoten_ph39729;
    }

    public void setHoten_ph39729(String hoten_ph39729) {
        this.hoten_ph39729 = hoten_ph39729;
    }

    public String getQuequan_ph39729() {
        return quequan_ph39729;
    }

    public void setQuequan_ph39729(String quequan_ph39729) {
        this.quequan_ph39729 = quequan_ph39729;
    }

    public String getDiem_ph39729() {
        return diem_ph39729;
    }

    public void setDiem_ph39729(String diem_ph39729) {
        this.diem_ph39729 = diem_ph39729;
    }

    public String getHinh_anh_ph39729() {
        return hinh_anh_ph39729;
    }

    public void setHinh_anh_ph39729(String hinh_anh_ph39729) {
        this.hinh_anh_ph39729 = hinh_anh_ph39729;
    }
}
