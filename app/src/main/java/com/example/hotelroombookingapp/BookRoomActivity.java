package com.example.hotelroombookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookRoomActivity extends AppCompatActivity {

    EditText edtCheckin, edtCheckout;
    TextView tvRoomInfo, tvTotalPrice;
    Button btnConfirm;
    DatabaseHelper db;
    int roomId, roomPrice;
    Calendar checkinCal, checkoutCal;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_room);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Book Room");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        edtCheckin = findViewById(R.id.edtCheckinDate);
        edtCheckout = findViewById(R.id.edtCheckoutDate);
        tvRoomInfo = findViewById(R.id.tvRoomInfo);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnConfirm = findViewById(R.id.btnConfirmBooking);

        db = new DatabaseHelper(this);
        sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        checkinCal = Calendar.getInstance();
        checkoutCal = Calendar.getInstance();
        checkoutCal.add(Calendar.DAY_OF_MONTH, 1);

        roomId = getIntent().getIntExtra("room_id", -1);
        
        // Check if dates were passed from room list
        String preCheckin = getIntent().getStringExtra("checkin_date");
        String preCheckout = getIntent().getStringExtra("checkout_date");
        
        if (preCheckin != null && preCheckout != null) {
            try {
                checkinCal.setTime(sdf.parse(preCheckin));
                checkoutCal.setTime(sdf.parse(preCheckout));
            } catch (Exception e) {
                // Use default dates if parsing fails
            }
        }
        
        loadRoomInfo();

        edtCheckin.setOnClickListener(v -> showDatePicker(true));
        edtCheckout.setOnClickListener(v -> showDatePicker(false));
        btnConfirm.setOnClickListener(v -> bookRoom());
    }

    private void loadRoomInfo() {
        Cursor cursor = db.getRoomById(roomId);
        if (cursor != null && cursor.moveToFirst()) {
            int numberIndex = cursor.getColumnIndex("room_number");
            int typeIndex = cursor.getColumnIndex("room_type");
            int priceIndex = cursor.getColumnIndex("price");

            String roomNumber = cursor.getString(numberIndex);
            String roomType = cursor.getString(typeIndex);
            roomPrice = cursor.getInt(priceIndex);

            tvRoomInfo.setText("Room " + roomNumber + " - " + roomType + " (₹" + roomPrice + "/night)");
            cursor.close();
            calculateTotal();
        }
    }

    private void showDatePicker(boolean isCheckin) {
        Calendar cal = isCheckin ? checkinCal : checkoutCal;
        DatePickerDialog picker = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    cal.set(year, month, dayOfMonth);
                    updateDateFields();
                    calculateTotal();
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH));

        picker.getDatePicker().setMinDate(System.currentTimeMillis());
        picker.show();
    }

    private void updateDateFields() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        edtCheckin.setText(sdf.format(checkinCal.getTime()));
        edtCheckout.setText(sdf.format(checkoutCal.getTime()));
    }

    private void calculateTotal() {
        long diff = checkoutCal.getTimeInMillis() - checkinCal.getTimeInMillis();
        int days = (int) (diff / (1000 * 60 * 60 * 24));
        if (days < 1) days = 1;
        int total = days * roomPrice;
        tvTotalPrice.setText("Total: ₹" + total + " (" + days + " night" + (days > 1 ? "s" : "") + ")");
    }

    private void bookRoom() {
        String checkin = edtCheckin.getText().toString().trim();
        String checkout = edtCheckout.getText().toString().trim();

        if (checkin.isEmpty() || checkout.isEmpty()) {
            Toast.makeText(this, "Please select check-in and check-out dates", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
        String username = prefs.getString("username", "Guest");

        long diff = checkoutCal.getTimeInMillis() - checkinCal.getTimeInMillis();
        int days = (int) (diff / (1000 * 60 * 60 * 24));
        if (days < 1) days = 1;
        int total = days * roomPrice;

        long inserted = db.addRoomBooking(roomId, username, checkin, checkout, total);

        if (inserted > 0) {
            Toast.makeText(this, "Room booked successfully!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Booking failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
