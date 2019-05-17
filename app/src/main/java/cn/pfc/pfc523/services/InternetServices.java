package cn.pfc.pfc523.services;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.WindowManager;
import android.widget.Toast;

public class InternetServices extends Service {
    Handler handler;
    boolean flag = true;
    AlertDialog dialog;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AlertDialog.Builder a = new AlertDialog.Builder(InternetServices.this);
        dialog = a.create();
        dialog.setTitle("提示");

        a.setPositiveButton("确定",null);
        dialog.setCancelable(false);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);//需要添加的语句
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
                        //TODO show dialog 
//                        dialog.setMessage("网络已连接");
//                        dialog.show();
                        Toast.makeText(InternetServices.this,"网络已连接",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
//                        dialog.setMessage("网络已断开");
//                        dialog.show();
                        Toast.makeText(InternetServices.this,"网络已断开",Toast.LENGTH_LONG).show();

                    }
                }
                flag = now;
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
        if(networkInfo!=null) {
            return true;
        }
        return false;
    }
}
