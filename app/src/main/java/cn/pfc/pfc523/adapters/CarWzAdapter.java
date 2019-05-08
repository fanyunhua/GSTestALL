package cn.pfc.pfc523.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.beans.CarWzBean;
import cn.pfc.pfc523.beans.Light2Bean;

public class CarWzAdapter extends BaseAdapter {
    List<CarWzBean>list;
    Context context;

    public CarWzAdapter(List<CarWzBean> list, Context context) {
        this.list = list;
        this.context = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_4,null);
        VideoView vv=view.findViewById(R.id.vv_item4);
        TextView tv=view.findViewById(R.id.tv_item4);

        vv.setVideoPath(list.get(position).getUrl());
        vv.start();

        tv.setText(list.get(position).getName());
        return view;
    }
}
