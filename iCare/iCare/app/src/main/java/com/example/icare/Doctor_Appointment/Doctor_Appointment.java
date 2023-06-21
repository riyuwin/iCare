package com.example.icare.Doctor_Appointment;

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

import com.example.icare.Adapters.Set_Appointment_Doctor;
import com.example.icare.Adapters.Appointment;
import com.example.icare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Doctor_Appointment extends AppCompatActivity {

    EditText datetxt, timetxt;
    Button setAppointmentBtn, setdate_btn, settime_btn;

    FirebaseAuth mAuth;

    DatePickerDialog.OnDateSetListener setListener;

    ProgressDialog pd;

    //Firestore instance
    FirebaseFirestore FirebaseDb;

    DatabaseReference reff;

    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment);

        datetxt = (EditText) findViewById(R.id.dr_setdate_appointment);
        timetxt = (EditText) findViewById(R.id.dr_settime_appointment);
        setdate_btn = (Button) findViewById(R.id.setdate_btn);
        settime_btn = (Button) findViewById(R.id.settime_btn);
        setAppointmentBtn = (Button) findViewById(R.id.dr_appoint_now_btn);

        mAuth = FirebaseAuth.getInstance();

        //progress dialog
        pd = new ProgressDialog(this);

        //firestore
        FirebaseDb = FirebaseFirestore.getInstance();

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

        reff = FirebaseDatabase.getInstance().getReference("Doctor Appointments").child("Cardiologist");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid=(snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        // start of date picker -------

        datetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Doctor_Appointment.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = year+"-"+month+"-"+dayOfMonth;
                datetxt.setText(date);
            }
        };

        setdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Doctor_Appointment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date = year+"-"+month+"-"+dayOfMonth;
                        datetxt.setText(date);
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(Doctor_Appointment.this,
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(Doctor_Appointment.this,
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

        setAppointmentBtn.setOnClickListener( (v)-> setAppointment());


    }

    void setAppointment(){
        String appointment_date = datetxt.getText().toString().trim();
        String appointment_time = timetxt.getText().toString().trim();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail = user.getEmail();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();


        if(appointment_date==null || appointment_date.isEmpty()){
            datetxt.setError("Appointment date is required.");
            return;
        } else if (appointment_time==null || appointment_time.isEmpty()){
            timetxt.setError("Appointment time is required.");
            return;
        } else {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date selectedDate = null;
            Date currentDate = new Date();

            try {
                selectedDate = dateFormat.parse(appointment_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (selectedDate != null && selectedDate.before(currentDate)) {
                datetxt.setError("Sorry, the date is not valid.");
                return;
            }
        }

        // --------------------------PANG KUHA NG DATA SA REALTIME DATABASE------------------------------------------------------------------

        String status = "Available";
        long userId = maxid + 1;

        // Get a reference to the database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

        // Attach a listener to a specific location in the database
        databaseRef.child("Doctor Accounts").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Handle the retrieved data
                if (dataSnapshot.exists()) {
                    String fullname = dataSnapshot.child("fullname").getValue(String.class);

                    Toast.makeText(Doctor_Appointment.this, fullname, Toast.LENGTH_LONG).show();

                    Set_Appointment_Doctor Doctor_Appointment = new Set_Appointment_Doctor(fullname, appointment_date, appointment_time, status, maxid+1);
                    reff.child(String.valueOf(maxid+1))
                            .setValue(Doctor_Appointment).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Doctor_Appointment.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(Doctor_Appointment.this, DoctorSetAppointment.class));
                                    } else {

                                    }
                                }
                            });//

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors
                //Log.e("Firebase", "Error: " + databaseError.getMessage());
                Toast.makeText(Doctor_Appointment.this, "Firebase" + "Error: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        // --------------------------PANG KUHA NG DATA SA REALTIME DATABASE------------------------------------------------------------------


        /*
        Appointment appointment = new Appointment();
        appointment.setEmail(userEmail);
        appointment.setAppointment_date(appointment_date);
        appointment.setAppointment_time(appointment_time);
        appointment.setTimestamp(Timestamp.now());

        saveAppointmentToFirebase(appointment);
        */
    }

    void saveAppointmentToFirebase(Appointment appointment){


        //FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        //FirebaseFirestore.getInstance().collection("Appointment").document(currentUser.getUid()).collection("my_appointment");

        //addd this data
        /*FirebaseDb.collection("Appointment").document(currentUser.getUid()).collection("my_appointment").add(appointment)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Doctor_Appointment.this, "Firebase connection Success", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
        */

    }

}