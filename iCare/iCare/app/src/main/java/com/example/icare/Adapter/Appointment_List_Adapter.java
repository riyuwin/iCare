package com.example.icare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icare.Adapters.AppointmentSelectListener;
import com.example.icare.R;

import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Appointment_List_Adapter extends RecyclerView.Adapter<Appointment_List_Adapter.MyViewHolder> {

    Context context;

    ArrayList<Appointment_List> list;

    private final Appointment_List_SelectListener listener;

    public Appointment_List_Adapter(Context context, ArrayList<Appointment_List> list, Appointment_List_SelectListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recyclerview_doctor_appointment_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Appointment_List appointments = list.get(position);
        holder.fullnametxt.setText(appointments.getName());
        holder.appointmentdatetxt.setText(appointments.getAppointmentdate());
        holder.appointmenttimetxt.setText(appointments.getAppointmenttime());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class  MyViewHolder extends RecyclerView.ViewHolder{

        TextView fullnametxt, appointmentdatetxt, appointmenttimetxt;

        public CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fullnametxt = itemView.findViewById(R.id.doctor_name_tv);
            appointmentdatetxt = itemView.findViewById(R.id.appointment_date_tv);
            appointmenttimetxt = itemView.findViewById(R.id.appointment_time_tv);

            cardView = itemView.findViewById(R.id.doctor_appointment_cd);

        }
    }

}
