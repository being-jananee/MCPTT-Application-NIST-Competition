package com.example.application.Messaging.UserFragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.application.DatabaseUtils;
import com.example.application.Messaging.MessageActivity;
import com.example.application.R;
import com.example.application.Domain.UserData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    public Context ms;
    public List<UserData> users;
    public UserData currentUser;
    public DatabaseUtils db = new DatabaseUtils();

    public UserAdapter(Context ms, ArrayList<UserData> users, UserData currentUser) {
        this.ms = ms;
        this.users = users;
        this.currentUser = currentUser;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserAdapter.UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        String user_name = users.get(position).getDisplayName();
        holder.user_name.setText(user_name);
    }
    //TODO: Fix UserAdapter
    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView user_name;
        public final String TAG = this.getClass().getSimpleName();

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ms, MessageActivity.class);
                    i.putExtra("currentUser", currentUser);
                    ArrayList<String> otherUserIds = new ArrayList<>(Arrays.asList(user_name.getText().toString().split(", ")));
                    Log.d(TAG, "onClick: "+otherUserIds.size() + otherUserIds.get(0));
                    i.putParcelableArrayListExtra("otherUsers", new ArrayList<>(db.getUserListById(otherUserIds)));
                    ms.startActivity(i);
                }
            });
            user_name = itemView.findViewById(R.id.user_name);
        }
    }
}
