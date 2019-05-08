package cn.pfc.pfc523.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.beans.CSOne;
import cn.pfc.pfc523.beans.CSTwo;

public class CSAdapter extends BaseExpandableListAdapter {

    private List<CSOne> oneData;
    private List<List<CSTwo>> twoData;
    private Context context;

    public CSAdapter(List<CSOne> oneData, List<List<CSTwo>> twoData, Context context) {
        this.oneData = oneData;
        this.twoData = twoData;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return oneData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return twoData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return oneData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return twoData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.cs_one_item,parent,false);
        }
        TextView co_way = view.findViewById(R.id.co_way);
        TextView co_money = view.findViewById(R.id.co_money);
        TextView co_mileage = view.findViewById(R.id.co_mileage);
        TextView co_time1 = view.findViewById(R.id.co_time1);
        TextView co_time2 = view.findViewById(R.id.co_time2);

        co_way.setText(oneData.get(groupPosition).getWay());
        co_money.setText(String.format(context.getResources().getString(R.string.cs_money),oneData.get(groupPosition).getMoney()));
        co_mileage.setText(String.format(context.getResources().getString(R.string.cs_mileage),oneData.get(groupPosition).getMileage()));
        co_time1.setText(oneData.get(groupPosition).getTime1());
        co_time2.setText(oneData.get(groupPosition).getTime2());

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.cs_two_item,parent,false);
        }
        TextView ci_isStart = view.findViewById(R.id.ci_isStart);
        TextView ci_station = view.findViewById(R.id.ci_station);

        ci_isStart.setText(twoData.get(groupPosition).get(childPosition).getIsStart());
        ci_station.setText(twoData.get(groupPosition).get(childPosition).getStation());
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
