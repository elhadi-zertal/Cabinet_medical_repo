package main.classes;

import java.util.Scanner;

public class ConsultationMenu {
    private Consultation consultation;

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nConsultation Menu:");
            System.out.println("1. Select Consultation Type");
            System.out.println("2. Start Consultation");
            System.out.println("3. Add Treatment");
            System.out.println("4. Add Note");
            System.out.println("5. View Fee");
            System.out.println("6. View Summary");
            System.out.println("7. Complete Consultation");
            System.out.println("8. Back to the main menu");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    selectConsultationType(scanner);
                    break;
                case 2:
                    startConsultation();
                    break;
                case 3:
                    addTreatment(scanner);
                    break;
                case 4:
                    addNote(scanner);
                    break;
                case 5:
                    viewFee();
                    break;
                case 6:
                    viewSummary();
                    break;
                case 7:
                    completeConsultation();
                    break;
                case 8:
                    exit = true;
                    System.out.println("Returning to the main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
         
    }
    }
    private void selectConsultationType(Scanner scanner) {
        System.out.println("\nAvailable Consultation Types:");
        for (consultationFee fee : consultationFee.values()) {
            System.out.println(fee.ordinal() + 1 + ". " + fee.getFullDescription());
        }

        System.out.print("Select a consultation type by number: ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (typeChoice > 0 && typeChoice <= consultationFee.values().length) {
            consultationFee selectedFee = consultationFee.values()[typeChoice - 1];
            System.out.print("Enter doctor's name: ");
            String doctorName = scanner.nextLine().trim();
    
            System.out.print("Enter patient's name: ");
            String patientName = scanner.nextLine().trim();
    

            consultation = new Consultation(selectedFee , doctorName , patientName);
            System.out.println("Selected Consultation Type: " + selectedFee.getDescription());
            System.out.println("Doctor: " + doctorName);
            System.out.println("Patient: " + patientName);
    
        } else {
            System.out.println("Invalid choice. Please select a valid consultation type.");
        }
    }
  

    private void startConsultation() {
        if (consultation == null) {
            System.out.println("No consultation type selected. Please select a type first.");
            return;
        }

        try {
            consultation.startConsultation();
            System.out.println("Consultation started.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addTreatment(Scanner scanner) {
        if (consultation == null) {
            System.out.println("No consultation in progress. Please start a consultation first.");
            return;
        }

        System.out.print("Enter treatment details: ");
        String treatment = scanner.nextLine();

        try {
            consultation.addTreatment(treatment);
            System.out.println("Treatment added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addNote(Scanner scanner) {
        if (consultation == null) {
            System.out.println("No consultation in progress. Please start a consultation first.");
            return;
        }

        System.out.print("Enter note details: ");
        String note = scanner.nextLine();

        try {
            consultation.addNote(note);
            System.out.println("Note added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewFee() {
        if (consultation == null) {
            System.out.println("No consultation type selected. Please select a type first.");
            return;
        }

        consultationFee fee = consultation.getFee();
        System.out.println("\nConsultation Fee Details:");
        System.out.println(fee.getFullDescription());
    }

    private void viewSummary() {
        if (consultation == null) {
            System.out.println("No consultation in progress. Please start a consultation first.");
            return;
        }

        System.out.println("\nConsultation Summary:");
        System.out.println(consultation.getConsultationSummary());
    }

    private void completeConsultation() {
        if (consultation == null) {
            System.out.println("No consultation in progress. Please start a consultation first.");
            return;
        }

        try {
            consultation.completeConsultation();
            System.out.println("Consultation completed.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
