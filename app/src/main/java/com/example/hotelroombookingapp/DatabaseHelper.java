package com.example.hotelroombookingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "hotel_booking.db";
    private static final int DB_VERSION = 2; // Incremented for new Users table

    // Users table
    public static final String TABLE_USERS = "Users";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_FULL_NAME = "full_name";
    public static final String COL_EMAIL = "email";
    public static final String COL_PHONE = "phone";

    // Rooms table
    public static final String TABLE_ROOMS = "Rooms";
    public static final String COL_ROOM_ID = "room_id";
    public static final String COL_ROOM_TYPE = "room_type";
    public static final String COL_ROOM_PRICE = "price";
    public static final String COL_ROOM_DESC = "description";
    public static final String COL_ROOM_AVAIL = "availability";
    public static final String COL_ROOM_NUMBER = "room_number";
    public static final String COL_ROOM_IMAGE = "image_url";

    // Room bookings table
    public static final String TABLE_ROOM_BOOKINGS = "RoomBookings";
    public static final String COL_BOOKING_ID = "booking_id";
    public static final String COL_BOOK_ROOM_ID = "room_id";
    public static final String COL_BOOK_CUSTOMER = "customer_name";
    public static final String COL_BOOK_CHECKIN = "checkin_date";
    public static final String COL_BOOK_CHECKOUT = "checkout_date";
    public static final String COL_BOOK_TOTAL = "total_price";

    // Services bookings table
    public static final String TABLE_SERVICES = "ServiceBookings";
    public static final String COL_SERVICE_ID = "service_id";
    public static final String COL_SERVICE_TYPE = "service_type";
    public static final String COL_SERVICE_ROOM_NO = "room_no";
    public static final String COL_SERVICE_TIME = "time_slot";
    public static final String COL_SERVICE_NOTES = "notes";
    public static final String COL_SERVICE_PRICE = "price";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Users table
        String createUsers = "CREATE TABLE " + TABLE_USERS + " (" +
                COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT UNIQUE, " +
                COL_PASSWORD + " TEXT, " +
                COL_FULL_NAME + " TEXT, " +
                COL_EMAIL + " TEXT, " +
                COL_PHONE + " TEXT)";
        db.execSQL(createUsers);

        // Rooms table
        String createRooms = "CREATE TABLE " + TABLE_ROOMS + " (" +
                COL_ROOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_ROOM_NUMBER + " TEXT, " +
                COL_ROOM_TYPE + " TEXT, " +
                COL_ROOM_PRICE + " INTEGER, " +
                COL_ROOM_DESC + " TEXT, " +
                COL_ROOM_AVAIL + " TEXT, " +
                COL_ROOM_IMAGE + " TEXT)";
        db.execSQL(createRooms);

        // Room bookings
        String createBookings = "CREATE TABLE " + TABLE_ROOM_BOOKINGS + " (" +
                COL_BOOKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_BOOK_ROOM_ID + " INTEGER, " +
                COL_BOOK_CUSTOMER + " TEXT, " +
                COL_BOOK_CHECKIN + " TEXT, " +
                COL_BOOK_CHECKOUT + " TEXT, " +
                COL_BOOK_TOTAL + " INTEGER)";
        db.execSQL(createBookings);

        // Services bookings
        String createServices = "CREATE TABLE " + TABLE_SERVICES + " (" +
                COL_SERVICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_SERVICE_TYPE + " TEXT, " +
                COL_SERVICE_ROOM_NO + " TEXT, " +
                COL_SERVICE_TIME + " TEXT, " +
                COL_SERVICE_NOTES + " TEXT, " +
                COL_SERVICE_PRICE + " INTEGER)";
        db.execSQL(createServices);

        // Preload sample data
        seedUsers(db);
        seedRooms(db);
    }

    private void seedUsers(SQLiteDatabase db) {
        // Create a default test user
        ContentValues cv = new ContentValues();
        cv.put(COL_USERNAME, "demo");
        cv.put(COL_PASSWORD, "demo123");
        cv.put(COL_FULL_NAME, "Demo User");
        cv.put(COL_EMAIL, "demo@hotel.com");
        cv.put(COL_PHONE, "1234567890");
        db.insert(TABLE_USERS, null, cv);
    }

    private void seedRooms(SQLiteDatabase db) {
        insertRoomRaw(db, "101", "Single", 1000, "Cozy single room with AC, WiFi, and TV", "yes", "single_room");
        insertRoomRaw(db, "102", "Double", 1600, "Spacious double bed room with city view", "yes", "double_room");
        insertRoomRaw(db, "201", "Deluxe", 2500, "Deluxe room with balcony and premium amenities", "yes", "deluxe_room");
        insertRoomRaw(db, "202", "Deluxe", 2500, "Deluxe room with garden view", "yes", "deluxe_room");
        insertRoomRaw(db, "301", "Suite", 4000, "Luxury suite with living room and kitchenette", "yes", "suite_room");
        insertRoomRaw(db, "302", "Suite", 4000, "Presidential suite with panoramic view", "yes", "suite_room");
    }

    private void insertRoomRaw(SQLiteDatabase db, String number, String type, int price, String desc, String avail, String image) {
        ContentValues cv = new ContentValues();
        cv.put(COL_ROOM_NUMBER, number);
        cv.put(COL_ROOM_TYPE, type);
        cv.put(COL_ROOM_PRICE, price);
        cv.put(COL_ROOM_DESC, desc);
        cv.put(COL_ROOM_AVAIL, avail);
        cv.put(COL_ROOM_IMAGE, image);
        db.insert(TABLE_ROOMS, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM_BOOKINGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // ----------- USER FUNCTIONS ------------

    public long registerUser(String username, String password, String fullName, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_USERNAME, username);
        cv.put(COL_PASSWORD, password);
        cv.put(COL_FULL_NAME, fullName);
        cv.put(COL_EMAIL, email);
        cv.put(COL_PHONE, phone);
        return db.insert(TABLE_USERS, null, cv);
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE " + COL_USERNAME + "=? AND " + COL_PASSWORD + "=?",
                new String[]{username, password}
        );
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public Cursor getUserByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE " + COL_USERNAME + "=?",
                new String[]{username}
        );
    }

    public boolean updateUserProfile(String username, String fullName, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_FULL_NAME, fullName);
        cv.put(COL_EMAIL, email);
        cv.put(COL_PHONE, phone);
        int rows = db.update(TABLE_USERS, cv, COL_USERNAME + "=?", new String[]{username});
        return rows > 0;
    }

    // ----------- ROOMS FUNCTIONS ------------

    // Get all rooms
    public Cursor getAllRooms() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ROOMS, null);
    }

    // Get available rooms
    public Cursor getAvailableRooms() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ROOMS + " WHERE " + COL_ROOM_AVAIL + "='yes'", null);
    }

    // Get available rooms for specific dates (excluding booked rooms)
    public Cursor getAvailableRoomsForDates(String checkinDate, String checkoutDate) {
        SQLiteDatabase db = this.getReadableDatabase();
        
        // Get all rooms that are NOT booked for the given date range
        String query = "SELECT * FROM " + TABLE_ROOMS + 
                " WHERE " + COL_ROOM_AVAIL + "='yes' " +
                " AND " + COL_ROOM_ID + " NOT IN (" +
                "   SELECT " + COL_BOOK_ROOM_ID + 
                "   FROM " + TABLE_ROOM_BOOKINGS +
                "   WHERE NOT (" +
                "       ? >= " + COL_BOOK_CHECKOUT + " OR " +
                "       ? <= " + COL_BOOK_CHECKIN +
                "   )" +
                ")";
        
        return db.rawQuery(query, new String[]{checkinDate, checkoutDate});
    }

    // ⭐ FIX: Get room by ID (missing earlier)
    public Cursor getRoomById(int roomId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM " + TABLE_ROOMS + " WHERE " + COL_ROOM_ID + "=?",
                new String[]{String.valueOf(roomId)}
        );
    }

    // ----------- ROOM BOOKING FUNCTIONS ------------

    public long addRoomBooking(int roomId, String name, String checkin, String checkout, int totalPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_BOOK_ROOM_ID, roomId);
        cv.put(COL_BOOK_CUSTOMER, name);
        cv.put(COL_BOOK_CHECKIN, checkin);
        cv.put(COL_BOOK_CHECKOUT, checkout);
        cv.put(COL_BOOK_TOTAL, totalPrice);

        return db.insert(TABLE_ROOM_BOOKINGS, null, cv);
    }

    public Cursor getAllRoomBookings() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ROOM_BOOKINGS, null);
    }

    // ----------- SERVICE BOOKING FUNCTIONS ------------

    public long addServiceBooking(String serviceType, String roomNo, String timeSlot, String notes, int price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_SERVICE_TYPE, serviceType);
        cv.put(COL_SERVICE_ROOM_NO, roomNo);
        cv.put(COL_SERVICE_TIME, timeSlot);
        cv.put(COL_SERVICE_NOTES, notes);
        cv.put(COL_SERVICE_PRICE, price);

        return db.insert(TABLE_SERVICES, null, cv);
    }

    public Cursor getAllServiceBookings() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_SERVICES, null);
    }
}
