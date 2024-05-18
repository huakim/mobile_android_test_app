package org.akusha.lang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;

public class GuessSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_success);

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v ->
                startActivity(new Intent(GuessSuccess.this, MainActivity.class))
        );
    }
}
