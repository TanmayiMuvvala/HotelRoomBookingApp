package com.example.hotelroombookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BookingHistoryActivity extends AppCompatActivity {

    LinearLayout layoutRoomHistory, layoutServiceHistory;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Booking History");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        layoutRoomHistory = findViewById(R.id.layoutRoomHistory);
        layoutServiceHistory = findViewById(R.id.layoutServiceHistory);
        db = new DatabaseHelper(this);

        loadRoomHistory();
        loadServiceHistory();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void loadRoomHistory() {
        Cursor c = db.getAllRoomBookings();

        if (c != null && c.moveToFirst()) {
            do {
                int nameIndex = c.getColumnIndex("customer_name");
                int checkinIndex = c.getColumnIndex("checkin_date");
                int checkoutIndex = c.getColumnIndex("checkout_date");
                int priceIndex = c.getColumnIndex("total_price");

                String details = "Customer: " + c.getString(nameIndex) +
                        "\nCheck-in: " + c.getString(checkinIndex) +
                        "\nCheck-out: " + c.getString(checkoutIndex) +
                        "\nTotal Price: ₹" + c.getInt(priceIndex);

                addHistoryItem(layoutRoomHistory, "Room Booking", details);

            } while (c.moveToNext());
            c.close();
        }
    }

    private void loadServiceHistory() {
        Cursor c = db.getAllServiceBookings();

        if (c != null && c.moveToFirst()) {
            do {
                int typeIndex = c.getColumnIndex("service_type");
                int roomIndex = c.getColumnIndex("room_no");
                int timeIndex = c.getColumnIndex("time_slot");
                int priceIndex = c.getColumnIndex("price");

                String details = "Service: " + c.getString(typeIndex) +
                        "\nRoom No: " + c.getString(roomIndex) +
                        "\nTime: " + c.getString(timeIndex) +
                        "\nPrice: ₹" + c.getInt(priceIndex);

                addHistoryItem(layoutServiceHistory, "Service Booking", details);

            } while (c.moveToNext());
            c.close();
        }
    }

    private void addHistoryItem(LinearLayout layout, String title, String details) {
        LayoutInflater inflater = LayoutInflater.from(this);
        android.view.View view = inflater.inflate(R.layout.item_history, layout, false);

        TextView txtTitle = view.findViewById(R.id.txtHistoryTitle);
        TextView txtDetails = view.findViewById(R.id.txtHistoryDetails);

        txtTitle.setText(title);
        txtDetails.setText(details);

        layout.addView(view);
    }
}
