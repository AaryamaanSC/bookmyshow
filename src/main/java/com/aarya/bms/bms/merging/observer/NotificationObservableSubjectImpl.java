package observer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class NotificationObservableSubjectImpl implements NotificationObservableSubject{

    BookingNotificationRepo bookingNotificationRepo = BookingNotifiationRepoImpl.getBookingNotificationRepo();

    @Override
    public void addObserver(NotificationObserver observer) {
        Long id = observer.getShowId();
        if(!bookingNotificationRepo.isPresent(id)){
            Set<NotificationObserver> observers = new HashSet<>();
            observers.add(observer);
            bookingNotificationRepo.putObserver(id,observers);
        }else {
            Set<NotificationObserver> observers = bookingNotificationRepo.getObservers(id);
            observers.add(observer);
            bookingNotificationRepo.putObserver(id,observers);
        }
    }

    @Override
    public void removeObserver(NotificationObserver observer) {
        Long id = observer.getShowId();
        if(bookingNotificationRepo.isPresent(id)){
            Set<NotificationObserver> observers = bookingNotificationRepo.getObservers(id);
            if(observers.contains(observers)){
                bookingNotificationRepo.removeObserver(id,observer);
            }
        }
    }

    @Override
    public void notifySubscriber(Long showId, String message) {
        Set<NotificationObserver> observers = bookingNotificationRepo.getObservers(showId);
        for(NotificationObserver observer:observers){
            observer.update(message);
        }
    }
}
