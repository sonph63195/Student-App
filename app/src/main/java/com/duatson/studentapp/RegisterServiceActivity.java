package com.duatson.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.duatson.studentapp.fragment.HistoryFragment;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.kofigyan.stateprogressbar.components.StateItem;
import com.kofigyan.stateprogressbar.listeners.OnStateItemClickListener;

public class RegisterServiceActivity extends AppCompatActivity {

    private TextView txtRegisterTitle;
    private Button btnNext;
    private Button btnConfirm;
    private Toolbar toolbar;
    private int currentNumber = 1;
    private StateProgressBar stateProgressBar;
    String[] descriptionData = {"CHI TIẾT", "ĐĂNG KÝ", "XÁC NHẬN"};
    private static final int PICK_FILE_RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_service);

        stateProgressBar = findViewById(R.id.progressStep);
        stateProgressBar.setStateDescriptionData(descriptionData);
        txtRegisterTitle = findViewById(R.id.txtRegisterTitle);
        btnNext = findViewById(R.id.btnNext);
        btnConfirm = findViewById(R.id.btnConfirm);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentNumber == 1) {
                    stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                    txtRegisterTitle.setText("Đăng ký thông tin");
                    currentNumber = 2;
                } else if (currentNumber == 2) {
                    stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                    txtRegisterTitle.setText("Xác nhận thông tin");
                    currentNumber = 3;
                    btnNext.setVisibility(View.GONE);
                    btnConfirm.setVisibility(View.VISIBLE);
                }
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            }
        });

        stateProgressBar.setOnStateItemClickListener(new OnStateItemClickListener() {
            @Override
            public void onStateItemClick(StateProgressBar stateProgressBar, StateItem stateItem, int stateNumber, boolean isCurrentState) {
                Log.d("StateItem", "stateItem=" + stateItem.toString() + " - " + "stateNumber=" + stateNumber);
                switch (stateNumber) {
                    case 1:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
                        txtRegisterTitle.setText("Thông tin chi tiết");
                        currentNumber = 1;
                        btnNext.setVisibility(View.VISIBLE);
                        btnConfirm.setVisibility(View.GONE);
                        break;
                    case 2:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                        txtRegisterTitle.setText("Đăng ký thông tin");
                        currentNumber = 2;
                        btnNext.setVisibility(View.VISIBLE);
                        btnConfirm.setVisibility(View.GONE);
                        break;
                    case 3:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                        txtRegisterTitle.setText("Xác nhận thông tin");
                        currentNumber = 3;
                        btnNext.setVisibility(View.GONE);
                        btnConfirm.setVisibility(View.VISIBLE);
                }
            }
        });

        toolbar = findViewById(R.id.tbrRegister);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case PICK_FILE_RESULT_CODE:
//                if (resultCode == RESULT_OK) {
//                    String FilePath = data.getData().getPath();
//                    textFile.setText(FilePath);
//                }
//                break;
//        }
//    }
}
