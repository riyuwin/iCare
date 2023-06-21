package com.example.icare.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.icare.Patient_Appointment.Patient_Appointment;
import com.example.icare.R;

public class Neurologist extends AppCompatActivity implements View.OnClickListener {

    public CardView neurocard1, neurocard2, neurocard3, neurocard4, neurocard5, neurocard6;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neurologist);

        neurocard1 = (CardView) findViewById(R.id.neuroc1);
        neurocard2 = (CardView) findViewById(R.id.neuroc2);
        neurocard3 = (CardView) findViewById(R.id.neuroc3);
        neurocard4 = (CardView) findViewById(R.id.neuroc4);
        neurocard5 = (CardView) findViewById(R.id.neuroc5);
        neurocard6 = (CardView) findViewById(R.id.neuroc6);

        neurocard1.setOnClickListener(this);
        neurocard2.setOnClickListener(this);
        neurocard3.setOnClickListener(this);
        neurocard4.setOnClickListener(this);
        neurocard5.setOnClickListener(this);
        neurocard6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.neuroc1:

            case R.id.neuroc2:

            case R.id.neuroc3:

            case R.id.neuroc4:

            case R.id.neuroc5:

            case R.id.neuroc6:
                i = new Intent(this, Patient_Appointment.class);
                startActivity(i);
                break;
        }

    }
}