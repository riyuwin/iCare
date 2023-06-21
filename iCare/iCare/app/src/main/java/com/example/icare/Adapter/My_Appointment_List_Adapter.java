package com.example.icare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icare.R;

import java.util.ArrayList;

public class My_Appointment_List_Adapter extends RecyclerView.Adapter<My_Appointment_List_Adapter.MyViewHolder>  {

    Context context;

    ArrayList<My_Appointment_List> list;

    private final My_Appointment_SelectListener listener;

    public My_Appointment_List_Adapter(Context context, ArrayList<My_Appointment_List> list, My_Appointment_SelectListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recyclerview_myappointments_items, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        My_Appointment_List appointments = list.get(position);
        holder.fullnametxt.setText(appointments.getFULLNAME());
        holder.identitytxt.setText(appointments.getIDENTITY());
        holder.appointmentdatetxt.setText(appointments.getAPPOINTMENTDATE());
        holder.appointmenttimetxt.setText(appointments.getAPPOINTMENTTIME());

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

        TextView fullnametxt, identitytxt, appointmentdatetxt, appointmenttimetxt;

        public CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fullnametxt = itemView.findViewById(R.id.fullname_tv);
            identitytxt = itemView.findViewById(R.id.identity);
            appointmentdatetxt = itemView.findViewById(R.id.appointment_date);
            appointmenttimetxt = itemView.findViewById(R.id.appointment_time);

            cardView = itemView.findViewById(R.id.my_appointment_cd);

        }
    }

}