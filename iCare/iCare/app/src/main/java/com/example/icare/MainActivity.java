package com.example.icare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.icare.Authentication.Login;
import com.example.icare.Fragment.Landing_Page;
import com.example.icare.utilities.Constants;
import com.example.icare.utilities.PreferenceManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferenceManager = new PreferenceManager(getApplicationContext()); // Initialize preferenceManager

        Toast.makeText(this, "Firebase connection Success", Toast.LENGTH_LONG).show();

        Date currentDate = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = dateFormat.format(currentDate);

        Toast.makeText(this, dateString, Toast.LENGTH_LONG).show();
    }

    public void LaunchAuthenticationMethod(View view) {
        if (preferenceManager.getBoolean(Constants.KEY_IS_SIGNED_IN)) {
            Intent intent = new Intent(this, Landing_Page.class);
            startActivity(intent);
        } else {
            // User is not signed in, perform the authentication process
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
    }

}