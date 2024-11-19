package main;
import main.classes.Appointment;

public class App {
    public static void main(String[] args) throws Exception {
        // Create two appointments
        Appointment appointment1 = new Appointment("Smith", "John", "123 Elm Street", "555-1234", 20, 11, 2024, 10);
        Appointment appointment2 = new Appointment("Doe", "Jane", "456 Oak Street", "555-5678", 20, 11, 2024, 10);

        // Add the first appointment
        appointment1.addAppointment();

        // Attempt to add the second appointment at the same time slot
        appointment2.addAppointment(); // Should fail because the slot is already booked

        // Display scheduled appointments
        System.out.println("Scheduled Appointments:");
        for (Appointment a : Appointment.getScheduledAppointments()) {
            System.out.println(a);
        }

        // Cancel the first appointment
        Appointment.cancelAppointment(appointment1.getAppointmentId());

        // Attempt to cancel a non-existent appointment
        Appointment.cancelAppointment("NonExistentID");
    }
}
