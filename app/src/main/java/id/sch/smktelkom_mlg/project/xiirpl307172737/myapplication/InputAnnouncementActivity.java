package id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InputAnnouncementActivity extends AppCompatActivity {
    public static final String KEY_TANGGAL = "tanggal";
    public static final String KEY_JAM = "jam";
    public static final String KEY_UNTUK = "untuk";
    public static final String KEY_PENGUMUMAN = "pengumuman";
    private static final String Input_URL = "http://mytsapp.16mb.com/android/insert_announcement.php";
    @Bind(R.id.et_tanggal)
    EditText etTanggal;
    @Bind(R.id.et_jam)
    EditText etJam;
    @Bind(R.id.et_untuk)
    EditText etUntuk;
    @Bind(R.id.et_pengumuman)
    EditText etPengumuman;
    @Bind(R.id.buttonOk)
    Button bOk;
    @Bind(R.id.buttonCancel)
    Button bCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_announcement);
        ButterKnife.bind(this);

        etTanggal = (EditText) findViewById(R.id.et_tanggal);
        etJam = (EditText) findViewById(R.id.et_jam);
        etUntuk = (EditText) findViewById(R.id.et_untuk);
        etPengumuman = (EditText) findViewById(R.id.et_pengumuman);
        bOk = (Button) findViewById(R.id.buttonOk);
        bCancel = (Button) findViewById(R.id.buttonCancel);

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputAnnouncementActivity.this.finish();
            }
        });

        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputAnnounce();
            }
        });
    }

    private void inputAnnounce() {
        final ProgressDialog progressDialog = new ProgressDialog(InputAnnouncementActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");

        final String tanggal = etTanggal.getText().toString().trim();
        final String jam = etJam.getText().toString().trim();
        final String untuk = etUntuk.getText().toString().trim();
        final String pengumuman = etPengumuman.getText().toString().trim();

        class InputAnnounce extends AsyncTask<Void, Void, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                Toast.makeText(InputAnnouncementActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(KEY_TANGGAL, tanggal);
                params.put(KEY_JAM, jam);
                params.put(KEY_UNTUK, untuk);
                params.put(KEY_PENGUMUMAN, pengumuman);

                RequestHandler requestHandler = new RequestHandler();
                String res = requestHandler.sendPostRequest(Input_URL, params);

                return res;
            }
        }

        InputAnnounce inputAnnounce = new InputAnnounce();
        inputAnnounce.execute();
    }

    public class RequestHandler {
        public String sendPostRequest(String requestURL,
                                      HashMap<String, String> postDataParams) {
            //Creating a URL
            URL url;

            //StringBuilder object to store the message retrieved from the server
            StringBuilder sb = new StringBuilder();
            try {
                //Initializing Url
                url = new URL(requestURL);

                //Creating an httmlurl connection
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                //Configuring connection properties
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                //Creating an output stream
                OutputStream os = conn.getOutputStream();

                //Writing parameters to the request
                //We are using a method getPostDataString which is defined below
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    sb = new StringBuilder();
                    String response;
                    //Reading server response
                    while ((response = br.readLine()) != null) {
                        sb.append(response);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }

            return result.toString();
        }
    }
}