package com.duatson.studentapp.services;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.duatson.studentapp.MainActivity;
import com.duatson.studentapp.R;
import com.google.android.material.chip.Chip;

public class ServiceRegisterDormitoryDetailActivity extends Activity {

    private Button btnCancel;
    private TextView txtTitle, txtFee, txtReasonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_register_dormitory_detail);
//        btnCancel = findViewById(R.id.btnCancel);
////        btnCancel.setOnClickListener(clickToCancel(cli););
//        txtReasonCancel = findViewById(R.id.txtReasonCancel);
//        Intent intent = this.getIntent();
//        txtTitle = findViewById(R.id.txtTitle);
//        txtFee = findViewById(R.id.txtFee);

    }

//    public void clickToCancel(View view) {
//        if(txtReasonCancel.getText().toString().trim().length() > 0){
//            Toast.makeText(this, "Hủy thành công", Toast.LENGTH_SHORT).show();
//            Chip chipConfirmStatus = findViewById(R.id.chipConfirmStatus);
//            chipConfirmStatus.setText("Đã hủy");
//            Button btnCancel = findViewById(R.id.btnCancel);
//            btnCancel.setVisibility(View.GONE);
//            txtReasonCancel.setVisibility(View.GONE);
//        }else{
//            txtReasonCancel.setError("Bạn không thể để trống lý do khi hủy yêu cầu.");
//
//        }
//    }

    public void clickToHomepage(View view) {
        this.finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
