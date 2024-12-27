
package main.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Consumer;



public class MedicalRecordMenu {

    Scanner scanner = new Scanner(System.in);
    MedicalRecordManager recordManager = new MedicalRecordManager();
    
    public void displayMenu() {
        while (true) {
            System.out.println("\n=== Medical Record Management ===");
            System.out.println("1. Add Medical Record");
            System.out.println("2. View Medical Record");
            System.out.println("3. Update Medical Record");
            System.out.println("4. Delete Medical Record");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                switch (choice) {
                    case 1 -> addMedicalRecord();
                    case 2 -> viewMedicalRecord();
                    case 3 -> updateMedicalRecord();
                    case 4 -> deleteMedicalRecord();
                    case 5 -> {
                        return;
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    public String getName() {
        return "Medical Record Menu";
    }
   private String generateUniqueRecordId() {
    LocalDate currentDate = LocalDate.now();
    String datePrefix = String.format("%d%02d%02d", 
        currentDate.getYear(),
        currentDate.getMonthValue(),
        currentDate.getDayOfMonth());
    
    // Generate a shorter unique string using UUID
    String uniquePart = UUID.randomUUID().toString()
        .substring(0, 8)
        .toUpperCase();
    
    return datePrefix + "-" + uniquePart;
}

    
    private void addMedicalRecord() {
        try {
            System.out.println("\n=== Add New Medical Record ===");
            
            // Get and validate Patient
            System.out.print("Enter Patient ID: ");
            String patientId = scanner.nextLine().trim();
            if (patientId.isEmpty()) {
                System.out.println("Patient ID cannot be empty!");
                return;
            }
            
            
            // Get patient from the PatientManager instance
        PatientManager patientManager = new PatientManager(); // Create instance
        Patient patient = patientManager.getPatientById(patientId);
        if (patient == null) {
            System.out.println("Patient not found with ID: " + patientId);
            return;
        }
            // Get and validate Doctor
            System.out.print("Enter Doctor ID: ");
            String doctorId = scanner.nextLine().trim();
            if (doctorId.isEmpty()) {
                System.out.println("Doctor ID cannot be empty!");
                return;
            }
            
            Doctor doctor = DoctorManager.getDoctorById(doctorId);
            if (doctor == null) {
                System.out.println("Doctor not found with ID: " + doctorId);
                return;
            }
    
            // Create new medical record
            String recordId = generateUniqueRecordId();
            
            MedicalRecord newRecord = new MedicalRecord(
                recordId,                // String recordId
                patient,                 // Patient patient
                doctor,                  // Doctor doctor
                new ArrayList<>(),       // List<String> diagnoses
                new ArrayList<>(),       // List<String> treatments
                new ArrayList<>(),       // List<String> allergies
                new ArrayList<>(),       // List<String> medicalHistory
                new ArrayList<>(),       // List<String> surgicalHistory
                new ArrayList<>(),       // List<Prescription> prescriptions
                ""                       // String additionalNotes
            );
            
            // Add diagnosis and treatment
            System.out.print("Enter initial diagnosis: ");
            String diagnosis = scanner.nextLine().trim();
            if (!diagnosis.isEmpty()) {
                newRecord.addDiagnosis(diagnosis);
            }
    
            System.out.print("Enter initial treatment: ");
            String treatment = scanner.nextLine().trim();
            if (!treatment.isEmpty()) {
                newRecord.addTreatment(treatment);
            }
    
            // Save the record
            recordManager.addMedicalRecord(newRecord);
            System.out.println("\nMedical record added successfully!");
            System.out.println("Record ID: " + recordId);
    
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    

    
    private void updateMedicalRecord() {
        System.out.println("\n=== Update Medical Record ===");
        System.out.print("Enter Record ID: ");
        String recordId = scanner.nextLine().trim();
        
        MedicalRecord record = recordManager.getMedicalRecordById(recordId);
        if (record == null) {
            System.out.println("Medical record not found.");
            return;
        }

        while (true) {
            displayUpdateMenu();
            try {
                int updateChoice = Integer.parseInt(scanner.nextLine().trim());
                
                if (updateChoice == 8) break;

                handleUpdateChoice(updateChoice, record);
                recordManager.updateMedicalRecord(record);
                System.out.println("Medical record updated successfully!");
                
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void displayUpdateMenu() {
        System.out.println("\n=== Update Options ===");
        System.out.println("1. Add Diagnosis");
        System.out.println("2. Add Treatment");
        System.out.println("3. Add Allergy");
        System.out.println("4. Add Medical History");
        System.out.println("5. Add Surgical History");
        System.out.println("6. Update Vital Signs");
        System.out.println("7. Add Additional Notes");
        System.out.println("8. Return to Previous Menu");
        System.out.print("Enter your choice: ");
    }

    private void handleUpdateChoice(int choice, MedicalRecord record) {
        switch (choice) {
            case 1 -> updateField("Enter new diagnosis: ", record::addDiagnosis);
            case 2 -> updateField("Enter new treatment: ", record::addTreatment);
            case 3 -> updateField("Enter new allergy: ", record::addAllergy);
            case 4 -> updateField("Enter medical history item: ", record::addMedicalHistory);
            case 5 -> updateField("Enter surgical history item: ", record::addSurgicalHistory);
            case 6 -> updateNotes(record);
            default -> System.out.println("Invalid choice!");
        }
    }

    private void updateField(String prompt, Consumer<String> updateFunction) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            updateFunction.accept(input);
        }
    }

    private void updateNotes(MedicalRecord record) {
        System.out.print("Enter additional notes: ");
        String notes = scanner.nextLine().trim();
        if (!notes.isEmpty()) {
            String currentNotes = record.getAdditionalNotes();
            record.setAdditionalNotes(currentNotes == null || currentNotes.isEmpty() ? 
                notes : currentNotes + "\n" + notes);
        }
    }

    private void viewMedicalRecord() {
        System.out.println("\n=== View Medical Record ===");
        System.out.println("1. Search by Record ID");
        System.out.println("2. Search by Patient ID");
        System.out.print("Enter your choice: ");
        
        try {
            int searchChoice = Integer.parseInt(scanner.nextLine().trim());
            MedicalRecord record = findRecord(searchChoice);
            
            if (record != null) {
                System.out.println("\n=== Medical Record Details ===");
                System.out.println(record);
            } else {
                System.out.println("Medical record not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private MedicalRecord findRecord(int searchChoice) {
        return switch (searchChoice) {
            case 1 -> {
                System.out.print("Enter Record ID: ");
                yield recordManager.getMedicalRecordById(scanner.nextLine().trim());
            }
            case 2 -> {
                System.out.print("Enter Patient ID: ");
                String patientId = scanner.nextLine().trim();
                List<MedicalRecord> patientRecords = recordManager.getPatientRecords(patientId);
                if (patientRecords.isEmpty()) {
                    System.out.println("No records found for this patient.");
                    yield null;
                }
                yield patientRecords.get(patientRecords.size() - 1);
            }
            default -> {
                System.out.println("Invalid choice!");
                yield null;
            }
        };
    }

    private void deleteMedicalRecord() {
        System.out.println("\n=== Delete Medical Record ===");
        System.out.print("Enter Record ID: ");
        String recordId = scanner.nextLine().trim();
        
        MedicalRecord recordToDelete = recordManager.getMedicalRecordById(recordId);
        if (recordToDelete == null) {
            System.out.println("Medical record not found.");
            return;
        }
        
        displayRecordToDelete(recordToDelete);
        
        if (confirmDeletion()) {
            boolean deleted = recordManager.deleteMedicalRecord(recordId);
            System.out.println(deleted ? 
                "Medical record deleted successfully!" : 
                "Failed to delete medical record.");
        } else {
            System.out.println("Delete operation cancelled.");
        }
    }

    private void displayRecordToDelete(MedicalRecord record) {
        System.out.println("\nRecord to delete:");
        System.out.println("Record ID: " + record.getRecordId());
        System.out.println("Patient: " + record.getPatient().getName());
        System.out.println("Doctor: " + record.getDoctor().getDoctorName());
        System.out.println("Date: " + record.getRecordDate());
    }

    private boolean confirmDeletion() {
        System.out.print("Are you sure you want to delete this record? (y/n): ");
        return scanner.nextLine().trim().equalsIgnoreCase("y");
    }}

    






