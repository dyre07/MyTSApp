package id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication.adapter.BarangAdapter;
import id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication.model.Barang;

public class BarangActivity extends AppCompatActivity {

    private static final String JSON_URL = "http://mytsapp.16mb.com/android/barang.php";
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    BarangAdapter barangAdapter;
    ListView listView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);

        progressDialog = new ProgressDialog(BarangActivity.this,
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
                        Toast.makeText(BarangActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
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
        listView = (ListView) findViewById(R.id.listview);
        barangAdapter = new BarangAdapter(this, R.layout.row_layout_barang);
        listView.setAdapter(barangAdapter);

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("result");
            int count = 0;
            String tanggal, kelas, namabarang;
            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                tanggal = JO.getString("tanggal");
                kelas = JO.getString("kelas");
                namabarang = JO.getString("namabarang");
                Barang barang = new Barang(tanggal, kelas, namabarang);
                barangAdapter.add(barang);
                count++;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    }
