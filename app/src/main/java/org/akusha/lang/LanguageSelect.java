package org.akusha.lang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import java.util.*;
import android.view.View;
import android.widget.Button;

public class LanguageSelect extends AppCompatActivity {
    // доступные языки
    static final int [] languages = new int[]{R.id.english_button, R.id.russian_button};
    static final Map<Integer, String> locales = new HashMap<>();
    static{
        locales.put(R.id.english_button, "en");
        locales.put(R.id.russian_button, "ru");
    }
    // выбрать язык
    static protected void languageChoose(int langid, Map<Integer, Button> buttonsmap){
        for (int lang: LanguageSelect.languages){
            buttonsmap.get(lang).setSelected(lang == langid);
        }
    }

    protected Button addButton(Map<Integer, Button> map, int id){
        Button button = findViewById(id);
        map.put(id, button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageChoose(id, map);
                String languageCode = locales.get(id);
                Locale locale = new Locale(languageCode);
                Locale.setDefault(locale);
                android.content.res.Resources resources = getResources();
                android.content.res.Configuration config = resources.getConfiguration();
                config.setLocale(locale);
                resources.updateConfiguration(config, resources.getDisplayMetrics());
                SplashScreen.setLang(LanguageSelect.this, locale);
            }
        });

        return button;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_select);

        int rusbutid = R.id.russian_button, engbutid = R.id.english_button;

        Map<Integer, Button> map = new HashMap<>();

        Button rusButton = addButton(map, rusbutid),
               engButton = addButton(map, engbutid);

        Button chooseButton = findViewById(R.id.choose_button);
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LanguageSelect.this, Login.class));
            }
        });
    }
}
