package main.classes;

import java.util.Date;
import java.util.List;

public class MedicalRecord {
    private String recordId;          // Unique ID for the medical record
    private Patient patient;          // Reference to the patient the record belongs to
    private Doctor doctor;            // Reference to the doctor who created or updated the record
    private List<String> diagnoses;   // List of diagnoses made during consultations
    private List<String> treatments;  // List of treatments prescribed or administered
    private List<String> allergies;   // List of known allergies for the patient
    private List<String> medicalHistory;  // List of past medical conditions or surgeries
    private List<Prescription> prescriptions;  // List of prescriptions related to the patient
    private Date recordDate;          // Date when the record was created or updated
    private String additionalNotes;   // Any additional notes or comments regarding the patient’s condition

    // Constructor
    public MedicalRecord(String recordId, Patient patient, Doctor doctor, List<String> diagnoses,
                         List<String> treatments, List<String> allergies, List<String> medicalHistory,
                         List<Prescription> prescriptions, Date recordDate, String additionalNotes) {
        this.recordId = recordId;
        this.patient = patient;
        this.doctor = doctor;
        this.diagnoses = diagnoses;
        this.treatments = treatments;
        this.allergies = allergies;
        this.medicalHistory = medicalHistory;
        this.prescriptions = prescriptions;
        this.recordDate = recordDate;
        this.additionalNotes = additionalNotes;
    }

    // Getters and setters for each attribute
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<String> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<String> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public List<String> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<String> treatments) {
        this.treatments = treatments;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public List<String> getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(List<String> medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    // Method to add a new prescription to the medical record
    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    // Method to add a diagnosis to the medical record
    public void addDiagnosis(String diagnosis) {
        diagnoses.add(diagnosis);
    }

    // Method to add a treatment to the medical record
    public void addTreatment(String treatment) {
        treatments.add(treatment);
    }

    // Method to add a medical history item
    public void addMedicalHistory(String historyItem) {
        medicalHistory.add(historyItem);
    }

    // Method to add an allergy to the medical record
    public void addAllergy(String allergy) {
        allergies.add(allergy);
    }

    // Method to display the medical record details
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
                ", prescriptions=" + prescriptions +
                ", recordDate=" + recordDate +
                ", additionalNotes='" + additionalNotes + '\'' +
                '}';
    }
}

        





