package com.pis1.dao.DAOs;

import com.pis1.dao.interfaces.RoleInterface;
import com.pis1.entities.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO implements RoleInterface {
    private Connection connection;
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_NAME = "name";
    private Statement statement;

    public RoleDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    private Role getRole(ResultSet resultSet) throws SQLException{
        long id = resultSet.getLong(COLUMN_ID);
        String name = resultSet.getString(COLUMN_NAME);
        return new Role(id, name);
    }

    @Override
    public Role findById(long id) {
        String query = "SELECT * FROM role WHERE role.id=" + id;

        Role role = new Role();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            role = getRole(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

    @Override
    public List<Role> findAll() {
        String query = "SELECT * FROM role";
        List<Role> roleList = new ArrayList<Role>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Role role = getRole(resultSet);
                roleList.add(role);
            }

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return roleList;
    }

    @Override
    public void createRole(Role role) {
        String query = "INSERT INTO role (name) VALUES ('";
        query += role.getName()+"', '"
               +") " ;

        long id = 0;

        try {
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                id = resultSet.getLong("id");
            }

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateById(long id, String new_role) {

    }

    @Override
    public void deleteById(long id) {

    }
}
