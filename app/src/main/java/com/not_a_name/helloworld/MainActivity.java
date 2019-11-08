package com.not_a_name.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.test_main);


        /*
        tvResult = findViewById(R.id.tvResult);
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
        //tvResult.setText(numbers.toString());
    }

}
