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

public class InputTugasActivity extends AppCompatActivity {
    public static final String KEY_TANGGAL = "tanggal";
    public static final String KEY_GURU = "guru";
    public static final String KEY_PELAJARAN = "pelajaran";
    public static final String KEY_KELAS = "kelas";
    public static final String KEY_TUGAS = "tugas";
    private static final String Input_URL = "http://mytsapp.16mb.com/android/assignment.php";
    @Bind(R.id.et_tanggal)
    EditText etTanggal;
    @Bind(R.id.et_guru)
    EditText etGuru;
    @Bind(R.id.et_pelajaran)
    EditText etPelajaran;
    @Bind(R.id.et_kelas)
    EditText etKelas;
    @Bind(R.id.et_tugas)
    EditText etTugas;
    @Bind(R.id.buttonOk)
    Button bOk;
    @Bind(R.id.buttonCancel)
    Button bCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_tugas);

        etTanggal = (EditText) findViewById(R.id.et_tanggal);
        etGuru = (EditText) findViewById(R.id.et_guru);
        etPelajaran = (EditText) findViewById(R.id.et_pelajaran);
        etKelas = (EditText) findViewById(R.id.et_kelas);
        etTugas = (EditText) findViewById(R.id.et_tugas);
        bOk = (Button) findViewById(R.id.buttonOk);
        bCancel = (Button) findViewById(R.id.buttonCancel);

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputTugasActivity.this.finish();
            }
        });

        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputTugas();
            }
        });
    }

    private void InputTugas() {
        final ProgressDialog progressDialog = new ProgressDialog(InputTugasActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please Wait...");

        final String tanggal = etTanggal.getText().toString().trim();
        final String guru = etGuru.getText().toString().trim();
        final String pelajaran = etPelajaran.getText().toString().trim();
        final String kelas = etKelas.getText().toString().trim();
        final String tugas = etTugas.getText().toString().trim();

        class InputTugas extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                Toast.makeText(InputTugasActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(KEY_TANGGAL, tanggal);
                params.put(KEY_GURU, guru);
                params.put(KEY_PELAJARAN, pelajaran);
                params.put(KEY_KELAS, kelas);
                params.put(KEY_TUGAS, tugas);

                RequestHandler requestHandler = new RequestHandler();
                String res = requestHandler.sendPostRequest(Input_URL, params);

                return res;
            }
        }
        InputTugas inputTugas = new InputTugas();
        inputTugas.execute();
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

