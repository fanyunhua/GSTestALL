package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.adapters.T63Adapter;
import cn.pfc.pfc523.beans.T63Bean;
import cn.pfc.pfc523.config.AppConfig;

public class T63Activity extends Activity {
    List<T63Bean>list;
    T63Adapter adapter;
    GridView gv;
    ImageView img;
    RequestQueue queue;
    String place="",pic="",infor="",tel="";
    int price=0,stars=0;
    JSONArray jsonArray;
    List<String>list_pic;
    List<T63Bean> listBean;
    Handler handler;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_63);
        
        initview();
        query();
    }

    private void query() {
        final String url= AppConfig.BASE_URL+"GetTravelInfor.do";
        final JSONObject jsonObject=new JSONObject();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(list_pic.size()>0) {
                    for (String cc : list_pic) {
                        ImageRequest imageRequest = new ImageRequest(cc, new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap bitmap) {

                                T63Bean bean = new T63Bean(bitmap, listBean.get(count).getName(), listBean.get(count).getMoney()+ "");
                                bean.setInfor(listBean.get(count).getInfor());
                                bean.setStars(listBean.get(count).getStars());
                                bean.setTel(listBean.get(count).getTel());

                                list.add(bean);
                                count++;
                                adapter = new T63Adapter(list, T63Activity.this);
                                gv.setAdapter(adapter);
                            }
                        }, 100, 100, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        });
                        queue.add(imageRequest);
                    }
                }else{
                    Toast.makeText(T63Activity.this,"暂无数据",Toast.LENGTH_SHORT).show();
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    jsonObject.put("UserName", "user1");
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            try {
                                JSONObject jsonObject1;
                                jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    jsonObject1 = jsonArray.getJSONObject(i);
                                    place = jsonObject1.getString("place");
                                    price = jsonObject1.getInt("price");
                                    pic = jsonObject1.getString("pic");
                                    stars = jsonObject1.getInt("stars");
                                    infor = jsonObject1.getString("infor");
                                    tel = jsonObject1.getString("tel");

                                    list_pic.add(AppConfig.BASE_URL_IMG + pic);
                                    T63Bean t63Bean = new T63Bean();
                                    t63Bean.setName(place);
                                    t63Bean.setMoney(price+"");
                                    t63Bean.setInfor(infor);
                                    t63Bean.setStars(stars);
                                    t63Bean.setTel(tel);
                                    listBean.add(t63Bean);
                                }
                                handler.sendEmptyMessage(0);

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

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();





    }

    private void initview() {
        listBean = new ArrayList<>();
        queue= Volley.newRequestQueue(this);
        img=findViewById(R.id.img_back_63);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gv=findViewById(R.id.gv_63);
        list=new ArrayList<>();
        list_pic=new ArrayList<>();
    }
}
