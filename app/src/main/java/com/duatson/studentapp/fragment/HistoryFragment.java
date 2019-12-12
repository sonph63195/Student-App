package com.duatson.studentapp.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private ExpandableHeightListView lvRequestsList;
    private List<Request> requests;

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

        requests.add(new Request("1", "CB9WNML20", "25/12/2019", "Pending", "Note something", null));
        requests.add(new Request("1", "CB9WNML21", "25/12/2019", "Pending", "Note something", null));
        requests.add(new Request("1", "CB9WNML22", "25/12/2019", "Pending", "Note something", null));
        requests.add(new Request("1", "CB9WNML23", "25/12/2019", "Pending", "Note something", null));
        requests.add(new Request("1", "CB9WNML24", "25/12/2019", "Pending", "Note something", null));
        requests.add(new Request("1", "CB9WNML25", "25/12/2019", "Pending", "Note something", null));
        requests.add(new Request("1", "CB9WNML32", "25/12/2019", "Pending", "Note something", null));
        requests.add(new Request("1", "CB9WNML27", "25/12/2019", "Pending", "Note something", null));
        requests.add(new Request("1", "CB9WNML28", "25/12/2019", "Pending", "Note something", null));
        requests.add(new Request("1", "CB9WNML29", "25/12/2019", "Pending", "Note something", null));

        RequestListAdapter adapter = new RequestListAdapter(getActivity(), requests);
        lvRequestsList.setAdapter(adapter);
    }

    private AdapterView.OnItemClickListener requestItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Request request = requests.get(position);
            Intent intent = new Intent(getContext(), RequestDetailActivity.class);

            startActivity(intent);
        }
    };
}
