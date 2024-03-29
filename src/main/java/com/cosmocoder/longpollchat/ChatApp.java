package com.cosmocoder.longpollchat;

import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.google.common.collect.Sets.newHashSet;

@Service
public class ChatApp {

    private final ConcurrentHashMap<String, User> users;
    private final Lock lock;

    public ChatApp() {
        this.users = new ConcurrentHashMap<String, User>();
        this.lock = new ReentrantLock();
    }

    public String joinRoom(String id, String name) {
        lock.lock();
        try {
            User currentUser = new User(id, name);
            for (User user : users.values()) {
                user.sendStatusMessage(MessageFormat.format("{0} has joined room", name));
            }
            users.put(id, currentUser);
        } finally {
            lock.unlock();
        }
        return String.format("Welcome %s", name);
    }

    public void listenToStatusMessages(String id, Listener<String> listener) {
        users.get(id).setStatusMessageListener(listener);
    }

    public void listenToChatMessages(String id, Listener<String> listener) {
        users.get(id).setChatMessageListener(listener);
    }

    public String sendChatMessage(String id, String message) {
        lock.lock();
        try {
            User currentUser = users.get(id);
            Set<User> otherUsers = newHashSet(users.values());
            otherUsers.remove(currentUser);
            String otherUsersMessage = MessageFormat.format("{0} sayed: {1}", currentUser.getName(), message);
            for (User user : otherUsers) {
                user.sendChatMessage(otherUsersMessage);
            }
        } finally {
            lock.unlock();
        }
        return MessageFormat.format("You sayed: {0}", message);
    }
}