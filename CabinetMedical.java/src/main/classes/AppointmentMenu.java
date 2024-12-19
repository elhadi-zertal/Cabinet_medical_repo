package main.classes;

import java.util.Scanner;

public class AppointmentMenu {
    
    private Scanner scanner = new Scanner(System.in);
    
    public void displayMenu() {
        int choice;
        
        do {
            System.out.println("\n=== Appointment Management ===");
            System.out.println("1. Add Appointment");
            System.out.println("2. Cancel Appointment");
            System.out.println("3. List All Appointments");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline
            
            switch (choice) {
                case 1 -> addAppointment();
                case 2 -> cancelAppointment();
                case 3 -> listAppointments();
                case 4 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);
    }
    
    // Method to add an appointment
    private void addAppointment() {
        
        // Get doctor and patient details
        System.out.print("Enter doctor's ID: ");
        String doctorId = scanner.nextLine();
        
        // Find the doctor 
        Doctor doctor = DoctorManager.getDoctorById(doctorId); 

        // Get patient details
        System.out.print("Enter patient ID: ");
        String patientId = scanner.nextLine();

        // Create an instance of PatientManager
        PatientManager patientManager = new PatientManager();
        
        // Find the patient 
        Patient patient = patientManager.getPatientById(patientId);
        
        // Get appointment details
        System.out.print("Enter appointment day: ");
        int day = scanner.nextInt();
        System.out.print("Enter appointment month: ");
        int month = scanner.nextInt();
        System.out.print("Enter appointment year: ");
        int year = scanner.nextInt();
        System.out.print("Enter appointment hour: ");
        int hour = scanner.nextInt();
        
        // Create appointment object
        Appointment appointment = new Appointment(doctor, patient, day, month, year, hour);
        
        // Add the appointment using AppointmentManager
        boolean success = AppointmentManager.addAppointment(doctor, appointment);
        
        if (success) {
            System.out.println("Appointment added successfully.");
        } else {
            System.out.println("Failed to add appointment, the slot is not available");
        }
    }
    
    private void cancelAppointment() {
        
        // Get doctor's ID
        System.out.print("Enter doctor's ID: ");
        String doctorId = scanner.nextLine();
        
        // Find the doctor
        Doctor doctor = DoctorManager.getDoctorById(doctorId);
        
        // Get the appointment ID to cancel
        System.out.print("Enter appointment ID to cancel: ");
        String appointmentId = scanner.nextLine();
        
        // Cancel the appointment using AppointmentManager
        AppointmentManager.cancelAppointment(doctor, appointmentId);
        
    }
    
    private void listAppointments() {
        
        // Get doctor's ID
        System.out.print("Enter doctor's ID: ");
        String doctorId = scanner.nextLine();
        
        // Find the doctor
        Doctor doctor = DoctorManager.getDoctorById(doctorId);
        
        // List the appointments for this doctor
        AppointmentManager.listAppointments(doctor);
    }
    
}
