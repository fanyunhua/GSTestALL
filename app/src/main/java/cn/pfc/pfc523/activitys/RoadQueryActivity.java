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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.pfc.pfc523.R;

public class RoadQueryActivity extends AppCompatActivity {

    TextView road_dateTV, road_weekTV, road_tempTV, road_humTV, road_pmTV, road_back;
    ImageView road_refreshIV;
    TextView road_huanCheng1, road_huanCheng2, road_huanCheng3, road_highSpeed, road_xueYuan, road_xingFu, road_lianXiang, road_yiYuan, road_park;
    RequestQueue queue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_query2);
        initViews();
        bindListeners();
        initData();
//        getRoadState();
    }

    private void initViews() {
        queue = Volley.newRequestQueue(this);
        road_dateTV = findViewById(R.id.road_dateTV);
        road_weekTV = findViewById(R.id.road_weekTV);
        road_tempTV = findViewById(R.id.road_tempTV);
        road_humTV = findViewById(R.id.road_humTV);
        road_pmTV = findViewById(R.id.road_pmTV);
        road_back = findViewById(R.id.road_back);
        road_refreshIV = findViewById(R.id.road_refreshIV);

        road_xueYuan = findViewById(R.id.road_xueYuan);
        road_lianXiang = findViewById(R.id.road_lianXiang);
        road_yiYuan = findViewById(R.id.road_yiYuan);
        road_xingFu = findViewById(R.id.road_xingFu);
        road_huanCheng1 = findViewById(R.id.road_huanCheng1);
        road_huanCheng2 = findViewById(R.id.road_huanCheng2);
        road_huanCheng3 = findViewById(R.id.road_huanCheng3);
        road_highSpeed = findViewById(R.id.road_highSpeed);
        road_park = findViewById(R.id.road_park);

        final TextView[] tvs = {road_huanCheng1, road_huanCheng2, road_huanCheng3, road_highSpeed, road_xueYuan, road_xingFu, road_lianXiang, road_yiYuan, road_park};

        @SuppressLint("HandlerLeak") final Handler handler1 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                for (int i = 0; i < tvs.length; i++) {
                    getRoadState(i + 1, tvs[i]);
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    handler1.sendEmptyMessage(1);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    road_dateTV.setText(getDate());

                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        handler.sendEmptyMessage(1);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        road_weekTV.setText(getWeek());

    }

    private void bindListeners() {
        road_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        road_refreshIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    private void initData() {
        //传感器的值：http://ip:port/transportservice/action/GetAllSense.do
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserName", "user1");
            String url = "http://192.168.90.6:8080/transportservice/action/GetAllSense.do";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if (jsonObject.optString("RESULT").equals("S")) {
                        road_tempTV.setText(String.format(getResources()
                                .getString(R.string.temp), jsonObject.optInt("temperature")));

                        road_humTV.setText(String.format(getResources()
                                .getString(R.string.hum), jsonObject.optInt("humidity")));

                        road_pmTV.setText(String.format(getResources()
                                .getString(R.string.pm), jsonObject.optInt("pm2.5")));
                    } else {
                        Toast.makeText(getApplicationContext(), "获取数据失败！", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getApplicationContext(), "网络连接失败！", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private String getDate() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分:ss秒");
        return sdf.format(new Date());
    }

    private String getWeek() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (i) {
            case 1:
                return "星期一";
            case 2:
                return "星期二";
            case 3:
                return "星期三";
            case 4:
                return "星期四";
            case 5:
                return "星期五";
            case 6:
                return "星期六";
            case 7:
                return "星期天";
            default:
                return "";
        }
    }

    private void getRoadState(int roadId, final TextView tv) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("RoadId", roadId).put("UserName", "user1");
            String url = "http://192.168.90.6:8080/transportservice/action/GetRoadStatus.do";
            final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if (jsonObject.optString("RESULT").equals("S")) {
                        int state = jsonObject.optInt("Status");
                        switch (state) {
                            case 1:
                                tv.setBackgroundColor(Color.parseColor("#6ab82e"));
                                break;
                            case 2:
                                tv.setBackgroundColor(Color.parseColor("#ece93a"));
                                break;
                            case 3:
                                tv.setBackgroundColor(Color.parseColor("#f49b25"));
                                break;
                            case 4:
                                tv.setBackgroundColor(Color.parseColor("#e33532"));
                                break;
                            case 5:
                                tv.setBackgroundColor(Color.parseColor("#b01e23"));
                                break;
                            default:
                                break;
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "数据获取失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getApplicationContext(), "网络请求失败", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
