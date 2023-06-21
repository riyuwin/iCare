package com.example.icare.Authentication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.icare.Fragment.Landing_Page;
import com.example.icare.R;
import com.example.icare.utilities.Constants;
import com.example.icare.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.example.icare.databinding.ActivitySignUpBinding;


public class Sign_up extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    private PreferenceManager preferenceManager;

    private String encodedImage;

    EditText fullnametxt, emailtxt, phonetxt, passwordtxt, confirm_passwordtxt, specialtytxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        emailtxt = (EditText) findViewById(R.id.inputEmail);
        passwordtxt = (EditText) findViewById(R.id.inputPassword);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        setListeners();
    }

    private void setListeners(){
        binding.textSignIn.setOnClickListener(v -> onBackPressed());
        binding.buttonSignUp.setOnClickListener(v -> {
            if(isValidSignUpDetails()){
                signUp();
            }
        });

        binding.textAddImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pickImage.launch(intent);

        });

    }

    private void showToast(String Message) {
        Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
    }

    private void signUp() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(binding.inputEmail.getText().toString(),
                        binding.inputPassword.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // User account creation is successful
                            FirebaseUser user1 = firebaseAuth.getCurrentUser();
                            String userId = user1.getUid();

                            loading(true);
                            FirebaseFirestore database = FirebaseFirestore.getInstance();
                            HashMap<String, Object> user = new HashMap<>();
                            user.put(Constants.KEY_NAME, binding.inputName.getText().toString());
                            user.put(Constants.KEY_EMAIL, binding.inputEmail.getText().toString());
                            user.put(Constants.KEY_PASSWORD, binding.inputPassword.getText().toString());
                            user.put(Constants.KEY_IDENTITY, "Patient");
                            user.put(Constants.KEY_IMAGE, encodedImage);
                            database.collection(Constants.KEY_COLLECTION_USERS)
                                    .document(userId) // Set the user ID as the document ID
                                    .set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            loading(false);
                                            preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                                            preferenceManager.putString(Constants.KEY_USER_ID, userId);
                                            preferenceManager.putString(Constants.KEY_NAME, binding.inputName.getText().toString());
                                            preferenceManager.putString(Constants.KEY_IDENTITY, "Patient");
                                            preferenceManager.putString(Constants.KEY_IMAGE, encodedImage);
                                            Toast.makeText(Sign_up.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), Login.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            loading(false);
                                            showToast(e.getMessage());
                                        }
                                    });
                        } else {
                            // User account creation failed
                            Exception exception = task.getException();
                            // Handle the error
                        }
                    }
                });

    }

    private String encodeImage(Bitmap bitmap) {
        int previewWidth = 150;
        int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBimap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBimap.compress(Bitmap.CompressFormat.JPEG,50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK) {
                    if(result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            binding.imageProfile.setImageBitmap(bitmap);
                            binding.textAddImage.setVisibility(View.GONE);
                            encodedImage = encodeImage(bitmap);
                        }catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    private Boolean isValidSignUpDetails(){
        if(encodedImage == null) {
            showToast("Select profile image");
            return false;
        }else if (binding.inputName.getText().toString().trim().isEmpty()) {
            showToast("Enter Name");
            return false;

        }else if (binding.inputEmail.getText().toString().trim().isEmpty()) {
            showToast("Enter email");
            return false;

        }else if (!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.getText().toString()).matches()) {
            showToast("Enter valid email");
            return false;
        }else if (binding.inputPassword.getText().toString().trim().isEmpty()){
            showToast("Enter password");
            return false;
        }else if(binding.inputConfirmPassword.getText().toString().isEmpty()){
            showToast("Confirm your password");
            return false;
        } else if (!binding.inputPassword.getText().toString().equals(binding.inputConfirmPassword.getText().toString())) {

            showToast("Password & Confirm password must be same");
            return false;
        }else {
            return true;
        }
    }

    private void loading(Boolean isLoading) {
        if(isLoading){
            binding.buttonSignUp.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);

        }else {
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.buttonSignUp.setVisibility(View.VISIBLE);

        }
    }
}