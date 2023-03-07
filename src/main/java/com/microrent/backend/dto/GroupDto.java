package com.microrent.backend.dto;

public class GroupDto {

    private int id;

    private String name;

    private String description;

    private HallDto hall;

    private StyleDTO style;

    private TeacherDTO teacher;

    public GroupDto() {
    }

    public GroupDto(int id, String name, String description, HallDto hall, StyleDTO style, TeacherDTO teacher) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hall = hall;
        this.style = style;
        this.teacher = teacher;
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

    public HallDto getHall() {
        return hall;
    }

    public void setHall(HallDto hall) {
        this.hall = hall;
    }

    public StyleDTO getStyle() {
        return style;
    }

    public void setStyle(StyleDTO style) {
        this.style = style;
    }

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "GroupDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hall=" + hall +
                ", style=" + style +
                ", teacher=" + teacher +
                '}';
    }
}
