package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import cn.pfc.pfc523.R;

public class Dialog4Activity extends Activity {

    VideoView vv;
    ImageView img_dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_4_1);


        vv=findViewById(R.id.vv_dialog4);
        img_dialog=findViewById(R.id.img_back_dialog4);
        Intent intent=getIntent();
        int a=intent.getIntExtra("play",0);
        switch (a){
            case 0:
                vv.setVideoPath("android.resource://"+getPackageName()+"/raw/"+R.raw.bus);
                break;
            case 1:
                vv.setVideoPath("android.resource://"+getPackageName()+"/raw/"+R.raw.car3);
                break;
            case 2:
                vv.setVideoPath("android.resource://"+getPackageName()+"/raw/"+R.raw.english);
                break;
            case 3:
                vv.setVideoPath("android.resource://"+getPackageName()+"/raw/"+R.raw.road);
                break;
            case 4:
                vv.setVideoPath("android.resource://"+getPackageName()+"/raw/"+R.raw.pm);
                break;
        }
        vv.start();
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        img_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
