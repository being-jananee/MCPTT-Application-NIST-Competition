package com.example.application.Messaging;

import android.util.Base64;
import android.util.Log;

import com.example.application.Domain.UserData;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MessageUtils {

    public static boolean checkIfThisChatIsTheChatYouSelected(UserData currentUser, ArrayList<UserData> otherUsers, DataSnapshot snapshot) {
        ArrayList<String> temp = new ArrayList<>();
        for(UserData user : otherUsers) {
            temp.add(user.getDisplayName());
        }
        if(snapshot.getKey() == null) return false;
        List<String> usersInChat = Arrays.asList(
                new String(Base64.decode(snapshot.getKey(), Base64.NO_WRAP | Base64.URL_SAFE))
                        .split(":"));
        temp.add(currentUser.getDisplayName());
        Collections.sort(temp);
        Collections.sort(usersInChat);
        return Arrays.equals(usersInChat.toArray(), temp.toArray());
    }

    public static boolean checkIfChatIncludesYou(UserData currentUser, DataSnapshot snapshot) {
        if(snapshot.getKey() == null) return false;
        List<String> usersInChat = Arrays.asList(
                new String(Base64.decode(snapshot.getKey(), Base64.NO_WRAP | Base64.URL_SAFE))
                        .split(":"));
        return usersInChat.contains(currentUser.getMcpttID());
    }

    public static ArrayList<String> getOtherChatUsers(UserData currentUser, DataSnapshot snapshot) {
        if(snapshot.getKey() == null) return new ArrayList<>();
        ArrayList<String> usersInChat = new ArrayList<>(Arrays.asList(
                new String(Base64.decode(snapshot.getKey(), Base64.NO_WRAP | Base64.URL_SAFE))
                        .split(":")));
        usersInChat.remove(currentUser.getMcpttID());
        return usersInChat;
    }

    public static String join(List<String> list, String delimiter) {
        StringBuilder joined = new StringBuilder();
        for(int i = 0;i<list.size();i++) {
            if(i < list.size() - 1)
                joined.append(list.get(i)).append(delimiter);
            else
                joined.append(list.get(i));
        }
        return joined.toString();
    }

    public static String yourChatId(UserData currentUser, List<UserData> otherUsers) {
        List<String> allUsers = new ArrayList<>();
        for(UserData user : otherUsers) {
            allUsers.add(user.getMcpttID());
        }
        allUsers.add(currentUser.getMcpttID());
        Log.d("TAG", "yourChatId: "+allUsers.size());
        if(allUsers.size() > 2) {
            String s = "  ";
            for(String user : allUsers) {
                s += user+":";
            }
            Log.d("TAGAGAGA", "yourChatId: "+s);
        }
        Collections.sort(allUsers);
        String joined = join(allUsers, ":");
        return Base64.encodeToString(joined.getBytes(), Base64.NO_WRAP | Base64.URL_SAFE);
    }
}
