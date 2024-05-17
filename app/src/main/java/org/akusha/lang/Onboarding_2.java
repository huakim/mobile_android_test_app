package org.akusha.lang;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
public class Onboarding_2 extends AppCompatActivity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding2);

        findViewById(R.id.textView).setOnClickListener(v -> startActivity(new Intent(Onboarding_2.this, LanguageSelect.class)));

        findViewById(R.id.button).setOnClickListener(v -> startActivity(new Intent(Onboarding_2.this, Onboarding_3.class)));
    }
}
