package com.aarya.bms.bms.merging.observer;

public interface NotificationObservableSubject {
        void addObserver(NotificationObserver observer);
        void removeObserver(NotificationObserver observer);
        void notifySubscriber(Long showId,String message);
}
