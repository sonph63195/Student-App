package com.duatson.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.duatson.studentapp.adapter.CategoryGridAdapter;
import com.duatson.studentapp.application.ExpandableHeightGridView;
import com.duatson.studentapp.fragment.ServiceDetailFragment;
import com.duatson.studentapp.fragment.ServicesListFragment;
import com.duatson.studentapp.model.Service;
import com.duatson.studentapp.network.FirebaseDb;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ServiceListActivity extends AppCompatActivity implements NavigationHost {

    private DatabaseReference firebaseDb;
    private Toolbar toolbar;

    private ExpandableHeightGridView gvCatDocs;
    private ExpandableHeightGridView gvCatLearn;
    private ExpandableHeightGridView gvCatOthers;
    private ExpandableHeightGridView gvCatAllowance;

    private List<Service> servicesCatDocs;
    private List<Service> servicesCatLearn;
    private List<Service> servicesCatOthers;
    private List<Service> servicesCatAllowance;

    private static final String FB_DOCS_PATH = "Services/docs";
    private static final String FB_LEARNS_PATH = "Services/learns";
    private static final String FB_OTHERS_PATH = "Services/others";
    private static final String FB_ALLOWANCE_PATH = "Services/allowance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
        toolbar = findViewById(R.id.app_top_bar);
        toolbar.setNavigationOnClickListener(new Toolbar.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack();
            }
        });
        gvCatDocs = findViewById(R.id.gvCatDocs);
        gvCatLearn = findViewById(R.id.gvCatLearn);
        gvCatAllowance = findViewById(R.id.gvCatAllowance);
        gvCatOthers = findViewById(R.id.gvCatOthers);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initServiceData();
    }

    private void initServiceData() {
        servicesCatDocs = dataSnapshot(FB_DOCS_PATH, gvCatDocs);
        servicesCatLearn = dataSnapshot(FB_LEARNS_PATH, gvCatLearn);
        servicesCatOthers = dataSnapshot(FB_OTHERS_PATH, gvCatOthers);
        servicesCatAllowance = dataSnapshot(FB_ALLOWANCE_PATH, gvCatAllowance);

        gvCatDocs.setOnItemClickListener(gridItemClick(servicesCatDocs));
        gvCatLearn.setOnItemClickListener(gridItemClick(servicesCatLearn));
        gvCatOthers.setOnItemClickListener(gridItemClick(servicesCatOthers));
        gvCatAllowance.setOnItemClickListener(gridItemClick(servicesCatAllowance));
    }

    private GridView.OnItemClickListener gridItemClick(final List<Service> services) {
        return new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Service service = services.get(position);
//                if (service != null) {
//                    Bundle args = new Bundle();
//                    args.putSerializable(ServicesListFragment.BUNDLE_KEY, service);
//
//                    ServiceDetailFragment serviceDetailFragment = new ServiceDetailFragment();
//                    serviceDetailFragment.setArguments(args);
//                    navigateTo(serviceDetailFragment, true);
//                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("service", service);

                Intent intent = new Intent(getApplicationContext(), ServiceDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };
    }

    private List<Service> dataSnapshot(String firebasePath, final ExpandableHeightGridView gridView) {
        final List<Service> services = new ArrayList<>();
        firebaseDb = FirebaseDb.makeDbRef(firebasePath);

        firebaseDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                services.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Service service = snapshot.getValue(Service.class);
                    services.add(service);
                }

                CategoryGridAdapter gridAdapter = new CategoryGridAdapter(ServiceListActivity.this, services);
                gridView.setAdapter(gridAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return services;
    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}
