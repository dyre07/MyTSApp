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
 * Created by Dyre on 11/21/2016.
 */

public class ContactAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public ContactAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Contacts object) {
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
        ContactHolder contactHolder;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            contactHolder = new ContactHolder();
            contactHolder.tx_jam = (TextView) row.findViewById(R.id.tx_jam);
            contactHolder.tx_untuk = (TextView) row.findViewById(R.id.tx_untuk);
            contactHolder.tx_pengumuman = (TextView) row.findViewById(R.id.tx_pengumuman);
            row.setTag(contactHolder);
        } else {
            contactHolder = (ContactHolder) row.getTag();
        }
        Contacts contacts = (Contacts) this.getItem(position);
        contactHolder.tx_jam.setText(contacts.getJam());
        contactHolder.tx_untuk.setText(contacts.getUntuk());
        contactHolder.tx_pengumuman.setText(contacts.getPengumuman());
        return row;
    }

    static class ContactHolder {
        TextView tx_jam, tx_untuk, tx_pengumuman;
    }
}
