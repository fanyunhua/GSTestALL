package cn.pfc.pfc523.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

public class InternetServices extends Service {
    Handler handler;
    boolean flag = true;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        netStutes();
    }
    void netStutes()
    {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                boolean now = isConn();
                if(flag!=now)
                {
                    if (now)
                    {
                        Toast.makeText(InternetServices.this,"网络已连接",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(InternetServices.this,"网络已断开",Toast.LENGTH_LONG).show();

                    }
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    try {
                        handler.sendEmptyMessage(0);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    boolean isConn()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null)
            return true;
        return false;
    }
}
