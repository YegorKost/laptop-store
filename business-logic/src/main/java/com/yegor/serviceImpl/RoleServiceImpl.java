package com.yegor.serviceImpl;

import com.yegor.dao.RoleDao;
import com.yegor.entity.RoleEntity;
import com.yegor.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YegorKost on 22.03.2017.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService<RoleEntity> {

    private RoleDao<RoleEntity> roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao<RoleEntity> roleDao) {
        this.roleDao = roleDao;
    }

    public RoleEntity addRole(RoleEntity roleEntity) {
        return roleDao.addRole(roleEntity);
    }

    public RoleEntity getRole(String role) {
        return roleDao.getRole(role);
    }

    public List<RoleEntity> getAllRoles() {
        return roleDao.getAllRoles();
    }

    public void deleteRole(RoleEntity roleEntity) {
        roleDao.deleteRole(roleEntity);
    }
}
