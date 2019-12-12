package com.duatson.studentapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RequestDetailActivity extends AppCompatActivity {

    private Button btnFeedback;
    private Toolbar topToolBar;
    private final int FEEDBACK_REQUEST_CODE = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_request_detail);

        btnFeedback = findViewById(R.id.btnFeedback);
        topToolBar = findViewById(R.id.app_top_bar);

        topToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(RequestDetailActivity.this, FeedbackActivity.class), FEEDBACK_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FEEDBACK_REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK){
                btnFeedback.setVisibility(View.GONE);
            }
        }
    }
}
