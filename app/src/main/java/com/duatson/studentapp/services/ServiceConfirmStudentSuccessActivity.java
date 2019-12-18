package com.duatson.studentapp.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.duatson.studentapp.MainActivity;
import com.duatson.studentapp.R;

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
                intent1 = new Intent(ServiceConfirmStudentSuccessActivity.this, ServiceConfirmStudentDetailActivity.class);
                break;
            case 2:
                intent1 = new Intent(this, StudentCardDetailActivity.class);
                break;
            case 3:
                intent1 = new Intent(this, LibraryCardDetailActivity.class);
                break;
            case ServiceConfirmStudentSuccessActivity.HOSTEL_CARD:
                intent1 = new Intent(ServiceConfirmStudentSuccessActivity.this, ServiceConfirmStudentDetailActivity.class);
                Request request = new Request("4", "CB9WNML53", "18/12/2019", "Đang xử lý", "Làm lại thẻ ký túc xá", null);
                intent1.putExtra("FEE", "30.000 VND");
                intent1.putExtra("REQUEST", request);
                break;
            default:
                intent1 = new Intent(ServiceConfirmStudentSuccessActivity.this, ServiceConfirmStudentDetailActivity.class);
        }

        startActivity(intent1);
    }
}
