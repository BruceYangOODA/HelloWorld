package com.not_a_name.helloworld;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Android13 extends AppCompatActivity {

    private WebView webView;
    private EditText editText;
    private LocationManager lmgr;
    private MyGpsListener myGpsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android13);
        if(ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);

        }
        else{init();}



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            init();
        }
        else {
            finish();
        }
    }

    private void init(){
        lmgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        webView = findViewById(R.id.webView);
        editText = findViewById(R.id.etvNumber);
        Button btnTest1 = findViewById(R.id.btnTest1);
        btnTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // webView.loadUrl("javascript:test1()");
                webView.loadUrl("javascript:test2("+editText.getText().toString()+")");
            }
        });
        Button btnTest2 = findViewById(R.id.btnTest2);

        initWebView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        myGpsListener = new MyGpsListener();
        lmgr.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,0,0,myGpsListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        lmgr.removeUpdates(myGpsListener);
    }

    private class MyGpsListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            Log.v("aaa",lat + " : " +lng);

            webView.loadUrl("javascript:moveTo("+lat+","+lng+")");

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }



    private void initWebView(){
        WebViewClient webViewClient = new WebViewClient();
        webView.setWebViewClient(webViewClient);

        //開啟JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //webSettings.setUseWideViewPort(true);

        webView.addJavascriptInterface(new MyJS(),"myJS");

       // webView.loadUrl("https://www.iii.org.tw");
        // webView.loadUrl("file:///android_asset/aaa.html");
        webView.loadUrl("file:///android_asset/map.html");

    }

    public class MyJS{
        //需標記是JS 介面
        @JavascriptInterface
        public void callFromJS(String userName){
         //   Log.v("aaa","OK");
            editText.setText(userName);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.v("aaa","keyCode : "+keyCode);

        if(keyCode==4 && webView.canGoBack()){
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.v("aaa","BackPressed");
    }
}
