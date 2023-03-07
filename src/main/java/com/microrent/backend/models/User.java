package com.microrent.backend.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private UserRole role;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "hash_salt")
    private String hashSalt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private AccountStatus status;

    @Column(name = "visit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date visitDate;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Group> groups;

    public User() {
    }

    public User(int id, String lastName, String firstName, String patronymic, Date dateOfBirth, String phoneNumber, UserRole role, String email, String password, String hashSalt, AccountStatus status, Date visitDate) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.email = email;
        this.password = password;
        this.hashSalt = hashSalt;
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashSalt() {
        return hashSalt;
    }

    public void setHashSalt(String hashSalt) {
        this.hashSalt = hashSalt;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole userRole) {
        this.role = userRole;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", hashSalt='" + hashSalt + '\'' +
                ", status=" + status +
                ", visitDate=" + visitDate +
                '}';
    }
}
