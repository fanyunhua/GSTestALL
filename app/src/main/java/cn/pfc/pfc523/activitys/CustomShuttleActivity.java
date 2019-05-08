package cn.pfc.pfc523.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.adapters.CSAdapter;
import cn.pfc.pfc523.beans.CSOne;
import cn.pfc.pfc523.beans.CSTwo;

public class CustomShuttleActivity extends AppCompatActivity {
    List<CSOne> oneData;
    List<List<CSTwo>> twoidata;
    List<CSTwo> twoData;
    ExpandableListView elv;
    CSAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_shuttle);
        elv = findViewById(R.id.cs_elv);
        oneData = new ArrayList<>();
        twoData = new ArrayList<>();
        twoidata = new ArrayList<>();

        oneData.add(new CSOne("1-2","19:00-20:00","18:50-19:50",2,20));
        oneData.add(new CSOne("1-2","19:00-20:00","18:50-19:50",2,20));

        twoData = new ArrayList<>();
        twoData.add(new CSTwo("始发","1"));
        twoData.add(new CSTwo("","2"));
        twoData.add(new CSTwo("","3"));
        twoData.add(new CSTwo("","4"));
        twoData.add(new CSTwo("终点","5"));
        twoidata.add(twoData);

        twoData = new ArrayList<>();
        twoData.add(new CSTwo("始发","6"));
        twoData.add(new CSTwo("","7"));
        twoData.add(new CSTwo("","8"));
        twoData.add(new CSTwo("","9"));
        twoData.add(new CSTwo("终点","10"));
        twoidata.add(twoData);

        adapter = new CSAdapter(oneData,twoidata,this);
        elv.setAdapter(adapter);

        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                startActivity(new Intent(CustomShuttleActivity.this,ShuttleInfoActivity.class));
                return false;
            }
        });
        findViewById(R.id.cs_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
