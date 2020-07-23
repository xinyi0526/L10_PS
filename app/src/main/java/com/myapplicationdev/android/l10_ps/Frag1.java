package com.myapplicationdev.android.l10_ps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag1 extends Fragment {

    Button btnChange1;
    LinearLayout layout;

    public Frag1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_1, container, false);

        btnChange1 = v.findViewById(R.id.btnChange1);
        layout = v.findViewById(R.id.changeColor);

        btnChange1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
            }
        });



        return v;
    }
}
