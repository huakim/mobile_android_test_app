package org.akusha.lang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import java.util.*;

public class GuessTheAnimal extends AppCompatActivity {
    void setPhoto(int R_drawable_myimage){
        ((android.widget.ImageView)findViewById(R.id.imageView3)).setImageResource(R_drawable_myimage);
    }

    static int [] images = new int[]{R.drawable.hen, R.drawable.dolphin, R.drawable.monkey},
                names = new int[]{R.string.hen, R.string.dolphin, R.string.monkey};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_animal);

        int random = new Random().nextInt(3);
        setPhoto(images[random]);
        String right = getResources().getString(
                names[random]);

        Button buttonGuess = findViewById(R.id.buttonGuess);
        EditText textGuess = findViewById(R.id.editTextGuess);
        buttonGuess.setOnClickListener(v -> {
            boolean isracoon = textGuess.getText().toString().equalsIgnoreCase(right);
            startActivity(new Intent(
                    GuessTheAnimal.this,
                    isracoon ?  GuessSuccess.class : GuessError.class));
        });
    }
}
