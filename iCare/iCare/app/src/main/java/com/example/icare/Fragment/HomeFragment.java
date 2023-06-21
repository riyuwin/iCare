package com.example.icare.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.icare.Adapters.Set_Appointment_Doctor;
import com.example.icare.Doctor_Appointment.DoctorListAppointments;
import com.example.icare.Doctor_Appointment.Setting_Appointment;
import com.example.icare.R;


public class HomeFragment extends Fragment implements View.OnClickListener{

    public CardView card1, card2, card3, card4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        card1 = (CardView) view.findViewById(R.id.c1);
        card2 = (CardView) view.findViewById(R.id.c2);
        card3 = (CardView) view.findViewById(R.id.c3);
        card4 = (CardView) view.findViewById(R.id.c4);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);

        return view;


    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {
            case R.id.c1:
                i = new Intent(getActivity(), DoctorListAppointments.class);
                i.putExtra("DOCTOR_SPECIALTY", "Cardiologist Appointment");
                startActivity(i);
                break;

            case R.id.c2:
                i = new Intent(getActivity(), DoctorListAppointments.class);
                i.putExtra("DOCTOR_SPECIALTY", "Pediatrician Appointment");
                startActivity(i);
                break;

            case R.id.c3:
                i = new Intent(getActivity(), DoctorListAppointments.class);
                i.putExtra("DOCTOR_SPECIALTY", "Dietitian Appointment");
                startActivity(i);
                break;

            case R.id.c4:
                i = new Intent(getActivity(), DoctorListAppointments.class);
                i.putExtra("DOCTOR_SPECIALTY", "Neurologist Appointment");
                startActivity(i);
                break;

            default:
                throw new IllegalArgumentException("Invalid view ID: " + view.getId());
        }

    }
}

