package main.classes;

import java.util.Scanner;

public class DoctorMenu {
    DoctorManager doctorManager = new DoctorManager();
    Scanner scanner = new Scanner(System.in);


    public static Doctor findDoctorByName(String doctorName, DoctorManager doctorManager) {
        if (doctorName == null || doctorName.trim().isEmpty() || doctorManager == null) {
            return null;
        }
        return doctorManager.getDoctorByName(doctorName);
    }
     public static Doctor findDoctorById(String doctorId, DoctorManager doctorManager) {
         if (doctorId == null || doctorId.trim().isEmpty() || doctorManager == null) {
             return null;
         }
         return doctorManager.getDoctorById(doctorId);
     }
     
    // Instance method for internal use
    private Doctor findDoctorByName(String doctorName) {
        return doctorManager.getDoctorByName(doctorName);
    }
    public void displayMenu() {
        int choice;
        do {
            System.out.println("\n=== Doctor Management Menu ===");
            System.out.println("1. Add a Doctor");
            System.out.println("2. Update Doctor Information");
            System.out.println("3. View Doctor Records");
            System.out.println("4. Delete a Doctor");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                processMenuChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = 0;
            }
        } while (choice != 5);
    }

    private void processMenuChoice(int choice) {
        switch (choice) {
            case 1 -> addDoctor();
            case 2 -> updateDoctor();
            case 3 -> viewDoctor();
            case 4 -> deleteDoctor();
            case 5 -> System.out.println("Returning to the main menu...");
            default -> System.out.println("Invalid choice! Please try again.");
        }
    }

    private void addDoctor() {
        System.out.println("\n=== Add a New Doctor ===");
        
        // Validate Doctor ID
        String id;
        do {
            System.out.print("Enter Doctor ID: ");
            id = scanner.nextLine().trim();
            if (id.isEmpty()) {
                System.out.println("Doctor ID cannot be empty.");
            }
        } while (id.isEmpty());
        
        // Validate Doctor Name
        String name;
        do {
            System.out.print("Enter Doctor Name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Doctor Name cannot be empty.");
            }
        } while (name.isEmpty());
        
        // Validate Specialization
        String specialization;
        do {
            System.out.print("Enter Doctor specialization: ");
            specialization = scanner.nextLine().trim();
            if (specialization.isEmpty()) {
                System.out.println("Specialization cannot be empty.");
            }
        } while (specialization.isEmpty());
        
        // Validate Contact Information
        String contactInfo;
        do {
            System.out.print("Enter Doctor contact information: ");
            contactInfo = scanner.nextLine().trim();
            if (contactInfo.isEmpty()) {
                System.out.println("Contact information cannot be empty.");
            }
        } while (contactInfo.isEmpty());
        
        try {
            // Using the Builder pattern to create a new Doctor
            Doctor doctor = new Doctor.Builder()
                .doctorId(id)
                .doctorName(name)
                .specialization(specialization)
                .contactInfo(contactInfo)
                .workSchedule(new Doctor.WorkSchedule())  // Initialize with empty schedule
                .build();
                
            doctorManager.addDoctor(doctor);
            System.out.println("Doctor added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding doctor: " + e.getMessage());
        }
    }

    private void updateDoctor() {
        System.out.println("\n=== Update Doctor Information ===");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.print("Enter your choice: ");
        
        try {
            int searchChoice = Integer.parseInt(scanner.nextLine().trim());
            Doctor existingDoctor = null;
            
            switch (searchChoice) {
                case 1 -> {
                    System.out.print("Enter Doctor ID: ");
                    String id = scanner.nextLine().trim();
                    existingDoctor = doctorManager.getDoctorById(id);
                }
                case 2 -> {
                    System.out.print("Enter Doctor Name: ");
                    String name = scanner.nextLine().trim();
                    existingDoctor = doctorManager.getDoctorByName(name);
                }
                default -> System.out.println("Invalid choice!");
            }
            
            if (existingDoctor == null) {
                System.out.println("Doctor not found.");
                return;
            }
    
            System.out.println("Enter new details (leave blank to keep current value):");
            
            System.out.print("Enter Contact Info (current: " + existingDoctor.getContactInfo() + "): ");
            String contactInfo = scanner.nextLine().trim();
            if (contactInfo.isEmpty()) {
                contactInfo = existingDoctor.getContactInfo();
            }
            
            System.out.print("Enter Specialization (current: " + existingDoctor.getSpecialization() + "): ");
            String specialization = scanner.nextLine().trim();
            if (specialization.isEmpty()) {
                specialization = existingDoctor.getSpecialization();
            }
            
            try {
                Doctor updatedDoctor = new Doctor.Builder()
                    .doctorId(existingDoctor.getDoctorId())
                    .doctorName(existingDoctor.getDoctorName())
                    .specialization(specialization)
                    .contactInfo(contactInfo)
                    .workSchedule(existingDoctor.getWorkSchedule())  // Keep existing work schedule
                    .build();
                    
                boolean updated = doctorManager.updateDoctor(updatedDoctor);
                
                if (updated) {
                    System.out.println("Doctor information updated successfully!");
                } else {
                    System.out.println("Failed to update doctor information.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error updating doctor: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
    
    
    private void viewDoctor() {
        System.out.println("\n=== View Doctor Information ===");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.print("Enter your choice: ");
        
        try {
            int searchChoice = Integer.parseInt(scanner.nextLine().trim());
            Doctor doctor = null;
            
            switch (searchChoice) {
                case 1 -> {
                    System.out.print("Enter Doctor ID: ");
                    String id = scanner.nextLine().trim();
                    doctor = doctorManager.getDoctorById(id);
                }
                case 2 -> {
                    System.out.print("Enter Doctor Name: ");
                    String name = scanner.nextLine().trim();
                    doctor = doctorManager.getDoctorByName(name);
                }
                default -> System.out.println("Invalid choice!");
            }
            
            if (doctor != null) {
                System.out.println("\n=== Doctor Details ===");
                System.out.println("Doctor ID: " + doctor.getDoctorId());
                System.out.println("Name: " + doctor.getDoctorName());
                System.out.println("Specialization: " + doctor.getSpecialization());
                System.out.println("Contact Info: " + doctor.getContactInfo());
            } else {
                System.out.println("Doctor not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private void deleteDoctor() {
        System.out.println("\n=== Delete a Doctor ===");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.print("Enter your choice: ");
        
        try {
            int searchChoice = Integer.parseInt(scanner.nextLine().trim());
            Doctor doctorToDelete = null;
            
            switch (searchChoice) {
                case 1 -> {
                    System.out.print("Enter Doctor ID: ");
                    String id = scanner.nextLine().trim();
                    doctorToDelete = doctorManager.getDoctorById(id);
                }
                case 2 -> {
                    System.out.print("Enter Doctor Name: ");
                    String name = scanner.nextLine().trim();
                    doctorToDelete = doctorManager.getDoctorByName(name);
                }
                default -> System.out.println("Invalid choice!");
            }
            
            if (doctorToDelete == null) {
                System.out.println("Doctor not found.");
                return;
            }
            
            // Show doctor details before confirmation
            System.out.println("\nDoctor to delete:");
            System.out.println("ID: " + doctorToDelete.getDoctorId());
            System.out.println("Name: " + doctorToDelete.getDoctorName());
            System.out.println("Specialization: " + doctorToDelete.getSpecialization());
            
            System.out.print("Are you sure you want to delete this doctor? (y/n): ");
            String confirmation = scanner.nextLine().trim();
            
            if (confirmation.equalsIgnoreCase("y")) {
                boolean deleted = doctorManager.deleteDoctor(doctorToDelete.getDoctorId());
                if (deleted) {
                    System.out.println("Doctor deleted successfully!");
                } else {
                    System.out.println("Failed to delete doctor.");
                }
            } else {
                System.out.println("Delete operation cancelled.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }}



