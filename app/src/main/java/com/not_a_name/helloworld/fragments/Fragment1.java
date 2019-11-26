package com.not_a_name.helloworld.fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.not_a_name.helloworld.R;
import com.not_a_name.helloworld.iiiAndroid11;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {


    private View myView;
    private TextView tvLottery;
    private Button btnLottery;
    private Button btnChangeTitle;
    private Button btnFragmentTitle;
    private iiiAndroid11 myActivity;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(myView==null) {
            myView = inflater.inflate(R.layout.fragment_fragment1, container, false);
            tvLottery = myView.findViewById(R.id.tvLottery);
            btnLottery = myView.findViewById(R.id.btnLottery);
            btnLottery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvLottery.setText("" + (int) (Math.random() * 49 + 1));
                }
            });
            btnChangeTitle = myView.findViewById(R.id.btnChangeTitle);
            btnChangeTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myActivity.setMyTitle("NEW TITLE");
                }
            });

            btnFragmentTitle = myView.findViewById(R.id.btnFragmentTitle);
            btnFragmentTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myActivity.getFragment2().chTitle("F2 NEW TITLE");
                }
            });

        }
        // Inflate the layout for this fragment
        return myView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        myActivity = (iiiAndroid11) context;
    }


}
