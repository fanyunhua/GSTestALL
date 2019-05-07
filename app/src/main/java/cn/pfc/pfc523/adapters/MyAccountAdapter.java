package cn.pfc.pfc523.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.beans.MyAccountBean;

public class MyAccountAdapter extends BaseAdapter {
    List<MyAccountBean>list;
    Context context;

    public MyAccountAdapter(List<MyAccountBean> list, Context context) {
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
        View view= LayoutInflater.from(context).inflate(R.layout.item_3,null);
        TextView t1=view.findViewById(R.id.tv_item3_1);
        TextView t2=view.findViewById(R.id.tv_item3_2);
        TextView t3=view.findViewById(R.id.tv_item3_3);
        TextView t4=view.findViewById(R.id.tv_item3_4);
        TextView t5=view.findViewById(R.id.tv_item3_5);
        t1.setText(list.get(position).getId()+"");
        t2.setText(list.get(position).getCar_id()+"");
        t3.setText(list.get(position).getMoney()+"");
        t4.setText(list.get(position).getRen()+"");
        t5.setText(list.get(position).getTime()+"");
        return view;
    }
}
