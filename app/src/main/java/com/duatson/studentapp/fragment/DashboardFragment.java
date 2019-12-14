package com.duatson.studentapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.duatson.studentapp.MainActivity;
import com.duatson.studentapp.NavigationHost;
import com.duatson.studentapp.R;
import com.duatson.studentapp.RequestDetailFragment;
import com.duatson.studentapp.ServiceDetailActivity;
import com.duatson.studentapp.ServiceListActivity;
import com.duatson.studentapp.adapter.CategoryGridAdapter;
import com.duatson.studentapp.adapter.RequestListAdapter;
import com.duatson.studentapp.adapter.SliderAdapter;
import com.duatson.studentapp.adapter.ViewPaperAdapter;
import com.duatson.studentapp.application.ExpandableHeightGridView;
import com.duatson.studentapp.application.ExpandableHeightListView;
import com.duatson.studentapp.model.Request;
import com.duatson.studentapp.model.Service;
import com.duatson.studentapp.network.FirebaseDb;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {

    private ExpandableHeightGridView gvCatDocs;
    private ExpandableHeightGridView gvCatLearn;
    private ExpandableHeightGridView gvCatOthers;
    private ExpandableHeightGridView gvCatAllowance;

    public static final String MY_SERVICE_KEY = "service";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        gvCatDocs = view.findViewById(R.id.gvCatDocs);
        gvCatLearn = view.findViewById(R.id.gvCatLearn);
        gvCatAllowance = view.findViewById(R.id.gvCatAllowance);
        gvCatOthers = view.findViewById(R.id.gvCatOthers);

        return view;
    }

    private GridView.OnItemClickListener gridItemClick(final List<Service> services) {
        return new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Service service = services.get(position);
                if (service != null) {
                    Intent intent = new Intent(getActivity(), ServiceDetailActivity.class);
                    intent.putExtra(MY_SERVICE_KEY, service);

                    startActivity(intent);
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();

        initServiceData();
    }

    private void initServiceData() {
        List<Service> servicesCatDocs = dataSnapshot("Services/docs", gvCatDocs);
        List<Service> servicesCatLearn = dataSnapshot("Services/learns", gvCatLearn);
        List<Service> servicesCatOthers = dataSnapshot("Services/others", gvCatOthers);
        List<Service> servicesCatAllowance = dataSnapshot("Services/allowance", gvCatAllowance);

        // Add item click listener
        gvCatDocs.setOnItemClickListener(gridItemClick(servicesCatDocs));
        gvCatLearn.setOnItemClickListener(gridItemClick(servicesCatLearn));
        gvCatOthers.setOnItemClickListener(gridItemClick(servicesCatOthers));
        gvCatAllowance.setOnItemClickListener(gridItemClick(servicesCatAllowance));
    }

    private List<Service> dataSnapshot(String firebasePath, final ExpandableHeightGridView gridView) {
        final List<Service> services = new ArrayList<>();
        DatabaseReference firebaseDb = FirebaseDb.makeDbRef(firebasePath);

        firebaseDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                services.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Service service = snapshot.getValue(Service.class);
                    services.add(service);
                }

                CategoryGridAdapter gridAdapter = new CategoryGridAdapter(getActivity(), services);
                gridView.setAdapter(gridAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return services;
    }

}
