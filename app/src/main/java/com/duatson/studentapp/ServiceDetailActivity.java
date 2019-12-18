package com.duatson.studentapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.duatson.studentapp.adapter.ContactAdapter;
import com.duatson.studentapp.application.ExpandableHeightListView;
import com.duatson.studentapp.fragment.DashboardFragment;
import com.duatson.studentapp.fragment.ServicesListFragment;
import com.duatson.studentapp.model.Contact;
import com.duatson.studentapp.model.Service;
import com.duatson.studentapp.services.ServiceConfirmStudentActivity;
import com.duatson.studentapp.services.ServiceHealthInsuranceCardActivity;
import com.duatson.studentapp.services.ServiceLibraryCardActivity;
import com.duatson.studentapp.services.ServiceRegisterDormitoryActivity;
import com.duatson.studentapp.services.ServiceScoreReportActivity;
import com.duatson.studentapp.services.ServiceStudentCardActivity;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ServiceDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ExpandableHeightListView lvContact;
    private List<Contact> contacts;
    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);

        lvContact = findViewById(R.id.lvContact);
        toolbar = findViewById(R.id.tbrServiceDetail);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        initServiceData();

        MaterialButton btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(registerCLicked);

        lvContact.setOnItemClickListener(contactClicked);
    }

    private Service getDataFromClick() {
        Intent intent = this.getIntent();
        return (Service) intent.getSerializableExtra(ServiceListActivity.MY_SERVICE_KEY);
    }

    private void initServiceData() {
        service = getDataFromClick();
        if (service != null) {
            TextView tvServiceTitle = findViewById(R.id.tvServiceTitle);
            tvServiceTitle.setText(service.getName());

            TextView tvServiceDescription = findViewById(R.id.tvServiceDescription);
            tvServiceDescription.setText(Html.fromHtml(service.getDescription()));

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
            contacts.add(new Contact(service.getEmail(), "Email", R.drawable.ic_mail_outline_black_24dp));
        }
        if (service.getPhone() != null) {
            contacts.add(new Contact(service.getPhone(), "Điện thoại", R.drawable.ic_phone));
        }
        if (contacts.size() > 0) {
            ContactAdapter contactAdapter = new ContactAdapter(this, contacts);
            lvContact.setAdapter(contactAdapter);
            lvContact.setOnItemClickListener(contactClicked);
        }
    }

    private View.OnClickListener registerCLicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;

            switch (service.getId()) {
                case "CB9WNML50":
                    intent = new Intent(getApplicationContext(), ServiceConfirmStudentActivity.class);
                    break;
                case "CB9WNML51":
                    intent = new Intent(getApplicationContext(), ServiceStudentCardActivity.class);
                    break;
                case "CB9WNML52":
                    intent = new Intent(getApplicationContext(), ServiceLibraryCardActivity.class);
                    break;
                case "CB9WNML53":
                    intent = new Intent(getApplicationContext(), ServiceRegisterDormitoryActivity.class);
                    break;
                case "CB9WNML54":
                    intent = new Intent(getApplicationContext(), ServiceHealthInsuranceCardActivity.class);
                    break;
                case "CB9WNML55":
                    intent = new Intent(getApplicationContext(), ServiceScoreReportActivity.class);
                    break;
            }
            System.out.println(service.getId());
            intent.putExtra(DashboardFragment.MY_SERVICE_KEY, service);
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
