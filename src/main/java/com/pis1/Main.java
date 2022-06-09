package com.pis1;

import com.pis1.connection.ConnectionPool;
import com.pis1.dao.DAOFactory;
import com.pis1.dao.interfaces.AppointmentInterface;
import com.pis1.dao.interfaces.ServiceInterface;
import com.pis1.dao.interfaces.UserInterface;
import com.pis1.entities.Appointment;
import com.pis1.entities.Service;
import com.pis1.entities.User;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println("\n");
        //System.out.println("Testing User DAO");
        //testUserDao(connectionPool);
        //System.out.println("Testing Service DAO");
        //testServiceDao(connectionPool);
        System.out.println("Testing Appointment DAO");
        testAppointmentDao(connectionPool);
    }

    private static void testUserDao(ConnectionPool connectionPool){
        try{
            //ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            System.out.println("Current services in DB: ");
            UserInterface userDao = DAOFactory.createUserDao(connection);
            userDao.printAll(userDao.findAll());

            Scanner sc = new Scanner(System.in);
            String s = null;

            while(!"E".equals(s)) {
                System.out.println("\nFind, Insert, Delete, or EXIT? F I D E");
                s = sc.nextLine();
                switch (s) {
                    case "F" -> {
                        System.out.println("Enter id");
                        long toBeFoundId = sc.nextLong();
                        userDao.print(userDao.findById(toBeFoundId));
                    }
                    case "I" -> {
                        System.out.println("Enter name, email, password");
                        String name = sc.nextLine();
                        String email = sc.nextLine();
                        String password = sc.nextLine();
                        User user1 = new User(name, email, password);
                        userDao.create(user1);
                        userDao.printAll(userDao.findAll());
                    }
                    case "D" -> {
                        System.out.println("Enter id");
                        long toBeDeletedId = sc.nextLong();
                        userDao.deleteById(toBeDeletedId);
                        userDao.printAll(userDao.findAll());
                    }
                }
            }
            sc.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testServiceDao(ConnectionPool connectionPool){
        try {
            //ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();

            System.out.println("Current services in DB: ");
            ServiceInterface serviceDao = DAOFactory.createServiceDao(connection);
            printAll(serviceDao.findAll());

            Scanner in = new Scanner(System.in);
            String s = null;

            while(!"E".equals(s)) {
                System.out.println("\nFind, Insert, Update, Delete, or EXIT? F I U D E");
                s = in.nextLine();
                switch (s) {
                    case "F" -> {
                        System.out.println("Enter id");
                        long toBeFoundId = in.nextLong();
                        serviceDao.printService(serviceDao.findById(toBeFoundId));
                    }
                    case "I" -> {
                        System.out.println("Enter name, price, master title, master id");
                        String name = in.nextLine();
                        long price = Long.parseLong(in.nextLine());
                        String masterTitle = in.nextLine();
                        long masterId = Long.parseLong(in.nextLine());
                        Service service1 = new Service(name, price, masterTitle, masterId);
                        serviceDao.createService(service1);
                        printAll(serviceDao.findAll());
                    }
                    case "U" -> {
                        System.out.println("Enter id");
                        long toBeUpdatedId = in.nextLong();
                        System.out.println("Enter new price");
                        long newPrice = in.nextLong();
                        serviceDao.updateById(toBeUpdatedId, newPrice);
                        printAll(serviceDao.findAll());
                    }
                    case "D" -> {
                        System.out.println("Enter id");
                        long toBeDeletedId = in.nextLong();
                        serviceDao.deleteById(toBeDeletedId);
                        printAll(serviceDao.findAll());
                    }
                }
            }
            in.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private static void testAppointmentDao(ConnectionPool connectionPool){
        try{
            //ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();

            System.out.println("Current appointments in DB: ");
            AppointmentInterface appointmentDao = DAOFactory.createAppointmentDao(connection);
            ServiceInterface serviceDao = DAOFactory.createServiceDao(connection);
            List<Appointment> appointments = appointmentDao.findAll();
            appointments.forEach((appointment) -> {
                System.out.println(appointment.getId()+"  "+appointment.getDate()+"  "+appointment.getTime()+"  "+appointment.getUser_id()+" "+serviceDao.findById(appointment.getService_id()).getName());
            });

            Scanner sc = new Scanner(System.in);
            String s = null;

            while(!"E".equals(s)) {
                System.out.println("\nFind, Insert, Delete, or EXIT? F I D E");
                s = sc.nextLine();
                switch (s) {
                    case "F" -> {
                        System.out.println("Enter id");
                        long toBeFoundId = sc.nextLong();
                        Appointment foundApp = appointmentDao.findById(toBeFoundId);
                        System.out.println(foundApp.getId()+"  "+foundApp.getDate()+"  "+foundApp.getTime()+"  "+foundApp.getUser_id()+"  "+serviceDao.findById(foundApp.getService_id()).getName()+"  "+foundApp.getService_user_id());
                    }
                    case "I" -> {
                        System.out.println("Enter date(YYYY-MM-DD), time, client id, service id, master id");

                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date fd = format.parse(sc.nextLine());
                        java.sql.Date sqlDate = new java.sql.Date(fd.getTime());

                        DateFormat formatTime = new SimpleDateFormat("HH:mm");
                        java.sql.Time timeValue = new java.sql.Time(formatTime.parse(sc.nextLine()).getTime());

                        long clientId = Long.parseLong(sc.nextLine());
                        long serviceId = Long.parseLong(sc.nextLine());
                        long masterId = Long.parseLong(sc.nextLine());

                        Appointment appointment1 = new Appointment(sqlDate, timeValue, clientId, serviceId, masterId);
                        appointmentDao.createAppointment(appointment1);

                        appointments = appointmentDao.findAll();
                        appointments.forEach((appointment) -> {
                            System.out.println(appointment.getId()+"  "+appointment.getDate()+"  "+appointment.getTime()+"  "+appointment.getUser_id()+" "+serviceDao.findById(appointment.getService_id()).getName()+"  "+appointment.getService_user_id());
                        });
                    }
                    case "D" -> {
                        System.out.println("Enter id");
                        long toBeDeletedId = sc.nextLong();
                        appointmentDao.deleteById(toBeDeletedId);

                        appointments = appointmentDao.findAll();
                        appointments.forEach((appointment) -> {
                            System.out.println(appointment.getId()+"  "+appointment.getDate()+"  "+appointment.getTime()+"  "+appointment.getUser_id()+" "+serviceDao.findById(appointment.getService_id()).getName());
                        });
                    }
                }
            }
            sc.close();
            connection.close();
        } catch (SQLException | ParseException e){
            e.printStackTrace();
        };
    }


    private static void printAll(List<Service> serviceList){
        serviceList.forEach((service) -> {
            service.printService();

        });
    }

}