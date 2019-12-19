package com.duatson.studentapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.chip.Chip;

public class RequestDetail5Activity extends AppCompatActivity {
    private Toolbar topToolBar;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail5);
        btnCancel = findViewById(R.id.btnCancel);
        topToolBar = findViewById(R.id.app_top_bar);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Hủy thành công", Toast.LENGTH_SHORT).show();
                Chip chipConfirmStatus = findViewById(R.id.chipConfirmStatus);
                chipConfirmStatus.setText("Đã hủy");
                btnCancel.setVisibility(View.INVISIBLE);
            }
        });
        topToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
