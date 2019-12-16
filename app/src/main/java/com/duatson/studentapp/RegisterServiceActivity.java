package com.duatson.studentapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.kofigyan.stateprogressbar.components.StateItem;
import com.kofigyan.stateprogressbar.listeners.OnStateItemClickListener;

public class RegisterServiceActivity extends AppCompatActivity {

    private TextView txtRegisterTitle;
    private Button btnNext1;
    private Button btnConfirm;
    private Button btnNext2;
    private Toolbar toolbar;
    private int currentNumber = 1;
    private StateProgressBar stateProgressBar;

    private ViewGroup registerStep;

    String[] descriptionData = {"CHI TIẾT", "ĐĂNG KÝ", "XÁC NHẬN"};
    private static final int PICK_FILE_RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_service);

        registerStep = findViewById(R.id.registerStep);
        setRegisterLayout(R.layout.layout_register_step_1);

        stateProgressBar = findViewById(R.id.progressStep);
        stateProgressBar.setStateDescriptionData(descriptionData);
        txtRegisterTitle = findViewById(R.id.txtRegisterTitle);
        btnNext1 = findViewById(R.id.btnNextFirst);

        btnNext1.setOnClickListener(clickToNextStep);

        stateProgressBar.setOnStateItemClickListener(new OnStateItemClickListener() {
            @Override
            public void onStateItemClick(StateProgressBar stateProgressBar, StateItem stateItem, int stateNumber, boolean isCurrentState) {
                Log.d("StateItem", "stateItem=" + stateItem.toString() + " - " + "stateNumber=" + stateNumber);
                switch (stateNumber) {
                    case 1:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
                        txtRegisterTitle.setText("Thông tin chi tiết");
                        currentNumber = 1;
                        break;
                    case 2:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                        txtRegisterTitle.setText("Đăng ký thông tin");
                        currentNumber = 2;

                        break;
                    case 3:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                        txtRegisterTitle.setText("Xác nhận thông tin");
                        currentNumber = 3;
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

    private View.OnClickListener confirmRegister = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener clickToNextStep = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (currentNumber == 1) {
                setRegisterLayout(R.layout.layout_register_step_2);
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                txtRegisterTitle.setText("Đăng ký thông tin");
                currentNumber = 2;
                btnNext2 = findViewById(R.id.btnNextSecond);
                btnNext2.setOnClickListener(clickToNextStep);
            } else if (currentNumber == 2) {
                setRegisterLayout(R.layout.layout_register_step_3);
                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                txtRegisterTitle.setText("Xác nhận thông tin");
                currentNumber = 3;
                btnConfirm = findViewById(R.id.btnConfirm);
                btnConfirm.setOnClickListener(confirmRegister);
            }
        }
    };

    private void setRegisterLayout(int layoutId) {
        registerStep.removeAllViews();
        View view = getLayoutInflater().inflate(layoutId, registerStep, false);
        registerStep.addView(view);
    }
}
