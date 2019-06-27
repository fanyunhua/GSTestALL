package cn.pfc.pfc523.activitys.fyh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.config.AppConfig;
import cn.pfc.pfc523.http.HttpThread;

public class PeccancyTypeParserActivity extends AppCompatActivity {
    private RadarChart mPCRadarChart;
    List<String> pcCodeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peccancy_type_parser);
        initView();
        requestData();
    }

    private void requestData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new HttpThread(PeccancyTypeParserActivity.this).
                            setURL(AppConfig.BASE_URL+"GetAllCarPeccancy.do")
                            .setJson(new JSONObject().put("UserName","admin"))
                            .setOnRequest(new HttpThread.OnRequest() {
                                @Override
                                public void onOk(JSONObject jsonObject, boolean isOK) throws JSONException {
                                    if (isOK)
                                    {
                                        JSONArray jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");
                                        for (int i = 0; i <jsonArray.length() ; i++) {
                                            JSONObject jj = jsonArray.getJSONObject(i);
                                            pcCodeList.add(jj.getString("pcode"));
                                        }
                                    }
                                }
                            }).run();
                    Thread.sleep(200);
                    new HttpThread(PeccancyTypeParserActivity.this)
                            .setURL(AppConfig.BASE_URL+"GetPeccancyType.do")
                            .setJson(new JSONObject().put("UserName","admin"))
                            .setOnRequest(new HttpThread.OnRequest() {
                                @Override
                                public void onOk(JSONObject jsonObject, boolean isOK) throws JSONException {
                                    if(isOK)
                                    {
                                        final Map<String,String> map = new HashMap<>();
                                        JSONArray jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");
                                        for (int i = 0; i <jsonArray.length() ; i++) {
                                            JSONObject jj = jsonArray.getJSONObject(i);
                                            map.put(jj.getString("pcode"),jj.getString("premarks"));
                                        }
                                        Map<String,Integer> mp = new TreeMap<>(new Comparator<String>() {
                                            @Override
                                            public int compare(String o1, String o2) {
                                                return o2.compareTo(o1);
                                            }
                                        });
                                        Set set = new HashSet();
                                        final List<String> list = new ArrayList();
                                        for (String cc:pcCodeList) {
                                            Integer count =  mp.get(cc);
                                            mp.put(cc,(count==null)?1:count+1);
                                            if(set.add(cc)) list.add(cc);
                                        }
                                        List<Integer> ll = new ArrayList<>();
                                        for (String cc:list) {
                                            ll.add(mp.get(cc));
                                        }
                                        List<RadarEntry> radarEntries = new ArrayList<>();
                                        if(ll.size()>=5)
                                        {
                                            for (int i = 0; i <5 ; i++) {
                                                RadarEntry ff = new RadarEntry(ll.get(i),map.get(list.get(i))+"");
                                                Log.d("ssssssssssss",map.get(list.get(i))+"");
                                                radarEntries.add(ff);
                                            }
                                        }
                                        RadarDataSet radarDataSet = new RadarDataSet(radarEntries,"");
                                        radarDataSet.setFillColor(ColorTemplate.COLORFUL_COLORS[0]);
                                        radarDataSet.setDrawFilled(true);
                                        RadarData radarData = new RadarData(radarDataSet);
                                        mPCRadarChart.setData(radarData);
                                        mPCRadarChart.invalidate();
                                    }
                                }
                            }).run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void initView() {
        mPCRadarChart = (RadarChart) findViewById(R.id.mPCRadarChart);
        pcCodeList = new ArrayList<>();
        Legend legend = mPCRadarChart.getLegend();
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
    }
}
