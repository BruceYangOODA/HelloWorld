package com.not_a_name.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);
        setContentView(R.layout.test_main);
        Log.v("Not_A_Name","onCreate");
        LinearLayout llTestMain = findViewById(R.id.llTestMain);
        llTestMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),Main2Activity.class);
                startActivity(intent);
            }
        });

        tvResult = findViewById(R.id.tvRotter);
        /*
        btnClick = findViewById(R.id.btnClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.v("Not_A_Name","OK");
                showLottery();
            }
        });*/
    }

    private void showLottery(){
       // Random random = new Random();
        TreeSet<Integer> numbers = new TreeSet<>();
        while (numbers.size()<6)
        {
            numbers.add((int) (Math.random()*49+1));
        }
        tvResult.setText(numbers.toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
    //    Log.v("Not_A_Name","onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
     //   Log.v("Not_A_Name","onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
     //   Log.v("Not_A_Name","onResume");
        showLottery();
    }


    @Override
    protected void onPause() {
        super.onPause();
      //  Log.v("Not_A_Name","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
      //  Log.v("Not_A_Name","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
      //  Log.v("Not_A_Name","onDestroy");
    }
}
