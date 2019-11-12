package com.not_a_name.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class FifthActivity extends AppCompatActivity {
    private TextView tvClock;
    private ListView lvLap;
    private Button btnLeft,btnRight;
    private boolean isRunning;
    private Timer timer;
    private int timeCount;
    private UIHandler uiHandler;
    private SimpleAdapter adapter;
    private LinkedList<HashMap<String,String>> data;
    private String[] from = {"timerItem"};
    protected int[] to = {R.id.tvItemTimer};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        tvClock= findViewById(R.id.tvClock);
        lvLap = findViewById(R.id.lvLap);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);
        btnLeft.setText(isRunning?"Lap":"Reset");
        btnRight.setText(isRunning?"Stop":"Start");
        timer = new Timer();
        uiHandler = new UIHandler();
        timer.schedule(new MyTask(),0,10);

        initListView();

    }

    private void initListView()
    {
        data = new LinkedList<>();
        adapter = new SimpleAdapter(this,data,R.layout.item_lv_timer,from,to);
        lvLap.setAdapter(adapter);
    }

    @Override
    public void finish() {
        if(timer!=null)
        {
            timer.cancel();
            timer.purge();
            timer = null;
        }
        super.finish();
    }

    private class UIHandler extends Handler
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            tvClock.setText(toClockString());
        }
    }

    private String toClockString()
    {
        //int hs = timeCount%100;
        //int ts = timeCount/100;
        //int hh = ts/(60*60);
        //int mm = (ts- ts/(60*60))/60;

        int hour,minute,second,milesecond;
       // hour=minute=second=0;
        milesecond = timeCount%100;
        hour = timeCount/(100*60*60);
        minute = timeCount/(100*60);
        second = timeCount/100;
        if(minute>60)
        {
            int temp = minute;
            minute = temp%60;
        }
        if(second>60)
        {
            int temp = second;
            second = temp%60;
        }

        return hour+":"+minute+":"+second+":"+milesecond;
    }

    private class  MyTask extends TimerTask
    {
        @Override
        public void run() {
            if(isRunning)
            {
                timeCount++;
                uiHandler.sendEmptyMessage(0);
                // tvClock.setText(""+timeCount);
            }
        }
    }

    public void clickLeft(View view) {
        if(isRunning)
        {
           doLap();
        }
        else
        {
            doReset();
        }
    }

    private void doLap()
    {
        HashMap<String,String> itemData = new HashMap<>();
        itemData.put(from[0],tvClock.getText().toString());
        //data.add(itemData);
        data.addFirst(itemData);
        adapter.notifyDataSetChanged();
    }
    private void doReset()
    {
        timeCount=0;
        uiHandler.sendEmptyMessage(0);
        data.clear();
        adapter.notifyDataSetChanged();
    }
    public void clickRight(View view) {
        isRunning = !isRunning;
        btnLeft.setText(isRunning?"Lap":"Reset");
        btnRight.setText(isRunning?"Stop":"Start");
    }
}
