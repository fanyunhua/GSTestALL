package cn.pfc.pfc523.activitys.fyh;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.adapters.MBusQueryDialogListViewAdapter;
import cn.pfc.pfc523.adapters.MExpandListViewAdapter;
import cn.pfc.pfc523.beans.BusPeopleBean;
import cn.pfc.pfc523.beans.BusQueryBean;
import cn.pfc.pfc523.config.AppConfig;
import cn.pfc.pfc523.http.HttpThread;

public class BusQueryActivity extends AppCompatActivity {
    private ImageView imgBackMBusQuery;
    private TextView mBusTVQueryPeople;
    private Button mBusBTNQuery;
    private ExpandableListView mBusEListView;
    List<BusQueryBean> list1,list2;
    Map<String,List<BusQueryBean>> map;
    String title []= new String[]{"中医院站","联想大厦站"};
    boolean flag = true;
    boolean peopleFlag = true;
    Random random;
    Dialog dialog;
    int peopleCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_query);
        random = new Random();
        initView();
        requestEListData();
        requestDialogData();
    }

    private void requestDialogData() {

        final List<BusPeopleBean> ll = new ArrayList<>();
        final int[] kk = {1};
        dialog = new Dialog(BusQueryActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        window.setContentView(R.layout.layout_dialog_bus_query_people);
        final ListView listView = window.findViewById(R.id.mBusQueryDialogListView);
        final TextView mBusQueryDialogCountPeople = window.findViewById(R.id.mBusQueryDialogCountPeople);
        Button mBusQueryDialogBackBtn =window.findViewById(R.id.mBusQueryDialogBackBtn);
        mBusQueryDialogBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        mBusBTNQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();


            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpThread httpThread = new HttpThread(BusQueryActivity.this);
                httpThread.setURL(AppConfig.BASE_URL+"GetBusCapacity.do");
                try {
                    while (true)
                    {
                        peopleCount = 0;
                        kk[0] = 1;
                        ll.clear();
                        ll.add(new BusPeopleBean("序号","公交车编号","承载人数"));
                        for (int i = 1; i <16 ; i++) {
                                httpThread.setJson(new JSONObject().put("BusId",i).put("UserName","user1")).setOnRequest(new HttpThread.OnRequest() {
                                    @Override
                                    public void onOk(JSONObject jsonObject, boolean isOK) throws JSONException {
                                        if (isOK)
                                        {
                                            peopleCount+=jsonObject.getInt("BusCapacity");
                                            ll.add(new BusPeopleBean(kk[0] +"", kk[0] +"",jsonObject.get("BusCapacity")+""));
                                            kk[0]++;
                                            if(kk[0]==15)
                                            {
                                                mBusTVQueryPeople.setText("当前承载能力："+peopleCount+"人");
                                                MBusQueryDialogListViewAdapter adapter = new MBusQueryDialogListViewAdapter(BusQueryActivity.this,ll);
                                                listView.setAdapter(adapter);
                                                mBusQueryDialogCountPeople.setText(peopleCount+"");
                                            }
                                        }
                                    }
                                }).run();

                        }
                        Thread.sleep(3000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void requestEListData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpThread httpThread = new HttpThread(BusQueryActivity.this);
                HttpThread httpPeople = new HttpThread(BusQueryActivity.this);
//                HttpThread httpThread3 = new HttpThread(BusQueryActivity.this);
                try {
                    while (true) {
                        list1.clear();
                        list2.clear();
                        map.clear();
                        httpThread.setURL(AppConfig.BASE_URL + "GetBusStationInfo.do").
                                setJson(new JSONObject().put("BusStationId",1).
                                        put("UserName","user1")).run();
                        httpThread.setURL(AppConfig.BASE_URL + "GetBusStationInfo.do").
                                setJson(new JSONObject().put("BusStationId",2).
                                        put("UserName","user1")).run();
//                        httpThread3.setURL(AppConfig.BASE_URL + "GetBusCapacity.do").
//                                setJson(new JSONObject().put("BusId",1).put("UserName","user1")).run();
//                        httpThread3.setURL(AppConfig.BASE_URL + "GetBusCapacity.do").
//                                setJson(new JSONObject().put("BusId",2).put("UserName","user1")).run();

                        httpThread. setOnRequest(
                                new HttpThread.OnRequest() {
                                    @Override
                                    public void onOk(JSONObject jsonObject, boolean isOK) throws JSONException {
                                        if(isOK)
                                        {
                                            int one = random.nextInt(100);
                                            int two = random.nextInt(100);
                                            JSONArray jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");
                                            if (flag)
                                            {
                                                for (int i = 0; i <jsonArray.length() ; i++) {
                                                    JSONObject jj = jsonArray.getJSONObject(i);
                                                    int distance = jj.getInt("Distance");
                                                    int time = ((distance*100)/20000)/60;
                                                    if (i==0)
                                                        list1.add(new BusQueryBean(i+1+"号"+"("+one+")人",""+time+"分钟到达","距站台"+(distance/10)+"米"));
                                                    else
                                                        list1.add(new BusQueryBean(i+1+"号"+"("+two+")人",""+time+"分钟到达","距站台"+(distance/10)+"米"));
                                                }
                                                flag = false;
                                            }
                                            else
                                            {
                                                for (int i = 0; i <jsonArray.length() ; i++) {
                                                    JSONObject jj = jsonArray.getJSONObject(i);
                                                    int distance = jj.getInt("Distance");
                                                    int time = ((distance*100)/20000)/60;
                                                    if (i==0)
                                                        list2.add(new BusQueryBean(i+1+"号"+"("+one+")人",""+time+"分钟到达","距站台"+(distance/10)+"米"));
                                                    else
                                                        list2.add(new BusQueryBean(i+1+"号"+"("+two+")人",""+time+"分钟到达","距站台"+(distance/10)+"米"));                                                }
                                                map.put(title[0],list1);
                                                map.put(title[1],list2);
                                                MExpandListViewAdapter adapter = new MExpandListViewAdapter(BusQueryActivity.this,
                                                        map,title);
                                                mBusEListView.setAdapter(adapter);
                                                int count = mBusEListView.getCount();
                                                for (int i = 0; i <count ; i++) {
                                                    mBusEListView.expandGroup(i);
                                                }
                                                flag = true;
                                            }

                                        }
                                    }
                                }
                        );
                        Thread.sleep(3000);
                        //TODO 数据同步 延时容易崩溃暂时随机数代替
//                        httpThread3.setOnRequest(
//                                new HttpThread.OnRequest() {
//                                    @Override
//                                    public void onOk(JSONObject jsonObject, boolean isOK) throws JSONException {
//                                        if(isOK)
//                                        {
//                                            int people = jsonObject.getInt("BusCapacity");
//                                            if(peopleFlag)
//                                            {
//                                                list1.get(0).setNum("1号("+people+"人)");
//                                                list2.get(0).setNum("1号("+people+"人)");
//                                                peopleFlag = false;
//                                            }
//                                            else
//                                            {
//                                                list1.get(1).setNum("1号("+people+"人)");
//                                                list2.get(1).setNum("1号("+people+"人)");
//                                                map.put(title[0],list1);
//                                                map.put(title[1],list2);
//                                                MExpandListViewAdapter adapter = new MExpandListViewAdapter(BusQueryActivity.this,
//                                                        map,title);
//                                                mBusEListView.setAdapter(adapter);
//                                                peopleFlag = true;
//                                            }
//
//                                        }
//                                    }
//                                });
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    private void initView() {
        imgBackMBusQuery = (ImageView) findViewById(R.id.img_back_mBusQuery);
        imgBackMBusQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBusTVQueryPeople = (TextView) findViewById(R.id.mBusTVQueryPeople);
        mBusBTNQuery = (Button) findViewById(R.id.mBusBTNQuery);
        mBusEListView = (ExpandableListView) findViewById(R.id.mBusEListView);

        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        map = new HashMap<>();
    }
}
