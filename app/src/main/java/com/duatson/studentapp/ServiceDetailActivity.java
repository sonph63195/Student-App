package com.duatson.studentapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.duatson.studentapp.adapter.ContactAdapter;
import com.duatson.studentapp.application.ExpandableHeightListView;
import com.duatson.studentapp.fragment.ServicesListFragment;
import com.duatson.studentapp.model.Contact;
import com.duatson.studentapp.model.Service;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ServiceDetailActivity extends AppCompatActivity {

    private ExpandableHeightListView lvContact;
    private List<Contact> contacts;
    private Service service;

    private Toolbar topToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);

        lvContact = findViewById(R.id.lvContact);
        topToolBar = findViewById(R.id.app_top_bar);

        initServiceData();

        MaterialButton btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(btnRegisterCLicked);


        topToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private Service getServiceDataFromClick() {
        Intent intent = this.getIntent();
        return (Service) intent.getSerializableExtra(ServicesListFragment.MY_SERVICE_KEY);
    }

    private void initServiceData() {
        service = getServiceDataFromClick();
        if (service != null) {
            TextView tvServiceTitle = findViewById(R.id.tvServiceTitle);
            tvServiceTitle.setText(service.getName());

            TextView tvServiceDescription = findViewById(R.id.tvServiceDescription);
            tvServiceDescription.setText(service.getDescription());

            NumberFormat formatter = new DecimalFormat("#,###");
            TextView tvServiceFee = findViewById(R.id.tvServiceFee);
            if (service.getFee() > 0) {
                tvServiceFee.setText(formatter.format(service.getFee()));
            } else {
                tvServiceFee.setText(getString(R.string.service_detail_free));
            }

            ImageView ivServiceThumbnail = findViewById(R.id.ivServiceThumbnail);
            Picasso.get().load(service.getThumbnail()).into(ivServiceThumbnail);

            setLvContact();
        }
    }

    private void setLvContact() {
        contacts = new ArrayList<>();
        if (service.getEmail() != null) {
            contacts.add(new Contact(service.getEmail(), "Email", R.drawable.ic_email));
        }

        if (service.getPhone() != null) {
            contacts.add(new Contact(service.getPhone(), "Phone", R.drawable.ic_phone));
        }

        if (contacts.size() > 0) {
            ContactAdapter contactAdapter = new ContactAdapter(this, contacts);
            lvContact.setAdapter(contactAdapter);
            lvContact.setOnItemClickListener(contactClicked);
        }
    }

    private View.OnClickListener btnRegisterCLicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), RegisterServiceActivity.class);
            startActivity(intent);
        }
    };

    private AdapterView.OnItemClickListener contactClicked = new AdapterView.OnItemClickListener() {

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (contacts.get(position).getContent().equals("Email")) {
                /* Create the Intent */
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                /* Fill it with Data */
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{service.getEmail()});

                /* Send it off to the Activity-Chooser */
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            } else {
                String posted_by = service.getPhone();
                String uri = "tel:" + posted_by.trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                }

            }
        }
    };


}
