package com.example.icare.Patient_Appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.icare.Fragment.Landing_Page;
import com.example.icare.R;

public class Patient_Appointment_4 extends AppCompatActivity {

    TextView app_name, appointment_date;

    LinearLayout linearLayout_appointment_completed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointment4);

        Intent Get_Credentials = getIntent();
        String date = Get_Credentials.getStringExtra("APPOINTMENTDATE");


        appointment_date = findViewById(R.id.patient_appointment_date);
        appointment_date.setText(date);

        app_name = findViewById(R.id.iCare_Text);
        linearLayout_appointment_completed = findViewById(R.id.appointment_completed);

        linearLayout_appointment_completed.setOnClickListener(view -> {
            Intent intent = new Intent (this, Landing_Page.class);
            startActivity(intent);
        });



    }


}