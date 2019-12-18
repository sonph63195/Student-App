package com.duatson.studentapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.duatson.studentapp.R;
import com.duatson.studentapp.application.StoreServices;
import com.duatson.studentapp.model.Request;
import com.duatson.studentapp.model.Service;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RequestListAdapter extends ArrayAdapter<Request> {
    private Activity context;
    private List<Request> requests;

    public RequestListAdapter(Activity context, List<Request> requests) {
        super(context, R.layout.item_request_list, requests);
        this.context = context;
        this.requests = requests;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        @SuppressLint("ViewHolder")
        View view = inflater.inflate(R.layout.item_request_list, null, true);
        final Request request = requests.get(position);
        final ImageView requestItemIcon = view.findViewById(R.id.requestItemIcon);

        StoreServices storeServices = new StoreServices();
        Service service = storeServices.setResources(view.getResources()).getServicesMap().get(request.getServiceId());

        if (service != null) {
            Picasso.get().load(service.getIcon()).into(requestItemIcon);
            TextView tvRequestItemTitle = view.findViewById(R.id.tvRequestItemTitle);
            tvRequestItemTitle.setText(service.getName());
        }

        TextView tvRequestItemStatus = view.findViewById(R.id.tvRequestItemStatus);
        tvRequestItemStatus.setText(request.getStatus());

        TextView tvRequestItemTime = view.findViewById(R.id.tvRequestItemTime);
        tvRequestItemTime.setText(request.getTime());

        return view;
    }
}
