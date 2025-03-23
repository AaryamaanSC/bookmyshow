package observer;

import java.util.Set;

public interface BookingNotificationRepo {

    boolean isPresent(Long id);

    void putObserver(Long id, Set<NotificationObserver> observers);

    Set<NotificationObserver> getObservers(Long id);

    void removeObserver(Long id, NotificationObserver observer);
}
