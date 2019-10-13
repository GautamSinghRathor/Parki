package com.gsr_product.parking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class Googlevision extends AppCompatActivity {

    static ImageView sourceImage;
    TextView sourceText;
    Button btnGetTextFromImage, btnSendToNext;
    private String plateNumber = "";

    static ImageView source = null;
    private static final String TAG = "Googlevision";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_googlevision);
        sourceImage = findViewById(R.id.img_source);
        sourceText = findViewById(R.id.tv_image_source);
        btnGetTextFromImage = findViewById(R.id.btn_get_text);
        btnSendToNext = findViewById(R.id.btn_next);


        if(getIntent().hasExtra("byteArray")) {
            ImageView previewThumbnail = new ImageView(this);
            Bitmap b = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
            previewThumbnail.setImageBitmap(b);
            //sourceImage.setImageBitmap(b);
        }




        btnGetTextFromImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTextFromImage();
            }
        });

        btnSendToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send the data to QrActivity
                Intent intent = new Intent(Googlevision.this, QrActivity.class);
                intent.putExtra("numberPlate" , plateNumber);
                startActivity(intent);
            }
        });


    }


    private void getTextFromImage(){


        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.numberplate);

        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        if(!textRecognizer.isOperational()){
            Log.d(TAG, "Detector dependencies are not yet available.");
        }else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();

            SparseArray<TextBlock> item = textRecognizer.detect(frame);

            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < item.size(); ++i){

                TextBlock myItem = item.valueAt(i);
                sb.append(myItem.getValue());
                sb.append("\n");
            }

            sourceText.setText(sb.toString());


            plateNumber = sb.toString();

            Log.d(TAG, "getTextFromImage: "+ plateNumber);
        }
    }
}
