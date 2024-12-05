package main.classes;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private String doctorId;
    private String doctorName;
    private String specialization;
    private List<Appointment> appointments; // List to store appointments for this doctor

    // Constructor
    public Doctor(String doctorId, String doctorName, String specialization) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.appointments = new ArrayList<>(); // Initialize the appointments list
    }

    // Getters and setters
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public String toString() {
        return "Doctor: " + doctorName + " (ID: " + doctorId + "), Specialization: " + specialization + ", Appointments: " + appointments;
    }



