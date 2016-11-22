package id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dyre on 11/22/2016.
 */

public class GuruAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public GuruAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Guru object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        GuruHolder guruHolder;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout_guru, parent, false);
            guruHolder = new GuruAdapter.GuruHolder();
            guruHolder.tx_guru = (TextView) row.findViewById(R.id.tx_guru);
            guruHolder.tx_pelajaran = (TextView) row.findViewById(R.id.tx_pelajaran);
            guruHolder.tx_kelas = (TextView) row.findViewById(R.id.tx_kelas);
            guruHolder.tx_tugas = (TextView) row.findViewById(R.id.tx_tugas);
            row.setTag(guruHolder);
        } else {
            guruHolder = (GuruAdapter.GuruHolder) row.getTag();
        }
        Guru guru = (Guru) this.getItem(position);
        guruHolder.tx_guru.setText(guru.getGuru());
        guruHolder.tx_pelajaran.setText(guru.getPelajaran());
        guruHolder.tx_kelas.setText(guru.getKelas());
        guruHolder.tx_tugas.setText(guru.getTugas());
        return row;
    }

    static class GuruHolder {
        TextView tx_guru, tx_pelajaran, tx_kelas, tx_tugas;
    }
}
