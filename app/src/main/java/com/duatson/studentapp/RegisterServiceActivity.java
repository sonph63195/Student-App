package com.duatson.studentapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.duatson.studentapp.fragment.DashboardFragment;
import com.duatson.studentapp.model.Request;
import com.duatson.studentapp.model.Service;
import com.duatson.studentapp.network.FirebaseDb;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterServiceActivity extends AppCompatActivity {

    private Service service;

    private TextInputEditText edRegisterNote;

    private DatabaseReference databaseReference;

    public static final String REQUEST_KEY = "requests";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_service);

        //getting the reference of requests node
        databaseReference = FirebaseDb.makeDbRef("requests");

        edRegisterNote = findViewById(R.id.edRegisterNote);

        // add back button on top bar
        Toolbar topToolBar = findViewById(R.id.tbrRegister);
        topToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        service = (Service) intent.getSerializableExtra(DashboardFragment.MY_SERVICE_KEY);
        if (service == null) {
            Toast.makeText(this, "Could not find this Service", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            TextView tvRegisterTitle = findViewById(R.id.tvRegisterTitle);
            tvRegisterTitle.setText(service.getName());
        }
    }

    public void clickToRegister(View view) {
        String note = edRegisterNote.getText().toString();

        // check
        if (!TextUtils.isEmpty(note)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for Request
            String id = databaseReference.push().getKey();

            if (id != null) {
                // Create new Request
                Request request = new Request(id, service.getId(), getCurrentDate(), "Pending", note, null);

                // Saving the Request
                databaseReference.child(id).setValue(request);

                //displaying a success toast
                Toast.makeText(this, "Create successfull", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, RegisterSuccessActivity.class);
                intent.putExtra(REQUEST_KEY, request);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Please enter something", Toast.LENGTH_SHORT).show();
        }
    }

    private String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();
//        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(c);
    }
}
