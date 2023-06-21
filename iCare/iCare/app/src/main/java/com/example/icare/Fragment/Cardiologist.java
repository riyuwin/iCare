package com.example.icare.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.icare.Doctor_Appointment.DoctorSetAppointment;
import com.example.icare.R;

public class Cardiologist extends AppCompatActivity implements View.OnClickListener {

    public CardView cardiocard1, cardiocard2, cardiocard3, cardiocard4, cardiocard5, cardiocard6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardiologist);

        cardiocard1 = (CardView) findViewById(R.id.cardioc1);
        cardiocard2 = (CardView) findViewById(R.id.cardioc2);
        cardiocard3 = (CardView) findViewById(R.id.cardioc3);
        cardiocard4 = (CardView) findViewById(R.id.cardioc4);
        cardiocard5 = (CardView) findViewById(R.id.cardioc5);
        cardiocard6 = (CardView) findViewById(R.id.cardioc6);

        cardiocard1.setOnClickListener(this);
        cardiocard2.setOnClickListener(this);
        cardiocard3.setOnClickListener(this);
        cardiocard4.setOnClickListener(this);
        cardiocard5.setOnClickListener(this);
        cardiocard6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.cardioc1:

            case R.id.cardioc2:

            case R.id.cardioc3:

            case R.id.cardioc4:

            case R.id.cardioc5:

            case R.id.cardioc6:
                i = new Intent(this, DoctorSetAppointment.class);
                startActivity(i);
                break;
        }

    }
}