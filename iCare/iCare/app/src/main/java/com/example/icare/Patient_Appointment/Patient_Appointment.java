package com.example.icare.Patient_Appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.icare.Patient_Appointment.Patient_Appointment_2;
import com.example.icare.R;

public class Patient_Appointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointment);

    }

    public void LaunchSetAppointment(View view){


        Intent Get_Credentials = getIntent();
        String appointmentdate = Get_Credentials.getStringExtra("DOCTOR_APPOINTMENTDATE");
        String appointmenttime = Get_Credentials.getStringExtra("DOCTOR_APPOINTMENTTIME");
        String email = Get_Credentials.getStringExtra("DOCTOR_EMAIL");
        String identity = Get_Credentials.getStringExtra("DOCTOR_IDENTITY");
        String fullname = Get_Credentials.getStringExtra("DOCTOR_NAME");
        String status = Get_Credentials.getStringExtra("STATUS");
        String appointmentid = Get_Credentials.getStringExtra("APPOINTMENTID");
        String dr_specialty = Get_Credentials.getStringExtra("DOCTOR_APPOINTMENT_NAME");

        Intent Credentials_Intent = new Intent(getApplicationContext(), Patient_Appointment_2.class);
        Credentials_Intent.putExtra("DOCTOR_APPOINTMENTDATE", appointmentdate);
        Credentials_Intent.putExtra("DOCTOR_APPOINTMENTTIME", appointmenttime);
        Credentials_Intent.putExtra("DOCTOR_EMAIL", email);
        Credentials_Intent.putExtra("DOCTOR_IDENTITY", identity);
        Credentials_Intent.putExtra("DOCTOR_NAME", fullname);
        Credentials_Intent.putExtra("STATUS", status);
        Credentials_Intent.putExtra("APPOINTMENTID", appointmentid);
        Credentials_Intent.putExtra("DOCTOR_APPOINTMENT_NAME", dr_specialty);
        startActivity(Credentials_Intent);
    }
}