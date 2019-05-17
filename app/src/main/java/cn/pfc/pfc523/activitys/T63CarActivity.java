package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import cn.pfc.pfc523.R;

public class T63CarActivity extends Activity {
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_63buycar);
        
        img=findViewById(R.id.img_back_63buycar);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initview();
    }

    private void initview() {
        
    }
}
