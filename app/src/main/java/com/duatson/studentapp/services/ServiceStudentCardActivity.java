package com.duatson.studentapp.services;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.duatson.studentapp.R;

import java.io.IOException;

public class ServiceStudentCardActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 3798;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_student_card);
        toolbar = findViewById(R.id.tbrApp);
        toolbar.setNavigationOnClickListener(backPress);

    }

    public void clickToReg(View view) {

        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ServiceConfirmStudentSuccessActivity.class);
        intent.putExtra("SERVICE", ServiceConfirmStudentSuccessActivity.STUDENT_CARD);
        startActivity(intent);
    }

    public void clickToChoosePhoto(View view) {
        Intent intent = new Intent();
        // Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                ImageView ivLibphoto = findViewById(R.id.ivLibphoto);
                ivLibphoto.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private View.OnClickListener backPress = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}
