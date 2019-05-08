package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import cn.pfc.pfc523.R;

public class Dialog42Activity extends Activity {

    ImageView img_dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_4_2);


        img_dialog=findViewById(R.id.img_back_dialog4_2);
        Intent intent=getIntent();
        int a=intent.getIntExtra("img",0);
        switch (a){
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
        }
        img_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
