package com.yegor.daoImpl;

import com.yegor.dao.RoleDao;
import com.yegor.entity.RoleEntity;
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
@Repository("roleDao")
public class RoleDaoImpl implements RoleDao<RoleEntity> {

    private static Logger LOGGER = LoggerFactory.getLogger(RoleDaoImpl.class);

    private SessionFactory sessionFactory;

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public RoleEntity addRole(RoleEntity roleEntity) {
        sessionFactory
                .getCurrentSession()
                .save(roleEntity);
        LOGGER.info("Add \'RoleEntity\' - " + roleEntity.getRole());
        return roleEntity;
    }

    @Override
    public RoleEntity getRole(String role) {
        LOGGER.info("Get \'RoleEntity\' - " + role);
        return sessionFactory
                .getCurrentSession()
                .get(RoleEntity.class, role);
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        LOGGER.info("Get all \'RoleEntity\'");
        return sessionFactory
                .getCurrentSession()
                .createNamedQuery("RoleEntity.getAllRoles", RoleEntity.class)
                .getResultList();
    }

    @Override
    public void deleteRole(RoleEntity roleEntity) {
        sessionFactory.getCurrentSession().delete(roleEntity);
        LOGGER.info("Delete \'RoleEntity\' - " + roleEntity.getRole());
    }
}
