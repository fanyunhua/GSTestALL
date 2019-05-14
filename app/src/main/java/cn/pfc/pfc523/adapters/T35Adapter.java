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
import cn.pfc.pfc523.beans.CarWzBean;
import cn.pfc.pfc523.beans.T35Bean;

public class T35Adapter extends BaseAdapter {
    List<T35Bean>list;
    Context context;

    public T35Adapter(List<T35Bean> list, Context context) {
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
        View view= LayoutInflater.from(context).inflate(R.layout.item_35,null);
        ImageView img=view.findViewById(R.id.img_item35);
        TextView tv1=view.findViewById(R.id.tv_item35_1);
        TextView tv2=view.findViewById(R.id.tv_item35_2);
        img.setBackgroundResource(list.get(position).getImg());
        tv1.setText(list.get(position).getPlace());
        tv2.setText("票价￥"+list.get(position).getPrice());

        return view;
    }
}
