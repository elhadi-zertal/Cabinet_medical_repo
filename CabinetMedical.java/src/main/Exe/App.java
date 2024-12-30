package main.Exe;
import java.util.Scanner;
import main.classes.AppointmentMenu;
import main.classes.ConsultationMenu;
import main.classes.Doctor;
import main.classes.DoctorManager;
import main.classes.DoctorMenu;
import main.classes.MedicalCertificateMenu;
import main.classes.MedicalRecordMenu;
import main.classes.Patient;
import main.classes.PatientManager;
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
            System.out.println("1. doctor Menu ");
            System.out.println("2. Patient Records Management Menu");
            System.out.println("3. Appointment Management Menu");
            System.out.println("4. Consultation Menu");
            System.out.println("5. Prescription Management Menu ");
            System.out.println("6. Manage Medical Certificates");
            System.out.println("7. Medical Record Management Menu");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the remaining newline

            switch (choice) {


                case 1 -> doctorMenu.displayMenu();
                case 2 -> patientMenu.displayMenu();
                case 3 -> appointmentMenu.displayMenu();
                case 4 -> consultationMenu.displayMenu();
                case 5 -> PrescriptionMenu.displayMenu();
                case 6 -> {
                    // Search for a doctor
                    System.out.print("Enter Doctor ID or Name to search: ");
                    String doctorSearchTerm = scanner.nextLine().trim();

                    // Attempt to find the doctor by ID first
                    Doctor selectedDoctor = DoctorManager.findDoctorById(doctorSearchTerm); // Call static method directly
                    if (selectedDoctor == null) {
                        // If not found by ID, try to find by name
                        selectedDoctor = DoctorManager.findDoctorByName(doctorSearchTerm); // Call static method directly
                    }

                    if (selectedDoctor == null) {
                        System.out.println("No doctor found with the given ID or name. Returning to main menu.");
                        break;
                    }

                    // Search for a patient
                    System.out.print("Enter Patient ID to search: ");
                    String patientId = scanner.nextLine().trim();
                    Patient selectedPatient = PatientManager.getPatientById(patientId); // Use the existing method

                    if (selectedPatient == null) {
                        System.out.println("No patient found with the given ID. Returning to main menu.");
                        break;
                    }

                    // Create an instance of MedicalCertificateMenu with selected doctor and patient
                    MedicalCertificateMenu certificateMenu = new MedicalCertificateMenu(selectedDoctor, selectedPatient);
                    certificateMenu.displayMenu(); // Call the displayMenu method
                }
                case 7 ->  medicalRecordMenu.displayMenu();
                case 8 -> System.out.println("System shutting down. Goodbye!");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 8);
        
        scanner.close(); // Close the scanner to prevent resource leaks
    }
}
