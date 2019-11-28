package com.not_a_name.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.not_a_name.helloworld.fragments.Fragment1;
import com.not_a_name.helloworld.fragments.Fragment2;

public class iiiAndroid11 extends AppCompatActivity {

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private FragmentManager fragmentManager;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iii_android11);

        title = findViewById(R.id.tvTitle);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.flContainer,fragment1);
        transaction.commit();

    }

    public void setMyTitle(String newTitle)
    {
        title.setText(newTitle);
    }

    public void toF1(View view) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flContainer,fragment1);
        transaction.commit();
    }
    public void toF2(View view) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flContainer,fragment2);
        transaction.commit();
    }

    public Fragment2 getFragment2()
    {
        return fragment2;
    }

}
