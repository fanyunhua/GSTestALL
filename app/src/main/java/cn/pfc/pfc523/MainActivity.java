package cn.pfc.pfc523;

import android.content.Intent;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import cn.pfc.pfc523.activitys.EnvironmentIndex.EnvironmentIndexActivity;
import cn.pfc.pfc523.activitys.MyAccountActivity;

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
                }
            }
        });
    }
}
