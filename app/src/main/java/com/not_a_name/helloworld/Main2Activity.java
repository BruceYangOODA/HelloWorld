package com.not_a_name.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        TextView tvSixth = findViewById(R.id.tvSixthPage);
        tvSixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),SixthActivity.class);
                startActivity(intent);
            }
        });

        TextView tvEighth = findViewById(R.id.tvEighthPage);
        tvEighth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),EighthActivity.class);
                startActivity(intent);
            }
        });
        TextView tvInternet = findViewById(R.id.tvInternetPage);
        tvInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),NetActivity.class);
                startActivity(intent);
            }
        });
        TextView tvJSON = findViewById(R.id.tvJSONPage);
        tvJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),iiiAndroid10.class);
                startActivity(intent);
            }
        });
        TextView tvFragment = findViewById(R.id.tvFragmentPage);
        tvFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),iiiAndroid11.class);
                startActivity(intent);
            }
        });
        TextView tvViewPager = findViewById(R.id.tvViewPager);
        tvViewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),Android12.class);
                startActivity(intent);
            }
        });
        TextView tvWebView = findViewById(R.id.tvWebView);
        tvWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),Android13.class);
                startActivity(intent);
            }
        });
    }
}
