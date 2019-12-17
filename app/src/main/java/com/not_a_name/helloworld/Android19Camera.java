package com.not_a_name.helloworld;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;

public class Android19Camera extends AppCompatActivity {
    ImageView imgTakePicture;
    private File sdRoot;
    private Uri photoUri;
    private SwitchCompat switchLight;
    private CameraManager cameraManager;
    private Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android19_camera);

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    123);
        }else{
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        init();
    }

    private void init(){
        imgTakePicture = findViewById(R.id.imgTackPicture);
        sdRoot = Environment.getExternalStorageDirectory();
        //手機太舊
        // cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        switchLight = findViewById(R.id.switchLight);
        switchLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){  lightOn();}
                else {  lightOff(); }
            }
        });

        // Read from the database
        myBike.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
          //      String temp1 = dataSnapshot.getValue(String.class);
                Bike bike2 = (Bike) dataSnapshot.getValue(Bike.class);  //Using HashMap




                Log.v("aaa",bike.name+":"+bike.speed);



              //  Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
             //   Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


    private void lightOn(){
        try{
            //手機太舊
            // cameraManager.setTorchMode("0",true);
        }
        catch (Exception e){}
    }

    private void lightOff(){
        try {
            //手機太舊
            // cameraManager.setTorchMode("0",false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
//            Set<String> keys = bundle.keySet();
//            for (String key : keys){
//                Object value = bundle.get(key);
//                Log.v("aaa",key+":"+value.getClass().getName());
//             }
            // 回傳 data:Bitmap
            // 回傳 縮圖
            Bitmap bitmap = (Bitmap) bundle.get("data");
            imgTakePicture.setImageBitmap(bitmap);

        }
        else if(requestCode == 2 && resultCode == RESULT_OK){
            //回傳 原圖
//           Bitmap bitmap =  BitmapFactory.decodeFile(sdRoot.getAbsolutePath()+"/takeP.jpg");
//            imgTakePicture.setImageBitmap(bitmap);
            if(photoUri != null)
                imgTakePicture.setImageURI(photoUri);
        }
    }

    public void click1(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,1);
        // 回傳 縮圖
    }

    public void click2(View view){
       photoUri = FileProvider.getUriForFile(this,
                getPackageName()+".provider",new File(sdRoot,"takeP.jpg"));

        //Uri photoUri2 = Uri.fromFile(new File(sdRoot,"takeP2.jpg"));

        //回傳 原圖
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
        startActivityForResult(intent,2);
    }

    public void click3(View view){
        // 版本 ORIO 之前
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            vibrator.vibrate(VibrationEffect.createOneShot(1*1000,
                    VibrationEffect.DEFAULT_AMPLITUDE));
        }
        else {  vibrator.vibrate(1*1000); }

    }

    private   FirebaseDatabase database = FirebaseDatabase.getInstance();
    private  DatabaseReference myRef= database.getReference("message");
    private  DatabaseReference myBike= database.getReference("bike");
    private Bike bike = new Bike();
    public void click4(View view) {
        // Write a message to the database

        myRef.setValue("Hello, World!");
        Bike bike = new Bike();
        bike.setName("aaa");
        bike.upSpeed();
        bike.upSpeed();
        bike.upSpeed();
        myBike.setValue(bike);


    }


}
