package observer;

import java.util.HashMap;
import java.util.Set;

public class BookingNotifiationRepoImpl implements BookingNotificationRepo{
    public static BookingNotifiationRepoImpl bookingNotifiationRepo;
    HashMap<Long, Set<NotificationObserver>> bookingNotificationHashMap;

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
    public boolean isPresent(Long id) {
        return bookingNotificationHashMap.containsKey(id);
    }

    @Override
    public void putObserver(Long id, Set<NotificationObserver> observers) {
        bookingNotificationHashMap.put(id,observers);
    }

    @Override
    public Set<NotificationObserver> getObservers(Long id) {
        return bookingNotificationHashMap.get(id);
    }

    @Override
    public void removeObserver(Long id, NotificationObserver observer) {
        bookingNotificationHashMap.remove(id,bookingNotificationHashMap.get(id).remove(observer));
    }
}
