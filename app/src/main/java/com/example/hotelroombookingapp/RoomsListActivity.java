package com.example.hotelroombookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class RoomsListActivity extends AppCompatActivity {

    RecyclerView recyclerRooms;
    TextView tvCheckinDate, tvCheckoutDate;
    Button btnSearchRooms;
    ArrayList<RoomModel> roomList = new ArrayList<>();
    RoomAdapter adapter;
    DatabaseHelper db;
    Calendar checkinCal, checkoutCal;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Available Rooms");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerRooms = findViewById(R.id.recyclerRooms);
        tvCheckinDate = findViewById(R.id.tvCheckinDate);
        tvCheckoutDate = findViewById(R.id.tvCheckoutDate);
        btnSearchRooms = findViewById(R.id.btnSearchRooms);

        recyclerRooms.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);
        sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        
        // Initialize dates
        checkinCal = Calendar.getInstance();
        checkoutCal = Calendar.getInstance();
        checkoutCal.add(Calendar.DAY_OF_MONTH, 1);
        
        updateDateDisplay();

        tvCheckinDate.setOnClickListener(v -> showDatePicker(true));
        tvCheckoutDate.setOnClickListener(v -> showDatePicker(false));
        btnSearchRooms.setOnClickListener(v -> loadAvailableRooms());

        // Load rooms for default dates
        loadAvailableRooms();
    }

    private void showDatePicker(boolean isCheckin) {
        Calendar cal = isCheckin ? checkinCal : checkoutCal;
        DatePickerDialog picker = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    cal.set(year, month, dayOfMonth);
                    
                    // Validate dates
                    if (!isCheckin && checkoutCal.before(checkinCal)) {
                        Toast.makeText(this, "Check-out must be after check-in", Toast.LENGTH_SHORT).show();
                        checkoutCal.setTime(checkinCal.getTime());
                        checkoutCal.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    
                    updateDateDisplay();
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH));

        picker.getDatePicker().setMinDate(System.currentTimeMillis());
        picker.show();
    }

    private void updateDateDisplay() {
        tvCheckinDate.setText("Check-in: " + sdf.format(checkinCal.getTime()));
        tvCheckoutDate.setText("Check-out: " + sdf.format(checkoutCal.getTime()));
    }

    private void loadAvailableRooms() {
        roomList.clear();
        
        String checkinDate = sdf.format(checkinCal.getTime());
        String checkoutDate = sdf.format(checkoutCal.getTime());
        
        Cursor cursor = db.getAvailableRoomsForDates(checkinDate, checkoutDate);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex("room_id");
                int numberIndex = cursor.getColumnIndex("room_number");
                int typeIndex = cursor.getColumnIndex("room_type");
                int priceIndex = cursor.getColumnIndex("price");
                int descIndex = cursor.getColumnIndex("description");
                int availIndex = cursor.getColumnIndex("availability");

                RoomModel room = new RoomModel(
                        cursor.getInt(idIndex),
                        cursor.getString(numberIndex),
                        cursor.getString(typeIndex),
                        cursor.getInt(priceIndex),
                        cursor.getString(descIndex),
                        cursor.getString(availIndex)
                );
                roomList.add(room);
            } while (cursor.moveToNext());
            cursor.close();
        }

        if (roomList.isEmpty()) {
            Toast.makeText(this, "No rooms available for selected dates", Toast.LENGTH_SHORT).show();
        }

        adapter = new RoomAdapter(this, roomList, checkinDate, checkoutDate);
        recyclerRooms.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void loadRooms() {
        Cursor cursor = db.getAvailableRooms();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex("room_id");
                int numberIndex = cursor.getColumnIndex("room_number");
                int typeIndex = cursor.getColumnIndex("room_type");
                int priceIndex = cursor.getColumnIndex("price");
                int descIndex = cursor.getColumnIndex("description");
                int availIndex = cursor.getColumnIndex("availability");

                RoomModel room = new RoomModel(
                        cursor.getInt(idIndex),
                        cursor.getString(numberIndex),
                        cursor.getString(typeIndex),
                        cursor.getInt(priceIndex),
                        cursor.getString(descIndex),
                        cursor.getString(availIndex)
                );
                roomList.add(room);
            } while (cursor.moveToNext());
            cursor.close();
        }

        adapter = new RoomAdapter(this, roomList);
        recyclerRooms.setAdapter(adapter);
    }
}
