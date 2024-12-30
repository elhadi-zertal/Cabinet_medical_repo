package main.classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MedicalRecord {
    private final String recordId;
    private final Patient patient;
    private final Doctor doctor;
    private final List<String> diagnoses;
    private final List<String> treatments;
    private final List<String> allergies;
    private final List<String> medicalHistory;
    private final List<String> surgicalHistory;
    private final List<Prescription> prescriptions;
    private String additionalNotes;
    private final LocalDateTime recordDate;
    private VitalSigns vitalSigns;

    // Updated constructor to include vital signs parameters
    public MedicalRecord(String recordId, Patient patient, Doctor doctor,
                         List<String> diagnoses, List<String> treatments,
                         List<String> allergies, List<String> medicalHistory,
                         List<String> surgicalHistory, List<Prescription> prescriptions,
                         String additionalNotes, double weight, double height, 
                         String bloodPressure, double temperature, String additionalMetrics) {
        this.recordId = Objects.requireNonNull(recordId, "Record ID cannot be null");
        this.patient = Objects.requireNonNull(patient, "Patient cannot be null");
        this.doctor = Objects.requireNonNull(doctor, "Doctor cannot be null");
        this.diagnoses = new ArrayList<>(diagnoses != null ? diagnoses : new ArrayList<>());
        this.treatments = new ArrayList<>(treatments != null ? treatments : new ArrayList<>());
        this.allergies = new ArrayList<>(allergies != null ? allergies : new ArrayList<>());
        this.medicalHistory = new ArrayList<>(medicalHistory != null ? medicalHistory : new ArrayList<>());
        this.surgicalHistory = new ArrayList<>(surgicalHistory != null ? surgicalHistory : new ArrayList<>());
        this.prescriptions = new ArrayList<>(prescriptions != null ? prescriptions : new ArrayList<>());
        this.additionalNotes = additionalNotes != null ? additionalNotes : "";
        this.recordDate = LocalDateTime.now();
        this.vitalSigns = new VitalSigns(weight, height, bloodPressure, temperature, additionalMetrics);
    }

    // Getters
    public String getRecordId() {
        return recordId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public List<String> getDiagnoses() {
        return new ArrayList<>(diagnoses);
    }

    public List<String> getTreatments() {
        return new ArrayList<>(treatments);
    }

    public List<String> getAllergies() {
        return new ArrayList<>(allergies);
    }

    public List<String> getMedicalHistory() {
        return new ArrayList<>(medicalHistory);
    }

    public List<String> getSurgicalHistory() {
        return new ArrayList<>(surgicalHistory);
    }

    public List<Prescription> getPrescriptions() {
        return new ArrayList<>(prescriptions);
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public String getRecordDate() {
        return recordDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    // Add methods
    public void addDiagnosis(String diagnosis) {
        if (diagnosis != null && !diagnosis.trim().isEmpty()) {
            diagnoses.add(diagnosis.trim());
        }
    }

    public void addTreatment(String treatment) {
        if (treatment != null && !treatment.trim().isEmpty()) {
            treatments.add(treatment.trim());
        }
    }

    public void addAllergy(String allergy) {
        if (allergy != null && !allergy.trim().isEmpty()) {
            allergies.add(allergy.trim());
        }
    }

    public void addMedicalHistory(String historyItem) {
        if (historyItem != null && !historyItem.trim().isEmpty()) {
            medicalHistory.add(historyItem.trim());
        }
    }

    public void addSurgicalHistory(String surgeryItem) {
        if (surgeryItem != null && !surgeryItem.trim().isEmpty()) {
            surgicalHistory.add(surgeryItem.trim());
        }
    }

    public void addPrescription(Prescription prescription) {
        if (prescription != null) {
            prescriptions.add(prescription);
        }
    }

    public void setAdditionalNotes(String notes) {
        this.additionalNotes = notes != null ? notes : "";
    }

    public void updateVitalSigns(double weight, double height, String bloodPressure, 
                                  double temperature, String additionalMetrics) {
        this.vitalSigns = new VitalSigns(weight, height, bloodPressure, temperature, additionalMetrics);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Medical Record ID: ").append(recordId)
          .append("\nDate: ").append(getRecordDate())
          .append("\nPatient: ").append(patient.getName())
          .append("\nDoctor: ").append(doctor.getDoctorName())
          .append("\n\nDiagnoses: ").append(String.join(", ", diagnoses))
          .append("\nTreatments: ").append(String.join(", ", treatments))
          .append("\nAllergies: ").append(String.join(", ", allergies))
          .append("\nMedical History: ").append(String.join(", ", medicalHistory))
          .append("\nSurgical History: ").append(String.join(", ", surgicalHistory))
          .append("\n\nVital Signs: ").append(vitalSigns)
          .append("\n\nAdditional Notes: ").append(additionalNotes);
        
        if (!prescriptions.isEmpty()) {
            sb.append("\n\nPrescriptions:");
            prescriptions.forEach(prescription -> 
                sb.append("\n- ").append(prescription));
        }
        
        return sb.toString();
    }

    // Inner class for Vital Signs
    public class VitalSigns {
        private double weight; // Weight in kilograms
        private double height; // Height in meters
        private String bloodPressure; // Blood pressure as a String (e.g., "120/80")
        private double temperature; // Temperature in Celsius
        private String additionalMetrics; // Additional metrics as a String

        // Constructor with parameters
        public VitalSigns(double weight, double height, String bloodPressure, double temperature, String additionalMetrics) {
            this.weight = weight;
            this.height = height;
            this.bloodPressure = bloodPressure;
            this.temperature = temperature; // Now accepting temperature as double
            this.additionalMetrics = additionalMetrics != null ? additionalMetrics : ""; // Initialize to empty string if null
        }

        // Getters and Setters
        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public String getBloodPressure() {
            return bloodPressure;
        }

        public void setBloodPressure(String bloodPressure) {
            this.bloodPressure = bloodPressure;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public String getAdditionalMetrics() {
            return additionalMetrics;
        }

        public void setAdditionalMetrics(String additionalMetrics) {
            this.additionalMetrics = additionalMetrics != null ? additionalMetrics : ""; // Ensure it's not null
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Weight: ").append(weight).append(" kg\n");
            sb.append("Height: ").append(height).append(" m\n");
            sb.append("Blood Pressure: ").append(bloodPressure).append("\n");
            sb.append("Temperature: ").append(temperature).append(" °C\n");

            // Check if additionalMetrics is not null and not empty
            if (this.additionalMetrics != null && !this.additionalMetrics.isEmpty()) {
                sb.append("Additional Metrics: ").append(this.additionalMetrics).append("\n");
            } else {
                sb.append("Additional Metrics: Not available\n");
            }

            return sb.toString();
        }
    }
}

