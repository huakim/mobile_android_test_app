package org.akusha.lang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.content.res.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class SplashScreen extends AppCompatActivity {
    public static boolean dark;
    public static File filesdir;

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
// Create the file in the internal directory
        SplashScreen.dark = enable;
        File configFile = new File(filesdir, "dark.txt");
        try {
            // Create a new file or overwrite the existing file
            FileOutputStream fos = new FileOutputStream(configFile);

            // Write the single byte to the file
            fos.write(enable ? 1 : 0);

            // Flush and close the FileOutputStream
            fos.flush();
            fos.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static boolean getDark(){
        return SplashScreen.dark;
    }

    public static void setDarkContext(AppCompatActivity context, boolean value){

        Locale locale = context.getResources().getConfiguration().getLocales().get(0);

        AppCompatDelegate.setDefaultNightMode(
                value ? AppCompatDelegate.MODE_NIGHT_YES:
                AppCompatDelegate.MODE_NIGHT_NO);
        context.recreate();

        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    public void init(){
        FileInputStream fis = null;
        try {
            File file = new File(SplashScreen.filesdir =
                    getFilesDir(), "dark.txt");
            if (!file.exists()) file.createNewFile();

            fis = new FileInputStream(file);

            boolean systemdark = isDarkModeEnabled(this);

            // Read a single byte from the file
            boolean dark = fis.read() == 1;
            if (systemdark != dark){
                setDarkContext(this, dark);
            }
            setDark(dark);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
