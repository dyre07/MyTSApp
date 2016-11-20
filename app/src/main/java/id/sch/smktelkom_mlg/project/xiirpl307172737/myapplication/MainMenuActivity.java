package id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication.R.id.textView;

public class MainMenuActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        findViewById(R.id.imageViewJadwal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, ScheduleActivity.class));
            }
        });

        findViewById(R.id.imageViewBarang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, barangtertinggal.class));
            }
        });

        textView = (TextView) findViewById(R.id.textViewUsername);

        Intent intent = getIntent();

        textView.setText("Welcome User " + intent.getStringExtra(LoginActivity.KEY_USERNAME));
    }
}
