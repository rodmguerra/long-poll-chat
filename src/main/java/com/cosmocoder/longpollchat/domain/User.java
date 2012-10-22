package com.cosmocoder.longpollchat.domain;

import com.cosmocoder.longpollchat.support.Listener;
import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "USERS")
public class User {

    @Id
    private String id;
    private String name;

    private transient Listener<String> statusMessageListener;
    private transient Listener<String> chatMessageListener;

    private User() {}

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

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            final User other = (User) obj;
            return Objects.equal(getId(), other.getId());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .toString();
    }
}
