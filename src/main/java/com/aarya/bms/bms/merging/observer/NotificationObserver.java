package com.aarya.bms.bms.merging.observer;

public interface NotificationObserver {
    void update(String message);
    String getShowId();
}

