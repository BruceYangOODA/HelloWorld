package com.not_a_name.helloworld;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {

    private String id,name,addr,tel,hostWords,foodFeature,coodinate,picURL;
    private ImageView imgContent;
    private TextView tvDetailContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        HashMap<String,String> row = (HashMap<String, String>) intent.getSerializableExtra("data");
       // Log.v("aaa",row.get("Name"));

        id = row.get("ID");
        name = row.get("Name");
        addr = row.get("Address");
        tel = row.get("Tel");
        hostWords = row.get("HostWords");
        foodFeature = row.get("FoodFeature");
        coodinate = row.get("Coordinate");
        picURL = row.get("PicURL");

        imgContent = findViewById(R.id.imgDetail);
        imgContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //GoTo
                Intent intent = new Intent(DetailActivity.this,DisplayActivity.class);
                startActivity(intent);
            }
        });
        tvDetailContent = findViewById(R.id.tvDetailContent);
        fetchRemoteImage();

    }

    private void fetchRemoteImage(){
        ImageRequest request = new ImageRequest(picURL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                MainApp.bmp = response;
                imgContent.setImageBitmap(response);
            }
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MainApp.queue.add(request);
    }


    public void gotoMAP(View view) {
        String[] temp = coodinate.split(",");
        Double lat = Double.valueOf(temp[0]);
        Double lng = Double.valueOf(temp[1]);
        Intent intent = new Intent(this,MapsActivity.class);
        intent.putExtra("lat",lat);
        intent.putExtra("lng",lng);
        startActivity(intent);
    }
}
