package com.yegor.service;

import java.util.List;

/**
 * Created by YegorKost on 22.03.2017.
 */
public interface LaptopService<E> {
    E addLaptop(E e);
    E getLaptop(String model);
    List<E> getAllLaptops();
    E updateLaptop(E e);
    void deleteLaptop(E e);
}
