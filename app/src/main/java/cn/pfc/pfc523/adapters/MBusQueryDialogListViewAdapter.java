package cn.pfc.pfc523.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.beans.BusPeopleBean;

public class MBusQueryDialogListViewAdapter extends BaseAdapter {
    List<BusPeopleBean> list;
    LayoutInflater layoutInflater;

    public MBusQueryDialogListViewAdapter(Context context,List<BusPeopleBean> list) {
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view==null)
        {
            view = layoutInflater.inflate(R.layout.layout_bus_query_dialog_listview_item,null);
        }
        TextView mBusQueryDialogItemTV1;
        TextView mBusQueryDialogItemTV2;
        TextView mBusQueryDialogItemTV3;
        BusPeopleBean bean = list.get(position);
        mBusQueryDialogItemTV1 = (TextView) view.findViewById(R.id.mBusQueryDialogItemTV1);
        mBusQueryDialogItemTV2 = (TextView) view.findViewById(R.id.mBusQueryDialogItemTV2);
        mBusQueryDialogItemTV3 = (TextView) view.findViewById(R.id.mBusQueryDialogItemTV3);
        mBusQueryDialogItemTV1.setText(bean.getId());
        mBusQueryDialogItemTV2.setText(bean.getBusId());
        mBusQueryDialogItemTV3.setText(bean.getProple());
        return view;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

}
