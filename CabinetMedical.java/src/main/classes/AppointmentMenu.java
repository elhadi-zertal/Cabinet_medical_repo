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
    
    public boolean isValid(int day, int month, int year) {
        if (year < 2025) {
            System.out.println("The year must be 2025 or later. Please enter a valid year.");
            return false;
        }
        if (month < 1 || month > 12) {
            System.out.println("Invalid month! Please enter a month between 1 and 12.");
            return false;
        }
        int maxDay = 31;
        if (month == 2) { // February
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) { // Leap year
                maxDay = 29;
            } else {
                maxDay = 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) { // April, June, September, November
            maxDay = 30;
        }
        if (day >= 1 && day <= maxDay) {
            return true;
        } else {
            System.out.println("Invalid day! Please enter a day between 1 and " + maxDay + ".");
            return false;
        }
    }
    
    // Method to add an appointment
    private void addAppointment() {
        //print all the doctors
        DoctorManager.listAllDoctors();

        String doctorId;
        Doctor doctor;

        do {
            System.out.print("Enter doctor's ID: ");
            doctorId = scanner.nextLine();
            // Find the doctor 
            doctor = DoctorManager.getDoctorById(doctorId);
            if (DoctorManager.findDoctorById(doctorId) == null) {
                System.out.println("There is no doctor with ID :" + doctorId);
            }
        } while (DoctorManager.findDoctorById(doctorId) == null);


        //print all the patients
        PatientManager.listAllPatients();

        String patientId;
        Patient patient;

        do {
            // Get patient details
            System.out.print("Enter patient ID: ");
            patientId = scanner.nextLine();
            // Find the patient 
            patient = PatientManager.getPatientById(patientId);
            if (PatientManager.getPatientById(patientId) == null) {
                System.out.println("There is no patient with ID :" + patientId);
            }
        } while (PatientManager.getPatientById(patientId) == null);


        int day, month, year;

        // Get appointment details
        do {
            System.out.print("Enter appointment day: ");
            day = scanner.nextInt();
            System.out.print("Enter appointment month: ");
            month = scanner.nextInt();
            System.out.print("Enter appointment year: ");
            year = scanner.nextInt();
        } while (!isValid(day,month,year));



        System.out.print("Enter appointment hour: ");
        int hour = scanner.nextInt();

        
        // Create appointment object
        Appointment appointment = new Appointment(doctor, patient, day, month, year, hour);
        
        // Add the appointment using AppointmentManager
        boolean success = AppointmentManager.addAppointment(doctor, appointment);
        
        if (success) {
            System.out.println("Appointment added successfully.");
            System.out.println(appointment);
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
