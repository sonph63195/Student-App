package com.duatson.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.duatson.studentapp.fragment.DashboardFragment;
import com.duatson.studentapp.model.Service;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class RegisterServiceActivity extends AppCompatActivity {

    private Service service;

    private StateProgressBar stateProgressBar;

    private ViewGroup registerStep;

    private int currentNumber = 1;
    String[] descriptionData = {"ĐĂNG KÝ", "XÁC NHẬN"};

    private static final int PICK_FILE_RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_service);

        registerStep = findViewById(R.id.registerStep);

        // Show step 2 layout first
        getServiceFromIntent();
        setRegisterLayout(R.layout.layout_register_step_2);

        stateProgressBar = findViewById(R.id.progressStep);
        stateProgressBar.setStateDescriptionData(descriptionData);
//        Button btnNext1 = findViewById(R.id.btnNext1);
        Button btnNext2 = findViewById(R.id.btnNext2);

        // Add back click listener
        Toolbar toolbar = findViewById(R.id.tbrRegister);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Click to next to step 2
        btnNext2.setOnClickListener(clickToNextStep);
        if (currentNumber == 1) {
            stepRegister();
        }
    }

    private void getServiceFromIntent() {
        Intent intent = getIntent();
        service = (Service) intent.getSerializableExtra(DashboardFragment.MY_SERVICE_KEY);
    }

    private View.OnClickListener clickToConfirmRegister = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener clickToNextStep = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (currentNumber == 1) {
                showLayoutStep(R.layout.layout_register_step_3, StateProgressBar.StateNumber.TWO, 2, R.id.btnConfirm, clickToConfirmRegister);
            } else if (currentNumber == 2) {
                showLayoutStep(R.layout.layout_register_step_2, StateProgressBar.StateNumber.ONE, 1, R.id.btnNext2, clickToNextStep);

            }
        }
    };

    private void showLayoutStep(int layout, StateProgressBar.StateNumber state, int currentNumber, int btnId, View.OnClickListener action) {
        setRegisterLayout(layout);
        stateProgressBar.setCurrentStateNumber(state);
        Button btn = findViewById(btnId);
        btn.setOnClickListener(action);
        this.currentNumber = currentNumber;
        if (currentNumber == 2) {
            stepConfirm();
        }
        if (currentNumber == 1) {
            stepRegister();
        }
    }

    private void setRegisterLayout(int layoutId) {
        registerStep.removeAllViews();
        View view = getLayoutInflater().inflate(layoutId, registerStep, false);
        registerStep.addView(view);
    }

    private void stepConfirm() {
        Button btnPrev = findViewById(R.id.btnPrev);
        btnPrev.setOnClickListener(clickToNextStep);
    }

    private void stepRegister() {
        // cho nay xu ly cho register step
    }
}
