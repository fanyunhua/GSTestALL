package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import cn.pfc.pfc523.R;

public class T49Activity extends Activity {
    WebView webView;
    ImageView img;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_49);

        img=findViewById(R.id.img_back_49);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        webView=findViewById(R.id.web_49);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);  //设置支持js

        progressDialog=new ProgressDialog(T49Activity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("加载中...");


        web();
    }

    private void web() {
        webView.loadUrl("file:///android_asset/t49.html");
//        webView.loadUrl("http://v.qq.com");
        progressDialog.show();
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100){
                    progressDialog.dismiss();
                }else {
                    progressDialog.setProgress(newProgress);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });
    }
}
