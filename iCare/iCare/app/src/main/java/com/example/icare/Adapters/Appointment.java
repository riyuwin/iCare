package com.example.icare.Adapters;

import com.google.firebase.Timestamp;

public class Appointment {
    String setappointmentdate;
    String setappointmenttime;
    String email;
    String status;

    String fullname;

    Timestamp timestamp;

    String userId;

    public String getUserId() {
        return userId;
    }

    public String getFullname() {
        return fullname;
    }

    public String getStatus() {
        return status;
    }
    public String getSetappointmentdate() {
        return setappointmentdate;
    }
    public String getSetappointmenttime() {
        return setappointmenttime;
    }
    public String getEmail() {
        return email;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
