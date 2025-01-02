
package main.classes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PrescriptionMenu {
    private static final int CREATE_PRESCRIPTION = 1;
    private static final int VIEW_PRESCRIPTIONS = 2;
    private static final int UPDATE_PRESCRIPTION = 3;
    private static final int DELETE_PRESCRIPTION = 4;
    private static final int BACK_TO_MAIN = 5;

    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Display the menu options
            System.out.println("\n=== Prescription Management Menu ===");
            System.out.println("1. Create New Prescription");
            System.out.println("2. View Prescriptions");
            System.out.println("3. Update Prescription");
            System.out.println("4. Delete Prescription");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case CREATE_PRESCRIPTION:
                        createPrescription(scanner);
                        break;
                    case VIEW_PRESCRIPTIONS:
                        viewPrescriptions(scanner);
                        break;
                    case UPDATE_PRESCRIPTION:
                        updatePrescription(scanner);
                        break;
                    case DELETE_PRESCRIPTION:
                        deletePrescription(scanner);
                        break;
                    case BACK_TO_MAIN:
                        System.out.println("Returning to main menu...");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Clear invalid input
                choice = 0; // Reset choice to continue loop
            }
        } while (choice != BACK_TO_MAIN);
    }

    private static void createPrescription(Scanner scanner) {
        System.out.println("\n=== Create New Prescription ===");
    
        try {
            // Get patient ID
            System.out.print("Enter patient ID: ");
            String patientId = scanner.nextLine().trim();
    
            // Get doctor ID
            System.out.print("Enter doctor ID: ");
            String doctorId = scanner.nextLine().trim();
    
            // Get medication details
            System.out.print("Enter medication name: ");
            String medicationName = scanner.nextLine().trim();
    
            System.out.print("Enter dosage (e.g., '500mg'): ");
            String dosage = scanner.nextLine().trim();
    
            System.out.print("Enter frequency (e.g., '3 times daily'): ");
            String frequency = scanner.nextLine().trim();
    
            System.out.print("Enter duration (e.g., '7 days'): ");
            String duration = scanner.nextLine().trim();
    
            System.out.print("Enter any special instructions: ");
            String instructions = scanner.nextLine().trim();
    
            // Create a new medication
            Medication medication = new Medication(medicationName, dosage, frequency, duration, instructions);
    
            // Create a set of medications
            Set<Medication> medications = new HashSet<>();
            medications.add(medication);
    
            // Create a new prescription using the Builder pattern
            Doctor doctor = DoctorManager.getDoctorById(doctorId);
            if (doctor == null) {
                System.out.println("Doctor with ID " + doctorId + " not found.");
                return;
            }
            
            Patient patient = PatientManager.getPatientById(patientId);
            if (patient == null) {
                System.out.println("Patient with ID " + patientId + " not found.");
                return;
            }

Prescription prescription = new Prescription.Builder(generatePrescriptionId())
    .doctor(doctor)
    .patient(patient)
    .medications(medications)
    .prescriptionDate(LocalDateTime.now())
    .validityDate(LocalDateTime.now().plusDays(7))
    .medicalExams(new ArrayList<>())
    .additionalNotes(instructions)
    .build();
    
            // Add the prescription to the static list
            Prescription.getPrescriptions().add(prescription);
    
            // Inform the user of the successful creation
            System.out.println("Prescription created successfully!");
            System.out.println(prescription);
    
        } catch (Exception e) {
            System.out.println("Error creating prescription: " + e.getMessage());
        }
    }

    private static void viewPrescriptions(Scanner scanner) {
        System.out.println("\n=== View Prescriptions ===");
    
        try {
            // Ask the user for the search criteria
            System.out.println("Search by:");
            System.out.println("1. Patient ID");
            System.out.println("2. Patient Name");
            System.out.println("3. Prescription ID");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());
    
            List<Prescription> prescriptionsToView = new ArrayList<>();
    
            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID: ");
                    String patientId = scanner.nextLine().trim();
                    prescriptionsToView = Prescription.getPrescriptionsByPatientId(patientId);
                    break;
                case 2:
                    System.out.print("Enter Patient Name: ");
                    String patientName = scanner.nextLine().trim();
                    prescriptionsToView = Prescription.getPrescriptionsByPatientName(patientName);
                    break;
                case 3:
                    System.out.print("Enter Prescription ID: ");
                    String prescriptionId = scanner.nextLine().trim();
                    Prescription prescription = Prescription.getPrescriptionById(prescriptionId);
                    if (prescription != null) {
                        prescriptionsToView.add(prescription);
                    }
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }
    
            if (prescriptionsToView.isEmpty()) {
                System.out.println("No prescriptions found.");
                return;
            }
    
            // Display the list of prescriptions
            System.out.println("Prescriptions found:");
            for (Prescription p : prescriptionsToView) {
                System.out.println(p);
            }
    
        } catch (Exception e) {
            System.out.println("Error viewing prescriptions: " + e.getMessage());
        }
    }
    
    private static void updatePrescription(Scanner scanner) {
        System.out.println("\n=== Update Prescription ===");
    
        try {
            // Ask the user for the search criteria
            System.out.println("Search by:");
            System.out.println("1. Patient ID");
            System.out.println("2. Patient Name");
            System.out.println("3. Prescription ID");
            System.out.print("Enter your choice: ");
            int searchChoice = Integer.parseInt(scanner.nextLine().trim());
    
            Prescription prescriptionToUpdate = null;
    
            switch (searchChoice) {
                case 1:
                    System.out.print("Enter Patient ID: ");
                    String patientId = scanner.nextLine().trim();
                    List<Prescription> prescriptionsByPatientId = Prescription.getPrescriptionsByPatientId(patientId);
                    if (prescriptionsByPatientId.isEmpty()) {
                        System.out.println("No prescriptions found for Patient ID: " + patientId);
                        return;
                    }
                    // Display the list of prescriptions
                    System.out.println("Prescriptions found:");
                    for (int i = 0; i < prescriptionsByPatientId.size(); i++) {
                        System.out.println((i + 1) + ": " + prescriptionsByPatientId.get(i));
                    }
                    // Ask the user to select a prescription to update
                    System.out.print("Enter the number of the prescription to update: ");
                    int index = Integer.parseInt(scanner.nextLine().trim()) - 1; // Convert to zero-based index
                    if (index < 0 || index >= prescriptionsByPatientId.size()) {
                        System.out.println("Invalid selection.");
                        return;
                    }
                    prescriptionToUpdate = prescriptionsByPatientId.get(index);
                    break;
                case 2:
                    System.out.print("Enter Patient Name: ");
                    String patientName = scanner.nextLine().trim();
                    List<Prescription> prescriptionsByPatientName = Prescription.getPrescriptionsByPatientName(patientName);
                    if (prescriptionsByPatientName.isEmpty()) {
                        System.out.println("No prescriptions found for Patient Name: " + patientName);
                        return;
                    }
                    // Display the list of prescriptions
                    System.out.println("Prescriptions found:");
                    for (int i = 0; i < prescriptionsByPatientName.size(); i++) {
                        System.out.println((i + 1) + ": " + prescriptionsByPatientName.get(i));
                    }
                    // Ask the user to select a prescription to update
                    System.out.print("Enter the number of the prescription to update: ");
                    index = Integer.parseInt(scanner.nextLine().trim()) - 1; // Convert to zero-based index
                    if (index < 0 || index >= prescriptionsByPatientName.size()) {
                        System.out.println("Invalid selection.");
                        return;
                    }
                    prescriptionToUpdate = prescriptionsByPatientName.get(index);
                    break;
                case 3:
                    System.out.print("Enter Prescription ID: ");
                    String prescriptionId = scanner.nextLine().trim();
                    prescriptionToUpdate = Prescription.getPrescriptionById(prescriptionId);
                    if (prescriptionToUpdate == null) {
                        System.out.println("No prescription found with ID: " + prescriptionId);
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }
    
            // Ask the user which field to update
            System.out.println("\nSelect the field to update:");
          
            System.out.println("1. Medications");
            System.out.println("2. Prescription Date");
            System.out.println("3. Validity Date");
            System.out.println("4. Medical Exams");
            System.out.println("5. Additional Notes");
            System.out.print("Enter your choice: ");
            int updateChoice = Integer.parseInt(scanner.nextLine().trim());
    
            switch (updateChoice) {
                
            case 1: // Update Medications
                System.out.println("Enter new Medication Details:");
                System.out.print("Medication Name: ");
                String medicationName = scanner.nextLine().trim();
                System.out.print("Dosage: ");
                String dosage = scanner.nextLine().trim();
                System.out.print("Frequency: ");
                String frequency = scanner.nextLine().trim();
                System.out.print("Duration: ");
                String duration = scanner.nextLine().trim();
                System.out.print("Special Instructions: ");
                String instructions = scanner.nextLine().trim();

                // Create a new Medication object
                Medication newMedication = new Medication(medicationName, dosage, frequency, duration, instructions);

                // Clear existing medications and add the new one
                prescriptionToUpdate.getMedications().clear();
                prescriptionToUpdate.getMedications().add(newMedication);
                System.out.println("Medications updated successfully!");
                break;
            case 2: // Update Prescription Date
                System.out.print("Enter new Prescription Date (yyyy-MM-dd HH:mm): ");
                String prescriptionDateStr = scanner.nextLine().trim();
                LocalDateTime prescriptionDate = LocalDateTime.parse(prescriptionDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                prescriptionToUpdate.setPrescriptionDate(prescriptionDate);
                System.out.println("Prescription Date updated successfully!");
                break;
            case 3: // Update Validity Date
                System.out.print("Enter new Validity Date (yyyy-MM-dd HH:mm): ");
                String validityDateStr = scanner.nextLine().trim();
                LocalDateTime validityDate = LocalDateTime.parse(validityDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                prescriptionToUpdate.setValidityDate(validityDate);
                System.out.println("Validity Date updated successfully!");
                break;
            case 4: // Update Medical Exams
                System.out.print("Enter new Medical Exam: ");
                String medicalExam = scanner.nextLine().trim();
                prescriptionToUpdate.getMedicalExams().clear(); // Clear existing medical exams
                prescriptionToUpdate.getMedicalExams().add(medicalExam); // Add the new medical exam
                System.out.println("Medical Exams updated successfully!");
                break;
            case 5: // Update Additional Notes
                System.out.print("Enter new Additional Notes: ");
                String additionalNotes = scanner.nextLine().trim();
                prescriptionToUpdate.setAdditionalNotes(additionalNotes);
                System.out.println("Additional Notes updated successfully!");
                break;
            default:
                System.out.println("Invalid choice! No updates were made.");
                return;
        }

    } catch (Exception e) {
        System.out.println("Error updating prescription: " + e.getMessage());
    }
}
                       
    private static void deletePrescription(Scanner scanner) {
        System.out.println("\n=== Delete Prescription ===");
    
        try {
            // Ask the user for the search criteria
            System.out.println("Search by:");
            System.out.println("1. Patient ID");
            System.out.println("2. Patient Name");
            System.out.println("3. Prescription ID");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());
    
            Prescription prescriptionToDelete = null;
    
            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID: ");
                    String patientId = scanner.nextLine().trim();
                    List<Prescription> prescriptionsByPatientId = Prescription.getPrescriptionsByPatientId(patientId);
                    if (prescriptionsByPatientId.isEmpty()) {
                        System.out.println("No prescriptions found for Patient ID: " + patientId);
                        return;
                    }
                    // Display the list of prescriptions
                    System.out.println("Prescriptions found:");
                    for (int i = 0; i < prescriptionsByPatientId.size(); i++) {
                        System.out.println((i + 1) + ": " + prescriptionsByPatientId.get(i));
                    }
                    // Ask the user to select a prescription to delete
                    System.out.print("Enter the number of the prescription to delete: ");
                    int index = Integer.parseInt(scanner.nextLine().trim()) - 1; // Convert to zero-based index
                    if (index < 0 || index >= prescriptionsByPatientId.size()) {
                        System.out.println("Invalid selection.");
                        return;
                    }
                    prescriptionToDelete = prescriptionsByPatientId.get(index);
                    break;
                case 2:
                    System.out.print("Enter Patient Name: ");
                    String patientName = scanner.nextLine().trim();
                    List<Prescription> prescriptionsByPatientName = Prescription.getPrescriptionsByPatientName(patientName);
                    if (prescriptionsByPatientName.isEmpty()) {
                        System.out.println("No prescriptions found for Patient Name: " + patientName);
                        return;
                    }
                    // Display the list of prescriptions
                    System.out.println("Prescriptions found:");
                    for (int i = 0; i < prescriptionsByPatientName.size(); i++) {
                        System.out.println((i + 1) + ": " + prescriptionsByPatientName.get(i));
                    }
                    // Ask the user to select a prescription to delete
                    System.out.print("Enter the number of the prescription to delete: ");
                    index = Integer.parseInt(scanner.nextLine().trim()) - 1; // Convert to zero-based index
                    if (index < 0 || index >= prescriptionsByPatientName.size()) {
                        System.out.println("Invalid selection.");
                        return;
                    }
                    prescriptionToDelete = prescriptionsByPatientName.get(index);
                    break;
                case 3:
                    System.out.print("Enter Prescription ID: ");
                    String prescriptionId = scanner.nextLine().trim();
                    prescriptionToDelete = Prescription.getPrescriptionById(prescriptionId);
                    if (prescriptionToDelete == null) {
                        System.out.println("No prescription found with ID: " + prescriptionId);
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }
    
            // Remove the selected prescription from the static list
            Prescription.getPrescriptions().remove(prescriptionToDelete);
            System.out.println("Prescription deleted successfully!");
    
        } catch (Exception e) {
            System.out.println("Error deleting prescription : " + e.getMessage());
        }
    }

    private static String generatePrescriptionId() {
        // Generate a unique prescription ID (this is a simple example)
        return "RX" + System.currentTimeMillis();
    }
}



