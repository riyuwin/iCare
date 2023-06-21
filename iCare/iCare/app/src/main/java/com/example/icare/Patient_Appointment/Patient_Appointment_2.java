package com.example.icare.Patient_Appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.icare.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class Patient_Appointment_2 extends AppCompatActivity {

    EditText fullnametxt, phonetxt, bdatetxt, addresstxt, agetxt;

    RadioGroup RadioBtnGroup;

    RadioButton selectedRadioButton;

    String gender;
    Button next_button;

    FirebaseAuth mAuth;

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointment2);

        fullnametxt = (EditText) findViewById(R.id.fullname_appointment);
        agetxt = (EditText) findViewById(R.id.age_appointment);
        phonetxt = (EditText) findViewById(R.id.phone_appointment);
        bdatetxt = (EditText) findViewById(R.id.bdate_appointment);
        addresstxt = (EditText) findViewById(R.id.address_appointment);
        next_button = (Button) findViewById(R.id.next_btn);

        RadioBtnGroup = findViewById(R.id.gender_radiogroup);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        // start of date picker -------

        bdatetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Patient_Appointment_2.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = dayOfMonth+"/"+month+"/"+year;
                bdatetxt.setText(date);
            }
        };

        // end of date picker -------

        next_button.setOnClickListener(view -> {
            ValidateAppointment();
        });

    }

    private void ValidateAppointment(){


        final String fullname = fullnametxt.getText().toString();
        final String age = agetxt.getText().toString();
        final String phone = phonetxt.getText().toString();
        final String bdate = bdatetxt.getText().toString();
        final String address = addresstxt.getText().toString();

        int selectid = RadioBtnGroup.getCheckedRadioButtonId();

        if (TextUtils.isEmpty(fullname)) {
            fullnametxt.setError("Username cannot be empty");
            fullnametxt.requestFocus();
        } else if (TextUtils.isEmpty(age)) {
            agetxt.setError("Age cannot be empty");
            agetxt.requestFocus();
        } else if (TextUtils.isEmpty(phone)) {
            phonetxt.setError("Phone number cannot be empty");
            phonetxt.requestFocus();
        } else if (TextUtils.isEmpty(bdate)) {
            bdatetxt.setError("Birthdate cannot be empty");
            bdatetxt.requestFocus();
        } else if (TextUtils.isEmpty(address)) {
            addresstxt.setError("Address cannot be empty");
            addresstxt.requestFocus();
        } else {

            if (RadioBtnGroup.getCheckedRadioButtonId() == -1)
            {
                // no radio buttons are checked
                Toast.makeText(this, "Gender cannot be empty", Toast.LENGTH_LONG).show();
            } else {
                // find the radiobutton by returned id
                selectedRadioButton = (RadioButton) findViewById(selectid);
                String gender = (String) selectedRadioButton.getText().toString();


                Intent Get_Credentials = getIntent();
                String appointmentdate = Get_Credentials.getStringExtra("DOCTOR_APPOINTMENTDATE");
                String appointmenttime = Get_Credentials.getStringExtra("DOCTOR_APPOINTMENTTIME");
                String email = Get_Credentials.getStringExtra("DOCTOR_EMAIL");
                String identity = Get_Credentials.getStringExtra("DOCTOR_IDENTITY");
                String name = Get_Credentials.getStringExtra("DOCTOR_NAME");
                String status = Get_Credentials.getStringExtra("STATUS");
                String appointmentid = Get_Credentials.getStringExtra("APPOINTMENTID");
                String dr_specialty = Get_Credentials.getStringExtra("DOCTOR_APPOINTMENT_NAME");


                Intent Personal_Details_Intent = new Intent(getApplicationContext(), Patient_Appointment_Step2.class);
                Personal_Details_Intent.putExtra("DOCTOR_FULLNAME", name);
                Personal_Details_Intent.putExtra("DOCTOR_EMAIL", email);
                Personal_Details_Intent.putExtra("DOCTOR_APPOINTMENTDATE", appointmentdate);
                Personal_Details_Intent.putExtra("DOCTOR_APPOINTMENTTIME", appointmenttime);
                Personal_Details_Intent.putExtra("DOCTOR_STATUS", status);
                Personal_Details_Intent.putExtra("DOCTOR_IDENTITY", identity);
                Personal_Details_Intent.putExtra("FULLNAME", fullname);
                Personal_Details_Intent.putExtra("GENDER", gender);
                Personal_Details_Intent.putExtra("AGE", age);
                Personal_Details_Intent.putExtra("PHONE", phone);
                Personal_Details_Intent.putExtra("BIRTHDATE", bdate);
                Personal_Details_Intent.putExtra("ADDRESS", address);
                Personal_Details_Intent.putExtra("APPOINTMENTID", appointmentid);
                Personal_Details_Intent.putExtra("DOCTOR_IDENTITY", identity);
                Personal_Details_Intent.putExtra("DOCTOR_APPOINTMENT_NAME", dr_specialty);
                startActivity(Personal_Details_Intent);
            }
        }
    }

}