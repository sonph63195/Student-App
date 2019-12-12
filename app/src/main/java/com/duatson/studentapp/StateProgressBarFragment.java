package com.duatson.studentapp;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kofigyan.stateprogressbar.StateProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class StateProgressBarFragment extends Fragment {

    public StateProgressBarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_state_progress_bar, container, false);
        return view;
    }

}
