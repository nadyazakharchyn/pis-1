package com.pis1.dao;

import com.pis1.dao.DAOs.AppointmentDAO;
import com.pis1.dao.DAOs.ServiceDAO;
import com.pis1.dao.DAOs.UserDAO;
import com.pis1.dao.interfaces.AppointmentInterface;
import com.pis1.dao.interfaces.ServiceInterface;
import com.pis1.dao.interfaces.UserInterface;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactory {

    public static AppointmentInterface createAppointmentDao (Connection connection) throws SQLException {
        return new AppointmentDAO(connection);
    }
    public static ServiceInterface createServiceDao (Connection connection) throws SQLException {
        return new ServiceDAO(connection);
    }
    public static UserInterface createUserDao (Connection connection) throws SQLException {
        return new UserDAO(connection) {
        };
    }

}
