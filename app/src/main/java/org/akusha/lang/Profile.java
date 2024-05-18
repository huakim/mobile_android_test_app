package org.akusha.lang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Profile extends AppCompatActivity {

    private static final int REQUEST_CODE_PICK_IMAGE = 123;
   // private static final int PERMISSION_REQUEST_CODE = 456;

    private void pickImage() {
        // Check if the app has permission to access external storage

            // Launch the image picker
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }
    public static Uri imageUri = null;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            // Get the selected image URI
            Uri imageUri = data.getData();
            Profile.imageUri = imageUri;
            // Do something with the selected image, e.g., display it in an ImageView
            ((ImageView)findViewById(R.id.imageAvatar2)).setImageURI(imageUri);
        }
    }
    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        TextView text = ((TextView)findViewById(R.id.textView21));
        String str = text.getText().toString();
        text.setText(Login.value+", "+str);

        Button themebutton = this.<Button>findViewById(R.id.themeButton);
        themebutton.setText(SplashScreen.getDark()? R.string.light: R.string.dark);

        themebutton.setOnClickListener(
                v -> {
                    boolean dark = ! SplashScreen.getDark();
                    themebutton.setText(dark ? R.string.light : R.string.dark);
                    SplashScreen.setDark(dark);
                    SplashScreen.setDarkContext(this, dark);
                }
        );

        android.net.Uri image = Profile.imageUri;
        if (image != null){
            ((ImageView) findViewById(R.id.imageAvatar2)).setImageURI(image);
        }

        findViewById(R.id.saveButton).setOnClickListener(
                v ->
                    startActivity(new Intent(Profile.this, MainActivity.class))

        );

        findViewById(R.id.choose_button5).setOnClickListener(
                v ->
                    startActivity(new Intent(Profile.this, Login.class))

        );

        findViewById(R.id.changeImageButton).setOnClickListener(
                v ->
                    pickImage()

        );

        findViewById(R.id.changeLangButton).setOnClickListener(
                v ->
                    startActivity(new Intent(Profile.this, LanguageSelect.class))

        );
    }
}
