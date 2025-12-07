package com.example.hotelroombookingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class DashboardActivity extends AppCompatActivity {

    private MaterialCardView cardRooms, cardServices, cardHistory;
    private Button btnLogout;
    private ImageView ivSettings;
    private TextView tvWelcome;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        db = new DatabaseHelper(this);

        cardRooms = findViewById(R.id.cardRooms);
        cardServices = findViewById(R.id.cardServices);
        cardHistory = findViewById(R.id.cardHistory);
        btnLogout = findViewById(R.id.btnLogout);
        ivSettings = findViewById(R.id.ivSettings);
        tvWelcome = findViewById(R.id.tvWelcome);

        if (tvWelcome != null) {
            loadUserName();
        }

        // Navigate to Rooms List
        cardRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, RoomsListActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // Navigate to Services List
        cardServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ServicesListActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // Navigate to Booking History
        cardHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, BookingHistoryActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // Settings
        if (ivSettings != null) {
            ivSettings.setOnClickListener(v -> {
                Intent intent = new Intent(DashboardActivity.this, SettingsActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            });
        }

        // Logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
                prefs.edit().clear().apply();

                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
    }

    private void loadUserName() {
        SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
        String username = prefs.getString("username", "Guest");

        try {
            Cursor cursor = db.getUserByUsername(username);
            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex("full_name");
                if (nameIndex >= 0) {
                    String fullName = cursor.getString(nameIndex);
                    if (fullName != null && !fullName.isEmpty()) {
                        tvWelcome.setText("Welcome, " + fullName + "!");
                    } else {
                        tvWelcome.setText("Welcome back!");
                    }
                }
                cursor.close();
            } else {
                tvWelcome.setText("Welcome back!");
            }
        } catch (Exception e) {
            tvWelcome.setText("Welcome back!");
        }
    }
}
