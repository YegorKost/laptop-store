package com.yegor.entity;

import javax.persistence.*;

/**
 * Created by YegorKost on 21.03.2017.
 */
@Entity
@Table(name = "roles")
@NamedQuery(name = "RoleEntity.getAllRoles", query = "select r from RoleEntity r")
public class RoleEntity {
    private String role;

    @Id
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return role != null ? role.hashCode() : 0;
    }
}
