package com.example.interntestapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.interntestapp.Data.UserDetailModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListAdapterViewHolder> {

    private Context context;
    public static List<UserDetailModel> list;

    public UserListAdapter(Context context,List<UserDetailModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UserListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_item,parent,false);
        return new UserListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapterViewHolder holder, int position) {

        if(list!=null){
            if(list.size()>0){
                holder.nameTextView.setText(list.get(position).getName());
                holder.addressTv.setText("Address: "+list.get(position).getAddress());
                holder.emailTv.setText("Email: "+list.get(position).getEmailAddress());
                holder.phoneNumberTv.setText("Contact Number: +91"+list.get(position).getPhoneNumber());
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

     class UserListAdapterViewHolder extends RecyclerView.ViewHolder {
        public  TextView nameTextView,phoneNumberTv,emailTv,addressTv;
        public UserListAdapterViewHolder(@NonNull View itemView)
        {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            phoneNumberTv = itemView.findViewById(R.id.phoneNumberTextView);
            emailTv = itemView.findViewById(R.id.emailTextview);
            addressTv = itemView.findViewById(R.id.addressTextView);
        }
    }
}
