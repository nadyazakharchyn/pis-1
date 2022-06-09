package com.pis1.dao.DAOs;

import com.pis1.dao.interfaces.AppointmentInterface;
import com.pis1.entities.Appointment;
import com.pis1.entities.Service;
import com.pis1.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO implements AppointmentInterface {

    private Connection connection;
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_DATE = "date";
    private final static String COLUMN_TIME = "time";
    private final static String COLUMN_SERVICEID = "service_id";
    private final static String COLUMN_SERVICEUSERID = "service_user_id";
    private final static String COLUMN_USERID = "user_id";
    private Statement statement;

    public AppointmentDAO(final Connection connection) throws SQLException{
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    private Appointment getAppointment(ResultSet resultSet) throws SQLException{
        long id = resultSet.getLong(COLUMN_ID);
        Date date = resultSet.getDate(COLUMN_DATE);
        Time time = resultSet.getTime(COLUMN_TIME);
        long serviceId = resultSet.getLong(COLUMN_SERVICEID);
        long serviceUserId = resultSet.getLong(COLUMN_SERVICEUSERID);
        long userId = resultSet.getLong(COLUMN_USERID);

        return new Appointment(id, date, time, serviceId, serviceUserId, userId);
    }

    @Override
    public Appointment findById(long id) {
        String query = "SELECT * FROM appointment WHERE appointment.id=" + id;
        Appointment appointment = new Appointment();
        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            appointment = getAppointment(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return appointment;
    }

    @Override
    public List<Appointment> findAll() {
        String query = "SELECT * FROM appointment";
        List<Appointment> serviceList = new ArrayList<Appointment>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Appointment appointment = getAppointment(resultSet);
                serviceList.add(appointment);
            }

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return serviceList;
    }

    @Override
    public List<Appointment> findByUserId(long userId) {
        String query = "SELECT * FROM appointment WHERE appointment.user_id=" + userId;
        List<Appointment> appointmentList = new ArrayList<Appointment>();
        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Appointment appointment = getAppointment(resultSet);
                appointmentList.add(appointment);
            }

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return appointmentList;
    }

    @Override
    public List<Appointment> findByMasterId(long masterId) {
        String query = "SELECT * FROM appointment WHERE appointment.service_user_id=" + masterId;
        List<Appointment> appointmentList = new ArrayList<Appointment>();
        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Appointment appointment = getAppointment(resultSet);
                appointmentList.add(appointment);
            }

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return appointmentList;
    }

    @Override
    public long createAppointment(Appointment appointment) {
        String query = "INSERT INTO appointment (date, time, service_id, service_user_id, user_id) VALUES ('";
        query += appointment.getDate()+"', '"
                +appointment.getTime()+"', '"
                +appointment.getService_id()+"', '"
                +appointment.getService_user_id()+"', '"
                +appointment.getUser_id()+"') " ;

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
    public void deleteById(long id) {
        String query = "DELETE FROM appointment WHERE id="+id;

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
