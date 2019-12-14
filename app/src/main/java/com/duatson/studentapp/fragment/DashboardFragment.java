package com.duatson.studentapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.duatson.studentapp.R;
import com.duatson.studentapp.ServiceDetailActivity;
import com.duatson.studentapp.ServiceListActivity;
import com.duatson.studentapp.adapter.CategoryGridAdapter;
import com.duatson.studentapp.application.ExpandableHeightGridView;
import com.duatson.studentapp.model.Service;
import com.duatson.studentapp.network.FirebaseDb;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {
    private DatabaseReference firebaseDb;
    private ExpandableHeightGridView gvServices;
    private TextView tvSearch;
    private List<Service> servicesCatDocs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        setHasOptionsMenu(true);

        tvSearch = view.findViewById(R.id.tvSearch);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ServiceListActivity.class);
                startActivity(intent);
            }
        });

        // Connect to firebase
        firebaseDb = FirebaseDb.makeDbRef("Services/trending");

        gvServices = view.findViewById(R.id.gvServices);
        gvServices.setOnItemClickListener(serviceItemCLickDocs);

        return view;
    }

    private GridView.OnItemClickListener serviceItemCLickDocs = new GridView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Service service = servicesCatDocs.get(position);

            Bundle bundle = new Bundle();
            bundle.putSerializable("service", service);

            Intent intent = new Intent(getActivity(), ServiceDetailActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    @Override
    public void onStart() {
        super.onStart();

        initServiceData();
    }

    private void initServiceData() {
        servicesCatDocs = new ArrayList<>();

        firebaseDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                servicesCatDocs.clear(); // clear all data

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Service service = snapshot.getValue(Service.class);
                    // Add to list
                    servicesCatDocs.add(service);
                }

                CategoryGridAdapter categoryDocsGridAdapter = new CategoryGridAdapter(getActivity(), servicesCatDocs);
                gvServices.setAdapter(categoryDocsGridAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private AdapterView.OnItemClickListener requestItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        }
    };

}
