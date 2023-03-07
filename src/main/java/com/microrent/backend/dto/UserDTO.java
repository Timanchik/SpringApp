package com.microrent.backend.dto;

import javax.validation.constraints.Email;
import java.util.Date;

public class UserDTO {

    private int id;

    private String lastName;

    private String firstName;

    private String patronymic;

    private Date dateOfBirth;
    private String phoneNumber;

    private String role;

    @Email
    private String email;

    private String status;

    private String visitDate;

    public UserDTO(int id, String lastName, String firstName, String patronymic, Date dateOfBirth, String phoneNumber, String role, String email, String accountStatus, String visitDate) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.email = email;
        this.status = accountStatus;
        this.visitDate = visitDate;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", accountStatus='" + status + '\'' +
                ", visitDate='" + visitDate + '\'' +
                '}';
    }
}
