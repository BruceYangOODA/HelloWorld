package com.not_a_name.helloworld.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.not_a_name.helloworld.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {
    private View myView;
    private TextView tvTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(myView == null)
        {
            myView =inflater.inflate(R.layout.fragment_fragment2, container, false);
            tvTitle = myView.findViewById(R.id.tvTitle);
        }
        return myView;
    }

    public void chTitle(String newTitle)
    {
        tvTitle.setText(newTitle);
    }

}
