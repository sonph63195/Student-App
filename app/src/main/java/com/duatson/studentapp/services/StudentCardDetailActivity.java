package com.duatson.studentapp.services;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.duatson.studentapp.R;
import com.google.android.material.chip.Chip;

public class StudentCardDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_card_detail);
    }

    public void clickToCancel(View view) {
        Toast.makeText(this, "Hủy thành công", Toast.LENGTH_SHORT).show();
        Chip chipConfirmStatus = findViewById(R.id.chipConfirmStatus);
        chipConfirmStatus.setText("Đã hủy");
        Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setVisibility(View.INVISIBLE);
    }
}
