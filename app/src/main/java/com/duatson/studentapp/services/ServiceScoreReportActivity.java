package com.duatson.studentapp.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.duatson.studentapp.R;

public class ServiceScoreReportActivity extends AppCompatActivity {

    LinearLayout linearScore;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_score_report);
        toolbar = findViewById(R.id.tbrApp);
        linearScore = findViewById(R.id.linearScore);
        toolbar.setNavigationOnClickListener(backPress);
    }

    public void clickToReg(View view) {
        Intent intent = new Intent(this, ServiceConfirmStudentSuccessActivity.class);
        intent.putExtra("SERVICE", ServiceConfirmStudentSuccessActivity.SCORE_REPORT);

        startActivity(intent);
    }

    private View.OnClickListener backPress = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}
