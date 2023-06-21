package com.example.icare.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.icare.Adapter.My_Appointment_List;
import com.example.icare.Adapter.My_Appointment_List_Adapter;
import com.example.icare.Adapter.My_Appointment_SelectListener;
import com.example.icare.Doctor_Appointment.DoctorFindings;
import com.example.icare.Doctor_Appointment.Patient_Findings;
import com.example.icare.Doctor_Appointment.Setting_Appointment;
import com.example.icare.Patient_Appointment.Patient_Appointment;
import com.example.icare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class DoctorFragment extends Fragment implements My_Appointment_SelectListener {


    public ImageButton add_appointment;

    ImageButton addAppointmentBtn;

    RecyclerView recyclerView;
    My_Appointment_List_Adapter myAdapter;
    ArrayList<My_Appointment_List> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor, container, false);

        addAppointmentBtn = view.findViewById(R.id.add_appointment_btn);

        recyclerView = view.findViewById(R.id.appointment_recycler_view);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference collectionRef = db.collection("Patient Appointment");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        myAdapter = new My_Appointment_List_Adapter(getContext(), list, this);
        recyclerView.setAdapter(myAdapter);

        collectionRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    if (querySnapshot != null) {
                        for (QueryDocumentSnapshot documentSnapshot : querySnapshot) {

                            String name = documentSnapshot.getString("name");
                            String email = documentSnapshot.getString("email");
                            String identity = documentSnapshot.getString("identity");
                            // ... Retrieve other fields as needed

                            My_Appointment_List appointment = documentSnapshot.toObject(My_Appointment_List.class);
                            list.add(appointment);
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
        db1.collection("users").document(currentUserUid).get()
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
                            } else if (identity.equals("Doctor") || identity.equals("Administrator")) {
                                addAppointmentBtn.setVisibility(View.GONE);
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

        return view;
    }

    @Override
    public void onItemClicked(My_Appointment_List appointment) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            String userId = user.getUid();

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("users")
                    .document(userId)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // Document exists, retrieve the data
                                String DRid = document.getString("identity");

                                Intent intent;
                                if (DRid.equals("Doctor")) {
                                    intent = new Intent(getContext(), DoctorFindings.class);
                                } else {
                                    intent = new Intent(getContext(), Patient_Findings.class);
                                }

                                intent.putExtra("DOCTOR_NAME", appointment.getDOCTORNAME());
                                intent.putExtra("PATIENT_EMAIL", appointment.getID());
                                intent.putExtra("PATIENT_NAME", appointment.getFULLNAME());
                                intent.putExtra("APPOINTMENTID", appointment.getAPPOINTMENTID());
                                startActivity(intent);
                            } else {
                                // Document does not exist
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
    }
}
