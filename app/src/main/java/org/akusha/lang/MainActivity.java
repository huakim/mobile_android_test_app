package org.akusha.lang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonGuessTheAnimal).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, GuessTheAnimal.class)));

        findViewById(R.id.buttonWordPractice).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, WordPractice.class)));


        findViewById(R.id.imageAvatar).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Profile.class)));
    }
}
