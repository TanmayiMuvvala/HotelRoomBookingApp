package com.example.hotelroombookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class RoomDetailsActivity extends AppCompatActivity {

    TextView txtRoomNumber, txtRoomType, txtPrice, txtDescription, txtAvailability;
    Button btnBookNow;
    DatabaseHelper db;
    int roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Room Details");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db = new DatabaseHelper(this);

        txtRoomNumber = findViewById(R.id.txtDetailRoomNumber);
        txtRoomType = findViewById(R.id.txtDetailRoomType);
        txtPrice = findViewById(R.id.txtDetailPrice);
        txtDescription = findViewById(R.id.txtDetailDescription);
        txtAvailability = findViewById(R.id.txtDetailAvailability);
        btnBookNow = findViewById(R.id.btnBookNow);

        // Get room ID passed from RoomsListActivity
        roomId = getIntent().getIntExtra("room_id", -1);

        loadRoomDetails();

        btnBookNow.setOnClickListener(v -> {
            Intent i = new Intent(RoomDetailsActivity.this, BookRoomActivity.class);
            i.putExtra("room_id", roomId);
            
            // Pass selected dates if available
            String checkinDate = getIntent().getStringExtra("checkin_date");
            String checkoutDate = getIntent().getStringExtra("checkout_date");
            if (checkinDate != null && checkoutDate != null) {
                i.putExtra("checkin_date", checkinDate);
                i.putExtra("checkout_date", checkoutDate);
            }
            
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void loadRoomDetails() {
        Cursor cursor = db.getRoomById(roomId);
        if (cursor != null && cursor.moveToFirst()) {
            int numberIndex = cursor.getColumnIndex("room_number");
            int typeIndex = cursor.getColumnIndex("room_type");
            int priceIndex = cursor.getColumnIndex("price");
            int descIndex = cursor.getColumnIndex("description");
            int availIndex = cursor.getColumnIndex("availability");

            txtRoomNumber.setText("Room " + cursor.getString(numberIndex));
            txtRoomType.setText(cursor.getString(typeIndex));
            txtPrice.setText("₹ " + cursor.getInt(priceIndex) + " per night");
            txtDescription.setText(cursor.getString(descIndex));
            txtAvailability.setText(cursor.getString(availIndex));
            cursor.close();
        }
    }
}
