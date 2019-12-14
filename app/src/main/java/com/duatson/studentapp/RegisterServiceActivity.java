package com.duatson.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.kofigyan.stateprogressbar.StateProgressBar;

public class RegisterServiceActivity extends AppCompatActivity {

    private TextView textFile;
    private Toolbar toolbar;
    String[] descriptionData = {"CHI TIẾT", "ĐĂNG KÝ", "XÁC NHẬN"};
    private static final int PICK_FILE_RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_service);

        toolbar = findViewById(R.id.tbrRegister);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        StateProgressBar stateProgressBar = findViewById(R.id.progressStep);
        stateProgressBar.setStateDescriptionData(descriptionData);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_FILE_RESULT_CODE:
                if (resultCode == RESULT_OK) {
                    String FilePath = data.getData().getPath();
                    textFile.setText(FilePath);
                }
                break;

        }
    }
}
