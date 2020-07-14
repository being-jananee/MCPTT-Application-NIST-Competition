package org.mcopenplatform.muoapi.mcopsdk.Messaging.ChatFragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.mcopenplatform.muoapi.mcopsdk.DatabaseUtils;
import org.mcopenplatform.muoapi.mcopsdk.Domain.ChatViewItem;
import org.mcopenplatform.muoapi.mcopsdk.Domain.UserDataLite;
import org.mcopenplatform.muoapi.mcopsdk.Messaging.MessageActivity;
import org.mcopenplatform.muoapi.R;
import org.mcopenplatform.muoapi.mcopsdk.Domain.UserData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    public Context ms;
    public List<ChatViewItem> items;
    public UserData currentUser;
    public DatabaseUtils db = new DatabaseUtils();

    public ChatAdapter(Context ms, ArrayList<ChatViewItem> items, UserData currentUser) {
        this.ms = ms;
        this.items = items;
        this.currentUser = currentUser;
    }

    @NonNull
    @Override
    public ChatAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatAdapter.ChatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ChatViewHolder holder, int position) {
        String user_name = items.get(position).getUserOrGroup().getDisplayName();
        String content = items.get(position).getMostRecentSender() + ": " + (items.get(position).getMostRecentChat().length() > 20
                ? items.get(position).getMostRecentChat().substring(0, 20) + "..."
                : items.get(position).getMostRecentChat());
        holder.user_name.setText(user_name);
        holder.content.setText(content);
        holder.unread.setVisibility(items.get(position).getUnread() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {

        public final String TAG = this.getClass().getSimpleName();

        public TextView user_name;
        public TextView content;
        public ImageView unread;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    items.get(getAdapterPosition()).setUnread(false);
                    Intent i = new Intent(ms, MessageActivity.class);
                    i.putExtra("currentUser", currentUser);;
                    ArrayList<String> otherUserIds = new ArrayList<>(Arrays.asList(items.get(getAdapterPosition()).getUserOrGroup().getMcpttID().split(":")));
                    Log.d(TAG, "onClick: "+otherUserIds.size() + otherUserIds.get(0));
                    i.putParcelableArrayListExtra("otherUsers", new ArrayList<>(db.getUserListById(otherUserIds)));
                    ms.startActivity(i);
                }
            });
            unread = itemView.findViewById(R.id.unread_icon);
            user_name = itemView.findViewById(R.id.username);
            content = itemView.findViewById(R.id.content);
        }
    }
}