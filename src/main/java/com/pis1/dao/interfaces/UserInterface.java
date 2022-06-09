package com.pis1.dao.interfaces;

import com.pis1.entities.User;

import java.util.List;

public interface UserInterface {
    User findById(long id);
    User findByEmail(String email);
    List<User> findAll();
    void create(User user);
    //void updateById(long id, String newEmail);
    void deleteById(long id);
    void print(User user);
    void printAll(List<User> users);
}
