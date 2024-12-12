package main.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class PatientMenu {

    PatientManager patientManager = new PatientManager();


  // This method will display the menu and handle user input
  @SuppressWarnings("ConvertToTryWithResources")
  Scanner scanner = new Scanner(System.in);
  public  void displayMenu() {
      int choice;

      do {
          // Display the menu options
          System.out.println("\n=== Patient Management Menu ===");
          System.out.println("1. Add a Patient");
          System.out.println("2. Update Patient Information");
          System.out.println("3. View Patient Records");
          System.out.println("4. Delete a Patient");
          System.out.println("5. Back to Main Menu");
          System.out.print("Enter your choice: ");
          // Get the user's choice
          choice = scanner.nextInt();
          scanner.nextLine(); // Consume the newline character

          // Perform an action based on the user's choice
          switch (choice) {
              case 1 -> addPatient();
              case 2 -> updatePatient();
              case 3 -> viewPatient();
              case 4 -> deletePatient();
              case 5 -> System.out.println("Returning to the main menu...");
              default -> System.out.println("Invalid choice! Please try again.");
          }
      } while (choice != 5); // Keep showing the menu until the user selects option 5

       
  
    }
  
 private void addPatient() {
    System.out.println("\n=== Add a New Patient ===");
    // Basic information
    System.out.print("Enter Patient ID: ");
    String id = scanner.nextLine();
    System.out.print("Enter Patient Name: ");
    String name = scanner.nextLine();
    System.out.print("Enter Patient Age: ");
    int age = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    System.out.print("Enter Patient Gender (M/F): ");
    String gender = scanner.nextLine();
    System.out.print("Enter Patient Height (in cm): ");
    double height = scanner.nextDouble();
    System.out.print("Enter Patient Weight (in kg): ");
    double weight = scanner.nextDouble();
    scanner.nextLine(); // Consume newline

    // Contact and address
    System.out.print("Enter Address: ");
    String address = scanner.nextLine();
    System.out.print("Enter Contact Info: ");
    String contactInfo = scanner.nextLine();

    // Appointment dates
    System.out.print("Enter Last Appointment Date (e.g., YYYY-MM-DD): ");
    String lastAppointmentDate = scanner.nextLine();
    System.out.print("Enter Next Appointment Date (e.g., YYYY-MM-DD): ");
    String nextAppointmentDate = scanner.nextLine();

    // Medical details
     System.out.print("Enter Medical History (comma-separated, or leave blank): ");
    String medicalHistoryInput = scanner.nextLine();
    List<String> medicalHistory = medicalHistoryInput.isEmpty() 
        ? new ArrayList<>() 
        : List.of(medicalHistoryInput.split(","));

    System.out.print("Enter Current Medications (comma-separated, or leave blank): ");
    String medicationsInput = scanner.nextLine();
    List<String> currentMedications = medicationsInput.isEmpty() 
        ? new ArrayList<>() 
        : List.of(medicationsInput.split(","));

    System.out.print("Enter Allergies (comma-separated, or leave blank): ");
    String allergiesInput = scanner.nextLine();
    List<String> allergies = allergiesInput.isEmpty() 
        ? new ArrayList<>() 
        : List.of(allergiesInput.split(","));

    // Other details
    System.out.print("Enter Blood Type: ");
    String bloodType = scanner.nextLine();
    System.out.print("Enter Doctor Name: ");
    String doctorName = scanner.nextLine();

    // Create a Patient object
    Patient patient = new Patient(
        id, name, age, gender, height, weight, address, contactInfo,
        lastAppointmentDate, nextAppointmentDate, medicalHistory,
        currentMedications, allergies, bloodType, doctorName
    );

    // Add patient 
    patientManager.addPatient(patient);
    System.out.println("Patient added successfully!");
}




private void updatePatient() {
    System.out.println("\n=== Update Patient Information ===");

    // Ask for the patient's ID to identify the patient to update
    System.out.print("Enter Patient ID: ");
    String id = scanner.nextLine();

    // Ask for the new details to update (some can be left blank)
    System.out.println("Enter new details (leave blank to keep current value):");

    // Collect information to update
    System.out.print("Enter Address (leave blank to keep current): ");
    String address = scanner.nextLine();
    
    System.out.print("Enter Contact Info (leave blank to keep current): ");
    String contactInfo = scanner.nextLine();

    System.out.print("Enter Last Appointment Date (e.g., YYYY-MM-DD, leave blank to keep current): ");
    String lastAppointmentDate = scanner.nextLine();

    System.out.print("Enter Next Appointment Date (e.g., YYYY-MM-DD, leave blank to keep current): ");
    String nextAppointmentDate = scanner.nextLine();

    // Medical details
    System.out.print("Enter Medical History (comma-separated, or leave blank to keep current): ");
    String medicalHistoryInput = scanner.nextLine();
    List<String> medicalHistory = medicalHistoryInput.isEmpty() 
        ? null 
        : List.of(medicalHistoryInput.split(","));

    System.out.print("Enter Current Medications (comma-separated, or leave blank to keep current): ");
    String medicationsInput = scanner.nextLine();
    List<String> currentMedications = medicationsInput.isEmpty() 
        ? null 
        : List.of(medicationsInput.split(","));

    System.out.print("Enter Allergies (comma-separated, or leave blank to keep current): ");
    String allergiesInput = scanner.nextLine();
    List<String> allergies = allergiesInput.isEmpty() 
        ? null 
        : List.of(allergiesInput.split(","));

    // Call the PatientManager's update method
    boolean updated = patientManager.updatePatient(id, address, contactInfo, lastAppointmentDate, 
            nextAppointmentDate, medicalHistory, currentMedications, allergies);

    // Provide feedback
    if (updated) {
        System.out.println("Patient information updated successfully!");
    } else {
        System.out.println("Patient with ID " + id + " not found.");
    }
}


private void viewPatient() {
    System.out.println("\n=== View Patient Information ===");

    // Ask for the patient's ID to identify the patient to view
    System.out.print("Enter Patient ID: ");
    String id = scanner.nextLine();

    // Call the PatientManager's method to retrieve the patient
    Patient patient = patientManager.getPatientById(id);

    // Display the patient details if found
    if (patient != null) {
        System.out.println("\n=== Patient Details ===");
        System.out.println("Patient ID: " + patient.getId());
        System.out.println("Name: " + patient.getName());
        System.out.println("Age: " + patient.getAge());
        System.out.println("Gender: " + patient.getGender());
        System.out.println("Height: " + patient.getHeight() + " cm");
        System.out.println("Weight: " + patient.getWeight() + " kg");
        System.out.println("Address: " + patient.getAddress());
        System.out.println("Contact Info: " + patient.getContactInfo());
        System.out.println("Last Appointment Date: " + patient.getLastAppointmentDate());
        System.out.println("Next Appointment Date: " + patient.getNextAppointmentDate());
        System.out.println("Medical History: " + String.join(", ", patient.getMedicalHistory()));
        System.out.println("Current Medications: " + String.join(", ", patient.getCurrentMedications()));
        System.out.println("Allergies: " + String.join(", ", patient.getAllergies()));
        System.out.println("Blood Type: " + patient.getBloodType());
        System.out.println("Doctor Name: " + patient.getDoctorName());
    } else {
        System.out.println("Patient with ID " + id + " not found.");
    }
}



private void deletePatient() {
    System.out.println("\n=== Delete a Patient ===");
    // Ask for the patient ID
    System.out.print("Enter Patient ID to delete: ");
    String id = scanner.nextLine();

    // Call the PatientManager to delete the patient
    patientManager.deletePatient(id);

    // Message indicating the action is complete (this assumes `deletePatient` in `PatientManager` prints a message)
    // If your `deletePatient` method in `PatientManager` already prints messages like "Patient removed" or "Patient not found," 
    // you can skip printing them here.
}













}


