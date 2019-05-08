package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.adapters.Light2Adapter;
import cn.pfc.pfc523.beans.Light2Bean;
import cn.pfc.pfc523.config.AppConfig;

public class Light2Activity extends Activity {
    ImageView img;
    Spinner spinner;
    Button but;
    ListView lv;
    RequestQueue queue;
    List<Light2Bean>list;
    Light2Adapter adapter;
    String s[]={"路口升序","路口降序","红灯升序","红灯降序","绿灯升序","绿灯降序","黄灯升序","黄灯降序"};
    ArrayAdapter arrayAdapter;
    int sort=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_light2);

        img=findViewById(R.id.img_back_2);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        but=findViewById(R.id.but_2);
        spinner=findViewById(R.id.spinner_2);
        lv=findViewById(R.id.lv_2);
        queue= Volley.newRequestQueue(this);
        list=new ArrayList<>();
        arrayAdapter=new ArrayAdapter(Light2Activity.this,
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

        for (int i = 0; i < 3; i++) {
            query(i);
        }
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                for (int i = 0; i < 3; i++) {
                    query(i);
                }
            }
        });
    }

    private void query(final int i) {
        String url= AppConfig.BASE_URL+ "GetTrafficLightConfigAction.do";
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("UserName","user1");
            jsonObject.put("TrafficLightId",i+1);
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {
                        String suc=jsonObject.getString("RESULT");
                        if(suc.equals("S")){
                            String red=jsonObject.getString("RedTime");
                            String yellow=jsonObject.getString("YellowTime");
                            String green=jsonObject.getString("GreenTime");
                            Light2Bean bean=new Light2Bean(i+1,red,yellow,green);
                            list.add(bean);

                            switch (sort){
                                case 0:
                                    Collections.sort(list, new Comparator<Light2Bean>() {
                                        @Override
                                        public int compare(Light2Bean o1, Light2Bean o2) {
                                            return o1.getRoad()-o2.getRoad();
                                        }
                                    });
                                    break;
                                case 1:
                                    Collections.sort(list, new Comparator<Light2Bean>() {
                                        @Override
                                        public int compare(Light2Bean o1, Light2Bean o2) {
                                            return o2.getRoad()-o1.getRoad();
                                        }
                                    });
                                    break;
                                case 2:
                                    Collections.sort(list, new Comparator<Light2Bean>() {
                                        @Override
                                        public int compare(Light2Bean o1, Light2Bean o2) {
                                            return Integer.parseInt(o1.getRed())-Integer.parseInt(o2.getRed());
                                        }
                                    });
                                    break;
                                case 3:
                                    Collections.sort(list, new Comparator<Light2Bean>() {
                                        @Override
                                        public int compare(Light2Bean o1, Light2Bean o2) {
                                            return Integer.parseInt(o2.getRed())-Integer.parseInt(o1.getRed());
                                        }
                                    });
                                    break;
                                case 4:
                                    Collections.sort(list, new Comparator<Light2Bean>() {
                                        @Override
                                        public int compare(Light2Bean o1, Light2Bean o2) {
                                            return Integer.parseInt(o1.getGreen())-Integer.parseInt(o2.getGreen());
                                        }
                                    });
                                    break;
                                case 5:
                                    Collections.sort(list, new Comparator<Light2Bean>() {
                                        @Override
                                        public int compare(Light2Bean o1, Light2Bean o2) {
                                            return Integer.parseInt(o2.getGreen())-Integer.parseInt(o1.getGreen());
                                        }
                                    });
                                    break;
                                case 6:
                                    Collections.sort(list, new Comparator<Light2Bean>() {
                                        @Override
                                        public int compare(Light2Bean o1, Light2Bean o2) {
                                            return Integer.parseInt(o1.getYellow())-Integer.parseInt(o2.getYellow());
                                        }
                                    });
                                    break;
                                case 7:
                                    Collections.sort(list, new Comparator<Light2Bean>() {
                                        @Override
                                        public int compare(Light2Bean o1, Light2Bean o2) {
                                            return Integer.parseInt(o2.getYellow())-Integer.parseInt(o1.getYellow());
                                        }
                                    });
                                    break;
                            }

                            adapter=new Light2Adapter(list,Light2Activity.this);
                            lv.setAdapter(adapter);
                        }else {
                            if(i==0) {
                                Toast.makeText(Light2Activity.this,
                                        "查询信息失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    if(i==0) {
                        Toast.makeText(Light2Activity.this,
                                "网络连接失败，请检查网络设置", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
