package com.yegor.serviceImpl;

import com.yegor.dao.UserDao;
import com.yegor.entity.UserEntity;
import com.yegor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YegorKost on 22.03.2017.
 */
@Service("userService")
public class UserServiceImpl implements UserService<UserEntity> {

    private UserDao<UserEntity> userDao;

    @Autowired
    public UserServiceImpl(UserDao<UserEntity> userDao) {
        this.userDao = userDao;
    }

    public UserEntity addUser(UserEntity userEntity) {
        return userDao.addUser(userEntity);
    }

    public UserEntity getUser(String login) {
        return userDao.getUser(login);
    }

    public List<UserEntity> getAllUsers() {
        return userDao.getAllUsers();
    }

    public UserEntity updateUser(UserEntity userEntity) {
        return userDao.updateUser(userEntity);
    }

    public void deleteUser(UserEntity userEntity) {
        userDao.deleteUser(userEntity);
    }
}
