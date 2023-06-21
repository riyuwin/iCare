package com.example.icare.Adapters;

public class DoctorAdapter {

    public String fullname, email, phone, identity, specialty;

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

    public String getSpecialty() {
        return specialty;
    }

    public DoctorAdapter(String fullname, String email, String phone, String identity, String specialty){
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.identity = identity;
        this.specialty = specialty;
    }

}
