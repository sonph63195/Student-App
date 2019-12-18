package com.duatson.studentapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.duatson.studentapp.MainActivity;
import com.duatson.studentapp.R;
import com.duatson.studentapp.model.Request;

public class RegisterSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);
    }

    public void clickToHomepage(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    public void clickToReqDetail(View view) {
        Intent intent = new Intent(this, RequestDetailActivity.class);
        Request request = new Request("1", "CB9WNML20", "25/12/2019", "Đang xử lý", "Note something", null);

        intent.putExtra("REQUEST", request);
        startActivity(intent);
    }
}
