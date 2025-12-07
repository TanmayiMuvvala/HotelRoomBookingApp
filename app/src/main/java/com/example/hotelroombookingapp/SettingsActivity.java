package com.example.hotelroombookingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private LinearLayout btnProfile, btnAbout, btnLogoutSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Settings");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnProfile = findViewById(R.id.btnProfile);
        btnAbout = findViewById(R.id.btnAbout);
        btnLogoutSettings = findViewById(R.id.btnLogoutSettings);

        btnProfile.setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileActivity.class));
        });

        btnAbout.setOnClickListener(v -> {
            Toast.makeText(this, "Hotel Booking App v1.0\nDeveloped for seamless hotel experience", Toast.LENGTH_LONG).show();
        });

        btnLogoutSettings.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
            prefs.edit().clear().apply();

            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
