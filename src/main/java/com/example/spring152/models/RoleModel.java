package com.example.spring152.models;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="t_role")
@Data

public class RoleModel implements GrantedAuthority {
    @Id
    private Long id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<UserModel> users;

    public RoleModel(Long id) {
        this.id = id;
    }

    public RoleModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleModel() {
    }

    public Set<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserModel> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
