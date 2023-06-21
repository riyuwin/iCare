package com.example.icare.Patient_Appointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.icare.Adapters.Set_Appointment_Doctor;
import com.example.icare.Doctor_Appointment.DoctorSetAppointment;
import com.example.icare.Doctor_Appointment.Doctor_Appointment;
import com.example.icare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Patient_Appointment_Step2 extends AppCompatActivity {


    ProgressDialog pd;

    //Firestore instance
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointment_step2);


        //progress dialog
        pd = new ProgressDialog(this);

        //firestore
        db = FirebaseFirestore.getInstance();

        Intent Get_Credentials = getIntent();
        String doc_userid = Get_Credentials.getStringExtra("DOCTOR_ID");
        String doc_fullname = Get_Credentials.getStringExtra("DOCTOR_FULLNAME");
        String doc_email = Get_Credentials.getStringExtra("DOCTOR_EMAIL");
        String doc_appointmentdate = Get_Credentials.getStringExtra("DOCTOR_APPOINTMENTDATE");
        String doc_appointmenttime = Get_Credentials.getStringExtra("DOCTOR_APPOINTMENTTIME");
        String doc_status = Get_Credentials.getStringExtra("DOCTOR_STATUS");
        String fullname = Get_Credentials.getStringExtra("FULLNAME");
        String gender = Get_Credentials.getStringExtra("GENDER");
        String age = Get_Credentials.getStringExtra("AGE");
        String phone = Get_Credentials.getStringExtra("PHONE");
        String bdate = Get_Credentials.getStringExtra("BIRTHDATE");
        String address = Get_Credentials.getStringExtra("ADDRESS");
        String date = Get_Credentials.getStringExtra("APPOINTMENTDATE");
        String time = Get_Credentials.getStringExtra("APPOINTMENTTIME");
        String status = Get_Credentials.getStringExtra("STATUS");
        String appointmentid = Get_Credentials.getStringExtra("APPOINTMENTID");
        String identity = Get_Credentials.getStringExtra("DOCTOR_IDENTITY");
        String dr_specialty = Get_Credentials.getStringExtra("DOCTOR_APPOINTMENT_NAME");

        EditText Message = (EditText) findViewById(R.id.messageEditText);
        Button AppointNow = (Button) findViewById(R.id.appoint_now_btn);

        AppointNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String User_Message = Message.getText().toString();

                uploadData(fullname, gender, age, phone, bdate, address, doc_appointmentdate, doc_appointmenttime, doc_fullname, appointmentid, identity, dr_specialty);
            }
        });
    }

    private void uploadData(String fullname, String gender, String age, String phone,
                            String bdate, String address, String appointment_date,
                            String appointment_time, String doc_fullname, String appointmentid,
                            String identity, String dr_specialty) {
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
        doc_appointment.put("DOCTORNAME", doc_fullname);
        doc_appointment.put("IDENTITY", identity);
        doc_appointment.put("APPOINTMENTID", appointmentid);

        //addd this data
        db.collection("Patient Appointment").document(appointmentid).set(doc_appointment)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data is added successfully
                        pd.dismiss();
                        Toast.makeText(Patient_Appointment_Step2.this, "Added Successfully...", Toast.LENGTH_SHORT).show();


                        // --------------------------PANG KUHA NG DATA SA REALTIME DATABASE------------------------------------------------------------------
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        DocumentReference docRef = db.collection(dr_specialty).document(appointmentid);

                        // Create a map with the fields you want to update
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("status", "OCCUPIED");

                        docRef.update(updates)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Data successfully updated in Firestore
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
                                        // Handle the failure
                                        Toast.makeText(Patient_Appointment_Step2.this, "Error while saving...", Toast.LENGTH_SHORT).show();
                                    }
                                });

                        /*
                        // Get a reference to the database
                        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

                        // Define the path to the "status" field within the "Doctor Appointments" child node
                        DatabaseReference statusRef = databaseRef.child("Cardiologist Appointment").child("Cardiologist")
                                .child(doc_userid).child("status");

                        // Update the "status" value
                        statusRef.setValue("OCCUPIED");
                        */
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called when there is any error while uploading
                        Toast.makeText(Patient_Appointment_Step2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

}