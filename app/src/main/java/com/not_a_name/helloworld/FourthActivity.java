package com.not_a_name.helloworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;

public class FourthActivity extends AppCompatActivity {

    private ListView lvContent;
    private SimpleAdapter adapter;
    private LinkedList<HashMap<String,String>> data;
    private String[] from = {"title","msg","detail"};
    private int[] to = {R.id.item_title,R.id.item_msg};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        lvContent = findViewById(R.id.lvContent);
        initListView();

        Button btnAddData = findViewById(R.id.btnAddData);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    private void initListView()
    {
        data = new LinkedList<>();
        for(int i=0;i<10;i++)
        {
            HashMap<String,String> dd = new HashMap<>();
            int rand = (int)(Math.random()*49+1);
            dd.put(from[0],"Title "+(i+1));
            dd.put(from[1],"Number "+rand);
            dd.put(from[2],"detail"+i);
            data.add(dd);
        }
        adapter = new SimpleAdapter(this,data,R.layout.item_lvcontent,from,to);
        lvContent.setAdapter(adapter);
        lvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StringBuffer sb = new StringBuffer();
                sb.append("Title" + data.get(position).get(from[0]) +"\n");
                sb.append("Message" + data.get(position).get(from[1]) +"\n");
                sb.append("Detail" + data.get(position).get(from[2]) +"\n");
                displayDetail(sb.toString());
            }
        });
    }

    private void addItem()
    {
        HashMap<String,String> dd = new HashMap<>();
        int rand = (int)(Math.random()*49+1);
        dd.put(from[0],"Title "+rand);
        dd.put(from[1],"Number "+rand);
        dd.put(from[2],"detail"+rand);
        data.add(dd);
        adapter.notifyDataSetChanged();
    }


    private void displayDetail(String msg)
    {
        new AlertDialog.Builder(this)
                .setMessage(msg)
                .create()
                .show();
    }

}
