package com.example.demo.Doctor.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",columnDefinition = "VARCHAR(30)")
    private String name;

    @Column(name = "last_name",columnDefinition = "VARCHAR(30)")
    private String lastName;

    @Column(name = "curp",columnDefinition = "VARCHAR(18)")
    private String curp;

    @Column(name = "phone",columnDefinition = "VARCHAR(15)")
    private String phone;


    @Column(name = "age",columnDefinition = "INTEGER")
    private int age;
    @Column(name = "speciality",columnDefinition = "VARCHAR(30)")
    private String speciality;

    @Column(name = "status",columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;

    public Doctor() {
    }

    public Doctor(String name, String speciality) {
        this.name = name;
        this.speciality = speciality;
    }

    public Doctor(Long id, String name, String lastName, String curp, String phone, int age, String speciality, boolean status) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.curp = curp;
        this.phone = phone;
        this.age = age;
        this.speciality = speciality;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
