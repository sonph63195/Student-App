package com.duatson.studentapp.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.duatson.studentapp.R;

public class ServiceScoreReportActivity extends AppCompatActivity {

    LinearLayout linearScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_score_report);

        linearScore = findViewById(R.id.linearScore);
    }

    public void clickToReg(View view) {
        Intent intent = new Intent(this, ServiceConfirmStudentSuccessActivity.class);
        intent.putExtra("SERVICE", ServiceConfirmStudentSuccessActivity.SCORE_REPORT);

        startActivity(intent);
    }
}
