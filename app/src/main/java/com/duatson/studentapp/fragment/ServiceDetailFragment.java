package com.duatson.studentapp.fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.duatson.studentapp.MainActivity;
import com.duatson.studentapp.NavigationHost;
import com.duatson.studentapp.R;
import com.duatson.studentapp.RegisterFragment;
import com.duatson.studentapp.RegisterServiceActivity;
import com.duatson.studentapp.adapter.ContactAdapter;
import com.duatson.studentapp.application.ExpandableHeightListView;
import com.duatson.studentapp.model.Contact;
import com.duatson.studentapp.model.Service;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceDetailFragment extends Fragment {


    private ExpandableHeightListView lvContact;
    private MaterialButton btnRegister;
    private List<Contact> contacts = new ArrayList<>();
    private Service service;

    private BottomSheetBehavior bottomSheetBehavior;

    public ServiceDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_service_detail, container, false);
        View view = inflater.inflate(R.layout.fragment_service_detail, container, false);
        lvContact = view.findViewById(R.id.lvContact);

        initServiceData(view);

        btnRegister = view.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(registerCLicked);

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                    if (getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(intent);
                    }

                }
            }
        });

        return view;
    }

    private Service getDataFromClick() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            return (Service) bundle.getSerializable("service");
        }
        return null;
    }

    @SuppressLint("DefaultLocale")
    private void initServiceData(View view) {
        service = getDataFromClick();
        if (service != null) {
            TextView tvServiceTitle = view.findViewById(R.id.tvServiceTitle);
            tvServiceTitle.setText(service.getName());

            TextView tvServiceDescription = view.findViewById(R.id.tvServiceDescription);
            tvServiceDescription.setText(service.getDescription());

            NumberFormat formatter = new DecimalFormat("#,###");
            TextView tvServiceFee = view.findViewById(R.id.tvServiceFee);
            if (service.getFee() > 0) {
                tvServiceFee.setText(formatter.format(service.getFee()));
            } else {
                tvServiceFee.setText(getString(R.string.service_detail_free));
            }

            ImageView ivServiceThumbnail = view.findViewById(R.id.ivServiceThumbnail);
            Picasso.get().load(service.getThumbnail()).into(ivServiceThumbnail);

            setLvContact();
        }
    }

    private void setLvContact() {
        contacts.add(new Contact(service.getEmail(), "Email", R.drawable.ic_email));
        contacts.add(new Contact(service.getPhone(), "Phone", R.drawable.ic_phone));
        ContactAdapter contactAdapter = new ContactAdapter(getActivity(), contacts);
        lvContact.setAdapter(contactAdapter);
    }

    private View.OnClickListener registerCLicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), RegisterServiceActivity.class);
            startActivity(intent);
        }
    };

}
