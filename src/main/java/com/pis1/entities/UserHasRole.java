package com.pis1.entities;

public class UserHasRole {
    private long id;
    private long user_id;
    private long role_id;

    public UserHasRole(long id, long user_id, long role_id) {
        this.id = id;
        this.user_id = user_id;
        this.role_id = role_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }
}
