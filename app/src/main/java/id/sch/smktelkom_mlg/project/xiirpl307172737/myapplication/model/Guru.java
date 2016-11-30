package id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication.model;

/**
 * Created by Dyre on 11/22/2016.
 */

public class Guru {
    private String guru, pelajaran, kelas, tugas;

    public Guru(String guru, String pelajaran, String kelas, String tugas) {
        this.setGuru(guru);
        this.setPelajaran(pelajaran);
        this.setKelas(kelas);
        this.setTugas(tugas);
    }

    public String getGuru() {
        return guru;
    }

    public void setGuru(String guru) {
        this.guru = guru;
    }

    public String getPelajaran() {
        return pelajaran;
    }

    public void setPelajaran(String pelajaran) {
        this.pelajaran = pelajaran;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getTugas() {
        return tugas;
    }

    public void setTugas(String tugas) {
        this.tugas = tugas;
    }
}
