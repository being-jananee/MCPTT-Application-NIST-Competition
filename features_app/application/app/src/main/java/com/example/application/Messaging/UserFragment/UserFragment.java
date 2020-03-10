package com.example.application.Messaging.UserFragment;

import android.os.Bundle;

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
import com.example.application.R;
import com.example.application.Domain.UserData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserFragment extends Fragment {

    public DatabaseUtils db = new DatabaseUtils();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        List<UserDataLite> users = MessageUtils.toLite(new ArrayList<>(db.getUsers()));
        Log.d("TAGAGA", "onCreateView: "+users.get(0).toString());
        assert getArguments() != null;
        UserData currentUser = getArguments().getParcelable("currentUser");
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        RecyclerView rv = view.findViewById(R.id.user_recycle);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        UserAdapter ua = new UserAdapter(getContext(), new ArrayList<>(users), currentUser);
        Log.d("TAG", "onCreateView: "+ua.userNames.size());
        rv.setAdapter(ua);

        return view;
    }

}
