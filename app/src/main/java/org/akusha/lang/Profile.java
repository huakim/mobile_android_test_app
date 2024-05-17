package org.akusha.lang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView text = ((TextView)findViewById(R.id.textView21));
        String str = text.getText().toString();
        text.setText(Login.value+", "+str);

        findViewById(R.id.changeLangButton).setOnClickListener(v -> startActivity(new Intent(Profile.this, LanguageSelect.class)));
    }
}
