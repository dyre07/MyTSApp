package id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication.model;

/**
 * Created by Ariffani on 12/1/2016.
 */

public class Barang {
    private String tanggal, kelas, namabarang;

    public Barang(String tanggal, String kelas, String namabarang) {
        this.setTanggal(tanggal);
        this.setKelas(kelas);
        this.setNamabarang(namabarang);
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getNamabarang() {
        return namabarang;
    }

    public void setNamabarang(String namabarang) {
        this.namabarang = namabarang;
    }
}
