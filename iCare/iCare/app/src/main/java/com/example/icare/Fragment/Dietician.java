package com.example.icare.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.icare.Patient_Appointment.Patient_Appointment;
import com.example.icare.R;

public class Dietician extends AppCompatActivity implements View.OnClickListener {

    public CardView dieticiancard1, dieticiancard2, dieticiancard3, dieticiancard4, dieticiancard5, dieticiancard6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietician);

        dieticiancard1 = (CardView) findViewById(R.id.dieticianc1);
        dieticiancard2 = (CardView) findViewById(R.id.dieticianc2);
        dieticiancard3 = (CardView) findViewById(R.id.dieticianc3);
        dieticiancard4 = (CardView) findViewById(R.id.dieticianc4);
        dieticiancard5 = (CardView) findViewById(R.id.dieticianc5);
        dieticiancard6 = (CardView) findViewById(R.id.dieticianc6);

        dieticiancard1.setOnClickListener(this);
        dieticiancard2.setOnClickListener(this);
        dieticiancard3.setOnClickListener(this);
        dieticiancard4.setOnClickListener(this);
        dieticiancard5.setOnClickListener(this);
        dieticiancard6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.dieticianc1:
                i = new Intent(this, DoctorDets.class);
                startActivity(i);
                break;

            case R.id.dieticianc2:
                i = new Intent(this, DoctorDets.class);
                startActivity(i);
                break;

            case R.id.dieticianc3:

            case R.id.dieticianc4:

            case R.id.dieticianc5:

            case R.id.dieticianc6:
                i = new Intent(this, Patient_Appointment.class);
                startActivity(i);
                break;
        }

    }
}