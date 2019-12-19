package com.duatson.studentapp.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.duatson.studentapp.MainActivity;
import com.duatson.studentapp.R;
import com.google.android.material.chip.Chip;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceDormitoryDetailActivity extends AppCompatActivity {

    private TextView txtResult, txtDate, txtId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_dormitory_detail);
        findView();
        initView();


    }

    public void clickToCancel(View view) {
        Toast.makeText(this, "Hủy thành công", Toast.LENGTH_SHORT).show();
        Chip chipConfirmStatus = findViewById(R.id.chipConfirmStatus);
        chipConfirmStatus.setText("Đã hủy");
        Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setVisibility(View.INVISIBLE);
    }

    private void findView(){
        txtResult = findViewById(R.id.txtReason);
        txtDate = findViewById(R.id.txtDate);
        txtId = findViewById(R.id.txtId);
    }

    private void initView(){
        txtResult.setText(this.getIntent().getStringExtra("RESULT"));
        Date date = new Date();
        String modifiedDate= new SimpleDateFormat("dd/MM/yyyy").format(date);
        txtDate.setText(modifiedDate);
        txtId.setText(this.getIntent().getStringExtra("ID"));
    }

    public void clickToHomepage(View view) {
        Intent intent = new Intent(ServiceDormitoryDetailActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
