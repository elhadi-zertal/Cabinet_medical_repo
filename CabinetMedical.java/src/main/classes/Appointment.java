package main.classes;

public class Appointment {
    private int day; // The day of the appointment
    private int month; // The month of the appointment
    private int year; // The year of the appointment
    private int hour; // The hour of the appointment
    private String appointmentId; // Unique ID for the appointment
    private Doctor doctor; // Associate each appointment with a doctor
    private Patient patient; // Associate each appointment with a patient

    // Constructor for Appointment
    public Appointment(Doctor doctor, Patient patient, int day, int month, int year, int hour) {
        this.doctor = doctor;
        this.patient = patient;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.appointmentId = generateAppointmentId(); // Generate unique ID
    }

    // Method to generate a unique appointment ID
    private String generateAppointmentId() {
        return patient.getId() + "_" + day + "-" + month + "-" + year + "_" + hour;
    }

    // Getters and Setters for Appointment's attributes
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    // Overriding toString().
    @Override
    public String toString() {
        return "Id: " + getAppointmentId() + "\n" +
               "Patient Name: " + patient.getName() + "\n" +
               "Patient Age: " + patient.getAge() + "\n" +
               "Patient Address: " + patient.getAddress() + "\n" +
               "Patient Contact Info: " + patient.getContactInfo() + "\n" +
               "Doctor: " + doctor.getDoctorName() + " (" + doctor.getSpecialization() + ")\n" +
               "Date: " + getDay() + "/" + getMonth() + "/" + getYear() + " at " + getHour() + ":00";
    }
}
