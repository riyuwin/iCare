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

import com.example.icare.Fragment.HomeFragment;
import com.example.icare.Fragment.Landing_Page;
import com.example.icare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

public class Setting_Appointment extends AppCompatActivity {

    EditText datetxt, timetxt;
    Button setAppointmentBtn, setdate_btn, settime_btn;

    DatePickerDialog.OnDateSetListener setListener;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_appointment);

        datetxt = (EditText) findViewById(R.id.dr_setdate_appointment);
        timetxt = (EditText) findViewById(R.id.dr_settime_appointment);
        setdate_btn = (Button) findViewById(R.id.setdate_btn);
        settime_btn = (Button) findViewById(R.id.settime_btn);
        setAppointmentBtn = (Button) findViewById(R.id.dr_appoint_now_btn);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        // start of date picker -------

        datetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Setting_Appointment.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year + "-" + month + "-" + dayOfMonth;
                datetxt.setText(date);
            }
        };

        setdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Setting_Appointment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = year + "-" + month + "-" + dayOfMonth;
                        datetxt.setText(date);
                    }
                }, year, month, day);
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(Setting_Appointment.this,
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(Setting_Appointment.this,
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

        setAppointmentBtn.setOnClickListener((v) -> setAppointment());

    }

    void setAppointment(){
        String appointment_date = datetxt.getText().toString().trim();
        String appointment_time = timetxt.getText().toString().trim();

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


        Intent Get_Credentials = getIntent();
        String dr_specialty = Get_Credentials.getStringExtra("DOCTOR_SPECIALTY");

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();


            // ----- PANG READ NG DATA MULA SA USERS TAPOS NAGCALL OUT NG FUNCTION PARA MASAVE SA IBANG DOCUMENT
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference sourceDocRef = db.collection("users").document(userId);

            sourceDocRef.get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                // Retrieve the data from the source document
                                String name = documentSnapshot.getString("name");
                                String email = documentSnapshot.getString("email");
                                String identity = documentSnapshot.getString("identity");

                                // Call a method or perform any necessary operations to store the data in another document
                                storeDataInAnotherDocument(dr_specialty, name, email, identity, appointment_date, appointment_time, userId);
                            } else {
                                // The source document does not exist
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error retrieving data from the source document
                        }
                    });


        } else {
            // User is not currently logged in
        }

    }

    private void storeDataInAnotherDocument(String db_collectionRef, String name, String email, String identity, String appointment_date, String appointment_time, String userId) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = db.collection(db_collectionRef);

        Map<String, Object> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("email", email);
        userData.put("identity", identity);
        userData.put("status", "Available");
        userData.put("appointmentdate", appointment_date);
        userData.put("appointmenttime", appointment_time);

        String documentId = collectionRef.document().getId(); // Generate a unique ID

        userData.put("appointmentID", documentId);

        collectionRef.document(documentId)
                .set(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Data successfully stored in Firestore with the specified document ID
                        Toast.makeText(Setting_Appointment.this, "Recorded successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Setting_Appointment.this, DoctorListAppointments.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Setting_Appointment.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}