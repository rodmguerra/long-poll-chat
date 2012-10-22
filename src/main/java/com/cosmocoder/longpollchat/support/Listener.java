package com.cosmocoder.longpollchat.support;

public interface Listener<T> {
    void onEvent(T event);
}
