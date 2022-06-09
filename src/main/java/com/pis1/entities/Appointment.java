package com.pis1.entities;

import java.sql.Time;
import java.sql.Date;

public class Appointment {
    private long id;
    private Date date;
    private Time time;

    private long user_id;

    private long service_id;
    private long service_user_id;

    public Appointment() {
    }

    public Appointment(Date date, Time time, long user_id, long service_id, long service_user_id) {
        this.date = date;
        this.time = time;
        this.user_id = user_id;
        this.service_id = service_id;
        this.service_user_id = service_user_id;
    }

    public Appointment(long id, Date date, Time time, long user_id, long service_id, long service_user_id) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.user_id = user_id;
        this.service_id = service_id;
        this.service_user_id = service_user_id;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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
