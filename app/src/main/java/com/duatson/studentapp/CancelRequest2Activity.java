package com.duatson.studentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class CancelRequest2Activity extends AppCompatActivity {
    private Toolbar topToolBar;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_request2);
        topToolBar = findViewById(R.id.app_top_bar);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
                Intent returnIntent = new Intent();
                TextInputEditText text = findViewById(R.id.tvReason);
                returnIntent.putExtra("result", "Đã hủy");
                returnIntent.putExtra("reason", text.getText().toString());
                setResult(Activity.RESULT_OK, returnIntent);
                Toast.makeText(CancelRequest2Activity.this, "Đã gửi", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        topToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang gửi...");
        progressDialog.show();
    }
}
