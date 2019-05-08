package cn.pfc.pfc523.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import cn.pfc.pfc523.R;

public class StationSelectActivity extends AppCompatActivity {

    EditText ss_name,ss_phone;
    Spinner ss_up,ss_down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_select);
        ss_name = findViewById(R.id.ss_name);
        ss_phone = findViewById(R.id.ss_phone);
        ss_up = findViewById(R.id.ss_up);
        ss_down = findViewById(R.id.ss_down);
        findViewById(R.id.ss_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.ss_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name",ss_name.getText().toString())
                        .putExtra("phone",ss_phone.getText().toString())
                        .putExtra("up",ss_up.getSelectedItem().toString())
                        .putExtra("down",ss_down.getSelectedItem().toString());
                startActivity(intent);
            }
        });
    }
}
