package com.duatson.studentapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.duatson.studentapp.services.ServiceConfirmStudentDetailActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;

import static com.duatson.studentapp.R.color.amaranth;

public class RequestDetail1Activity extends AppCompatActivity {
    private Toolbar topToolBar;
    private Button btnCancel;
    private String txtReason = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail1);
        btnCancel = findViewById(R.id.btnCancel);
        topToolBar = findViewById(R.id.app_top_bar);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getApplicationContext());
//                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("Hủy yêu cầu");
                final EditText input = new EditText(getApplicationContext());
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);
                builder.setMessage("Vui lòng cung cấp lý do hủy yêu cầu:");
                builder.setNegativeButton("đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("gửi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        txtReason = input.getText().toString();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                Toast.makeText(getApplicationContext(), txtReason, Toast.LENGTH_SHORT).show();
//                Chip chipConfirmStatus = findViewById(R.id.chipConfirmStatus);
//                chipConfirmStatus.setText("Đã hủy");
//                btnCancel.setVisibility(View.INVISIBLE);
            }
        });
        topToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
