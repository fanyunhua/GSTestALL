package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.adapters.MyAccountAdapter;
import cn.pfc.pfc523.beans.MyAccountBean;
import cn.pfc.pfc523.utils.db.DBUtils;

public class ZDGLActivity extends Activity {
    ImageView img;
    TextView tv;
    ListView lv;
    Spinner spinner;
    LinearLayout lin;
    Button but;
    String s[]={"时间升序","时间降序"};
    int sort=1;
    MyAccountAdapter adapter;
    List<MyAccountBean>list;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_zhgl);

        list=new ArrayList<>();
        img=findViewById(R.id.img_back_3);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv=findViewById(R.id.tv_3);
        lv=findViewById(R.id.lv_3);
        lin=findViewById(R.id.lin_3);
        lv.setVisibility(View.INVISIBLE);
        lin.setVisibility(View.INVISIBLE);
        but=findViewById(R.id.but_3);
        spinner=findViewById(R.id.spinner_3);
        ArrayAdapter arrayAdapter=new ArrayAdapter(ZDGLActivity.this,
                R.layout.support_simple_spinner_dropdown_item,s);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sort=position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        query();
        click();
    }

    private void click() {
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=0;
                list.clear();
                query();
            }
        });
    }

    private void query() {
        String a;
        if(sort==0){
            a="select * from table3 order by atime asc";
        }else {
            a="select * from table3 order by atime desc";
        }
        Cursor cursor= DBUtils.cha(ZDGLActivity.this).rawQuery(a,null);
        if(cursor.moveToFirst()){
            tv.setVisibility(View.INVISIBLE);
            lv.setVisibility(View.VISIBLE);
            lin.setVisibility(View.VISIBLE);
            do{
                id++;
                int car_id=cursor.getInt(cursor.getColumnIndex("car_id"));
                int money=cursor.getInt(cursor.getColumnIndex("money"));
                String ren=cursor.getString(cursor.getColumnIndex("ren"));
                String time=cursor.getString(cursor.getColumnIndex("atime"));

                MyAccountBean bean=new MyAccountBean(id,car_id,money,ren,time);
                list.add(bean);
            }while (cursor.moveToNext());
            adapter=new MyAccountAdapter(list,ZDGLActivity.this);
            lv.setAdapter(adapter);
        }else {
            tv.setVisibility(View.VISIBLE);
            lv.setVisibility(View.INVISIBLE);
            lin.setVisibility(View.INVISIBLE);
        }
    }
}
