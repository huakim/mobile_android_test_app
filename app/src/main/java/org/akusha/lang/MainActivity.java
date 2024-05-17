package org.akusha.lang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonGuessTheAnimal).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, GuessTheAnimal.class)));

        findViewById(R.id.buttonWordPractice).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, WordPractice.class)));

        android.net.Uri image = Profile.imageUri;
        if (image != null){
            ((ImageView) findViewById(R.id.imageAvatar)).setImageURI(image);
        }

        findViewById(R.id.imageAvatar).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Profile.class)));
    }
}
