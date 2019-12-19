package com.duatson.studentapp.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.duatson.studentapp.R;
import com.duatson.studentapp.RegisterServiceActivity;
import com.duatson.studentapp.RegisterSuccessActivity;

public class ServiceConfirmStudent2Activity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvResult;
    private Button btnConfirm;
    private TextView tvStdName;
    private TextView tvMSSV;
    private TextView tvMajor;
    private TextView tvPurpose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_confirm_student2);

        toolbar = findViewById(R.id.tbrApp);
        tvResult = findViewById(R.id.txtResult);
        btnConfirm = findViewById(R.id.btnConfirm);
        tvStdName = findViewById(R.id.tvStdName);
        tvMSSV = findViewById(R.id.tvMSSV);
        tvMajor = findViewById(R.id.tvMajor);
        tvPurpose = findViewById(R.id.tvPurpose);

        initData();

        btnConfirm.setOnClickListener(clickToConfirmRegister);
        toolbar.setNavigationOnClickListener(backPress);
    }

    private void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        tvStdName.setText(name);
        String number = intent.getStringExtra("NUMBER");
        tvMSSV.setText(number);
        String major = intent.getStringExtra("MAJOR");
        tvMajor.setText(major);
        String purpose = intent.getStringExtra("PURPOSE");
        tvPurpose.setText(purpose);
    }

    private View.OnClickListener clickToConfirmRegister = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), ServiceConfirmStudentSuccessActivity.class);
            intent.putExtra("SERVICE", ServiceConfirmStudentSuccessActivity.CONFIRM_STUDENT);
            startActivity(intent);
        }
    };

    private View.OnClickListener backPress = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}
