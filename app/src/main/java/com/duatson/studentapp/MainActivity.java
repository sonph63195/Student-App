package com.duatson.studentapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.duatson.studentapp.fragment.DashboardFragment;

public class MainActivity extends AppCompatActivity implements NavigationHost {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new DashboardFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }


    public void clickToSignOut(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Bạn thực sự muốn đăng xuất khỏi ứng dụng CTSV?");
        builder.setPositiveButton("hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setNegativeButton("Đăng xuất", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Đăng xuất thành công!", Toast.LENGTH_SHORT).show();
                navigateTo(new DashboardFragment(), false);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void clickToEditProfile(View view) {
        Toast.makeText(this, "Edit profile", Toast.LENGTH_SHORT).show();
    }

    public void clickToViewProfile(View view) {
        Intent intent = new Intent(this, StudentInfoActivity.class);
        startActivity(intent);
    }

    public void clickToHelp(View view) {
        Intent intent = new Intent(this, HelpContactActivity.class);
        startActivity(intent);
    }

    public void clickToSetting(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void clickToViewNote1(View view) {
        Intent intent = new Intent(this, Notification1DetailActivity.class);
        startActivity(intent);
    }
}
