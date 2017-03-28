package com.yegor.daoImpl;

import com.yegor.dao.UserDao;
import com.yegor.entity.UserEntity;
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
@Repository("userDao")
public class UserDaoImpl implements UserDao<UserEntity> {

    private static Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionFactory sessionFactory;

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserEntity addUser(UserEntity userEntity) {
        sessionFactory
                .getCurrentSession()
                .save(userEntity);
        LOGGER.info("Add \'UserEntity\' with login - " + userEntity.getLogin());
        return userEntity;
    }

    @Override
    public UserEntity getUser(String login) {
        LOGGER.info("Get \'UserEntity\' with login - " + login);
        return sessionFactory
                .getCurrentSession()
                .get(UserEntity.class, login);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        LOGGER.info("Get all \'UserEntity\'");
        return sessionFactory
                .getCurrentSession()
                .createNamedQuery("UserEntity.getAllUsers", UserEntity.class)
                .getResultList();
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        sessionFactory
                .getCurrentSession()
                .update(userEntity);
        LOGGER.info("Update \'UserEntity\' - " + userEntity.getLogin());
        return userEntity;
    }

    @Override
    public void deleteUser(UserEntity userEntity) {
        sessionFactory.getCurrentSession().delete(userEntity);
        LOGGER.info("Delete \'UserEntity\' - " + userEntity.getLogin());
    }
}
