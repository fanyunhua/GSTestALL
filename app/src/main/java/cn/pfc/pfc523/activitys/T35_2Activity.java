package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.adapters.T35Adapter;
import cn.pfc.pfc523.beans.T35Bean;
import cn.pfc.pfc523.config.AppConfig;

public class T35_2Activity extends Activity {
    ImageView img;
    TextView tv,tv_dp,tv_dh;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_35_2);

        queue=Volley.newRequestQueue(this);
        img=findViewById(R.id.img_back_35_2);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv=findViewById(R.id.tv_35_2);
        tv_dh=findViewById(R.id.tv_35_2dh);
        tv_dp=findViewById(R.id.tv_35_2dp);

        query();
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
                        Intent intent=getIntent();

                        JSONObject jsonObject1=jsonArray.getJSONObject(intent.getIntExtra("lx",0));
                        String infor=jsonObject1.getString("infor");
                        int stars=jsonObject1.getInt("stars");
                        String tel=jsonObject1.getString("tel");

                        tv.setText(infor);
                        tv_dp.setText("游友点评："+stars);
                        tv_dh.setText("联系电话："+tel);

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
