package com.example.mmm_tp3;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private List<com.example.mmm_tp3.User> users = new ArrayList<>();


    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        com.example.mmm_tp3.User currentUser = users.get(position);
        holder.firstName.setText(currentUser.firstName);
        holder.lastName.setText(currentUser.lastName);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<com.example.mmm_tp3.User> Users) {
        this.users = Users;
        notifyDataSetChanged();
    }

    public com.example.mmm_tp3.User getUserAt(int adapterPosition) {
        return users.get(adapterPosition);
    }

    class UserHolder extends RecyclerView.ViewHolder {
        private TextView firstName;
        private TextView lastName;

        public UserHolder(View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);
        }
    }
}