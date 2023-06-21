package com.example.icare.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.icare.Patient_Appointment.Patient_Appointment;
import com.example.icare.R;

public class Pediatrician extends AppCompatActivity implements View.OnClickListener {

    public CardView pediacard1, pediacard2, pediacard3, pediacard4, pediacard5, pediacard6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pediatrician);

        pediacard1 = (CardView) findViewById(R.id.pediac1);
        pediacard2 = (CardView) findViewById(R.id.pediac2);
        pediacard3 = (CardView) findViewById(R.id.pediac3);
        pediacard4 = (CardView) findViewById(R.id.pediac4);
        pediacard5 = (CardView) findViewById(R.id.pediac5);
        pediacard6 = (CardView) findViewById(R.id.pediac6);

        pediacard1.setOnClickListener(this);
        pediacard2.setOnClickListener(this);
        pediacard3.setOnClickListener(this);
        pediacard4.setOnClickListener(this);
        pediacard5.setOnClickListener(this);
        pediacard6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.pediac1:

            case R.id.pediac2:

            case R.id.pediac3:

            case R.id.pediac4:

            case R.id.pediac5:

            case R.id.pediac6:
                i = new Intent(this, Patient_Appointment.class);
                startActivity(i);
                break;
        }

    }
}