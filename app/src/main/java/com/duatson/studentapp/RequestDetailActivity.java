package com.duatson.studentapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.duatson.studentapp.application.StoreServices;
import com.duatson.studentapp.model.Request;
import com.duatson.studentapp.model.Service;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class RequestDetailActivity extends AppCompatActivity {

    private Toolbar topToolBar;

    private TextView tvRequestStatus;
    private TextView tvRequestTitle;
    private TextView tvRequestFee;
    private TextView tvRequestNote;
    private TextView tvRequestDate;

    private ImageView ivRequestIcon;

    private Request request;

    private Service service;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_request_detail);

        topToolBar = findViewById(R.id.app_top_bar);
        tvRequestStatus = findViewById(R.id.tvRequestStatus);
        tvRequestTitle = findViewById(R.id.tvRequestTitle);
        ivRequestIcon = findViewById(R.id.ivRequestIcon);
        tvRequestFee = findViewById(R.id.tvRequestFee);
        tvRequestNote = findViewById(R.id.tvRequestNote);
        tvRequestDate = findViewById(R.id.tvRequestDate);

        topToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        request = (Request) intent.getSerializableExtra("REQUEST");
        if (request == null) {
            Toast.makeText(this, "Could not find this Request", Toast.LENGTH_SHORT).show();
            fileList();
        } else {
            StoreServices storeServices = new StoreServices();
            service = storeServices.setResources(getResources()).getServicesMap().get(request.getServiceId());
            if (service != null) {
                showRequestToView();
            } else {
                finish();
            }
        }
    }

    private void showRequestToView() {
        tvRequestTitle.setText(service.getName());
        tvRequestStatus.setText(request.getStatus());
        tvRequestNote.setText(request.getNote());
        tvRequestDate.setText(request.getTime());
        Picasso.get().load(service.getIcon()).into(ivRequestIcon);

        NumberFormat formatter = new DecimalFormat("#,###");
        if (service.getFee() > 0) {
            tvRequestFee.setText(formatter.format(service.getFee()));
        } else {
            tvRequestFee.setText(getString(R.string.service_detail_free));
        }
    }


}
