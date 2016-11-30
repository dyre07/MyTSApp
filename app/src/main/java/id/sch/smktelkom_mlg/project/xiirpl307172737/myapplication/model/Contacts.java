package id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication.model;

/**
 * Created by Dyre on 11/21/2016.
 */

public class Contacts {
    private String jam, untuk, pengumuman;

    public Contacts(String jam, String untuk, String pengumuman) {
        this.setJam(jam);
        this.setUntuk(untuk);
        this.setPengumuman(pengumuman);
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getUntuk() {
        return untuk;
    }

    public void setUntuk(String untuk) {
        this.untuk = untuk;
    }

    public String getPengumuman() {
        return pengumuman;
    }

    public void setPengumuman(String pengumuman) {
        this.pengumuman = pengumuman;
    }
}
