

package main.classes;

import java.util.ArrayList;
import java.util.List;

public class PatientManager {

    @SuppressWarnings("FieldMayBeFinal")
    private static List<Patient> patients = new ArrayList<>();
    // A list to store patients and their information

    // Add a new patient to the list
    public void addPatient(Patient patient) {
      if (patient == null) {
          throw new IllegalArgumentException("Patient cannot be null");
      }
      patients.add(patient);
  }
  

    // Retrieve a patient by their ID
    public static Patient getPatientById(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) { // Compare the IDs
                return patient; // Return the patient if the ID matches
            }
        }
        return null; // Return null if no patient is found
    }
    

    // Update a patient's information by their ID
    public boolean updatePatient(String id, String address, String contactInfo, String lastAppointmentDate,
            String nextAppointmentDate, List<String> medicalHistory,
            List<String> currentMedications, List<String> allergies) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                // Update only if new data is provided
                if (address != null) {
                    patient.setAddress(address);
                }
                if (contactInfo != null) {
                    patient.setContactInfo(contactInfo);
                }
                if (lastAppointmentDate != null) {
                    patient.setLastAppointmentDate(lastAppointmentDate);
                }
                if (nextAppointmentDate != null) {
                    patient.setNextAppointmentDate(nextAppointmentDate);
                }
                if (medicalHistory != null) {
                    patient.setMedicalHistory(medicalHistory);
                }
                if (currentMedications != null) {
                    patient.setCurrentMedications(currentMedications);
                }
                if (allergies != null) {
                    patient.setAllergies(allergies);
                }
                return true; // Update was successful
            }
        }
        return false; // Patient not found
    }

    // Delete a patient by their ID
    public void deletePatient(String id) {
        boolean removed = patients.removeIf(patient -> patient.getId().equals(id)); // Remove the patient if ID matches
        if (removed) {
            System.out.println("Patient removed");
        } else {
            System.out.println("Patient not found");
        }
    }

        // Print all patients
    public static void listAllPatients() {
        if (patients.isEmpty()) {
          System.out.println("No patients available.");
          return;
        }
     System.out.println("List of patients:");
        for (Patient patient : patients) {
          System.out.println(patient); // Assumes Patient has a toString() method
        }
    }


    
}
