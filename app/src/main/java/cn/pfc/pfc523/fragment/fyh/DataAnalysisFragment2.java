package cn.pfc.pfc523.fragment.fyh;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.Toast;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.activitys.fyh.DataAnalysisActivity;
import cn.pfc.pfc523.config.AppConfig;
import cn.pfc.pfc523.http.HttpThread;

public class DataAnalysisFragment2 extends Fragment {
    private HorizontalBarChart mDataAnalisisHBarChart;


    List<BarEntry>list;
    List<String>peccancyCar,allUser;
    Map<String,String> map;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_data_analysis_fragment2,null);
        mDataAnalisisHBarChart = (HorizontalBarChart) view.findViewById(R.id.mDataAnalisisHBarChart);

        initChart();
        initData();
        //TODO 绘图
        requestData();
        return view;
    }

    private void requestData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new HttpThread(getContext())
                            .setURL(AppConfig.BASE_URL+"GetAllCarPeccancy.do")
                            .setJson(new JSONObject().put("UserName","admin"))
                            .setOnRequest(new HttpThread.OnRequest() {
                                @Override
                                public void onOk(JSONObject jsonObject, boolean isOK) throws JSONException {
                                    if(isOK)
                                    {
                                        JSONArray jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");
                                        for (int i = 0; i <jsonArray.length() ; i++) {
                                            JSONObject jj = jsonArray.getJSONObject(i);
                                            peccancyCar.add(jj.getString("pcode"));
                                        }
                                    }
                                }
                            }).run();
                    Thread.sleep(200);
                    new HttpThread(getContext())
                            .setURL(AppConfig.BASE_URL+"GetPeccancyType.do")
                            .setJson(new JSONObject().put("UserName","admin"))
                            .setOnRequest(new HttpThread.OnRequest() {
                                @Override
                                public void onOk(JSONObject jsonObject, boolean isOK) throws JSONException {
                                    if(isOK)
                                    {
                                        JSONArray jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");
                                        for (int i = 0; i <jsonArray.length() ; i++) {
                                            JSONObject jj = jsonArray.getJSONObject(i);
                                            map.put(jj.getString("pcode"),jj.getString("premarks"));
                                        }
                                        float reCount = peccancyCar.size();//一共多少个违章避免后面再次计算
                                        Map mMap = new HashMap();//计数后的map
                                        List<String> result = new ArrayList<>();//去重后的违章类型
                                        Set set = new HashSet();
                                        //计数并去重
                                        for (String cc:peccancyCar) {
                                            Integer count = (Integer) mMap.get(cc);
                                            mMap.put(cc,(count==null)?1:count+1);
                                            if(set.add(cc)) result.add(cc);
                                        }
                                        final List<String> title = new ArrayList();
                                        for (int i = 0; i <result.size() ; i++) {
                                            title.add(map.get(result.get(i)));
                                            int cc = (int) mMap.get(result.get(i));
                                            float dd = cc;
                                            float res = (dd/reCount)*100;
                                            list.add(new BarEntry(i,res,map.get(result.get(i))));
                                        }
                                        BarDataSet barDataSet = new BarDataSet(list,"");
                                        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                                        barDataSet.setValueFormatter(new IValueFormatter() {
                                            @Override
                                            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                                                return String.format("%.2f",v)+"%";
                                            }
                                        });
                                        BarData barData = new BarData(barDataSet);
                                        mDataAnalisisHBarChart.setData(barData);
                                        mDataAnalisisHBarChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
                                            @Override
                                            public String getFormattedValue(float v, AxisBase axisBase) {
                                                return title.get((int) v)+"";
                                            }
                                        });
                                        mDataAnalisisHBarChart.invalidate();
                                    }
                                }
                            }).run();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    private void initChart() {
        Description description = new Description();
        description.setText("");
        mDataAnalisisHBarChart.setDescription(description);
        mDataAnalisisHBarChart.getLegend();
        mDataAnalisisHBarChart.getAxisRight().setEnabled(false);
        mDataAnalisisHBarChart.getAxisLeft().setAxisMinValue(0);
        mDataAnalisisHBarChart.getAxisLeft().setAxisMaxValue(17);
        XAxis xAxis = mDataAnalisisHBarChart.getXAxis();
        xAxis.setTextSize(10f);
        mDataAnalisisHBarChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
    }

    private void initData() {
        list = new ArrayList<>();
        peccancyCar = new ArrayList<>();
        allUser = new ArrayList<>();
        map = new HashMap();
    }
}
