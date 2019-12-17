package com.duatson.studentapp.services;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.duatson.studentapp.MainActivity;
import com.duatson.studentapp.R;
import com.google.android.material.chip.Chip;

public class ServiceConfirmStudentDetailActivity extends Activity {

    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_confirm_student_detail);
        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(cancelRequest);
    }

    private View.OnClickListener cancelRequest = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(ServiceConfirmStudentDetailActivity.this, "Hủy thành công", Toast.LENGTH_SHORT).show();
            Chip chipConfirmStatus = findViewById(R.id.chipConfirmStatus);
            chipConfirmStatus.setText("Đã hủy");
            btnCancel.setVisibility(View.INVISIBLE);
        }
    };
}
