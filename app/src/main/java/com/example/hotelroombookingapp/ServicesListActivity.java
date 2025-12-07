package com.example.hotelroombookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class ServicesListActivity extends AppCompatActivity {

    LinearLayout btnCleaning, btnLaundry, btnFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Hotel Services");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnCleaning = findViewById(R.id.btnCleaning);
        btnLaundry = findViewById(R.id.btnLaundry);
        btnFood = findViewById(R.id.btnFood);

        btnCleaning.setOnClickListener(v -> openBooking("Room Cleaning"));
        btnLaundry.setOnClickListener(v -> openBooking("Laundry Service"));
        btnFood.setOnClickListener(v -> openBooking("Food Delivery"));
    }

    private void openBooking(String serviceType) {
        Intent intent = new Intent(this, BookServiceActivity.class);
        intent.putExtra("service_type", serviceType);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
