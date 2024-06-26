package org.akusha.lang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;

public class GuessError extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_error);

        Button nextButton = findViewById(R.id.nextButton);
        Button tryAgainButton = findViewById(R.id.tryAgainButton);

        nextButton.setOnClickListener(v -> startActivity(new Intent(GuessError.this, MainActivity.class)));

        tryAgainButton.setOnClickListener(v -> startActivity(new Intent(GuessError.this, GuessTheAnimal.class)));
    }
}
