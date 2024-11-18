package main.classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Consultation {

    private Patient patient; // Reference to the patient
    private LocalDateTime consultationDateTime; // Date and time of the consultation (from Appointment)
    private List<String> diagnoses; // List of diagnoses
    private List<String> treatmentPlans; // List of treatment plans
    private List<String> notes; // Optional notes from the doctor
    private Prescription prescription; // Associated prescription
    private String summary; // Summary of the consultation
    private String doctorName;

    // Constructor
    public Consultation(Patient patient, LocalDateTime consultationDateTime,String diagnoses ,String treatmentPlans,
                         String notes , Prescription prescription ,String summary , String doctorName) {
        this.patient = patient;
        this.consultationDateTime = consultationDateTime;
        this.diagnoses = new ArrayList<>();
        this.treatmentPlans = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.prescription = prescription ;
        this.summary = summary ;
        this.doctorName = doctorName;
    }

    // Getters and Setters
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getConsultationDateTime() {
        return consultationDateTime;
    }

    public void setConsultationDateTime(LocalDateTime consultationDateTime) {
        this.consultationDateTime = consultationDateTime;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public List<String> getDiagnoses() {
        return diagnoses;
    }

    public List<String> getTreatmentPlans() {
        return treatmentPlans;
    }

    public List<String> getNotes() {
        return notes;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    // Methods to add details to the consultation
    public void addDiagnosis(String diagnosis) {
        if (diagnosis != null && !diagnosis.trim().isEmpty()) {
            diagnoses.add(diagnosis);
        } else {
            throw new IllegalArgumentException("Diagnosis cannot be null or empty.");
        }
    }

    public void addTreatmentPlan(String treatmentPlan) {
        if (treatmentPlan != null && !treatmentPlan.trim().isEmpty()) {
            treatmentPlans.add(treatmentPlan);
        } else {
            throw new IllegalArgumentException("Treatment plan cannot be null or empty.");
        }
    }

    public void addNote(String note) {
        if (note != null && !note.trim().isEmpty()) {
            notes.add(note);
        } else {
            throw new IllegalArgumentException("Note cannot be null or empty.");
        }
    }

    public void generatePrescription(Patient patient, List<Medication> medications, LocalDateTime issueDate, LocalDateTime expirationDate) {
        if (medications == null || medications.isEmpty()) {
            throw new IllegalArgumentException("Medications list cannot be empty.");
        }
        this.prescription = new Prescription(
                "RX-" + System.currentTimeMillis(),
                patient,
                medications,
                issueDate.toLocalDate(),
                expirationDate.toLocalDate(),
                this.doctorName,
                new ArrayList<>()
        );
    }

    public void generateSummary() {
        StringBuilder summaryBuilder = new StringBuilder();
        summaryBuilder.append("Consultation Summary\n")
                .append("Date: ").append(consultationDateTime).append("\n")
                .append("Doctor: ").append(doctorName).append("\n")
                .append("Patient: ").append(patient.getName()).append("\n")
                .append("\nDiagnoses:\n");

        for (String diagnosis : diagnoses) {
            summaryBuilder.append("- ").append(diagnosis).append("\n");
        }

        summaryBuilder.append("\nTreatment Plans:\n");
        for (String plan : treatmentPlans) {
            summaryBuilder.append("- ").append(plan).append("\n");
        }

        if (prescription != null) {
            summaryBuilder.append("\nPrescription:\n").append(prescription.toString()).append("\n");
        }

        summaryBuilder.append("\nDoctor's Notes:\n");
        for (String note : notes) {
            summaryBuilder.append("- ").append(note).append("\n");
        }

        this.summary = summaryBuilder.toString();
    }

    @Override
    public String toString() {
        return summary != null ? summary : "Consultation details not finalized yet.";
    }
}
    

