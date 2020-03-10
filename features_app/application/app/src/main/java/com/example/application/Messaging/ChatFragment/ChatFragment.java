package com.example.application.Messaging.ChatFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.application.DatabaseUtils;
import com.example.application.Domain.UserDataLite;
import com.example.application.Messaging.MessageUtils;
import com.example.application.Messaging.UserFragment.UserAdapter;
import com.example.application.R;
import com.example.application.Domain.UserData;
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

    Set<UserDataLite> userNames = new HashSet<>();
    private DatabaseReference messageRef = FirebaseDatabase.getInstance().getReference().child("chats");
    DatabaseUtils db = new DatabaseUtils();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final UserData currentUser = getArguments().getParcelable("currentUser");
        View view =  inflater.inflate(R.layout.fragment_chat, container, false);
        final RecyclerView rv = view.findViewById(R.id.chat_recycle);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        messageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userNames.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(MessageUtils.checkIfChatIncludesYou(currentUser, snapshot)) { ;
                        ArrayList<String> chatUsers = MessageUtils.getOtherChatUsers(currentUser, snapshot);
                        if(chatUsers.size() > 1) {
                            Log.d(TAG, "onDataChange: "+chatUsers.size() + "CHATS");
                            userNames.add(MessageUtils.makeGroup(db.getUserListById(chatUsers)));
                        } else if(chatUsers.size() == 1) {
                            userNames.add(UserDataLite.fromUser(db.getUserById(chatUsers.get(0))));
                        }
                    }
                }
                UserAdapter ua = new UserAdapter(getContext(), new ArrayList<>(userNames), currentUser);
                rv.setAdapter(ua);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }
}
