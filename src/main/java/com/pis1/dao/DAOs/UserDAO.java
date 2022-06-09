package com.pis1.dao.DAOs;

import com.pis1.dao.interfaces.UserInterface;
import com.pis1.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements UserInterface {
    private Connection connection;
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_EMAIL = "email";
    private final static String COLUMN_HASHPASSWORD = "hash_password";
    private final static String COLUMN_NAME = "name";
    private Statement statement;

    public UserDAO(final Connection connection) throws SQLException{
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    public User getUser(ResultSet resultSet) throws SQLException{
        long id = resultSet.getLong(COLUMN_ID);
        String email = resultSet.getString(COLUMN_EMAIL);
        String hashPassword = resultSet.getString(COLUMN_HASHPASSWORD);
        String name = resultSet.getString(COLUMN_NAME);
        return new User(id, name, email, hashPassword);
    }

    @Override
    public User findById(long id) {
        String query = "SELECT * FROM user WHERE user.id=" + id;

        User user = new User();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            user = getUser(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User findByEmail(String email) {
        String query = "SELECT * FROM user WHERE user.email=" + email;

        User user = new User();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            user = getUser(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM user";

        List<User> userList = new ArrayList();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = getUser(resultSet);
                userList.add(user);
            }
            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void create(User user) {
        String query = "INSERT INTO user (name, email, hash_password) VALUES ('";
        query += user.getName()+"', '"
                +user.getEmail()+"', '"
                +user.getHash_password()+"') " ;

        long id = 0;

        try {
            statement.executeUpdate(query);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteById(long id) {
        String query = "DELETE FROM user WHERE id="+id;

        try {
            statement.executeUpdate(query);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void print(User user) {
        System.out.println(user.getId()+" "+user.getName()+" "+user.getEmail());

    }

    @Override
    public void printAll(List<User> users) {
        users.forEach((user) -> {
            System.out.println(user.getId()+" "+user.getName()+" "+user.getEmail());
        });
    }
}
