package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.adapters.T60Adapter1;
import cn.pfc.pfc523.adapters.T60Adapter2;
import cn.pfc.pfc523.beans.T60bean1;
import cn.pfc.pfc523.beans.T60bean2;

public class T60Activity extends Activity {
    GridView gv1,gv2;
    List<T60bean1>list1;
    List<T60bean2>list2;
    T60Adapter1 adapter1;
    T60Adapter2 adapter2;
    ImageView img;
    String news[]={"推荐","安全驾驶","交通分类","科技类","路况类","汽车类","二手车类","改装车","违章"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_60);

        img=findViewById(R.id.img_back_60);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list1=new ArrayList<>();
        list2=new ArrayList<>();
        gv1=findViewById(R.id.gv60_1);
        gv2=findViewById(R.id.gv60_2);

        for (int i = 0; i < news.length; i++) {
            list2.add(new T60bean2(news[i]));
        }
        adapter1=new T60Adapter1(list1,T60Activity.this);
        gv1.setAdapter(adapter1);

        adapter2=new T60Adapter2(list2,T60Activity.this);
        gv2.setAdapter(adapter2);

        gv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                list1.add(new T60bean1(list2.get(position).getNews()));
                list2.remove(position);

                adapter1=new T60Adapter1(list1,T60Activity.this);
                gv1.setAdapter(adapter1);

                adapter2=new T60Adapter2(list2,T60Activity.this);
                gv2.setAdapter(adapter2);
            }
        });
        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                list2.add(new T60bean2(list1.get(position).getNews()));
                list1.remove(position);

                adapter1=new T60Adapter1(list1,T60Activity.this);
                gv1.setAdapter(adapter1);

                adapter2=new T60Adapter2(list2,T60Activity.this);
                gv2.setAdapter(adapter2);
            }
        });
    }
}
