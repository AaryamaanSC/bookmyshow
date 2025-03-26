package com.aarya.bms.bms.merging.observer;

import java.util.Set;

public interface BookingNotificationRepo {

    boolean isPresent(String id);

    void putObserver(String id, Set<NotificationObserver> observers);

    Set<NotificationObserver> getObservers(String id);

    void removeObserver(String id, NotificationObserver observer);
}
