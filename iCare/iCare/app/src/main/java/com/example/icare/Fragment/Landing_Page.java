package com.example.icare.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.icare.Authentication.Doctor_Signup;
import com.example.icare.Authentication.Doctor_Signup_Specialty;
import com.example.icare.Authentication.Login;
import com.example.icare.R;
import com.example.icare.activities.UsersActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class Landing_Page extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    TextView UserName_Txt, Email_Txt;

    FirebaseAuth mAuth;

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    com.example.icare.Fragment.calendarFragment calendarFragment = new calendarFragment();

    ChatFragment chatFragment = new ChatFragment();

    DoctorFragment doctorFragment = new DoctorFragment();

    ProfileFragment profileFragment = new ProfileFragment();
    ActionBar actionBar;

    ImageView logout_icon;

    private ImageView userProfileImage;
    private TextView userNameTextView;
    private TextView userEmailTextView;

    private DrawerLayout drawer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        mAuth = FirebaseAuth.getInstance();


        //user checker

        userProfileImage = findViewById(R.id.user_image);
        userNameTextView = findViewById(R.id.username);
        userEmailTextView = findViewById(R.id.user_email);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.doctor:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, doctorFragment).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                        return true;

                }

                return false;
            }
        });


        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            String userId = user.getUid();


            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("users")
                    .document(userId)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // Document exists, retrieve the data
                                String email = document.getString("email");
                                String name = document.getString("name");
                                String phone = document.getString("phone");
                                String identity = document.getString("identity");

                                TextView fullname_txt = findViewById(R.id.username);
                                TextView identity_txt = findViewById(R.id.user_email);
                                fullname_txt.setText(name);
                                identity_txt.setText(identity);
                            } else {
                                // Document does not exist
                                System.out.println("Document does not exist");
                            }
                        } else {
                            // Handle the error
                            Exception exception = task.getException();
                            System.out.println("Error retrieving data: " + exception.getMessage());
                        }
                    });



        } else {
            // User is not currently authenticated
            System.out.println("User not authenticated");
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                        new AboutusFragment()).commit();
                break;

            case R.id.add_doctors:
                Intent intent1 = new Intent(this, Doctor_Signup_Specialty.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                break;

            case R.id.nav_chat:
                Intent intent = new Intent(this, UsersActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                logout();
                return true;
        }
        drawer.closeDrawer(GravityCompat.START); // Close the drawer after handling the item selection
        return true;

    }

    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut(); // Sign out the user from Firebase Authentication

        // Clear all activities from the back stack and start the login activity
        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear all activities except the login activity
        startActivity(intent);
        finishAffinity(); // Finish all activities in the current task
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(this, Login.class));
        }
    }
}
