package main.classes;

import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {

    // Method to check if the appointment slot is free
    public static boolean isFree(Doctor doctor, Appointment appointment) {
        for (Appointment x : doctor.getAppointments()) {
            if (x.getDay() == appointment.getDay() && x.getMonth() == appointment.getMonth() && 
                x.getYear() == appointment.getYear() && x.getHour() == appointment.getHour()) {
                return false; // Slot is already booked
            }
        }
        return true; // Slot is free
    }

    // Add appointment to the schedule
    public static boolean addAppointment(Doctor doctor, Appointment appointment) {
        if (isFree(doctor, appointment)) {
            doctor.getAppointments().add(appointment); // Appointment successfully added
            System.out.println("Appointment successfully added.");
            return true;
        } else {
            System.out.println("Failed to add appointment: Slot is already booked.");
            return false;
        }
    }

    // Method to cancel an appointment by ID
    public static boolean cancelAppointment(Doctor doctor, String appointmentId) {
        for (Appointment x : doctor.getAppointments()) {
            if (x.getAppointmentId().equals(appointmentId)) {
                doctor.getAppointments().remove(x);
                System.out.println("Appointment with ID " + appointmentId + " successfully canceled.");
                return true;
            }
        }
        System.out.println("No appointment found with ID: " + appointmentId);
        return false;
    }

    // Method to list all appointments
    public static void listAppointments(Doctor doctor) {
        if (doctor.getAppointments().isEmpty()) {
            System.out.println("No scheduled appointments.");
        } else {
            System.out.println("Scheduled Appointments for " + doctor.getDoctorName() + ":");
            for (Appointment appointment : doctor.getAppointments()) {
                System.out.println(appointment);
            }
        }
    }
}
