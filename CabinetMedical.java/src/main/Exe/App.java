package main.Exe;
import java.util.Scanner;
import main.classes.AppointmentMenu;
import main.classes.ConsultationMenu;
import main.classes.DoctorMenu;
import main.classes.MedicalRecordMenu;
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
        // Create an instance of MedicalRecordMenu
        MedicalRecordMenu medicalRecordMenu = new MedicalRecordMenu();
        //create an instance of DoctorMenu
        DoctorMenu doctorMenu = new DoctorMenu();
        //create an instance of ConsultationMenu
        ConsultationMenu consultationMenu = new ConsultationMenu();


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
            scanner.nextLine(); // Consume the remaining newlin3e

            switch (choice) {


                case 1 -> doctorMenu.displayMenu();
                case 2 -> patientMenu.displayMenu();
                case 3 -> appointmentMenu.displayMenu();
                case 4 -> consultationMenu.displayMenu();
                case 5 -> PrescriptionMenu.displayMenu();
                case 6 -> System.out.println("Manage Medical Certificates (to be implemented)");
                case 7 ->  medicalRecordMenu.displayMenu();
                case 8 -> System.out.println("System shutting down. Goodbye!");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 8);
        
       
    }
}
