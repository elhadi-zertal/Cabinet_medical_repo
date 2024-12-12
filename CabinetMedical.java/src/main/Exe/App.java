package main.Exe;
import java.util.Scanner;
<<<<<<< HEAD
import main.classes.Consultation;
import main.classes.MedicalRecord;
import main.classes.MedicalRecordMenu;

=======

import main.classes.AppointmentMenu;
>>>>>>> d23e3527adf1a6e69f32ecb43491398c2ece89b0
import main.classes.PatientMenu;
import main.classes.PrescriptionMenu;

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
            System.out.println("1. Medical Record Management Menu");
            System.out.println("2. Appointment Management Menu");
            System.out.println("3. Patient Records Management Menu");
            System.out.println("4. Consultation Menu");
            System.out.println("5. Prescription Management Menu ");
            System.out.println("6. Manage Medical Certificates");
            System.out.println("7. doctor Menu");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the remaining newline

            switch (choice) {
<<<<<<< HEAD
                case 1 -> MedicalRecordMenu.displayMenu;
                case 2 -> System.out.println("Appointment Management (to be implemented)");
=======
                case 1 -> System.out.println("Medical Record Management (to be implemented)");
                case 2 -> appointmentMenu.displayMenu();
>>>>>>> d23e3527adf1a6e69f32ecb43491398c2ece89b0
                case 3 -> patientMenu.displayMenu();
                case 4 -> ConsultationMenu.displayMenu();
                case 5 -> PrescriptionMenu.displayMenu();
                case 6 -> System.out.println("Manage Medical Certificates (to be implemented)");
                case 7 -> DoctorMenu.displayMenu();
                case 8 -> System.out.println("System shutting down. Goodbye!");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 7);
        
       
    }
}
