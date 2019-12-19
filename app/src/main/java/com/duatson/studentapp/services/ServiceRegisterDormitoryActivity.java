package com.duatson.studentapp.services;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private EditText edtOther;
    private EditText edtId;
    private ImageView imgSelectConfirmStudent, imgSelectPicture, imgSelectFront, imgSelectBack;
    private Button btnConfirmStudent, btnPicture, btnFront, btnBack;
    private final int CONFIRM_IMG = 1;
    private final int PICTURE_IMG = 2;
    private final int FRONT_IMG = 3;
    private final int BACK_IMG = 4;
    private boolean isStudent = false, isPicture = false, isFront = false, isBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_register_dormitory);
        findView();
        clickToBack();
        radioChecked();

        imgSelectConfirmStudent = findViewById(R.id.imgConfirmStudent);
        imgSelectPicture = findViewById(R.id.imgPicture);
        imgSelectFront = findViewById(R.id.imgFrontImage);
        imgSelectBack = findViewById(R.id.imgBackImage);

        btnPicture = findViewById(R.id.btnSelectPicture);
        btnConfirmStudent = findViewById(R.id.btnSelectConfirmStudent);
        btnFront = findViewById(R.id.btnSelectFront);
        btnBack = findViewById(R.id.btnSelectBack);

        btnConfirmStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, CONFIRM_IMG);
                isStudent = true;
            }
        });

        btnPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, PICTURE_IMG);
                isPicture = true;
            }
        });

        btnFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, FRONT_IMG);
                isFront = true;
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, BACK_IMG);
                isBack = true;
            }
        });
    }

    private void findView() {
        toolbar = findViewById(R.id.tbrApp);
        radioGroup = findViewById(R.id.rdGroup);
        edtOther = findViewById(R.id.edtOther);
        edtId = findViewById(R.id.edtId);
    }

    public void clickToNext(View view) {
        Intent intent = new Intent(this, ServiceRegisterDormitory2Activity.class);
        String toast = "";
        if(edtId.getText().toString().trim().length() == 0){
            edtId.setError("Mã thẻ không được để trống");
        }
        if ((!result.equals("Lý do khác: ") || edtOther.getText().toString().trim().length()> 0) && result.length() > 0
                && isStudent && isFront && isBack && isPicture)
         {
             if(result.equals("Lý do khác: ")){
                 result = edtOther.getText().toString().trim();
             }
             if(edtId.getText().toString().trim().length() > 0) {
                 intent.putExtra("ID", edtId.getText().toString().trim());
                 intent.putExtra("RESULT", result);
                 startActivity(intent);
             }
        } else if(result.length() == 0 || result.equals("Lý do khác: ")) {

                if(result.equals("Lý do khác: ") && edtOther.getText().length() == 0){
                    toast = "Bạn đã chọn muc lý do khác, vui lòng điền lý do";
                }else if(result.length() == 0){
                    toast = "Vui lòng chọn lý do cấp lại thẻ ký túc xá";
                }
                if (!isStudent || !isFront || !isBack || !isPicture) {
                    if(toast.length() > 0) {
                        toast = toast + " và các ảnh được yêu cầu";
                    }else{
                        toast = "Vui lòng thêm các ảnh được yêu cầu";
                    }
                }
        }else if (!isStudent || !isFront || !isBack || !isPicture){
            if(result.length() > 0)
                toast = "Vui lòng thêm các ảnh được yêu cầu";
        }

        if(toast.length() > 0)
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            switch (requestCode){
                case CONFIRM_IMG:
                    imgSelectConfirmStudent.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    break;
                case PICTURE_IMG:
                    imgSelectPicture.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    break;
                case FRONT_IMG:
                    imgSelectFront.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    break;
                case BACK_IMG:
                    imgSelectBack.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    break;
            }
        }


    }
}
