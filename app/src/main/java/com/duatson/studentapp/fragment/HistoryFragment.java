package com.duatson.studentapp.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.duatson.studentapp.R;
import com.duatson.studentapp.RequestDetailActivity;
import com.duatson.studentapp.adapter.RequestListAdapter;
import com.duatson.studentapp.application.ExpandableHeightListView;
import com.duatson.studentapp.model.Request;
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
public class HistoryFragment extends Fragment {

    private ExpandableHeightListView lvRequestsList;
    private List<Request> requests;

    private static final String REQUEST_PATH = "requests";

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        lvRequestsList = view.findViewById(R.id.lvRequestsList);
        lvRequestsList.setOnItemClickListener(requestItemClick);
        loadRequests();
        return view;
    }

    private void loadRequests() {
        requests = new ArrayList<>();

        DatabaseReference firebaseDb = FirebaseDb.makeDbRef(REQUEST_PATH);

        firebaseDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // make new list
                requests.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Request request = snapshot.getValue(Request.class);
                    requests.add(request);
                }

                // set into list
                RequestListAdapter adapter = new RequestListAdapter(getActivity(), requests);
                lvRequestsList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("loadReq_onCancelled", "Cancelled Request");
            }
        });
    }

    private AdapterView.OnItemClickListener requestItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Request request = requests.get(position);
            Intent intent = new Intent(getContext(), RequestDetailActivity.class);
            intent.putExtra("REQUEST", request);
            startActivity(intent);
        }
    };
}
