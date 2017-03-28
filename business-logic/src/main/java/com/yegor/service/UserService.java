package com.yegor.service;

import java.util.List;

/**
 * Created by YegorKost on 22.03.2017.
 */
public interface UserService<E> {
    E addUser(E e);
    E getUser(String login);
    List<E> getAllUsers();
    E updateUser(E e);
    void deleteUser(E e);
}
