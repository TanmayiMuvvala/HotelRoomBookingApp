package com.example.hotelroombookingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    Context context;
    ArrayList<RoomModel> roomList;
    String checkinDate;
    String checkoutDate;

    public RoomAdapter(Context context, ArrayList<RoomModel> roomList) {
        this.context = context;
        this.roomList = roomList;
        this.checkinDate = "";
        this.checkoutDate = "";
    }

    public RoomAdapter(Context context, ArrayList<RoomModel> roomList, String checkinDate, String checkoutDate) {
        this.context = context;
        this.roomList = roomList;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        RoomModel room = roomList.get(position);

        holder.txtRoomNumber.setText("Room " + room.getRoomNumber());
        holder.txtRoomType.setText(room.getType());
        holder.txtPrice.setText("₹ " + room.getPrice() + " / night");
        holder.txtAvailability.setText(room.getAvailability());

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, RoomDetailsActivity.class);
            i.putExtra("room_id", room.getId());
            i.putExtra("checkin_date", checkinDate);
            i.putExtra("checkout_date", checkoutDate);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    class RoomViewHolder extends RecyclerView.ViewHolder {

        TextView txtRoomNumber, txtRoomType, txtPrice, txtAvailability;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);

            txtRoomNumber = itemView.findViewById(R.id.txtRoomNumber);
            txtRoomType = itemView.findViewById(R.id.txtRoomType);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtAvailability = itemView.findViewById(R.id.txtAvailability);
        }
    }
}
