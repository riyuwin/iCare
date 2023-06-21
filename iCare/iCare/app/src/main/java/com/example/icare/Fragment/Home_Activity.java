package com.example.icare.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.icare.Authentication.Login;
import com.example.icare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Home_Activity extends AppCompatActivity {

    Button BtnLogOut;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    ImageView logout_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        logout_icon = findViewById(R.id.logout_icon);

        logout_icon.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(Home_Activity.this, Login.class));
            finish(); // Optional: finish the current activity to prevent going back
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(this, Login.class));
            finish(); // Optional: finish the current activity to prevent going back
        }
    }
}
