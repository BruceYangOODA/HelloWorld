package com.not_a_name.helloworld;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ortiz.touchview.TouchImageView;

public class DisplayActivity extends AppCompatActivity {

    private TouchImageView myImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        myImg = findViewById(R.id.imgMyView);
        //Bitmap bmp = (Bitmap) getIntent().getParcelableExtra("img");
        myImg.setImageBitmap(MainApp.bmp);

    }
}
