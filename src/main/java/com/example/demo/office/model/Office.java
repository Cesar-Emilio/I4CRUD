package com.example.demo.office.model;

import com.example.demo.doctor.model.Doctor;
import com.example.demo.patient.model.Patient;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "offices")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "consultation_date",columnDefinition = "DATE")
    private Date date_of_creation;

    @Column(name = "diagnosis",columnDefinition = "VARCHAR(100)")
    private String diagnosis;

    @Column(name = "treatment",columnDefinition = "VARCHAR(100)")
    private String treatment;

    @Column(name = "status",columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Office() {
    }

    public Office(Long id, Date date_of_creation, String diagnosis, String treatment, boolean status, Doctor doctor, Patient patient) {
        this.id = id;
        this.date_of_creation = date_of_creation;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.status = status;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_of_creation() {
        return date_of_creation;
    }

    public void setDate_of_creation(Date date_of_creation) {
        this.date_of_creation = date_of_creation;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
