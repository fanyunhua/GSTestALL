package cn.pfc.pfc523.activitys;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import cn.pfc.pfc523.R;

public class RoadQuery2Activity extends AppCompatActivity {

    TextView road2_pmTV,road2_tempTV,road2_humTV,
            road2_r1TV,road2_r2TV,road2_r3TV,
            road2_r1Color,road2_r2Color,road2_r3Color;
    ImageView road2_back;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_query3);
        initViews();
        bindListeners();

    }

    private void initViews() {
        queue = Volley.newRequestQueue(this);
        road2_back = findViewById(R.id.road2_back);
        road2_pmTV = findViewById(R.id.road2_pmTV);
        road2_tempTV = findViewById(R.id.road2_tempTV) ;
        road2_humTV = findViewById(R.id.road2_humTV);
        road2_r1TV = findViewById(R.id.road2_r1TV);
        road2_r2TV = findViewById(R.id.road2_r2TV);
        road2_r3TV = findViewById(R.id.road2_r3TV);
        road2_r1Color = findViewById(R.id.road2_r1Color);
        road2_r2Color = findViewById(R.id.road2_r2Color);
        road2_r3Color = findViewById(R.id.road2_r3Color);

        final TextView[] colors = {road2_r1Color,road2_r2Color,road2_r3Color};
        final TextView[] texts = {road2_r1TV,road2_r2TV,road2_r3TV};
        final int[] ress = {R.string.road1,R.string.road2,R.string.road3};

        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 1){
                    getData();
                    for (int i = 0; i < colors.length; i++) {
                        getRoadState(i + 1,colors[i],texts[i],ress[i]);
                    }
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    handler.sendEmptyMessage(1);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

    private void bindListeners() {
        road2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getData(){
        //http://ip:port/transportservice/action/GetAllSense.do
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserName","user1");
            String url = "http://192.168.90.6:8080/transportservice/action/GetAllSense.do";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if(jsonObject.optString("RESULT").equals("S")){
                        road2_pmTV.setText(String.format(getResources().getString(R.string.pm),jsonObject.optInt("pm2.5")));

                        road2_tempTV.setText(String.format(getResources().getString(R.string.temp),jsonObject.optInt("temperature")));

                        road2_humTV.setText(String.format(getResources().getString(R.string.hum),jsonObject.optInt("humidity")));
                    }else{
                        Toast.makeText(getApplicationContext(), "传感器：数据获取失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getApplicationContext(), "传感器：网络请求失败", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private void getRoadState(int roadId, final TextView color, final TextView text, final int resId){
        //http://ip:port/transportservice/action/GetRoadStatus.do
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("RoadId",roadId).put("UserName","user1");
            String url = "http://192.168.90.6:8080/transportservice/action/GetRoadStatus.do";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if(jsonObject.optString("RESULT").equals("S")){
                        int state = jsonObject.optInt("Status");
                        switch (state){
                            case 1:
                                text.setText(String.format(getResources().getString(resId),"通畅"));
                                color.setBackgroundColor(Color.parseColor("#0ebd12"));
                                break;
                            case 2:
                                text.setText(String.format(getResources().getString(resId),"较通畅"));
                                color.setBackgroundColor(Color.parseColor("#98ed1f"));
                                break;
                            case 3:
                                text.setText(String.format(getResources().getString(resId),"拥挤"));
                                color.setBackgroundColor(Color.parseColor("#ffff01"));
                                break;
                            case 4:
                                text.setText(String.format(getResources().getString(resId),"堵塞"));
                                color.setBackgroundColor(Color.parseColor("#ff0103"));
                                break;
                            case 5:
                                text.setText(String.format(getResources().getString(resId),"爆表"));
                                color.setBackgroundColor(Color.parseColor("#4c060e"));
                                break;
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "道路状态：数据获取失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getApplicationContext(), "道路状态：网络请求失败", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(request);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
