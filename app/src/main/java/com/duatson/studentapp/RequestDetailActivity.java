package com.duatson.studentapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.duatson.studentapp.model.Request;

public class RequestDetailActivity extends AppCompatActivity {

    private Button btnFeedback;
    private Toolbar topToolBar;
    private Button btnCancel;
    private TextView tvRequestStatus;
    private final int FEEDBACK_REQUEST_CODE = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_request_detail);

        Intent intent = this.getIntent();
        Request request = (Request) intent.getSerializableExtra("REQUEST");
        String status = request.getStatus();

        btnFeedback = findViewById(R.id.btnFeedback);
        topToolBar = findViewById(R.id.app_top_bar);
        tvRequestStatus = findViewById(R.id.tvRequestStatus);
        btnCancel = findViewById(R.id.btnCancel);


        if (status.equals("Đã hoàn thành")) {
            tvRequestStatus.setTextColor(Color.RED);
            btnFeedback.setVisibility(View.VISIBLE);
            btnCancel.setVisibility(View.GONE);
        } else if (status.equals("Đang xử lý")) {
            tvRequestStatus.setTextColor(Color.YELLOW);
        }
        tvRequestStatus.setText(status);

        topToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FEEDBACK_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                btnFeedback.setVisibility(View.GONE);
            }
        }
    }
}
