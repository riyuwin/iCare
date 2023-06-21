package com.example.icare.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.icare.R;
import com.example.icare.Adapters.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_up_2 extends AppCompatActivity {

    private RadioButton radioButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        RadioGroup RadioBtnGroup = findViewById(R.id.identity_radiogroup);
        Button Confirm_btn = findViewById(R.id.identity_next);

        //getting the credentials by the user
        Intent Get_Credentials = getIntent();
        String fullname = Get_Credentials.getStringExtra("FULLNAME");
        String email = Get_Credentials.getStringExtra("EMAIL");
        String phone = Get_Credentials.getStringExtra("PHONE");
        String password = Get_Credentials.getStringExtra("PASSWORD");

        mAuth = FirebaseAuth.getInstance();

        Confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectid = RadioBtnGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectid);

                String getText_radio = (String) radioButton.getText();

                if (getText_radio.equals("Doctor")) {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Sign_up_2.this, "User registered successfully", Toast.LENGTH_SHORT).show();

                                //
                                User user = new User(fullname, email, phone, getText_radio);

                                FirebaseDatabase.getInstance().getReference("Doctor Accounts")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(Sign_up_2.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                                    startActivity(new Intent(Sign_up_2.this, Login.class));
                                                } else {

                                                }
                                            }
                                        });//

                            } else {
                                Toast.makeText(Sign_up_2.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                } else {
                 ///////////////////////////////////////////////////////////////////////////

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Sign_up_2.this, "User registered successfully", Toast.LENGTH_SHORT).show();

                                //
                                User user = new User(fullname, email, phone, getText_radio);

                                FirebaseDatabase.getInstance().getReference("Patient Accounts")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(Sign_up_2.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                                    startActivity(new Intent(Sign_up_2.this, Login.class));
                                                } else {

                                                }
                                            }
                                        });//

                            } else {
                                Toast.makeText(Sign_up_2.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                    ///////////////////////////////////////////////////////////////////////////
                }
            }

        });

    }
}