package org.akusha.lang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.*;
public class WordPractice extends AppCompatActivity {

    String [] rus = new String[]{
            "слива",
            "банан",
            "апельсин",
            "груша" ,
            "торт" ,
            "клубника" ,
            "суп",
            "рыба"
    };

    String [] eng = new String[]{
            "plum" ,
            "banana" ,
            "orange",
            "pear" ,
            "cake",
            "strawberry",
            "soup",
            "fish"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_practice);

        ArrayList<Integer> list = new ArrayList<Integer>();
        list.ensureCapacity(eng.length);
        for (int i=0; i<eng.length; i++) list.add(i);
        Collections.shuffle(list);

        int random_right = list.get(0);
        int [] random_options = new int[]{
                list.get(1),
                list.get(2),
                list.get(3)
        };
        int random_right_index = new Random().nextInt(random_options.length);

        random_options[random_right_index] = random_right;

        Locale currentLoc = getResources().getConfiguration().getLocales().get(0);

        String []localstr ;
        String []localtransl ;

        if (currentLoc.toLanguageTag().equals("ru")){
            localstr = eng;
            localtransl = rus;
        } else {
            localstr = rus;
            localtransl = eng;
        }

        ((TextView)findViewById(R.id.text_guess)).setText(localstr[random_right]);

        Button button1 = ((Button)findViewById(R.id.button_1));
        button1.setText(localtransl[random_options[0]]);
        Button button2 = ((Button)findViewById(R.id.button_2));
        button2.setText(localtransl[random_options[1]]);
        Button button3 = ((Button)findViewById(R.id.button_3));
        button3.setText(localtransl[random_options[2]]);

        button1.setOnClickListener(v -> {
            boolean isracoon = 0 == random_right_index;
            startActivity(new Intent(
                    WordPractice.this,
                    isracoon ?  GuessSuccess.class : GuessError.class));
        });
        button2.setOnClickListener(v -> {
            boolean isracoon = 1 == random_right_index;
            startActivity(new Intent(
                    WordPractice.this,
                    isracoon ?  GuessSuccess.class : GuessError.class));
        });

        button3.setOnClickListener(v -> {
            boolean isracoon = 2 == random_right_index;
            startActivity(new Intent(
                    WordPractice.this,
                    isracoon ?  GuessSuccess.class : GuessError.class));
        });


    }
}
