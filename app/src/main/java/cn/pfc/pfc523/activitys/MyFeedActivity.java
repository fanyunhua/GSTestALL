package cn.pfc.pfc523.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.adapters.MyFeedAdapter;
import cn.pfc.pfc523.beans.MyFeed;
import cn.pfc.pfc523.config.AppConfig;
import cn.pfc.pfc523.http.HttpThread;

public class MyFeedActivity extends AppCompatActivity {
    ListView my_feed_lv;
    List<MyFeed> data;
    MyFeedAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_feed);
        initViews();
        initData();
    }

    private void initData() {
        try {
            new HttpThread(this).setURL(AppConfig.BASE_URL + "GetSuggestions.do")
                    .setJson(new JSONObject().put("UserName","user1"))
                    .setOnRequest(new HttpThread.OnRequest() {
                        @Override
                        public void onOk(JSONObject jsonObject, boolean isOK) throws JSONException {
                            if(jsonObject.optString("RESULT").equals("S")){
                                JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                                    String title,subTime,hfTime,hfContent;
                                    String state = jsonObject1.optString("status");
                                    switch (state){
                                        case "未受理":
                                            title = jsonObject1.optString("title");
                                            subTime = jsonObject1.optString("subTime");
                                            data.add(new MyFeed(title,state,subTime,"--","--"));
                                            break;
                                        case "受理":
                                            title = jsonObject1.optString("title");
                                            subTime = jsonObject1.optString("subTime");
                                            hfTime = jsonObject1.optString("replyTime");
                                            hfContent = jsonObject1.optString("replyContent");
                                            data.add(new MyFeed(title,state,subTime,hfContent,hfTime));
                                            break;
                                    }
                                }
                                adapter.refresh(data);

                            }else{
                                Toast.makeText(getApplicationContext(), "数据获取失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).run();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initViews() {
        my_feed_lv = findViewById(R.id.my_feed_lv);
        data = new ArrayList<>();
        adapter = new MyFeedAdapter(data,this);
        my_feed_lv.setAdapter(adapter);
        findViewById(R.id.my_feed_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
