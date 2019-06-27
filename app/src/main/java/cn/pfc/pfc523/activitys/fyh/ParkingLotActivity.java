package cn.pfc.pfc523.activitys.fyh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.config.AppConfig;
import cn.pfc.pfc523.http.HttpThread;

public class ParkingLotActivity extends AppCompatActivity {
    private ImageView mParkingBackImage;
    private ImageView mParkingImage;
    private TextView mParkingTVCarCount;
    private Button mParkingBtnQuery;
    private TextView mParkingMontyTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_lot);
        initView();
        requestData();
    }

    private void requestData() {
        try {
            new HttpThread(ParkingLotActivity.this)
            .setURL(AppConfig.BASE_URL+"GetStopCar.do")
            .setJson(new JSONObject().put("UserName","user1"))
            .setOnRequest(new HttpThread.OnRequest() {
                @Override
                public void onOk(JSONObject jsonObject, boolean isOK) throws JSONException {
                    if(isOK)
                    {
                        JSONArray jsonArray = jsonObject.getJSONArray("Free");
                        mParkingTVCarCount.setText("剩余车位："+jsonArray.length()+"/10");
                    }
                }
            }).run();
            new HttpThread(ParkingLotActivity.this)
                    .setURL(AppConfig.BASE_URL+"GetParkRate.do")
                    .setJson(new JSONObject().put("UserName","user1"))
                    .setOnRequest(new HttpThread.OnRequest() {
                        @Override
                        public void onOk(JSONObject jsonObject, boolean isOK) throws JSONException {
                            if(isOK)
                            {
                                int money = jsonObject.getInt("Money");
                                mParkingMontyTv.setText("收费标准："+money+"元/小时");
                            }
                        }
                    }).run();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        mParkingBackImage = (ImageView) findViewById(R.id.mParkingBackImage);
        mParkingBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mParkingImage = (ImageView) findViewById(R.id.mParkingImage);
        mParkingTVCarCount = (TextView) findViewById(R.id.mParkingTVCarCount);
        mParkingBtnQuery = (Button) findViewById(R.id.mParkingBtnQuery);
        mParkingMontyTv = (TextView) findViewById(R.id.mParkingMontyTv);
        mParkingBtnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });

    }
}
