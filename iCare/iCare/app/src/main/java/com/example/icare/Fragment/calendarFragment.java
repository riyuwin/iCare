package com.example.icare.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.icare.Patient_Appointment.Patient_Appointment;
import com.example.icare.R;


public class calendarFragment extends Fragment {

    public ImageView add_appointment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        add_appointment = (ImageView) view.findViewById(R.id.add_appointment_btn);

        add_appointment.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), Patient_Appointment.class)));

        return view;
    }
}