package cn.pfc.pfc523.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.beans.Light2Bean;

public class Light2Adapter extends BaseAdapter {
    List<Light2Bean>list;
    Context context;

    public Light2Adapter(List<Light2Bean> list, Context context) {
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
        View view= LayoutInflater.from(context).inflate(R.layout.item_2,null);
        TextView t1=view.findViewById(R.id.tv_item2_1);
        TextView t2=view.findViewById(R.id.tv_item2_2);
        TextView t3=view.findViewById(R.id.tv_item2_3);
        TextView t4=view.findViewById(R.id.tv_item2_4);
        t1.setText(list.get(position).getRoad()+"");
        t2.setText(list.get(position).getRed()+"");
        t3.setText(list.get(position).getYellow()+"");
        t4.setText(list.get(position).getGreen()+"");
        return view;
    }
}
