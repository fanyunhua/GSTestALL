package cn.pfc.pfc523.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.pfc.pfc523.R;
import cn.pfc.pfc523.utils.db.DBUtils;

public class MyAccountActivity extends Activity {
    ImageView img;
    TextView tv;
    EditText et;
    Spinner spinner;
    int sort=0;
    RequestQueue queue;
    String a[]={"1","2","3"};
    Button but_cx,but_cz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_myaccount);

        but_cx=findViewById(R.id.but_1_cx);
        but_cz=findViewById(R.id.but_1_cz);
        queue= Volley.newRequestQueue(this);
        img=findViewById(R.id.img_back_1);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv=findViewById(R.id.tv_1_yue);
        et=findViewById(R.id.et_1);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(et.getText().length()>0){
                    if(Integer.parseInt(s.toString())==0){
                        Toast.makeText(MyAccountActivity.this,
                                "只能输入1-999",Toast.LENGTH_SHORT).show();
                        s.delete(0,1);
                    }
                }
            }
        });
        spinner=findViewById(R.id.spinner_1);
        ArrayAdapter arrayAdapter=new ArrayAdapter(MyAccountActivity.this,
                R.layout.support_simple_spinner_dropdown_item,a);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sort=position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        query();
        click();
    }

    private void click() {
        but_cx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });
        but_cz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et.getText().length()>0){
                    cz();
                }else {
                    Toast.makeText(MyAccountActivity.this,
                            "金额不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cz() {
        String url="http://192.168.90.6:8080/transportservice/action/SetCarAccountRecharge.do";
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("UserName","user1");
            jsonObject.put("CarId",sort+1);
            jsonObject.put("Money",Integer.parseInt(et.getText().toString()));
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {
                        String suc=jsonObject.getString("RESULT");
                        if(suc.equals("S")){
                            query();
                            Toast.makeText(MyAccountActivity.this,
                                    "充值成功",Toast.LENGTH_SHORT).show();
                            save();
                            et.setText("");
                        }else {
                            Toast.makeText(MyAccountActivity.this,
                                    "充值失败",Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(MyAccountActivity.this,
                            "网络连接失败，请检查网络设置",Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd HH:mm");
        String time=df.format(new Date());
        String c="insert into table3 values("+(sort+1)+","+Integer.parseInt(et.getText().toString())+",'admin','"+time+"')";
        DBUtils.cun(MyAccountActivity.this).getWritableDatabase().execSQL(c);
    }

    private void query() {
        String url="http://192.168.90.6:8080/transportservice/action/GetCarAccountBalance.do";
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("UserName","user1");
            jsonObject.put("CarId",sort+1);
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {
                        String suc=jsonObject.getString("RESULT");
                        if(suc.equals("S")){
                            int b=jsonObject.getInt("Balance");
                            tv.setText(b+"元");
                        }else {
                            Toast.makeText(MyAccountActivity.this,
                                    "查询失败",Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(MyAccountActivity.this,
                            "网络连接失败，请检查网络设置",Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
