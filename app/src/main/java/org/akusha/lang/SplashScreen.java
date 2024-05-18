package org.akusha.lang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


import android.content.Context;
import android.content.Intent;

import android.content.res.Resources;

import android.os.Bundle;
import android.os.Handler;
import android.content.res.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Locale;

public class SplashScreen extends AppCompatActivity {
    public static boolean dark;
    public static File filesdir;

    public static boolean isDarkModeEnabled(Context context) {
        int currentNightMode = context.getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        // Check for dark mode on Android Q (API level 29) and above
            return currentNightMode == Configuration.UI_MODE_NIGHT_YES;
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
        setDarkContext(context, value, null);
    }
    public static void setDarkContext(AppCompatActivity context, boolean value, Locale locale){

        if (locale == null) locale = context.getResources().getConfiguration().getLocales().get(0);

        AppCompatDelegate.setDefaultNightMode(
                value ? AppCompatDelegate.MODE_NIGHT_YES:
                AppCompatDelegate.MODE_NIGHT_NO);
        context.recreate();

        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    public static Locale getLang(){
        File file = new File(SplashScreen.filesdir, "lang.txt");

            try {
                if (!file.exists()) file.createNewFile();
                FileReader reader = new FileReader(file);
                CharBuffer buffer = CharBuffer.allocate(2);
                reader.read(buffer);
                String locstr = new String(buffer.array());
                Locale locale = new Locale(locstr);

                return locale;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }

    public static boolean getSkip(Context context){
        File file = new File(SplashScreen.filesdir, "skip_intro.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
                return false;
            } else {
                return true;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void setLang(Context context, Locale locale){
        File file = new File(SplashScreen.filesdir, "lang.txt");
        try {

            if (file.exists()) file.delete();
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(locale.toLanguageTag());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

            setDarkContext(this, dark, getLang());
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
            startActivity( new Intent(SplashScreen.this, getSkip(this)
                    ? MainActivity.class
                    : Onboarding_1.class));
            finish();
        }, 100);
    }
}
