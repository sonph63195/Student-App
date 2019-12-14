package com.duatson.studentapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.duatson.studentapp.fragment.DashboardFragment;
import com.duatson.studentapp.fragment.HelpFragment;
import com.duatson.studentapp.fragment.SettingsFragment;
import com.duatson.studentapp.fragment.StudentInfoFragment;

public class MainActivity extends AppCompatActivity implements NavigationHost {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new DashboardFragment())
                    .commit();

            System.out.println("Frame: " + getSupportFragmentManager().getBackStackEntryCount());
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
        navigateTo(new StudentInfoFragment(), true);
    }

    public void clickToHelp(View view) {
        navigateTo(new HelpFragment(), true);
    }

    public void clickToSetting(View view) {
        navigateTo(new SettingsFragment(), true);
    }
}
