package com.duatson.studentapp.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.duatson.studentapp.R;

import java.util.ArrayList;

public class ServiceConfirmStudentActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private RadioButton rdOther;
    private EditText edtStdName;
    private EditText edtMSSV;
    private EditText edtOther;
    private EditText edtMajor;
    private String name;
    private String number;
    private String major;
    private String purpose;

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
        name = edtStdName.getText().toString().trim();
        number = edtMSSV.getText().toString().trim();
        major = edtMajor.getText().toString().trim();

        if (name.length() > 0) {
            intent.putExtra("NAME", name);
        }
        if (number.length() > 0) {
            intent.putExtra("NUMBER", number);
        }
        if (major.length() > 0) {
            intent.putExtra("MAJOR", major);
        }
        if (rdOther.isChecked()) {
            purpose = edtOther.getText().toString().trim();
        }
        if (purpose.length() > 0) {
            intent.putExtra("PURPOSE", purpose);
        }
        if (name.length() == 0 || number.length() == 0 || purpose.length() == 0 || major.length() == 0) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin theo yêu cầu", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(intent);
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
                if (radioButton.getText().equals("Mục đích khác")) {
//                    purpose = edtOther.getText().toString().trim();
                } else {
                    purpose = radioButton.getText().toString();
                }

            }
        });
    }

    private void findView() {
        toolbar = findViewById(R.id.tbrApp);
        radioGroup = findViewById(R.id.rdGroup);
        edtStdName = findViewById(R.id.edtStdName);
        edtMSSV = findViewById(R.id.edtMSSV);
        edtOther = findViewById(R.id.edtOther);
        edtMajor = findViewById(R.id.edtMajor);
        rdOther= findViewById(R.id.rd5);
    }

}
