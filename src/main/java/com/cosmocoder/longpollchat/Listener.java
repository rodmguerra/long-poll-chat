package com.cosmocoder.longpollchat;

public interface Listener<T> {
    void onEvent(T event);
}
