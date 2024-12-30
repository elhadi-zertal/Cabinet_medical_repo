package main.classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MedicalCertificate {
    private String certificateId;
    private Patient patient;
    private Doctor doctor;
    private String purpose;      // Purpose of the certificate (e.g., work absence, sports)
    private String description;  // Medical observations or restrictions
    private Date issueDate;
    private Date validUntil;     // Optional expiration date

    public MedicalCertificate(String certificateId, Patient patient, Doctor doctor, 
            String purpose, String description, Date issueDate, Date validUntil) {
        this.certificateId = certificateId;
        this.patient = patient;
        this.doctor = doctor;
        this.purpose = purpose;
        this.description = description;
        this.issueDate = issueDate;
        this.validUntil = validUntil;
    }
 private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    // Method to generate a formatted certificate text
    public String generateCertificateText() {
        StringBuilder sb = new StringBuilder();
        sb.append(" \nMedical Certificate\n");
        sb.append("I, Dr. ").append(doctor.getDoctorName())
          .append(", hereby certify that I have examined the following individual today:\n\n");
        sb.append("Mr./Mrs. ").append(patient.getName())
          .append("\n\nPurpose: ").append(purpose) // Ensure this is only added once
          .append("\n\nObservations: ").append(description)
          .append("\n\nCertificate issued on: ").append(DATE_FORMAT.format(issueDate)); // Format the issue date
    
        if (validUntil != null) {
            sb.append("\nValid until: ").append(DATE_FORMAT.format(validUntil)); // Format the valid until date
        }
    
        sb.append("\n\nDoctor's Signature:\nDr. ").append(doctor.getDoctorName())
          .append("\n").append(doctor.getSpecialization()); // Assuming you have a specialization field
    
        return sb.toString();
    }

    // Getters and setters
    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }
}
