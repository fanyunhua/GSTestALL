package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.adapters.CarWzAdapter;
import cn.pfc.pfc523.beans.CarWzBean;

public class CarWzActivity extends Activity {
    ImageView img;
    TabHost tabHost;
    Button but_tab1,but_tab2;
    GridView gv;
    List<CarWzBean>list;
    CarWzAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_carwz);

        list=new ArrayList<>();
        img=findViewById(R.id.img_back_4);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tabHost=findViewById(R.id.tab_4);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("t1").setContent(R.id.tab4_1));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("t2").setContent(R.id.tab4_2));
        gv=findViewById(R.id.gv_2);
        gv.setVerticalSpacing(50);
        gv.setHorizontalSpacing(50);
        but_tab1=findViewById(R.id.but2_tab1);
        but_tab2=findViewById(R.id.but2_tab2);
        
        but_tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabHost.setCurrentTab(0);
                but_tab1.setBackgroundColor(Color.argb(255,255,255,255));
                but_tab2.setBackgroundColor(Color.argb(255,128,128,128));
            }
        });
        but_tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabHost.setCurrentTab(1);
                but_tab2.setBackgroundColor(Color.argb(255,255,255,255));
                but_tab1.setBackgroundColor(Color.argb(255,128,128,128));
            }
        });
        
        wz();
    }

    private void wz() {
        int url[]={R.mipmap.pm,R.mipmap.car3,R.mipmap.english,R.mipmap.road,R.mipmap.bus};
        String name[]={"bus.mp4","car3.3gp","english.mp4","road.mp4","pm.mp4"};

        for (int i = 0; i < name.length; i++) {
            list.add(new CarWzBean(url[i],name[i]));
        }
        adapter=new CarWzAdapter(list,CarWzActivity.this);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(CarWzActivity.this,Dialog4Activity.class);
                intent.putExtra("play",position);
                startActivity(intent);
            }
        });
    }
}
