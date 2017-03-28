package com.yegor.dao;

import java.util.List;

/**
 * Created by YegorKost on 21.03.2017.
 */
public interface UserDao<E> {
    E addUser(E e);
    E getUser(String login);
    List<E> getAllUsers();
    E updateUser(E e);
    void deleteUser(E e);
}
