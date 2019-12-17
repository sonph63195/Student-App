package com.duatson.studentapp.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.duatson.studentapp.R;

public class ServiceConfirmStudentActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_confirm_student);

        findView();
        clickToBack();
        radioChecked();
    }

    public void clickToNext(View view) {
        Intent intent = new Intent(this, ServiceConfirmStudent2Activity.class);
        if (result.length() > 0) {
            intent.putExtra("RESULT", result);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Vui lòng chọn mục đích bạn muốn yêu cầu", Toast.LENGTH_SHORT).show();
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

    private void findView() {
        toolbar = findViewById(R.id.tbrApp);
        radioGroup = findViewById(R.id.rdGroup);
    }

}
