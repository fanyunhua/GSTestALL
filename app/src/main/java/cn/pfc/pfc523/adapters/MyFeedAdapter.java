package cn.pfc.pfc523.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
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

    public void refresh(List<MyFeed> data){
        this.data = data;
        notifyDataSetChanged();
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
        TextView item_feed_hfContent = view.findViewById(R.id.item_feed_hfContent);

        item_feed_title.setText(String.format(context.getResources().getString(R.string.feed_title),data.get(position).getTitle()));
        item_feed_state.setText(String.format(context.getResources().getString(R.string.feed_state),data.get(position).getState()));
        item_feed_submitTime.setText(String.format(context.getResources().getString(R.string.feed_submitTime),data.get(position).getSubmitTime()));
        item_feed_hfTime.setText(String.format(context.getResources().getString(R.string.feed_hfTime),data.get(position).getHfTime()));
        item_feed_hfContent.setText(String.format(context.getResources().getString(R.string.feed_hfContent),data.get(position).getHfContent()));

        return view;
    }
}
