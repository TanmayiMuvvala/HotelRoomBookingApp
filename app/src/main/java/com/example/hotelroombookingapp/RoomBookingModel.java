package com.example.hotelroombookingapp;

public class RoomBookingModel {
    private int bookingId;
    private int roomId;
    private String customerName;
    private String checkinDate;
    private String checkoutDate;
    private int totalPrice;

    public RoomBookingModel() {}

    public RoomBookingModel(int bookingId, int roomId, String customerName, String checkinDate, String checkoutDate, int totalPrice) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.customerName = customerName;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCheckinDate() { return checkinDate; }
    public void setCheckinDate(String checkinDate) { this.checkinDate = checkinDate; }

    public String getCheckoutDate() { return checkoutDate; }
    public void setCheckoutDate(String checkoutDate) { this.checkoutDate = checkoutDate; }

    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }
}
