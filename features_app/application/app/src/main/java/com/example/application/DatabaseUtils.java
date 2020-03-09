package com.example.application;

import com.example.application.Domain.UserData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseUtils {

    private List<UserData> users = Arrays.asList(
            new UserData("SFF", "User1"),
            new UserData("SDD", "User2"),
            new UserData("SCC", "User3")
    );

    public List<UserData> getUsers() {
        return users;
    }

    public List<UserData> getUserListById(ArrayList<String> ids) {
        List<UserData> userData = new ArrayList<>();
        for(UserData user : users) {
            if(ids.contains(user.getMcpttID())) {
                userData.add(user);
            }
        }
        return userData;
    }

    public UserData getUserById(String id) {
        for(UserData user : users) {
            if(user.getMcpttID().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(UserData user) {
        users.add(user);
    }

    public void removeUser(UserData user) {
        users.remove(user);
    }
}
