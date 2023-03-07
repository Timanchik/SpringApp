package com.microrent.backend.dto;

public class TeacherDTO {

    private int id;

    private String lastName;

    private String firstName;

    private String patronymic;

    public TeacherDTO(int id, String lastName, String firstName, String patronymic) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
}
