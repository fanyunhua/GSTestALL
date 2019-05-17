package cn.pfc.pfc523.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.beans.BusQueryBean;

public class MExpandListViewAdapter extends BaseExpandableListAdapter {
    Map<String, List<BusQueryBean>> map;
    String title[];
    LayoutInflater layoutInflater;

    public MExpandListViewAdapter(Context context,Map<String, List<BusQueryBean>> map, String[] title) {
        this.map = map;
        this.title = title;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return map.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map.get(title[groupPosition]).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return map.get(title[groupPosition]);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return  map.get(title[groupPosition]).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        if(view==null)
        {
            view = layoutInflater.inflate(R.layout.bus_query_group_layout,null);
        }
        TextView textView = view.findViewById(R.id.mBusQueryFGroupTV);
        textView.setText(title[groupPosition]);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        if(view==null)
        {
            view = layoutInflater.inflate(R.layout.bus_query_child_layout,null);
        }
        TextView t1 = view.findViewById(R.id.mBusQueryFChildTV1);
        TextView t2 = view.findViewById(R.id.mBusQueryFChildTV2);
        TextView t3 = view.findViewById(R.id.mBusQueryFChildTV3);
        BusQueryBean busQueryBean = map.get(title[groupPosition]).get(childPosition);
        t1.setText(busQueryBean.getNum());
        t2.setText(busQueryBean.getTime());
        t3.setText(busQueryBean.getDistance());
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }



}
