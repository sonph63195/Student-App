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
import com.duatson.studentapp.RequestDetail1Activity;
import com.duatson.studentapp.RequestDetail2Activity;
import com.duatson.studentapp.RequestDetail3Activity;
import com.duatson.studentapp.RequestDetail4Activity;
import com.duatson.studentapp.RequestDetail5Activity;
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

        requests.add(new Request("1", "CB9WNML50", "18/12/2019", "Đang xử lý", "Note something", null, 1));
        requests.add(new Request("2", "CB9WNML51", "18/12/2019", "Đang xử lý", "Note something", null, 1));
        requests.add(new Request("3", "CB9WNML52", "17/12/2019", "Đã hoàn thành", "Note something", null, 2));
        requests.add(new Request("4", "CB9WNML54", "16/12/2019", "Đã hủy", "Note something", null, 3));
        requests.add(new Request("5", "CB9WNML53", "16/12/2019", "Đã hủy", "Note something", null, 3));

        RequestListAdapter adapter = new RequestListAdapter(getActivity(), requests);
        lvRequestsList.setAdapter(adapter);
    }

    private AdapterView.OnItemClickListener requestItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Request request = requests.get(position);
            Intent intent = null;
            switch (request.getId()) {
                case "1":
                    intent = new Intent(getContext(), RequestDetail1Activity.class);
                    break;
                case "2":
                    intent = new Intent(getContext(), RequestDetail2Activity.class);
                    break;
                case "3":
                    intent = new Intent(getContext(), RequestDetail3Activity.class);
                    break;
                case "4":
                    intent = new Intent(getContext(), RequestDetail4Activity.class);
                    break;
                case "5":
                    intent = new Intent(getContext(), RequestDetail5Activity.class);
                    break;
            }

            intent.putExtra("REQUEST", request);
            startActivity(intent);
        }
    };
}
