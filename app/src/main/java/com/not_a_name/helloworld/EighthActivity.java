package com.not_a_name.helloworld;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class EighthActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private TextView tvContent;
    private File sdRoot,appRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE},9487);
        }
        else
        {   init();        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==9487)
        {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                init();
            }
        }
        else
        {
            finish();
        }
    }

    private void init()
    {
        tvContent = findViewById(R.id.tvContent);
        preferences = getSharedPreferences("not_a_name",MODE_PRIVATE);
        editor = preferences.edit();
        sdRoot = Environment.getExternalStorageDirectory();
        appRoot = new File(sdRoot,"Android/data/"+getPackageName());

        if(!appRoot.exists())
        {
            appRoot.mkdirs();
        }

        Log.v("not_a_name",sdRoot.getAbsolutePath());
    }

    public void test1(View view) {
        editor.putString("userName","not_a_name");
        editor.putBoolean("sound",false);
        editor.putInt("stage",4);
        editor.commit();

        Toast.makeText(this,"save_ok",Toast.LENGTH_SHORT).show();
    }

    public void test2(View view) {
        boolean isSound = preferences.getBoolean("sound",false);
        int stage = preferences.getInt("stage",1);
        String userName = preferences.getString("useName","NoBody");
        Log.v("not_a_name",userName+":" +stage+":" +isSound);
    }

    public void test3(View view)  {
        try
        {
        FileOutputStream fout = openFileOutput("not_a_name.txt",MODE_APPEND);
        fout.write("Hello, World\n".getBytes());
        fout.flush();
        fout.close();
        Toast.makeText(this,"Save_OK",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {e.printStackTrace();}
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void test4(View view) {
        try (FileInputStream fin = openFileInput("not_a_name.txt"))
        {
            StringBuffer sb = new StringBuffer();
            byte[] buf = new byte[1024];
            int len;
            while ((len=fin.read(buf))!= -1)
            {
                sb.append(new String(buf,0,len));
            }
            tvContent.setText(sb.toString());
        }
        catch (Exception e){}



    }

    public void test5(View view) {
        File file = new File(sdRoot,"not_a_name.txt");

        try {
            FileOutputStream fout = new FileOutputStream(file);
            fout.write("Hello, World, sdRoot".getBytes());
            fout.flush();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void test6(View view) {
        File file = new File(appRoot,"not_a_name.txt");

        try {
            FileOutputStream fout = new FileOutputStream(file);
            fout.write("Hello, World, appRoot".getBytes());
            fout.flush();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
