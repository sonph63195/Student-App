package com.duatson.studentapp.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.duatson.studentapp.MainActivity;
import com.duatson.studentapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentInfoFragment extends Fragment {
    private Toolbar toolbar;
//    BottomNavigationView navigationView;

    public StudentInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_info, container, false);

//        navigationView = view.getRootView().findViewById(R.id.bottom_navigation);
//        if (navigationView != null) {
//            Toast.makeText(getContext(), "Navigation != null", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getContext(), "Navigation == null", Toast.LENGTH_SHORT).show();
//        }
        setUpTopToolBar(view);
        return view;
    }

    private void setUpTopToolBar(View view) {
        toolbar = view.findViewById(R.id.app_top_bar);
        toolbar.setNavigationOnClickListener(new Toolbar.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}
