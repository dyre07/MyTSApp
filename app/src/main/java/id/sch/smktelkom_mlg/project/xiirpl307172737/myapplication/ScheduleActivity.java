package id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ScheduleActivity extends AppCompatActivity {

    Spinner spJP, spDay;
    TextView tvHasil;
    String[][] arDay = {{"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"},
            {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"},
            {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"},
            {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"},
            {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"},
            {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"},
            {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"},
            {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"},
            {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"},
            {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}, {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"},
            {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}};
    ArrayList<String> listDay = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        spJP = (Spinner) findViewById(R.id.spinnerJP);
        spDay = (Spinner) findViewById(R.id.spinnerDay);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listDay);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDay.setAdapter(adapter);

        spJP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {

                listDay.clear();
                listDay.addAll(Arrays.asList(arDay[pos]));
                adapter.notifyDataSetChanged();
                spDay.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        findViewById(R.id.buttonProses).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doClick();
            }
        });
    }

    private void doClick() {
        tvHasil.setText("Jadwal Pelajaran " + spJP.getSelectedItem().toString() + " Hari " + spDay.getSelectedItem().toString());
    }
}