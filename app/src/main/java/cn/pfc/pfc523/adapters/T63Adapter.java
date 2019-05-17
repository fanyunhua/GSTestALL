package cn.pfc.pfc523.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.activitys.T63CarActivity;
import cn.pfc.pfc523.activitys.T63InForActivity;
import cn.pfc.pfc523.beans.T63Bean;

public class T63Adapter extends BaseAdapter {
    public static List<T63Bean>list;
    Context context;

    public T63Adapter(List<T63Bean> list, Context context) {
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
        convertView= LayoutInflater.from(context).inflate(R.layout.item_63,null);
        final ImageView img=convertView.findViewById(R.id.img_item63);
        TextView t1=convertView.findViewById(R.id.tv1_item63);
        TextView t2=convertView.findViewById(R.id.tv2_item63);
        Button but=convertView.findViewById(R.id.but_item63);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, T63CarActivity.class);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, T63InForActivity.class);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });

        img.setImageBitmap(list.get(position).getBitmap());
        t1.setText(list.get(position).getName());
        t2.setText("票价￥"+list.get(position).getMoney());
        return convertView;
    }
}
