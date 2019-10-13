package com.gsr_product.parking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import java.util.Calendar;
import java.util.Date;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QrActivity extends AppCompatActivity {

    private static final String TAG = "QrActivity";
    
    EditText input;
    ImageView imageView;
    Button generate, save, webview;
    Bitmap bitmap;
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);


        Log.d(TAG, "onCreate: started....");

        //get data from parking employee
        Intent intent = getIntent();

        String sVehicalNumber = intent.getStringExtra("numberPlate");

        Log.d(TAG, "onCreate: sVehicalNumber : " + sVehicalNumber);

        //String sParkTime = intent.getStringExtra("ptime");

        Date currentTime = Calendar.getInstance().getTime();
        String time = currentTime.toString();

        final String finalData = "v.no: " + sVehicalNumber + "\n" + "time : " + time ;

        //input = findViewById(R.id.edit_text);

        imageView = findViewById(R.id.img_capture);
        //generate = findViewById(R.id.generate);
        //save = findViewById(R.id.save);
        //webview = findViewById(R.id.webView);



                if (!finalData.isEmpty()) {
                    QRGEncoder qrgEncoder = new QRGEncoder(finalData, null, QRGContents.Type.TEXT, 500);
                    try {
                        // Getting QR-Code as Bitmap
                        bitmap = qrgEncoder.encodeAsBitmap();
                        // Setting Bitmap to ImageView
                        imageView.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        //Log.v(TAG, e.toString());
                    }
                }


        /*save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean save;
                String result;
                try {
                    save = QRGSaver.save(savePath, input.getText().toString().trim(), bitmap, QRGContents.ImageType.IMAGE_JPEG);
                    result = save ? "Image Saved" : "Image Not Saved";
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/

    }
}
