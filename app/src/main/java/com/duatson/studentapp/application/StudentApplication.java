package com.duatson.studentapp.application;


import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

public class StudentApplication extends Application {
    private static StudentApplication instance;
    private static Context appContext;

    public static StudentApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static void setAppContext(Context appContext) {
        StudentApplication.appContext = appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        this.setAppContext(getApplicationContext());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
