package observer;

public class EmailNotificationObserver implements NotificationObserver {
    private Long id;
    private String email;

    public EmailNotificationObserver(Long id,String email) {
        this.id = id;
        this.email = email;
    }

    @Override
    public void update(String message) {
        System.out.println("Sending Email to " + email + ": " +"about the info regarding show :"+this.id+" "+ message);
    }

    @Override
    public Long getShowId() {
        return this.id;
    }

}

