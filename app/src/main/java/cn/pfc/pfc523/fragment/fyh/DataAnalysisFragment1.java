package cn.pfc.pfc523.fragment.fyh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.config.AppConfig;
import cn.pfc.pfc523.http.HttpThread;

public class DataAnalysisFragment1 extends Fragment {
    private PieChart mDataAnalisisPie1;
    List<PieEntry> list;
    List<String> peccancyCar,allUser;
    boolean flag = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_data_analysis_fragment1,null);
        mDataAnalisisPie1 = (PieChart) view.findViewById(R.id.mDataAnalisisPie1);
        initChart();
        initData();
        requestData();
        return view;
    }

    private void requestData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpThread httpThread = new HttpThread(getContext());
                HttpThread httpThread2 = new HttpThread(getContext());

                try {
                    httpThread.setURL(AppConfig.BASE_URL+"GetAllCarPeccancy.do").
                            setJson(new JSONObject().put("UserName","admin")).
                            setOnRequest(new HttpThread.OnRequest() {
                                @Override
                                public void onOk(JSONObject jsonObject, boolean isOK) throws JSONException {
                                    if(isOK)
                                    {
                                        List<String> ll = new ArrayList<>();
                                        JSONArray jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");
                                        for (int i = 0; i <jsonArray.length() ; i++) {
                                            JSONObject jj = jsonArray.getJSONObject(i);
                                            ll.add(jj.getString("carnumber"));
                                        }
                                        Set set = new HashSet();
                                        for (String str:ll) {
                                            if(set.add(str)) peccancyCar.add(str);
                                        }
                                        flag = true;
                                    }
                                }
                            }).run();
                    Thread.sleep(100);
                    httpThread2.setURL(AppConfig.BASE_URL+"GetCarInfo.do").
                            setJson(new JSONObject().put("UserName","admin")).
                            setOnRequest(new HttpThread.OnRequest() {
                                @Override
                                public void onOk(JSONObject jsonObject, boolean isOK) throws JSONException {
                                    if(isOK)
                                    {
                                        JSONArray jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");
                                        for (int i = 0; i <jsonArray.length() ; i++) {
                                            JSONObject jj = jsonArray.getJSONObject(i);
                                            allUser.add(jj.getString("carnumber"));
                                        }
                                        if(flag)
                                        {
                                            float y = (peccancyCar.size()/allUser.size())*100;
                                            float w = ((allUser.size()-peccancyCar.size())/allUser.size())*100;
                                            Log.d("sssssss",y+"");
                                            Log.d("sssssss",w+"");
                                            list.add(new PieEntry(peccancyCar.size(),"有违章"));
                                            list.add(new PieEntry(allUser.size(),"无违章"));
                                            PieDataSet pieDataSet = new PieDataSet(list,"");
                                            pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                                            PieData pieData = new PieData(pieDataSet);
                                            mDataAnalisisPie1.setData(pieData);
                                            mDataAnalisisPie1.invalidate();
                                        }
                                    }
                                }
                            }).run();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void initChart() {
        Description description = new Description();
        description.setText("");
        mDataAnalisisPie1.setDescription(description);
        mDataAnalisisPie1.setDrawHoleEnabled(false);
        mDataAnalisisPie1.setUsePercentValues(true);
        Legend legend = mDataAnalisisPie1.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        mDataAnalisisPie1.getLegend().setTextSize(30);
    }

    private void initData() {
        list = new ArrayList<>();
        peccancyCar = new ArrayList<>();
        allUser = new ArrayList<>();
    }
}
