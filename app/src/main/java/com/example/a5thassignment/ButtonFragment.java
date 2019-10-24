package com.example.a5thassignment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class ButtonFragment extends Fragment {


    public ButtonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Buttons
        Button button = view.findViewById(R.id.button);
        Button button2 = view.findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //test that internet connection works
                if (((MainActivity)getActivity()).isNetworkAvailable() == false){
                    Toast.makeText(getActivity().getApplicationContext(), "Internet connection not available", Toast.LENGTH_SHORT);
                }
                else {
                    // Fetch data and put it into ListView.
                    ((MainActivity)getActivity()).fetchData();
                }
            }
        });
    }
}
