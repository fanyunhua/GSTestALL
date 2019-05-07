package cn.pfc.pfc523.activitys.fyh.EnvironmentIndex;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import cn.pfc.pfc523.R;
/**
 * @author fyh 2019.5.7 10:27
 * 阈值设置
 * */
public class SettingYuZhiActivity extends AppCompatActivity {
    private ImageView imgBackYuzhi;
    private Switch mENVSwitch;
    private EditText mENET1;
    private EditText mENET2;
    private EditText mENET3;
    private EditText mENET4;
    private EditText mENET5;
    private EditText mENET6;
    private Button mENVsave;
    int etID[] = new int[]{
            R.id.mENET1,
            R.id.mENET2,
            R.id.mENET3,
            R.id.mENET4,
            R.id.mENET5,
            R.id.mENET6
    };
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    boolean states;
    EditText editTexts[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_yu_zhi);
        sp = getSharedPreferences("env",MODE_PRIVATE);
        editor = sp.edit();
        states = sp.getBoolean("state",true);
        initView();
    }

    private void initView() {
        imgBackYuzhi = (ImageView) findViewById(R.id.img_back_yuzhi);
        mENVSwitch = (Switch) findViewById(R.id.mENVSwitch);
        mENVsave = (Button) findViewById(R.id.mENVsave);
        editTexts = new EditText[etID.length];
        for (int i = 0; i <etID.length ; i++) {
            editTexts[i] = findViewById(etID[i]);
        }
        mENVSwitch.setChecked(sp.getBoolean("state",true));
        updateState(sp.getBoolean("state",true));
        mENVSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(SettingYuZhiActivity.this,isChecked+"",Toast.LENGTH_SHORT).show();

                updateState(isChecked);
                editor.putBoolean("state",isChecked);
                editor.commit();
            }
        });
        imgBackYuzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mENVsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    boolean save = false;
                    for (int i = 0; i <etID.length ; i++) {
                        if(editTexts[i].getText().toString().length()==0)
                        {
                            Toast.makeText(SettingYuZhiActivity.this,"请完善数据",Toast.LENGTH_SHORT).show();
                            break;
                        }
                        else
                        {
                            int kk = Integer.parseInt(editTexts[i].getText().toString());
                            editor.putInt(""+i,kk);
                            save = true;
                        }

                    }
                    if(save)
                    {
                        editor.commit();
                        Toast.makeText(SettingYuZhiActivity.this,"设置成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }

            }
        });

    }
    void updateState(boolean state)
    {
        if(state)//状态为开的时候不可输入 不可保存
        {
            for (int i = 0; i <etID.length ; i++) {
                editTexts[i].setEnabled(false);
                editTexts[i].setBackgroundColor(Color.DKGRAY);
            }
            mENVsave.setEnabled(false);
        }
        else //状态为关的时候可输入可保存
        {
            for (int i = 0; i <etID.length ; i++) {
                editTexts[i].setEnabled(true);
                editTexts[i].setBackgroundColor(Color.WHITE);
            }
            mENVsave.setEnabled(true);
        }
    }
}
