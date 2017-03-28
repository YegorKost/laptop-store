package com.yegor.daoImpl;

import com.yegor.dao.LaptopDao;
import com.yegor.entity.LaptopEntity;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by YegorKost on 21.03.2017.
 */
@Transactional
@Repository("laptopDao")
public class LaptopDaoImpl implements LaptopDao<LaptopEntity> {

    private static Logger LOGGER = LoggerFactory.getLogger(LaptopDaoImpl.class);

    private SessionFactory sessionFactory;

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public LaptopEntity addLaptop(LaptopEntity laptopEntity) {
        sessionFactory
                .getCurrentSession()
                .save(laptopEntity);
        LOGGER.info("Add \'LaptopEntity\' - " + laptopEntity.getModel());
        return laptopEntity;
    }

    @Override
    public LaptopEntity getLaptop(String model) {
        LOGGER.info("Get \'LaptopEntity\' - " + model);
        return sessionFactory
                .getCurrentSession()
                .get(LaptopEntity.class, model);
    }

    @Override
    public List<LaptopEntity> getAllLaptops() {
        LOGGER.info("Get all \'LaptopEntity\'");
        return sessionFactory
                .getCurrentSession()
                .createNamedQuery("LaptopEntity.getAllLaptops", LaptopEntity.class)
                .getResultList();
    }

    @Override
    public LaptopEntity updateLaptop(LaptopEntity laptopEntity) {
        sessionFactory
                .getCurrentSession()
                .update(laptopEntity);
        LOGGER.info("Update \'LaptopEntity\' - " + laptopEntity.getModel());
        return laptopEntity;
    }

    @Override
    public void deleteLaptop(LaptopEntity laptopEntity) {
        sessionFactory.getCurrentSession().delete(laptopEntity);
        LOGGER.info("Delete \'LaptopEntity\' - " + laptopEntity.getModel());
    }
}
