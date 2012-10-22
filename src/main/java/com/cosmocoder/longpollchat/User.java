package com.cosmocoder.longpollchat;

public class User {

    private String id;
    private String name;

    private transient Listener<String> statusMessageListener;
    private transient Listener<String> chatMessageListener;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void sendStatusMessage(String message) {
        if (statusMessageListener != null) {
            statusMessageListener.onEvent(message);
        }
    }

    public void sendChatMessage(String message) {
        if (chatMessageListener != null) {
            chatMessageListener.onEvent(message);
        }
    }

    public Listener<String> getStatusMessageListener() {
        return statusMessageListener;
    }

    public void setStatusMessageListener(Listener<String> statusMessageListener) {
        this.statusMessageListener = statusMessageListener;
    }

    public Listener<String> getChatMessageListener() {
        return chatMessageListener;
    }

    public void setChatMessageListener(Listener<String> chatMessageListener) {
        this.chatMessageListener = chatMessageListener;
    }
}
