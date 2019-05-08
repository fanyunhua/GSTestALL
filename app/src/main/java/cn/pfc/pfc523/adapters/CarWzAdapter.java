package cn.pfc.pfc523.adapters;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.beans.CarWzBean;

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
        ImageView img=view.findViewById(R.id.img_item4);
        TextView tv=view.findViewById(R.id.tv_item4);

        img.setBackgroundResource(list.get(position).getUrl());
        tv.setText(list.get(position).getName());

        return view;
    }
}
