package main.classes;

import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {
    static List<Appointment> scheduledAppointments = new ArrayList<>(); // List to store the appointments

    // Method to check if the appointment slot is free
    public static boolean isFree(Appointment appointment) {
        for (Appointment x : scheduledAppointments) {
            if (x.getDay() == appointment.getDay() && x.getMonth() == appointment.getMonth() && 
                x.getYear() == appointment.getYear() && x.getHour() == appointment.getHour()) {
                return false; // Slot is already booked
            }
        }
        return true; // Slot is free
    }

    // Add appointment to the schedule
    public static boolean addAppointment(Appointment appointment) {
        if (isFree(appointment)) {
            scheduledAppointments.add(appointment); // Appointment successfully added
            System.out.println("Appointment successfully added.");
            return true;
        } else {
            System.out.println("Failed to add appointment: Slot is already booked.");
            return false;
        }
    }

    // Method to cancel an appointment by ID
    public static boolean cancelAppointment(String appointmentId) {
        for (Appointment x : scheduledAppointments) {
            if (x.getAppointmentId().equals(appointmentId)) {
                scheduledAppointments.remove(x);
                System.out.println("Appointment with ID " + appointmentId + " successfully canceled.");
                return true;
            }
        }
        System.out.println("No appointment found with ID: " + appointmentId);
        return false;
    }

    // Method to list all appointments
    public static void listAppointments() {
        if (scheduledAppointments.isEmpty()) {
            System.out.println("No scheduled appointments.");
        } else {
            for (Appointment appointment : scheduledAppointments) {
                System.out.println(appointment);
            }
        }
    }
}
