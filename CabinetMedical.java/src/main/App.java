package main;
import main.classes.Appointment;

public class App {
    public static void main(String[] args) throws Exception {
        // Create Appointment objects
        Appointment p1 = new Appointment("Smith", "John", "john@example.com", "1234567890", 17, 11, 2024, 14);
        Appointment p2 = new Appointment("Doe", "Jane", "jane@example.com", "0987654321", 17, 11, 2024, 14);  // Same time as a1
        Appointment p3 = new Appointment("Brown", "Lisa", "lisa@example.com", "1112223333", 18, 11, 2024, 15);

        // Attempt to add appointments
        p1.addAppointment();  // Should succeed
        p2.addAppointment();  // Should fail (slot already booked)
        p3.addAppointment();  // Should succeed

        // Display all scheduled appointments
        System.out.println("\nScheduled Appointments:");
        for (Appointment appt : Appointment.getScheduledAppointments()) {
            System.out.println(appt.family_name + " " + appt.first_name + " on " +
                               appt.day + "/" + appt.month + "/" + appt.year + " at " +
                               appt.hour + ":00");
        }
    }
}
