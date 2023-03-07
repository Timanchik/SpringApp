package com.microrent.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(value = {"users"}, allowSetters = true)
@Table(name = "Roles")
public class UserRole {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "role_name")
    String roleName;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public UserRole() {
    }

    public UserRole(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
