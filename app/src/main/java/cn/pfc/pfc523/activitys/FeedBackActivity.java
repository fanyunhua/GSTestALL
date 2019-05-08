package cn.pfc.pfc523.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.config.AppConfig;
import cn.pfc.pfc523.http.HttpThread;

public class FeedBackActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView feed_back;
    TextView feed_my;
    EditText feed_title,feed_content,feed_phone;
    Button feed_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initViews();
        bindListeners();
    }

    private void initViews() {
        feed_back = findViewById(R.id.feed_back);
        feed_my = findViewById(R.id.feed_my);
        feed_title = findViewById(R.id.feed_title);
        feed_content = findViewById(R.id.feed_content);
        feed_phone = findViewById(R.id.feed_phone);
        feed_submit = findViewById(R.id.feed_submit);
    }

    private void bindListeners() {
        feed_back.setOnClickListener(this);
        feed_submit.setOnClickListener(this);
        feed_my.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.feed_back:
                finish();
                break;
            case R.id.feed_submit:
                String title = feed_title.getText().toString();
                String content = feed_content.getText().toString();
                String phone = feed_phone.getText().toString();
                if(title.length() > 0){
                    if(content.length() > 0){
                        if(phone.length() == 11){
                            submit(title,content,phone);
                        }else
                            Toast.makeText(getApplicationContext(), "请输入完整的手机号码", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(getApplicationContext(), "请输入意见内容", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getApplicationContext(), "请输入意见标题", Toast.LENGTH_SHORT).show();
                break;
            case R.id.feed_my:
                startActivity(new Intent(FeedBackActivity.this,MyFeedActivity.class));
                break;
        }
    }

    private void submit(String title,String content,String phone){
        try {
            new HttpThread(this)
                    .setURL(AppConfig.BASE_URL + "SubmitSuggestions.do")
                    .setJson(new JSONObject().put("UserName","user1")
                            .put("title",title)
                            .put("suggestion",content)
                            .put("phone",phone))
                    .setOnRequest(new HttpThread.OnRequest() {
                @Override
                public void onOk(JSONObject jsonObject, boolean isOK) throws JSONException {
                    if(jsonObject.optString("RESULT").equals("S"))
                        Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "失败", Toast.LENGTH_SHORT).show();

                }
            }).run();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
