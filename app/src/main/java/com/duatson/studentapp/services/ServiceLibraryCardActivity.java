package com.duatson.studentapp.services;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.duatson.studentapp.R;
import com.duatson.studentapp.RegisterSuccessActivity;
import com.duatson.studentapp.fragment.DashboardFragment;
import com.duatson.studentapp.model.Request;
import com.duatson.studentapp.model.Service;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class ServiceLibraryCardActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 3798;

    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_library_card);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        service = (Service) intent.getSerializableExtra(DashboardFragment.MY_SERVICE_KEY);
        if (service == null) {
            Toast.makeText(this, "Không tìm thấy dịch vụ", Toast.LENGTH_SHORT).show();
            fileList();
        }
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

    public void clickToReg(View view) {

        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ServiceConfirmStudentSuccessActivity.class);
        intent.putExtra("SERVICE", ServiceConfirmStudentSuccessActivity.LIBRARY_CARD);
        startActivity(intent);
    }
}
