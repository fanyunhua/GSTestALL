package cn.pfc.pfc523.activitys.fyh.EnvironmentIndex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import cn.pfc.pfc523.R;
import cn.pfc.pfc523.adapters.MViewPagerAdapter;
import cn.pfc.pfc523.fragment.fyh.ENVPFragment1;
import cn.pfc.pfc523.fragment.fyh.ENVPFragment2;
import cn.pfc.pfc523.fragment.fyh.ENVPFragment3;
import cn.pfc.pfc523.fragment.fyh.ENVPFragment4;
import cn.pfc.pfc523.fragment.fyh.ENVPFragment5;
import cn.pfc.pfc523.fragment.fyh.ENVPFragment6;
/**
 * @author fyh 2019.5.7 10:27
 * 滑动切换
 * */
public class ENDrawingVieaPagerActivity extends AppCompatActivity {
    private ViewPager mGSENVP1;
    TextView textViews[];
    int tvId[] = new int[]{
            R.id.mGSENTV1,
            R.id.mGSENTV2,
            R.id.mGSENTV3,
            R.id.mGSENTV4,
            R.id.mGSENTV5,
            R.id.mGSENTV6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endrawing_view_pager);
        initView();
    }

    private void initView() {
        ImageView img_back_ENVP = findViewById(R.id.img_back_ENVP);
        img_back_ENVP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mGSENVP1 = findViewById(R.id.mGSENVP1);
        textViews = new TextView[tvId.length];
        for (int i = 0; i <tvId.length ; i++) {
            textViews[i] = findViewById(tvId[i]);
        }
        List<Fragment> list = new ArrayList<>();
        list.add(new ENVPFragment1());
        list.add(new ENVPFragment2());
        list.add(new ENVPFragment3());
        list.add(new ENVPFragment4());
        list.add(new ENVPFragment5());
        list.add(new ENVPFragment6());
        FragmentManager fm = getSupportFragmentManager();
        MViewPagerAdapter adapter = new MViewPagerAdapter(fm,list);
        mGSENVP1.setAdapter(adapter);
        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        Log.d("ssssssssssssss",index+"sssssssssssss");

        mGSENVP1.setCurrentItem(index);
        for (int j = 0; j <tvId.length ; j++) {
            if(index==j)
            {
                textViews[j].setBackgroundResource(R.drawable.point_black);
            }
            else
            {
                textViews[j].setBackgroundResource(R.drawable.point_white);
            }
        }

        mGSENVP1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                for (int j = 0; j <tvId.length ; j++) {
                    if(i==j)
                    {
                        textViews[j].setBackgroundResource(R.drawable.point_black);
                    }
                    else
                    {
                        textViews[j].setBackgroundResource(R.drawable.point_white);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

}
