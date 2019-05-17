package cn.pfc.pfc523.activitys.fyh;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.adapters.MViewPagerAdapter;
import cn.pfc.pfc523.fragment.fyh.DataAnalysisFragment1;
import cn.pfc.pfc523.fragment.fyh.DataAnalysisFragment2;

public class DataAnalysisActivity extends AppCompatActivity {
    private ViewPager mDataAnalisisVP;
    TextView textViews[];
    int []tvId = new int[]{
            R.id.mDataAnalisisTV1,
            R.id.mDataAnalisisTV2
    };
    List<Fragment> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analysis);
        initView();
    }

    private void initView() {
        mDataAnalisisVP = (ViewPager) findViewById(R.id.mDataAnalisisVP);
        textViews = new TextView[tvId.length];
        for (int i = 0; i <tvId.length ; i++) {
            textViews[i] = findViewById(tvId[i]);
        }
        list = new ArrayList<>();
        list.add(new DataAnalysisFragment1());
        list.add(new DataAnalysisFragment2());
        MViewPagerAdapter adapter = new MViewPagerAdapter(getSupportFragmentManager(),list);
        mDataAnalisisVP.setAdapter(adapter);
        mDataAnalisisVP.setCurrentItem(0);
        mDataAnalisisVP.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if(i==1)
                {
                    Toast.makeText(DataAnalysisActivity.this,"如左侧坐标轴未完全展开请双击坐标轴查看",Toast.LENGTH_LONG).show();

                }
                for (int j = 0; j <tvId.length ; j++) {
                    if(i==j) textViews[j].setBackgroundResource(R.drawable.point_black);
                    else textViews[j].setBackgroundResource(R.drawable.point_white);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
