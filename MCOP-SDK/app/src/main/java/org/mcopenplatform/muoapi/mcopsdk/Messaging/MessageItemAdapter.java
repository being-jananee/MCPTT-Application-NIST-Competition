package org.mcopenplatform.muoapi.mcopsdk.Messaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.mcopenplatform.muoapi.R;
import org.mcopenplatform.muoapi.mcopsdk.Domain.UserData;

import java.util.List;

public class MessageItemAdapter extends RecyclerView.Adapter<MessageItemAdapter.MessageItemViewHolder> {

    public Context ms;
    public List<InstantMessage> messages;
    public UserData currentUser;
    public int RECIEVED_VIEW_TYPE = 0;
    public int SENT_VIEW_TYPE = 1;

    public MessageItemAdapter(Context ms, List<InstantMessage> messages, UserData currentUser) {
        this.ms = ms;
        this.messages = messages;
        this.currentUser = currentUser;
    }

    @NonNull
    @Override
    public MessageItemAdapter.MessageItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == RECIEVED_VIEW_TYPE) {
            return new MessageItemViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.receive_instant_message_item, parent, false));
        } else {
            return new MessageItemViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.send_instant_message_item, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).sender.equals(currentUser.getMcpttID()) ? SENT_VIEW_TYPE : RECIEVED_VIEW_TYPE;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageItemAdapter.MessageItemViewHolder holder, int position) {
        InstantMessage message = messages.get(position);
        holder.content.setText(message.content);
        holder.timestamp.setText(message.clientTimeStamp);
        if(!message.sender.equals(currentUser.getMcpttID())) {
            holder.username.setText(message.sender);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class MessageItemViewHolder extends RecyclerView.ViewHolder {

        public TextView timestamp;
        public TextView content;
        public TextView username;

        public MessageItemViewHolder(@NonNull View itemView) {
            super(itemView);
            timestamp = itemView.findViewById(R.id.text_message_time);
            content = itemView.findViewById(R.id.text_message_body);
            username = itemView.findViewById(R.id.text_message_name);
            content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    timestamp.setVisibility(timestamp.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                }
            });
        }
    }
}