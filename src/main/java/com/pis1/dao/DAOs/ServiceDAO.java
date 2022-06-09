package com.pis1.dao.DAOs;

import com.pis1.dao.interfaces.ServiceInterface;
import com.pis1.entities.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO implements ServiceInterface {

    private Connection connection;
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_NAME = "name";
    private final static String COLUMN_PRICE = "price";
    private final static String COLUMN_MASTERTITLE = "master_title";
    private final static String COLUMN_MASTERID = "user_id";
    private Statement statement;

    public ServiceDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    private Service getService(ResultSet resultSet) throws SQLException{
        long id = resultSet.getLong(COLUMN_ID);
        String name = resultSet.getString(COLUMN_NAME);
        long price = resultSet.getLong(COLUMN_PRICE);
        String masterTitle = resultSet.getString(COLUMN_MASTERTITLE);
        long masterId = resultSet.getLong(COLUMN_MASTERID);

        return new Service(id, name, price, masterTitle, masterId);
    }
    @Override
    public void printService(Service service){
        System.out.println(service.getId()+" "+service.getName()+" "+service.getPrice()+" UAH "+service.getMaster_title()+" "+service.getMaster_id());
    }

    @Override
    public Service findById(long id) {
        String query = "SELECT * FROM service WHERE service.id=" + id;

        Service service = new Service();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            service = getService(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return service;
    }

    @Override
    public List<Service> findAll() {
        String query = "SELECT * FROM service";
        List<Service> serviceList = new ArrayList<Service>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Service service = getService(resultSet);
                serviceList.add(service);
            }

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return serviceList;
    }

    @Override
    public long createService(Service service) {
        String query = "INSERT INTO service (name, price, master_title, user_id) VALUES ('";
        query += service.getName()+"', '"
                +service.getPrice()+"', '"
                +service.getMaster_title()+"', '"
                +service.getMaster_id()+"') " ;

        long createdId = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                createdId = resultSet.getLong(1);
            }
            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return createdId;
    }

    @Override
    public void updateById(long id, long newPrice) {
        String query = "UPDATE service SET price = "+newPrice+" WHERE id="+id;

        try {
            Statement statement = connection.createStatement();
            long newId = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            statement.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteById(long id) {
        String query = "DELETE FROM service WHERE id="+id;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}
