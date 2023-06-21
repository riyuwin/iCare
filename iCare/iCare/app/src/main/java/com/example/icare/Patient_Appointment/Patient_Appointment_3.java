package com.example.icare.Patient_Appointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.icare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class Patient_Appointment_3 extends AppCompatActivity {

    Button appoint_now_button, setdate_btn, settime_btn;

    EditText datetxt, timetxt;

    ProgressDialog pd;

    //Firestore instance
    FirebaseFirestore db;

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointment3);

        Intent Get_Credentials = getIntent();
        String fullname = Get_Credentials.getStringExtra("FULLNAME");
        String gender = Get_Credentials.getStringExtra("GENDER");
        String age = Get_Credentials.getStringExtra("AGE");
        String phone = Get_Credentials.getStringExtra("PHONE");
        String bdate = Get_Credentials.getStringExtra("BIRTHDATE");
        String address = Get_Credentials.getStringExtra("ADDRESS");

        appoint_now_button = (Button) findViewById(R.id.appoint_now_btn);

        datetxt = (EditText) findViewById(R.id.setdate_appointment);
        timetxt = (EditText) findViewById(R.id.settime_appointment);
        setdate_btn = (Button) findViewById(R.id.setdate_btn);
        settime_btn = (Button) findViewById(R.id.settime_btn);

        //progress dialog
        pd = new ProgressDialog(this);

        //firestore
        db = FirebaseFirestore.getInstance();


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        // start of date picker -------

        datetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Patient_Appointment_3.this,
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
                datetxt.setText(date);
                Toast.makeText(Patient_Appointment_3.this, date,
                        Toast.LENGTH_LONG).show();

            }
        };

        setdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Patient_Appointment_3.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        datetxt.setText(date);
                        Toast.makeText(Patient_Appointment_3.this, date,
                                Toast.LENGTH_LONG).show();
                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });

        // end of date picker -------

        // time picker ------
        timetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                int mins = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(Patient_Appointment_3.this,
                        androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE, minute);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
                        String time = format.format(c.getTime());
                        timetxt.setText(time);
                    }
                }, hours, mins, false);
                timePickerDialog.show();
            }
        });

        settime_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                int mins = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(Patient_Appointment_3.this,
                        androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE, minute);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
                        String time = format.format(c.getTime());
                        timetxt.setText(time);
                    }
                }, hours, mins, false);
                timePickerDialog.show();
            }
        });

        // end of time picker --------------


        appoint_now_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // input data
                final String appointment_time = timetxt.getText().toString();
                final String appointment_date = datetxt.getText().toString();

                ///function call to upload data
                uploadData(fullname, gender, age, phone, bdate, address, appointment_date, appointment_time);
            }
        });
    }

    private void uploadData(String fullname, String gender, String age, String phone, String bdate, String address, String appointment_date, String appointment_time) {
        //getting the current user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        assert user != null;
        String userEmail = user.getEmail();

        Map<String, Object> doc_appointment = new HashMap<>();
        doc_appointment.put("ID", userEmail);
        doc_appointment.put("FULLNAME", fullname);
        doc_appointment.put("GENDER", gender);
        doc_appointment.put("AGE", age);
        doc_appointment.put("PHONE", phone);
        doc_appointment.put("BIRTHDATE", bdate);
        doc_appointment.put("ADDRESS", address);
        doc_appointment.put("APPOINTMENTDATE", appointment_date);
        doc_appointment.put("APPOINTMENTTIME", appointment_time);

        //addd this data
        db.collection("Doctor Appointment").document(userEmail).set(doc_appointment)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data is added successfully
                        pd.dismiss();
                        Toast.makeText(Patient_Appointment_3.this, "Added Successfully...", Toast.LENGTH_SHORT).show();


                        Intent Appointment_Details_Intent = new Intent(getApplicationContext(), Patient_Appointment_4.class);
                        Appointment_Details_Intent.putExtra("FULLNAME", fullname);
                        Appointment_Details_Intent.putExtra("GENDER", gender);
                        Appointment_Details_Intent.putExtra("AGE", age);
                        Appointment_Details_Intent.putExtra("PHONE", phone);
                        Appointment_Details_Intent.putExtra("BIRTHDATE", bdate);
                        Appointment_Details_Intent.putExtra("ADDRESS", address);
                        Appointment_Details_Intent.putExtra("APPOINTMENTDATE", appointment_date);
                        Appointment_Details_Intent.putExtra("APPOINTMENTTIME", appointment_time);
                        startActivity(Appointment_Details_Intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called when there is any error while uploading
                        Toast.makeText(Patient_Appointment_3.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}