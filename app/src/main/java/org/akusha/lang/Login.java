package org.akusha.lang;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Login extends AppCompatActivity {
    public static String value = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.textViewSignup).setOnClickListener(v -> startActivity(new Intent(Login.this, Signup_1.class)));

        findViewById(R.id.choose_button).setOnClickListener((View.OnClickListener) v -> {
            String value = ((EditText)findViewById(R.id.editTextEmail)).getText().toString();
            Login.value = value;
            startActivity(new Intent(Login.this, MainActivity.class));
        });

        findViewById(R.id.forgotPassText).setOnClickListener(v -> startActivity(new Intent(Login.this, Signup_2.class)));
    }
    public void onBackButtonClicked(View view) {
        onBackPressed();

    }
}
