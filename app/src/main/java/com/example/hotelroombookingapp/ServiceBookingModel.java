package com.example.hotelroombookingapp;

public class ServiceBookingModel {
    private int id;
    private String serviceType;
    private String roomNo;
    private String timeSlot;
    private String notes;
    private int price;

    public ServiceBookingModel() {}

    public ServiceBookingModel(int id, String serviceType, String roomNo, String timeSlot, String notes, int price) {
        this.id = id;
        this.serviceType = serviceType;
        this.roomNo = roomNo;
        this.timeSlot = timeSlot;
        this.notes = notes;
        this.price = price;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }

    public String getRoomNo() { return roomNo; }
    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }

    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}
