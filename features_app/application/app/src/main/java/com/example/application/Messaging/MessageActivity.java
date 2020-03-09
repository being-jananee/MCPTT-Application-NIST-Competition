package com.example.application.Messaging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.application.R;
import com.example.application.Domain.UserData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MessageActivity extends AppCompatActivity {

    public ArrayList<InstantMessage> messageList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MessageItemAdapter messageItemAdapter;
    private EditText messageContent;
    public UserData currentUser;
    public ArrayList<UserData> otherUsers;
    private DatabaseReference messageRef = FirebaseDatabase.getInstance().getReference().child("chats");
    private static DateFormat dbFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.sss", Locale.US);
    private static DateFormat displayFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.US);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        currentUser = getIntent().getParcelableExtra("currentUser");
        otherUsers = getIntent().getParcelableArrayListExtra("otherUsers");
        recyclerView = findViewById(R.id.message_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageRef.addValueEventListener(listenForMessages());
        messageContent = findViewById(R.id.edittext_chatbox);
        Button mButton = findViewById(R.id.button_chatbox_send);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(currentUser.getMcpttID(), messageContent.getText().toString());

            }
        });
    }

    public ValueEventListener listenForMessages() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messageList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    boolean isYourChat = MessageUtils.checkIfThisChatIsTheChatYouSelected(currentUser, otherUsers, snapshot);
                    if(isYourChat) {
                        Log.d("is your chat", "onDataChange: "+snapshot.getKey());
                        for (DataSnapshot messageSnapshot : snapshot.child("messages").getChildren()) {
                            InstantMessage message = messageSnapshot.getValue(InstantMessage.class);
                            if(message != null) {
                                String oldTimestamp = message.timeStamp;
                                try {
                                    message.timeStamp = displayFormat.format(dbFormat.parse(message.timeStamp));
                                } catch (Exception pe) {
                                    message.timeStamp = oldTimestamp;
                                }
                                messageList.add(message);
                            }
                        }
                        Collections.sort(messageList, InstantMessage.Comparators.TIME);
                        messageItemAdapter = new MessageItemAdapter(MessageActivity.this, messageList, currentUser);
                        recyclerView.setAdapter(messageItemAdapter);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
    }

    void sendMessage(String sender, String content) {
        HashMap<String, String> map = new HashMap<>();
        map.put("sender", sender);
        map.put("content", content);
        map.put("timeStamp", dbFormat.format(new Date()));
        System.out.println(map.get("sender"));
        System.out.println(map.get("content"));
        System.out.println(map.get("timeStamp"));
        messageRef.child(MessageUtils.yourChatId(currentUser, otherUsers)).child("messages").push().setValue(map);
        messageContent.setText("");
    }
}
