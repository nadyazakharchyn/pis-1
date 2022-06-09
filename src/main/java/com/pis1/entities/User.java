package com.pis1.entities;

public class User {
    private long id;
    private String name;
    private String email;
    private String hash_password;

    public User(){

    }

    public User(String name, String email, String hash_password) {
        this.name = name;
        this.email = email;
        this.hash_password = hash_password;
    }

    public User(long id, String name, String email, String hash_password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hash_password = hash_password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash_password() {
        return hash_password;
    }

    public void setHash_password(String hash_password) {
        this.hash_password = hash_password;
    }
}