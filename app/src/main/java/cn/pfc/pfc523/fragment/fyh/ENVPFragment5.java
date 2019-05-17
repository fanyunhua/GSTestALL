package cn.pfc.pfc523.fragment.fyh;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.utils.db.DBUtils;

public class ENVPFragment5 extends Fragment {
    LineChart lineChart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_en_vp_item_fragment5,null);

        initView(view);
        requestData();
        return view;
    }

    private void requestData() {
        String sql = "select pm,dt from env order by dt desc limit 0,21";
        Cursor cursor = DBUtils.cha(getContext()).rawQuery(sql,null);
        if(cursor.getCount()>0)
        {
            List<Entry> list = new ArrayList<>();
            List<String> title = new ArrayList<>();
            int count = 0;
            cursor.moveToFirst();
            while (cursor.moveToNext())
            {
                list.add(new Entry(cursor.getInt(0),count));
                title.add(cursor.getString(1));
                count++;
            }
            LineDataSet lineDataSet = new LineDataSet(list,"");
            lineDataSet.setCircleColorHole(Color.BLUE);
            lineDataSet.setColor(Color.BLUE);
            LineData lineData = new LineData(lineDataSet);
            lineChart.setData(lineData);
            lineChart.invalidate();
        }
        else
        {
            Toast.makeText(getContext(),"暂无数据",Toast.LENGTH_SHORT).show();
        }
    }

    private void initView(View view) {
        lineChart = view.findViewById(R.id.mENVPF5LineChart);
        Description description = new Description();
        description.setText("");
        lineChart.setDescription(description);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getLegend().setEnabled(false);
    }
}
