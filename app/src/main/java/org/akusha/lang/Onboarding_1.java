package org.akusha.lang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Onboarding_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding1);

        findViewById(R.id.textView).setOnClickListener(v -> startActivity(new Intent(Onboarding_1.this, LanguageSelect.class)));

        findViewById(R.id.button).setOnClickListener(v -> startActivity(new Intent(Onboarding_1.this, Onboarding_2.class)));
    }
}
