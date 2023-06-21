package com.example.icare.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.example.icare.Fragment.Landing_Page;
import com.example.icare.R;

public class Intro extends AppCompatActivity implements View.OnClickListener {

    ViewFlipper viewFlipper;
    Button next;
    Button skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
        next = (Button)findViewById(R.id.next);
        skip = (Button)findViewById(R.id.skip);

        next.setOnClickListener(this);
        skip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == next) {
            viewFlipper.showNext();
        } else if (v == skip) {
            Intent intent = new Intent (this, Landing_Page.class);
            startActivity(intent);
        }
    }
}