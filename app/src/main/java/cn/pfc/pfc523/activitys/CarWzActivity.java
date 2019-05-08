package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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
import cn.pfc.pfc523.adapters.CarWzAdapter2;
import cn.pfc.pfc523.beans.CarWzBean;
import cn.pfc.pfc523.beans.CarWzBean2;

public class CarWzActivity extends Activity {
    ImageView img;
    TabHost tabHost;
    Button but_tab1,but_tab2;
    GridView gv,gv2;
    List<CarWzBean>list;
    CarWzAdapter adapter;
    List<CarWzBean2>list2;
    CarWzAdapter2 adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_carwz);

        list=new ArrayList<>();
        list2=new ArrayList<>();
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
        gv2=findViewById(R.id.gv_4);
        gv2.setVerticalSpacing(50);
        gv2.setHorizontalSpacing(50);
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
        wz2();
    }

    private void wz2() {
        int url[]={R.mipmap.car1,R.mipmap.car2,R.mipmap.car3,R.mipmap.car4};

        for (int i = 0; i < url.length; i++) {
            list2.add(new CarWzBean2(url[i]));
        }
        adapter2=new CarWzAdapter2(list2,CarWzActivity.this);
        gv2.setAdapter(adapter2);
        gv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(CarWzActivity.this,Dialog42Activity.class);
                intent.putExtra("img",position);
                startActivity(intent);
            }
        });
    }

    private void wz() {
        int url[]={R.mipmap.pm,R.mipmap.car_play,R.mipmap.english,R.mipmap.road,R.mipmap.bus};
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
