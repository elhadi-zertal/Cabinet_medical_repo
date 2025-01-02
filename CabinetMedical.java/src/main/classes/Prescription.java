package main.classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Prescription {
    private final String prescriptionId;
    private Doctor doctor;
    private Patient patient;
    private final Set<Medication> medications;
    private LocalDateTime prescriptionDate;
    private LocalDateTime validityDate;
    private final List<String> medicalExams;
    private String additionalNotes;
    private static List<Prescription> prescriptions = new ArrayList<>(); // Static list to hold all prescriptions
    // Constructor with builder pattern
    private Prescription(Builder builder) {
        this.prescriptionId = Objects.requireNonNull(builder.prescriptionId, "Prescription ID cannot be null");
        this.doctor = Objects.requireNonNull(builder.doctor, "Doctor cannot be null");
        this.patient = Objects.requireNonNull(builder.patient, "Patient cannot be null");
        this.medications = new LinkedHashSet<>(Objects.requireNonNull(builder.medications, "Medications cannot be null"));
        this.prescriptionDate = Objects.requireNonNull(builder.prescriptionDate, "Prescription date cannot be null");
        this.validityDate = Objects.requireNonNull(builder.validityDate, "Validity date cannot be null");
        this.medicalExams = new ArrayList<>(Objects.requireNonNull(builder.medicalExams, "Medical exams cannot be null"));
        this.additionalNotes = builder.additionalNotes;
    }

    // Builder class
    public static class Builder {
        private final String prescriptionId;
        private Doctor doctor;
        private Patient patient;
        private Set<Medication> medications = new LinkedHashSet<>();
        private LocalDateTime prescriptionDate;
        private LocalDateTime validityDate;
        private List<String> medicalExams = new ArrayList<>();
        private String additionalNotes;

        public Builder(String prescriptionId) {
            this.prescriptionId = prescriptionId;
        }

        public Builder doctor(Doctor doctor) {
            this.doctor = doctor;
            return this;
        }

        public Builder patient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public Builder medications(Set<Medication> medications) {
            this.medications = new LinkedHashSet<>(medications);
            return this;
        }

        public Builder prescriptionDate(LocalDateTime prescriptionDate) {
            this.prescriptionDate = prescriptionDate;
            return this;
        }

        public Builder validityDate(LocalDateTime validityDate) {
            this.validityDate = validityDate;
            return this;
        }

        public Builder medicalExams(List<String> medicalExams) {
            this.medicalExams = new ArrayList<>(medicalExams);
            return this;
        }

        public Builder additionalNotes(String additionalNotes) {
            this.additionalNotes = additionalNotes;
            return this;
        }

        public Prescription build() {
            return new Prescription(this);
        }
    }

    // Getters
    public String getPrescriptionId() {
        return prescriptionId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public Set<Medication> getMedications() {
        return Collections.unmodifiableSet(medications);
    }

    public LocalDateTime getPrescriptionDate() {
        return prescriptionDate;
    }

    public LocalDateTime getValidityDate() {
        return validityDate;
    }

    public List<String> getMedicalExams() {
        return Collections.unmodifiableList(medicalExams);
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }
    

    public static List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    // Setters with validation
    public void setDoctor(Doctor doctor) {
        this.doctor = Objects.requireNonNull(doctor, "Doctor cannot be null");
    }

    public void setPatient(Patient patient) {
        this.patient = Objects.requireNonNull(patient, "Patient cannot be null");
    }

    public void setPrescriptionDate(LocalDateTime prescriptionDate) {
        this.prescriptionDate = Objects.requireNonNull(prescriptionDate, "Prescription date cannot be null");
    }

    public void setValidityDate(LocalDateTime validityDate) {
        this.validityDate = Objects.requireNonNull(validityDate, "Validity date cannot be null");
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
    public void setDetails(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
    

   
    // Medication Management Methods
    public boolean addMedication(Medication medication) {
        return Objects.requireNonNull(medication, "Medication cannot be null") != null 
            && medications.add(medication);
    }

    public boolean removeMedication(Medication medication) {
        return Objects.requireNonNull(medication, "Medication cannot be null") != null 
            && medications.remove(medication);
    }

    public boolean addMedicalExam(String exam) {
        return Objects.requireNonNull(exam, "Medical exam cannot be null") != null 
            && medicalExams.add(exam);
    }

    public boolean removeMedicalExam(String exam) {
        return Objects.requireNonNull(exam, "Medical exam cannot be null") != null 
            && medicalExams.remove(exam);
    }

    // Optimized search methods using streams
    public Optional<Medication> findMedicationByName(String name) {
        Objects.requireNonNull(name, "Medication name cannot be null");
        return medications.stream()
                .filter(med -> med.getName().equalsIgnoreCase(name))
                .findFirst();
    }

// Methods to get prescriptions by patient ID name or ID 
public static List<Prescription> getPrescriptionsByPatientId(String patientId) {
    List<Prescription> patientPrescriptions = new ArrayList<>();
    for (Prescription prescription : prescriptions) {
        if (prescription.getPatient().getId().equals(patientId)) {
            patientPrescriptions.add(prescription);
        }
    }
    return patientPrescriptions; // Return the list of prescriptions for the specified patient ID
}
public static List<Prescription> getPrescriptionsByPatientName(String patientName) {
    return prescriptions.stream()
            .filter(p -> p.getPatient().getName().equalsIgnoreCase(patientName))
            .collect(Collectors.toList());
}
public static Prescription getPrescriptionById(String prescriptionId) {
    return prescriptions.stream()
            .filter(p -> p.getPrescriptionId().equals(prescriptionId))
            .findFirst()
            .orElse(null);
}
    // Validation Methods
   

    public boolean hasMedicationConflicts() {
        return medications.size() != medications.stream()
                .map(Medication::getName)
                .collect(Collectors.toSet())
                .size();
    }

    public boolean isValidPrescriptionPeriod() {
        LocalDateTime now = LocalDateTime.now();
        return prescriptionDate.isBefore(validityDate) && validityDate.isAfter(now);
    }

    // Utility Methods
    public long getDaysUntilExpiration() {
        return ChronoUnit.DAYS.between(LocalDateTime.now(), validityDate);
    }

    public String generatePrescriptionSummary() {
        StringBuilder summary = new StringBuilder(256);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        
        summary.append("Prescription Summary for ").append(patient.getName())
               .append("\nPrescribed by: Dr. ").append(doctor.getDoctorName())
               .append("\nDate: ").append(prescriptionDate.format(dateFormatter))
               .append("\nValid until: ").append(validityDate.format(dateFormatter))
               .append("\n\nMedications:");
        
        medications.forEach(med -> summary.append("\n- ").append(med));
        
        if (!medicalExams.isEmpty()) {
            summary.append("\n\nMedical Exams:");
            medicalExams.forEach(exam -> summary.append("\n- ").append(exam));
        }
        
        if (additionalNotes != null && !additionalNotes.isEmpty()) {
            summary.append("\n\nAdditional Notes:\n").append(additionalNotes);
        }
        
        return summary.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prescription)) return false;
        Prescription that = (Prescription) o;
        return Objects.equals(prescriptionId, that.prescriptionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prescriptionId);
    }

    @Override
    public String toString() {
        return new StringBuilder(128)
            .append("Prescription{prescriptionId='").append(prescriptionId)
            .append("', doctor=").append(doctor.getDoctorName())
            .append(" (").append(doctor.getSpecialization()).append(')')
            .append(", patient=").append(patient.getName())
            .append(" (").append(patient.getId()).append(')')
            .append(", medications=").append(medications)
            .append(", prescriptionDate=").append(prescriptionDate)
            .append(", validityDate=").append(validityDate)
            .append(", medicalExams=").append(medicalExams)
            .append(", additionalNotes='").append(additionalNotes).append('\'')
            .append('}')
            .toString();
    }
}
