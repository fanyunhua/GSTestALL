package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.adapters.T63Adapter;
import cn.pfc.pfc523.beans.T63Bean;

public class T63InForActivity extends Activity {
    ImageView img,img_pic;
    TextView tv,tv_stars,tv_tel;
    RatingBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_63infor);

        initview();
        Intent intent = getIntent();
        final T63Bean position = T63Adapter.list.get(intent.getIntExtra("position",0));

        img_pic.setImageBitmap(position.getBitmap());
        tv.setText(position.getInfor());
        tv_stars.setText("游友点评："+position.getStars()+"星");
        tv_tel.setText("联系电话："+position.getTel());

        tv_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent();
                intent1.setAction(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse(Uri.parse("tel:")+position.getTel()));
                startActivity(intent1);
            }
        });
        bar.setRating(position.getStars());
    }

    private void initview() {
        img=findViewById(R.id.img_back_63_infor);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_pic=findViewById(R.id.img_63infor);
        tv=findViewById(R.id.tv_63infor);
        tv_stars=findViewById(R.id.tv1_63infor);
        tv_tel=findViewById(R.id.tv2_63infor);
        bar=findViewById(R.id.star_bar);
    }
}
