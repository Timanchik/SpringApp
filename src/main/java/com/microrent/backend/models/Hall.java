package com.microrent.backend.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Halls")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "hall", fetch = FetchType.LAZY)
    private List<Group> groups;

    public Hall(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Hall() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
