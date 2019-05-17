package cn.pfc.pfc523;

import android.content.Intent;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import cn.pfc.pfc523.activitys.CarWzActivity;
import cn.pfc.pfc523.activitys.CustomShuttleActivity;
import cn.pfc.pfc523.activitys.FeedBackActivity;
import cn.pfc.pfc523.activitys.Light2Activity;
import cn.pfc.pfc523.activitys.T27Activity;
import cn.pfc.pfc523.activitys.T33Activity;
import cn.pfc.pfc523.activitys.T35Activity;
import cn.pfc.pfc523.activitys.T60Activity;
import cn.pfc.pfc523.activitys.T63Activity;
import cn.pfc.pfc523.activitys.ZDGLActivity;
//import cn.pfc.pfc523.activitys.fyh.BusQueryActivity;
//import cn.pfc.pfc523.activitys.fyh.BusQueryActivity;
import cn.pfc.pfc523.activitys.fyh.BusQueryActivity;
import cn.pfc.pfc523.activitys.fyh.DataAnalysisActivity;
import cn.pfc.pfc523.activitys.fyh.EnvironmentIndex.EnvironmentIndexActivity;
import cn.pfc.pfc523.activitys.MyAccountActivity;
import cn.pfc.pfc523.activitys.RoadQueryActivity;
import cn.pfc.pfc523.activitys.fyh.TravelManageActivity;

public class MainActivity extends AppCompatActivity {
    SlidingPaneLayout sli;
    ImageView img;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sli=findViewById(R.id.sli);
        img=findViewById(R.id.img_zjm);
        lv=findViewById(R.id.lv_menu);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sli.isOpen()){
                    sli.closePane();
                }else {
                    sli.openPane();
                }
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(MainActivity.this, MyAccountActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, EnvironmentIndexActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, RoadQueryActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, TravelManageActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, ZDGLActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, BusQueryActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, RoadQuery2Activity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, FeedBackActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, Light2Activity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, CarWzActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, CustomShuttleActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, T27Activity.class));
                        break;
                    case 12:
                        startActivity(new Intent(MainActivity.this, T33Activity.class));
                        break;
                    case 13:
                        startActivity(new Intent(MainActivity.this, T35Activity.class));
                        break;
                    case 14:
                        startActivity(new Intent(MainActivity.this, T60Activity.class));
                        break;
                    case 15:
                        startActivity(new Intent(MainActivity.this, T63Activity.class));
                        break;
                    case 16:
                        startActivity(new Intent(MainActivity.this, DataAnalysisActivity.class));
                        break;
                }
            }
        });
    }
}
