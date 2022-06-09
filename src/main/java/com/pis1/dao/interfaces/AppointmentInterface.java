package com.pis1.dao.interfaces;

import com.pis1.entities.Appointment;

import java.util.List;

public interface AppointmentInterface {
    Appointment findById(long id);
    List<Appointment> findAll();
    List<Appointment> findByUserId(long userId);
    List<Appointment> findByMasterId(long MasterId);
    long createAppointment(Appointment appointment);
    void deleteById(long id);
}
