package main.classes;

import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MedicalCertificateMenu {
    private Scanner scanner;
    private Doctor doctor;
    private Patient patient;
    private MedicalCertificate currentCertificate;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final String SUCCESS_SUFFIX = " successfully!";
    private static final String ERROR_PREFIX = "Error: ";

    public MedicalCertificateMenu(Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null");
        }
        this.scanner = scanner;
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n=== Medical Certificate Management ===");
            System.out.println("1. Create New Certificate");
            System.out.println("2. View Certificate Details");
            System.out.println("3. Update Certificate");
            System.out.println("4. Generate Certificate Text");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> {
                        Doctor selectedDoctor = selectDoctor();
                        Patient selectedPatient = selectPatient();
                        if (selectedDoctor != null && selectedPatient != null) {
                            this.doctor = selectedDoctor;
                            this.patient = selectedPatient;
                            currentCertificate = createNewCertificate();
                        }
                    }
                    case 2 -> viewCertificateDetails();
                    case 3 -> updateCertificate();
                    case 4 -> generateCertificateText();
                    case 5 -> System.out.println("Returning to Main Menu...");
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = 0;
            }
        } while (choice != 5);
    }

    private Doctor selectDoctor() {
        System.out.println("\nSelect Doctor:");
        // TODO: Implement proper doctor selection using DoctorManager
        return new Doctor("D001", "Dr. Smith");
    }

    private Patient selectPatient() {
        System.out.println("\nSelect Patient:");
        // TODO: Implement proper patient selection using PatientManager
        return new Patient("P001", "John Doe");
    }

    private MedicalCertificate createNewCertificate() {
        if (doctor == null || patient == null) {
            System.out.println(ERROR_PREFIX + "Doctor and Patient must be selected first.");
            return null;
        }

        try {
            System.out.println("\n=== Create New Medical Certificate ===");
            String certificateId = getStringInput("Certificate ID: ");
            String purpose = getStringInput("Certificate Purpose (e.g., work absence, sports): ");
            String description = getMultiLineInput("Medical Observations/Restrictions (empty line to finish):");
            
            Date issueDate = getDateInput("Issue Date (dd/MM/yyyy): ");
            Date validUntil = null;
            if (confirmAction("Add expiration date")) {
                validUntil = getDateInput("Valid Until (dd/MM/yyyy): ");
            }
            
            MedicalCertificate certificate = new MedicalCertificate(
                certificateId, patient, doctor, purpose, description, issueDate, validUntil
            );
            System.out.println("Medical Certificate created" + SUCCESS_SUFFIX);
            return certificate;
        } catch (Exception e) {
            System.out.println(ERROR_PREFIX + e.getMessage());
            return null;
        }
    }

    private void viewCertificateDetails() {
        if (currentCertificate == null) {
            System.out.println(ERROR_PREFIX + "No certificate available. Please create one first.");
            return;
        }

        System.out.println("\n=== Certificate Details ===");
        System.out.println("Certificate ID: " + currentCertificate.getCertificateId());
        System.out.println("Patient: " + currentCertificate.getPatient().getName());
        System.out.println("Doctor: Dr. " + currentCertificate.getDoctor().getDoctorName());
        System.out.println("Purpose: " + currentCertificate.getPurpose());
        System.out.println("Description: " + currentCertificate.getDescription());
        System.out.println("Issue Date: " + DATE_FORMAT.format(currentCertificate.getIssueDate()));
        if (currentCertificate.getValidUntil() != null) {
            System.out.println("Valid Until: " + DATE_FORMAT.format(currentCertificate.getValidUntil()));
        }
    }

    private void updateCertificate() {
        if (currentCertificate == null) {
            System.out.println(ERROR_PREFIX + "No certificate available. Please create one first.");
            return;
        }

        try {
            boolean updating = true;
            while (updating) {
                System.out.println("\n=== Update Certificate ===");
                System.out.println("1. Update Purpose");
                System.out.println("2. Update Description");
                System.out.println("3. Update Valid Until Date");
                System.out.println("4. Return to Previous Menu");

                int choice = getIntInput("Enter your choice: ");
                switch (choice) {
                    case 1 -> {
                        String newPurpose = getStringInput("New Purpose: ");
                        currentCertificate.setPurpose(newPurpose);
                        System.out.println("Purpose updated" + SUCCESS_SUFFIX);
                    }
                    case 2 -> {
                        String newDescription = getMultiLineInput("New Description (empty line to finish):");
                        currentCertificate.setDescription(newDescription);
                        System.out.println("Description updated" + SUCCESS_SUFFIX);
                    }
                    case 3 -> {
                        Date newValidUntil = getDateInput("New Valid Until Date (dd/MM/yyyy): ");
                        currentCertificate.setValidUntil(newValidUntil);
                        System.out.println("Valid Until date updated" + SUCCESS_SUFFIX);
                    }
                    case 4 -> updating = false;
                    default -> System.out.println("Invalid option.");
                }
            }
        } catch (Exception e) {
            System.out.println(ERROR_PREFIX + e.getMessage());
        }
    }

    private void generateCertificateText() {
        if (currentCertificate == null) {
            System.out.println(ERROR_PREFIX + "No certificate available. Please create one first.");
            return;
        }
        System.out.println("\n=== Generated Certificate ===");
        System.out.println(currentCertificate.generateCertificateText());
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
        return input;
    }

    private String getMultiLineInput(String prompt) {
        System.out.println(prompt);
        StringBuilder input = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            input.append(line).append("\n");
        }
        return input.toString().trim();
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a valid number");
        }
    }

    private Date getDateInput(String prompt) throws ParseException {
        System.out.print(prompt);
        String dateStr = scanner.nextLine().trim();
        return DATE_FORMAT.parse(dateStr);
    }

    private boolean confirmAction(String action) {
        System.out.printf("%s? (y/n): ", action);
        String response = scanner.nextLine().trim().toLowerCase();
        return response.startsWith("y");
    }
}