package com.example.application.Messaging.GroupFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.application.DatabaseUtils;
import com.example.application.Messaging.MessageActivity;
import com.example.application.R;
import com.example.application.Domain.UserData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

public class GroupFragment extends Fragment {

    public DatabaseUtils db = new DatabaseUtils();

    public FloatingActionButton b;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final List<UserData> users = db.getUsers();
        assert getArguments() != null;
        final UserData currentUser = getArguments().getParcelable("currentUser");
        View view = inflater.inflate(R.layout.fragment_group, container, false);
        b = view.findViewById(R.id.group_button);
        RecyclerView rv = view.findViewById(R.id.group_recycle);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        final GroupAdapter ua = new GroupAdapter(getContext(), users);
        Log.d("TAG", "onCreateView: "+ua.users.size());
        rv.setAdapter(ua);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<UserData> toSend = new ArrayList<>();
                for(UserData user : ua.users) {
                    Log.d(TAG, "onClick: "+user.isChecked()+" "+user.getDisplayName());
                    if(user.isChecked()) {
                        toSend.add(user);
                    }
                }
                Intent i = new Intent(getContext(), MessageActivity.class);
                i.putExtra("currentUser", currentUser);
                i.putParcelableArrayListExtra("otherUsers", toSend);
                startActivity(i);
            }
        });
        return view;
    }
}
