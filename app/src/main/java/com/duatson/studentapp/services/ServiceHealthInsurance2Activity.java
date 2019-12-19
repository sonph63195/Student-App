package com.duatson.studentapp.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duatson.studentapp.R;

public class ServiceHealthInsurance2Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnConfirm;
    private LinearLayout lnId;
    private TextView txtResult, txtFee, txtService, txtId;
    private String reason = "", service = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_health_insurance2);
        findView();
        toolbar.setNavigationOnClickListener(backPress);
        btnConfirm.setOnClickListener(clickToConfirmRegister);

        Intent intent = this.getIntent();
        service = intent.getStringExtra("SERVICE");
        if(service != null){
            reason = intent.getStringExtra("RESULT");
            if(service.equals("Cấp lại thẻ")){
                txtService.setText(service + ":");
                lnId.setVisibility(View.VISIBLE);
                txtFee.setText("30.000 VND");
                txtId.setText(intent.getStringExtra("ID"));

                if(!reason.equals("Lý do khác: ")){
                    txtResult.setText(reason);
                }else{
                    txtResult.setText(intent.getStringExtra("OTHER"));
                }
            }
        }
    }

    private void findView(){
        toolbar = findViewById(R.id.tbrApp);
        btnConfirm = findViewById(R.id.btnConfirm);
        txtFee = findViewById(R.id.txtFee);
        txtResult = findViewById(R.id.txtResult);
        txtService = findViewById(R.id.txtService);
        lnId = findViewById(R.id.lnId);
        txtId = findViewById(R.id.txtId);
    }

    private View.OnClickListener clickToConfirmRegister = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), ServiceConfirmStudentSuccessActivity.class);
            intent.putExtra("SERVICE", ServiceConfirmStudentSuccessActivity.HOSPITAL_CARD);
            intent.putExtra("SERVICE_TYPE", service);
            if(service.equals("Cấp lại thẻ")) {
//                intent.putExtra("ID", txtId.getText().toString());
                intent.putExtra("REASON", txtResult.getText().toString());
            }
            startActivity(intent);
        }
    };

    private View.OnClickListener backPress = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

}
