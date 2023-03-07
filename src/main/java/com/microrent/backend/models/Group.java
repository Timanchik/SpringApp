package com.microrent.backend.models;

import javax.persistence.*;

@Entity
@Table(name = "Group_table")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;

    @ManyToOne
    @JoinColumn(name = "style_id", referencedColumnName = "id")
    private Style style;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private User teacher;

    public Group() {
    }

    public Group(int id, String name, String description, Hall hall, Style style, User teacher) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hall = hall;
        this.style = style;
        this.teacher = teacher;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hall=" + hall +
                ", style=" + style +
                ", teacher=" + teacher +
                '}';
    }
}
