package id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

import id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication.adapter.ContactAdapter;
import id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication.model.Contacts;

public class AnnouncementGuruActivity extends AppCompatActivity {
    private static final String JSON_URL = "http://mytsapp.16mb.com/android/announcement.php";
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ContactAdapter contactAdapter;
    ListView listView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_guru);
        progressDialog = new ProgressDialog(AnnouncementGuruActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();
        getJSON();

        findViewById(R.id.iv_input).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AnnouncementGuruActivity.this, InputAnnouncementActivity.class));
            }
        });
    }

    public void getJSON() {
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
                        Toast.makeText(AnnouncementGuruActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
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
        contactAdapter = new ContactAdapter(this, R.layout.row_layout);
        listView.setAdapter(contactAdapter);

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("result");
            int count = 0;
            String jam, untuk, pengumuman;
            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                jam = JO.getString("jam");
                untuk = JO.getString("untuk");
                pengumuman = JO.getString("pengumuman");
                Contacts contacts = new Contacts(jam, untuk, pengumuman);
                contactAdapter.add(contacts);
                count++;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
