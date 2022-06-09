package com.pis1.dao.interfaces;

import com.pis1.entities.Service;

import java.util.List;

public interface ServiceInterface {
    Service findById(long id);
    List<Service> findAll();
    long createService (Service service);
    void updateById (long id, long newPrice);
    void deleteById(long id);
    void printService(Service service);
}
