package com.yegor.dao;

import com.yegor.entity.RoleEntity;
import com.yegor.entity.UserEntity;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by YegorKost on 22.03.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dal-context-test.xml")
@Sql(scripts = "/create-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UserDaoImplTest {

    @Autowired
    private UserDao<UserEntity> userDao;

    private static RoleEntity roleEntity1, roleEntity2, roleEntity3;
    private static UserEntity userEntity1, userEntity2;

    @BeforeClass
    public static void setUp() {
        roleEntity1 = new RoleEntity();
        roleEntity1.setRole("admin");

        roleEntity2 = new RoleEntity();
        roleEntity2.setRole("user");

        roleEntity3 = new RoleEntity();
        roleEntity3.setRole("roleForDel");

        userEntity1 = new UserEntity();
        userEntity1.setLogin("userLogin1");
        userEntity1.setPassword("userPassword1");
        userEntity1.setRole(roleEntity1);

        userEntity2 = new UserEntity();
        userEntity2.setLogin("userLogin2");
        userEntity2.setPassword("userPassword2");
        userEntity2.setRole(roleEntity2);
    }

    @Test
    public void addUser() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin("newLogin");
        userEntity.setPassword("newPassword");
        userDao.addUser(userEntity);
        assertEquals(userEntity, userDao.getUser("newLogin"));
    }

    @Test
    public void getUser() throws Exception {
        assertEquals(userEntity1, userDao.getUser("userLogin1"));
        assertEquals(userEntity2, userDao.getUser("userLogin2"));
        assertEquals(roleEntity1, userDao.getUser("userLogin1").getRole());
        assertEquals(roleEntity2, userDao.getUser("userLogin2").getRole());
    }

    @Test
    public void getAllUsers() throws Exception {
        List<UserEntity> userEntities = userDao.getAllUsers();
        assertEquals(2, userEntities.size());
        assertTrue(userEntities.contains(userEntity1));
        assertTrue(userEntities.contains(userEntity2));
    }

    @Test
    public void updateUser() throws Exception {
        UserEntity userEntityUpdate = userDao.getUser("userLogin1");
        userEntityUpdate.setPassword("newPassword");
        userEntityUpdate.getRole().setRole("roleForDel");
        userDao.updateUser(userEntityUpdate);
        assertEquals(userEntityUpdate, userDao.getUser("userLogin1"));
        assertEquals(roleEntity3, userDao.getUser("userLogin1").getRole());

    }

    @Test
    public void deleteUser() throws Exception {
        UserEntity userEntityDel = userDao.getUser("userLogin1");
        userDao.deleteUser(userEntityDel);
        assertEquals(null, userDao.getUser("userLogin1"));
    }

}