package com.example.administrator.databindingadvanceddemo;

import android.app.Application;

public class CustomApplication extends Application {

    public static boolean isHomeSelected, isFavoriteSelected, isMemberSelected, isMoreSelected;

    @Override
    public void onCreate() {
        super.onCreate();

        InitializeGlobalVariables();
    }

    private void InitializeGlobalVariables() {
        isHomeSelected = true;
        isFavoriteSelected = false;
        isMemberSelected = false;
        isMoreSelected = false;
    }
}

