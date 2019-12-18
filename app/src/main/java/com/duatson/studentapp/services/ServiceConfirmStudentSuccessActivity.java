package com.duatson.studentapp.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.duatson.studentapp.MainActivity;
import com.duatson.studentapp.R;
import com.duatson.studentapp.model.Request;

public class ServiceConfirmStudentSuccessActivity extends AppCompatActivity {

    public static final int CONFIRM_STUDENT = 1;
    public static final int STUDENT_CARD = 2;
    public static final int LIBRARY_CARD = 3;
    public static final int HOSTEL_CARD = 4;
    public static final int HOSPITAL_CARD = 5;
    public static final int SCORE_REPORT = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_confirm_student_success);
    }

    public void clickToHomepage(View view) {
        this.finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void clickToReqDetail(View view) {
        Intent intent = getIntent();

        int service = (int) intent.getSerializableExtra("SERVICE");

        Intent intent1 = null;
        switch (service) {
            case CONFIRM_STUDENT:
                intent1 = new Intent(this, ServiceConfirmStudentDetailActivity.class);
                break;
            case STUDENT_CARD:
                intent1 = new Intent(this, StudentCardDetailActivity.class);
                break;
            case LIBRARY_CARD:
                intent1 = new Intent(this, LibraryCardDetailActivity.class);
                break;
            case HOSTEL_CARD:
               intent1 = new Intent(this, ServiceRegisterDormitoryDetailActivity.class);
                break;
            case SCORE_REPORT:
                intent1 = new Intent(this, ScoreReportDetailActivity.class);
                break;
        }
        this.finish();
        startActivity(intent1);
    }
}
