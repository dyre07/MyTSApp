package id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        findViewById(R.id.imageViewJadwal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, ScheduleActivity.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        findViewById(R.id.imageViewPengumuman).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, AnnounceActivity.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        findViewById(R.id.imageViewBarang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, barangtertinggal.class));
            }
        });


        findViewById(R.id.imageViewGuru).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, GuruActivity.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }
}
