package com.aarya.bms.bms.merging.observer;

import java.util.HashMap;
import java.util.Set;

public class BookingNotifiationRepoImpl implements BookingNotificationRepo {
    public static BookingNotifiationRepoImpl bookingNotifiationRepo;
    HashMap<String, Set<NotificationObserver>> bookingNotificationHashMap;

    private BookingNotifiationRepoImpl(){
        bookingNotificationHashMap =new HashMap<>();
    }

    public static BookingNotifiationRepoImpl getBookingNotificationRepo(){
        if(bookingNotifiationRepo==null){
            bookingNotifiationRepo = new BookingNotifiationRepoImpl();
            return bookingNotifiationRepo;
        }else{
            return bookingNotifiationRepo;
        }
    }

    @Override
    public boolean isPresent(String id) {
        return bookingNotificationHashMap.containsKey(id);
    }

    @Override
    public void putObserver(String id, Set<NotificationObserver> observers) {
        bookingNotificationHashMap.put(id,observers);
    }

    @Override
    public Set<NotificationObserver> getObservers(String id) {
        return bookingNotificationHashMap.get(id);
    }

    @Override
    public void removeObserver(String id, NotificationObserver observer) {
        bookingNotificationHashMap.remove(id,bookingNotificationHashMap.get(id).remove(observer));
    }
}
