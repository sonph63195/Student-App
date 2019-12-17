package com.duatson.studentapp.services;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.duatson.studentapp.R;

public class ServiceRegisterDormitory2Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvResult;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_register_dormitory2);

        toolbar = findViewById(R.id.tbrApp);
        tvResult = findViewById(R.id.txtResult);
        btnConfirm = findViewById(R.id.btnConfirm);

        initData();

        btnConfirm.setOnClickListener(clickToConfirmRegister);
        toolbar.setNavigationOnClickListener(backPress);
    }

    private void initData() {
        Intent intent = getIntent();
        String result = intent.getStringExtra("RESULT");
        tvResult.setText(result);
    }

    private View.OnClickListener clickToConfirmRegister = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), ServiceConfirmStudentSuccessActivity.class);
            intent.putExtra("SERVICE", ServiceConfirmStudentSuccessActivity.HOSTEL_CARD);
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
