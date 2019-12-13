package com.duatson.studentapp.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.duatson.studentapp.NavigationHost;
import com.duatson.studentapp.R;
import com.duatson.studentapp.ServiceDetailActivity;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesListFragment extends Fragment {

    private DatabaseReference firebaseDb;
    private Toolbar toolbar;

    // private ListView lvServicesList;
    private ExpandableHeightGridView gvCatDocs;
    private ExpandableHeightGridView gvCatLearn;
    private ExpandableHeightGridView gvCatOthers;
    private ExpandableHeightGridView gvCatAllowance;

    private List<Service> servicesCatDocs;
    private List<Service> servicesCatLearn;
    private List<Service> servicesCatOthers;
    private List<Service> servicesCatAllowance;

    public static final String BUNDLE_KEY = "service";

    public ServicesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_services_list, container, false);
        View view = inflater.inflate(R.layout.fragment_services_list, container, false);

        setUpTopToolBar(view);

        gvCatDocs = view.findViewById(R.id.gvCatDocs);
        gvCatLearn = view.findViewById(R.id.gvCatLearn);
        gvCatAllowance = view.findViewById(R.id.gvCatAllowance);
        gvCatOthers = view.findViewById(R.id.gvCatOthers);

        return view;
    }

//    private GridView.OnItemClickListener serviceItemCLickDocs = new GridView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Service service = servicesCatDocs.get(position);
//
//            Bundle bundle = new Bundle();
//            bundle.putSerializable(ServicesListFragment.BUNDLE_KEY, service);
//
//            ServiceDetailFragment serviceDetailFragment = new ServiceDetailFragment();
//            serviceDetailFragment.setArguments(bundle);
//
//            // move to service_detail_fragment
//            ((NavigationHost) getActivity()).navigateTo(serviceDetailFragment, true);
//        }
//    };

    private GridView.OnItemClickListener gridItemClick(final List<Service> services) {
        return new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Service service = services.get(position);
//                if (service != null) {
//                    Bundle args = new Bundle();
//                    args.putSerializable(ServicesListFragment.BUNDLE_KEY, service);
//
//                    ServiceDetailFragment fragment = new ServiceDetailFragment();
//                    fragment.setArguments(args);
//                    // move to service_detail_fragment
//                    ((NavigationHost) getActivity()).navigateTo(fragment, true);
//                }
//                System.out.println(services.get(position));
                Bundle bundle = new Bundle();
                bundle.putSerializable("service", service);

                Intent intent = new Intent(getContext(), ServiceDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();

        initServiceData();
    }

    private void initServiceData() {
//        lvServicesList = view.findViewById(R.id.lvServicesList);
        servicesCatDocs = dataSnapshot("Services/docs", gvCatDocs);
        servicesCatLearn = dataSnapshot("Services/learns", gvCatLearn);
        servicesCatOthers = dataSnapshot("Services/others", gvCatOthers);
        servicesCatAllowance = dataSnapshot("Services/allowance", gvCatAllowance);

        // Add item click listener
        gvCatDocs.setOnItemClickListener(gridItemClick(servicesCatDocs));
        gvCatLearn.setOnItemClickListener(gridItemClick(servicesCatLearn));
        gvCatOthers.setOnItemClickListener(gridItemClick(servicesCatOthers));
        gvCatAllowance.setOnItemClickListener(gridItemClick(servicesCatAllowance));
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

                CategoryGridAdapter gridAdapter = new CategoryGridAdapter(getActivity(), services);
                gridView.setAdapter(gridAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return services;
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
