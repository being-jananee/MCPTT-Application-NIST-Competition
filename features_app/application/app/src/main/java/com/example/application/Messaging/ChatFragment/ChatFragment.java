package com.example.application.Messaging.ChatFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.application.DatabaseUtils;
import com.example.application.Domain.ChatViewItem;
import com.example.application.Domain.UserDataLite;
import com.example.application.Messaging.InstantMessage;
import com.example.application.Messaging.MessageUtils;
import com.example.application.Messaging.UserFragment.UserAdapter;
import com.example.application.R;
import com.example.application.Domain.UserData;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.content.ContentValues.TAG;

public class ChatFragment extends Fragment {

    ArrayList<ChatViewItem> chatView = new ArrayList<>();
    private DatabaseReference messageRef = FirebaseDatabase.getInstance().getReference().child("chats");
    DatabaseUtils db = new DatabaseUtils();
    ChatAdapter ua;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final UserData currentUser = getArguments().getParcelable("currentUser");
        View view =  inflater.inflate(R.layout.fragment_chat, container, false);
        final RecyclerView rv = view.findViewById(R.id.chat_recycle);
        rv.setHasFixedSize(true);
        ua = new ChatAdapter(getContext(), chatView, currentUser);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        messageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chatView.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(MessageUtils.checkIfChatIncludesYou(currentUser, snapshot)) {
                        ArrayList<String> chatUsers = MessageUtils.getOtherChatUsers(currentUser, snapshot);
                        UserDataLite user = null;
                        if(chatUsers.size() > 1) {
                            user = MessageUtils.makeGroup(db.getUserListById(chatUsers));
                        } else if(chatUsers.size() == 1) {
                            user = UserDataLite.fromUser(db.getUserById(chatUsers.get(0)));
                        }
                        ///messageRef.child(snapshot.getKey()).child("messages").addChildEventListener(listenForUnreadMessages(currentUser, user.getMcpttID()));
                        ChatViewItem item = new ChatViewItem();
                        item.setUserOrGroup(user);
                        InstantMessage im = null;
                        long i = snapshot.child("messages").getChildrenCount() - 1;
                        for(DataSnapshot s : snapshot.child("messages").getChildren()) {
                            Log.d(TAG, "onDataChange: Item #"+i);
                            if(i == 0) {
                                im = s.getValue(InstantMessage.class);
                            }
                            i--;
                        }
                        if(im != null) {
                            item.setMostRecentSender(im.sender);
                            item.setMostRecentChat(im.content);
                            chatView.add(item);
                        }
                    }
                    ua = new ChatAdapter(getContext(), chatView, currentUser);
                }
                Log.d(TAG, "onDataChange: "+chatView.size() + " ChatView");
                rv.setAdapter(ua);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

    private void setItemUnread(String idList) {
        for(ChatViewItem item : chatView) {
            if(item.userOrGroup.getMcpttID().equals(idList)) {
                item.unread = true;
            }
        }
        ua.notifyDataSetChanged();
    }

//    private ChildEventListener listenForUnreadMessages(final UserData currentUser, final String idList) {
//        return new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                if(!dataSnapshot.child("sender").getValue(String.class).equalsIgnoreCase(currentUser.getMcpttID())) {
//                    setItemUnread(idList);
//                }
//            }
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) { }
//        };
//    }
}
