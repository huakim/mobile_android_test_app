package org.akusha.lang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

public class Signup_1 extends AppCompatActivity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        findViewById(R.id.textViewLogin).setOnClickListener(v -> startActivity(new Intent(Signup_1.this, Login.class)));

        findViewById(R.id.continue_button).setOnClickListener(v -> startActivity(new Intent(Signup_1.this, Signup_2.class)));
    }
}
