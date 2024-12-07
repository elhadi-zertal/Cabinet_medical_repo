package main;
import main.classes.Appointment;
import main.classes.AppointmentManager;
import main.classes.Consultation;

public class App {
    public static void main(String[] args) throws Exception {
        // Creating a new appointment
        Appointment appointment1 = new Appointment("P123", "John Doe", 20, 11, 2024, 10);
        Appointment appointment2 = new Appointment("P124", "Jane Doe", 20, 11, 2024, 14);

        // Adding appointments using the AppointmentManager
        AppointmentManager.addAppointment(appointment1);
        AppointmentManager.addAppointment(appointment2);

        // Listing all scheduled appointments
        AppointmentManager.listAppointments();

        // Canceling an appointment
        AppointmentManager.cancelAppointment(appointment1.getAppointmentId());

        // Listing appointments after cancellation
        AppointmentManager.listAppointments();


        // Create Consultation object for this Appointment (No hardcoded values)
        Consultation consultation = new Consultation("", "", "", "", "", "", "", "", appointment);
    }
}
