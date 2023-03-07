package com.microrent.backend.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Account_statuses")
public class AccountStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "status")
    private List<User> users;

    public AccountStatus() {
    }

    public AccountStatus(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "AccountStatuses{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}

