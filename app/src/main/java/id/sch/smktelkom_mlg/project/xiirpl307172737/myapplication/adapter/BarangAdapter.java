package id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication.R;
import id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication.model.Barang;
import id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication.model.Contacts;

/**
 * Created by Ariffani on 12/1/2016.
 */

public class BarangAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public BarangAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Barang object) {
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
        BarangAdapter.BarangHolder barangHolder;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout_barang, parent, false);
            barangHolder = new BarangAdapter.BarangHolder();
            barangHolder.tx_tanggal = (TextView) row.findViewById(R.id.tx_tanggal);
            barangHolder.tx_kelas = (TextView) row.findViewById(R.id.tx_kelas);
            barangHolder.tx_namabarang = (TextView) row.findViewById(R.id.tx_namabarang);
            row.setTag(barangHolder);
        } else {
            barangHolder = (BarangAdapter.BarangHolder) row.getTag();
        }
        Barang barang = (Barang) this.getItem(position);
        barangHolder.tx_tanggal.setText(barang.getTanggal());
        barangHolder.tx_kelas.setText(barang.getKelas());
        barangHolder.tx_namabarang.setText(barang.getNamabarang());
        return row;
    }

    static class BarangHolder {
        TextView tx_tanggal, tx_kelas, tx_namabarang;
    }
}
