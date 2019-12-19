package com.duatson.studentapp.services;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.DialogFragment;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.duatson.studentapp.R;

public class ServiceHealthInsuranceCardActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ImageView imgSelectFront, imgSelectBack, imgSelectPicture;
    private Button btnFront, btnBack, btnPicture;
    private Toolbar toolbar;
    private RadioGroup radioGroup1, radioGroup2;
    private RadioButton radioButton1, radioButton2;
    private final int FRONT_IMG = 1;
    private final int BACK_IMG = 2;
    private final int PICTURE_IMG = 3;
    private LinearLayout lnLose;
    private EditText edtOther;
    private EditText edtId;
    private EditText edtEnd;
    private String result1 = "", result2 = "";
    private boolean isPicture = false, isFront = false, isBack = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_health_insurance);
        findView();
        clickToBack();
        radio1Checked();
        radio2Checked();

        btnFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, FRONT_IMG);
                isFront = true;
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
        imgSelectFront = findViewById(R.id.imgFrontImage);
        imgSelectBack = findViewById(R.id.imgBackImage);
        btnPicture = findViewById(R.id.btnSelectPicture);
        btnFront = findViewById(R.id.btnSelectFront);
        btnBack = findViewById(R.id.btnSelectBack);
        imgSelectPicture = findViewById(R.id.imgSelectPicture);
        lnLose = findViewById(R.id.lnLose);
        radioGroup1 = findViewById(R.id.rdGroup1);
        radioGroup2 = findViewById(R.id.rdGroup2);
        edtId = findViewById(R.id.edtId);
        edtOther = findViewById(R.id.edtOther);
        edtEnd = findViewById(R.id.edtEnd);
    }

    private void clickToBack() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void radio1Checked() {
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton1 = findViewById(i);
                result1 = radioButton1.getText().toString();
                if(result1.equals("Cấp lại thẻ")){
                    lnLose.setVisibility(View.VISIBLE);
                }else{
                    lnLose.setVisibility(View.GONE);
                    edtId.setError(null);
                }
            }
        });
    }

    private void radio2Checked() {
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton2 = findViewById(i);
                result2 = radioButton2.getText().toString();
                if(!result2.equals("Lý do khác: ")){
                    edtOther.setError(null);
                }
            }
        });
    }

    public void clickToNext(View view) {
        String toast = "";
        Intent intent;
        if (edtId.getText().toString().trim().length() == 0 && lnLose.getVisibility() == View.VISIBLE){
            edtId.setError("Mã thẻ không được để trống");
        }else
        if(lnLose.getVisibility() == View.GONE && result1.length() > 0 && isFront && isBack && isPicture){
            intent = new Intent(this, ServiceHealthInsurance2Activity.class);
            intent.putExtra("SERVICE", result1);
            startActivity(intent);

        } else if(result2.length() > 0 && !result2.equals("Lý do khác: ") && isFront && isBack && isPicture){
            if(edtEnd.getText().toString().length() > 0) {
                intent = new Intent(this, ServiceHealthInsurance2Activity.class);
                intent.putExtra("SERVICE", result1);
                intent.putExtra("RESULT", result2);
            intent.putExtra("ID", edtId.getText().toString().trim());
                startActivity(intent);
            }
        } else if(result2.length() > 0 && result2.equals("Lý do khác: ") && edtOther.getText().toString().trim().length() > 0 && isFront && isBack && isPicture){
            intent = new Intent(this, ServiceHealthInsurance2Activity.class);
            intent.putExtra("SERVICE", result1);
            intent.putExtra("RESULT", result2);
            intent.putExtra("OTHER", edtOther.getText().toString().trim());
            intent.putExtra("ID", edtId.getText().toString().trim());
            startActivity(intent);
        }
        if(result1.length() == 0){
            toast = "Vui lòng chọn lý do làm thẻ BHYT";
        }
        if((lnLose.getVisibility() == View.VISIBLE && result2.length() == 0)){
            toast = "Vui lòng chọn lý do làm lại thẻ BHYT";
        }
        if(!isFront || !isBack || !isPicture){
            if(toast.length() > 0){
                toast = toast + " và thêm những hình ảnh được yêu cầu.";
            }else{
                toast = "Vui lòng chọn những hình ảnh được yêu cầu";
            }
        }
        if(result2.equals("Lý do khác: ") && edtOther.getText().toString().trim().length() == 0){
            edtOther.setError("Bạn đã chọn lý do khác nên vui lòng nhập lý do.");
        }

        if(edtEnd.getText().toString().trim().length() == 0 && radioGroup2.getVisibility() == View.VISIBLE){
            edtEnd.setError("Bạn không được để trống mục này.");
        }

        if(toast.length() > 0)
        Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
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
                case FRONT_IMG:
                    imgSelectFront.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    break;
                case BACK_IMG:
                    imgSelectBack.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    break;
                case PICTURE_IMG:
                    imgSelectPicture.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    break;
            }
        }


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + (month + 1) + "/" +  year;
        edtEnd.setText(date);
    }

    public void clickToGetDate(View view) {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getFragmentManager(), "Date picker");
    }
}
