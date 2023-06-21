package com.example.icare.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.icare.R;

public class Doctor_Signup_Specialty extends AppCompatActivity {

    RadioGroup RadioBtnGroup;
    Button Confirm_btn;

    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup_specialty);


        RadioBtnGroup = findViewById(R.id.identity_radiogroup);
        Confirm_btn = findViewById(R.id.confirm_btn);


        Confirm_btn.setOnClickListener(view -> {
            int selectid = RadioBtnGroup.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            radioButton = (RadioButton) findViewById(selectid);
            String getText_radio = (String) radioButton.getText();

            if (!radioButton.isChecked()) {
                Toast.makeText(this, "Specialty is required", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getText_radio, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Doctor_Signup_Specialty.this, Doctor_Signup.class);
                intent.putExtra("SPECIALTY", getText_radio);
                startActivity(intent);
            }

        });

    }
}