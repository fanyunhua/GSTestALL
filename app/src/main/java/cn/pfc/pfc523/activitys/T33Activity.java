package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.pfc.pfc523.R;

public class T33Activity extends Activity {
    ImageView img;
    TextView tv;
    DatePickerDialog pickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_33);

        img=findViewById(R.id.img_back_33);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv=findViewById(R.id.tv_33);

        pickerDialog=new DatePickerDialog(T33Activity.this,DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String s="";
                if(Math.IEEEremainder(dayOfMonth,2)==0){
                    s="双号";
                }else {
                    s="单号";
                }
                tv.setText(year+"年"+(month+1)+"月"+dayOfMonth+"日："+s);
            }
        },2019,4,14);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerDialog.show();
            }
        });
    }
}
