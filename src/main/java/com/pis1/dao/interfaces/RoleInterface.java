package com.pis1.dao.interfaces;

import com.pis1.entities.Role;

import java.util.List;

public interface RoleInterface {
    Role findById(long id);
    List<Role> findAll();
    void createRole(Role role);
    void updateById(long id, String new_role);
    void deleteById(long id);
}
