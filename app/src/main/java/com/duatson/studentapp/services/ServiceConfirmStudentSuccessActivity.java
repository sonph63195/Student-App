package com.duatson.studentapp.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.duatson.studentapp.MainActivity;
import com.duatson.studentapp.R;
import com.duatson.studentapp.RequestDetailActivity;
import com.duatson.studentapp.model.Request;

public class ServiceConfirmStudentSuccessActivity extends AppCompatActivity {

    public static final int CONFIRM_STUDENT = 1;
    public static final int STUDENT_CARD = 2;
    public static final int LIBRARY_CARD = 3;
    public static final int HOSTEL_CARD = 4;
    public static final int HOSPITAL_CARD = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_confirm_student_success);
    }

    public void clickToHomepage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void clickToReqDetail(View view) {
        Intent intent = getIntent();

        int service = (int) intent.getSerializableExtra("SERVICE");

        Intent intent1 = null;
        switch (service) {
            case 1:
                intent1 = new Intent(this, ServiceConfirmStudentDetailActivity.class);
                break;
            case 2:
                intent1 = new Intent(this, ServiceConfirmStudentDetailActivity.class);
                break;
            case 3:
                intent1 = new Intent(this, ServiceConfirmStudentDetailActivity.class);
                break;
            case 4:
                break;
            default:
                intent1 = new Intent(this, ServiceConfirmStudentDetailActivity.class);
        }

        startActivity(intent1);
    }
}
