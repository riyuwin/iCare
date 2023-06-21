package com.example.icare.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.icare.Fragment.Landing_Page;
import com.example.icare.R;
import com.example.icare.databinding.ActivitySignInBinding;
import com.example.icare.utilities.Constants;
import com.example.icare.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    private ActivitySignInBinding binding;

    private PreferenceManager preferenceManager;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceManager = new PreferenceManager(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();

        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners(){
        binding.textCreateNewAccount.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), Sign_up.class)));
        binding.buttonSignIn.setOnClickListener(view -> {
            if(isValidSignInDetails()) {
                signIN();
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    private void signIN(){


        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS)
                .whereEqualTo(Constants.KEY_EMAIL, binding.inputEmail.getText().toString())
                .whereEqualTo(Constants.KEY_PASSWORD, binding.inputPassword.getText().toString())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null && !task.getResult().isEmpty()) {
                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                        String userId = documentSnapshot.getId();

                        mAuth.signInWithEmailAndPassword(binding.inputEmail.getText().toString(), binding.inputPassword.getText().toString())
                                .addOnCompleteListener(authTask -> {
                                    if (authTask.isSuccessful()) {
                                        preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                                        preferenceManager.putString(Constants.KEY_USER_ID, userId);
                                        preferenceManager.putString(Constants.KEY_NAME, documentSnapshot.getString(Constants.KEY_NAME));
                                        preferenceManager.putString(Constants.KEY_IMAGE, documentSnapshot.getString(Constants.KEY_IMAGE));
                                        Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), Landing_Page.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        loading(false);
                                        showToast("Login Error: " + authTask.getException().getMessage());
                                    }
                                });
                    } else {
                        loading(false);
                        showToast("Unable to sign in");
                    }
                });

    }

    private void loading(Boolean isLoading) {
        if(isLoading){
            binding.buttonSignIn.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);

        }else {
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.buttonSignIn.setVisibility(View.VISIBLE);
        }
    }


    private Boolean isValidSignInDetails(){
        if(binding.inputEmail.getText().toString().trim().isEmpty()) {
            showToast("Enter email");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.getText().toString()).matches()) {
            showToast("Enter valid email");
            return false;
        }else if(binding.inputPassword.getText().toString().trim().isEmpty()) {
            showToast("Enter password");
            return false;

        }else {
            return true;
        }
    }
}