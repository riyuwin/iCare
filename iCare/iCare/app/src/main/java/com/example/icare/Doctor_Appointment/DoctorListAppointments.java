package com.example.icare.Doctor_Appointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.icare.Adapter.Appointment_List;
import com.example.icare.Adapter.Appointment_List_Adapter;
import com.example.icare.Adapter.Appointment_List_SelectListener;
import com.example.icare.Adapters.Appointment;
import com.example.icare.Adapters.AppointmentSelectListener;
import com.example.icare.Adapters.User;
import com.example.icare.Patient_Appointment.Patient_Appointment;
import com.example.icare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;

public class DoctorListAppointments extends AppCompatActivity implements Appointment_List_SelectListener {

    RecyclerView recyclerView;
    DatabaseReference database;
    Appointment_List_Adapter myAdapter;
    ArrayList<Appointment_List> list;

    private String dr_specialty;
    ImageButton addAppointmentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list_appointments);

        addAppointmentBtn = findViewById(R.id.add_appointment_btn);

        Intent Get_Credentials = getIntent();
        dr_specialty = Get_Credentials.getStringExtra("DOCTOR_SPECIALTY");

        recyclerView = findViewById(R.id.appointment_recycler_view);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = db.collection(dr_specialty);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new Appointment_List_Adapter(this, list, this);
        recyclerView.setAdapter(myAdapter);

        collectionRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    if (querySnapshot != null) {
                        for (QueryDocumentSnapshot documentSnapshot : querySnapshot) {

                            String status = documentSnapshot.getString("status");

                            if (status.equals("Available")) {
                                Appointment_List appointment = documentSnapshot.toObject(Appointment_List.class);
                                list.add(appointment);
                            }

                        }
                        myAdapter.notifyDataSetChanged();
                    }
                } else {
                    // Handle the task failure
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle the failure
            }
        });

        FirebaseFirestore db1 = FirebaseFirestore.getInstance();
        String currentUserUid = FirebaseAuth.getInstance().getCurrentUser().getUid(); // Get the UID of the current user

        // Construct the Firestore query
        db.collection("users").document(currentUserUid).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // User document exists in Firestore
                            // Retrieve specific data fields from the document
                            String name = documentSnapshot.getString("name");
                            String email = documentSnapshot.getString("email");
                            String identity = documentSnapshot.getString("identity");
                            // ... Retrieve other fields as needed

                            if (identity.equals("Patient")) {
                                addAppointmentBtn.setVisibility(View.GONE);
                            } else if (identity.equals("Doctor")) {
                                addAppointmentBtn.setOnClickListener((v) -> {
                                    Intent intent = new Intent(DoctorListAppointments.this, Setting_Appointment.class);
                                    intent.putExtra("DOCTOR_SPECIALTY", dr_specialty);
                                    startActivity(intent);
                                });
                            } else if (identity.equals("Administrator")){
                                addAppointmentBtn.setOnClickListener((v) -> {
                                    Intent intent = new Intent(DoctorListAppointments.this, Setting_Appointment.class);
                                    intent.putExtra("DOCTOR_SPECIALTY", dr_specialty);
                                    startActivity(intent);
                                });
                            }
                        } else {
                            // User document does not exist in Firestore
                            Log.d("Firestore", "User document does not exist");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle the failure
                        Log.e("Firestore", "Error getting user document: " + e.getMessage());
                    }
                });

        //intent
                //menuBtn.setOnClickListener((v)->showMenu() );

    }

    void showMenu(){
        //TODO Display menu
    }

    void setupRecyclerView(){

    }

    @Override
    public void onItemClicked(Appointment_List appointment) {

        Intent Credentials_Intent = new Intent(getApplicationContext(), Patient_Appointment.class);
        Credentials_Intent.putExtra("DOCTOR_APPOINTMENTDATE", appointment.getAppointmentdate());
        Credentials_Intent.putExtra("DOCTOR_APPOINTMENTTIME", appointment.getAppointmenttime());
        Credentials_Intent.putExtra("DOCTOR_EMAIL", appointment.getEmail());
        Credentials_Intent.putExtra("DOCTOR_IDENTITY", appointment.getIdentity());
        Credentials_Intent.putExtra("DOCTOR_NAME", appointment.getName());
        Credentials_Intent.putExtra("STATUS", appointment.getStatus());
        Credentials_Intent.putExtra("APPOINTMENTID", appointment.getAppointmentID());
        Credentials_Intent.putExtra("DOCTOR_APPOINTMENT_NAME", dr_specialty);
        startActivity(Credentials_Intent);
    }
}