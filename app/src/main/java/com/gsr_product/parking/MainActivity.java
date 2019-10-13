package com.gsr_product.parking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Context mContext;

    ImageView captureImg, manualImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started ....");
        mContext = MainActivity.this;

        captureImg = findViewById(R.id.img_capture);
        manualImg = findViewById(R.id.img_manual);

        captureImg.animate().translationY(100).setDuration(1000).setStartDelay(300);
        manualImg.animate().translationY(-100).setDuration(1000).setStartDelay(300);

        captureImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, CaptureCamera.class));
            }
        });

        manualImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, ManualActivity.class));
            }
        });
    }
}
