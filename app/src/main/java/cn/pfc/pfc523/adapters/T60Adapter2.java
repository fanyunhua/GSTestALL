package cn.pfc.pfc523.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.beans.T60bean2;

public class T60Adapter2 extends BaseAdapter {
    List<T60bean2>list;
    Context context;

    public T60Adapter2(List<T60bean2> list, Context context) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.item60_2,null);
        ImageView img=convertView.findViewById(R.id.img_item60_2);
        TextView tv=convertView.findViewById(R.id.tv_item60_2);

//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                list.remove(position);
//                notifyDataSetChanged();
//            }
//        });
        tv.setText(list.get(position).getNews());

        return convertView;
    }
}
