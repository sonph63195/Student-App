package com.duatson.studentapp.services;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.duatson.studentapp.R;

public class ServiceRegisterDormitoryActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_register_dormitory);
        findView();
        clickToBack();
        radioChecked();
    }

    private void findView() {
        toolbar = findViewById(R.id.tbrApp);
        radioGroup = findViewById(R.id.rdGroup);
    }

    public void clickToNext(View view) {
        Intent intent = new Intent(this, ServiceRegisterDormitory2Activity.class);
        if (result.length() > 0) {
            intent.putExtra("RESULT", result);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Vui lòng chọn lý do cấp lại thẻ ký túc xá", Toast.LENGTH_SHORT).show();
        }
    }

    private void clickToBack() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void radioChecked() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = findViewById(i);
                result = radioButton.getText().toString();
            }
        });
    }
}
