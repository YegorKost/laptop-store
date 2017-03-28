package com.yegor.dao;

import java.util.List;

/**
 * Created by YegorKost on 21.03.2017.
 */
public interface RoleDao<E> {
    E addRole(E e);
    E getRole(String role);
    List<E> getAllRoles();
    void deleteRole(E e);
}
