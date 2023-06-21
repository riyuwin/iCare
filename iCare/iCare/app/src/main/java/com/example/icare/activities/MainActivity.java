package com.example.icare.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.icare.R;
import com.example.icare.databinding.ActivityMainBinding;
import com.example.icare.utilities.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferenceManager = new PreferenceManager(getApplicationContext());

    }



}