
package main.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MedicalCertificateMenu {
    private final Scanner scanner;
    private final Doctor doctor;  // The doctor who will issue certificates
    private final Patient patient ;
    private static final String ERROR_PREFIX = "Error: ";
    private static final String SUCCESS_SUFFIX = " successfully.";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    
    private static final Map<Integer, String> MENU_OPTIONS = new HashMap<>();
    
    static {
        MENU_OPTIONS.put(1, "Create New Medical Certificate");
        MENU_OPTIONS.put(2, "View Certificate Details");
        MENU_OPTIONS.put(3, "Update Certificate");
        MENU_OPTIONS.put(4, "Generate Certificate Text");
        MENU_OPTIONS.put(0, "Exit");
    }

    public MedicalCertificateMenu(Doctor doctor , Patient patient ) {
        if (doctor == null || patient == null) {
            throw new IllegalArgumentException("Doctor and patient cannot be null");
        }
        this.doctor = doctor;
        this.patient = patient;
        this.scanner = new Scanner(System.in);  // Initialize the scanner
    }
    

    public void displayMenu() {
        MedicalCertificate certificate = null;
        boolean exit = false;
        
        while (!exit) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1 -> certificate = createNewCertificate();
                case 2 -> viewCertificateDetails(certificate);
                case 3 -> updateCertificate(certificate);
                case 4 -> generateCertificateText(certificate);
                case 0 -> exit = true;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Medical Certificate Management ===");
        System.out.println("Doctor: Dr. " + doctor.getDoctorName());
        MENU_OPTIONS.forEach((key, value) -> 
            System.out.println(key + ". " + value));
        System.out.println("===================================");
    }

    private MedicalCertificate createNewCertificate() {
        try {
            System.out.println("\n=== Create New Medical Certificate ===");
            
            // Get certificate details
            String certificateId = getStringInput("Certificate ID: ");
            
            // Get certificate purpose and description
            String purpose = getStringInput("Certificate Purpose (e.g., work absence, sports): ");
            String description = getMultiLineInput("Medical Observations/Restrictions (empty line to finish):");
            
            // Get dates
            Date issueDate = getDateInput("Issue Date (dd/MM/yyyy): ");
            Date validUntil = null;
            if (confirmAction("Add expiration date")) {
                validUntil = getDateInput("Valid Until (dd/MM/yyyy): ");
            }
            
            // Create and return the certificate
            MedicalCertificate certificate = new MedicalCertificate(
                certificateId, patient , doctor, purpose, description, issueDate, validUntil
            );
            
            System.out.println("Medical Certificate created" + SUCCESS_SUFFIX);
            return certificate;
            
        } catch (Exception e) {
            System.out.println(ERROR_PREFIX + e.getMessage());
            return null;
        }
    }

    private void viewCertificateDetails(MedicalCertificate certificate) {
        if (certificate == null) {
            System.out.println(ERROR_PREFIX + "No certificate available. Please create one first.");
            return;
        }


System.out.println("\n=== Certificate Details ===");
        System.out.println("Certificate ID: " + certificate.getCertificateId());
        System.out.println("Patient: " + certificate.getPatient().getName());
        System.out.println("Doctor: Dr. " + certificate.getDoctor().getDoctorName());
        System.out.println("Purpose: " + certificate.getPurpose());
        System.out.println("Description: " + certificate.getDescription());
        System.out.println("Issue Date: " + DATE_FORMAT.format(certificate.getIssueDate()));
        if (certificate.getValidUntil() != null) {
            System.out.println("Valid Until: " + DATE_FORMAT.format(certificate.getValidUntil()));
        }
    }

    private void updateCertificate(MedicalCertificate certificate) {
        if (certificate == null) {
            System.out.println(ERROR_PREFIX + "No certificate available. Please create one first.");
            return;
        }

        try {
            System.out.println("\n=== Update Certificate ===");
            System.out.println("1. Update Purpose");
            System.out.println("2. Update Description");
            System.out.println("3. Update Valid Until Date");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1 -> {
                    String newPurpose = getStringInput("New Purpose: ");
                    certificate.setPurpose(newPurpose);
                }
                case 2 -> {
                    String newDescription = getMultiLineInput("New Description (empty line to finish):");
                    certificate.setDescription(newDescription);
                }
                case 3 -> {
                    Date newValidUntil = getDateInput("New Valid Until Date (dd/MM/yyyy): ");
                    certificate.setValidUntil(newValidUntil);
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
            System.out.println("Certificate updated" + SUCCESS_SUFFIX);
            
        } catch (Exception e) {
            System.out.println(ERROR_PREFIX + e.getMessage());
        }
    }

    private void generateCertificateText(MedicalCertificate certificate) {
        if (certificate == null) {
            System.out.println(ERROR_PREFIX + "No certificate available. Please create one first.");
            return;
        }

        System.out.println("\n=== Generated Certificate ===");
        System.out.println(certificate.generateCertificateText());
    }

    // Utility Methods
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
