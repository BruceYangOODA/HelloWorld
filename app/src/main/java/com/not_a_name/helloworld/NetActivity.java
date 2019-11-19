package com.not_a_name.helloworld;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetActivity extends AppCompatActivity {

    private RequestQueue queue;
    private ImageView img ;
    private Bitmap bitmap;
    private UIHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        queue = Volley.newRequestQueue(this);
        img = findViewById(R.id.imgNet);
        handler = new UIHandler();
    }

    public void internet(View view) {
        new Thread(){
            @Override
            public void run(){
                test11();
            }
        }.start();
    }

    private void test11(){
        try {
            URL url = new URL("http://140.116.180.113/notname.html");
            //URL url = new URL("http://www.google.com");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            //BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null)
            {
                Log.v("not_a_name",line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void queue(View view) {
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Log.v("not_a_name",response);
                parseJSON(response);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("not_a_name","error:"+error.toString());
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://data.coa.gov.tw/Service/OpenData/RuralTravelData.aspx",listener, errorListener);
        queue.add(stringRequest);
    }

    public void webLoader(View view) {
        new Thread(){
            @Override
            public void run() {
                test3();
            }
        }.start();
    }

    private void test3(){
        try {
            URL url = new URL("https://img.ruten.com.tw/s4/9af/688/a0908811829/a/ba/19/21908911501849_703.jpg");
            //URL url = new URL("http://www.google.com");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.connect();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
           // img.setImageBitmap(bitmap);   //在其他執行序當中,使用handler處理ImageView更換
            handler.sendEmptyMessage(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseJSON(String json)
    {
        try{
            JSONArray root = new JSONArray(json);
            for (int i=0;i<root.length();i++)
            {
                JSONObject row = root.getJSONObject(i);
                String title = row.getString("Title");
                Log.v("not_a_name",title);
            }
        }
        catch (Exception e){e.printStackTrace();}
    }

    private class UIHandler extends Handler
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            img.setImageBitmap(bitmap);
        }
    }

}
