package com.example.icare.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.icare.Adapters.Set_Appointment_Doctor;
import com.example.icare.Doctor_Appointment.DoctorSetAppointment;
import com.example.icare.Doctor_Appointment.Doctor_Appointment;
import com.example.icare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoctorDets extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dets);

        TextView DoctorName = (TextView) findViewById(R.id.drname);
        TextView DoctorSpecialty = (TextView) findViewById(R.id.dr_specialty);

        // --------------------------PANG KUHA NG DATA SA REALTIME DATABASE------------------------------------------------------------------

        // Get a reference to the database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

        // Attach a listener to a specific location in the database
        databaseRef.child("Doctor Accounts").child("33wwfNbORfaxUp7E8TU2gN4fISm2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Handle the retrieved data
                if (dataSnapshot.exists()) {
                    String fullname = dataSnapshot.child("fullname").getValue(String.class);
                    String identity = dataSnapshot.child("identity").getValue(String.class);

                    DoctorName.setText(fullname);
                    DoctorSpecialty.setText(identity);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors
                //Log.e("Firebase", "Error: " + databaseError.getMessage());
                Toast.makeText(DoctorDets.this, "Firebase" + "Error: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        // --------------------------PANG KUHA NG DATA SA REALTIME DATABASE------------------------------------------------------------------



    }
}