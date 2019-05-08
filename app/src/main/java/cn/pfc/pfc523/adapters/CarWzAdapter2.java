package cn.pfc.pfc523.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.beans.CarWzBean2;

public class CarWzAdapter2 extends BaseAdapter {
    List<CarWzBean2>list;
    Context context;

    public CarWzAdapter2(List<CarWzBean2> list, Context context) {
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
        View view= LayoutInflater.from(context).inflate(R.layout.item_4_2,null);
        ImageView img=view.findViewById(R.id.img_item4_2);

        img.setBackgroundResource(list.get(position).getUrl());

        return view;
    }
}
