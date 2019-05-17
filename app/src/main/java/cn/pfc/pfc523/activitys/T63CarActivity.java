package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.util.Date;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.adapters.T63Adapter;
import cn.pfc.pfc523.beans.T63Bean;

public class T63CarActivity extends Activity {
    ImageView img,img_pic;
    TextView tv_gl,tv_date,tv_place,tv_infor,tv_num,tv_money,tv_sum;
    Button but_jia,but_jian,but_del,but_pay,but_qk;
    LinearLayout lin;
    int sum=0,sort=1;
    T63Bean bean;
    TimePickerView pickerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_63buycar);
        
        img=findViewById(R.id.img_back_63buycar);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initview();
        Intent intent = getIntent();
        bean = T63Adapter.list.get(intent.getIntExtra("position",0));
        click();
    }

    private void click() {
        tv_money.setText(bean.getMoney()+"元");
        img_pic.setImageBitmap(bean.getBitmap());
        tv_place.setText(bean.getName());
        tv_infor.setText(bean.getInfor());
        but_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lin.setVisibility(View.INVISIBLE);
            }
        });
        but_qk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lin.setVisibility(View.INVISIBLE);
            }
        });
        tv_gl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                but_del.setVisibility(View.VISIBLE);
            }
        });
        tv_num.setText(sort+"");
        sum=sort*Integer.parseInt(bean.getMoney());
        tv_sum.setText("总金额："+sum+"元");
        but_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                but_jian.setEnabled(true);
                sort++;
                tv_num.setText(sort+"");
                sum=sort*Integer.parseInt(bean.getMoney());
                tv_sum.setText("总金额："+sum+"元");
            }
        });
        but_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort--;
                if(sort==0){
                    but_jian.setEnabled(false);
                }
                tv_num.setText(sort+"");
                sum=sort*Integer.parseInt(bean.getMoney());
                tv_sum.setText("总金额："+sum+"元");
            }
        });
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerView.show();
            }
        });
        but_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sum==0) {
                    Toast.makeText(T63CarActivity.this,
                            "无付款项目",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(T63CarActivity.this, T63PayActivity.class);
                    intent.putExtra("place", bean.getName());
                    intent.putExtra("sum", sum+"");
                    startActivity(intent);
                }
            }
        });
    }

    private void initview() {
        lin=findViewById(R.id.lin_63car);
        img_pic=findViewById(R.id.img_63car);
        but_del=findViewById(R.id.but_del_63car);
        but_del.setVisibility(View.INVISIBLE);
        but_jia=findViewById(R.id.but2_63car);
        but_jian=findViewById(R.id.but1_63car);
        but_pay=findViewById(R.id.but_pay_63car);
        but_qk=findViewById(R.id.but_qk_63car);
        tv_date=findViewById(R.id.tv_date63car);
        tv_gl=findViewById(R.id.tv_gl63car);
        tv_infor=findViewById(R.id.tv_infor_63car);
        tv_money=findViewById(R.id.tv_money_63car);
        tv_num=findViewById(R.id.tv_num_63car);
        tv_place=findViewById(R.id.tv_name_63car);
        tv_sum=findViewById(R.id.tv_sum_63car);

        pickerView=new TimePickerBuilder(T63CarActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

            }
        }).build();
    }
}
