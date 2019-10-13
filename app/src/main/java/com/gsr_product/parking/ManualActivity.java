package com.gsr_product.parking;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


public class ManualActivity extends AppCompatActivity {

    private static final String TAG = "ManualActivity";

    private Context mContext;
    EditText mVehicalNumber, mParkTime;
    Button btn_capture, btn_submit;
    TextView tvTime;
    Date currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        Log.d(TAG, "onCreate: started...");

        mContext = ManualActivity.this;
        mVehicalNumber = findViewById(R.id.et_veh_number);
        btn_capture = findViewById(R.id.btn_capture);
        btn_submit = findViewById(R.id.btn_submit);
        tvTime = findViewById(R.id.tv_time);


        currentTime = Calendar.getInstance().getTime();
        tvTime.setText(currentTime.toString());

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String sVehicalNumber = mVehicalNumber.getText().toString();
                String sParkTime = currentTime.toString();

                Intent intent = new Intent(mContext, QrActivity.class);
                intent.putExtra("vehical no", sVehicalNumber);
                intent.putExtra("ptime", sParkTime);

                startActivity(intent);*/

                Intent intent = new Intent(mContext, Googlevision.class);
                startActivity(intent);
            }
        });


    }
}
