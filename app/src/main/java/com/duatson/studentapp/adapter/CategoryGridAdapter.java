package com.duatson.studentapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.duatson.studentapp.R;
import com.duatson.studentapp.application.DrawableUtil;
import com.duatson.studentapp.model.Service;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryGridAdapter extends ArrayAdapter<Service> {

    private final Activity context;
    private final List<Service> services;

    public CategoryGridAdapter(Activity context, List<Service> services) {
        super(context, R.layout.service_list_item, services);
        this.context = context;
        this.services = services;
        System.out.println(services);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Service service = services.get(position);

        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"})
        View view = inflater.inflate(R.layout.service_list_item, null, true);

        if (service != null) {
            // Item title
            TextView serviceItemTitle = view.findViewById(R.id.serviceItemTitle);
            serviceItemTitle.setText(service.getName());
            // Item Icon
            if (service.getIcon() != null) {
                ImageView serviceItemIcon = view.findViewById(R.id.serviceItemIcon);
                Picasso.get().load(service.getIcon()).into(serviceItemIcon);
            }
        }

        return view;
    }
}
