package org.akusha.lang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.content.res.Configuration;

public class SplashScreen extends AppCompatActivity {
    public int color = R.string.light;

    public static SharedPreferences sharedPreferences;

    public static boolean isDarkModeEnabled(Context context) {
        int currentNightMode = context.getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        // Check for dark mode on Android Q (API level 29) and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return currentNightMode == Configuration.UI_MODE_NIGHT_YES;
        } else {
            // Check for dark mode on Android Pie (API level 28) and below
            UiModeManager uiModeManager = (UiModeManager) context.getSystemService(Context.UI_MODE_SERVICE);
            return uiModeManager != null && uiModeManager.getNightMode() == UiModeManager.MODE_NIGHT_YES;
        }
    }

    public static void setDark(boolean enable){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("language_app_theme_settings", enable);
        edit.apply();
    }

    public static boolean getDark(){
        return sharedPreferences.getBoolean("language_app_theme_settings", false);
    }

    public static void setDarkContext(AppCompatActivity context, boolean value){
        AppCompatDelegate.setDefaultNightMode(
                value ? AppCompatDelegate.MODE_NIGHT_YES:
                AppCompatDelegate.MODE_NIGHT_NO);
        context.recreate();
    }

    public void init(){

        sharedPreferences = getSharedPreferences("language_app_theme_settings", Context.MODE_PRIVATE);

        boolean systemdark = isDarkModeEnabled(this);
        boolean dark = sharedPreferences.getBoolean("night", systemdark);
        if (systemdark != dark){
            setDarkContext(this, dark);
        }
        setDark(dark);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        init();
        new Handler().postDelayed(() -> {
            startActivity( new Intent(SplashScreen.this, Onboarding_1.class));
            finish();
        }, 100);
    }
}
