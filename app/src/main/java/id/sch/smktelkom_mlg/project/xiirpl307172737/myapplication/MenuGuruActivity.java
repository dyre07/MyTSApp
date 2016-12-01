package id.sch.smktelkom_mlg.project.xiirpl307172737.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MenuGuruActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_guru);

        textView = (TextView) findViewById(R.id.textViewUsername);
        Intent intent = getIntent();
        textView.setText("Welcome User " + intent.getStringExtra(LoginActivity.KEY_USERNAME));

        findViewById(R.id.imageViewPengumuman).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuGuruActivity.this, AnnouncementGuruActivity.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        findViewById(R.id.imageViewGuru).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuGuruActivity.this, InputTugasActivity.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }
}
