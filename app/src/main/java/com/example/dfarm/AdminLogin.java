package com.example.dfarm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminLogin extends AppCompatActivity {

    Button Admin, Forget1;
    EditText adminEmail, adminPassword;
    FirebaseAuth auth;

    private static final String ADMIN_EMAIL = "prathmeshpatil8066@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Admin = findViewById(R.id.adminlogin);
        Forget1 = findViewById(R.id.forget1);
        adminEmail = findViewById(R.id.login_email);
        adminPassword = findViewById(R.id.adminPassword);

        auth = FirebaseAuth.getInstance();

        Admin.setOnClickListener(v -> {

            String emailTxt = adminEmail.getText().toString().trim();
            String passTxt = adminPassword.getText().toString().trim();

            if (emailTxt.isEmpty() || passTxt.isEmpty()) {
                Toast.makeText(this, "Enter Email or Password", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.signInWithEmailAndPassword(emailTxt, passTxt)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {

                            FirebaseUser user = auth.getCurrentUser();

                            if (user != null && user.getEmail().equals(ADMIN_EMAIL)) {

                                Toast.makeText(this, "Admin Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AdminLogin.this, Home2.class));
                                finish();

                            } else {
                                auth.signOut();
                                Toast.makeText(this, "You are not Admin", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(this,
                                    "Login Failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });

        // 🔹 FORGET PASSWORD
        Forget1.setOnClickListener(v -> {
            Intent intent = new Intent(AdminLogin.this, Forgetpassword.class);
            startActivity(intent);
        });
    }
}