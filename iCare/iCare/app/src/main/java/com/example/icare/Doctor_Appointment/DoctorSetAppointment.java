package com.example.icare.Doctor_Appointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.icare.Adapters.Appointment;
import com.example.icare.Adapters.AppointmentSelectListener;
import com.example.icare.Adapters.User;
import com.example.icare.Patient_Appointment.Patient_Appointment;
import com.example.icare.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorSetAppointment extends AppCompatActivity implements AppointmentSelectListener {

    FloatingActionButton addAppointmentBtn;

    ImageButton menuBtn;

    //--------------
    RecyclerView recyclerView;

    DatabaseReference database;

    User.DoctorAppointmentAdapter doctorAppointmentAdapter;

    ArrayList<Appointment> list;
    //--------------
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_set_appointment);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching data...");
        progressDialog.show();
        mAuth = FirebaseAuth.getInstance();

        addAppointmentBtn = findViewById(R.id.add_appointment_btn);
        recyclerView = findViewById(R.id.recycler_view);
        menuBtn = findViewById(R.id.menu_btn);

        database = FirebaseDatabase.getInstance().getReference("Doctor Appointments").child("Cardiologist");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        doctorAppointmentAdapter = new User.DoctorAppointmentAdapter(this, list, this);
        recyclerView.setAdapter(doctorAppointmentAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear(); // Clear the list before adding new data

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Appointment appointment = dataSnapshot.getValue(Appointment.class);

                    // Check if the status is "Available"
                    if (appointment != null && appointment.getStatus().equals("Available")) {
                        list.add(appointment);
                    }
                }
                doctorAppointmentAdapter.notifyDataSetChanged();

                if (progressDialog.isShowing())
                    progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //intent
        addAppointmentBtn.setOnClickListener((v) -> startActivity(new Intent(DoctorSetAppointment.this, Doctor_Appointment.class)));
        menuBtn.setOnClickListener((v)->showMenu() );
    }
    void showMenu(){
        //TODO Display menu
    }

    void setupRecyclerView(){

    }

    @Override
    public void onItemClicked(Appointment user) {

        Toast.makeText(this, user.getUserId(), Toast.LENGTH_SHORT).show();

        Intent Credentials_Intent = new Intent(getApplicationContext(), Patient_Appointment.class);
        Credentials_Intent.putExtra("DOCTOR_ID", user.getUserId());
        Credentials_Intent.putExtra("DOCTOR_FULLNAME", user.getFullname());
        Credentials_Intent.putExtra("DOCTOR_EMAIL", user.getEmail());
        Credentials_Intent.putExtra("DOCTOR_APPOINTMENTDATE", user.getSetappointmentdate());
        Credentials_Intent.putExtra("DOCTOR_APPOINTMENTTIME", user.getSetappointmenttime());
        Credentials_Intent.putExtra("DOCTOR_STATUS", user.getStatus());
        startActivity(Credentials_Intent);

    }
}