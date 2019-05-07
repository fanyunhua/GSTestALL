package cn.pfc.pfc523.activitys.EnvironmentIndex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.config.AppConfig;
import cn.pfc.pfc523.http.HttpThread;
import cn.pfc.pfc523.utils.db.DBUtils;

/**
 * @author fyh 2019.5.7 10:27
 * 环境指标  国赛题库5-7
 * */
public class EnvironmentIndexActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout linearLayouts[];
    int layoutID[] = new int[]{
            R.id.mENLL1,
            R.id.mENLL2,
            R.id.mENLL3,
            R.id.mENLL4,
            R.id.mENLL5,
            R.id.mENLL6
    };
    TextView textViews[];
    int tvID[] = new int[]{
            R.id.mENTV1,
            R.id.mENTV2,
            R.id.mENTV3,
            R.id.mENTV4,
            R.id.mENTV5,
            R.id.mENTV6
    };
    TextView mENTVYUZHI ;
    int co2,pm,w,s,g;
    boolean onStop = false;
    boolean requestOK = false;
    SharedPreferences sp;
    boolean yuzhiStates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment_index);
        requestOK = false;
        initView();
    }

    private void requestData () {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpThread httpThread = new HttpThread(EnvironmentIndexActivity.this);
                HttpThread httpThread2 = new HttpThread(EnvironmentIndexActivity.this);
                while (true)
                {
                    if(onStop) {break;}
                    try {
                    httpThread.setURL(AppConfig.BASE_URL+"GetAllSense.do").
                            setJson(new JSONObject().put("UserName","admin")).
                            setOnRequest(new HttpThread.OnRequest() {
                                @Override
                                public void onOk(JSONObject jsonObject, boolean isOK) throws JSONException {
                                    if(isOK)
                                    {
//                                        {"RESULT":"S","ERRMSG":"成功","city":"北京","pm2.5":8,"co2":5919,
//                                                "LightIntensity":1711,"humidity":44,"temperature":28}
//                                        {"RESULT":"F","ERRMSG":"失败"}

                                        yuzhiStates = sp.getBoolean("state",true);
                                        co2 = jsonObject.getInt("co2");
                                        pm  = jsonObject.getInt("pm2.5");
                                        s   = jsonObject.getInt("humidity");
                                        g   = jsonObject.getInt("LightIntensity");
                                        w   = jsonObject.getInt("temperature");
                                        requestOK = true;
                                        setText(co2+"",3);
                                        setText(pm+"",4);
                                        setText(s+"",1);
                                        setText(g+"",2);
                                        setText(w+"",0);
                                    }
                                }

                    }).run();
                    httpThread2.setURL(AppConfig.BASE_URL+"GetRoadStatus.do").
                            setJson(new JSONObject().put("UserName","user1").
                                    put("RoadId",1)).
                            setOnRequest(new HttpThread.OnRequest() {
                                @Override
                                public void onOk(JSONObject jsonObject, boolean isOK) throws JSONException {
                                    if(isOK)
                                    {
                                        int status;
                                        status = jsonObject.getInt("Status");
//                                        String env = "create table env (dt varchar(30),co2 int,pm int ,g int, s int,w int,road int )";
                                        if(requestOK)
                                        {
                                            //NONE 数据库存储
                                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM:SS");
                                            String dt = simpleDateFormat.format(new Date());
                                            String insert  = "insert into env values("+
                                                    "'"+dt+"',"+co2+","+pm+","+g+","+s+","+w+","+status+
                                                    ")";
                                            DBUtils.cun(EnvironmentIndexActivity.this).getWritableDatabase().execSQL(insert);
                                            if (yuzhiStates)
                                            {
                                                int arr[] = new int[]{w,s,g,co2,pm,status};
                                                for (int i = 0; i <layoutID.length ; i++) {
                                                    if (arr[i]>= sp.getInt(""+i,0))
                                                    {
                                                        setColor(i,Color.RED);
                                                    }
                                                    else
                                                    {
                                                        setColor(i,Color.GREEN);
                                                    }
                                                    Log.d("aaaaaaaaaaaaaa",i+"");
                                                }
                                            }
                                            requestOK = false;
                                        }


                                        setText(status+"",5);
                                    }
                                }
                            }).run();

                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void initView() {
        mENTVYUZHI = findViewById(R.id.mENTVYUZHI);
        linearLayouts = new LinearLayout[layoutID.length];
        textViews = new TextView[tvID.length];
        for (int i = 0; i <layoutID.length ; i++) {
            linearLayouts[i] = findViewById(layoutID[i]);
            textViews[i] = findViewById(tvID[i]);
            linearLayouts[i].setOnClickListener(this);
        }
        mENTVYUZHI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EnvironmentIndexActivity.this,SettingYuZhiActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        onStop = false;
        requestData();

        sp = getSharedPreferences("env",MODE_PRIVATE);
        yuzhiStates = sp.getBoolean("state",true);
    }
    @Override
    protected void onStop() {
        super.onStop();
        onStop = true;
    }
    private void setColor(int color)
    {
        //设置全部layout颜色
        for (int i = 0; i <linearLayouts.length ; i++) {
            linearLayouts[i].setBackgroundColor(color);
        }
    }
    private void setColor(int i,int color)
    {
        //设置全部单个layout颜色
            linearLayouts[i].setBackgroundColor(color);
    }
    private void setText(String text,int i)
    {
        //设置tv的值
        textViews[i].setText(text);
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i <layoutID.length ; i++) {
            if(v.getId()==layoutID[i])
            {
                Intent intent = new Intent(EnvironmentIndexActivity.this,ENDrawingVieaPagerActivity.class);
                intent.putExtra("index",i);
                startActivity(intent);
            }
        }
    }
}
