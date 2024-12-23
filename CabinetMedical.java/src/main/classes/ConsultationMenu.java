package main.classes;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ConsultationMenu {
    private static final String ERROR_PREFIX = "Error: ";
    private static final String SUCCESS_SUFFIX = " ✓";
    
    private Scanner scanner;
    private Consultation consultation ;
    private consultationFee selectedFee;

    public ConsultationMenu() {
        this.scanner = new Scanner(System.in);
        this.consultation = consultation;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=== Consultation Menu ===");
            System.out.println("1. Select Consultation Type");
            System.out.println("2. Start Consultation");
            System.out.println("3. Medical Records");
            System.out.println("4. View Fees");
            System.out.println("5. View Summary");
            System.out.println("6. Complete Consultation");
            System.out.println("0. Exit");

            try {
                int choice = getIntInput("Enter your choice: ");
                if (processChoice(choice)) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(ERROR_PREFIX + e.getMessage());
            }
        }
    }

    private boolean processChoice(int choice) {
        try {
            return switch (choice) {
                case 1 -> handleConsultationType();
                case 2 -> handleStartConsultation();
                case 3 -> handleMedicalRecords();
                case 4 -> handleViewFees();
                case 5 -> handleViewSummary();
                case 6 -> handleCompleteConsultation();
                case 0 -> true;
                default -> {
                    System.out.println("Invalid option. Please try again.");
                    yield false;
                }
            };
        } catch (Exception e) {
            System.out.println(ERROR_PREFIX + e.getMessage());
            return false;
        }
    }

    private boolean handleConsultationType() {
        System.out.println("\n=== Available Consultation Types ===");
        for (consultationFee fee : consultationFee.values()) {
            System.out.println(fee.toString());
            if (fee.isSuitableForTelemedicine()) {
                System.out.println("* Available for Telemedicine");
            }
            System.out.println("Estimated Wait Time: " + fee.getEstimatedWaitTime());
            System.out.println("--------------------------------");
        }

        try {
            String type = getStringInput("Enter consultation type: ");
            selectedFee = consultationFee.getByType(type);
            System.out.println("Selected: " + selectedFee.getFullDescription());
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_PREFIX + e.getMessage());
            return false;
        }
    }

    private boolean handleStartConsultation() {
        if (selectedFee == null) {
            System.out.println(ERROR_PREFIX + "Please select a consultation type first");
            return false;
        }

        try {
            consultation.startConsultation();
            System.out.println("Consultation started" + SUCCESS_SUFFIX);
            return false;
        } catch (Exception e) {
            System.out.println(ERROR_PREFIX + e.getMessage());
            return false;
        }
    }

    private boolean handleMedicalRecords() {
        if (!consultation.isStarted()) {
            System.out.println(ERROR_PREFIX + "Consultation must be started first");
            return false;
        }
        addMedicalRecordMenu();
        return false;
    }

    private boolean handleViewFees() {
        if (selectedFee == null) {
            System.out.println(ERROR_PREFIX + "Please select a consultation type first");
            return false;
        }


System.out.println("\n=== Fee Details ===");
        System.out.println("Base Fee: " + selectedFee.getFormattedFee());
        System.out.println("Hourly Rate: " + String.format("%,.2f DZD", selectedFee.getHourlyRate()));
        
        LocalDateTime consultationTime = consultation.getConsultationDateTime();
        boolean isWeekend = confirmAction("Calculate weekend fee");
        boolean isHoliday = confirmAction("Calculate holiday fee");
        
        double calculatedFee = selectedFee.calculateFee(consultationTime, isHoliday);
        System.out.printf("Calculated Fee: %,.2f DZD%n", calculatedFee);
        
        if (selectedFee.isAfterHours(consultationTime)) {
            System.out.println("* After-hours surcharge applied");
        }
        if (selectedFee.isEmergency()) {
            System.out.println("* Emergency consultation fee applied");
        }
        
        return false;
    }

    private boolean handleViewSummary() {
        try {
            System.out.println("\n=== Consultation Summary ===");
            System.out.println(consultation.getConsultationSummary());
            return false;
        } catch (Exception e) {
            System.out.println(ERROR_PREFIX + e.getMessage());
            return false;
        }
    }

    private boolean handleCompleteConsultation() {
        if (!confirmAction("complete this consultation")) {
            return false;
        }
        try {
            consultation.completeConsultation();
            System.out.println("Consultation completed" + SUCCESS_SUFFIX);
            handleViewSummary();
            return true;
        } catch (Exception e) {
            System.out.println(ERROR_PREFIX + e.getMessage());
            return false;
        }
    }

    private void addMedicalRecordMenu() {
        while (true) {
            System.out.println("\n=== Medical Records Menu ===");
            System.out.println("1. Add Diagnosis");
            System.out.println("2. Add Treatment");
            System.out.println("3. Update Medical History");
            System.out.println("4. Log Allergy");
            System.out.println("5. Add Note");
            System.out.println("0. Back to Main Menu");

            try {
                int choice = getIntInput("Enter your choice: ");
                if (!processMedicalRecordChoice(choice)) {
                    return;
                }
            } catch (Exception e) {
                System.out.println(ERROR_PREFIX + e.getMessage());
            }
        }
    }

    private boolean processMedicalRecordChoice(int choice) {
        try {
            return switch (choice) {
                case 1 -> { addDiagnosis(); yield true; }
                case 2 -> { addTreatment(); yield true; }
                case 3 -> { updateMedicalHistory(); yield true; }
                case 4 -> { logAllergy(); yield true; }
                case 5 -> { addNote(); yield true; }
                case 0 -> false;
                default -> {
                    System.out.println("Invalid option. Please try again.");
                    yield true;
                }
            };
        } catch (Exception e) {
            System.out.println(ERROR_PREFIX + e.getMessage());
            return true;
        }
    }

    private void addDiagnosis() {
        String diagnosis = getMultiLineInput("Enter diagnosis (press Enter twice to finish):");
        consultation.provideDiagnosis(diagnosis);
        System.out.println("Diagnosis added" + SUCCESS_SUFFIX);
    }

    private void addTreatment() {
        String treatment = getMultiLineInput("Enter treatment plan (press Enter twice to finish):");
        consultation.addTreatment(treatment);
        System.out.println("Treatment added" + SUCCESS_SUFFIX);
    }

    private void updateMedicalHistory() {
        String history = getMultiLineInput("Enter medical history update (press Enter twice to finish):");
        consultation.updateMedicalHistory(history);
        System.out.println("Medical history updated" + SUCCESS_SUFFIX);
    }


private void logAllergy() {
        String allergy = getStringInput("Enter allergy information: ");
        consultation.logAllergy(allergy);
        System.out.println("Allergy logged" + SUCCESS_SUFFIX);
    }

    private void addNote() {
        String note = getMultiLineInput("Enter note (press Enter twice to finish):");
        consultation.addNote(note);
        System.out.println("Note added" + SUCCESS_SUFFIX);
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
        String result = input.toString().trim();
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
        return result;
    }

    private int getIntInput(String prompt) {
        String input = getStringInput(prompt);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a valid number");
        }
    }

    private boolean confirmAction(String action) {
        return getStringInput("Do you want to " + action + "? (y/n): ")
            .toLowerCase()
            .startsWith("y");
    }

    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

    public Consultation getConsultation() {
        return consultation;
    }
}
