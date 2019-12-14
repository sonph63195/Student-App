package com.duatson.studentapp.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.duatson.studentapp.NavigationHost;
import com.duatson.studentapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigation extends Fragment {

    private BottomNavigationView bottomNavigationView;
    private NavigationHost navigationHost;

    private Fragment dashboardFragment = new DashboardFragment();
    private Fragment historyFragment = new HistoryFragment();
    private Fragment profileFragment = new ProfileFragment();
    private Fragment notificationFragment = new NotificationFragment();
    private Fragment active = dashboardFragment;
    private Fragment lastActive = active;

    public BottomNavigation() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bottom_navigation, container, false);
        bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigationHost = (NavigationHost) getActivity();
        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.icProfile:
                    active = profileFragment;
                    replaceFragment();
                    return true;
                case R.id.icDashboard:
                    active = dashboardFragment;
                    replaceFragment();
                    return true;
                case R.id.icNotification:
                    active = notificationFragment;
                    replaceFragment();
                    return true;
                case R.id.icHistory:
                    active = historyFragment;
                    replaceFragment();
                    return true;
            }
            return false;
        }
    };

    private void replaceFragment() {
        boolean lastOpen = lastActive.equals(active);
        navigationHost.navigateTo(active, !lastOpen);
        lastActive = active;

    }
}
