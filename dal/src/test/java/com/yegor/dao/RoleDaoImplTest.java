package com.yegor.dao;

import com.yegor.entity.RoleEntity;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by YegorKost on 21.03.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dal-context-test.xml")
@Sql(scripts = "/create-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class RoleDaoImplTest {
    @Autowired
    private RoleDao<RoleEntity> roleDao;

    private static RoleEntity roleEntity1, roleEntity2, roleEntity3;

    @BeforeClass
    public static void setUp() {
        roleEntity1 = new RoleEntity();
        roleEntity1.setRole("admin");

        roleEntity2 = new RoleEntity();
        roleEntity2.setRole("user");

        roleEntity3 = new RoleEntity();
        roleEntity3.setRole("roleForDel");
    }


    @Test
    public void addRoleTest() throws Exception {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole("newRole");
        roleDao.addRole(roleEntity);
        assertEquals(roleEntity, roleDao.getRole("newRole"));
    }

    @Test
    public void getRoleTest() throws Exception {
        assertEquals(roleEntity1, roleDao.getRole("admin"));
        assertEquals(roleEntity2, roleDao.getRole("user"));
    }

    @Test
    public void getAllRolesTest() throws Exception {
        List<RoleEntity> roleEntities = roleDao.getAllRoles();
        assertEquals(3, roleEntities.size());
        assertTrue(roleEntities.contains(roleEntity1));
        assertTrue(roleEntities.contains(roleEntity2));
        assertTrue(roleEntities.contains(roleEntity3));
    }

    @Test
    public void deleteRoleTest() throws Exception {
        RoleEntity roleEntityDel = roleDao.getRole("roleForDel");
        roleDao.deleteRole(roleEntityDel);
        assertEquals(null, roleDao.getRole("roleForDel"));
    }
}