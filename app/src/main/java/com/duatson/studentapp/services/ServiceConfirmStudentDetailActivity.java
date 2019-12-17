package com.duatson.studentapp.services;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.duatson.studentapp.MainActivity;
import com.duatson.studentapp.R;

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
//
//            AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
//                    getApplicationContext());
            new AlertDialog.Builder(getApplicationContext())
//            alertDialog2
                    .setTitle("Thông báo")
                    .setMessage("Xác nhận hủy yêu cầu")
                    .setNegativeButton("Xác nhận", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new MainActivity().getIntent());
                        }
                    })
                    .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .show();
        }
    };
}
