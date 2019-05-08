package cn.pfc.pfc523.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.beans.MyFeed;

public class MyFeedAdapter extends BaseAdapter {

    private List<MyFeed> data;
    private Context context;

    public MyFeedAdapter(List<MyFeed> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"InflateParams", "ViewHolder"})
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.feed_item,null);
        TextView item_feed_title = view.findViewById(R.id.item_feed_title);
        TextView item_feed_state = view.findViewById(R.id.item_feed_state);
        TextView item_feed_submitTime = view.findViewById(R.id.item_feed_submitTime);
        TextView item_feed_hfTime = view.findViewById(R.id.item_feed_hfTime);
        return view;
    }
}
