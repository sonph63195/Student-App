package com.duatson.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.duatson.studentapp.model.Request;

public class RegisterSuccessActivity extends AppCompatActivity {

    private Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        request = (Request) intent.getSerializableExtra(RegisterServiceActivity.REQUEST_KEY);

        if (request == null) {
            Toast.makeText(this, "Could not find this Request", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void clickToHomepage(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    public void clickToReqDetail(View view) {
        Intent intent = new Intent(this, RequestDetailActivity.class);
        intent.putExtra("REQUEST", request);
        startActivity(intent);
    }
}
