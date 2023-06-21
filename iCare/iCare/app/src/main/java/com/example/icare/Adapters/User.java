package com.example.icare.Adapters;

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

public class User {
    public String fullname, email, phone, identity;

    //plan constructor in case we want to read the file
    public User(){

    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getIdentity() {
        return identity;
    }

    // initializer
    public User(String fullname, String email, String phone, String identity){
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.identity = identity;
    }

    public static class DoctorAppointmentAdapter extends RecyclerView.Adapter<DoctorAppointmentAdapter.MyViewHolder> {

        Context context;

        ArrayList<Appointment> list;

        private AppointmentSelectListener listener;

        public DoctorAppointmentAdapter(Context context, ArrayList<Appointment> list, AppointmentSelectListener listener) {
            this.context = context;
            this.list = list;
            this.listener = listener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.recycler_appointment_item, parent,false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            Appointment appointment = list.get(position);
            holder.doctor_name.setText(appointment.getEmail());
            holder.setappointmentdate.setText(appointment.getSetappointmentdate());
            holder.setappointmenttime.setText(appointment.getSetappointmenttime());

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

        public static class MyViewHolder extends RecyclerView.ViewHolder{

            TextView doctor_name, setappointmentdate, setappointmenttime;

            public CardView cardView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                doctor_name = itemView.findViewById(R.id.doctor_name_tv);
                setappointmentdate = itemView.findViewById(R.id.appointment_date_tv);
                setappointmenttime = itemView.findViewById(R.id.appointment_time_tv);

                cardView = itemView.findViewById(R.id.appointment_cardview);

            }
        }

    }
}
