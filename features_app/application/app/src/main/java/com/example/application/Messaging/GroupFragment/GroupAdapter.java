package com.example.application.Messaging.GroupFragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;
import com.example.application.Domain.UserData;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    public Context ms;
    public List<UserData> users;

    public GroupAdapter(Context ms, List<UserData> users) {
        this.ms = ms;
        this.users = users;
    }

    @NonNull
    @Override
    public GroupAdapter.GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroupAdapter.GroupViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.GroupViewHolder holder, int position) {
        String user_name = users.get(position).getDisplayName();
        boolean checked = users.get(position).isChecked();
        holder.user_name.setText(user_name);
        holder.checkBox.setSelected(checked);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {

        public TextView user_name;
        public CheckBox checkBox;
        public final String TAG = this.getClass().getSimpleName();

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkBox.performClick();
                }
            });
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.d(TAG, "onClick: "+isChecked + " " + users.get(getAdapterPosition()).getDisplayName());
                    users.get(getAdapterPosition()).setChecked(isChecked);
                }
            });
            user_name = itemView.findViewById(R.id.user_name);
        }
    }
}
