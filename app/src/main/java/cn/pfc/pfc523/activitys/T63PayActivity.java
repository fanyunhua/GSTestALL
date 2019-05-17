package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;
import java.util.Random;

import cn.pfc.pfc523.R;

public class T63PayActivity extends Activity {
    ImageView img,img_infor;
    TextView tv_infor;
    Handler handler;
    Random random;
    int m=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_63pay);

        img=findViewById(R.id.img_back_63pay);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_infor=findViewById(R.id.img_63pay);
        tv_infor=findViewById(R.id.tv_63pay);
        random=new Random();

        img();
        img_infor.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent=getIntent();
                tv_infor.setText("付款项目："+intent.getStringExtra("place")+
                        "    付款金额："+intent.getStringExtra("sum")+"元");
                return false;
            }
        });
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                m=random.nextInt(500);
                img();
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(5000);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void img() {
        int w=400;
        int h=400;
        String url=m+"1231";
        Hashtable<EncodeHintType,String> hints=new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");

        BitMatrix bitMatrix = null;
        try {
            bitMatrix=new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE,w,h,hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        int pixs[]=new int[w*h];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if(bitMatrix.get(j,i)){
                    pixs[i*w+j]=0xff000000;
                }else {
                    pixs[i*w+j]=0xffffffff;
                }
            }
        }
        Bitmap bitmap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixs,0,w,0,0,w,h);
        img_infor.setImageBitmap(bitmap);
    }
}
