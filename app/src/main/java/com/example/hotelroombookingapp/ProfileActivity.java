package com.example.hotelroombookingapp;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvProfileUsername;
    private TextInputEditText etProfileFullName, etProfileEmail, etProfilePhone;
    private Button btnUpdateProfile;
    private DatabaseHelper db;
    private String currentUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db = new DatabaseHelper(this);

        tvProfileUsername = findViewById(R.id.tvProfileUsername);
        etProfileFullName = findViewById(R.id.etProfileFullName);
        etProfileEmail = findViewById(R.id.etProfileEmail);
        etProfilePhone = findViewById(R.id.etProfilePhone);
        btnUpdateProfile = findViewById(R.id.btnUpdateProfile);

        SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
        currentUsername = prefs.getString("username", "");

        loadUserProfile();

        btnUpdateProfile.setOnClickListener(v -> updateProfile());
    }

    private void loadUserProfile() {
        Cursor cursor = db.getUserByUsername(currentUsername);
        if (cursor != null && cursor.moveToFirst()) {
            int usernameIndex = cursor.getColumnIndex("username");
            int fullNameIndex = cursor.getColumnIndex("full_name");
            int emailIndex = cursor.getColumnIndex("email");
            int phoneIndex = cursor.getColumnIndex("phone");

            tvProfileUsername.setText(cursor.getString(usernameIndex));
            etProfileFullName.setText(cursor.getString(fullNameIndex));
            etProfileEmail.setText(cursor.getString(emailIndex));
            etProfilePhone.setText(cursor.getString(phoneIndex));
            cursor.close();
        }
    }

    private void updateProfile() {
        String fullName = etProfileFullName.getText().toString().trim();
        String email = etProfileEmail.getText().toString().trim();
        String phone = etProfilePhone.getText().toString().trim();

        if (fullName.isEmpty()) {
            Toast.makeText(this, "Full name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean updated = db.updateUserProfile(currentUsername, fullName, email, phone);

        if (updated) {
            Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
