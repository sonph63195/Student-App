package com.duatson.studentapp.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.duatson.studentapp.MainActivity;
import com.duatson.studentapp.R;
import com.duatson.studentapp.services.ServiceConfirmStudentDetailActivity;
import com.google.android.material.chip.Chip;

public class LibraryCardDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_card_detail);
    }

    public void clickToCancel(View view) {
        Toast.makeText(this, "Hủy thành công", Toast.LENGTH_SHORT).show();
        Chip chipConfirmStatus = findViewById(R.id.chipConfirmStatus);
        chipConfirmStatus.setText("Đã hủy");
        Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setVisibility(View.INVISIBLE);
    }

    public void clickToHomepage(View view) {
        this.finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
