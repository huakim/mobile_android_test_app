package org.akusha.lang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

public class Signup_2 extends AppCompatActivity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        findViewById(R.id.textViewLogin).setOnClickListener(v -> startActivity(new Intent(
                Signup_2.this,
                Login.class)));
    }
}
