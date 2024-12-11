package main.Exe;
import java.util.Scanner;

import main.classes.AppointmentMenu;
import main.classes.PatientMenu;

public class App {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        // Create an instance of PatientMenu
        PatientMenu patientMenu = new PatientMenu();
        // Create an instance of AppointmentMenu
        AppointmentMenu appointmentMenu = new AppointmentMenu();
        
        do {
            System.out.println("\n=== Medical Practice Management ===");
            System.out.println("1. Medical Record Management");
            System.out.println("2. Appointment Management");
            System.out.println("3. Patient Records Management");
            System.out.println("4. View Medical Record");
            System.out.println("5. Write a Prescription");
            System.out.println("6. Manage Medical Certificates");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the remaining newline

            switch (choice) {
                case 1 -> System.out.println("Medical Record Management (to be implemented)");
                case 2 -> appointmentMenu.displayMenu();
                case 3 -> patientMenu.displayMenu();
                case 4 -> System.out.println("View Medical Record (to be implemented)");
                case 5 -> System.out.println("Write a Prescription (to be implemented)");
                case 6 -> System.out.println("Manage Medical Certificates (to be implemented)");
                case 7 -> System.out.println("System shutting down. Goodbye!");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 7);
        
       
    }
}
