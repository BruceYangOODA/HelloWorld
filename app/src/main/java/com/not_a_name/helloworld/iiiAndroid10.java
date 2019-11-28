package com.not_a_name.helloworld;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;

public class iiiAndroid10 extends AppCompatActivity {
    private ListView listView;
    private MyAdapter myAdapter;
    //private RequestQueue queue;
    private LinkedList<HashMap<String,String>> data;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iiiandroid10);

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("This is A-Pen");
       // queue = Volley.newRequestQueue(this);
        data = new LinkedList<>();
        listView = findViewById(R.id.lvandroid10);
        initListView();
        fetchRemoteData();
    }

    private void fetchRemoteData(){

        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.GET,
                "http://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelFood.aspx",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("aaa",response);
                        parseJSON(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("aaa",error.toString());
                        progressDialog.dismiss();
                    }
                });
        MainApp.queue.add(request);
    }

    private void parseJSON(String json){
        try{
            JSONArray root = new JSONArray(json);
            for(int i=0;i<root.length();i++)
            {
                HashMap<String,String> dd = new HashMap<>();
                JSONObject row = root.getJSONObject(i);
                dd.put("ID",row.getString("ID"));
                dd.put("Name",row.getString("Name"));
                dd.put("Address",row.getString("Address"));
                dd.put("Tel",row.getString("Tel"));
                dd.put("HostWords",row.getString("HostWords"));
                dd.put("FoodFeature",row.getString("FoodFeature"));
                dd.put("Coordinate",row.getString("Coordinate"));
                dd.put("PicURL",row.getString("PicURL"));
                dd.put("Heart","xx");   //ok,xxs
                data.add(dd);
            }
            myAdapter.notifyDataSetChanged();
        }
        catch (Exception e){e.printStackTrace();}
        progressDialog.dismiss();
    }

    private void initListView(){
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotoDetail(position);
            }
        });
    }

    private void gotoDetail(int index){
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra("data",data.get(index));    //Serializable
        startActivity(intent);
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(iiiAndroid10.this);
            View view = inflater.inflate(R.layout.item,null);
            TextView tvName = view.findViewById(R.id.item_name);
            tvName.setText(data.get(position).get("Name"));
            TextView tvTel = view.findViewById(R.id.item_tel);
            tvTel.setText(data.get(position).get("Tel"));
            TextView tvAdress = view.findViewById(R.id.item_address);
            tvAdress.setText(data.get(position).get("Address"));
            ImageView imgHeart = view.findViewById(R.id.item_heart);
            imgHeart.setImageResource(data.get(position).get("Heart").equals("ok")?R.drawable.heart:R.drawable.heart_no);
            imgHeart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("aaa","pos:"+position);
                    data.get(position).put("Heart",data.get(position).get("Heart").equals("ok")?"xx":"ok");
                    ((ImageView)v).setImageResource(data.get(position).get("Heart").equals("ok")?R.drawable.heart:R.drawable.heart_no);
                }
            });

            return view;
        }
    }

}