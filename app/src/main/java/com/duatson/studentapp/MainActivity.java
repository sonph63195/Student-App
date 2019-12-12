package com.duatson.studentapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.duatson.studentapp.fragment.DashboardFragment;
import com.duatson.studentapp.fragment.HelpFragment;
import com.duatson.studentapp.fragment.NotificationFragment;
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

    /**
     * Navigate to the given fragment.
     *
     * @param fragment       Fragment to navigate to.
     * @param addToBackstack Whether or not the current fragment should be added to the backstack.
     */
    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment);

        if (addToBackstack) {
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

    public void clickToBack(View view) {
        super.onBackPressed();
    }

    public void clickToNotification(MenuItem item) {
        this.navigateTo(new NotificationFragment(), true);
    }

    public void clickToSignOut(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Bạn thực sự muốn đăng xuất khỏi ứng dụng CTSV?");
        builder.setPositiveButton("hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // TODO do nothing
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
