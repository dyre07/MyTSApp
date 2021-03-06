package id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication.adapter.GuruAdapter;
import id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication.model.Guru;

public class GuruActivity extends AppCompatActivity {
    private static final String JSON_URL = "http://mytsapp.16mb.com/android/guru.php";
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    GuruAdapter guruAdapter;
    ListView listView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guru);

        progressDialog = new ProgressDialog(GuruActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();
        getJSON();
    }

    private void getJSON() {
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(GuruActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        TextView textView = (TextView) findViewById(R.id.textview);
        textView.setText(response);
        json_string = response;
        progressDialog.dismiss();
        parseJSON();
    }

    public void parseJSON() {
        listView = (ListView) findViewById(R.id.lv);
        guruAdapter = new GuruAdapter(this, R.layout.row_layout_guru);
        listView.setAdapter(guruAdapter);

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("result");
            int count = 0;
            String teacher, pelajaran, kelas, tugas;
            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                teacher = JO.getString("guru");
                pelajaran = JO.getString("pelajaran");
                kelas = JO.getString("kelas");
                tugas = JO.getString("tugas");
                Guru guru = new Guru(teacher, pelajaran, kelas, tugas);
                guruAdapter.add(guru);
                count++;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
