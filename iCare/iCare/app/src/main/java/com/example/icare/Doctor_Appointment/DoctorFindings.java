package com.example.icare.Doctor_Appointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.icare.Fragment.DoctorFragment;
import com.example.icare.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DoctorFindings extends AppCompatActivity {

    EditText doctor_findings, doctor_recommendation;

    Button confirm_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_findings);

        doctor_findings = findViewById(R.id.findings_edittext);
        doctor_recommendation = findViewById(R.id.recommendation_edittext);

        confirm_button = findViewById(R.id.confirm_btn);

        confirm_button.setOnClickListener(view -> {
            Intent Get_Credentials = getIntent();
            String dr_name = Get_Credentials.getStringExtra("DOCTOR_NAME");
            String patient_email = Get_Credentials.getStringExtra("PATIENT_EMAIL");
            String patient_name = Get_Credentials.getStringExtra("PATIENT_NAME");
            String appointmentid = Get_Credentials.getStringExtra("APPOINTMENTID");

            String dr_findings = doctor_findings.getText().toString();
            String dr_recommendation = doctor_recommendation.getText().toString();

            save_findings(dr_name, dr_findings, dr_recommendation, patient_email, patient_name, appointmentid);
        });

    }
    public void save_findings(String dr_name, String dr_findings, String dr_recommendation, String patient_email,
                              String patient_name, String appointmentid) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> data = new HashMap<>();
        data.put("doctor_name", dr_name);
        data.put("doctor_findings", dr_findings);
        data.put("doctor_recommendation", dr_recommendation);
        data.put("patient_email", patient_email);
        data.put("patient_name", patient_name);
        data.put("appointment_id", appointmentid);

        DocumentReference findingsDocument = db.collection("Doctor Findings").document(appointmentid);
        findingsDocument
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(DoctorFindings.this, "Records successfully saved.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DoctorFindings.this, DoctorFragment.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Firestore", "Error adding document: " + e.getMessage());
                    }
                });
    }

}
