package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import cn.pfc.pfc523.R;

public class MyAccountActivity extends Activity {
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_myaccount);

        img=findViewById(R.id.img_back_1);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
