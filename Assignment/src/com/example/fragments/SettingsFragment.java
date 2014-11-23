package com.example.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment.MainActivity;
import com.example.assignment.R;

public class SettingsFragment extends Fragment {
   
  

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
        TextView textView =(TextView)rootView.findViewById(R.id.textView1);
        textView.setText("Settings Fragment");
        ((MainActivity) getActivity()).lock();
        getActivity().setTitle("Settings");
        return rootView;
    }
}
