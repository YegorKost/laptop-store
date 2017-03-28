package com.yegor.service;

import java.util.List;

/**
 * Created by YegorKost on 22.03.2017.
 */

public interface RoleService<E> {
    E addRole(E e);
    E getRole(String role);
    List<E> getAllRoles();
    void deleteRole(E e);
}
