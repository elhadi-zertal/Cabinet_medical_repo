package main.Exe;
import java.util.Scanner;
import main.classes.*;

public class App {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        // Create instances of all menu classes
        PatientMenu patientMenu = new PatientMenu();
        AppointmentMenu appointmentMenu = new AppointmentMenu();
        MedicalRecordManager recordManager = new MedicalRecordManager();
        MedicalRecordMenu medicalRecordMenu = new MedicalRecordMenu(scanner, recordManager);
        ConsultationMenu consultationMenu = new ConsultationMenu();
        PrescriptionMenu prescriptionMenu = new PrescriptionMenu();
        DoctorMenu doctorMenu = new DoctorMenu();
        MedicalCertificateMenu certificateMenu = new MedicalCertificateMenu(scanner);
        
        do {
            System.out.println("\n=== Medical Practice Management ===");
            System.out.println("1. Medical Record Management Menu");
            System.out.println("2. Appointment Management Menu");
            System.out.println("3. Patient Records Management Menu");
            System.out.println("4. Consultation Menu");
            System.out.println("5. Prescription Management Menu");
            System.out.println("6. Manage Medical Certificates");
            System.out.println("7. Doctor Menu");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the remaining newline

            switch (choice) {
                case 1 -> medicalRecordMenu.displayMenu();
                case 2 -> appointmentMenu.displayMenu();
                case 3 -> patientMenu.displayMenu();
                case 4 -> consultationMenu.displayMenu();
                case 5 -> prescriptionMenu.displayMenu();
                case 6 -> certificateMenu.displayMenu();
                case 7 -> doctorMenu.displayMenu();
                case 8 -> System.out.println("System shutting down. Goodbye!");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 8);
    }
}
