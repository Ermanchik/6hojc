package com.example.doskao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView nav_view;
    private DrawerLayout drawerLayout;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init()
    {
        nav_view = findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference myRef = database.getReference("Tabla");
        myRef.setValue("qwqe");

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.id_my_ads:
                Toast.makeText(this, "pressed id MyAds", Toast.LENGTH_SHORT).show();
                break;

            case R.id.id_cars_ads:
                Toast.makeText(this, "pressed id cars", Toast.LENGTH_SHORT).show();
                break;

            case R.id.id_pc_ads:
                Toast.makeText(this, "pressed id PC", Toast.LENGTH_SHORT).show();
                break;

            case R.id.id_smartphone_ads:
                Toast.makeText(this, "pressed id phones", Toast.LENGTH_SHORT).show();
                break;

            case R.id.id_dm_ads:
                Toast.makeText(this, "pressed id DM", Toast.LENGTH_SHORT).show();
                break;

            case R.id.id_sign_up:
                signUpDialog(R.string.sign_up, R.string.signup_button, 0);
                Toast.makeText(this, "pressed id SignUP", Toast.LENGTH_SHORT).show();
                break;

            case R.id.id_sign_in:
                signUpDialog(R.string.sign_in, R.string.sign_in_button, 1 );
                Toast.makeText(this, "pressed id SignIN", Toast.LENGTH_SHORT).show();
                break;

            case R.id.id_sign_out:
                Toast.makeText(this, "pressed id SignOUT", Toast.LENGTH_SHORT).show();
                break;




        }
        return true;
    }
    private void signUpDialog(int title, int buttonTitle, int index)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.sign_up_layout, null);
        dialogBuilder.setView(dialogView);

        TextView titleTextView = dialogView.findViewById(R.id.tvAlertTitle);
        titleTextView.setText(title);

        Button b = dialogView.findViewById(R.id.buttonSignUp);
        EditText edEmail = dialogView.findViewById(R.id.edEmail);
        EditText edPassword = dialogView.findViewById(R.id.edPassword);
        b.setText(buttonTitle);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == 0)
                {
                    signUp(edEmail.getText().toString(),edEmail.getText().toString());
                }
            }
        });

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
    private void signUp(String email, String password)
    {
        if (email.equals("")&& !password.equals("")){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d("MyLogMainActivity" , "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        }else {
                            Log.w("MyLogMainActivity", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
        else {
            Toast.makeText(this, "Email")
        }

}}