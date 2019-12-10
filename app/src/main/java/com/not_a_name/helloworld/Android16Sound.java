package com.not_a_name.helloworld;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

public class Android16Sound extends AppCompatActivity {

    private AudioManager audioManager;
    private SoundPool soundPool;
    private int s1,s2;
    private File musicDir;
    private MediaRecorder mediaRecorder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android16_sound);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        else {
            init();
        }





    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length>1 && grantResults[0]== PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED){
            init();
        }
    }

    private void init(){

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        soundPool = new SoundPool(10,AudioManager.STREAM_MUSIC,5);
        s1 = soundPool.load(this,R.raw.s1,1);
        s2 = soundPool.load(this,R.raw.s2,2);

        musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);


    }

    public void sound1(View view) {

        audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_INVALID);


    }
    public void sound2(View view) {

        soundPool.play(s1,0.5f,0.5f,1,0,1);

    }
    public void sound3(View view) {

        soundPool.play(s2,0.5f,0.5f,1,10,1);

    }
    public void sound4(View view) {

        //模擬器沒有預設錄音程式
//        Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(musicDir,"record1.amr")));
//        startActivityForResult(intent,123);

        try{
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

        //mediaRecorder.setOutputFile(new File(musicDir,"record.3gp"));  //api 26
        mediaRecorder.setOutputFile(new File(musicDir,"sound1.3gp").getAbsolutePath());
        mediaRecorder.prepare();
        mediaRecorder.start();
        }
        catch (Exception e){e.printStackTrace();}


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("aaa","OK");

    }

    public void sound5(View view) {
        if(mediaRecorder != null){
            mediaRecorder.stop();
            mediaRecorder.reset();
            mediaRecorder.release();
        }
    }
}
