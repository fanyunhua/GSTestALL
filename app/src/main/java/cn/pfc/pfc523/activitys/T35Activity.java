package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.adapters.T35Adapter;
import cn.pfc.pfc523.beans.T35Bean;
import cn.pfc.pfc523.config.AppConfig;

public class T35Activity extends Activity {
    ImageView img;
    RequestQueue queue;
    GridView gv;
    List<T35Bean>list;
    T35Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_35);

        img=findViewById(R.id.img_back_35);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        queue= Volley.newRequestQueue(this);
        gv=findViewById(R.id.gv_35);
        gv.setVerticalSpacing(20);
        gv.setHorizontalSpacing(20);
        list=new ArrayList<>();

        query();
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(T35Activity.this,T35_2Activity.class);
                intent.putExtra("lx",position);
                startActivity(intent);
            }
        });
    }

    private void query() {
        String url= AppConfig.BASE_URL+ "GetTravelInfor.do";
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("UserName","user1");
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {
                        JSONArray jsonArray=jsonObject.getJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String tp=jsonObject1.getString("pic");
                            String place=jsonObject1.getString("place");
                            int price=jsonObject1.getInt("price");


                            T35Bean bean=new T35Bean(R.mipmap.car1,place,price);
                            list.add(bean);
                        }
                        adapter=new T35Adapter(list,T35Activity.this);
                        gv.setAdapter(adapter);
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
