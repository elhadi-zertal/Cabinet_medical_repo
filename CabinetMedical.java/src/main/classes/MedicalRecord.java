package main.classes;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class MedicalRecord {
    private String recordId;          // Unique ID for the medical record
    private Patient patient;          // Reference to the patient the record belongs to
    private Doctor doctor;            // Reference to the doctor who created or updated the record
    private List<String> diagnoses;   // List of diagnoses made during consultations
    private List<String> treatments;  // List of treatments prescribed or administered
    private List<String> allergies;   // List of known allergies for the patient
    private List<String> medicalHistory;  // List of past medical conditions
    private List<String> surgicalHistory; // List of past surgeries and operations
    private List<Prescription> prescriptions;  // List of prescriptions related to the patient
    private Date recordDate;          // Date when the record was created or updated
    private String additionalNotes;   // Any additional notes or comments regarding the patient's condition

    // Constructor
    public MedicalRecord(String recordId, Patient patient, Doctor doctor, List<String> diagnoses,
            List<String> treatments, List<String> allergies, List<String> medicalHistory,
            List<String> surgicalHistory, List<Prescription> prescriptions, String additionalNotes) {
        this.recordId = recordId;
        this.patient = patient;
        this.doctor = doctor;
        this.diagnoses = diagnoses != null ? diagnoses : new ArrayList<>();
        this.treatments = treatments != null ? treatments : new ArrayList<>();
        this.allergies = allergies != null ? allergies : new ArrayList<>();
        this.medicalHistory = medicalHistory != null ? medicalHistory : new ArrayList<>();
        this.surgicalHistory = surgicalHistory != null ? surgicalHistory : new ArrayList<>();
        this.prescriptions = prescriptions != null ? prescriptions : new ArrayList<>();
        this.additionalNotes = additionalNotes;
        this.recordDate = new Date();
    }

    // Method to add a prescription to the medical record
    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
        recordDate = new Date();
    }

    // Method to add a diagnosis to the medical record
    public void addDiagnosis(String diagnosis) {
        diagnoses.add(diagnosis);
        recordDate = new Date();
    }

    // Method to add a treatment to the medical record
    public void addTreatment(String treatment) {
        treatments.add(treatment);
        recordDate = new Date();
    }

    // Method to add a medical history item
    public void addMedicalHistory(String historyItem) {
        medicalHistory.add(historyItem);
        recordDate = new Date();
    }

    // Method to add surgical history
    public void addSurgicalHistory(String surgeryDetails) {
        surgicalHistory.add(surgeryDetails);
        recordDate = new Date();
    }

    // Method to add an allergy to the medical record
    public void addAllergy(String allergy) {
        allergies.add(allergy);
        recordDate = new Date();
    }

    // Method to track patient vital signs
    public void updateVitalSigns(double weight, double height, String bloodPressure, 
            double temperature, String additionalMetrics) {
        StringBuilder vitalInfo = new StringBuilder();
        vitalInfo.append("Vital Signs Update:\n")
                .append("Weight: ").append(weight).append(" kg\n")
                .append("Height: ").append(height).append(" cm\n")
                .append("Blood Pressure: ").append(bloodPressure).append("\n")
                .append("Temperature: ").append(temperature).append("°C");
        
        if (additionalMetrics != null && !additionalMetrics.isEmpty()) {
            vitalInfo.append("\nAdditional Metrics: ").append(additionalMetrics);
        }
        
        additionalNotes += "\n" + vitalInfo.toString();
        recordDate = new Date();
    }

    // Standard getters and setters
    public String getRecordId() { return recordId; }
    public void setRecordId(String recordId) { this.recordId = recordId; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public List<String> getDiagnoses() { return diagnoses; }
    public void setDiagnoses(List<String> diagnoses) { this.diagnoses = diagnoses; }
    public List<String> getTreatments() { return treatments; }
    public void setTreatments(List<String> treatments) { this.treatments = treatments; }
    public List<String> getAllergies() { return allergies; }
    public void setAllergies(List<String> allergies) { this.allergies = allergies; }
    public List<String> getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(List<String> medicalHistory) { this.medicalHistory = medicalHistory; }
    public List<String> getSurgicalHistory() { return surgicalHistory; }
    public void setSurgicalHistory(List<String> surgicalHistory) { this.surgicalHistory = surgicalHistory; }
    public List<Prescription> getPrescriptions() { return prescriptions; }
    public void setPrescriptions(List<Prescription> prescriptions) { this.prescriptions = prescriptions; }
    public Date getRecordDate() { return recordDate; }
    public void setRecordDate(Date recordDate) { this.recordDate = recordDate; }
    public String getAdditionalNotes() { return additionalNotes; }
    public void setAdditionalNotes(String additionalNotes) { this.additionalNotes = additionalNotes; }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "recordId='" + recordId + '\'' +
                ", patient=" + patient.getName() + " (" + patient.getId() + ")" +
                ", doctor=" + doctor.getDoctorName() + " (" + doctor.getSpecialization() + ")" +
                ", diagnoses=" + diagnoses +
                ", treatments=" + treatments +
                ", allergies=" + allergies +
                ", medicalHistory=" + medicalHistory +
                ", surgicalHistory=" + surgicalHistory +
                ", prescriptions=" + prescriptions +
                ", recordDate=" + recordDate +
                ", additionalNotes='" + additionalNotes + '\'' +
                '}';
    }
}
