package com.duatson.studentapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.duatson.studentapp.services.ServiceConfirmStudentDetailActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;

import static com.duatson.studentapp.R.color.amaranth;

public class RequestDetail1Activity extends AppCompatActivity {
    private Toolbar topToolBar;
    private Button btnCancel;
    private TextView tvReason;
    private TextView tvTitle;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail1);
        btnCancel = findViewById(R.id.btnCancel);
        topToolBar = findViewById(R.id.app_top_bar);
        tvReason = findViewById(R.id.txtReason);
        tvTitle = findViewById(R.id.txtTitle);
        linearLayout = findViewById(R.id.lyCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CancelRequest1Activity.class);
                startActivityForResult(intent, 1);
            }
        });
        topToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                String reason = data.getStringExtra("reason");
                Chip chipConfirmStatus = findViewById(R.id.chipConfirmStatus);
                tvReason.setText(reason);
                chipConfirmStatus.setText(result);
                tvReason.setVisibility(View.VISIBLE);
                tvTitle.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.INVISIBLE);
            }
        }
    }
}
