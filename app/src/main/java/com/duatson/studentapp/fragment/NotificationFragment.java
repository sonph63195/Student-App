package com.duatson.studentapp.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import static com.duatson.studentapp.R.id;
import static com.duatson.studentapp.R.layout;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {
    private Toolbar toolbar;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(layout.fragment_notification, container, false);
        setUpTopToolBar(view);
        // Inflate the layout for this fragment
        return view;
    }

    private void setUpTopToolBar(View view) {
        toolbar = view.findViewById(id.tbrNotification);
        toolbar.setNavigationOnClickListener(new Toolbar.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

}
