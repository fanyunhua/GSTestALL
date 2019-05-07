package cn.pfc.pfc523.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpThread extends Thread{
    JSONObject json;
    String URL;
    RequestQueue mQueue;
    OnRequest onRequest;
    public HttpThread(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    public HttpThread setOnRequest(OnRequest onRequest) {
        this.onRequest = onRequest;
        return this;
    }

    @Override
    public void run() {
        Log.d("JSONOBJECT",json.toString());
        Log.d("URL",URL.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                URL,
                json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("sucss",jsonObject.toString());
                        if(onRequest!=null)
                        {
                            try {
                                boolean isOK = jsonObject.getString("RESULT").equals("S");
                                onRequest.onOk(jsonObject,isOK);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("error",volleyError.toString());
                    }
                }
        );
        mQueue.add(jsonObjectRequest);
    }

    public HttpThread setJson(JSONObject json) {
        this.json = json;
        return this;
    }

    public HttpThread setURL(String URL) {
        this.URL = URL;
        return this;
    }
    public interface OnRequest{
        void onOk(JSONObject jsonObject, boolean isOK) throws JSONException;
    }
}
