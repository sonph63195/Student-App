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
        Intent intent = new Intent(this, ServiceConfirmStudentDetailActivity.class);
//        Request request = new Request("1", "CB9WNML20", "28/12/2019", "Đang xử lý", "", null);
//        intent.putExtra("REQUEST", request);
        startActivity(intent);
    }
}
