package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.config.AppConfig;

public class T27Activity extends Activity {
    ImageView img;
    TextView t1,t2,t3,tv1,tv2,tv3,tv4;
    RequestQueue queue;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_27);

        queue= Volley.newRequestQueue(this);
        img=findViewById(R.id.img_back_27);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        t1=findViewById(R.id.tv_27_pm);
        t2=findViewById(R.id.tv_27_tem);
        t3=findViewById(R.id.tv_27_hem);
        tv1=findViewById(R.id.tv_27_1);
        tv2=findViewById(R.id.tv_27_2);
        tv3=findViewById(R.id.tv_27_3);
        tv4=findViewById(R.id.tv_27_4);

        initdata();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                initdata();
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(3000);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void initdata() {
        String url= AppConfig.BASE_URL+ "GetAllSense.do";
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("UserName","user1");
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {
                        int pm=jsonObject.getInt("pm2.5");
                        int tem=jsonObject.getInt("temperature");
                        int hem=jsonObject.getInt("humidity");
                        t1.setText("PM2.5:"+pm);
                        t2.setText("温   度:"+tem);
                        t3.setText("湿   度:"+hem);

                        if(pm<100){
                            tv3.setText("良好");
                            tv4.setText("气象条件会对晨练影响不大");
                            tv3.setTextColor(Color.argb(255,0,0,0));
                        }else if(pm>=100&&pm<200){
                            tv3.setText("轻度");
                            tv4.setText("受天气影响，较不宜晨练");
                            tv3.setTextColor(Color.argb(255,0,0,0));
                        }else if(pm>=200&&pm<300){
                            tv3.setText("重度");
                            tv4.setText("减少外出，出行注意戴口罩");
                            tv3.setTextColor(Color.argb(255,255,0,0));
                        }else{
                            tv3.setText("爆表");
                            tv4.setText("停止一切外出活动");
                            tv3.setTextColor(Color.argb(255,255,0,0));
                        }

                        int light=jsonObject.getInt("LightIntensity");
                        if(light<1000){
                            tv1.setText("非常弱");
                            tv2.setText("您无须担心紫外线");
                            tv1.setTextColor(Color.argb(255,0,0,0));
                        }else if(light>=1000&&light<1600){
                            tv1.setText("弱");
                            tv2.setText("外出适当涂抹低倍数防晒霜");
                            tv1.setTextColor(Color.argb(255,0,0,0));
                        }else {
                            tv1.setText("强");
                            tv2.setText("外出需要涂抹中倍数防晒霜");
                            tv1.setTextColor(Color.argb(255,255,0,0));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
