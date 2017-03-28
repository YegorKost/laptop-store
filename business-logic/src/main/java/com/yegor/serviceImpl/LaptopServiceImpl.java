package com.yegor.serviceImpl;

import com.yegor.dao.LaptopDao;
import com.yegor.entity.LaptopEntity;
import com.yegor.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YegorKost on 22.03.2017.
 */
@Service("laptopService")
public class LaptopServiceImpl implements LaptopService<LaptopEntity> {

    private LaptopDao<LaptopEntity> laptopDao;

    @Autowired
    public LaptopServiceImpl(LaptopDao<LaptopEntity> laptopDao) {
        this.laptopDao = laptopDao;
    }

    public LaptopEntity addLaptop(LaptopEntity laptopEntity) {
        return laptopDao.addLaptop(laptopEntity);
    }

    public LaptopEntity getLaptop(String model) {
        return laptopDao.getLaptop(model);
    }

    public List<LaptopEntity> getAllLaptops() {
        return laptopDao.getAllLaptops();
    }

    public LaptopEntity updateLaptop(LaptopEntity laptopEntity) {
        return laptopDao.updateLaptop(laptopEntity);
    }

    public void deleteLaptop(LaptopEntity laptopEntity) {
        laptopDao.deleteLaptop(laptopEntity);
    }
}
