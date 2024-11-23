package main.classes;

import java.util.List;

public class Consultation { 
    private Patient patient; // The patient involved in the consultation
    private Appointment appointment; // The appointment for the consultation
    private String doctorName; // The name of the doctor conducting the consultation
    private String description; // The description of the consultation
    private List<String> treatments; // List of treatments given during the consultation
    private List<String> prescriptions; // List of prescriptions provided during the consultation
    private String doctorNotes; // Notes made by the doctor during the consultation
    private List<String> medications; // List of medications prescribed during the consultation

    /**
     * Constructor 
     * @param patient Patient associated with the consultation
     * @param appointment Appointment associated with the consultation
     * @param doctorName Name of the doctor
     * @param description A description of the consultation
     * @param treatments Treatments given during the consultation
     * @param prescriptions Prescriptions given during the consultation
     * @param medications Medications prescribed during the consultation
     * @throws IllegalArgumentException if any of the required parameters are invalid
     */

    public Consultation(Patient patient, Appointment appointment, String doctorName, String description, 
                        List<String> treatments, List<String> prescriptions, List<String> medications) {
        // Ensure all required fields are valid
        if (patient == null || appointment == null || doctorName == null || doctorName.isEmpty()) {
            throw new IllegalArgumentException("Patient, appointment, and doctor name cannot be null or empty.");
        }
        
        this.patient = patient;
        this.appointment = appointment;
        this.doctorName = doctorName;
        this.description = description;
        this.treatments = treatments != null ? treatments : List.of(); // Ensure no null list
        this.prescriptions = prescriptions != null ? prescriptions : List.of(); // Ensure no null list
        this.medications = medications != null ? medications : List.of(); // Ensure no null list
        this.doctorNotes = "";
    }

    // Getters and Setters with exception handling for invalid inputs

    public void setDoctorName(String doctorName) {
        if (doctorName == null || doctorName.isEmpty()) {
            throw new IllegalArgumentException("Doctor name cannot be null or empty.");
        }
        this.doctorName = doctorName;
    }

    public void setMedications(List<String> medications) {
        if (medications == null) {
            throw new IllegalArgumentException("Medications list cannot be null.");
        }
        this.medications = medications;
    }

    public void setTreatments(List<String> treatments) {
        if (treatments == null) {
            throw new IllegalArgumentException("Treatments list cannot be null.");
        }
        this.treatments = treatments;
    }

    public void setPrescriptions(List<String> prescriptions) {
        if (prescriptions == null) {
            throw new IllegalArgumentException("Prescriptions list cannot be null.");
        }
        this.prescriptions = prescriptions;
    }

    public void setDoctorNotes(String doctorNotes) {
        if (doctorNotes == null || doctorNotes.isEmpty()) {
            throw new IllegalArgumentException("Doctor notes cannot be null or empty.");
        }
        this.doctorNotes = doctorNotes;
    }

    /**
     * Method to schedule a follow-up appointment
     * @param followUpAppointment The follow-up appointment to schedule
     * @return The follow-up appointment
     * @throws IllegalArgumentException if the follow-up appointment is scheduled in the past
     */

    public Appointment scheduleFollowUp(Appointment followUpAppointment) {
        if (followUpAppointment == null) {
            throw new IllegalArgumentException("Follow-up appointment cannot be null.");
        }
        
        // Check if the follow-up appointment is in the past
        if (followUpAppointment.getYear() < appointment.getYear() || 
            (followUpAppointment.getYear() == appointment.getYear() && followUpAppointment.getMonth() < appointment.getMonth())) {
            throw new IllegalArgumentException("Follow-up appointment cannot be scheduled in the past.");
        }
        return followUpAppointment;
    }

    /**
     * Method to check if there are conflicts in prescribed medications
     * @param prescribedMedications The list of medications prescribed during the consultation
     * @return true if there are conflicts with the patient's current medications, false otherwise
     * @throws IllegalArgumentException if the prescribed medications list is null
     */

    public boolean checkMedicationConflicts(List<String> prescribedMedications) {
        if (prescribedMedications == null) {
            throw new IllegalArgumentException("Prescribed medications list cannot be null.");
        }
        
        // Check if any prescribed medication conflicts with current medications
        for (String prescription : prescribedMedications) {
            if (patient.getCurrentMedications().contains(prescription)) {
                System.out.println("Conflict: " + prescription + " is already in patient's current medications.");
                return true;
            }
        }
        return false;
    }

    /**
     * Method to update a patient's medical records with new information
     * @param newMedicalHistory New medical history to update
     * @param newAllergies New allergies to update
     * @throws IllegalArgumentException if medical history or allergies lists are null
     */

    public void updatePatientRecords(List<String> newMedicalHistory, List<String> newAllergies) {
        if (newMedicalHistory == null || newAllergies == null) {
            throw new IllegalArgumentException("Medical history and allergies lists cannot be null.");
        }
        
        // Update the patient's medical history and allergies
        patient.setMedicalHistory(newMedicalHistory);
        patient.setAllergies(newAllergies);
    }

    /**
     * Generates a detailed consultation report
     * @return A string representing the consultation report
     * @throws IllegalStateException if any essential information is missing
     */

    public String generateConsultationReport() {
        if (patient == null || appointment == null || doctorName == null) {
            throw new IllegalStateException("Consultation cannot be generated without valid patient, appointment, and doctor.");
        }
        
        // Generate the consultation report
        return "Consultation Report for " + patient.getName() + " on " + appointment.getDay() + "/" + 
               appointment.getMonth() + "/" + appointment.getYear() + " at " + appointment.getHour() + ":00\n" +
               "Doctor: " + doctorName + "\n" +
               "Description: " + description + "\n" +
               "Treatments: " + treatments + "\n" +
               "Prescriptions: " + prescriptions + "\n" +
               "Medications: " + medications + "\n" +
               "Doctor's Notes: " + doctorNotes;
    }

    /**
     * Sends the consultation summary to the patient
     * @throws IllegalStateException if patient or doctor information is missing
     */
    
    public void sendConsultationSummaryToPatient() {
        if (patient == null || doctorName == null) {
            throw new IllegalStateException("Cannot send consultation summary without valid patient and doctor information.");
        }
        
        // Generate and send the consultation summary to the patient
        String summary = "Consultation Summary for " + patient.getName() + " on " + appointment.getDay() + "/" + 
                          appointment.getMonth() + "/" + appointment.getYear() + " at " + appointment.getHour() + ":00\n" +
                          "Doctor: " + doctorName + "\n" +
                          "Description: " + description + "\n" +
                          "Treatments: " + treatments + "\n" +
                          "Prescriptions: " + prescriptions + "\n" +
                          "Medications: " + medications;  // Include medications in the summary
        System.out.println("Summary sent to patient: \n" + summary);
    }

    /**
     * Validates the appointment date to ensure it's in the future
     * @throws IllegalArgumentException if the appointment date is in the past
     */
    public void validateAppointmentDate() {
        // Check if the appointment is in the future
        if (appointment.getYear() < 2024 || 
            (appointment.getYear() == 2024 && appointment.getMonth() < 11) || 
            (appointment.getYear() == 2024 && appointment.getMonth() == 11 && appointment.getDay() < 21)) {
            throw new IllegalArgumentException("Appointment cannot be scheduled in the past.");
        }
    }
}
