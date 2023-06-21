package com.example.icare.Doctor_Appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.icare.Fragment.DoctorFragment;
import com.example.icare.Fragment.Landing_Page;
import com.example.icare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Patient_Findings extends AppCompatActivity {

    Button back_button;

    TextView doctor_name, doctor_findings, doctor_recommendation;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_findings);

        doctor_name = findViewById(R.id.dr_name_text);
        doctor_findings = findViewById(R.id.dr_findings_text);
        doctor_recommendation = findViewById(R.id.recommendation_text);

        back_button = findViewById(R.id.back_btn);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        Intent Get_Credentials = getIntent();
        String appointmentid = Get_Credentials.getStringExtra("APPOINTMENTID");

        if (user != null) {
            String userId = user.getUid();


            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("Patient Appointment")
                    .document(appointmentid)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // Document exists, retrieve the data
                                String DRname = document.getString("DOCTORNAME");
                                String DRfindings = document.getString("doctor_findings");
                                String DRrecommendation = document.getString("doctor_recommendation");

                                doctor_name.setText(DRname);
                                doctor_findings.setText(DRfindings);
                                doctor_recommendation.setText(DRrecommendation);
                            } else {
                                // Document does not exist
                                doctor_name.setText("No records yet");
                                doctor_findings.setText("No records yet");
                                doctor_recommendation.setText("No records yet");
                            }
                        } else {
                            // Handle the error
                            Exception exception = task.getException();
                            System.out.println("Error retrieving data: " + exception.getMessage());
                        }
                    });



        } else {
            // User is not currently authenticated
            System.out.println("User not authenticated");
        }


        back_button.setOnClickListener(view -> {
            Intent intent = new Intent(Patient_Findings.this, Landing_Page.class);
            startActivity(intent);
        });
    }


}