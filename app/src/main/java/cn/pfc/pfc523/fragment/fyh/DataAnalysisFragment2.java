package cn.pfc.pfc523.fragment.fyh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import cn.pfc.pfc523.R;

public class DataAnalysisFragment2 extends Fragment {
    private PieChart mDataAnalisisPie2;
    List<PieEntry>list;
    List<String>peccancyCar,allUser;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_data_analysis_fragment2,null);
        mDataAnalisisPie2 = (PieChart) view.findViewById(R.id.mDataAnalisisPie2);
        initChart();
        initData();
        //TODO 绘图
        return view;
    }

    private void initChart() {
        Description description = new Description();
        description.setText("");
        mDataAnalisisPie2.setDescription(description);
        mDataAnalisisPie2.setDrawHoleEnabled(false);
        mDataAnalisisPie2.setUsePercentValues(true);
        Legend legend = mDataAnalisisPie2.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        mDataAnalisisPie2.getLegend().setTextSize(30);
    }

    private void initData() {
        list = new ArrayList<>();
        peccancyCar = new ArrayList<>();
        allUser = new ArrayList<>();
    }
}
