
package main.classes;
import java.util.InputMismatchException;
import java.util.Scanner;

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
                        viewPrescriptions();
                        break;
                    case UPDATE_PRESCRIPTION:
                        updatePrescription();
                        break;
                    case DELETE_PRESCRIPTION:
                        deletePrescription();
                        break;
                    case BACK_TO_MAIN:
                        System.out.println("Returning to main menu...");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
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
            int patientId = getValidIntInput(scanner);

            // Get medication details
            System.out.print("Enter medication name: ");
            String medication = getValidStringInput(scanner);

            System.out.print("Enter dosage (e.g., '500mg'): ");
            String dosage = getValidStringInput(scanner);

            System.out.print("Enter frequency (e.g., '3 times daily'): ");
            String frequency = getValidStringInput(scanner);

            System.out.print("Enter duration (e.g., '7 days'): ");
            String duration = getValidStringInput(scanner);

            System.out.print("Enter any special instructions: ");
            String instructions = getValidStringInput(scanner);

            // Display prescription summary
            displayPrescriptionSummary(medication, dosage, frequency, duration, instructions);
            
            // TODO: Add logic to save the prescription to database
            
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }
    
    
    private static String generatePrescriptionId() {
        return "PRES" + System.currentTimeMillis();
    }
    
    

    private static String getValidStringInput(Scanner scanner) {
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
        return input;
    }

    private static int getValidIntInput(Scanner scanner) {
        int value = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (value <= 0) {
            throw new IllegalArgumentException("Value must be positive");
        }
        return value;
    }

    private static void displayPrescriptionSummary(String medication, String dosage, 
                                                 String frequency, String duration, 
                                                 String instructions) {
        System.out.println("\nPrescription created successfully!");
        System.out.println("Medication: " + medication);
        System.out.println("Dosage: " + dosage);
        System.out.println("Frequency: " + frequency);
        System.out.println("Duration: " + duration);
        System.out.println("Instructions: " + instructions);
    }

    private static void viewPrescriptions() {
        System.out.println("\n=== View Prescriptions ===");
        try {
            // Here you would normally fetch and display prescriptions
            System.out.println("Feature to be implemented:");
            System.out.println("- Will display list of prescriptions");
            System.out.println("- Will allow filtering by patient");
            System.out.println("- Will show prescription details");
        } catch (Exception e) {
            System.out.println("Error viewing prescriptions: " + e.getMessage());
        }
    }

    private static void updatePrescription() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Update Prescription ===");
        
        try {
            System.out.print("Enter prescription ID to update: ");
            int prescriptionId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Here you would normally fetch the existing prescription
            System.out.println("Feature to be implemented:");
            System.out.println("- Will allow updating prescription details");
            System.out.println("- Will validate prescription ID");
            System.out.println("- Will save updated information");

        } catch (Exception e) {
            System.out.println("Error updating prescription: " + e.getMessage());
        }
    }

    private static void deletePrescription() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Delete Prescription ===");
        
        try {
            System.out.print("Enter prescription ID to delete: ");
            int prescriptionId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Are you sure you want to delete this prescription? (y/n): ");
            String confirm = scanner.nextLine();

            if (confirm.toLowerCase().equals("y")) {
                // Here you would normally delete the prescription
                System.out.println("Prescription deleted successfully!");
            } else {
                System.out.println("Deletion cancelled.");
            }

        } catch (Exception e) {
            System.out.println("Error deleting prescription: " + e.getMessage());
        }
    }
}
