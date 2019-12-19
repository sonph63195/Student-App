package com.duatson.studentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        toolbar = findViewById(R.id.tbrSettings);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void clickToChangePwd(View view) {
        Toast.makeText(this, "Thay đổi mật khẩu", Toast.LENGTH_SHORT).show();
    }

    public void clickToForgotPwd(View view) {
        Toast.makeText(this, "Quên mật khẩu", Toast.LENGTH_SHORT).show();
    }

    public void clickToLang(View view) {
        Toast.makeText(this, "Thay đổi ngôn ngữ ứng dụng", Toast.LENGTH_SHORT).show();
    }
}
