package com.yegor.dao;

import java.util.List;

/**
 * Created by YegorKost on 21.03.2017.
 */
public interface LaptopDao<E> {
    E addLaptop(E e);
    E getLaptop(String model);
    List<E> getAllLaptops();
    E updateLaptop(E e);
    void deleteLaptop(E e);
}
