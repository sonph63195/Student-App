package com.duatson.studentapp.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.duatson.studentapp.MainActivity;
import com.duatson.studentapp.R;
import com.google.android.material.chip.Chip;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceHealthInsuranceDetailActivity extends AppCompatActivity {

    private TextView txtResult, txtDate, txtId, txtFee;
    private LinearLayout lnId;
    private View line;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_health_insurance_detail);

        findView();
        initView();

        Intent intent = this.getIntent();
        String service = intent.getStringExtra("SERVICE_TYPE");
        if(service.equals("Cấp lại thẻ")){
            String reason = intent.getStringExtra("REASON");
            lnId.setVisibility(View.VISIBLE);
            txtFee.setText("30.000 VND");
            txtResult.setText(service + " vì " + reason);
            txtId.setText(this.getIntent().getStringExtra("ID"));
            line.setVisibility(View.VISIBLE);
        }
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
        txtFee = findViewById(R.id.txtFee);
        lnId = findViewById(R.id.lnId);
        line = findViewById(R.id.line);

    }

    private void initView(){
        Date date = new Date();
        String modifiedDate= new SimpleDateFormat("dd/MM/yyyy").format(date);
        txtDate.setText(modifiedDate);
    }

    public void clickToHomepage(View view) {
        Intent intent = new Intent(ServiceHealthInsuranceDetailActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
