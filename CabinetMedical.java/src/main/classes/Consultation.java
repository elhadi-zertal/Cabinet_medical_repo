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
    private final LocalDateTime consultationDateTime;
    private ConsultationStatus status;
    private double consultationFee;

    // Enum for consultation status
    public enum ConsultationStatus {
        SCHEDULED, IN_PROGRESS, COMPLETED, CANCELLED
    }

    // Simple note class - could be moved to a separate file if needed
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
    public Consultation(Appointment appointment, MedicalRecord medicalRecord) {
        this.consultationId = generateConsultationId();
        this.appointment = appointment;
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
        addNote("Diagnosis provided: " + diagnosis, getDoctorName());
        medicalRecord.addDiagnosis(diagnosis);  // Update medical record
    }

    // Enhanced method to add treatment
    public void addTreatment(String treatment) {
        validateInput(treatment, "Treatment cannot be empty");
        this.treatments.add(treatment);
        addNote("Treatment added: " + treatment, getDoctorName());
        medicalRecord.addTreatment(treatment);  // Update medical record
    }

    // Method to add prescription using existing Prescription class
    public void addPrescription(Prescription prescription) {
        if (prescription == null) {
            throw new IllegalArgumentException("Prescription cannot be null");
        }
        this.prescriptions.add(prescription);
        addNote("New prescription added: " + prescription.toString(), getDoctorName());
        medicalRecord.addPrescription(prescription);  // Update medical record
    }

    // Method to update medical history
    public void updateMedicalHistory(String medicalHistoryEntry) {
        validateInput(medicalHistoryEntry, "Medical history entry cannot be empty");
        medicalRecord.addMedicalHistory(medicalHistoryEntry);
        addNote("Medical history updated: " + medicalHistoryEntry, getDoctorName());
    }

    // Method to log allergy
    public void logAllergy(String allergy) {
        validateInput(allergy, "Allergy cannot be empty");
        medicalRecord.addAllergy(allergy);
        addNote("New allergy recorded: " + allergy, getDoctorName());
    }

    // Method to add consultation notes
    public void addNote(String note, String authorName) {
        validateInput(note, "Note cannot be empty");
        validateInput(authorName, "Author name cannot be empty");
        this.notes.add(new ConsultationNote(note, authorName));
    }

    // Method to start consultation
    public void startConsultation() {
        if (status != ConsultationStatus.SCHEDULED) {
            throw new IllegalStateException("Consultation must be in SCHEDULED state to start");
        }
        status = ConsultationStatus.IN_PROGRESS;
        addNote("Consultation started", getDoctorName());
    }

    // Method to complete consultation
    public void completeConsultation() {
        if (status != ConsultationStatus.IN_PROGRESS) {
            throw new IllegalStateException("Consultation must be in IN_PROGRESS state to complete");
        }
        status = ConsultationStatus.COMPLETED;
        addNote("Consultation completed", getDoctorName());
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
    
    // Existing appointment-related methods
    public String getAppointmentDate() { return appointment.getDay() + "/" + appointment.getMonth() + "/" + appointment.getYear(); }
    public String getAppointmentTime() { return appointment.getHour() + ":00"; }
    public String getDoctorName() { return appointment.getDoctorName(); }
    public String getPatientName() { return appointment.getName(); }
    public String getPatientContactInfo() { return appointment.getContactInfo(); }
    public String getPatientId() { return appointment.getId(); }
}