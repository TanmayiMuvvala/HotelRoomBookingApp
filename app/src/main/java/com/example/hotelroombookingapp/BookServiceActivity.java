package com.example.hotelroombookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BookServiceActivity extends AppCompatActivity {

    TextView txtServiceTitle;
    EditText edtRoom, edtTime, edtNotes, edtPrice;
    Button btnConfirm;
    DatabaseHelper db;
    String serviceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_service);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Book Service");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        txtServiceTitle = findViewById(R.id.txtServiceTitle);
        edtRoom = findViewById(R.id.edtRoomNo);
        edtTime = findViewById(R.id.edtTimeSlot);
        edtNotes = findViewById(R.id.edtNotes);
        edtPrice = findViewById(R.id.edtServicePrice);
        btnConfirm = findViewById(R.id.btnConfirmServiceBooking);

        db = new DatabaseHelper(this);

        serviceType = getIntent().getStringExtra("service_type");
        txtServiceTitle.setText(serviceType);

        // Set default prices
        setDefaultPrice();

        btnConfirm.setOnClickListener(v -> bookService());
    }

    private void setDefaultPrice() {
        int defaultPrice = 0;
        if (serviceType.contains("Cleaning")) {
            defaultPrice = 200;
        } else if (serviceType.contains("Laundry")) {
            defaultPrice = 150;
        } else if (serviceType.contains("Food")) {
            defaultPrice = 300;
        }
        edtPrice.setText(String.valueOf(defaultPrice));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void bookService() {
        String room = edtRoom.getText().toString().trim();
        String time = edtTime.getText().toString().trim();
        String notes = edtNotes.getText().toString().trim();
        String priceStr = edtPrice.getText().toString().trim();

        if (room.isEmpty() || time.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int price = Integer.parseInt(priceStr);
            long result = db.addServiceBooking(serviceType, room, time, notes, price);

            if (result > 0) {
                Toast.makeText(this, "Service booked successfully!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to book service", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid price format", Toast.LENGTH_SHORT).show();
        }
    }
}
