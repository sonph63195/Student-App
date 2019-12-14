package com.duatson.studentapp.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.duatson.studentapp.MainActivity;
import com.duatson.studentapp.NavigationHost;
import com.duatson.studentapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Objects;

public class BottomNavigation extends Fragment {

    private BottomNavigationView navigationView;
    private NavigationHost navigationHost;

    private Fragment dashboardFragment = new DashboardFragment();
    private Fragment historyFragment = new HistoryFragment();
    private Fragment profileFagment = new ProfileFragment();

    private Fragment active = dashboardFragment;

    private Fragment lastActive = active;

    public BottomNavigation() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.bottom_navigation, container, false);

        navigationView = view.findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigationHost = (NavigationHost) getActivity();


        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.icDashboard:
                    active = dashboardFragment;
                    replaceFragment();
                    return true;
                case R.id.icHistory:
                    active = historyFragment;
                    replaceFragment();
                    return true;
                case R.id.icProfile:
                    active = profileFagment;
                    replaceFragment();
                    return true;
            }

            return false;
        }
    };

    private void replaceFragment() {
        boolean lastOpen = lastActive.equals(active);
        //System.out.println("Last Open: " + lastOpen);
        navigationHost.navigateTo(active, !lastOpen);
        lastActive = active;

    }
}
