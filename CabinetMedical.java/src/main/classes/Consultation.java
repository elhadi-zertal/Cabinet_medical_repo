package main.classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Consultation {
    private final String consultationId;
    private String diagnosis;
    private List<String> treatments;
    private List<Prescription> prescriptions;  
    private MedicalRecord medicalRecord;            
    private String followUp;
    private List<ConsultationNote> notes;
    private final Appointment appointment;
    private final Doctor doctor;
    private final Patient patient;
    private final LocalDateTime consultationDateTime;
    private ConsultationStatus status;
    private double consultationFee;

    // Enum for consultation status
    public enum ConsultationStatus {
        SCHEDULED, IN_PROGRESS, COMPLETED, CANCELLED
    }

    // Simple note class 
    public static class ConsultationNote {
        private final String note;
        private final LocalDateTime timestamp;
        private final String authorName;

        public ConsultationNote(String note, String authorName) {
            this.note = note;
            this.timestamp = LocalDateTime.now();
            this.authorName = authorName;
        }

        // Getters
        public String getNote() { return note; }
        public LocalDateTime getTimestamp() { return timestamp; }
        public String getAuthorName() { return authorName; }
    }

    // Constructor
    public Consultation(Appointment appointment, MedicalRecord medicalRecord , Doctor doctor, Patient patient) {
        this.consultationId = generateConsultationId();
        this.appointment = appointment;
        this.doctor = doctor;
        this.patient = patient;
        this.medicalRecord = medicalRecord;
        this.consultationDateTime = LocalDateTime.now();
        this.status = ConsultationStatus.SCHEDULED;
        
        // Initialize collections
        this.treatments = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
        this.notes = new ArrayList<>();
    }

    // Generate unique consultation ID
    private String generateConsultationId() {
        return "CONS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Enhanced method to provide diagnosis
    public void provideDiagnosis(String diagnosis) {
        validateInput(diagnosis, "Diagnosis cannot be empty");
        this.diagnosis = diagnosis;
        addNote("Diagnosis provided: " + diagnosis);
        medicalRecord.addDiagnosis(diagnosis);  // Update medical record
    }

    // Enhanced method to add treatment
    public void addTreatment(String treatment) {
        validateInput(treatment, "Treatment cannot be empty");
        this.treatments.add(treatment);
        addNote("Treatment added: " + treatment);
        medicalRecord.addTreatment(treatment);  // Update medical record
    }

    // Method to add prescription using existing Prescription class
public void addPrescription(Prescription prescription) {
    if (prescription == null) {
        throw new IllegalArgumentException("Prescription cannot be null");
    }
    
    // Assuming prescriptions is a List<Prescription>
    this.prescriptions.add(prescription);
    
    // Add a note about the new prescription
    addNote("New prescription added: " + prescription.toString());
    
    // Update the medical record with the prescription
    if (medicalRecord != null) {
        medicalRecord.addPrescription(prescription);
    }
}


    // Method to update medical history
    public void updateMedicalHistory(String medicalHistoryEntry) {
        validateInput(medicalHistoryEntry, "Medical history entry cannot be empty");
        medicalRecord.addMedicalHistory(medicalHistoryEntry);
        addNote("Medical history updated: " + medicalHistoryEntry);
    }

    // Method to log allergy
    public void logAllergy(String allergy) {
        validateInput(allergy, "Allergy cannot be empty");
        medicalRecord.addAllergy(allergy);
        addNote("New allergy recorded: " + allergy);
    }


// Method to add consultation notes
    public void addNote(String note) {
        validateInput(note, "Note cannot be empty");
        validateInput( toString() ,"Author name cannot be empty");
        this.notes.add(new ConsultationNote(note, toString()));
    }
    
        // Method to check if consultation is started
        public boolean isStarted() {
            return status == ConsultationStatus.IN_PROGRESS;
        }

    // Method to start consultation
    public void startConsultation() {
        if (status != ConsultationStatus.SCHEDULED) {
            throw new IllegalStateException("Consultation must be in SCHEDULED state to start");
        }
        status = ConsultationStatus.IN_PROGRESS;
        addNote("Consultation started");
    }

    // Method to complete consultation
    public void completeConsultation() {
        if (status != ConsultationStatus.IN_PROGRESS) {
            throw new IllegalStateException("Consultation must be in IN_PROGRESS state to complete");
        }
        status = ConsultationStatus.COMPLETED;
        addNote("Consultation completed");
    }

    // Input validation method
    private void validateInput(String input, String errorMessage) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    // Method to get consultation summary
    public String getConsultationSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Consultation Summary\n");
        summary.append("===================\n");
        summary.append("ID: ").append(consultationId).append("\n");
        summary.append("Date: ").append(consultationDateTime).append("\n");
        summary.append("Doctor: ").append(getDoctorName()).append("\n");
        summary.append("Patient: ").append(getPatientName()).append("\n");
        summary.append("Status: ").append(status).append("\n");
        summary.append("Diagnosis: ").append(diagnosis).append("\n");
        // Add more summary information as needed
        return summary.toString();
    }

    // Getters
    public String getConsultationId() { return consultationId; }
    public String getDiagnosis() { return diagnosis; }
    public List<String> getTreatments() { return new ArrayList<>(treatments); }
    public List<Prescription> getPrescriptions() { return new ArrayList<>(prescriptions); }
    public MedicalRecord getMedicalRecord() { return medicalRecord; }
    public String getFollowUp() { return followUp; }
    public List<ConsultationNote> getNotes() { return new ArrayList<>(notes); }
    public Appointment getAppointment() { return appointment; }
    public ConsultationStatus getStatus() { return status; }
    public LocalDateTime getConsultationDateTime() { return consultationDateTime; }
    
       // Appointment-related methods
    public String getAppointmentDate() { 
        return appointment.getDay() + "/" + appointment.getMonth() + "/" + appointment.getYear(); 
    }

    public String getAppointmentTime() { 
        return appointment.getHour() + ":00"; 
    }

    // Methods using Doctor class
    public String getDoctorName() { 
        return doctor.getDoctorName();
    }

    public String getDoctorSpecialization() {
        return doctor.getSpecialization();
    }

    // Methods using Patient class
    public String getPatientName() { 
        return patient.getName();
    }

    public String getPatientContactInfo() { 
        return patient.getContactInfo();
    }

    public String getPatientId() { 
        return patient.getId();
    }

    public int getPatientAge() {
        return patient.getAge();
    }

    public String getPatientAddress() {
        return patient.getAddress();
    }

    public String getAppointmentId() {
        return appointment.getAppointmentId();
    }

    }