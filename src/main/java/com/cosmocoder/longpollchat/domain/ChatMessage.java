package com.cosmocoder.longpollchat.domain;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "CHAT_MESSAGES")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    private Date timestamp;

    private ChatMessage () {}

    public ChatMessage(User user, String text) {
        this.text = text;
        this.timestamp = new Date();
        this.user = user;
    }

    private Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ChatMessage) {
            final ChatMessage other = (ChatMessage) obj;
            return Objects.equal(getId(), other.getId());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("user", user)
                .add("timestamp", timestamp)
                .add("text", text)
                .toString();
    }
}
