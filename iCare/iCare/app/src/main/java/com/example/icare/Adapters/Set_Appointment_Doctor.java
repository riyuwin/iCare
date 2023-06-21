package com.example.icare.Adapters;

public class Set_Appointment_Doctor {

    public String email, setappointmentdate, setappointmenttime, status, userId;

    //plan constructor in case we want to read the file

    public Set_Appointment_Doctor(String email, String setappointmentdate, String setappointmenttime, String status, long userId){
        this.email = email;
        this.setappointmentdate = setappointmentdate;
        this.setappointmenttime = setappointmenttime;
        this.status = status;
        this.userId = String.valueOf(userId);
    }

}
