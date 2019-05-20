package cn.pfc.pfc523.activitys.fyh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.pfc.pfc523.R;

public class ParkingLotActivity extends AppCompatActivity {
    private ImageView mParkingBackImage;
    private ImageView mParkingImage;
    private TextView mParkingTVCarCount;
    private Button mParkingBtnQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_lot);
        initView();
    }

    private void initView() {

        mParkingBackImage = (ImageView) findViewById(R.id.mParkingBackImage);
        mParkingImage = (ImageView) findViewById(R.id.mParkingImage);
        mParkingTVCarCount = (TextView) findViewById(R.id.mParkingTVCarCount);
        mParkingBtnQuery = (Button) findViewById(R.id.mParkingBtnQuery);
    }
}
