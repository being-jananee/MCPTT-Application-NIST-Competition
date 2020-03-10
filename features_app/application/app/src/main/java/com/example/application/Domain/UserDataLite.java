package com.example.application.Domain;

import androidx.annotation.NonNull;

public class UserDataLite {
    private String mcpttID;
    private String displayName;

    public UserDataLite(){}

    public String getMcpttID() {
        return mcpttID;
    }

    public void setMcpttID(String mcpttID) {
        this.mcpttID = mcpttID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public static UserDataLite fromUser(UserData ud) {
        UserDataLite userDataLite = new UserDataLite();
        userDataLite.setMcpttID(ud.getMcpttID());
        userDataLite.setDisplayName(ud.getDisplayName());
        return userDataLite;
    }

    @NonNull
    @Override
    public String toString() {
        return this.displayName + "\n" + this.mcpttID;

    }
}
