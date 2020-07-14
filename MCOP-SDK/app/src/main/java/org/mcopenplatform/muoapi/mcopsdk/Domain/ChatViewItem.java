package org.mcopenplatform.muoapi.mcopsdk.Domain;


public class ChatViewItem {
    public UserDataLite userOrGroup;
    public String mostRecentChat;
    public String mostRecentSender;
    public boolean unread;

    public ChatViewItem(UserDataLite user, String mostRecentChat, String mostRecentSender) {
        this.userOrGroup = user;
        this.mostRecentChat = mostRecentChat;
        this.mostRecentSender = mostRecentSender;
    }

    public ChatViewItem() {
        this.unread = false;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public boolean getUnread() {
        return this.unread;
    }

    public UserDataLite getUserOrGroup() {
        return userOrGroup;
    }

    public void setUserOrGroup(UserDataLite user) {
        this.userOrGroup = user;
    }

    public String getMostRecentChat() {
        return mostRecentChat;
    }

    public void setMostRecentChat(String mostRecentChat) {
        this.mostRecentChat = mostRecentChat;
    }

    public String getMostRecentSender() {
        return mostRecentSender;
    }

    public void setMostRecentSender(String mostRecentSender) {
        this.mostRecentSender = mostRecentSender;
    }
}