package com.nure.komarkova.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nure.komarkova.backend.bean.EmployeeBean;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    private Position position;

    public Employee() {
    }

    public Employee(EmployeeBean employeeBean) {
        this.firstName = employeeBean.getFirstName();
        this.lastName = employeeBean.getLastName();
        this.gender = employeeBean.getGender();
        this.dateOfBirth = Date.valueOf(employeeBean.getDateOfBirth());
        this.position = employeeBean.getPosition();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
