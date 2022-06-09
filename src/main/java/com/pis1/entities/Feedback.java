package com.pis1.entities;

public class Feedback {
    private long id;
    private int rating;
    private String text;

    private long user_id;

    private long service_id;
    private long service_user_id;

    public Feedback(long id, int rating, String text, long user_id, long service_id, long service_user_id) {
        this.id = id;
        this.rating = rating;
        this.text = text;
        this.user_id = user_id;
        this.service_id = service_id;
        this.service_user_id = service_user_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getService_id() {
        return service_id;
    }

    public void setService_id(long service_id) {
        this.service_id = service_id;
    }

    public long getService_user_id() {
        return service_user_id;
    }

    public void setService_user_id(long service_user_id) {
        this.service_user_id = service_user_id;
    }
}
