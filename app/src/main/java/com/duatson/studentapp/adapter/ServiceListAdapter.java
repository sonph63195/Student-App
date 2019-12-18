package com.duatson.studentapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.duatson.studentapp.R;
import com.duatson.studentapp.model.Service;

import java.util.List;

public class ServiceListAdapter extends ArrayAdapter<Service> {
    private Activity context;
    private List<Service> services;

    public ServiceListAdapter(Activity context, List<Service> services) {
        super(context, R.layout.item_service_list, services);
        this.context = context;
        this.services = services;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        LayoutInflater inflater = context.getLayoutInflater();

        View viewItem = inflater.inflate(R.layout.item_service_list, null, true);

//        MaterialCardView cvSurface = viewItem.findViewById(R.id.serviceItemSurface);
//        TextView tvServiceItemTitle = viewItem.findViewById(R.id.tvServiceItemTitle);
//        TextView tvServiceItemDescription = viewItem.findViewById(R.id.tvServiceItemDescription);
//
//        Service service = services.get(position);
//        tvServiceItemTitle.setText(service.getName());
//        tvServiceItemDescription.setText(service.getDesctiption());

        return viewItem;
    }
}
