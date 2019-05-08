package cn.pfc.pfc523.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import cn.pfc.pfc523.R;

public class SelectDateActivity extends AppCompatActivity {
    CalendarView sd_cv;
    TextView sd_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);
        sd_cv = findViewById(R.id.sd_cv);
        sd_tv = findViewById(R.id.sd_tv);

        sd_cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String date = sd_tv.getText().toString() + "," + year + "-" + (month + 1) + "-" + dayOfMonth;
                if(date.charAt(0) == ','){
                    date = date.substring(1);
                }
                sd_tv.setText(date);
            }
        });
        findViewById(R.id.sd_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.sd_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
            }
        });

    }
}
