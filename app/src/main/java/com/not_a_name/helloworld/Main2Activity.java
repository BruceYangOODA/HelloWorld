package com.not_a_name.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView textView = findViewById(R.id.tvSecondPage);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        TextView tvThird = findViewById(R.id.tvThirdPage);
        tvThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),ThirdActivity.class);
                startActivity(intent);
            }
        });
        TextView tvFourth = findViewById(R.id.tvFourthPage);
        tvFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), FourthActivity.class);
                startActivity(intent);
            }
        });
        TextView tvFifth = findViewById(R.id.tvFifthPage);
        tvFifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), FifthActivity.class);
                startActivity(intent);
            }
        });

    }
}
