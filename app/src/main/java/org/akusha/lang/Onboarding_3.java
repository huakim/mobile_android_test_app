package org.akusha.lang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

public class Onboarding_3 extends AppCompatActivity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding3);

     //   findViewById(R.id.textView).setOnClickListener(v -> startActivity(new Intent(Onboarding_3.this, LanguageSelect.class)));

        findViewById(R.id.button).setOnClickListener(v -> startActivity(new Intent(Onboarding_3.this, LanguageSelect.class)));
    }
}
