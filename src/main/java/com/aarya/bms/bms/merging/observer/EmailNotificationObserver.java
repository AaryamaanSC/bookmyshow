package com.aarya.bms.bms.merging.observer;

public class EmailNotificationObserver implements NotificationObserver {
    private String id;
    private String email;

    public EmailNotificationObserver(String id,String email) {
        this.id = id;
        this.email = email;
    }

    @Override
    public void update(String message) {
        System.out.println("Sending Email to " + email + ": " +"about the info regarding show :"+this.id+" "+ message);
    }

    @Override
    public String getShowId() {
        return this.id;
    }

}

